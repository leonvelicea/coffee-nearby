create table addresses (
    id  bigserial not null,
    city varchar(150) not null,
    country varchar(150) not null,
    number varchar(50) not null,
    postal_code varchar(20) not null,
    street varchar(150) not null,
    coffee_shop_id int8,
    primary key (id)
);
create table coffee_shops (
    id  bigserial not null,
    geo_location geography,
    name varchar(100) not null,
    primary key (id)
);
create table social_data (
    id  bigserial not null,
    email varchar(100),
    facebook_url varchar(255),
    instagram_url varchar(255),
    telephone varchar(15) not null,
    trip_advisor_url varchar(255),
    coffee_shop_id int8,
    primary key (id)
);

alter table addresses add constraint address_coffee_shop_fk foreign key (coffee_shop_id) references coffee_shops;
alter table social_data add constraint social_data_coffee_shop_fk foreign key (coffee_shop_id) references coffee_shops;