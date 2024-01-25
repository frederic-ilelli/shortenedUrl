CREATE TABLE shortened_urls (
   id UUID NOT NULL,
   long_url VARCHAR(255),
   short_url VARCHAR(255),
   code VARCHAR(255),
   created_on_utc date,
   CONSTRAINT pk_shortenedurls PRIMARY KEY (id)
);