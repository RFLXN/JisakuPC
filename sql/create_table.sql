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