CREATE TABLE IF NOT EXISTS user (
  id int auto_increment primary key,
  username varchar(40) not null,
  phone varchar(20) default '',
  email varchar(40) default '',
  password varchar(64) not null,
  gmt_created date not null,
  gmt_modified date not null
)