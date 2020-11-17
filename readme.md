# 自作パソコンサイト JisakuPC

## やることリスト

### 1. 作成サイトの決定
    自作パソコンサイト

### 2. 元サイトの選定
    https://jisaku.com/

### 3. 使用対象者の設定 (ペルソナ)
    * PCパーツの知識があり（普段自作でPCを作る位）自作PCの見積もりを一括で簡単に作りたい人。
    * また作成を自慢したり、他の人の作成を見たい人

### 4. 扱う機能 ・ データの選定
#### 機能
    * 検索
     * キーワードでパーツ検索
     * Amazonからの価格データでパーツをソート
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

product_table

| Column Name   | Data Type   | Constraints                |
| ------------- | ----------- | -------------------------- |
| product_no    | int         | primary key auto_increment |
| product_name  | varchar(60) | not null                   |
| product_price | int         | not null                   |
| product_spec  | json        | not null                   |
| product_brand | varchar(30) | not null                   |
| product_type  | varchar(12) | not null                   |


user_table

| Column Name | Data Type   | Constraints                |
| ----------- | ----------- | -------------------------- |
| user_no     | int         | primary key auto_increment |
| user_id     | varchar(20) | not null unique            |
| user_pw     | varchar(20) | not null                   |
| admin       | boolean     | not null                   |


build_table

| Column Name            | Data Type | Constraints                                       |
| ---------------------- | --------- | ------------------------------------------------- |
| build_no               | int       | primary key auto_increment                        |
| user_no                | int       | references user_table(user_no)                    |
| cpu_product_no         | int       | default null references product_table(product_no) |
| gpu_product_no         | int       | default null references product_table(product_no) |
| ram_product_no         | int       | default null references product_table(product_no) |
| storage_product_no     | int       | default null references product_table(product_no) |
| motherboard_product_no | int       | default null references product_table(product_no) |
| cooler_product_no      | int       | default null references product_table(product_no) |
| case_product_no        | int       | default null references product_table(product_no) |
| etc_product_no         | int       | default null references product_table(product_no) |


build_post_table

| Column Name | Data Type     | Constraints                      |
| ----------- | ------------- | -----------                      |
| post_no     | int           | primary key auto_increment       |
| user_no     | int           | references user_table(user_no)   |
| build_no    | int           | references build_table(build_no) |
| title       | varchar(60)   | not null                         |
| description | varchar(1000) | not null                         |


### 6. データベース設計

### 7. データベースSQLの作成

```mysql
create table product_table(
    product_no int(8) not null auto_increment,
    product_name varchar(60) not null,
    product_price int(8) not null,
    product_spec json not null,
    product_brand varchar(30) not null,
    product_type varchar(12) not null,
    primary key (product_no)
);

create table user_table(
    user_no int(8) auto_increment,
    user_id varchar(20) not null unique,
    user_pw varchar(20) not null,
    admin boolean not null,
    primary key (user_no)
);

create table build_table(
    build_no int(8) auto_increment,
    user_no int(8) references user_table(user_no),
    cpu_product_no int(8) default null references product_table(product_no),
    gpu_product_no int(8) default null references product_table(product_no),
    ram_product_no int(8) default null references product_table(product_no),
    storage_product_no int(8) default null references product_table(product_no),
    motherboard_product_no int(8) default null references product_table(product_no),
    cooler_product_no int(8) default null references product_table(product_no),
    case_product_no int(8) default null references product_table(product_no),
    etc_product_no int(8) default null references product_table(product_no),
    primary key (build_no)
);

create table build_post_table(
    post_no int(8) auto_increment,
    user_no int(8) references user_table(user_no),
    build_no int(8) references build_table(build_no),
    title varchar(60) not null,
    description varchar(1000) not null,
    date datetime default now(),
    primary key (post_no)
);
```

### 8. サンプル挿入SQLの作成

```mysql
insert into product_table
    (product_name, product_price, product_spec, product_brand, product_type)
values
    ('dummy cpu', 9999, '{"dummy spec":"dummy spec"}', 'dummy brand', 'cpu'),
    ('dummy ram', 9999, '{"dummy spec":"dummy spec"}', 'dummy brand', 'ram'),
    ('dummy gpu', 9999, '{"dummy spec":"dummy spec"}', 'dummy brand', 'gpu'),
    ('dummy ssd', 9999, '{"dummy spec":"dummy spec"}', 'dummy brand', 'storage');

insert into user_table
    (user_id, user_pw, admin)
values
    ('userid1', 'userpass1', false),
    ('userid2', 'userpass2', false),
    ('admin', 'adminpass', true);

```

### 9. テストDBの作成

### 10. Gitによるプロジェクトの作成 ・ 共有

### 11. EclipseによるGitとの連携 ・ 設定

### 12. 必要技術の調査 ・ 選定 (特にUI周り)

### 13. J2EEパターンの適用

### 14. コマンド (とりあえず一つ作成)

### 15. テストラン
