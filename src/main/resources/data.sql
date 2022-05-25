DELETE FROM demo_reaction;
DELETE FROM demo_comment;
DELETE FROM demo_post;
DELETE FROM demo_user;

-- for demo purpose
ALTER SEQUENCE hibernate_sequence RESTART WITH 10;

-- create two users, Gary, Peter
INSERT INTO demo_user (id, name) VALUES (1, 'Gary');
INSERT INTO demo_user (id, name) VALUES (2, 'Peter');

-- Gary created two post, post_id = [1,2]
INSERT INTO demo_post (id, owner_id, content) VALUES (1, 1, 'foo1');
INSERT INTO demo_post (id, owner_id, content) VALUES (2, 1, 'foo2');

-- Peter created two post, post_id = [3,4]
INSERT INTO demo_post (id, owner_id, content) VALUES (3, 2, 'foo3');
INSERT INTO demo_post (id, owner_id, content) VALUES (4, 2, 'foo4');

-- Gary create first comment for post_id = 1
INSERT INTO demo_comment (id, owner_id, post_id, content) VALUES (1, 1, 1, 'gary comment');

-- Peter reply Gary's comment for comment_id = 1, post_id = 1
INSERT INTO demo_comment (id, owner_id, post_id, content) VALUES (2, 2, 1, 'peter comment');

-- Gary like his own comment, for comment_id = 1, post_id = 1
INSERT INTO demo_reaction (id, reaction_type, comment_id, owner_id, post_id) VALUES (1, 1, 1, 1, 1);

-- Gary like peter comment for comment_id = 2, post_id = 1
INSERT INTO demo_reaction (id, reaction_type, comment_id, owner_id, post_id) VALUES (2, 1, 2, 1, 1);

-- Peter like Gary comment for comment_id = 1, post_id = 1
INSERT INTO demo_reaction (id, reaction_type, comment_id, owner_id, post_id) VALUES (3, 1, 1, 2, 1);


