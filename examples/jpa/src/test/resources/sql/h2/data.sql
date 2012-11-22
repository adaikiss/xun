--user
insert into user(id, nice_name, display_name, login_name, password, status)values(1, '赵云', '常山赵子龙', 'zhaoyun', '123456', 'normal');
insert into user(id, nice_name, display_name, login_name, password, status)values(2, '阿尔萨斯', '夜之哀伤', 'asas', '123456', 'normal');
--post
insert into post(id, title, author_id, content, post_status, comment_status, password)values(1, 'first post', 1, 'Hello, world! This is my first post!', 'publish', 'open', null);
insert into post(id, title, author_id, content, post_status, comment_status, password)values(2, 'second post', 1, 'Hello, world! This is my second post!', 'publish', 'open', null);
insert into post(id, title, author_id, content, post_status, comment_status, password)values(3, 'third post', 1, 'Hello, world! This is my third post!', 'publish', 'open', null);
insert into post(id, title, author_id, content, post_status, comment_status, password)values(4, 'first post', 2, 'Hello, world! This is my first post! And my name is asas!', 'publish', 'open', null);

--attachment
insert into attachment(id, name, description, mime_type, value)values(1, '只若初见', '人生若只如初见，何事秋风画扇窗。等闲忘却故人心，却道故人心易变。', 'image/jpg', 'a/b/zrcj.jpg');
insert into attachment(id, name, description, mime_type, value)values(2, '陌然微笑', '陌然微笑，眼泪不自觉地掉。', 'image/jpg', 'd/e/mrwx.jpg');

--category
insert into category(id, name, description, slug)values(1, 'Javaee', 'All javaee posts', 'javaee');
insert into category(id, name, description, slug, parent_id)values(2, 'Javaee 2', 'All javaee posts 2', 'javaee_2', 1);

--credit
insert into credit(id, user_id, credit1, credit2, credit3)values(1, 1, 150, 250, 250);
insert into credit(id, user_id, credit1, credit2, credit3)values(2, 2, 150, 300, 350);
