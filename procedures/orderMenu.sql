
DELIMITER //

DROP PROCEDURE IF EXISTS order_menu;

CREATE PROCEDURE order_menu(

	    IN g_id INT,

	    IN t_id INT,

	    IN quant INT,

	    IN mn_id INT

)

BEGIN

START TRANSACTION;

	      INSERT INTO order (guest_id,table_id,quantity) VALUES

		      (g_id,t_id,quant);

		          IF mn_id NOT IN (SELECT menu_id FROM menu) THEN 

			        ROLLBACK;
		          ELSE 

		 	        INSERT INTO menu_order (menu_id,order_id) VALUES
     
    			        (mn_id,LAST_INSERT_ID());

		                COMMIT;
 		          END IF;

END //

DELIMITER ;
