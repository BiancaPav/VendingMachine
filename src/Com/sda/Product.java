package Com.sda;

public enum Product {
    SNICKERS("Snickers", 2.5F, "60g", "#1"),
    COCA_COLA("Coca Cola", 5F, "500ml", "#2"),
    WATER("Borsec", 3F, "500ml","#3"),
    PEANUTS("Mogyi", 6F, "50g","#4"),
    CHIPS("Lays", 4F, "120g","#5"),
    KINDER("Kinder Bueno", 3.5F, "70g", "#6");

    private String name;
    private float price;
    private String quantity;
    private String code;

    Product(String name, float price, String quantity, String code) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getCode() {
        return code;
    }
}
