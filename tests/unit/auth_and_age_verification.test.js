const assert=require('assert');
const auth=require('../../backend/src/domains/auth/service');
const compliance=require('../../backend/src/domains/compliance/service');
const user=auth.register({email:`age_${Date.now()}@example.com`,password:'SecurePassword2026!',username:`age_${Date.now()}`,is18PlusDeclared:true});
assert.strictEqual(user.isAgeVerified,false);
assert.throws(()=>auth.register({email:`minor_${Date.now()}@example.com`,password:'SecurePassword2026!',is18PlusDeclared:false}),/18\+/);
assert.throws(()=>compliance.verifyAdultAge({userId:user.id,documentType:'PASSPORT',legalDob:'2015-01-01'}),/under 18/);
assert.strictEqual(compliance.verifyAdultAge({userId:user.id,documentType:'PASSPORT',legalDob:'1995-05-15'}).isAgeVerified,true);
console.log('auth/age tests passed');
