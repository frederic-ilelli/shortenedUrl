ALTER TABLE shortened_urls
    ADD CONSTRAINT uc_shortenedurls_code UNIQUE (code);

ALTER TABLE shortened_urls
    ALTER COLUMN code TYPE VARCHAR(7) USING (code::VARCHAR(7));

ALTER TABLE shortened_urls
    ALTER COLUMN code SET NOT NULL;

ALTER TABLE shortened_urls
    DROP COLUMN created_on_utc;

ALTER TABLE shortened_urls
    ADD created_on_utc TIMESTAMP WITHOUT TIME ZONE NOT NULL;

ALTER TABLE shortened_urls
    ALTER COLUMN created_on_utc SET NOT NULL;

ALTER TABLE shortened_urls
    ALTER COLUMN long_url SET NOT NULL;

ALTER TABLE shortened_urls
    ALTER COLUMN short_url SET NOT NULL;