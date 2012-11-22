--H2 user-define function for oracle compatibility
DROP ALIAS IF EXISTS to_date;
CREATE ALIAS to_date AS '
@CODE
java.util.Date toDate(String datetime, String format) throws java.text.ParseException {
	return new java.text.SimpleDateFormat(format.replace("Y", "y").replace("D", "d")).parse(datetime);
}
';

--user
insert into t_user(id, name, login_name, status, registered)values(1, '传说哥', 'jetty', 'normal', to_date('2012-09-01 12:24:11', 'YYYY-MM-DD HH:mm:ss'));
insert into t_user(id, name, login_name, status, registered)values(2, '阿杰', 'jack', 'denied', to_date('2012-08-21 10:59:45', 'YYYY-MM-DD HH:mm:ss'));
insert into t_user(id, name, login_name, status, registered)values(3, '囧囧', 'john', 'normal', to_date('2012-08-11 06:08:04', 'YYYY-MM-DD HH:mm:ss'));
insert into t_user(id, name, login_name, status, registered)values(4, '荀荀', 'xun', 'normal', to_date('2012-08-01 15:32:13', 'YYYY-MM-DD HH:mm:ss'));
insert into t_user(id, name, login_name, password, status, registered)values(5, '管理员', 'admin', 'rA59A3gXCU6eC0RB+brjIJ1nsC+khJFwZfcbFhCaGng=', 'normal', to_date('2012-07-21 09:12:32', 'YYYY-MM-DD HH:mm:ss'));

--role
insert into t_role(id, name, description)values(1, 'admin', '超级管理员');

--permission
insert into t_permission(domain, actions, targets, description)values('*', '*', '*', '超级权限');


--category
insert into t_category(id, name, description, charge_type, user_id)values(1, '工资', '工资收入', 'revenue', 1);
insert into t_category(id, name, description, charge_type, user_id, parent_id)values(2, '奖金', '季度奖', 'revenue', 1, 1);

--charge
insert into t_charge(id, remark, amount, date, user_id)values(1, '8月工资', 7321.45, to_date('2012-09-01', 'YYYY-MM-DD'), 1);
insert into t_charge(id, remark, amount, date, user_id)values(2, '9月房租', -650, to_date('2012-09-01', 'YYYY-MM-DD'), 1);
insert into t_charge(id, remark, amount, date, user_id)values(3, '红包', -200, to_date('2012-09-15', 'YYYY-MM-DD'), 1);
insert into t_charge(id, remark, amount, date, user_id)values(4, '聚餐', -150, to_date('2012-09-20', 'YYYY-MM-DD'), 1);

--charge_category
insert into t_charge_category(charge_id, category_id)values(1, 1);
insert into t_charge_category(charge_id, category_id)values(1, 2);