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

        if (noOfElectronics>=3){      //if user buy 3 products from a category,user eligible for 20% discount
            categoryDiscount=calculateTotal()*0.2;
            return Math.round(categoryDiscount);
        }

        else if (noOfClothing>=3){
            categoryDiscount=calculateTotal()*0.2;
            return Math.round(categoryDiscount);     //Math.round use to avoid decimals
        }
        else{
            return 0;
        }

    }
    public double firstPurchaseDiscount(){
        double discount=0;
        if (LoginGUI.enteredUsername==null) {     //non-registered customers don't have username
            discount=0;
        }
        else{                                    //check username and if user had purchased before.if user buy before history should be 1
            for (User user : WestminsterShoppingManager.userList) {
                if (LoginGUI.enteredUsername.equals(user.getUserName() )&& user.getHistory()==0){
                    //System.out.println(user.getHistory());

                    discount=calculateTotal()*0.1;
                }
            }
        }

        return Math.round(discount);
    }

    public void removeProduct(Product product) {
        shoppingCartArray.remove(product);
    }

    public static double calculateTotal() {
        double total = 0;

        for (Product product_loop : shoppingCartArray) {
            total += product_loop.getPrice() * product_loop.getQuantity();   //quantity*product price
        }
        return Math.round(total);
    }

    public double calculateFinalTotal(){
        return calculateTotal()-(discountCategory()+firstPurchaseDiscount());
    }
}

class TableModel1 extends AbstractTableModel {     //this tablemodel for shopping cart table

    private String[] columnNames = { "Product", "Quantity", "Price" };
    private ArrayList<Product> tableList = new ArrayList<>();

    TableModel1(ArrayList<Product> cartProductArray) {
        if (cartProductArray != null && !cartProductArray.isEmpty()) {     //check whether the shoppingCart list is empty or not
            this.tableList = cartProductArray;
        } else {

            this.tableList = new ArrayList<>();        // If the cart is empty, creating an empty model
        }
    }

    @Override
    public int getRowCount() {
        return tableList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = tableList.get(rowIndex);

        if (columnIndex == 0) {
            return product.getProductName();
        } else if (columnIndex == 1) {

            //return product.getProductId(); // Assuming there's a getQuantity() method in your Product class-make it
            return product.getQuantity();
        } else if (columnIndex == 2) {
            return Double.toString(product.getPrice()); // Assuming there's a getPrice() method in your Product class
        }    //product.getPrice()
        return null;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    double totalPrice = 0;

    private double applyCategoryDiscount() {
        double categoryDiscount;
        return categoryDiscount = (totalPrice)*0.2;
    }

    private double applyFirstPurchaseDiscount(int userCount) {
        double firstPurchaseDiscount;
        if (userCount == 0){
            return firstPurchaseDiscount = (totalPrice)*0.1;
        }
        return 0;  //make this
    }

    private double getFinalTotal(int userCount) {
        double finalTotal;
        return finalTotal = totalPrice - applyCategoryDiscount() -applyFirstPurchaseDiscount(userCount);

    }


}