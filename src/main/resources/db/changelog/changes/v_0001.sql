drop table if exists mood cascade;
drop table if exists medicine cascade;

create table mood (
  uuid uuid primary key,
  time timestamp,

  joy integer default 5,
  focus integer default 5,
  anxiety integer default 0,
  irritability integer default 0,

  sleep integer,
  sport integer,

  coffee integer,

  comment text
);

create table medicine (
  uuid uuid primary key,
  mood_uuid uuid references mood(uuid),

  type varchar(50),
  unit varchar(25),
  amount numeric
);