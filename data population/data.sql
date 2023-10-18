use hotel_managment_system;

-- code(insertion) order does matter

-- insert into tables
INSERT INTO `table` VALUES
	(DEFAULT, 1),
	(DEFAULT, 1),
	(DEFAULT, 1),
	(DEFAULT, 1),
	(DEFAULT, 0);

-- insert into staff- role
INSERT INTO staff_role VALUES
	(DEFAULT,'manager', 'Operations oversight, staff management.'),
	(DEFAULT,'Receptionist', 'Guest check-in/check-out, customer service'),
	(DEFAULT,'Housekeeper', 'Cleans and maintains guest rooms'),
	(DEFAULT,'Waiter/Waitress', 'Food/beverage service, order taking.'),
	(DEFAULT,'Maintenance', 'Repairs, maintenance tasks.'),
	(DEFAULT,'Security', 'Guest safety, property protection.');

-- insert into special_request
INSERT INTO special_request VALUES
	(DEFAULT,"medical need", 'medical attention' ),
	(DEFAULT,"Room with a view", 'Additional linens' ),
	(DEFAULT,"Non-smoking room", 'Smoke-free lodging' ),
	(DEFAULT,"Accessible room", 'Disability-friendly lodging' ),
	(DEFAULT,"Wake-up call", 'Morning alarm service' ),
	(DEFAULT,"Room service", 'Morning alarm service' ),
	(DEFAULT,"Special dietary", 'customized meals' );

-- insert into room_type
INSERT INTO room_type VALUES
	(DEFAULT,'Standard',2,100,'Basic amenities'),
	(DEFAULT,'Deluxe',2,150,'Upgraded features'),
	(DEFAULT,'Suite',4,200,'Spacious, separate living area'),
	(DEFAULT,'Executive',2,300,'Business-focused amenities'),
	(DEFAULT,'Penthouse',2,500,'Luxury, top-floor, panoramic views');

-- inseret into room_amenity
INSERT INTO room_amenity VALUES
		(DEFAULT, 'Wi-Fi','Complimentary internet access'),
		(DEFAULT, 'Fitness Center','On-site gym facilities'),
		(DEFAULT, 'Private Bathroom','Personal restroom facilities'),
		(DEFAULT, 'Air Conditioning',' Climate control for comfort'),
		(DEFAULT, 'Safe','Secure storage for valuables'),
		(DEFAULT, 'Telephone','Communication within the room'),
		(DEFAULT, 'Soundproofing','Soundproofing'),
		(DEFAULT, 'Work Desk','Space for productivity'),
		(DEFAULT, 'Mini Fridge','Convenient storage for food and drinks');

-- insert into room
INSERT INTO room VALUES
		(DEFAULT, 1, 1),
		(DEFAULT, 1, 1),
		(DEFAULT, 3, 1),
		(DEFAULT, 2, 1),
		(DEFAULT, 2, 0),
		(DEFAULT, 4, 1),
		(DEFAULT, 1, 0);

-- insert into room_type_amenity
INSERT INTO room_type_amenity (type_id, amenity_id) 
VALUES 
(1, 1), -- Room type 1 has amenity 1
(1, 2), -- Room type 1 has amenity 2
(2, 1), -- Room type 2 has amenity 1
(3, 3); -- Room type 3 has amenity 3

-- insert into menu
INSERT INTO menu VALUES 
	(DEFAULT, 'Signature Cocktail', 'Exquisite blend, tantalizing flavors',12, 'drink' ),
	(DEFAULT, 'tella', 'Traditional alcholic drink',12, 'drink' ),
	(DEFAULT, 'Coffee', 'Traditional coffee brewing, rich flavors',30, 'drink' ),
	(DEFAULT, 'Tej', 'Honey wine, sweet and smooth',20, 'drink' ),
	(DEFAULT, 'Fruit Smoothie', 'ropical paradise in a glass',8, 'drink' ),
	(DEFAULT, 'Fluffy Pancakes', 'Pillowy softness, sweet morning treat',50, 'food' ),
	(DEFAULT, 'Classic Caesar Salad', ' Crisp greens, creamy dressing',45, 'food' ),
	(DEFAULT, 'Injera with Doro Wat', ' Spongy flatbread, spicy chicken stew',70, 'food' ),
	(DEFAULT, 'Tibs', '  Grilled meat or vegetables, aromatic and flavorful',65, 'food' );


