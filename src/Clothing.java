import java.io.Serializable;

public class Clothing extends Product implements Serializable {
    private String clothColor;
    private int clothSize;

    public Clothing(String productId, String productName, int noItems, double price, String clothColor, int clothSize) {
        super(productId,productName,noItems,price);
        this.clothColor = clothColor;
        this.clothSize = clothSize;
    }

    public String getClothColor() {
        return clothColor;
    }

    public void setClothColor(String clothColor) {
        this.clothColor = clothColor;
    }

    public float getClothSize() {
        return clothSize;
    }

    public void setClothSize(float clothSize) {
        this.clothSize = (int) clothSize;
    }

    @Override
    public void displayProductDetails() {
        System.out.println("Size: " + getClothSize());
        System.out.println("Color: " + getClothColor());
    }

    @Override
    public String displayProductInfo() {
        return "Category: Clothing"+ "\n" +
                "Product ID: " + getProductId() + "\n" +
                "Name: " + getProductName() + "\n" +
                "Price: " + getPrice()+"\n"+
                "Color: " + clothColor+"\n"+
                "No of Items: " + getNoItems()

                ;

    }
}
