# --- !Ups

# --- set ignorecase true;

CREATE TABLE tags (
  id                        BIGINT NOT NULL AUTO_INCREMENT,
  name                      VARCHAR(255) NOT NULL,
  created_date               DATETIME NOT NULL DEFAULT NOW(),
  CONSTRAINT pk_tags PRIMARY KEY (id))
;

# --- !Downs

drop table if exists tags;
