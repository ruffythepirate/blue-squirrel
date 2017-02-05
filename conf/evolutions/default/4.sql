# --- !Ups

CREATE TABLE tags (
  id                        BIGINT NOT NULL AUTO_INCREMENT,
  name                      VARCHAR(255) NOT NULL,
  created_date               DATETIME DEFAULT NOW() NOT NULL ,
  CONSTRAINT pk_tags PRIMARY KEY (id))
;

# --- !Downs

drop table if exists tags;
