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