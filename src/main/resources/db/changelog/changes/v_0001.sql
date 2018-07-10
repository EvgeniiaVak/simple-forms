create table mood (
  "uuid" uuid not null,
  time timestamp,
  mood integer,
  focus integer,
  primary key ("uuid")
)