import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    JTextField output = new JTextField();
    JPanel panel = new JPanel();
    JPanel operatorPanel = new JPanel();
    JPanel operandPanel = new JPanel();

    JButton buttonPlus;
    JButton buttonMinus;

    JButton buttonClear;

    private Expression e1;
    private Expression e2;
    private boolean isFirstSet = false;
    private boolean isSecondSet = false;
    private Expression expr;

    public MyFrame(String title) throws HeadlessException {
        super(title);
        this.getContentPane().setLayout(new BorderLayout());
        // panel.setBackground(Color.green);
        this.getContentPane().add(output, BorderLayout.NORTH);
        this.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(1, 2));
        panel.add(operandPanel);
        panel.add(operatorPanel);
        operandPanel.setBackground(Color.cyan);
        operandPanel.setLayout(new GridLayout(4, 3, 5, 5));
        operatorPanel.setLayout(new GridLayout(4, 1, 5, 5));

        Font myFont = new Font(Font.SANS_SERIF, Font.BOLD, 18);
        operatorPanel.setBackground(Color.cyan);
        Listener listener = new Listener();
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton("" + i);
            button.setFont(myFont);
            button.addActionListener(listener);
            operandPanel.add(button);
        }
        JButton button0 = new JButton("0");
        button0.setFont(myFont);
        button0.addActionListener(listener);
        operandPanel.add(button0);
        JButton buttonPoint = new JButton(".");
        buttonPoint.addActionListener(listener);
        buttonPoint.setFont(myFont);
        operandPanel.add(buttonPoint);

        buttonPlus = new JButton("+");
        buttonPlus.setFont(myFont);
        buttonPlus.addActionListener(listener);
        operatorPanel.add(buttonPlus);
        buttonMinus = new JButton("-");
        buttonMinus.setFont(myFont);
        buttonMinus.addActionListener(listener);
        operatorPanel.add(buttonMinus);
        buttonClear = new JButton("Clear");
        buttonClear.setFont(myFont);
        buttonClear.addActionListener(listener);
        operatorPanel.add(buttonClear);

        Font outputFont = new Font(Font.SANS_SERIF, Font.BOLD, 42);
        output.setFont(outputFont);
    }

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if (button == buttonClear) {
                output.setText("");
            } else {
                System.out.println(button.getText());
                String text = output.getText();
                output.setText(text + "" + button.getText());
                if (!isFirstSet) {
                    expr = Integer.parseInt(button.getText());
                    isFirstSet = true;
                } else {
                    switch (operator) {
                        case "+" ->
                            new Addition(temp, new MyInteger(Integer.parseInt(button.getText())));

                        case "-" ->
                            new Subtract(temp, new MyInteger(Integer.parseInt(button.getText())));
                    }

                }
            }
        }
    }

    public static void main(String[] args) {
        MyFrame frame = new MyFrame("Calulator");
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Expression e = new Subtract(new Addition(new MyInteger(8), new MyInteger(5)),
                new Addition(new MyInteger(3), new MyInteger(8)));
        System.out.println(e.evaluate());
    }

}
