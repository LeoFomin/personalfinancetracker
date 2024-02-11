import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class User {

    private File operationFile = new File("operations.txt");
    private File targetFile = new File("target.txt");


    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public ArrayList<Transaction> transactions = new ArrayList<>();
    public Target targetWished;

    public User() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(operationFile));

            String data;
            String[] datas;

            while ((data = reader.readLine()) != null) {
                datas = data.split(",");

                transactions.add(new Transaction(datas[0], Double.parseDouble(datas[1]), LocalDate.parse(datas[2], formatter)));

            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(targetFile));

            String data = reader.readLine();
            String[] datas = data.split(",");

            this.targetWished = new Target(datas[0], Double.parseDouble(datas[1]), Double.parseDouble(datas[2]));


            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveTargetFile() {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(operationFile));

            for (Transaction m : transactions) {
                writer.write(m.getCategoria() + "," + m.getsum() + "," + m.getDataString() + "\n");
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile));

            writer.write(this.targetWished.getName() + "," + this.targetWished.getinitialBalance() + "," + this.targetWished.getfinalBalance());

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void addTransaction(Transaction m) {
        transactions.add(m);
    }

    public ArrayList<Transaction> gettransactions() {
        return this.transactions;
    }

    public void settransactions(ArrayList<Transaction> n) {
        this.transactions = n;
    }
}
