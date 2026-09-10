module.exports = {
  PORT: process.env.PORT || 8080,
  JWT_SECRET: process.env.JWT_SECRET || 'project_atlas_super_secure_jwt_secret_2026_creatoros_prod',
  JWT_ACCESS_EXPIRY_SEC: 900,
  REFRESH_TOKEN_EXPIRY_DAYS: 30,
  MAX_LOGIN_ATTEMPTS: 5,
  LOCKOUT_MINUTES: 15,
  MINIMUM_PAYOUT_CENTS: 2000,
  PLATFORM_FEE_BPS: 1500,
  MAX_PPV_PRICE_CENTS: 50000,
  PROHIBITED_TERMS: ['escort', 'meetup', 'in person', 'hotel meet', 'outcall', 'incall', 'massage', 'sugar baby', 'full service', 'physical meetup', 'cash in person']
};
