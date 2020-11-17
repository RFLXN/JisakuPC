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
    | Column Name | Data Type | Constraints |
    | ----------- | --------- | ----------- |
    | product_no  | number(8) | PRIMARY KEY |
    
    
    
### 6. データベース設計

### 7. データベースSQLの作成

### 8. サンプル挿入SQLの作成

### 9. テストDBの作成

### 10. Gitによるプロジェクトの作成 ・ 共有

### 11. EclipseによるGitとの連携 ・ 設定

### 12. 必要技術の調査 ・ 選定 (特にUI周り)

### 13. J2EEパターンの適用

### 14. コマンド (とりあえず一つ作成)

### 15. テストラン