
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CardLayoutExample {

    private JPanel contentPane;
    private MyPanel panel1;
    private MyPanel2 panel2;
    private MyPanel2 panel3;
    private JComboBox choiceBox;
    private String[] choices = {
        "Panel 1",
        "Panel 2",
        "Panel 3"
    };

    private void displayGUI() {
        JFrame frame = new JFrame("Card Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new CardLayout());

        choiceBox = new JComboBox(choices);

        panel1 = new MyPanel(contentPane, this);
        panel2 = new MyPanel2(contentPane, Color.GREEN.darker().darker(), this);
        panel3 = new MyPanel2(contentPane, Color.DARK_GRAY, this);

        contentPane.add(panel1, "Panel 1");
        contentPane.add(panel2, "Panel 2");
        contentPane.add(panel3, "Panel 3");

        frame.getContentPane().add(choiceBox, BorderLayout.PAGE_START);
        frame.getContentPane().add(contentPane, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public JComboBox getChoiceBox() {
        return choiceBox;
    }

    public static void main(String... args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CardLayoutExample().displayGUI();
            }
        });
    }
}

class MyPanel extends JPanel {

    private JButton jcomp4;
    private JPanel contentPane;
    private JComboBox choiceBox;

    public MyPanel(JPanel panel, CardLayoutExample cle) {
        choiceBox = cle.getChoiceBox();
        contentPane = panel;
        setOpaque(true);
        setBackground(Color.RED.darker().darker());
        //construct components
        jcomp4 = new JButton("Show New Panel");
        jcomp4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String changeToPanel = (String) choiceBox.getSelectedItem();
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, changeToPanel);
            }
        });
        add(jcomp4);
    }

    @Override
    public Dimension getPreferredSize() {
        return (new Dimension(500, 500));
    }
}

class MyPanel2 extends JPanel {

    private JButton jcomp1;
    private JPanel contentPane;
    private Color backgroundColour;
    private JComboBox choiceBox;

    public MyPanel2(JPanel panel, Color c, CardLayoutExample cle) {
        contentPane = panel;
        backgroundColour = c;
        choiceBox = cle.getChoiceBox();

        setOpaque(true);
        setBackground(backgroundColour);

        //construct components
        jcomp1 = new JButton("Show New Panel");
        jcomp1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String changeToPanel = (String) choiceBox.getSelectedItem();
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, changeToPanel);
            }
        });

        add(jcomp1);
    }

    @Override
    public Dimension getPreferredSize() {
        return (new Dimension(500, 500));
    }
}