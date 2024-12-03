-- Inserting a new customer
INSERT INTO customers (account_id, username, password, dtype) VALUES ('test_id', 'test_username', 'test_password', 'Customer');

-- Insert a Car
INSERT INTO vehicle (id, name, brand_owner, release_year, cost, color, fuel_type, vehicle_state) VALUES (1, 'Tesla Model 3', 'Tesla', 2023, 50000.0, 'RED', 'electric', 'AVAILABLE');
INSERT INTO cars (id, number_of_doors, trunk_capacity) VALUES (1, 4, 400.0);

-- Insert a Scooter
INSERT INTO vehicle (id, name, brand_owner, release_year, cost, color, fuel_type, vehicle_state) VALUES (2, 'Vespa GTS', 'Vespa', 2020, 6000.0, 'BLACK', 'petrol', 'AVAILABLE');
INSERT INTO scooters (id, has_helmet_included, max_passengers, range_per_fuel_tank) VALUES (2, TRUE, 2, 200.0);

-- Insert a Truck
INSERT INTO vehicle (id, name, brand_owner, release_year, cost, color, fuel_type, vehicle_state) VALUES (3, 'Ford F-150', 'Ford', 2023, 45000.0, 'RED', 'diesel', 'AVAILABLE');
INSERT INTO trucks (id, number_of_axles, payload_capacity, towing_capacity) VALUES (3, 2, 3000.0, 7000.0);

-- Insert a Van
INSERT INTO vehicle (id, name, brand_owner, release_year, cost, color, fuel_type, vehicle_state) VALUES (4, 'Toyota Sienna', 'Toyota', 2022, 40000.0, 'WHITE', 'petrol', 'AVAILABLE');
INSERT INTO vans (id, cargo_capacity, number_of_seats) VALUES (4, 800.0, 8);

