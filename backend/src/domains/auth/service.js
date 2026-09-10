const { hashPassword, verifyPassword, generateUUID, signJwt } = require('../../utils/crypto');
const config = require('../../config');
const { recordAudit } = require('../../utils/audit');
const ledger = require('../ledger/engine');

class AuthService {
  constructor() { this.users = new Map(); this.usersById = new Map(); this.sessions = new Map(); }
  register({ email, password, username, displayName, is18PlusDeclared }) {
    if (!is18PlusDeclared) throw new Error('Mandatory 18+ adult age declaration required to register.');
    if (this.users.has(email.toLowerCase())) throw new Error('An account with this email address already exists.');
    if (!password || password.length < 10) throw new Error('Password must be at least 10 characters long.');
    const userId = generateUUID();
    const user = { id:userId,email:email.toLowerCase(),username:username || `user_${userId.substring(0,8)}`,displayName:displayName || username || 'CreatorOS Member',passwordHash:hashPassword(password),role:'FAN',is18PlusDeclared:true,isAgeVerified:false,ageVerifiedAt:null,failedAttempts:0,lockedUntil:null,createdAt:new Date().toISOString() };
    this.users.set(user.email,user); this.usersById.set(userId,user); ledger.getOrCreateUserWallet(userId);
    recordAudit({actorUserId:userId,actorRole:'FAN',action:'USER_REGISTRATION',resourceType:'USER',resourceId:userId});
    return user;
  }
  login({ email, password, ipAddress='127.0.0.1', userAgent='Android-Client' }) {
    const user=this.users.get(email.toLowerCase()); if(!user) throw new Error('Invalid email or password.');
    if(user.lockedUntil && new Date(user.lockedUntil)>new Date()) throw new Error('Account temporarily locked due to repeated failed login attempts.');
    if(!verifyPassword(password,user.passwordHash)){ user.failedAttempts+=1; if(user.failedAttempts>=config.MAX_LOGIN_ATTEMPTS) user.lockedUntil=new Date(Date.now()+config.LOCKOUT_MINUTES*60000).toISOString(); throw new Error('Invalid email or password.'); }
    user.failedAttempts=0; user.lockedUntil=null;
    const accessToken=signJwt({sub:user.id,email:user.email,role:user.role,isAgeVerified:user.isAgeVerified},config.JWT_SECRET,config.JWT_ACCESS_EXPIRY_SEC);
    const refreshToken=generateUUID(); this.sessions.set(refreshToken,{userId:user.id,ipAddress,userAgent,createdAt:new Date().toISOString()});
    recordAudit({actorUserId:user.id,actorRole:user.role,action:'USER_LOGIN',resourceType:'SESSION',resourceId:user.id});
    return {user,accessToken,refreshToken};
  }
  getUserById(id){ return this.usersById.get(id)||null; }
}
module.exports = new AuthService();
