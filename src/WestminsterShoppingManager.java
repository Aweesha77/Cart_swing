import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.*;
//perfect one
public class WestminsterShoppingManager implements ShoppingManager {
    Scanner input = new Scanner(System.in);

    static String electronics;
    static String clothes;
    static ArrayList<Product> consoleProductList = new ArrayList<>();
    private ArrayList<Product> shoppingCartList = new ArrayList<>();
    static ArrayList<User> userList = new ArrayList<>();
    static ArrayList<Product> electronicsHoldArrayList = new ArrayList<>();
    static ArrayList<Product> clothingHoldArrayList = new ArrayList<>();

    public WestminsterShoppingManager() throws IOException {
        menuChoice();
    }

    public void menuDisplay() {
        System.out.println("1. Add a new product to the system");
        System.out.println("2. Delete a product from the system");
        System.out.println("3. Print the list of products in the system");
        System.out.println("4. Save products to a file");
        System.out.println("5. Exit");
        System.out.println("6. Open GUI");
        System.out.println("7. Register customers");
        System.out.print("Enter your choice: ");
        // System.out.println("5. Load products from a file");
        // System.out.println("6. Apply Discount");
    }

    public void menuChoice() throws IOException {
        loadProductsFromFile();
        //loadUserDetails();

        while (true) {
            menuDisplay();
            try {
                int choice = input.nextInt();
                System.out.println();

                switch (choice) {
                    case 1:
                        addProductToSystem();
                        saveProductsToFile();
                        //updateVaribles();
                        break;
                    case 2:
                        removeProductFromSystem();
                        saveProductsToFile();
                        //updateVaribles();
                        break;
                    case 3:
                        printProductList(WestminsterShoppingManager.consoleProductList);
                        //updateVaribles();
                        break;
                    case 4:
                        saveProductsToFile();
                        break;
                     case 5:
                     System.out.println("Exiting the system. Goodbye!");
                     System.exit(0);
                    case 6:
                        openGUI();  // implement this now
                        break;
                    case 7:
                        registerCustomer();
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.\n");
                }
            } catch (Exception e) {
                System.out.println("Entered input is not a number.\n");
                String junk = input.nextLine();
            }
        }
    }

