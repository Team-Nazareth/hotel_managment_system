-- CREATE USER 'manager'@'%' IDENTI-- FIED BY 'managerpass';
-- GRANT ALL PRIVILEGES ON testhrs.* TO 'manager'@'%';

CREATE USER 'reception'@'%' IDENTIFIED BY 'receptionpass';
GRANT ALL PRIVILEGES ON testhrs.guest  TO 'reception'@'%';
GRANT ALL PRIVILEGES ON testhrs.reservation  TO 'reception'@'%';
GRANT CREATE ON testhrs.staff_assignment  TO 'reception'@'%';

CREATE USER 'regular_staff'@'%' IDENTIFIED BY 'regular_staffpass';
GRANT SELECT ON testhrs.staff_assignment TO 'regular_staff'@'%';

CREATE USER 'guest'@'%' IDENTIFIED BY 'guestpass';
GRANT SELECT, INSERT, UPDATE ON testhrs.guest TO 'guest'@'%';
GRANT SELECT, INSERT, UPDATE ON testhrs.order TO 'guest'@'%';
GRANT SELECT, INSERT, UPDATE ON testhrs.reservation TO 'guest'@'%';
GRANT SELECT ON testhrs.invoice TO 'guest'@'%';
GRANT SELECT ON testhrs.menu TO 'guest'@'%';
GRANT SELECT ON testhrs.available_rooms TO 'guest'@'%';
GRANT SELECT ON testhrs.payment_method TO 'guest'@'%';



FLUSH PRIVILEGES;
SELECT user, host FROM mysql.user ;