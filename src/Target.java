public class Target {
    private double initialBalance = 0;
    private double finalBalance = 0;
    private String userName;

    public Target(String userName, double initialBalance, double finalBalance) {
        this.userName = userName;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
    }

    public String getName() {
        return this.userName;
    }

    public double getfinalBalance() {
        return this.finalBalance;
    }

    public double getinitialBalance() {
        return this.initialBalance;
    }

    public double getSaldoCorrente() {
        return finalBalance - initialBalance;
    }

    public double getPercent() {
        return (initialBalance / finalBalance) * 100;
    }

    public void setName(String newName) {
        this.userName = newName;
    }

    public void setinitialBalance(double n) {
        this.initialBalance = n;
    }

    public void setfinalBalance(double n) {
        this.finalBalance = n;
    }

    public void add(double n) {
        this.initialBalance += n;
    }

    public boolean check() {
        if (initialBalance >= finalBalance) return true;

        return false;
    }

}
