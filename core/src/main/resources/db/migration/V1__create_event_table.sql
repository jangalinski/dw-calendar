create table EVENT (
       UID varchar(255) primary key,
       SUMMARY varchar(255) not null,
       START timestamp not null,
       END timestamp,
       CREATED timestamp not null
);
