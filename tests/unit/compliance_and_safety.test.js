const assert=require('assert');
const market=require('../../backend/src/domains/marketplace/service');
const moderation=require('../../backend/src/domains/moderation/service');
assert.throws(()=>market.createListing({creatorId:'c',title:'In-person hotel meetup',description:'physical meetup',category:'DIGITAL_ART',basePriceCents:2000,deliveryDays:1}),/prohibits physical meetups/);
const listing=market.createListing({creatorId:'c',title:'Digital avatar',description:'Vector artwork',category:'DIGITAL_ART',basePriceCents:8500,deliveryDays:4});
assert.strictEqual(listing.isApprovedBySafety,true);
assert.strictEqual(moderation.fileReport({reporterUserId:'u',targetEntityType:'USER',targetEntityId:'x',reasonCode:'CSAM_SUSPICION'}).severity,'CRITICAL_LEGAL');
console.log('compliance/safety tests passed');
