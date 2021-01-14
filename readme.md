# 自作パソコンサイト JisakuPC

## やることリスト

### 1. 作成サイトの決定
* 自作パソコンサイト

### 2. 元サイトの選定
* [https://jisaku.com/](https://jisaku.com/)

### 3. 使用対象者の設定 (ペルソナ)
    * PCパーツの知識があり（普段自作でPCを作る位）自作PCの見積もりを一括で簡単に作りたい人。
    * また作成を自慢したり、他の人の作成を見たい人

### 4. 扱う機能 ・ データの選定
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

### 5. データ構造の設定
> [HY000][1681] Integer display width is deprecated and will be removed in a future release.
    
    MySQLの定数型はサイズを決めることが非推奨です。

> in Constraints : AUTO_INCREMENT

    MySQLではシーケンスがありません。
    その代わりに AUTO_INCREMENTを使えます。
    
> [JSON型 (JSON FUNCTION REFERENCE DOCUMENT)](https://dev.mysql.com/doc/refman/5.7/en/json-function-reference.html)

    MySQLにはJSON型があり、JSONデータを処理するための色んな関数が定義されています。
    
    
product_table

| Column Name   | Data Type   | Constraints                |
| ------------- | ----------- | -------------------------- |
| product_no    | INT         | PRIMARY KEY AUTO_INCREMENT |
| product_name  | VARCHAR(60) | NOT NULL                   |
| product_price | INT         | NOT NULL                   |
| product_spec  | JSON        | NOT NULL                   |
| product_brand | VARCHAR(30) | NOT NULL                   |
| product_type  | VARCHAR(12) | NOT NULL                   |


user_table

| Column Name | Data Type   | Constraints                |
| ----------- | ----------- | -------------------------- |
| user_no     | INT         | PRIMARY KEY AUTO_INCREMENT |
| user_id     | VARCHAR(20) | NOT NULL UNIQUE            |
| user_pw     | VARCHAR(20) | NOT NULL                   |
| admin       | BOOLEAN     | NOT NULL                   |


build_table

| Column Name            | Data Type | Constraints                                       |
| ---------------------- | --------- | ------------------------------------------------- |
| build_no               | INT       | PRIMARY KEY AUTO_INCREMENT                        |
| user_no                | INT       | REFERENCES user_table(user_no)                    |
| cpu_product_no         | INT       | DEFAULT NULL REFERENCES product_table(product_no) |
| gpu_product_no         | INT       | DEFAULT NULL REFERENCES product_table(product_no) |
| ram_product_no         | INT       | DEFAULT NULL REFERENCES product_table(product_no) |
| storage_product_no     | INT       | DEFAULT NULL REFERENCES product_table(product_no) |
| motherboard_product_no | INT       | DEFAULT NULL REFERENCES product_table(product_no) |
| cooler_product_no      | INT       | DEFAULT NULL REFERENCES product_table(product_no) |
| case_product_no        | INT       | DEFAULT NULL REFERENCES product_table(product_no) |
| etc_product_no         | INT       | DEFAULT NULL REFERENCES product_table(product_no) |


build_post_table

| Column Name | Data Type     | Constraints                      |
| ----------- | ------------- | -----------                      |
| post_no     | INT           | PRIMARY KEY AUTO_INCREMENT       |
| user_no     | INT           | REFERENCES user_table(user_no)   |
| build_no    | INT           | REFERENCES build_table(build_no) |
| title       | VARCHAR(60)   | NOT NULL                         |
| description | VARCHAR(1000) | NOT NULL                         |


### 6. データベース設計

### 7. データベースSQLの作成

```mysql
CREATE TABLE product_table(
    product_no INT NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(60) NOT NULL,
    product_price INT NOT NULL,
    product_spec JSON NOT NULL,
    product_brand VARCHAR(30) NOT NULL,
    product_type VARCHAR(12) NOT NULL,
    PRIMARY KEY (product_no)
);

CREATE TABLE user_table(
    user_no INT AUTO_INCREMENT,
    user_id VARCHAR(20) NOT NULL UNIQUE,
    user_pw VARCHAR(20) NOT NULL,
    admin BOOLEAN NOT NULL,
    PRIMARY KEY (user_no)
);

CREATE TABLE build_table(
    build_no INT AUTO_INCREMENT,
    user_no INT REFERENCES user_table(user_no),
    cpu_product_no INT DEFAULT NULL REFERENCES product_table(product_no),
    gpu_product_no INT DEFAULT NULL REFERENCES product_table(product_no),
    ram_product_no INT DEFAULT NULL REFERENCES product_table(product_no),
    storage_product_no INT DEFAULT NULL REFERENCES product_table(product_no),
    motherboard_product_no INT DEFAULT NULL REFERENCES product_table(product_no),
    cooler_product_no INT DEFAULT NULL REFERENCES product_table(product_no),
    case_product_no INT DEFAULT NULL REFERENCES product_table(product_no),
    etc_product_no INT DEFAULT NULL REFERENCES product_table(product_no),
    PRIMARY KEY (build_no)
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

### 8. サンプル挿入SQLの作成

```mysql
INSERT INTO product_table
    (product_name, product_price, product_spec, product_brand, product_type)
VALUES
    ('dummy cpu', 9999, '{"dummy spec":"dummy spec"}', 'dummy brand', 'cpu'),
    ('dummy ram', 9999, '{"dummy spec":"dummy spec"}', 'dummy brand', 'ram'),
    ('dummy gpu', 9999, '{"dummy spec":"dummy spec"}', 'dummy brand', 'gpu'),
    ('dummy ssd', 9999, '{"dummy spec":"dummy spec"}', 'dummy brand', 'storage');

INSERT INTO user_table
    (user_id, user_pw, admin)
VALUES
    ('userid1', 'userpass1', false),
    ('userid2', 'userpass2', false),
    ('admin', 'adminpass', true);

```

### 9. テストDBの作成
in MySQL Console
```mysql
# $project_root = プロジェクトのルートパス

# ルートユーザーでログイン
source $project_root/sql/create_database.sql

# username : jisaku / password : jisaku / database : jisaku でログイン
source $project_root/sql/create_table.sql
source $project_root/sql/insert_dummy_data.sql
```
### 10. Gitによるプロジェクトの作成 ・ 共有

### 11. EclipseによるGitとの連携 ・ 設定

### 12. 必要技術の調査 ・ 選定 (特にUI周り)

### 13. J2EEパターンの適用

### 14. コマンド (とりあえず一つ作成)

### 15. テストラン
