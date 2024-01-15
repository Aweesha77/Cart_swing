
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public class WestminsterGUI extends JFrame {   //remove action listener

    public ArrayList<Product> selectedProductsArray = new ArrayList<>();


    JButton shoppingCartButton, addCartButton;
    JTextArea area;
    JPanel northPanel, centerPanel, southPanel, extraPanel1, extraPanel2;
    JLabel label;
    JTable table;
    JScrollPane pane;
    JComboBox<String> comboBox;
    TableModel tableModel; //ask it

    private ArrayList<Electronics> electronicsArrayList;
    public ArrayList<Clothing> clothingArrayList;
    private ArrayList<Product> westminsterProductList;     //implemented 9.30pm
    public ArrayList<Product> shoppingCartList2;       //implemented 9.30pm

    private static ArrayList<Product> guiProductList;
    private ArrayList<User> user = new ArrayList<>();
    private ArrayList<Product> loginListCheck = new ArrayList<>();

    public WestminsterGUI(ArrayList<Product> shoppingCartList, ArrayList<User> userList) {

        //ShoppingCart.shoppingCartArray = productList;
        LoginGui.userDetails = userList;   //aweesha

        //WestminsterGUI.guiProductList = productList;

        northPanel = new JPanel();
        this.add(northPanel, BorderLayout.NORTH);
        northPanel.setBackground(Color.WHITE);

        centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        this.add(centerPanel, BorderLayout.CENTER);

        southPanel = new JPanel();
        this.add(southPanel, BorderLayout.SOUTH);
        southPanel.setBackground(Color.WHITE);
        southPanel.setPreferredSize(new Dimension(735, 130));
        southPanel.setLayout(new BorderLayout()); //

        northPanel.setLayout(new GridLayout(1, 4));
        label = new JLabel("Select Product Category");
        northPanel.add(label, 0);

        comboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});
        northPanel.add(comboBox, 1);

        extraPanel1 = new JPanel();
        extraPanel1.setBackground(Color.WHITE);
        northPanel.add(extraPanel1, 2);

        extraPanel2 = new JPanel();
        extraPanel2.setBackground(Color.WHITE);
        northPanel.add(extraPanel2, 3);

        shoppingCartButton = new JButton("Shopping Cart");
        northPanel.add(shoppingCartButton, 4);
        shoppingCartButton.setPreferredSize(new Dimension(80, 50));
        shoppingCartButton.setForeground(new Color(115, 92, 156));
        shoppingCartButton.setBorder(new LineBorder(new Color(115, 92, 156), 2));

        tableModel = new tableModel(WestminsterShoppingManager.consoleProductList);
        table = new JTable(tableModel);
        pane = new JScrollPane(table);
        centerPanel.add(pane);
        pane.setPreferredSize(new Dimension(735, 280));

        addCartButton = new JButton("Add to Cart");
        southPanel.add(addCartButton, BorderLayout.EAST);
        addCartButton.setPreferredSize(new Dimension(120, 30));
        addCartButton.setForeground(new Color(115, 92, 156));
        addCartButton.setBorder(new LineBorder(new Color(115, 92, 156), 2));

        area = new JTextArea(5, 10);
        southPanel.add(area, BorderLayout.WEST);
        area.setPreferredSize(new Dimension(300, 100));
        area.setBackground(new Color(115, 92, 156));

        rowColor(WestminsterShoppingManager.consoleProductList);
        //System.out.println(ShoppingCart.shoppingCartArray);       //not necessary-make this correct

        ItemListener handler = new ItemListener();     //add by c   //cannot create a object as it's interface
        table.addMouseListener(new DemoEventHandleClass());          //ask it
        addCartButton.addActionListener(new DemoEventHandleClass());
        shoppingCartButton.addActionListener(new DemoEventHandleClass());
        comboBox.addItemListener(new ItemListener());
    }

    private void rowColor(ArrayList<Product> consoleProductList) {
        for (int i = 0; i < consoleProductList.size(); i++) {// for (Product product : productList) {
            int row = i + 1;
            Product product = consoleProductList.get(i);
            if (product.getNoItems() < 3) {
                table.getColumnModel().getColumn(0).setCellRenderer(new CustomRenderer());
                table.getColumnModel().getColumn(1).setCellRenderer((TableCellRenderer) new CustomRenderer());
                table.getColumnModel().getColumn(2).setCellRenderer(new CustomRenderer());
                table.getColumnModel().getColumn(3).setCellRenderer((TableCellRenderer) new CustomRenderer());
                table.getColumnModel().getColumn(4).setCellRenderer((TableCellRenderer) new CustomRenderer());
            }
        }
    }


    private class DemoEventHandleClass extends MouseAdapter implements ActionListener {

        int selectedRow;
        int selectItem;
//        ArrayList<Product> electronicsArrayList;   //may be need
//        ArrayList<Product> clothingArrayList;


        @Override
        public void mouseClicked(MouseEvent e) {
            selectedRow = table.getSelectedRow();
            if (selectItem==0) {
                area.setText(WestminsterShoppingManager.consoleProductList.get(selectedRow).displayProductInfo());
            }
            if (selectItem==1){
                area.setText(WestminsterShoppingManager.electronicsHoldArrayList.get(selectedRow).displayProductInfo());
                //System.out.println(WestminsterShoppingManager.electronicsHoldArrayList);
            }
            if (selectItem==2){
                area.setText(WestminsterShoppingManager.clothingHoldArrayList.get(selectedRow).displayProductInfo());
                //System.out.println(WestminsterShoppingManager.clothingHoldArrayList);
            }

        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == shoppingCartButton) {
                ShoppingCartGUI cartFrame = new ShoppingCartGUI();
                cartFrame.setTitle("Shopping Cart");
                cartFrame.setVisible(true);
                cartFrame.setSize(450, 360);

            }
            if (e.getSource() == addCartButton) {
                selectedRow = table.getSelectedRow();
                System.out.println(selectedRow);
                Product selectedProduct = WestminsterShoppingManager.consoleProductList.get(selectedRow);
                ShoppingCart cartObject = new ShoppingCart();
                cartObject.addProduct(selectedProduct);

//                if (selectedRow >= 0) {    //not necessary
//                    Product selectedProduct = WestminsterShoppingManager.consoleProductList.get(selectedRow);
//
//                    ShoppingCart cartObject = new ShoppingCart();
//                    cartObject.addProduct(selectedProduct);

//                    if (selectedRow >= 0) {    //not necessary
//                        Product selectedProduct = WestminsterShoppingManager.consoleProductList.get(selectedRow);
//                        ShoppingCart cartObject = new ShoppingCart();
//
//
//                        for (int i=0;i<ShoppingCart.shoppingCartArray.size();i++){
//                            if (selectedProduct.equals(ShoppingCart.shoppingCartArray.get(i))){
//
//                            }
//                        }
//                        //cartObject.productCount++;           increment part
//                        //System.out.println(productCount);
//                    }


                    //cartObject.productCount++;           increment part
                    //System.out.println(productCount);
                }

//                if  (selectItem==1){
//                    selectedRow = table.getSelectedRow();
//                    System.out.println(selectedRow);
//                    if (selectedRow >= 0) {    //not necessary
//                        Product selectedProduct = WestminsterShoppingManager.electronicsHoldArrayList.get(selectedRow);
//
//                        ShoppingCart cartObject = new ShoppingCart();
//                        cartObject.addProduct(selectedProduct);
//                        //cartObject.productCount++;           increment part
//                        //System.out.println(productCount);
//                    }
//                }
            }

        }


     class ItemListener implements java.awt.event.ItemListener {   //import the  class not here

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                int number = comboBox.getSelectedIndex();
                System.out.println(number);
                area.setText(" ");

                switch (number) {
                    case 0:
                        TableModel allModel = new tableModel(WestminsterShoppingManager.consoleProductList);
                        table.setModel(allModel);
                        //table.setModel(new tableModel(WestminsterShoppingManager.consoleProductList));
                        break;
                    case 1:
                        area.setText("");
                        ArrayList<Product> electronicsArrayList = new ArrayList<>();
                        for (Product product : WestminsterShoppingManager.consoleProductList) {
                            if (product instanceof Electronics) {
                                electronicsArrayList.add(product);
                                WestminsterShoppingManager.electronicsHoldArrayList.add(product);
                            }
                        }
                        tableModel elecModel = new tableModel(electronicsArrayList);
                        table.setModel(elecModel);

//                        ArrayList<Product> electronicsArrayList = new ArrayList<>();
//                        for (Product product : WestminsterShoppingManager.consoleProductList) {
//                            if (product instanceof Electronics) {
//                                electronicsArrayList.add((Electronics) product);
//                            }
//                        }
//                        System.out.println(electronicsArrayList);
//                        table.setModel(new tableModel(electronicsArrayList));
                        break;
                    case 2:

                        area.setText("");
                        ArrayList<Product> clothingArrayList = new ArrayList<>();
                        for (Product product : WestminsterShoppingManager.consoleProductList) {
                            if (product instanceof Clothing) {
                                clothingArrayList.add(product);
                                WestminsterShoppingManager.clothingHoldArrayList.add(product);
                            }
                        }
                        tableModel clothModel = new tableModel(WestminsterShoppingManager.clothingHoldArrayList);
                        table.setModel(clothModel);

//                        ArrayList<Product> clothingArrayList = new ArrayList<>();
//                        for (Product product : WestminsterShoppingManager.consoleProductList) {
//                            if (product instanceof Clothing) {
//                                clothingArrayList.add((Clothing) product);
//                            }
//                        }
//                        System.out.println(clothingArrayList);
//                        table.setModel(new tableModel(clothingArrayList));
                        break;
                }
            }
        }
    }


    class CustomRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component rendererComp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                    column);

            // Check if the product's noItems is less than 3
            if (WestminsterShoppingManager.consoleProductList.get(row).getNoItems() < 3) {


                rendererComp.setBackground(Color.RED);

            } else {
                // Reset the background color for other rows
                rendererComp.setBackground(table.getBackground());
                rendererComp.setForeground(Color.BLACK);
            }
            return rendererComp;
        }
    }


    class tableModel extends AbstractTableModel {

    private String[] columnNames = { "Product ID", "Name", "Category", "Price", "Info" };
    private ArrayList<Product> tableList = new ArrayList<>();


    tableModel(ArrayList<Product> tableList) {    //assign data from arraylist
        this.tableList = tableList;
    }


    @Override
    public int getRowCount() {
        return tableList.size();
    }     //get size of the product list

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;

        if (columnIndex == 0) {
            temp = tableList.get(rowIndex).getProductId();    //what is this???
        } else if (columnIndex == 1) {
            temp = tableList.get(rowIndex).getProductName();
        } else if (columnIndex == 2) {
            if (tableList.get(rowIndex) instanceof Electronics) {
                temp = "Electronics";
            } else {
                temp = "Clothing";
            }
        } else if (columnIndex == 3) {
            temp = tableList.get(rowIndex).getPrice();
        }

        else if (columnIndex == 4) {
            if (tableList.get(rowIndex) instanceof Electronics) {
                Electronics electronics = (Electronics) tableList.get(rowIndex);
                temp = electronics.getWarrantyPeriod() + " " + electronics.getBrand();
            } else if (tableList.get(rowIndex) instanceof Clothing) {
                Clothing clothing=(Clothing) tableList.get(rowIndex);
                temp = clothing.getClothColor() + " " + clothing.getClothSize();
            }
        }
        return temp;

    }

    // Needed to show column names in JTable
    public String getColumnName(int col) {
        return columnNames[col];
    } }}



