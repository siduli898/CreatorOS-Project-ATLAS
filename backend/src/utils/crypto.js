const crypto = require('crypto');
function hashPassword(password){const salt=crypto.randomBytes(16).toString('hex');const key=crypto.scryptSync(password,salt,64);return `${salt}:${key.toString('hex')}`;}
function verifyPassword(password,stored){const [salt,key]=stored.split(':');if(!salt||!key)return false;const derived=crypto.scryptSync(password,salt,64);return crypto.timingSafeEqual(Buffer.from(key,'hex'),derived);}
function generateSecureToken(bytes=32){return crypto.randomBytes(bytes).toString('hex');}
function generateUUID(){return crypto.randomUUID();}
function signJwt(payload,secret,expiresInSec=900){const h=Buffer.from(JSON.stringify({alg:'HS256',typ:'JWT'})).toString('base64url');const p=Buffer.from(JSON.stringify({...payload,exp:Math.floor(Date.now()/1000)+expiresInSec,iat:Math.floor(Date.now()/1000)})).toString('base64url');const s=crypto.createHmac('sha256',secret).update(`${h}.${p}`).digest('base64url');return `${h}.${p}.${s}`;}
function verifyJwt(token,secret){const parts=token.split('.');if(parts.length!==3)return null;const [h,p,s]=parts;const expected=crypto.createHmac('sha256',secret).update(`${h}.${p}`).digest('base64url');if(!crypto.timingSafeEqual(Buffer.from(s),Buffer.from(expected)))return null;const payload=JSON.parse(Buffer.from(p,'base64url').toString());return payload.exp&&payload.exp<Math.floor(Date.now()/1000)?null:payload;}
module.exports={hashPassword,verifyPassword,generateSecureToken,generateUUID,signJwt,verifyJwt};
