CREATE USER 'manager'@'%' IDENTIFIED BY 'managerpass';
GRANT ALL PRIVILEGES ON hotel_managment_system.* TO 'manager'@'%';

CREATE USER 'reception'@'%' IDENTIFIED BY 'receptionpass';
GRANT ALL PRIVILEGES ON hotel_managment_system.guest  TO 'reception'@'%';
GRANT ALL PRIVILEGES ON hotel_managment_system.reservation  TO 'reception'@'%';
GRANT CREATE ON hotel_managment_system.staff_assignment  TO 'reception'@'%';

CREATE USER 'regular_staff'@'%' IDENTIFIED BY 'regular_staffpass';
GRANT SELECT ON hotel_managment_system.staff_assignment TO 'regular_staff'@'%';

CREATE USER 'guest'@'%' IDENTIFIED BY 'guestpass';
GRANT SELECT, INSERT, UPDATE ON hotel_managment_system.guest TO 'guest'@'%';
GRANT SELECT, INSERT, UPDATE ON hotel_managment_system.order TO 'guest'@'%';
GRANT SELECT, INSERT, UPDATE ON hotel_managment_system.reservation TO 'guest'@'%';
GRANT SELECT ON hotel_managment_system.invoice TO 'guest'@'%';
GRANT SELECT ON hotel_managment_system.menu TO 'guest'@'%';
GRANT SELECT ON hotel_managment_system.available_rooms TO 'guest'@'%';
GRANT SELECT ON hotel_managment_system.payment_method TO 'guest'@'%';


FLUSH PRIVILEGES;
SELECT user, host FROM mysql.user ;