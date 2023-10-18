-- trigger for reserving a room

-- check room availablity

DELIMITER //

DROP TRIGGER IF EXISTS before_reserving_room;
CREATE TRIGGER before_reserving_room
BEFORE INSERT ON reservation
FOR EACH ROW
BEGIN
        
	-- Check room availability
	IF NOT( SELECT is_available FROM room 
        WHERE room_id = NEW.room_id) THEN 
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Room not available for the selected dates.'; 
	ELSE 
		UPDATE room SET is_available = 0
        WHERE room_id = NEW.room_id;
	END IF;
    
END //

DELIMITER ;
