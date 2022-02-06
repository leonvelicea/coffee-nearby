CREATE EXTENSION IF NOT EXISTS Postgis;

create table addresses (
    id  bigserial not null,
    city varchar(150) not null,
    country varchar(150) not null,
    number varchar(50) not null,
    postal_code varchar(20) not null,
    street varchar(150) not null,
    primary key (id)
);
create table social_data (
    id  bigserial not null,
    email varchar(100),
    facebook_url varchar(255),
    instagram_url varchar(255),
    telephone varchar(15) not null,
    trip_advisor_url varchar(255),
    primary key (id)
);

create table coffee_shops (
      id  bigserial not null,
      geo_location geography,
      name varchar(100) not null,
      address_id int8,
      social_data_id int8,
      primary key (id),
      CONSTRAINT coffee_shop_address_fk FOREIGN KEY (address_id) REFERENCES addresses(id),
      CONSTRAINT coffee_shop_social_data_fk FOREIGN KEY (social_data_id) REFERENCES social_data(id)
);