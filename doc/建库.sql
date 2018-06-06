create database blzo default character set utf8mb4 collate utf8mb4_general_ci;
create user blzo@'%' identified by 'testpwd';
grant all privileges on blzo.* to blzo;