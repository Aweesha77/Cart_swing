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
