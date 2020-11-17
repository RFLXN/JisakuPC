CREATE DATABASE jisaku;

CREATE USER jisaku IDENTIFIED BY 'jisaku';

GRANT ALL PRIVILEGES ON jisaku.* TO 'jisaku'@'%';