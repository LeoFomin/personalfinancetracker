import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddTransaction extends JFrame implements ActionListener, MouseListener {
    User user;
    //Labels
    private JLabel titleLabel = new JLabel("Добавить операцию:");
    private JLabel sumLabel = new JLabel("Сумма: ");
    private JLabel categoriaLabel = new JLabel("Категория: ");
    private JLabel dataLabel = new JLabel("Дата: ");

    //Buttons e field
    private JTextField sumFiled = new JTextField();
    private JTextField categoryField = new JTextField();
    private JTextField dataField = new JTextField();
    private JButton backButton = new JButton();
    private JButton confirmButton = new JButton();
    private JButton todayButton = new JButton();
    private LocalDate dateToChoose;


    public AddTransaction(User user) {
        this.user = user;

        this.setLayout(null);
        this.setSize(1100, 725);
        this.setTitle("Операции");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Style.mediumGreen);
        this.setVisible(true);

        titleLabel.setBounds(304, 108, 489, 38);
        titleLabel.setFont(Style.textFont);
        titleLabel.setForeground(Style.darkestGreen);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        sumLabel.setBounds(304, 213, 189, 36);
        sumLabel.setFont(Style.textFont);
        sumLabel.setForeground(Style.darkestGreen);
        sumLabel.setHorizontalAlignment(JLabel.RIGHT);

        categoriaLabel.setBounds(260, 267, 233, 36);
        categoriaLabel.setFont(Style.textFont);
        categoriaLabel.setForeground(Style.darkestGreen);
        categoriaLabel.setHorizontalAlignment(JLabel.RIGHT);

        dataLabel.setBounds(304, 327, 189, 36);
        dataLabel.setFont(Style.textFont);
        dataLabel.setForeground(Style.darkestGreen);
        dataLabel.setHorizontalAlignment(JLabel.RIGHT);

        sumFiled.setBounds(506, 209, 333, 45);
        sumFiled.setBackground(Style.darkGreen);
        sumFiled.setBorder(null);
        sumFiled.setFont(Style.textFont);
        sumFiled.setForeground(Color.WHITE);
        sumFiled.setCaretColor(Color.WHITE);

        categoryField.setBounds(506, 262, 333, 45);
        categoryField.setBackground(Style.darkGreen);
        categoryField.setBorder(null);
        categoryField.setFont(Style.textFont);
        categoryField.setForeground(Color.WHITE);
        categoryField.setCaretColor(Color.WHITE);


        backButton.setBounds(496, 460, 104, 45);
        backButton.setText("Отменить");
        backButton.setFocusable(false);
        backButton.setOpaque(true);
        backButton.setBorder(null);
        backButton.setFont(Style.buttonsFont);
        backButton.setForeground(Style.darkGreen);
        backButton.setBackground(Style.lightGreen);
        backButton.addMouseListener(this);
        backButton.addActionListener(this);

        confirmButton.setBounds(506, 518, 85, 45);
        confirmButton.setText("Сохранить");
        confirmButton.setFocusable(false);
        confirmButton.setOpaque(true);
        confirmButton.setBorder(null);
        confirmButton.setFont(Style.buttonsFont);
        confirmButton.setForeground(Style.darkGreen);
        confirmButton.setBackground(Style.lightGreen);
        confirmButton.addMouseListener(this);
        confirmButton.addActionListener(this);

        todayButton.setBounds(687, 317, 120, 45);
        todayButton.setText("Сегодня");
        todayButton.setFocusable(false);
        todayButton.setOpaque(true);
        todayButton.setBorder(null);
        todayButton.setFont(Style.buttonsFont);
        todayButton.setForeground(Style.lightGreen);
        todayButton.setBackground(Style.darkGreen);
        todayButton.addMouseListener(this);
        todayButton.addActionListener(this);

        dataField.setBounds(507, 317, 167, 45);
        dataField.setBackground(Style.darkGreen);
        dataField.setBorder(null);
        dataField.setForeground(Color.WHITE);
        dataField.setCaretColor(Color.WHITE);


        this.add(dataField);
        this.add(todayButton);
        this.add(confirmButton);
        this.add(backButton);
        this.add(categoryField);
        this.add(sumFiled);
        this.add(dataLabel);
        this.add(categoriaLabel);
        this.add(sumLabel);
        this.add(titleLabel);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == todayButton) {
            this.dateToChoose = LocalDate.now();

            String temp = dateToChoose.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            dataField.setText(temp);

        }

        if (e.getSource() == confirmButton) {
            dispose();
            double sum = Double.parseDouble(sumFiled.getText());
            String cat = categoryField.getText();
            dateToChoose = LocalDate.parse(dataField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            this.user.transactions.add(new Transaction(cat, sum, dateToChoose));
            this.user.saveTargetFile();

            SwingUtilities.invokeLater(() -> {
                DashBoard d = new DashBoard(this.user);
                d.setVisible(true);
            });

            try {
                Thread.sleep(100);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }


        if (e.getSource() == backButton) {
            dispose();
            SwingUtilities.invokeLater(() -> {
                DashBoard d = new DashBoard(this.user);
                d.setVisible(true);
            });

            try {
                Thread.sleep(100);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == backButton) {
            backButton.setBackground(Style.darkGreen);
            backButton.setForeground(Style.lightGreen);
        }

        if (e.getSource() == confirmButton) {
            confirmButton.setBackground(Style.darkGreen);
            confirmButton.setForeground(Style.lightGreen);
        }

        if (e.getSource() == todayButton) {
            todayButton.setBackground(Style.lightGreen);
            todayButton.setForeground(Style.darkGreen);
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == backButton) {
            backButton.setBackground(Style.lightGreen);
            backButton.setForeground(Style.darkestGreen);
        }
        if (e.getSource() == confirmButton) {
            confirmButton.setBackground(Style.lightGreen);
            confirmButton.setForeground(Style.darkestGreen);
        }

        if (e.getSource() == todayButton) {
            todayButton.setBackground(Style.darkGreen);
            todayButton.setForeground(Style.lightGreen);
        }
    }
}
