drop table if exists xun_aoo;
CREATE TABLE `xun_aoo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `create_time` timestamp DEFAULT NULL,
  PRIMARY KEY (`id`)
);

drop table if exists xun_boo;
CREATE TABLE `xun_boo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `foo_id` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

drop table if exists xun_coo;
CREATE TABLE `xun_coo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

drop table if exists xun_foo;
CREATE TABLE `xun_foo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `aoo_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

drop table if exists xun_foo_coo;
CREATE TABLE `xun_foo_coo` (
  `foo_id` bigint(20) NOT NULL,
  `coo_id` bigint(20) NOT NULL
);

drop table if exists xun_noo;
CREATE TABLE `xun_noo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `size` int(4) DEFAULT 200,
  `create_time` timestamp DEFAULT NULL,
  PRIMARY KEY (`id`)
);
