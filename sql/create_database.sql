create database jisaku;

create user jisaku identified by 'jisaku';

grant all privileges on jisaku.* to 'jisaku'@'%';