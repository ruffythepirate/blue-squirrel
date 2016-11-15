# --- Sample dataset

# --- !Ups

insert into blogposts (id,title,body) values (1, 'Example 1', 'Text 1');
insert into blogposts (id,title,body) values (2, 'Example 2', 'Text 2');

# --- !Downs

delete from blogposts;
