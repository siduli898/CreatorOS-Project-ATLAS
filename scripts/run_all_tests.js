const { spawnSync } = require('node:child_process');
const fs = require('node:fs');
const path = require('node:path');
const root = path.join(process.cwd(), 'tests');
const files = [];
function walk(dir) { for (const e of fs.readdirSync(dir,{withFileTypes:true})) { const p=path.join(dir,e.name); if(e.isDirectory()) walk(p); else if(e.name.endsWith('.test.js')) files.push(p); } }
walk(root);
let failed=false;
for (const file of files.sort()) { console.log(`\n=== ${file} ===`); const r=spawnSync(process.execPath,['--test',file],{stdio:'inherit'}); if(r.status!==0) failed=true; }
process.exit(failed?1:0);
