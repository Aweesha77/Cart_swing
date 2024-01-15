import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Map;    //addded

public class ShoppingCart {
    static int noProducts=0;

    public static ArrayList<Product> shoppingCartArray = new ArrayList<>();

    ShoppingCart() {
    }

    public void addProduct(Product product) {
        shoppingCartArray.add(product);
    }
    public void noOfProduct(){
         noProducts++;
        System.out.println(noProducts);
    }

    public void removeProduct(Product product) {
        shoppingCartArray.remove(product);
    }

    public double calculateTotal() {
        double total = 0;

        for (int i = 0; i < shoppingCartArray.size(); i++) {
            Product product = shoppingCartArray.get(i); // give the value of object
            total += product.getPrice();
        }
        return total;
    }


}

class TableModel1 extends AbstractTableModel {

    private String[] columnNames = { "Product", "Quantity", "Price" };
    private ArrayList<Product> tableList = new ArrayList<>();

    TableModel1(ArrayList<Product> cartProductArray) {
        if (cartProductArray != null && !cartProductArray.isEmpty()) {     //check whether the shoppingCart list is empty or not
            this.tableList = cartProductArray;
        } else {
            // If the cart is empty, create an empty model
            this.tableList = new ArrayList<>();        //what is this??
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
            return 1;//IMPLEMENT THIS
            //return product.getProductId(); // Assuming there's a getQuantity() method in your Product class-make it
        } else if (columnIndex == 2) {
            return Double.toString(product.getPrice()); // Assuming there's a getPrice() method in your Product class
        }    //product.getPrice()
        return null;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    static ArrayList<Product> productList;
    Map<String, Integer> purchaseHistory;
    double totalPrice = 0;

    private double applyCategoryDiscount() {
        // Apply a 20% discount when the user buys at least three products of the same category
        // (This is a placeholder method; you might need to adjust it based on your actual requirements)
//        double CategoryDiscount = applyCategoryDiscount(totalPrice);
        double categoryDiscount;
        return categoryDiscount = (totalPrice)*0.2;
    }

    private double applyFirstPurchaseDiscount(int userCount) {
        // Apply a 10% discount for the very first purchase
        // (This is a placeholder method; you might need to adjust it based on your actual requirements)
        double firstPurchaseDiscount;
        if (userCount == 0){
            return firstPurchaseDiscount = (totalPrice)*0.1;
        }

        return 0;
    }

    private double getFinalTotal(int userCount) {
        double finalTotal;
        return finalTotal = totalPrice - applyCategoryDiscount() -applyFirstPurchaseDiscount(userCount);

    }


}