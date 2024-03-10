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
        total.setForeground(new Color(255, 255, 255));

        double firstDiscount = cart.firstPurchaseDiscount();  //access for first purchase method
        JLabel discount10 = new JLabel("" + firstDiscount);
        discount10.setForeground(new Color(255, 255, 255));

        double categoryDiscount = cart.discountCategory();       //access for 20 percent method
        JLabel discount20 = new JLabel("" + categoryDiscount);
        discount20.setForeground(new Color(255, 255, 255));

        double cartFinalTotal = cart.calculateFinalTotal();     //access for final total method
        JLabel finalTotal = new JLabel("" + cartFinalTotal);
        finalTotal.setForeground(new Color(255, 255, 255));

        extraPanel9 = new JPanel();
        extraPanel4.add(extraPanel9,0);
        extraPanel9.setBackground(new Color(115, 92, 156));
        extraPanel9.add(total);

        extraPanel9.setLayout(new FlowLayout());
        extraPanel9.setFont(myFont);

        extraPanel10 = new JPanel();
        extraPanel4.add(extraPanel10,1);
        extraPanel10.setBackground(new Color(115, 92, 156));
        extraPanel10.add(discount10);

        extraPanel11 = new JPanel();
        extraPanel4.add(extraPanel11,2);
        extraPanel11.setBackground(new Color(115, 92, 156));
        extraPanel11.add(discount20);

        extraPanel12 = new JPanel();
        extraPanel4.add(extraPanel12,3);
        extraPanel12.setBackground(new Color(115, 92, 156));
        extraPanel12.add(finalTotal);

        extraPanel5 = new JPanel();
        extraPanel3.add(extraPanel5,0);
        extraPanel5.setBackground(new Color(115, 92, 156));
        extraPanel5.add(totalLabel);

        extraPanel6 = new JPanel();
        extraPanel3.add(extraPanel6,1);
        extraPanel6.setBackground(new Color(115, 92, 156));
        extraPanel6.add(discount10Label);

        extraPanel7 = new JPanel();
        extraPanel3.add(extraPanel7,2);
        extraPanel7.setBackground(new Color(115, 92, 156));
        extraPanel7.add(discount20Label);

        extraPanel8 = new JPanel();
        extraPanel3.add(extraPanel8,3);
        extraPanel8.setBackground(new Color(115, 92, 156));
        extraPanel8.add(finalTotalLabel);

        TableModel1 model1 = new TableModel1(ShoppingCart.shoppingCartArray);
        table1 = new JTable(model1);
        pane = new JScrollPane(table1);
        centerPanel.add(pane);
        pane.setPreferredSize(new Dimension(500, 210));

        BackToShoppingButton.addActionListener(new EventListenerClass());   //adding action and those action should work when button clicked
        PlaceOrderButton.addActionListener(new EventListenerClass());

    }


    private class EventListenerClass implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == BackToShoppingButton) {       //when bach to shopping button clicked the westminster gui created
                WestminsterGUI frame = new WestminsterGUI();
                frame.setTitle("Westminster Shopping Centre");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //application will exit
                frame.setVisible(true);
                frame.setSize(750, 500); // resize this
                frame.setLocationRelativeTo(null);
                dispose();
            }

            if (e.getSource() == PlaceOrderButton) {
                if (ShoppingCart.shoppingCartArray.size() > 0) {
                    if (LoginGUI.enteredUsername!=null){
                        for (User user : WestminsterShoppingManager.userList) { //if user history=0, make it 1
                            if (LoginGUI.enteredUsername.equals(user.getUserName()) && user.getHistory() == 0) {
                                user.setHistory(1);
                            }
                        }
                    }

                    try {
                        WestminsterShoppingManager.saveProductsToFile();  //save updates no of items in to the file
                        WestminsterShoppingManager.saveUser();         //save user details specially the history
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(null, "Order successfully!");
                    ShoppingCart.shoppingCartArray = new ArrayList<>();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Cart is empty!");  //if cart doesn't have products and try to place an order this message will pop up
                }
            }

        }
    }
}


//JOptionPane.showMessageDialog(null, "Order successfully!");

