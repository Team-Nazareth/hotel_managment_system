-- shows the menu 
DELIMITER //
DROP PROCEDURE IF EXISTS get_menu;
CREATE DEFINER=`root`@`localhost` PROCEDURE get_menu()
BEGIN
SELECT * FROM Menu;
END 
