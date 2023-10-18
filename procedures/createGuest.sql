delimiter //

CREATE DEFINER=`root`@`localhost` PROCEDURE `create_guest`(
    IN pswd varchar(20),
    IN f_name varchar(20),
    IN l_name varchar(20),
    IN eml varchar(30),
    IN sx char(1),
    IN b_date date,
    IN p_num char(9)
)
BEGIN
        IF (pswd IS NOT NULL AND p_num IS NOT NULL AND f_name IS NOT NULL) THEN
        
			INSERT INTO Guest (username, password, first_name, last_name, email, sex, phone_num,birth_date)
			VALUES ('placeholder', SHA2(pswd, 256), f_name, l_name, eml, sx, p_num,b_date);

            -- generate username
			UPDATE Guest 
			SET username = CONCAT(f_name, LAST_INSERT_ID())
			WHERE guest_id = LAST_INSERT_ID();
        ELSE
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'one or more important attribute/s are null';
        END IF;
END