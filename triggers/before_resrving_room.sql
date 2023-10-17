-- trigger for reserving a room

-- check room availablity
-- generate invoice when checkout date provided

DELIMITER //

DROP TRIGGER IF EXISTS before_reserving_room;
CREATE TRIGGER before_reserving_room
BEFORE INSERT ON reservation
FOR EACH ROW
BEGIN
    DECLARE invoice_total int;
    DECLARE unit_price int;
    
    
    SET unit_price = (
		SELECT 
			rate
		FROM room r 
		JOIN room_type rt USING (type_id)
		WHERE room_id = NEW.room_id);
        
	-- Check room availability
	IF NOT( SELECT is_available FROM room 
        WHERE room_id = NEW.room_id) THEN 
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Room not available for the selected dates.'; 
	ELSE 
		UPDATE room SET is_available = 0
        WHERE room_id = NEW.room_id;
	END IF;
    
    -- Generate invoice when checkout date is provided
    IF NEW.checkout_date IS NOT NULL THEN
		SET invoice_total = DATEDIFF(NEW.checkout_date , NEW.checkin_date) * unit_price;
        
        INSERT INTO invoice (guest_id, reservation_id, invoice_total, payment_total, invoice_date)
        VALUES (NEW.guest_id, NEW.reservation_id, invoice_total , 0, NOW());
    END IF;
END //

DELIMITER ;
