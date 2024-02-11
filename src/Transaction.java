import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String categoria;
    private double sum;
    private LocalDate data;

    public Transaction(String categoria, double sum, LocalDate data) {
        this.categoria = categoria;
        this.sum = sum;
        this.data = data;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public double getsum() {
        return this.sum;
    }

    public LocalDate getData() {
        return this.data;
    }

    public String getDataString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.data.format(f);
    }

    @Override
    public String toString() {
        return this.categoria + " - " + this.sum + " - " + this.getDataString();
    }

}
