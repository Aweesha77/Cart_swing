//Date:2024.01.12(12.30am)
//Author:Kashuni Aweesha
//Application:ShoppingSystem Application(OOP_CW)

import java.io.*;
import java.util.ArrayList;      //import all the classes
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;
//perfect one
public class WestminsterShoppingManager implements ShoppingManager {
    final static int MAX_PRODUCTS=50;        //make it final as this variable not changing
    Scanner input = new Scanner(System.in);

    static ArrayList<Product> consoleProductList = new ArrayList<>();     //static use to access in everywhere
    private ArrayList<Product> shoppingCartList = new ArrayList<>();
    static ArrayList<User> userList = new ArrayList<>();          //to hold username,password and history
    static ArrayList<Product> electronicsHoldArrayList = new ArrayList<>();     //to old information about electronic products
    static ArrayList<Product> clothingHoldArrayList = new ArrayList<>();

    public WestminsterShoppingManager() throws IOException {
        menuChoice();
    }

    public void menuDisplay() {
        System.out.println("-------------------MAIN MENU-------------------------");
        System.out.println("1. Add a new product to the system");
        System.out.println("2. Delete a product from the system");
        System.out.println("3. Print the list of products in the system");
        System.out.println("4. Save products to a file");
        System.out.println("5. Register customers");
        System.out.println("6. Open GUI");
        System.out.println("7. Exit");
        System.out.println("-----------------------------------------------------");
        System.out.print("Enter your choice: ");
    }

