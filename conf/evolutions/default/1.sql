# --- First database schema

# --- !Ups

# --- set ignorecase true;

CREATE TABLE blogposts (
  id                        BIGINT NOT NULL AUTO_INCREMENT,
  title                      VARCHAR(255) NOT NULL,
  body                       VARCHAR(1000) NOT NULL,
  CONSTRAINT pk_blogposts PRIMARY KEY (id))
;

# --- !Downs

drop table if exists blogposts;
