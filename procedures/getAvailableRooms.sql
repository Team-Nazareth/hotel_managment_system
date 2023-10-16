--shows every available room with description
DELIMITER //
DROP PROCEDURE IF EXISTS get_available_rooms;
CREATE PROCEDURE get_available_rooms()
BEGIN
  SELECT * FROM Room r JOIN Room_type rt
  ON r.type_id=rt.type_id;
END //
DELIMITER ;

