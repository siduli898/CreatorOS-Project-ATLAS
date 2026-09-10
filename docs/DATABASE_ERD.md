# Project ATLAS Database ERD Specification

Core storage uses PostgreSQL with UUID primary keys, strict foreign keys, normalized domain tables, JSONB metadata where appropriate, and BIGINT integer currency values in cents.

Core entities include users, creator profiles, performer compliance records, content/media, subscriptions, PPV purchases, marketplace listings/orders, ledger accounts/transactions/entries, live rooms, conversations/messages, moderation cases, and audit logs.

Financial invariant: every ledger transaction must have balanced entry legs whose sum is exactly zero.
