# 自作パソコンサイト JisakuPC

### 作成サイト
* 自作パソコンサイト

### 元サイト
* [https://jisaku.com/](https://jisaku.com/)

### 使用対象者 (ペルソナ)
* PCパーツの知識があり（普段自作でPCを作る位）自作PCの見積もりを一括で簡単に作りたい人。
* また作成を自慢したり、他の人の作成を見たい人

### 扱う機能・データ

#### 機能
* 検索
  * キーワードでパーツ検索
  * Rakutenからの価格データでパーツをソート
  * 自作PC構成の各パーツを選択
  * スペックで検索
     
* 投稿・閲覧
  * 投稿の閲覧
  * 各パーツ一覧を閲覧
  * 実際に自作したPCの画像、説明を投稿
     
* その他
  * 購入時、アマゾンに転送
  * 部品の合計金額を総計
  * ユーザー名、パスワードでログイン
  * 作成した見積もりを保存
  * 商品の追加、削除
  * 投稿の削除

#### データ
* 商品
　* 商品名
　* 商品のスペック
　* 商品の値段
　* 商品の簡単な説明
　* 商品分類
     
* ユーザー
  * ユーザー ID
  * ユーザーのパスワード
  * 管理者かどうか
     
* 見積
  * 選んだパーツ
  * 見積説明
  * 見積タイトル

### データ構造の設定
> [HY000][1681] Integer display width is deprecated and will be removed in a future release.
    
    MySQLの定数型はサイズを決めることが非推奨です。

> in Constraints : AUTO_INCREMENT

    MySQLではシーケンスがありません。
    その代わりに AUTO_INCREMENTを使えます。
    
> [JSON型 (JSON FUNCTION REFERENCE DOCUMENT)](https://dev.mysql.com/doc/refman/5.7/en/json-function-reference.html)

    MySQLにはJSON型があり、JSONデータを処理するための色んな関数が定義されています。
    
    
product_table

| Column Name   | Data Type    | Constraints                |
| ------------- | ------------ | -------------------------- |
| product_no    | INT          | PRIMARY KEY AUTO_INCREMENT |
| product_name  | VARCHAR(120) | NOT NULL                   |
| product_price | INT          | NOT NULL                   |
| product_spec  | JSON         | NOT NULL                   |
| product_brand | VARCHAR(30)  | NOT NULL                   |
| product_type  | VARCHAR(12)  | NOT NULL                   |


user_table

| Column Name | Data Type   | Constraints                |
| ----------- | ----------- | -------------------------- |
| user_no     | INT         | PRIMARY KEY AUTO_INCREMENT |
| user_id     | VARCHAR(20) | NOT NULL UNIQUE            |
| user_pw     | VARCHAR(64) | NOT NULL                   |
| admin       | BOOLEAN     | NOT NULL                   |


build_table

| Column Name            | Data Type    | Constraints                    |
| ---------------------- | ------------ | ------------------------------ |
| build_no               | INT          | PRIMARY KEY AUTO_INCREMENT     |
| user_no                | INT          | REFERENCES user_table(user_no) |
| build_name             | VARCHAR(120) | NOT NULL                       |


build_parts_table

| Column Name | Data Type | Constraints                          |
| ----------- | --------- | ------------------------------------ |
| build_no    | INT       | REFERENCES build_table(build_no)     |
| product_no  | INT       | REFERENCES product_table(product_no) |


build_post_table

| Column Name | Data Type     | Constraints                      |
| ----------- | ------------- | -----------                      |
| post_no     | INT           | PRIMARY KEY AUTO_INCREMENT       |
| user_no     | INT           | REFERENCES user_table(user_no)   |
| build_no    | INT           | REFERENCES build_table(build_no) |
| title       | VARCHAR(60)   | NOT NULL                         |
| description | VARCHAR(1000) | NOT NULL                         |


### データベースSQL

```mysql
CREATE TABLE product_table(
    product_no INT NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(120) NOT NULL,
    product_price INT NOT NULL,
    product_spec json NOT NULL,
    product_brand VARCHAR(30) NOT NULL,
    product_type VARCHAR(12) NOT NULL,
    PRIMARY KEY (product_no)
);


CREATE TABLE user_table(
    user_no INT AUTO_INCREMENT,
    user_id VARCHAR(20) NOT NULL UNIQUE,
    user_pw VARCHAR(64) NOT NULL,
    admin BOOLEAN NOT NULL,
    PRIMARY KEY (user_no)
);


CREATE TABLE build_table(
    build_no INT AUTO_INCREMENT,
    user_no INT REFERENCES user_table(user_no),
    build_name VARCHAR(120) NOT NULL,
    PRIMARY KEY (build_no)
);


CREATE TABLE build_parts_table(
    build_no INT REFERENCES build_table(build_no),
    product_no INT REFERENCES product_table(product_no)
);


CREATE TABLE build_post_table(
    post_no INT AUTO_INCREMENT,
    user_no INT REFERENCES user_table(user_no),
    build_no INT REFERENCES build_table(build_no),
    title VARCHAR(60) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    date DATETIME DEFAULT now(),
    PRIMARY KEY (post_no)
);
```

### サンプル挿入SQL

```mysql
INSERT INTO user_table
    (user_id, user_pw, admin)
VALUES
    ('userid1', sha2('userpass1', 256), false),
    ('userid2', sha2('userpass2', 256), false),
    ('admin', sha2('adminpass', 256), true);


INSERT INTO build_table
    (user_no, build_name)
VALUES
    (1, 'build1'),
    (2, 'build2');


INSERT INTO build_parts_table
    (build_no, product_no)
VALUES
    (1, 1),
    (1, 381),
    (1, 1743),
    (1, 2323),
    (1, 2923),
    (1, 3846),
    (1, 4492),
    (1, 4937),
    (1, 5413),
    (2, 1),
    (2, 381),
    (2, 1743),
    (2, 2323),
    (2, 2923),
    (2, 3846),
    (2, 4492),
    (2, 4937),
    (2, 5413);
```

### DBの作成
in MySQL Console
```mysql
# $project_root = プロジェクトのルートパス

# ルートユーザーでログイン
source $project_root/sql/create_database.sql

# username : jisaku / password : jisaku / database : jisaku でログイン
source $project_root/sql/create_table.sql
source $project_root/sql/insert_dummy_data.sql
```

### 外部ライブラリ

* WebContent/WEB-INF/lib/
    * [json-20201115.jar: JSON-java ライブラリ](https://github.com/stleary/JSON-java)
    
* WebContent/css/
    * [bootstrap.css: bootstrap css ライブラリ](http://bootstrapk.com/)

* WebContent/js/
    * [bootstrap.js: bootstrap js ライブラリ](http://bootstrapk.com/)
    * [jquery-3.5.1.min.js: jQuery ライブラリ](https://jquery.com/)
