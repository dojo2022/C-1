//【IDPWテーブルを作成するSQL文】

  
    create table list (
    number int PRIMARY KEY auto_increment NOT NULL,
    date date NOT NULL,
    id varchar(20) NOT NULL,
    check_tf boolean NOT NULL default false
);

//【IDPWテーブルにサンプルデータを登録するSQL文】
INSERT INTO list (date, id) VALUES ( '2022-06-13', 'dojo');










DROP TABLE list
select * from list
