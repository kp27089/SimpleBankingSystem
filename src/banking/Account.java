package banking;

public class Account {
    String pin;
    String cardNumber;
    int balance;

    public Account( String cardNumber, String pin) {

        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = 0;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
