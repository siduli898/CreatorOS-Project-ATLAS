const auditLogStore=[];
function recordAudit({actorUserId,actorRole,action,resourceType,resourceId,ipAddress,userAgent,details}){const entry={id:require('crypto').randomUUID(),actorUserId:actorUserId||'SYSTEM',actorRole:actorRole||'SYSTEM',action,resourceType,resourceId:String(resourceId),ipAddress:ipAddress||'127.0.0.1',userAgent:userAgent||'Internal/Agent',details:details||{},timestamp:new Date().toISOString()};auditLogStore.push(entry);return entry;}
function getAuditLogs(){return [...auditLogStore];}
module.exports={recordAudit,getAuditLogs};
