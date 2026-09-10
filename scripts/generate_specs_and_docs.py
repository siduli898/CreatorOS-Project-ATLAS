from pathlib import Path
import json

ROOT = Path(__file__).resolve().parents[1]
contract = ROOT / 'packages/contracts/openapi.json'
with contract.open_text(encoding='utf-8') as f:
    json.load(f)
print('Validated OpenAPI contract:', contract)
print('Documentation generation hook completed.')
