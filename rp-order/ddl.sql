create table rp_order (
	ord_no varchar(20) not null,
	mbr_id varchar(20) not null,
	ord_tot_amt long,
	ord_stat_cd char(6),
	
	primary key (ord_no)
);

create table rp_order_prod (
	ord_no varchar(20) not null,
	seq int(3) not null,
	prod_nm varchar(100) not null,
	prod_amt long,
	ord_prod_cnt int(3),
	purch_cfrm_dt datetime,
	seller_id varchar(20) not null,
	
	primary key(ord_no, seq)
);

create table rp_delv (
	ord_no varchar(20) not null,
	addr varchar(100),
	shp_no varchar(20),
	
	primary key(ord_no)
);