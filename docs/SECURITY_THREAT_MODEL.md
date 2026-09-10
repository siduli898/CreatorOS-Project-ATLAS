# Project ATLAS Security Threat Model

## STRIDE Controls
- Spoofing: strong password hashing, short-lived access tokens and refresh-token sessions.
- Tampering: server-side authorization, entitlement and ledger calculations.
- Repudiation: immutable-style audit events for security and financial actions.
- Information disclosure: segregated compliance records and authenticated media access.
- Denial of service: planned rate limiting, upload controls and infrastructure isolation.
- Elevation of privilege: role checks on protected API routes.

## Adult Safety
Age is explicitly verified rather than inferred from appearance. Performer compliance records and consent metadata are treated as restricted data. Illegal or non-consensual content must be removed and escalated according to applicable law.
