-- shows every available room with description
DELIMITER //


DROP PROCEDURE IF EXISTS get_available_rooms;
CREATE DEFINER=`root`@`localhost` PROCEDURE get_available_rooms()
BEGIN
  SELECT r.room_id,rt.type_name,description,capacity,rate FROM Room r JOIN Room_type rt
  ON r.type_id=rt.type_id WHERE is_available=1;
END //
DELIMITER ;
