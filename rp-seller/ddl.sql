create table rp_seller (
	id bigint auto_increment,
	name varchar(100) not null,
	
	primary key (id)
);

create table rp_product (
	id bigint auto_increment,
	name varchar(100) not null,
	price long,
	seller_id long not null,
	
	primary key(id)
);