package view.ManagerView;

import view.ProfileFields;

public class StaffProfile extends ProfileFields {
	public Integer staff_id;
	public String role;
	public String role_description; 
	public access_level accessLevel;
	public enum access_level {
		MANAGER,
		RECEPTION,
		REGULAR_STAFF
	};
}