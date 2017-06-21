create table rp_seller (
	id long not null,
	name varchar(100) not null,
	
	primary key (long)
);

create table rp_product (
	id long not null,
	name varchar(100) not null,
	price long,
	seller_id long not null,
	
	primary key(id)
);