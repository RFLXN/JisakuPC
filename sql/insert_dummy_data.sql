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
    ('userid1', sha2('userpass1', 256), false),
    ('userid2', sha2('userpass2', 256), false),
    ('admin', sha2('adminpass', 256), true);