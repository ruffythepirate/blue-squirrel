# --- !Ups

set ignorecase true;

CREATE TABLE blogposts_tags (
  tags_id      BIGINT NOT NULL,
  blogposts_id BIGINT NOT NULL,
  FOREIGN KEY(tags_id) REFERENCES tags(id),
  FOREIGN KEY(blogposts_id) REFERENCES blogposts(id),
  PRIMARY KEY(tags_id, blogposts_id)
);

# --- !Downs

drop table if exists blogposts_tags;
