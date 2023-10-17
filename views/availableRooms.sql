DROP VIEW IF EXISTS `available_rooms`;
CREATE VIEW `available_rooms` AS 
    SELECT `Room`.`room_id` AS `room_id` FROM `Room`
WHERE `Room`.`is_available` = 1;
