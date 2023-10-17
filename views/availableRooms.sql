DROP VIEW IF EXISTS `available_rooms`;
CREATE VIEW `available_rooms` AS 
  SELECT r.room_id,rt.type_name,description,capacity,rate FROM Room r JOIN Room_type rt
  ON r.type_id=rt.type_id WHERE is_available=1;
