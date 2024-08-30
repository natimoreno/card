create table IF not exists CARD(
  ID bigint not null AUTO_INCREMENT,
  NAME varchar(200) not null,
  SEGMENT varchar(200) not null,
  STATUS varchar(100) not null,
  PRIMARY KEY ( ID )
);

CREATE UNIQUE INDEX "INDEX_NAME_SEGMENT" ON "CARD"
  (
    "NAME", "SEGMENT"
  );