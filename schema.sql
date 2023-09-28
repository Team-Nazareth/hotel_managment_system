DROP DATABASE IF exists `HOTEL_MANAGMENT_SYSTEM`;

CREATE DATABASE HOTEL_MANAGMENT_SYSTEM;

USE HOTEL_MANAGMENT_SYSTEM;

CREATE TABLE `Room` (
  `room_id` INT UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `type_id` INT NOT NULL,
  `room_number` INT UNIQUE,
  `location` char(32),
  `is_available` boolean NOT NULL
);

CREATE TABLE `Room_type` (
  `type_id` int UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `type_name` varchar(32),
  `capacity` int,
  `rate` int,
  `description` varchar(50)
);

CREATE TABLE `Room_Amenity` (
  `amenity_id` int UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `amenity_name` varchar(20),
  `description` varchar(50)
);

-- JUNCTION TABLE
CREATE TABLE `Room_type_amenity` (
  `type_id` int NOT NULL,
  `amenity_id` int NOT NULL,
  PRIMARY KEY (`type_id`, `amenity_id`)
);

CREATE TABLE `Guest` (
  `guest_id` int UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `username` varchar(20) UNIQUE NOT NULL,
  `password` varchar(20) NOT NULL,
  `first_name` char(10) NOT NULL,
  `last_name` char(10),
  `email` char(20),
  `birth_date` DATE,
  `sex` char(1),
  `phone_num` char(9) NOT NULL
);

CREATE TABLE `Reservation` (
  `reservation_id` int UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `guest_id` int NOT NULL,
  `room_id` int NOT NULL,
  `checkin_date` DATETIME,
  `checkout_date` DATETIME,
  `num_of_guest` int,
  `special_request` int,
  `cancel_date` DATETIME
);

CREATE TABLE `Special_request` (
  `spId` int UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` char(10),
  `description` char(20)
);

CREATE TABLE `Staff` (
  `staff_id` int UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `username` varchar(20) UNIQUE NOT NULL,
  `password` varchar(20) NOT NULL,
  `role_id` INT NOT NULL,
  `first_name` char(10) NOT NULL,
  `last_name` char(10),
  `email` char(20),
  `birth_date` DATE,
  `sex` char(1),
  `phone_num` char(9) NOT NULL,
  `reports_to` INT, -- use staff id
  `access_level` enum("manager", "reception", "regular_staff") NOT NULL
);

CREATE TABLE `Staff_role` (
  `role_id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` char(10),
  `description` char(50)
);

-- fix nullable (either order or resrvation)
CREATE TABLE `Invoice` (
  `invoice_id` int UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `guest_id` INT NOT NULL,
  `reservation_id` INT,
  `order_id` INT,
  `invoice_total` INT NOT NULL,
  `payment_total` INT ,
  `invoice_date` TIMESTAMP,
  `payment_date` DATETIME
);

-- JUNCTION TABLE
CREATE TABLE `Staff_assignment` (
  `staff_id` int NOT NULL,
  `reservation_id` int NOT NULL,
  `assignment_date` DATETIME,
  `notes` varchar(50),
  PRIMARY KEY(staff_id,reservation_id)
);

CREATE TABLE `Payment` (
  `payment_id` int UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `guest_id` INT,
  `reservation_id` int,
  `order_id` INT,
  `invoice_id` INT NOT NULL,
  `payment_method_id` int NOT NULL,
  `amount` int NOT NULL,
  `payment_date` TIMESTAMP
);

CREATE TABLE `Payment_method` (
  `payment_method_id` int UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(20),
  `processing_fee` FLOAT,
  `description` varchar(30)
);

CREATE TABLE `Menu` (
	menu_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `description` varchar(100),
    price FLOAT,
    category ENUM('drink', 'food')
);

CREATE TABLE `Table` (
	table_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    is_available boolean NOT NULL
);

CREATE TABLE `Order` (
	order_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    guest_id INT NOT NULL,
    table_id INT,
    quantity INT NOT NULL DEFAULT 1 ,
    order_date TIMESTAMP,
	cancel_date DATETIME
);

-- JUNCTION TABLE
CREATE TABLE `Menu_order` (
	menu_id INT NOT NULL,
    order_id INT NOT NULL,
    order_date DATETIME,
    PRIMARY KEY (menu_id, order_id)
);

ALTER TABLE `Room` ADD FOREIGN KEY (`type_id`) REFERENCES `Room_type` (`type_id`);

ALTER TABLE `Room_type_amenity` ADD FOREIGN KEY (`type_id`) REFERENCES `Room_type` (`type_id`);

ALTER TABLE `Room_type_amenity` ADD FOREIGN KEY (`amenity_id`) REFERENCES `Room_Amenity` (`amenity_id`);

ALTER TABLE `Reservation` ADD FOREIGN KEY (`guest_id`) REFERENCES `Guest` (`guest_id`);

ALTER TABLE `Reservation` ADD FOREIGN KEY (`room_id`) REFERENCES `Room` (`room_id`);

ALTER TABLE `Reservation` ADD FOREIGN KEY (`special_request`) REFERENCES `Special_request` (`spId`);

ALTER TABLE `Staff` ADD FOREIGN KEY (`role_id`) REFERENCES `Staff_role` (`role_id`);

ALTER TABLE `Invoice` ADD FOREIGN KEY (`guest_id`) REFERENCES `Guest` (`guest_id`);

ALTER TABLE `Invoice` ADD FOREIGN KEY (`reservation_id`) REFERENCES `Reservation` (`reservation_id`);

ALTER TABLE `Invoice` ADD FOREIGN KEY (`order_id`) REFERENCES `Order` (`order_id`);

ALTER TABLE `Staff_assignment` ADD FOREIGN KEY (`staff_id`) REFERENCES `Staff` (`staff_id`);

ALTER TABLE `Staff_assignment` ADD FOREIGN KEY (`reservation_id`) REFERENCES `Reservation` (`reservation_id`);

ALTER TABLE `Payment` ADD FOREIGN KEY (`reservation_id`) REFERENCES `Reservation` (`reservation_id`);

ALTER TABLE `Payment` ADD FOREIGN KEY (`payment_method_id`) REFERENCES `Payment_method` (`payment_method_id`);

ALTER TABLE `Payment` ADD FOREIGN KEY (`order_id`) REFERENCES `Order` (`order_id`);

ALTER TABLE `Payment` ADD FOREIGN KEY (`guest_id`) REFERENCES `Guest` (`guest_id`);

ALTER TABLE `Payment` ADD FOREIGN KEY (`invoice_id`) REFERENCES `Invoice` (`invoice_id`);

ALTER TABLE `Order` ADD FOREIGN KEY (`guest_id`) REFERENCES `Guest` (`guest_id`);

ALTER TABLE `Order` ADD FOREIGN KEY (`table_id`) REFERENCES `Table` (`table_id`);

ALTER TABLE `Menu_order` ADD FOREIGN KEY (`menu_id`) REFERENCES `Menu` (`menu_id`);
