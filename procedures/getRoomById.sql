--shows the room details with that id
DELIMITER //
DROP PROCEDURE IF EXISTS get_room_by_id;
CREATE PROCEDURE get_room_by_id(
       IN id INT
)
BEGIN
  SELECT * FROM Room r JOIN Room_type rt
  ON r.type_id=rt.type_id WHERE room_id=id;
END //
DELIMITER ;