    public void addProductToSystem() {
        while (true) {
            try {
                System.out.println("Choose product type:");
                System.out.println("    1-Electronics");
                System.out.println("    2-Clothing");
                System.out.print("Enter your option: ");
                int option = input.nextInt();
                System.out.println("\n");

                if (option == 1) {
                    addElectronicProduct();
                } else if (option == 2) {
                    addClothingProduct();
                } else {
                    System.out.println("Something went wrong\n");
                }

                System.out.print("Do you want to add another Item (Yes/No): ");
                String answer = input.next();
                System.out.println();

                if (answer.equalsIgnoreCase("No")) {
                    break;
                } else if (answer.equalsIgnoreCase("Yes")) {
                    continue;
                } else {
                    System.out.println("Entered input is invalid\nDirecting to the MAIN MENU\n");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Enter a valid input(like numbers)\n");
                String junk = input.nextLine();
            }
        }
    }


    private void addElectronicProduct() {

        while (true) {
            try {
                System.out.println("ADD ELECTRONIC ITEM\n");
                System.out.print("Enter Item ID: ");
                String productId = input.next();
                System.out.print("Enter Item name: ");
                String productName = input.next();
                System.out.print("Enter number of Items: ");
                int noItems = input.nextInt();
                System.out.print("Enter price of the item: ");
                double price = input.nextDouble();
                System.out.print("Enter Brand of the item: ");
                String brand = input.next();
                System.out.print("Item warranty period (Days): ");
                int warrantyPeriod = input.nextInt();

                Electronics newItem = new Electronics(productId, productName, noItems, price, brand, warrantyPeriod);
                WestminsterShoppingManager.consoleProductList.add(newItem);
                //System.out.println(WestminsterShoppingManager.consoleProductList);//make this comment
                //WestminsterShoppingManager.saveListAdd.add(newItem);
                System.out.println();
                System.out.println("Product details-\n");
                displayProductInfo(newItem);
                System.out.println("Product added successfully.\n");

            } catch (Exception e) {
                System.out.println("Enter a valid input(like numbers)\n");
                String junk = input.nextLine();
            }

            try {
                System.out.print("Do you want to add another Electronic Item (Yes/No): ");
                String answer = input.next();

                if (answer.equalsIgnoreCase("No")) {
                    break;
                } else if (answer.equalsIgnoreCase("Yes")) {
                    continue;
                } else {
                    System.out.println("Entered input is invalid\n");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input\n");   //
                String junk = input.nextLine();
            }
        }
    }

    private void addClothingProduct() throws IOException {

        while (true) {
            try {
                System.out.println("ADD CLOTHING ITEM\n");
                System.out.print("Enter Item ID: ");
                String productId = input.next();
                System.out.print("Enter Item name: ");
                String productName = input.next();
                System.out.print("Enter number of Items: ");
                int noItems = input.nextInt();
                System.out.print("Enter price of the item: ");
                double price = input.nextDouble();
                System.out.print("Enter color of item: ");
                String clothColor = input.next();
                System.out.print("Item size of item: ");
                int clothSize = input.nextInt();

                Clothing newItem = new Clothing(productId, productName, noItems, price, clothColor, clothSize);
                WestminsterShoppingManager.consoleProductList.add(newItem);
                //WestminsterShoppingManager.saveListAdd.add(newItem);

                System.out.println();
                System.out.println(WestminsterShoppingManager.consoleProductList);
                System.out.println("Product details-\n");
                displayProductInfo(newItem);
                System.out.println("Product added successfully.\n");

            } catch (Exception e) {
                System.out.println("Enter a valid input(like numbers)\n");
                String junk = input.nextLine();
            }

            System.out.print("Do you want to add another Clothing Item (Yes/No): ");
            String answer = input.next();
            System.out.println();

            if (answer.equalsIgnoreCase("No")) {
                break;
            } else if (answer.equalsIgnoreCase("Yes")) {
                continue;
            } else {
                System.out.println("Entered input is invalid\n");
                break;
            }
        }
        //saveProductsToFile();
    }

    public void removeProductFromSystem() {
        System.out.println("REMOVE ELECTRONIC ITEM\n");

        while (true) {
            Iterator<Product> itemIterator = WestminsterShoppingManager.consoleProductList.iterator();
            if (itemIterator.hasNext()) {
                System.out.print("Enter id of the product: ");
                String deleteProductId = input.next();
                Product product = itemIterator.next();

                if (product.getProductId().equals(deleteProductId)) {
                    System.out.println("Product details-\n");
                    displayProductInfo(product);

                    if (product instanceof Electronics) {
                        System.out.println("A electronic Item");
                    } else {
                        System.out.println("A clothing Item");
                    }

                    // ask whether user want to remove it
                    itemIterator.remove(); // explain it
                    System.out.println(WestminsterShoppingManager.consoleProductList);
                    System.out.println("Product removed successfully.\n");
                    System.out.println("Total number of products left: " + WestminsterShoppingManager.consoleProductList.size());
                } else {
                    System.out.println("No product under the entered ID");
                }

                try {
                    System.out.print("Do you want to remove another Item (Yes/No): ");
                    String answer = input.next();
                    if (answer.equalsIgnoreCase("No")) {
                        break;
                    } else if (answer.equalsIgnoreCase("Yes")) {
                        continue;
                    } else {
                        System.out.println("Entered input is invalid\n");
                    }
                } catch (Exception e) {
                    System.out.println("Enter a valid input(like numbers)\n");
                    String junk = input.nextLine();
                }
            } else {
                System.out.println("Product list is empty.\n");
                break;
            }
        }
    }

    public void displayProductInfo(Product product) {
        System.out.println("Product ID: " + product.getProductId());
        System.out.println("Product Name: " + product.getProductName());
        System.out.println("Available Items: " + product.getNoItems());
        System.out.println("Price: " + product.getPrice());
        // Display additional attributes based on the type of product

        if (product instanceof Electronics) {
            Electronics electronics = (Electronics) product;
            System.out.println("Brand: " + electronics.getBrand());
            System.out.println("Warranty Period: " + electronics.getWarrantyPeriod() + " months");
        } else if (product instanceof Clothing) {
            Clothing clothing = (Clothing) product;
            System.out.println("Size: " + clothing.getClothSize());
            System.out.println("Color: " + clothing.getClothColor());
        }
    }

    private void printProductList(ArrayList<Product> consoleProductList) {

        System.out.println("List of Products in the System:");
        if (WestminsterShoppingManager.consoleProductList.isEmpty()) {
            System.out.println("No products in the system.");
        } else {
            ArrayList<Product> sortedProducts = new ArrayList<>(WestminsterShoppingManager.consoleProductList);
            selectionSort(sortedProducts);

            for (Product product : sortedProducts) {
                System.out.println("-------------------------");
                System.out.println("Product ID: " + product.getProductId());
                System.out.println("Product Name: " + product.getProductName());
                System.out.println("Number of Items: " + product.getNoItems());
                System.out.println("Price: " + product.getPrice());

                if (product instanceof Electronics) {
                    System.out.println("Brand: " + ((Electronics) product).getBrand());
                    System.out.println("Warranty Period: " + ((Electronics) product).getWarrantyPeriod() + " months");
                    System.out.println("Product Type: Electronics");
                } else if (product instanceof Clothing) {
                    System.out.println("Color: " + ((Clothing) product).getClothColor());
                    System.out.println("Size: " + ((Clothing) product).getClothSize());
                    System.out.println("Product Type: Clothing");
                }
            }
            System.out.println("-------------------------");
        }
    }

    public static void selectionSort(ArrayList<Product> products) {
        int n = products.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                // Compare product IDs for sorting
                if (products.get(j).getProductId().compareTo(products.get(minIndex).getProductId()) < 0) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            Product temp = products.get(minIndex);
            products.set(minIndex, products.get(i));
            products.set(i, temp);
        }
    }

    private void saveProductsToFile() throws IOException {
//        for (Product obj : WestminsterShoppingManager.saveListAdd) {
//            // Check if the object is not in the first ArrayList
//            if (!WestminsterShoppingManager.consoleProductList.contains(obj)) {
//                // Add the object to the first ArrayList
//                WestminsterShoppingManager.consoleProductList.add(obj);
//            }
//        }
        System.out.println(WestminsterShoppingManager.consoleProductList);
        //File file = new File("ProductListDetails.txt");
        FileWriter fileWriter = new FileWriter("ProductListDetails.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (Product product : WestminsterShoppingManager.consoleProductList) {
            // Convert product to a string representation and write it to the file
            String productInfo = convertProductToString(product);
            bufferedWriter.write(productInfo);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }

    private String convertProductToString(Product product) {
        // Customize this method based on your Product class structure
        // For example, assuming Product has getProductId(), getProductName(), etc.
        String productInfo = product.getProductId() + "," + product.getProductName() + "," + product.getNoItems() + ","
                + product.getPrice();

        // Additional attributes for Electronics
        if (product instanceof Electronics) {
            Electronics electronics = (Electronics) product;
            productInfo += "," + electronics.getBrand() + "," + electronics.getWarrantyPeriod()+","+"Electronics";
        }

        // Additional attributes for Clothing
        if (product instanceof Clothing) {
            Clothing clothing = (Clothing) product;
            productInfo += "," + clothing.getClothColor() + "," + clothing.getClothSize()+ ","+"Clothing"+","+"";
        }
        return productInfo;
    }

    private void loadProductsFromFile() throws IOException {
        //File file = new File("ProductListDetails.txt");
        FileReader fileReader = new FileReader("ProductListDetails.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            // Convert the line (product information) back to a Product object
            //System.out.println(++count);

            convertStringToProduct(line);
        }
        //System.out.println(WestminsterShoppingManager.consoleProductList);
        bufferedReader.close();
    }

    private void convertStringToProduct(String line) {
        String[] parts = line.split(",");
        if(parts.length > 1){
            Product product = null;

            String productId = parts[0];
            String productName = parts[1];

            int noItems = Integer.parseInt(parts[2]);
            double price = Double.parseDouble(parts[3]);

            if (parts.length == 7) {
                // Electronics
                String brand = parts[4];
                try {
                    int warrantyPeriod = Integer.parseInt(parts[5]);
                    product = new Electronics(productId, productName, noItems, price, brand, warrantyPeriod);
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing warranty period for Electronics product. Skipping product.");
                }
            } else if (parts.length == 8) {
                // Clothing
                String clothColor = parts[4];
                try {
                    int clothSize = Integer.parseInt(parts[5]);
                    product = new Clothing(productId, productName, noItems, price, clothColor, clothSize);
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing cloth size for Clothing product. Skipping product.");
                }
            }
            else {
                // Default case (consider adjusting this based on your actual class structure)
                product = new Product(productId, productName, noItems, price) {
                    @Override
                    public void displayProductDetails() {
                        // Implement if needed
                    }
                };
            }
            if (product != null) {
                WestminsterShoppingManager.consoleProductList.add(product);
            }
//            public void displayProductDetails();


        }
        else{
            System.out.println("File loading error");     //////////////////
        }

    }

    private void convertStringToProducts(String line) {
        Product product=null;
        String[] parts = line.split(",");
        if(!(parts[1].isEmpty())){
            System.out.println("File loading error");
        }
        else{

            String productId = parts[0];
            String productName = parts[1];

            int noItems = Integer.parseInt(parts[2]);
            double price = Double.parseDouble(parts[3]);


            if (parts[4].equals("Electronics")) {
                String brand = parts[4];
                double warrantyPeriod = Double.parseDouble(parts[5]);  // Change this line
            }

            if (parts[4].equals("Clothing")) {
                String clothColor = parts[4];
                double clothSize = Double.parseDouble(parts[5]);  // Change this line
            }

            WestminsterShoppingManager.consoleProductList.add(product);
        }
    }


    private void openGUI() {
        //System.out.println(WestminsterShoppingManager.consoleProductList);
        WestminsterGUI frame = new WestminsterGUI(shoppingCartList, WestminsterShoppingManager.userList);
        frame.setTitle("Westminster Shopping Centre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(750, 500); // resize this
    }



    public void registerCustomer() {
        while (true) {
            try {
                System.out.print("Enter User name: ");
                String user_name = input.next();
                System.out.print("Enter User Password: ");
                String user_password = input.next();
                User user = new User(user_name, user_password);
                WestminsterShoppingManager.userList.add(user);
                System.out.println(WestminsterShoppingManager.userList);
                System.out.println("Product added successfully.\n");
                //uploadUser();

                System.out.print("Do you want to register another User (Yes/No): ");
                String answer = input.next();
                System.out.println();

                if (answer.equalsIgnoreCase("No")) {
                    break;
                } else if (answer.equalsIgnoreCase("Yes")) {
                    continue;
                } else {
                    System.out.println("Entered input is invalid\n");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Enter a valid input\n");
                String junk = input.nextLine();
            }
        }
}
}
