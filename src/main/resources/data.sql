CREATE TYPE pings.post_status_info AS ENUM (
    'EXPIRED', 'BANNED'
);
;
INSERT INTO pings.user (login) VALUES ('dan')