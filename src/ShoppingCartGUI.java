import javax.swing.*;
import java.awt.*;

public class ShoppingCartGUI extends JFrame {

//    public ArrayList<Product> shoppingCartArray = new ArrayList<Product>();

    JPanel panel1, panel2;
    JTextArea discountArea;
    JTable table1;
    JScrollPane pane;

    ShoppingCartGUI() {
        setLayout(new BorderLayout());
        panel1 = new JPanel();
        TableModel1 model1 = new TableModel1(ShoppingCart.shoppingCartArray);

        table1 = new JTable(model1);
        pane = new JScrollPane(table1);
        panel1.add(pane);
        pane.setPreferredSize(new Dimension(400, 180));
        panel1.setBackground(new Color(115, 92, 156));
        panel1.setLayout(new FlowLayout());

        discountArea = new JTextArea();
        panel2 = new JPanel();
        panel2.setBackground(new Color(115, 92, 156));
        discountArea.setPreferredSize(new Dimension(300,150));

        JLabel total = new JLabel("Total");
        JLabel discount10 = new JLabel("First Purchase Discount(10%)");   //where are these??
        JLabel  discount20 = new JLabel("Three items in save category discount");
        JLabel finalTotal = new JLabel("Final Total");

        panel2.add(total);
        panel2.add(discount10);
        panel2.add(discount20);
        panel2.add(finalTotal);

        panel2.add(discountArea);

        add(panel1,BorderLayout.NORTH);
        add(panel2,BorderLayout.SOUTH);

    }

}
