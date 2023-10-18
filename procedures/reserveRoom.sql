delimiter //

drop procedure if exists reserve_room;
create procedure reserve_room(
  in g_id int,
  in rm_id int,
  in chkout_date datetime
)
begin
  INSERT INTO Reservation (
    guest_id,room_id,checkout_date
  ) VALUES (g_id,rm_id,chkout_date );
end//
delimiter ;
