package dz_4.Models;

public class Carrier {

    private int id;
    private long cardNumber;

    public Carrier(int id, long cardNumber) {
        this.id = id;
        this.cardNumber = cardNumber;
    }

    public int getId() {
        return id;
    }

    public long getCardNumber(){
        return this.cardNumber;
    }
}