-- insert into guest
INSERT INTO guest(username, password, first_name, last_name, email, birth_date, sex, phone_num)  
VALUES
	('john123', 'password1', 'John', 'Doe', 'john.doe@example.com', '1990-05-15', 'M', '123456789'),
	('jane456', 'password2', 'Jane', 'Smith', 'jane.smith@example.com', '1985-09-22', 'F', '987654321'),
	('mike789', 'password3', 'Mike', 'Johnson', 'mike.johnson@example.com', '1995-12-10', 'M', '456789123');
    
-- insert into Staff
INSERT INTO staff 
(username, password, role_id, first_name, last_name, email, birth_date, sex, phone_num, reports_to, access_level) 
VALUES 
	('john123', 'password1', 1, 'John', 'Doe', 'john.doe@example.com', '1990-05-15', 'M', '123456789', NULL, 'manager'),
	('jane456', 'password2', 2, 'Jane', 'Smith', 'jane.smith@example.com', '1985-09-22', 'F', '987654321', 1, 'reception'),
	('mike789', 'password3', 3, 'Mike', 'Johnson', 'mike.johnson@example.com', '1995-12-10', 'M', '456789123', 1, 'regular_staff');
    
    
-- insert into reservation
INSERT INTO reservation (guest_id, room_id, checkout_date, special_request, cancel_date) 
VALUES 
		(1, 1, '2023-01-15 12:00:00', 2, NULL),
		(2, 2, '2023-02-10 11:00:00', 1, NULL),
		(3, 3, '2023-03-25 10:00:00', 3, NULL);
        
-- insert into paymnet_methods
INSERT INTO payment_method (name, processing_fee, description) 
VALUES 
	('Credit Card', 2.5, 'Payment made using a credit card'),
	('PayPal', 1.5, 'Payment made through PayPal'),
	('Cash', 0.0, 'Payment made in cash');

-- insert into order
INSERT INTO `order` (guest_id, table_id, quantity, cancel_date) 
VALUES 
	(1, 1, 2,  NULL),
	(2, 2, 1,  NULL),
    (2, 3, 1, NULL),
    (1, 4, 1,  NULL),
    (3, 5, 3,  '2023-10-13 20:20:00');
    
-- insert into menu order
INSERT INTO menu_order (menu_id, order_id) 
VALUES 
(1, 6),
(2, 6),
(3, 6),
(4, 9),
(5, 9),
(6, 10);

 -- insert into invoice
-- INSERT INTO invoice (guest_id, reservation_id, order_id, invoice_total, payment_total, invoice_date, payment_date) 
-- VALUES 
-- 	(1, 1, 3, 500, 0, '2023-10-13 12:00:00', NULL),
-- 	(2, 2, 2, 750, 250, '2023-10-14 10:30:00', '2023-10-14 16:45:00'),
-- 	(3, 3, 1, 1000, 1000, '2023-10-15 15:45:00', '2023-10-15 15:45:00');
--     
-- inseret into payment

-- INSERT INTO payment (invoice_id, payment_method_id, amount, payment_date) 
-- VALUES 
-- 		(2, 1, 100, '2023-10-13 12:00:00'),
-- 		(2, 2, 250, '2023-10-14 16:45:00'),
-- 		(3, 1, 1000, '2023-10-15 15:45:00');
        
-- insert into staff assignment
INSERT INTO staff_assignment (staff_id, reservation_id, notes)
VALUES
	(1, 1, 'Assigned to handle check-in.'),
	(2, 2, 'Assigned to assist with event setup.'),
	(3, 3, 'Assigned as a tour guide for the day.');
        
        