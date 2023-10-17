CREATE DEFINER=`root`@`localhost` TRIGGER `menu_order_AFTER_INSERT` AFTER INSERT ON `menu_order` FOR EACH ROW BEGIN
	-- generate invoice on menu order
    DECLARE total_price float;
    DECLARE guest_id_ int;
    
    SET total_price = (
			SELECT 
				SUM(price*quantity)
			FROM menu_order mo 
			JOIN menu m USING (menu_id)
			JOIN `Order` O USING (order_id)
			GROUP BY O.order_id
			HAVING O.order_id = NEW.order_id);

    SET guest_id_ = (
			SELECT 
				distinct guest_id
			FROM `order` o
			JOIN  menu_order mo  USING (order_id)
			WHERE order_id = NEW.order_id);

        INSERT INTO invoice (guest_id, order_id, invoice_total, payment_total)
        VALUES (guest_id_, NEW.order_id, total_price , 0);

END