    public void menuChoice() throws IOException {
        loadProductsFromFile();                       //load user details and product details beginning
        loadUser();

        while (true) {
            menuDisplay();          //display main menu
            try {
                int choice = input.nextInt();
                System.out.println();

                switch (choice) {
                    case 1:
                        addProductToSystem();
                        saveProductsToFile();           //used save method to update file after adding or removing a product
                        break;
                    case 2:
                        removeProductFromSystem();
                        saveProductsToFile();
                        break;
                    case 3:
                        printProductList(WestminsterShoppingManager.consoleProductList);       //sort alphabetical order according to product id
                        break;
                    case 4:
                        saveProductsToFile();
                        break;
                    case 5:
                        registerCustomer();
                        saveUser();         //save user details after registering
                    case 6:
                        openGUI();  //call for login GUI
                        break;
                    case 7:
                        System.out.println("Exiting the system. Goodbye!");   //exist from the application
                        System.exit(0);
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
            if (WestminsterShoppingManager.consoleProductList.size()<MAX_PRODUCTS){   //check whether the product list is below than 50
                try {

                    System.out.println("Choose product type:");
                    System.out.println("    1-Electronics");
                    System.out.println("    2-Clothing");
                    System.out.print("Enter your option: ");
                    int option = input.nextInt();
                    System.out.println("\n");

                    if (option == 1) {
                        addElectronicProduct();         //select the category
                    } else if (option == 2) {
                        addClothingProduct();
                    } else {
                        System.out.println("Something went wrong\n");     //if user input another number this will print
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
                    System.out.println("Enter a valid input(like numbers)\n");  //user input another type input this message will print
                    String junk = input.nextLine();
                }
            }else{
                System.out.println("Product list is almost full\n");
                break;
            }

        }
    }



    private void addElectronicProduct() {
        while (true) {
            if (WestminsterShoppingManager.consoleProductList.size() < MAX_PRODUCTS) {
                try {
                    System.out.println("ADD ELECTRONIC ITEM\n");
                    System.out.print("Enter Item ID: ");
                    String productId = input.next();
                    productId = productId.toUpperCase();

                    if (productId.length() == 4 && Character.isLetter(productId.charAt(0))) {
                        boolean idExists = false;
                        for (Product product : WestminsterShoppingManager.consoleProductList) {
                            if (product.getProductId().equals(productId)) {
                                idExists = true;
                                break;
                            }
                        }

                        if (idExists) {
                            System.out.println("Product with the same ID already exists. Please enter a different ID.\n");
                            continue;
                        }

                        System.out.print("Enter Item name: ");
                        String productName = input.next();
                        System.out.print("Enter number of Items: ");
                        int noItems = input.nextInt();
                        System.out.print("Enter price of the item: ");
                        double price = input.nextDouble();
                        System.out.print("Enter Brand of the item: ");
                        String brand = input.next();
                        System.out.print("Item warranty period (Days): ");     //creating the product instance
                        int warrantyPeriod = input.nextInt();

                        Electronics newItem = new Electronics(productId, productName, noItems, price, brand, warrantyPeriod);
                        WestminsterShoppingManager.consoleProductList.add(newItem);   //add it to the product arraylist
                        System.out.println();
                        System.out.println("Product details-\n");
                        displayProductInfo(newItem);
                        System.out.println("Product added successfully.\n");


                    } else {
                        System.out.println("Invalid ID format. Please try again.\n");
                    }

                    // Check if the entered ID already exists

                } catch (Exception e) {
                    System.out.println("Enter a valid input (like numbers)\n");
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
            } else {
                System.out.println("Product list is almost full");          //if product list is 50
                break;
            }
        }
    }

    private void addClothingProduct(){

        while (true) {
            if (WestminsterShoppingManager.consoleProductList.size()<MAX_PRODUCTS){
                try {
                    System.out.println("ADD CLOTHING ITEM\n");
                    System.out.print("Enter Item ID: ");
                    String productId = input.next();
                    productId = productId.toUpperCase();       //product id's letter is getting as capital letters

                    if (productId.length() == 4 && Character.isLetter(productId.charAt(0))) {    //check the first element is string and length should be 4

                        boolean idExists = false;                                                    //try to find whether the entered id is already exist or not

                        for (Product product : WestminsterShoppingManager.consoleProductList) {
                            if (product.getProductId().equals(productId)) {
                                idExists = true;
                                break;
                            }
                        }

                        if (idExists) {
                            System.out.println("Product with the same ID already exists. Please enter a different ID.\n");
                            continue;
                        }

                        System.out.print("Enter Item name: ");
                        String productName = input.next();
                        System.out.print("Enter number of Items: ");
                        int noItems = input.nextInt();
                        System.out.print("Enter price of the item: ");
                        double price = input.nextDouble();
                        System.out.print("Enter color of item: ");
                        String clothColor = input.next();
                        System.out.print("Item size of item(UK size): ");
                        int clothSize = input.nextInt();                             //create cloth instance

                        Clothing newItem = new Clothing(productId, productName, noItems, price, clothColor, clothSize);
                        WestminsterShoppingManager.consoleProductList.add(newItem);

                        System.out.println();
                        //System.out.println(WestminsterShoppingManager.consoleProductList);
                        System.out.println("Product details-\n");
                        displayProductInfo(newItem);
                        System.out.println("Product added successfully.\n");

                    } else {
                        System.out.println("Invalid ID format. Please try again.");
                    }


                } catch (Exception e) {
                    System.out.println("Enter a valid input(like numbers)\n");
                    String junk = input.nextLine();
                }

                System.out.print("Do you want to add another Clothing Item (Yes/No): \n");
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
            else {
                System.out.println("Product is almost full");
                break;
            }

        }
    }

    public void removeProductFromSystem(){
        System.out.println("REMOVE ITEM\n");

        while (true) {
            boolean productFound = false;

            System.out.print("Enter id of the product: ");
            String deleteProductId = input.next();

            for (Product product : WestminsterShoppingManager.consoleProductList) {   //find the entered id has a product
                if (product.getProductId().equals(deleteProductId)) {
                    System.out.println("Product details-\n");
                    displayProductInfo(product);

                    if (product instanceof Electronics) {
                        System.out.println("A electronic Item");
                    } else {
                        System.out.println("A clothing Item");
                    }

                    WestminsterShoppingManager.consoleProductList.remove(product);  //remove the product from arraylist
                    productFound = true;
//                    System.out.println(WestminsterShoppingManager.consoleProductList);
                    System.out.println("Product removed successfully.\n");
                    System.out.println("Total number of products left: " + WestminsterShoppingManager.consoleProductList.size());
//                    WestminsterShoppingManager.saveProductsToFile();
                    break; // Exit the loop once the product is found and removed
                }
            }

            if (!productFound) {
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
                System.out.println("Enter a valid input (like numbers)\n");
                String junk = input.nextLine();
            }

            if (WestminsterShoppingManager.consoleProductList.isEmpty()) {
                System.out.println("Product list is empty.\n");
                break;
            }
        }
    }


    public void displayProductInfo(Product product) {
        System.out.println("Product ID: " + product.getProductId());      //using get methods access the product
        System.out.println("Product Name: " + product.getProductName());
        System.out.println("Available Items: " + product.getNoItems());
        System.out.println("Price: " + product.getPrice());
        // Display additional attributes based on the type of product

        if (product instanceof Electronics) {
            Electronics electronics = (Electronics) product;             //cast product to electronics
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
            ArrayList<Product> sortedProducts = new ArrayList<>(WestminsterShoppingManager.consoleProductList);      //create an arraylist and assign product list to it
            selectionSort(sortedProducts);        //access the method call selectionSort

            for (Product product : sortedProducts) {                //created arraylist use for sorting
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