create table rp_member (
	id varchar(20) not null,
	name varchar(100) not null,
	birth char(8) not null,
	sex char(1) not null,
	addr varchar(100),
	
	primary key(id)
);