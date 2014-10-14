create table t_board(
board_id int primary key IDENTITY(1,1),
board_name varchar(150) not null default '',
board_desc varchar(255) null default NULL,
board_num int not null default 0
)

create table t_board_manager(
boeard_id int not null,
user_id int not null,
primary key(boeard_id,user_id)
)

create table t_topic(
topic_id int primary key IDENTITY(1,1),
board_id int not null,
topic_title varchar(100) not null default '',
user_id int not null,
create_time datetime not null,
last_post datetime not null,
topic_views int not null,
topic_replies int not null,
digest int not null
)

create table t_post(
post_id int primary key IDENTITY(1,1),
board_id int not null,
topic_id int not null,
user_id int not null,
post_type tinyint not null default 2,
post_text text not null,
create_time datetime not null
)

create table t_user(
user_id int primary key identity(1,1),
user_name varchar(30) not null,
password varchar(40) not null,
user_type tinyint not null default 1,
locked tinyint not null default 0,
credit int default 0
)

create table t_login_log(
login_log_id int primary key identity(1,1),
user_id int not null,
ip varchar(30) not null,
login_datetime datetime not null
)