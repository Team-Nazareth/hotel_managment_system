package Model;

public class OrderData {
	public String item_name;
    public Integer qty;
    public Integer id;
    public Double unit_price;
    public Category category;

    public enum Category {
        ROOM,
        MENU
    }
}
