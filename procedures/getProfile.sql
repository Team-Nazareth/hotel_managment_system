-- shows the profile
DELIMITER //


DROP PROCEDURE IF EXISTS get_profile;
CREATE PROCEDURE get_profile(
    in id int,
    in user_type varchar(6)
)
BEGIN
    IF lower(user_type)='guest' 
       THEN SELECT * FROM guest WHERE guest_id=id;
    ELSE SELECT * FROM staff WHERE staff_id=id;
    END IF;
END //
DELIMITER ;

