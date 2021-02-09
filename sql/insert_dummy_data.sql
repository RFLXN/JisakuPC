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