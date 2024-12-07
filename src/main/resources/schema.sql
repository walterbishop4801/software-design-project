create table account (
    username varchar not null,
    password varchar not null,
    DTYPE varchar,
    primary key(username)
);

CREATE TABLE booking (
    booking_id UUID PRIMARY KEY,
    account_id varchar,
    vehicle_id BIGINT,
    is_authenticated BOOLEAN,
    price DECIMAL(10,2),
    number_of_renting_days INT,
    booking_type varchar,
    DTYPE varchar
);

CREATE TABLE booking_decorators (
    booking_id UUID,
    decorators VARCHAR(50)
);

CREATE TABLE vehicle (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    brand_owner VARCHAR(255),
    release_year INT,
    cost DOUBLE,
    color VARCHAR(255),
    fuel_type VARCHAR(255),
    vehicle_state VARCHAR(255)
);

CREATE TABLE car (
    id BIGINT PRIMARY KEY,
    number_of_doors INT,
    trunk_capacity FLOAT,
    FOREIGN KEY (id) REFERENCES vehicle(id)
);

CREATE TABLE truck (
    id BIGINT PRIMARY KEY,
    payload_capacity FLOAT,
    towing_capacity FLOAT,
    number_of_axles INT,
    FOREIGN KEY (id) REFERENCES vehicle(id)
);

CREATE TABLE van (
    id BIGINT PRIMARY KEY,
    cargo_capacity FLOAT,
    number_of_seats INT,
    FOREIGN KEY (id) REFERENCES vehicle(id)
);

CREATE TABLE scooter (
    id BIGINT PRIMARY KEY,
    has_helmet_included BOOLEAN,
    max_passengers INT,
    range_per_fuel_tank INT,
    FOREIGN KEY (id) REFERENCES vehicle(id)
);