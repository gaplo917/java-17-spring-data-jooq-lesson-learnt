create table if not exists demo_user
(
    id   bigint not null
        primary key,
    name varchar(255)
);

create table if not exists demo_post
(
    id       bigint not null primary key,
    content  text,
    owner_id bigint
        constraint fk3vcb4bg67kh3kkpwa6ubef2cv
            references demo_user
);

create table if not exists demo_comment
(
    id       bigint not null
        primary key,
    content  text,
    owner_id bigint
        constraint fkl5vfcchs6bcq1rbfdci49jg5k
            references demo_user,
    post_id  bigint
        constraint fk5v0uvvsi31gp6ehv7pyrd1gy
            references demo_post
);

create table if not exists demo_reaction
(
    id            bigint  not null
        primary key,
    reaction_type integer not null,
    comment_id    bigint
        constraint fke3x36tk0hg7ss256dppt68ine
            references demo_comment,
    owner_id      bigint
        constraint fkdbin9dw4gve23vwotnrdl35mi
            references demo_user,
    post_id       bigint
        constraint fkhhtal4lrtc4el1ifppxf1orte
            references demo_post,
    constraint uk88ad5qtxpmsn0y8fpxqvgrp9c
        unique (owner_id, comment_id, post_id)
);
