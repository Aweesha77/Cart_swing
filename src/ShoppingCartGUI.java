import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class ShoppingCartGUI extends JFrame {
    JPanel northPanel, centerPanel, southPanel, extraPanel1, extraPanel2,extraPanel3,extraPanel4,extraPanel5,extraPanel6,
            extraPanel7,extraPanel8,extraPanel9,extraPanel10,extraPanel11,extraPanel12;
    JTable table1;
    JScrollPane pane;
    JButton PlaceOrderButton, BackToShoppingButton;

    ShoppingCartGUI() {

        Font myFont = new Font("Arial ", Font.PLAIN, 12);

        northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(450, 80));
        this.add(northPanel, BorderLayout.NORTH);
        northPanel.setBackground(new Color(229, 229, 229, 255));
        northPanel.setLayout(new GridLayout(1,2));

        centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(450, 180));
        this.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setBackground(new Color(229, 229, 229, 255));

        southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(450, 140));
        this.add(southPanel, BorderLayout.SOUTH);
        southPanel.setBackground(new Color(229, 229, 229, 255));
        southPanel.setLayout(new GridLayout(1,2));


        extraPanel1 = new JPanel();
        northPanel.add(extraPanel1,0);
        extraPanel1.setBackground(new Color(229, 229, 229, 255));
        extraPanel1.setLayout(new FlowLayout());

        extraPanel2 = new JPanel();
        northPanel.add(extraPanel2,1);
        extraPanel2.setBackground(new Color(229, 229, 229, 255));

        BackToShoppingButton = new JButton("BackTo Shopping");
        extraPanel1.add(BackToShoppingButton);
        BackToShoppingButton.setPreferredSize(new Dimension(150, 40));
        BackToShoppingButton.setForeground(new Color(115, 92, 156));
        BackToShoppingButton.setBorder(new LineBorder(new Color(115, 92, 156), 2));

        PlaceOrderButton = new JButton("Place Order");
        extraPanel2.add(PlaceOrderButton);
        PlaceOrderButton.setPreferredSize(new Dimension(150, 40));
        PlaceOrderButton.setForeground(new Color(115, 92, 156));
        PlaceOrderButton.setBorder(new LineBorder(new Color(115, 92, 156), 2));

        extraPanel3 = new JPanel();
        southPanel.add(extraPanel3,0);
        extraPanel3.setBackground(Color.GREEN);
        extraPanel3.setLayout(new GridLayout(4,1));

        extraPanel4 = new JPanel();
        southPanel.add(extraPanel4,1);
        extraPanel4.setBackground(Color.BLACK);
        extraPanel4.setLayout(new GridLayout(4,1));

        JLabel totalLabel = new JLabel("Total - ");
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setFont(myFont);    //
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel discount10Label = new JLabel("First Purchase Discount(10%) - ");
        discount10Label.setFont(myFont);
        discount10Label.setForeground(Color.WHITE);
        discount10Label.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel discount20Label = new JLabel("Three items in save category discount(20%) - ");
        discount20Label.setFont(myFont);
        discount20Label.setForeground(Color.WHITE);
        discount20Label.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel finalTotalLabel = new JLabel("Final Total - ");
        finalTotalLabel.setFont(myFont);
        finalTotalLabel.setForeground(Color.WHITE);
        finalTotalLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        ShoppingCart cart = new ShoppingCart(WestminsterGUI.selectedProduct);
        double productTotal = cart.calculateTotal();    //access for calculateTotal method
        JLabel total = new JLabel("" + productTotal);
        