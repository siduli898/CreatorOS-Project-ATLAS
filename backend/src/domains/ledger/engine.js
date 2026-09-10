const { generateUUID } = require('../../utils/crypto');
const { recordAudit } = require('../../utils/audit');
class LedgerEngine {
 constructor(){this.accounts=new Map();this.transactions=new Map();this.entries=[];this.idempotencyMap=new Map();}
 createAccount(type,ownerUserId=null,currency='USD'){const a={id:generateUUID(),type,ownerUserId,currency,balanceCents:0,createdAt:new Date().toISOString()};this.accounts.set(a.id,a);return a;}
 getAccount(id){return this.accounts.get(id)||null;}
 getAccountByOwnerAndType(owner,type){for(const a of this.accounts.values())if(a.ownerUserId===owner&&a.type===type)return a;return null;}
 getOrCreateUserWallet(id){return this.getAccountByOwnerAndType(id,'LIABILITY_CUSTOMER_WALLET')||this.createAccount('LIABILITY_CUSTOMER_WALLET',id);}
 getOrCreateCreatorPayable(id){return this.getAccountByOwnerAndType(id,'LIABILITY_CREATOR_PAYABLE')||this.createAccount('LIABILITY_CREATOR_PAYABLE',id);}
 getSystemAccount(type){return this.getAccountByOwnerAndType('SYSTEM',type)||this.createAccount(type,'SYSTEM');}
 recordTransaction({idempotencyKey,entryType,referenceEntityType,referenceEntityId,description,legs,actorUserId='SYSTEM'}){
  if(this.idempotencyMap.has(idempotencyKey)){const id=this.idempotencyMap.get(idempotencyKey);return {duplicate:true,transaction:this.transactions.get(id)};}
  if(!Array.isArray(legs)||legs.length<2)throw new Error('Ledger transaction must have at least two legs.');
  const sum=legs.reduce((s,l)=>s+l.amountCents,0);if(!legs.every(l=>Number.isInteger(l.amountCents)))throw new Error('All ledger amounts must be strict integers (minor units/cents).');if(sum!==0)throw new Error(`Ledger invariant violation: Debits and Credits do not balance (sum = ${sum}).`);
  for(const l of legs){const a=this.accounts.get(l.accountId);if(!a)throw new Error(`Target ledger account ${l.accountId} does not exist.`);if((a.type==='LIABILITY_CUSTOMER_WALLET'||a.type==='LIABILITY_CREATOR_PAYABLE')&&a.balanceCents+l.amountCents<0)throw new Error(`Insufficient funds: account ${a.id} balance cannot become negative.`);}
  const tx={id:generateUUID(),idempotencyKey,entryType,referenceEntityType,referenceEntityId,description,createdAt:new Date().toISOString()};const entries=[];
  for(const l of legs){const a=this.accounts.get(l.accountId);a.balanceCents+=l.amountCents;const e={id:generateUUID(),transactionId:tx.id,accountId:a.id,amountCents:l.amountCents,runningBalanceAfterCents:a.balanceCents,createdAt:new Date().toISOString()};this.entries.push(e);entries.push(e);}
  this.transactions.set(tx.id,tx);this.idempotencyMap.set(idempotencyKey,tx.id);recordAudit({actorUserId,actorRole:'LEDGER_ENGINE',action:'RECORD_TRANSACTION',resourceType:'LEDGER_TRANSACTION',resourceId:tx.id});return {duplicate:false,transaction:tx,entries};
 }
 getStatement(id){const a=this.accounts.get(id);if(!a)return null;return {accountId:a.id,accountType:a.type,currency:a.currency,currentBalanceCents:a.balanceCents,availableBalanceCents:a.balanceCents,pendingEscrowCents:0,entries:this.entries.filter(e=>e.accountId===id)};}
}
module.exports=new LedgerEngine();
