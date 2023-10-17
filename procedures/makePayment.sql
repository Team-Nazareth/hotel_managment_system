CREATE DEFINER=`root`@`localhost` PROCEDURE `make_payment`(
		IN invoiceId int,
        IN  p_m_id int,
        IN amt int
)
BEGIN
	INSERT INTO Payment ( invoice_id, payment_method_id, amount)
    VALUES (invoiceId, p_m_id, amt);
END