
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

select reimb_id, reimb_amount, reimb_subitted, reimb_resolved, reimb_description, reimb_author, reimb_resolver, reimb_status, reimb_type from ers_reimbursement
join ers_reimbursement_status using (reimb_status_id)
join ers_reimbursement_type using (reimb_type_id)
;
