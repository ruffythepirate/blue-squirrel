# --- First database schema

# --- !Ups

set ignorecase true;

ALTER TABLE blogposts ADD COLUMN updated_date DATE DEFAULT(NOW()) NOT NULL;
--ADD COLUMN created_date DATE DEFAULT(NOW()) NOT NULL
--ADD INDEX updated_date;
--insert into blogposts (id,title,body) values (3, 'Example 3', 'Text 3');

# --- !Downs

--delete from blogposts
ALTER TABLE blogposts drop column updated_date;
--drop column created_date;