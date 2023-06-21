CREATE TABLE IF NOT EXISTS url (
   id serial PRIMARY KEY,
   url VARCHAR (2000) NOT NULL,
   shortened_url VARCHAR (2000),
   access_count INTEGER NOT NULL DEFAULT 0
);