-- edits the profile
DELIMITER //

DROP PROCEDURE IF EXISTS edit_guest_profile;
CREATE DEFINER=`root`@`localhost` PROCEDURE edit_guest_profile(
    in id int,
    in ph_num char(9),
    in pw varchar(20),
    in eml varchar(30)
)
BEGIN
    UPDATE guest
    SET phone_num=IFNULL(ph_num,phone_num),email=IFNULL(eml,email),password=IFNULL(pw,password)
    WHERE guest_id=id;
END //
DELIMITER ;

