CREATE DEFINER=`root`@`localhost` TRIGGER `after_insert_payment` AFTER INSERT ON `payment` FOR EACH ROW BEGIN
	UPDATE invoice 
    SET payment_total = payment_total + NEW.amount
    WHERE invoice_id = NEW.invoice_id ;
END