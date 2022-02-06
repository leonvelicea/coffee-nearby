insert into addresses (city, country, number, postal_code, street) values ('Brasov', 'RO', '1', '500600', 'Republicii');
insert into social_data (email, facebook_url, instagram_url, telephone, trip_advisor_url) values ('coffee1@coffee.com', 'https://facebook.com', 'https://instagram.com', '0771777777', 'https://trip-advisor.com');
insert into coffee_shops (geo_location, name, address_id, social_data_id) values (ST_GeomFromText('POINT(45.643971 25.591870)'), 'Coffee shop 1', 1, 1);

insert into addresses (city, country, number, postal_code, street) values ('Brasov', 'RO', '15', '500601', 'Michael Weiss');
insert into social_data (email, facebook_url, instagram_url, telephone, trip_advisor_url) values ('coffee2@coffee.com', 'https://facebook.com', 'https://instagram.com', '0772777777', 'https://trip-advisor.com');
insert into coffee_shops (geo_location, name, address_id, social_data_id) values (ST_GeomFromText('POINT(45.643135 25.591200)'), 'Coffee shop 2', 2, 2);


insert into addresses (city, country, number, postal_code, street) values ('Brasov', 'RO', '250', '500602', 'Republicii');
insert into social_data (email, facebook_url, instagram_url, telephone, trip_advisor_url) values ('coffee3@coffee.com', 'https://facebook.com', 'https://instagram.com', '0773777777', 'https://trip-advisor.com');
insert into coffee_shops (geo_location, name, address_id, social_data_id) values (ST_GeomFromText('POINT(45.641833 25.590863)'), 'Coffee shop 3', 3, 3);
