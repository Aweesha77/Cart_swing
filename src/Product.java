import java.io.Serializable;

public abstract class Product implements Serializable {
    private String productId;
    private String productName;
    private int noItems;
    private double price;
    private int quantity;   //to update the shopping cart

    public Product(String productId, String productName, int noItems, double price) {    //constructor
        this.productId = productId;
        this.productName = productName;
        this.noItems = noItems;
        this.price = price;
    }



    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNoItems() {
        return noItems;
    }

    public void setNoItems(int noItems) {
        this.noItems = noItems;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract void displayProductDetails();

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public String displayProductInfo() {
        return "Product ID: " + productId + "\n" +
                "Name: " + productName + "\n" +
                "Price: " + price + "\n" +
                "No of Items: " + noItems
                ;

    }

}
