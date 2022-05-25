-- demo purpose, delete every start
DELETE
FROM demo_reaction;
DELETE
FROM demo_comment;
DELETE
FROM demo_post;
DELETE
FROM demo_user;

-- for demo purpose
ALTER SEQUENCE hibernate_sequence RESTART WITH 10;

-- create users
INSERT INTO demo_user (id, name)
VALUES (1, 'Gary');
INSERT INTO demo_user (id, name)
VALUES (2, 'Peter');
INSERT INTO demo_user (id, name)
VALUES (3, 'Mary');
INSERT INTO demo_user (id, name)
VALUES (4, 'Celia');

-- Gary created two post, post_id = [1,2]
INSERT INTO demo_post (id, owner_id, content)
VALUES (1, 1, 'foo1');
INSERT INTO demo_post (id, owner_id, content)
VALUES (2, 1, 'foo2');

-- Peter created two post, post_id = [3,4]
INSERT INTO demo_post (id, owner_id, content)
VALUES (3, 2, 'foo3');
INSERT INTO demo_post (id, owner_id, content)
VALUES (4, 2, 'foo4');

-- Gary create first comment for post_id = 1
INSERT INTO demo_comment (id, owner_id, post_id, content)
VALUES (1, 1, 1, 'gary comment');

-- Peter reply Gary's comment for comment_id = 1, post_id = 1
INSERT INTO demo_comment (id, owner_id, post_id, content)
VALUES (2, 2, 1, 'peter comment');

-- Gary like his own comment, for comment_id = 1, post_id = 1
INSERT INTO demo_reaction (id, reaction_type, comment_id, owner_id, post_id)
VALUES (1, 1, 1, 1, 1);

-- Gary like Peter's comment for comment_id = 2, post_id = 1
INSERT INTO demo_reaction (id, reaction_type, comment_id, owner_id, post_id)
VALUES (2, 1, 2, 1, 1);

-- Peter like Gary's comment for comment_id = 1, post_id = 1
INSERT INTO demo_reaction (id, reaction_type, comment_id, owner_id, post_id)
VALUES (3, 1, 1, 2, 1);

-- Mary like Gary's comment
INSERT INTO demo_reaction (id, reaction_type, comment_id, owner_id, post_id)
VALUES (4, 1, 1, 3, 1);

-- Celia like Gary's comment
INSERT INTO demo_reaction (id, reaction_type, comment_id, owner_id, post_id)
VALUES (5, 1, 1, 4, 1);
