

use fisa;


-- 고객 테이블
drop table if exists customer;

-- 등급 테이블
drop table if exists grade;

-- 메뉴 테이블
drop table if exists menu;

-- 주문 테이블
drop table if exists sale;


create table customer(
	cust_id varchar(5),
	cust_name varchar(45) not null,
	cust_phone varchar(15) not null,
	cust_birth date not null,
	gender varchar(1),
	amount int,
	grade_id int,
    primary key(cust_id),
    foreign key (grade_id) references grade(grade_id)
	);



desc customer;

select * from information_schema.TABLE_CONSTRAINTS tc
where table_name='customer';

create table grade(
	grade_id int,
	grade_name varchar(30),
	standard int,
	primary key (grade_id)		
	);


	
create table menu(
	menu_id int,
	menu_name varchar(50),
	price int,
	categories int,
	calorie int
	);
	
create table sale(
	order_id int,
	order_date timestamp,
	total int,
	cust_id varchar(5),
	primary key (order_id),
	foreign key (cust_id) references customer(cust_id)
);

desc sale;



-- select * from information_schema.TABLE_CONSTRAINTS tc
-- where table_name='sale';
-- alter talbe

desc customer;

select * from information_schema.TABLE_CONSTRAINTS tc
where table_name='customer';

create table grade(
	grade_id int,
	grade_name varchar(30),
	standard int,
	primary key (grade_id)		
	);


	
create table menu(
	menu_id int,
	menu_name varchar(50),
	price int,
	categories int,
	calorie int
	);
	
create table sale(
	order_id int,
	order_table timestamp,
	total int,
	cust_id varchar(5),
	primary key (order_id),
	foreign key (cust_id) references customer(cust_id)
);

desc sale;

select * from information_schema.TABLE_CONSTRAINTS tc
where table_name='sale';
alter talbe
alter table sale add foreign key (cust_id) references Cafecustomer(cust_id);