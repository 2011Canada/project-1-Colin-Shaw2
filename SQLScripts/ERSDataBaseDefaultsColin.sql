drop schema if exists project1colin cascade;
create schema project1colin;
set schema 'project1colin';




create table ers_reimbursement_status(
	reimb_status_id serial primary key,
	reimb_status text 
		check (reimb_status like 'PENDING' or reimb_status like 'APPROVED' 
		or reimb_status like 'DENIED') not null
);

create table ers_reimbursement_type(
	reimb_type_id serial primary key,
	reimb_type text 
		check (reimb_type like 'LODGING' or reimb_type like 'TRAVEL' 
		or reimb_type like 'FOOD' or reimb_type like 'OTHER') not null
);

create table ers_user_roles(
user_role_id serial primary key,
user_role text 
	check (user_role like 'EMPLOYEE' or user_role like 'FINANCE_MANAGER') not null
);


create table ers_users(
	ers_users_id serial primary key,
	ers_username text unique not null,
	ers_password text not null,
	user_first_name text not null,
	user_last_name text not null,
	user_email text unique not null,
	user_role_id integer references ers_user_roles(user_role_id) not null,
	unique(ers_username, user_email)
);


create table ers_reimbursement(
	reimb_id serial primary key,
	reimb_amount numeric(12,2) not null,
	reimb_submitted timestamp not null,
	reimb_resolved timestamp,
	reimb_description text,
--	reimb_receipt oid,
	reimb_author integer references ers_users(ers_users_id) not null,
	reimb_resolver integer references ers_users(ers_users_id),
	reimb_status_id integer references ers_reimbursement_status(reimb_status_id) not null,
	reimb_type_id integer references ers_reimbursement_type(reimb_type_id) not null
);

insert into ers_reimbursement_status(reimb_status)
	values('PENDING'),
	('APPROVED'),
	('DENIED');

insert into ers_reimbursement_type(reimb_type)
	values('LODGING'),
	('TRAVEL'),
	('FOOD'),
	('OTHER');
	
insert into ers_user_roles(user_role)
	values('EMPLOYEE'),
	('FINANCE_MANAGER');

insert into ers_users(
	ers_username,
	ers_password,
	user_first_name,
	user_last_name,
	user_email,
	user_role_id
	)
	values(
	'colin',
	's',
	'colin',
	'shaw',
	'c.s@mail.ca',
	1),
	(
	'mike',
	's',
	'micheal',
	'sw',
	'm.s@mail.ca',
	1),
	(
	'eric',
	'o',
	'eric',
	'os',
	'e.o@mail.ca',
	2),
	(
	'dave',
	'd',
	'dave',
	'da',
	'd.d@mail.ca',
	2)
	;

insert into ers_reimbursement(	
	reimb_amount,
	reimb_submitted,
	reimb_resolved,
	reimb_description,
	reimb_author,
	reimb_resolver,
	reimb_status_id,
	reimb_type_id
	)
	values(	
	1000.59,
	current_timestamp,
	null,
	'reimb_description blah blah blah',
	1,
	null,
	1,
	2
	)
	;
