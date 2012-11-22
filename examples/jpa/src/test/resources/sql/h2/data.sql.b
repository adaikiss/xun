--user
insert into user(id, nice_name, display_name, login_name, password, status)values(1, 'Yun Zhao', 'chang shan zhao zi long', 'zhaoyun', '123456', 'normal');
insert into user(id, nice_name, display_name, login_name, password, status)values(2, 'Asas', 'night grieved', 'asas', '123456', 'normal');
--post
insert into post(id, title, author_id, content, post_status, comment_status, password)values(1, 'first post', 1, 'Hello, world! This is my first post!', 'publish', 'open', null);
insert into post(id, title, author_id, content, post_status, comment_status, password)values(2, 'second post', 1, 'Hello, world! This is my second post!', 'publish', 'open', null);
insert into post(id, title, author_id, content, post_status, comment_status, password)values(3, 'third post', 1, 'Hello, world! This is my third post!', 'publish', 'open', null);
insert into post(id, title, author_id, content, post_status, comment_status, password)values(4, 'first post', 2, 'Hello, world! This is my first post! And my name is asas!', 'publish', 'open', null);

--attachment
insert into attachment(id, name, description, mime_type, value)values(1, 'first glance', 'if hold the feeling of the first glance, there is nothing.your no-care makes people not care you, said people''s hearts uncertain.', 'image/jpg', 'a/b/zrcj.jpg');
insert into attachment(id, name, description, mime_type, value)values(2, 'suddenly smile', 'suddenly smile, tears unconsciously', 'image/jpg', 'd/e/mrwx.jpg');

--category
insert into category(id, name, description, slug)values(1, 'Javaee', 'All javaee posts', 'javaee');
insert into category(id, name, description, slug)values(2, 'Javaee 2', 'All javaee posts 2', 'javaee_2');
