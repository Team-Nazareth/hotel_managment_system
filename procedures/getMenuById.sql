-- shows the menu item with that id
DELIMITER //
DROP PROCEDURE IF EXISTS get_menu_by_id;
CREATE DEFINER=`root`@`localhost` PROCEDURE get_menu_by_id(
       IN id INT
)
BEGIN
  SELECT * FROM Menu WHERE menu_id=id;
END
