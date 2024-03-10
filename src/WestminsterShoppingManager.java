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