import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class DashBoard extends JFrame implements MouseListener, ActionListener {
    private User user;
    private String periodo = "Всего";
    private final DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    //Buttons
    private JButton addTransaction = new JButton();
    private JButton refreshButton = new JButton();
    private JButton transactionsButton = new JButton();
    private JButton weekButton = new JButton();
    private JButton monthButton = new JButton();
    private JButton totaleButton = new JButton();
    private JButton modifyTarget = new JButton();
    private JButton addToTarget = new JButton();

    //Labels
    private JLabel mainChartLabel = new JLabel();
    private JLabel chartTitle = new JLabel();
    private JLabel profitLabel = new JLabel();
    private JLabel expenceLabel = new JLabel();
    private JLabel profitBar = new JLabel();
    private JLabel expenceBar = new JLabel();
    private JLabel targetLabel = new JLabel();
    private JLabel downBarLabel = new JLabel();
    private JLabel upBarLabel = new JLabel();
    private JLabel categoryLabel = new JLabel();
    private JLabel lastLabel = new JLabel("Система контроля финансов");


    public DashBoard(User user) {
        this.user = user;

        this.setLayout(null);
        this.setSize(1100, 725);
        this.setTitle("Дашборд");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Style.mediumGreen);
        this.setVisible(true);

        mainChartLabel.setBounds(70, 111, 491, 476);
        mainChartLabel.setBackground(Style.lightGreen);
        mainChartLabel.setOpaque(true);

        chartTitle.setBounds(70, 113, 491, 32);
        chartTitle.setOpaque(false);
        chartTitle.setHorizontalAlignment(JLabel.CENTER);
        chartTitle.setVerticalAlignment(JLabel.CENTER);
        chartTitle.setFont(Style.textFont);
        chartTitle.setForeground(Style.darkGreen);

        double e = 0, u = 0;
        for (Transaction m : user.gettransactions()) {
            if (m.getsum() >= 0) e += m.getsum();
            else u += m.getsum();
        }
        chartTitle.setText("Доходы: " + decimalFormat.format(e) + "руб - Расходы: " + decimalFormat.format(u) + "руб");

        profitLabel.setBounds(110, 528, 162, 30);
        profitLabel.setOpaque(false);
        profitLabel.setHorizontalAlignment(JLabel.CENTER);
        profitLabel.setVerticalAlignment(JLabel.CENTER);
        profitLabel.setFont(Style.textFont);
        profitLabel.setForeground(Style.darkestGreen);
        profitLabel.setText("Доходы");

        expenceLabel.setBounds(338, 528, 162, 30);
        expenceLabel.setOpaque(false);
        expenceLabel.setHorizontalAlignment(JLabel.CENTER);
        expenceLabel.setVerticalAlignment(JLabel.CENTER);
        expenceLabel.setFont(Style.textFont);
        expenceLabel.setForeground(Style.darkestGreen);
        expenceLabel.setText("Расходы");

        addTransaction.setBounds(70, 38, 404, 66);
        addTransaction.setText("Добавить операцию");
        addTransaction.setFocusable(false);
        addTransaction.setOpaque(true);
        addTransaction.setBorder(null);
        addTransaction.setFont(Style.buttonsFont);
        addTransaction.setForeground(Style.darkGreen);
        addTransaction.setBackground(Style.lightGreen);
        addTransaction.addMouseListener(this);
        addTransaction.addActionListener(this);

        refreshButton.setBounds(491, 38, 70, 66);
        refreshButton.setText("🔄");
        refreshButton.setFocusable(false);
        refreshButton.setOpaque(true);
        refreshButton.setBorder(null);
        refreshButton.setFont(Style.buttonsFont);
        refreshButton.setForeground(Style.darkGreen);
        refreshButton.setBackground(Style.lightGreen);
        refreshButton.addMouseListener(this);
        refreshButton.addActionListener(this);

        profitBar.setVisible(true);
        profitBar.setBackground(Style.darkestGreen);
        profitBar.setOpaque(true);

        expenceBar.setBackground(Style.darkestGreen);
        expenceBar.setOpaque(true);
        expenceBar.setVisible(true);

        double diff = (e) - (u);

        double proporzione = 0.0;
        if (diff != 0) proporzione = 359.0 / diff;

        int altezzaprofitBar = (int) (proporzione * e);
        profitBar.setBounds(110, 528 - altezzaprofitBar, 162, altezzaprofitBar);
        profitBar.setOpaque(true);
        profitBar.setBackground(Style.darkestGreen);

        int altezzaexpenceBar = 359 - altezzaprofitBar;
        expenceBar.setBounds(338, 528 - altezzaexpenceBar, 162, altezzaexpenceBar);
        expenceBar.setOpaque(true);
        expenceBar.setBackground(Style.darkestGreen);


        transactionsButton.setBounds(70, 597, 243, 66);
        transactionsButton.setText("Последние операции");
        transactionsButton.setFocusable(false);
        transactionsButton.setOpaque(true);
        transactionsButton.setBorder(null);
        transactionsButton.setFont(Style.buttonsFont);
        transactionsButton.setForeground(Style.darkGreen);
        transactionsButton.setBackground(Style.lightGreen);
        transactionsButton.addMouseListener(this);
        transactionsButton.addActionListener(this);

        weekButton.setBounds(352, 596, 66, 66);
        weekButton.setText("Неделя");
        weekButton.setFocusable(false);
        weekButton.setOpaque(true);
        weekButton.setBorder(null);
        weekButton.setFont(Style.buttonsFont);
        weekButton.setForeground(Style.darkGreen);
        weekButton.setBackground(Style.lightGreen);
        weekButton.addMouseListener(this);
        weekButton.addActionListener(this);

        monthButton.setBounds(424, 596, 66, 66);
        monthButton.setText("Месяц");
        monthButton.setFocusable(false);
        monthButton.setOpaque(true);
        monthButton.setBorder(null);
        monthButton.setFont(Style.buttonsFont);
        monthButton.setForeground(Style.darkGreen);
        monthButton.setBackground(Style.lightGreen);
        monthButton.addMouseListener(this);
        monthButton.addActionListener(this);

        totaleButton.setBounds(495, 596, 66, 66);
        totaleButton.setText("Всего");
        totaleButton.setFocusable(false);
        totaleButton.setOpaque(true);
        totaleButton.setBorder(null);
        totaleButton.setFont(Style.buttonsFont);
        totaleButton.setForeground(Style.lightGreen);
        totaleButton.setBackground(Style.darkGreen);
        totaleButton.addMouseListener(this);
        totaleButton.addActionListener(this);

        targetLabel.setBounds(578, 37, 489, 66);
        targetLabel.setOpaque(true);
        targetLabel.setText(this.user.targetWished.getName() + " - " + decimalFormat.format(this.user.targetWished.getPercent()) + "% на: " + decimalFormat.format(this.user.targetWished.getfinalBalance()) + "руб");
        targetLabel.setBackground(Style.lightGreen);
        targetLabel.setHorizontalAlignment(JLabel.CENTER);
        targetLabel.setFont(Style.textFont);
        targetLabel.setForeground(Style.darkGreen);

        downBarLabel.setOpaque(true);
        downBarLabel.setBounds(578, 113, 489, 45);
        downBarLabel.setBackground(Style.lightGreen);

        upBarLabel.setBounds(578, 113, (int) (489 * this.user.targetWished.getPercent()) / 100, 45);
        upBarLabel.setOpaque(true);
        upBarLabel.setBackground(Style.darkestGreen);
        if (upBarLabel.getWidth() > 489) upBarLabel.setBounds(578, 113, 489, 45);

        modifyTarget.setBounds(578, 168, 234, 66);
        modifyTarget.setText("Изменить");
        modifyTarget.setFocusable(false);
        modifyTarget.setOpaque(true);
        modifyTarget.setBorder(null);
        modifyTarget.setFont(Style.buttonsFont);
        modifyTarget.setForeground(Style.darkGreen);
        modifyTarget.setBackground(Style.lightGreen);
        modifyTarget.addMouseListener(this);
        modifyTarget.addActionListener(this);

        addToTarget.setBounds(829, 168, 238, 66);
        addToTarget.setText("Добавить средства");
        addToTarget.setFocusable(false);
        addToTarget.setOpaque(true);
        addToTarget.setBorder(null);
        addToTarget.setFont(Style.buttonsFont);
        addToTarget.setForeground(Style.darkGreen);
        addToTarget.setBackground(Style.lightGreen);
        addToTarget.addMouseListener(this);
        addToTarget.addActionListener(this);

        categoryLabel.setBounds(578, 244, 489, 343);
        categoryLabel.setBackground(Style.lightGreen);
        categoryLabel.setOpaque(true);
        categoryLabel.setHorizontalAlignment(JLabel.CENTER);
        categoryLabel.setHorizontalTextPosition(JLabel.CENTER);
        categoryLabel.setFont(Style.buttonsFont);
        categoryLabel.setForeground(Style.darkGreen);

        ArrayList<String> categorie = new ArrayList<>();

        for (Transaction m : this.user.gettransactions()) {
            boolean flag = false;

            for (int i = 0; i < categorie.size(); i++) {
                if (m.getCategoria().equalsIgnoreCase(categorie.get(i))) {
                    flag = true;
                    break;
                }
            }

            if (!flag) categorie.add(m.getCategoria());
        }

        double[] importi = new double[categorie.size()];
        for (int i = 0; i < categorie.size(); i++) {
            for (Transaction m : this.user.gettransactions())
                if (m.getCategoria().equalsIgnoreCase(categorie.get(i))) importi[i] += m.getsum();
        }

        String textCategory = "<html>РАСХОДЫ ПО КАТЕГОРИЯМ:<br><br>";
        for (int i = 0; i < categorie.size(); i++) {
            textCategory += categorie.get(i) + ": " + decimalFormat.format(importi[i]) + "руб<br>";
        }
        textCategory += "</html>";

        categoryLabel.setText(textCategory);

        lastLabel.setBounds(578, 618, 489, 23);
        lastLabel.setFont(Style.textFont);
        lastLabel.setForeground(Color.BLACK);
        lastLabel.setHorizontalAlignment(JLabel.CENTER);


        this.add(lastLabel);
        this.add(categoryLabel);
        this.add(addToTarget);
        this.add(modifyTarget);
        this.add(upBarLabel);
        this.add(downBarLabel);
        this.add(targetLabel);
        this.add(totaleButton);
        this.add(monthButton);
        this.add(weekButton);
        this.add(transactionsButton);
        this.add(expenceBar);
        this.add(profitBar);
        this.add(refreshButton);
        this.add(addTransaction);
        this.add(expenceLabel);
        this.add(profitLabel);
        this.add(chartTitle);
        this.add(mainChartLabel);
    }

    private void aggiorna() {
        int range = 0;
        if (periodo.equals("Неделя")) range = 7;
        if (periodo.equals("Месяц")) range = 30;

        LocalDate dataCorrente = LocalDate.now();

        double e = 0, u = 0;
        for (Transaction m : user.gettransactions()) {
            LocalDate d = m.getData();

            if (range == 0) {
                if (m.getsum() >= 0) e += m.getsum();
                else u += m.getsum();
            } else {
                long differenzaGiorni = ChronoUnit.DAYS.between(d, dataCorrente);

                if (differenzaGiorni <= range) {
                    if (m.getsum() >= 0) e += m.getsum();
                    else u += m.getsum();
                }
            }
        }

        double diff = (e) - (u);
        double proporzione = 0.0;
        if (diff != 0) proporzione = 359.0 / diff;

        int altezzaprofitBar = (int) (proporzione * e);
        profitBar.setBounds(110, 528 - altezzaprofitBar, 162, altezzaprofitBar);


        int altezzaexpenceBar = 359 - altezzaprofitBar;
        expenceBar.setBounds(338, 528 - altezzaexpenceBar, 162, altezzaexpenceBar);

        chartTitle.setText("Доходы: " + decimalFormat.format(e) + "руб - Расходы: " + decimalFormat.format(u) + "руб");


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Ultimi
        if (e.getSource() == transactionsButton) {
            dispose();
            SwingUtilities.invokeLater(() -> {
                LatestOperations d = new LatestOperations(this.user);
                d.setVisible(true);
            });

            try {
                Thread.sleep(100);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }


        //Refresh
        if (e.getSource() == refreshButton) {
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

        if (e.getSource() == totaleButton && !periodo.equals("TOT")) {
            periodo = "TOT";
            monthButton.setBackground(Style.lightGreen);
            monthButton.setForeground(Style.darkGreen);
            weekButton.setBackground(Style.lightGreen);
            weekButton.setForeground(Style.darkGreen);

            aggiorna();
        }
        if (e.getSource() == monthButton && !periodo.equals("MM")) {
            periodo = "MM";
            totaleButton.setBackground(Style.lightGreen);
            totaleButton.setForeground(Style.darkGreen);
            weekButton.setBackground(Style.lightGreen);
            weekButton.setForeground(Style.darkGreen);

            aggiorna();
        }
        if (e.getSource() == weekButton && !periodo.equals("Неделя")) {
            periodo = "Неделя";
            totaleButton.setBackground(Style.lightGreen);
            totaleButton.setForeground(Style.darkGreen);
            monthButton.setBackground(Style.lightGreen);
            monthButton.setForeground(Style.darkGreen);
            aggiorna();
        }

        if (e.getSource() == addTransaction) {
            dispose();
            new AddTransaction(this.user);
        }

        if (e.getSource() == modifyTarget) {
            String newName = JOptionPane.showInputDialog(this, "Введите новое имя: ", null);

            String newSaldo = JOptionPane.showInputDialog(this, "Сколько хотите съекономить:  ", null);
            try {
                double finalBalance = Double.parseDouble(newSaldo);

                this.user.targetWished.setName(newName);
                this.user.targetWished.setfinalBalance(finalBalance);

                targetLabel.setText(this.user.targetWished.getName() + " - " + decimalFormat.format(this.user.targetWished.getPercent()) + "% на: " + decimalFormat.format(this.user.targetWished.getfinalBalance()) + "руб");

                downBarLabel.setBounds(578, 113, 489, 45);
                upBarLabel.setBounds(578, 113, (int) (489 * this.user.targetWished.getPercent()) / 100, 45);
                if (upBarLabel.getWidth() > 489) upBarLabel.setBounds(578, 113, 489, 45);

                this.user.saveTargetFile();

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, "Ошибка!", "Неправильная вставка", JOptionPane.ERROR_MESSAGE);
            }


        }

        if (e.getSource() == addToTarget) {
            String newSaldo = JOptionPane.showInputDialog(this, "Сколько вы хотите добавить: ", null);

            try {
                double finalBalance = Double.parseDouble(newSaldo);

                this.user.targetWished.add(finalBalance);

                targetLabel.setText(this.user.targetWished.getName() + " - " + decimalFormat.format(this.user.targetWished.getPercent()) + "% на: " + decimalFormat.format(this.user.targetWished.getfinalBalance()) + "руб");

                downBarLabel.setBounds(578, 113, 489, 45);
                upBarLabel.setBounds(578, 113, (int) (489 * this.user.targetWished.getPercent()) / 100, 45);
                if (upBarLabel.getWidth() > 489) upBarLabel.setBounds(578, 113, 489, 45);

                this.user.saveTargetFile();

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, "Ошибка!", "Неправильная вставка", JOptionPane.ERROR_MESSAGE);
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
        if (e.getSource() == addTransaction) {
            addTransaction.setBackground(Style.darkGreen);
            addTransaction.setForeground(Style.lightGreen);
        }

        if (e.getSource() == refreshButton) refreshButton.setBackground(Style.darkGreen);

        if (e.getSource() == transactionsButton) {
            transactionsButton.setBackground(Style.darkGreen);
            transactionsButton.setForeground(Style.lightGreen);
        }

        if (e.getSource() == weekButton && !periodo.equals("Неделя")) {
            weekButton.setBackground(Style.darkGreen);
            weekButton.setForeground(Style.lightGreen);
        }
        if (e.getSource() == monthButton && !periodo.equals("MM")) {
            monthButton.setBackground(Style.darkGreen);
            monthButton.setForeground(Style.lightGreen);
        }

        if (e.getSource() == totaleButton && !periodo.equals("Всего")) {
            totaleButton.setBackground(Style.darkGreen);
            totaleButton.setForeground(Style.lightGreen);
        }

        if (e.getSource() == modifyTarget) {
            modifyTarget.setBackground(Style.darkGreen);
            modifyTarget.setForeground(Style.lightGreen);
        }
        if (e.getSource() == addToTarget) {
            addToTarget.setBackground(Style.darkGreen);
            addToTarget.setForeground(Style.lightGreen);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == addTransaction) {
            addTransaction.setBackground(Style.lightGreen);
            addTransaction.setForeground(Style.darkGreen);
        }

        if (e.getSource() == refreshButton) refreshButton.setBackground(Style.lightGreen);

        if (e.getSource() == transactionsButton) {
            transactionsButton.setBackground(Style.lightGreen);
            transactionsButton.setForeground(Style.darkGreen);
        }

        if (e.getSource() == weekButton && !periodo.equals("День")) {
            weekButton.setBackground(Style.lightGreen);
            weekButton.setForeground(Style.darkGreen);
        }
        if (e.getSource() == monthButton && !periodo.equals("Месяц")) {
            monthButton.setBackground(Style.lightGreen);
            monthButton.setForeground(Style.darkGreen);
        }
        if (e.getSource() == totaleButton && !periodo.equals("Всего")) {
            totaleButton.setBackground(Style.lightGreen);
            totaleButton.setForeground(Style.darkGreen);
        }

        if (e.getSource() == modifyTarget) {
            modifyTarget.setBackground(Style.lightGreen);
            modifyTarget.setForeground(Style.darkGreen);
        }
        if (e.getSource() == addToTarget) {
            addToTarget.setBackground(Style.lightGreen);
            addToTarget.setForeground(Style.darkGreen);
        }
    }
}
