package days.day11;

import java.math.BigInteger;

public class Item implements Cloneable{
    private BigInteger worryLevel;
    public Item(String value) {
        worryLevel = new BigInteger(value);
    }

    public BigInteger getWorryLevel() {
        return worryLevel;
    }

    public void setWorryLevel(BigInteger worryLevel) {
        this.worryLevel = worryLevel;
    }

    @Override
    public Item clone() {
        try {
            return (Item) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
