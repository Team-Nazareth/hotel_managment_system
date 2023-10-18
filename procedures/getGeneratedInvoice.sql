DELIMITER //

DROP PROCEDURE IF EXISTS get_generated_invoice;
CREATE DEFINER=`root`@`localhost` PROCEDURE get_generated_invoice(
    in g_id int
)
BEGIN
    SELECT * FROM Invoice WHERE guest_id=g_id and invoice_total-payment_total>0;
END