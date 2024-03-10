//new
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public class WestminsterGUI extends JFrame {
    int dropDownSelectItem;

    public ArrayList<Product> selectedProductsArray = new ArrayList<>();

    JButton shoppingCartButton, addCartButton;
    static JTextArea area;
    JPanel northPanel, centerPanel, southPanel, extraPanel1, extraPanel2,extraPanel3,extraPanel4,extraPanel5,extraPanel6,
            extraPanel7,extraPanel11;
    JLabel label;
    static JTable table;
    JScrollPane pane;
    JComboBox<String> comboBox;
    TableModel tableModel;
    static Product selectedProduct;


    public WestminsterGUI() {

        Font myFont = new Font("Arial ", Font.PLAIN, 14);


        northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(735, 70));       //set size
        this.add(northPanel, BorderLayout.NORTH);
        northPanel.setBackground(new Color(229, 229, 229, 255));

        centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(735, 230));
        centerPanel.setBackground(new Color(229, 229, 229, 255));    //set color using rgb concept
        this.add(centerPanel, BorderLayout.CENTER);

        southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(735, 150));
        southPanel.setLayout(new GridLayout(1,2));                   //use gridlayout
        southPanel.setBackground(new Color(229, 229, 229, 255));
        this.add(southPanel, BorderLayout.SOUTH);

        northPanel.setLayout(new GridLayout(1, 2));

        extraPanel1 = new JPanel();
        northPanel.add(extraPanel1,0);
        extraPanel1.setBackground(new Color(229, 229, 229, 255));
        extraPanel1.setLayout(new FlowLayout());

        extraPanel2 = new JPanel();
        northPanel.add(extraPanel2,1);
        extraPanel2.setLayout(new GridLayout(1,2));
        extraPanel2.setBackground(new Color(229, 229, 229, 255));


        extraPanel3 = new JPanel();
        extraPanel2.add(extraPanel3,0);
        extraPanel3.setBackground(new Color(229, 229, 229, 255));
        extraPanel3.setLayout(new FlowLayout());

        extraPanel11 = new JPanel();
        extraPanel2.add(extraPanel11,1);
        extraPanel3.setBackground(new Color(229, 229, 229, 255));
        extraPanel11.setBackground(new Color(229, 229, 229, 255));
        extraPanel11.setLayout(new FlowLayout());

        label = new JLabel("Select Product Category");
        extraPanel1.add(label);
        label.setForeground(new Color(115, 92, 156));
        label.setFont(myFont);

        comboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});
        extraPanel1.add(comboBox);
        comboBox.setForeground(Color.BLACK);
        comboBox.setFont(myFont);

        shoppingCartButton = new JButton("Shopping Cart");
        shoppingCartButton.setForeground(new Color(115, 92, 156));
        shoppingCartButton.setBorder(new LineBorder(new Color(115, 92, 156), 2));   //add border
        shoppingCartButton.setPreferredSize(new Dimension(150, 50));
        shoppingCartButton.setFont(myFont);
        shoppingCartButton.setBackground(new Color(229, 229, 229, 255));
        extraPanel11.add(shoppingCartButton);

        tableModel = new tableModel(WestminsterShoppingManager.consoleProductList);    //assign this arraylist to tablemodel
        table = new JTable(tableModel);
        pane = new JScrollPane(table);
        centerPanel.add(pane);
        pane.setPreferredSize(new Dimension(735, 280));
        //table.setBorder(new LineBorder(Color.DARK_GRAY, 1));
        table.getTableHeader().setFont(myFont);                               //font change in table header
        table.setBackground(new Color(229, 201, 253, 255));



        extraPanel4 = new JPanel();
        southPanel.add(extraPanel4,0);
        extraPanel4.setBackground(new Color(229, 229, 229, 255));

        extraPanel5=new JPanel();
        extraPanel5.setLayout(new GridLayout(1,2));
        southPanel.add(extraPanel5);
        extraPanel5.setBackground(new Color(229, 229, 229, 255));

        extraPanel6=new JPanel();
        extraPanel6.setBackground(new Color(229, 229, 229, 255));
        extraPanel5.add(extraPanel6,0);

        extraPanel7=new JPanel();
        extraPanel7.setBackground(new Color(229, 229, 229, 255));
        extraPanel7.setLayout(new FlowLayout());

        extraPanel5.add(extraPanel7,1);

        area = new JTextArea(8, 24);
        area.setFont(myFont);
        area.setForeground(new Color(255, 255, 255));
        extraPanel4.add(area);
        area.setBackground(new Color(115, 92, 156));

        addCartButton = new JButton("Add to Cart");
        addCartButton.setForeground(new Color(115, 92, 156));
        addCartButton.setBorder(new LineBorder(new Color(115, 92, 156), 2));
        addCartButton.setPreferredSize(new Dimension(150, 50));
        addCartButton.setFont(myFont);
        addCartButton.setBackground(new Color(229, 229, 229, 255));
        extraPanel7.add(addCartButton);

        rowColor(WestminsterShoppingManager.consoleProductList);

        table.addMouseListener(new DemoEventHandleClass());
        addCartButton.addActionListener(new DemoEventHandleClass());
        shoppingCartButton.addActionListener(new DemoEventHandleClass());
        comboBox.addItemListener(new ItemListener());
    }

    public void rowColor(ArrayList<Product> consoleProductList) {
        for (int i = 0; i < consoleProductList.size(); i++) {     // for (Product product : productList) {
            int row = i + 1;
            Product product = consoleProductList.get(i);
            if (product.getNoItems() < 3) {                                                              //if
                table.getColumnModel().getColumn(0).setCellRenderer(new CustomRenderer());    //making red a cell
                table.getColumnModel().getColumn(1).setCellRenderer(new CustomRenderer());   //https://stackoverflow.com/questions/64276674/java-swing-cell-renderer-based-on-column-class
                table.getColumnModel().getColumn(2).setCellRenderer(new CustomRenderer());
                table.getColumnModel().getColumn(3).setCellRenderer(new CustomRenderer());
                table.getColumnModel().getColumn(4).setCellRenderer(new CustomRenderer());
            }
        }
    }

    private class DemoEventHandleClass extends MouseAdapter implements ActionListener {
        int selectedRow;
        @Override
        public void mouseClicked(MouseEvent e) {
            selectedRow = table.getSelectedRow();
            if(dropDownSelectItem!=-1){
                if (dropDownSelectItem==0) {            //get the number which got when clicked the combo box selections
                    area.setText(WestminsterShoppingManager.consoleProductList.get(selectedRow).displayProductInfo());
                }
                if (dropDownSelectItem==1){          //get row number which mouse pointer clicked
                    area.setText(WestminsterShoppingManager.electronicsHoldArrayList.get(selectedRow).displayProductInfo());

                }
                if (dropDownSelectItem==2){
                    area.setText(WestminsterShoppingManager.clothingHoldArrayList.get(selectedRow).displayProductInfo());

                }
            }
            else {
                System.out.println("Error");
            }

        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == shoppingCartButton) {     //when shopping cart button clicked
                ShoppingCartGUI cartFrame = new ShoppingCartGUI();
                cartFrame.setTitle("Shopping Cart");
                cartFrame.setVisible(true);
                cartFrame.setSize(750, 500);
                cartFrame.setLocationRelativeTo(null);
                dispose();     //close the frame
            }

            if (e.getSource() == addCartButton) {
                if (dropDownSelectItem==0){
                    selectedRow = table.getSelectedRow();
                    if (WestminsterShoppingManager.consoleProductList.get(selectedRow).getNoItems() > 0) {   //check the products is more than 0
                        Product selectedProduct = WestminsterShoppingManager.consoleProductList.get(selectedRow);
                        ShoppingCart cartObject = new ShoppingCart(selectedProduct);
                        cartObject.addProduct(selectedProduct);
                        int count=selectedProduct.getNoItems();
                        count--;
                        selectedProduct.setNoItems(count);
                        area.setText(WestminsterShoppingManager.consoleProductList.
                                get(selectedRow).displayProductInfo());
                    } else {                                                                            //if products=0, message will appear
                        JOptionPane.showMessageDialog(null, "Product availability is not sufficient");
                    }
                }

                if (dropDownSelectItem==1){
                    selectedRow = table.getSelectedRow();      //write above function to all combo box tablemodels-electronic category
                    if (WestminsterShoppingManager.consoleProductList.get(selectedRow).getNoItems() > 0) {
                        Product selectedProduct = WestminsterShoppingManager.electronicsHoldArrayList.get(selectedRow);
                        ShoppingCart cartObject = new ShoppingCart(selectedProduct);
                        cartObject.addProduct(selectedProduct);
                        int count=selectedProduct.getNoItems();    //make this
                        count--;
                        selectedProduct.setNoItems(count);
                        area.setText(WestminsterShoppingManager.electronicsHoldArrayList.
                                get(selectedRow).displayProductInfo());
                    } else {
                        JOptionPane.showMessageDialog(null, "Product availability is not sufficient");
                    }
                }

                if (dropDownSelectItem==2){
                    selectedRow = table.getSelectedRow();
                    if (WestminsterShoppingManager.consoleProductList.get(selectedRow).getNoItems() > 0) {
                        Product selectedProduct = WestminsterShoppingManager.clothingHoldArrayList.get(selectedRow);
                        ShoppingCart cartObject = new ShoppingCart(selectedProduct);
                        cartObject.addProduct(selectedProduct);
                        int count=selectedProduct.getNoItems();    //make this
                        count--;
                        selectedProduct.setNoItems(count);
                        area.setText(WestminsterShoppingManager.clothingHoldArrayList.
                                get(selectedRow).displayProductInfo());
                    } else {
                        JOptionPane.showMessageDialog(null, "Product availability is not sufficient");

                    }
                }
            }
        }
    }

    class ItemListener implements java.awt.event.ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                dropDownSelectItem = comboBox.getSelectedIndex();
                //System.out.println(dropDownSelectItem);
                area.setText(" ");

                switch (dropDownSelectItem) {
                    case 0:
                        TableModel allModel = new tableModel(WestminsterShoppingManager.consoleProductList);
                        table.setModel(allModel);
                        rowColor(WestminsterShoppingManager.consoleProductList);
                        break;

                    case 1:
                        area.setText("");   //when dropdown clicked area will clear
                        ArrayList<Product> electronicsArrayList = new ArrayList<>();
                        for (Product product : WestminsterShoppingManager.consoleProductList) {
                            if (product instanceof Electronics) {    //create an arraylist and assign
                                electronicsArrayList.add(product);
                                WestminsterShoppingManager.electronicsHoldArrayList.add(product);
                            }
                        }
                        tableModel elecModel = new tableModel(electronicsArrayList);   //create a table model to refresh the table when clicked a category
                        table.setModel(elecModel);
                        rowColor(WestminsterShoppingManager.electronicsHoldArrayList);
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
                        tableModel clothModel = new tableModel(clothingArrayList);
                        table.setModel(clothModel);
                        rowColor(WestminsterShoppingManager.clothingHoldArrayList);
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


            if (WestminsterShoppingManager.consoleProductList.get(row).getNoItems() < 3) {
                rendererComp.setBackground(new Color(255, 123, 123));  // Check if the product's noItems is less than 3
            }
//
            else {

                rendererComp.setBackground(table.getBackground());   // Reset the background color for other rows
                rendererComp.setForeground(Color.BLACK);
            }
            return rendererComp;
        }
    }


    static class tableModel extends AbstractTableModel {        //https://stackoverflow.com/questions/9845800/abstracttablemodel-tutorial

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
                temp = tableList.get(rowIndex).getProductId();    //assign the values using getter methods  to relevant row number
            } else if (columnIndex == 1) {
                temp = tableList.get(rowIndex).getProductName();
            } else if (columnIndex == 2) {
                if (tableList.get(rowIndex) instanceof Electronics) {
                    temp = "Electronics";        //check whether its electronic or clothing
                } else {
                    temp = "Clothing";
                }
            } else if (columnIndex == 3) {
                temp = tableList.get(rowIndex).getPrice();
            }

            else if (columnIndex == 4) {
                if (tableList.get(rowIndex) instanceof Electronics) {     //fill the info column
                    Electronics electronics = (Electronics) tableList.get(rowIndex);
                    temp = electronics.getWarrantyPeriod() + "," + electronics.getBrand();
                } else if (tableList.get(rowIndex) instanceof Clothing) {
                    Clothing clothing=(Clothing) tableList.get(rowIndex);
                    temp = clothing.getClothColor() + "," + clothing.getClothSize();
                }
            }
            return temp;
        }

        // Needed to show column names in JTable
        public String getColumnName(int col) {
            return columnNames[col];
        } }}



