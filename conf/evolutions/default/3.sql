# --- First database schema

# --- !Ups

ALTER TABLE blogposts ADD COLUMN updated_date DATETIME DEFAULT NOW()  NOT NULL;
ALTER TABLE blogposts ADD COLUMN created_date DATETIME DEFAULT NOW()  NOT NULL;

# --- !Downs

ALTER TABLE blogposts drop column updated_date;
ALTER TABLE blogposts drop column created_date;
