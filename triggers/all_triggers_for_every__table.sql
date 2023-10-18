DELIMITER //

CREATE TRIGGER guest_after_insert
AFTER INSERT ON guest
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('guest', NEW.guest_id, 'INSERT', CURRENT_USER());
END;

CREATE TRIGGER guest_after_update
AFTER UPDATE ON guest
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('guest', NEW.guest_id, 'UPDATE', CURRENT_USER());
END;

CREATE TRIGGER guest_after_delete
AFTER DELETE ON guest
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('guest', OLD.guest_id, 'DELETE', CURRENT_USER());
END;
CREATE TRIGGER invoice_after_insert
AFTER INSERT ON invoice
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('invoice', NEW.invoice_id, 'INSERT', CURRENT_USER());
END;

CREATE TRIGGER invoice_after_update
AFTER UPDATE ON invoice
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('invoice', NEW.invoice_id, 'UPDATE', CURRENT_USER());
END;

CREATE TRIGGER invoice_after_delete
AFTER DELETE ON invoice
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('invoice', OLD.invoice_id, 'DELETE', CURRENT_USER());
END;
CREATE TRIGGER menu_after_insert
AFTER INSERT ON menu
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('menu', NEW.menu_id, 'INSERT', CURRENT_USER());
END;

CREATE TRIGGER menu_after_update
AFTER UPDATE ON menu
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('menu', NEW.menu_id, 'UPDATE', CURRENT_USER());
END;

CREATE TRIGGER menu_after_delete
AFTER DELETE ON menu
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('menu', OLD.menu_id, 'DELETE', CURRENT_USER());
END;

CREATE TRIGGER order_after_insert
AFTER INSERT ON `order`
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('order', NEW.order_id, 'INSERT', CURRENT_USER());
END;

CREATE TRIGGER order_after_update
AFTER UPDATE ON `order`
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('order', NEW.order_id, 'UPDATE', CURRENT_USER());
END;

CREATE TRIGGER order_after_delete
AFTER DELETE ON `order`
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('order', OLD.order_id, 'DELETE', CURRENT_USER());
END;
CREATE TRIGGER payment_after_insert
AFTER INSERT ON payment
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('payment', NEW.payment_id, 'INSERT', CURRENT_USER());
END;

CREATE TRIGGER payment_after_update
AFTER UPDATE ON payment
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('payment', NEW.payment_id, 'UPDATE', CURRENT_USER());
END;

CREATE TRIGGER payment_after_delete
AFTER DELETE ON payment
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('payment', OLD.payment_id, 'DELETE', CURRENT_USER());
END;
CREATE TRIGGER payment_method_after_insert
AFTER INSERT ON payment_method
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('payment_method', NEW.payment_method_id, 'INSERT', CURRENT_USER());
END;

CREATE TRIGGER payment_method_after_update
AFTER UPDATE ON payment_method
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('payment_method', NEW.payment_method_id, 'UPDATE', CURRENT_USER());
END;

CREATE TRIGGER payment_method_after_delete
AFTER DELETE ON payment_method
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('payment_method', OLD.payment_method_id, 'DELETE', CURRENT_USER());
END;
CREATE TRIGGER reservation_after_insert
AFTER INSERT ON reservation
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('reservation', NEW.reservation_id, 'INSERT', CURRENT_USER());
END;

CREATE TRIGGER reservation_after_update
AFTER UPDATE ON reservation
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('reservation', NEW.reservation_id, 'UPDATE', CURRENT_USER());
END;

CREATE TRIGGER reservation_after_delete
AFTER DELETE ON reservation
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('reservation', OLD.reservation_id, 'DELETE', CURRENT_USER());
END;
CREATE TRIGGER room_after_insert
AFTER INSERT ON room
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('room', NEW.room_id, 'INSERT', CURRENT_USER());
END;

CREATE TRIGGER room_after_update
AFTER UPDATE ON room
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('room', NEW.room_id, 'UPDATE', CURRENT_USER());
END;

CREATE TRIGGER room_after_delete
AFTER DELETE ON room
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('room', OLD.room_id, 'DELETE', CURRENT_USER());
END;
CREATE TRIGGER room_amenity_after_insert
AFTER INSERT ON room_amenity
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('room_amenity', NEW.amenity_id, 'INSERT', CURRENT_USER());
END;

CREATE TRIGGER room_amenity_after_update
AFTER UPDATE ON room_amenity
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('room_amenity', NEW.amenity_id, 'UPDATE', CURRENT_USER());
END;

CREATE TRIGGER room_amenity_after_delete
AFTER DELETE ON room_amenity
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('room_amenity', OLD.amenity_id, 'DELETE', CURRENT_USER());
END;
CREATE TRIGGER room_type_after_insert
AFTER INSERT ON room_type
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('room_type', NEW.type_id, 'INSERT', CURRENT_USER());
END;

CREATE TRIGGER room_type_after_update
AFTER UPDATE ON room_type
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('room_type', NEW.type_id, 'UPDATE', CURRENT_USER());
END;

CREATE TRIGGER room_type_after_delete
AFTER DELETE ON room_type
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('room_type', OLD.type_id, 'DELETE', CURRENT_USER());
END;

CREATE TRIGGER staff_after_insert
AFTER INSERT ON staff
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('staff', NEW.staff_id, 'INSERT', CURRENT_USER());
END;

CREATE TRIGGER staff_after_update
AFTER UPDATE ON staff
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('staff', NEW.staff_id, 'UPDATE', CURRENT_USER());
END;

CREATE TRIGGER staff_after_delete
AFTER DELETE ON staff
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('staff', OLD.staff_id, 'DELETE', CURRENT_USER());
END;

CREATE TRIGGER staff_role_after_insert
AFTER INSERT ON staff_role
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('staff_role', NEW.role_id, 'INSERT', CURRENT_USER());
END;

CREATE TRIGGER staff_role_after_update
AFTER UPDATE ON staff_role
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('staff_role', NEW.role_id, 'UPDATE', CURRENT_USER());
END;

CREATE TRIGGER staff_role_after_delete
AFTER DELETE ON staff_role
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('staff_role', OLD.role_id, 'DELETE', CURRENT_USER());
END;
CREATE TRIGGER table_after_insert
AFTER INSERT ON `table`
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('table', NEW.table_id, 'INSERT', CURRENT_USER());
END;

CREATE TRIGGER table_after_update
AFTER UPDATE ON `table`
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('table', NEW.table_id, 'UPDATE', CURRENT_USER());
END;

CREATE TRIGGER table_after_delete
AFTER DELETE ON `table`
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (table_name, record_id, action, user_id)
  VALUES ('table', OLD.table_id, 'DELETE', CURRENT_USER());
END;

