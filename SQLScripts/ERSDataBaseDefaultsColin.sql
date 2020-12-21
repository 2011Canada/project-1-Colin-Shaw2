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
ers_user_role_id serial primary key,
user_role text 
	check (user_role like 'EMPLOYEE' or user_role like 'FINANCE_MANGER') not null
);


create table ers_users(
	ers_users_id serial primary key,
	ers_username text unique not null,
	ers_password text not null,
	user_first_name text not null,
	user_last_name text not null,
	user_email text unique not null,
	user_role_id integer references ers_user_roles(ers_user_role_id) not null,
	unique(ers_username, user_email)
);


create table ers_reimbursement(
	reimb_id serial primary key,
	reimb_amount numeric(12,2) not null,
	reimb_subitted timestamp not null,
	reimb_resolved timestamp,
	reimb_description text,
	reimb_receipt text,
	reimb_author integer references ers_users(ers_users_id) not null,
--	reimb_resolver integer references ers_users(ers_users_id),
	reimb_status_id integer references ers_reimbursement_status(reimb_status_id) not null,
	reimb_type_id integer references ers_reimbursement_type(reimb_type_id) not null
);