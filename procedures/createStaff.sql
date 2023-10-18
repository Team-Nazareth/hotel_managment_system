delimiter //

CREATE DEFINER=`root`@`localhost` PROCEDURE `create_staff`(
	IN roleId int,
    IN pswd varchar(20),
    IN f_name varchar(20),
    IN l_name varchar(20),
    IN eml varchar(30),
    IN sx char(1),
    IN b_date date,
    IN p_num char(9),
    IN reportsTo int,
    IN accessLevel enum('manager','reception','regular_staff')
)
BEGIN
        IF (pswd IS NOT NULL AND p_num IS NOT NULL AND f_name IS NOT NULL AND roleId IS NOT NULL ) THEN
        
			IF NOT EXISTS(SELECT role_id FROM Staff_role WHERE role_id = roleId) THEN
				SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'referenced role doesnt exist in staff_role table';
            ELSE
				INSERT INTO Staff 
                (username, password, role_id, first_name, 
                last_name, email, sex, phone_num,birth_date, reports_to, access_level)
				VALUES ('placeholder', SHA2(pswd, 256), roleId ,f_name,
						l_name, eml, sx, p_num,b_date, reportsTo, accessLevel);

				-- generate username
				UPDATE Staff 
				SET username = CONCAT(f_name, LAST_INSERT_ID())
				WHERE staff_id = LAST_INSERT_ID();
            END IF;
            
        ELSE -- outer
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'one or more important attribute/s are null';
        END IF; -- outer
END