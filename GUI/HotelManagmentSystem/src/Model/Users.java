package Model;


public final class Users {
		public static Crediential getRoot() {
			Crediential crediential = new Crediential("root","root2332");
			return crediential;
		}
		
		public static Crediential getManager() {
			Crediential crediential = new Crediential("manager","managerpass");
			return crediential;
		}
		
		public static Crediential getReception() {
			Crediential crediential = new Crediential("reception","receptionpass");
			return crediential;
		}
		public static Crediential getRegularStaff() {
			Crediential crediential = new Crediential("regular_staff","regular_staffpass");
			return crediential;
		}
		
		public static Crediential getGuest() {
			Crediential crediential = new Crediential("guest","guestpass");
			return crediential;
		}
}
