import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ShoppingCart {
    static int productCount=0;

    public static void setProductCount(int productCount) {
        ShoppingCart.productCount = productCount;
    }

    public static int getProductCount(Product product) {
        return productCount;
    }
    public static ArrayList<Product> shoppingCartArray = new ArrayList<>();   //make it public arraylist as any class can access to it

    ShoppingCart(Product product) {
    }
    public int addProduct(Product product) {
        for (Product cartProduct : ShoppingCart.shoppingCartArray) {
            if (product.getProductId().equals(cartProduct.getProductId())) {   //check product id equals to cart's product id
                int currentQuantity = cartProduct.getQuantity();
                cartProduct.setQuantity(currentQuantity + 1);   //update the quantity if same product add
                return currentQuantity + 1;
            }
        }

        product.setQuantity(1);
        shoppingCartArray.add(product);   //add product to shopping cart

        return 1;
    }

    public double discountCategory(){
        double categoryDiscount;
        double noOfElectronics=0;
        double noOfClothing=0;

        for (Product product : ShoppingCart.shoppingCartArray) {
            if (product instanceof Electronics){
                noOfElectronics=noOfElectronics+product.getQuantity();   //get quantity to each product category to find if user eligible for 20% discount
            }
            if (product instanceof Clothing){
                noOfClothing=noOfClothing+product.getQuantity();
            }
        }