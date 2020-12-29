
set schema 'project1colin';

select * from ers_reimbursement_status;
select * from ers_reimbursement_type;
select * from ers_user_roles;
select * from ers_users;
select * from ers_reimbursement;

select * from ers_users where (ers_username like 'colin' and ers_password like 's');


select ers_users_id, ers_username, ers_password, user_first_name, user_last_name, user_email, user_role 
from ers_users 
join ers_user_roles 
using (user_role_id)
--on ers_users.user_role_id = ers_user_roles.ers_user_role_id
where (ers_username like 'colin' and ers_password like 's');


select * 
from ers_users 
join ers_user_roles 
using (user_role_id)
--on ers_users.user_role_id = ers_user_roles.ers_user_role_id
where (ers_username like 'colin' and ers_password like 's');

select reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, 
reimb_author, reimb_resolver, reimb_status, reimb_type 
from ers_reimbursement
join ers_reimbursement_status using (reimb_status_id)
join ers_reimbursement_type using (reimb_type_id)
;

select reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, 
reimb_author, reimb_resolver, reimb_status, reimb_type 
from ers_reimbursement
join ers_reimbursement_status using (reimb_status_id)
join ers_reimbursement_type using (reimb_type_id)
where reimb_status like 'PENDING'
;

select reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, 
reimb_author, reimb_resolver, reimb_status, reimb_type 
from ers_reimbursement
join ers_reimbursement_status using (reimb_status_id)
join ers_reimbursement_type using (reimb_type_id)
where reimb_id=1
;

select reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, 
reimb_author, reimb_resolver, reimb_status, reimb_type 
from ers_reimbursement
join ers_reimbursement_status using (reimb_status_id)
join ers_reimbursement_type using (reimb_type_id)
where reimb_author=1
;

insert into ers_reimbursement 
	values(default, 200.59, current_timestamp, null, 'second reimbursement', 1, null, 
(select reimb_status_id from ers_reimbursement_status where reimb_status like 'PENDING'), 
(select reimb_type_id from ers_reimbursement_type where reimb_type like 'LODGING'))
returning reimb_id;

update ers_reimbursement 
set	
	reimb_amount = 40.44,
	reimb_submitted = current_timestamp,
	reimb_resolved = null,
	reimb_description = 'updated reimbursement',
	reimb_author = 1,
	reimb_resolver = null,
	reimb_status_id = (select reimb_status_id from ers_reimbursement_status where reimb_status like 'PENDING'),
	reimb_type_id =(select reimb_type_id from ers_reimbursement_type where reimb_type like 'LODGING')
where reimb_id=1;
