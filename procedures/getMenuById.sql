--shows the menu item with that id
DELIMITER //
DROP PROCEDURE IF EXISTS get_menu_by_id;
CREATE PROCEDURE get_menu_by_id(
       IN id INT
)
BEGIN
  SELECT * FROM Menu WHERE id=room_id;
END //
DELIMITER ;
