package days.day11;


import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class Monkey {
    private static final BigInteger TEMP = new BigInteger("96".repeat(1000));
    private final static String ITEMS_PREFIX = "Starting items:";

    public BigInteger numberOfInspections = new BigInteger("0");
    public List<Item> items = new LinkedList<>();
    private String name;

    private Predicate<Item> test;
    private String operation;
    private BigInteger operationValue;
    private BigInteger divisor;
    private static BigInteger kgv;

    int monkeyToThrowToIfTrueIndex;
    int monkeyToThrowToIfFalseIndex;

    Monkey monkeyToThrowToIfTrue;
    Monkey monkeyToThrowToIfFalse;

    public static void setKgv(BigInteger kgv) {
        Monkey.kgv = kgv;
    }

    public Monkey(List<String> monkeyString) {
        name = monkeyString.get(0);

        // Parse: Starting items
        String startingItemsString = monkeyString.get(1).strip().replace(ITEMS_PREFIX, "");

        String[] startingItemsArray = startingItemsString.split(",");

        for (String item : startingItemsArray) {
            items.add(new Item(item.strip()));
        }

        // Parse: Operation
        String[] operationStringParts = monkeyString.get(2).strip().split(" ");

        operation = operationStringParts[4].strip();
        if(operationStringParts[5].strip().equals("old")) {
            operationValue = BigInteger.valueOf(-1);
        } else {
            operationValue = new BigInteger(operationStringParts[5].strip());
        }


        List<String> testParts = monkeyString.subList(3, monkeyString.size());

        divisor = new BigInteger(testParts.get(0).strip().split(" ")[3].strip());

        monkeyToThrowToIfTrueIndex = Integer.parseInt(testParts.get(1).strip().split(" ")[5].strip());
        monkeyToThrowToIfFalseIndex = Integer.parseInt(testParts.get(2).strip().split(" ")[5].strip());
    }

    public void setMonkeyToThrowToIfTrue(Monkey monkeyToThrowToIfTrue) {
        this.monkeyToThrowToIfTrue = monkeyToThrowToIfTrue;
    }

    public void setMonkeyToThrowToIfFalse(Monkey monkeyToThrowToIfFalse) {
        this.monkeyToThrowToIfFalse = monkeyToThrowToIfFalse;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item getNew(Item startingItem) {
        Item newItem = startingItem.clone();
        BigInteger temp = operationValue;
        if(operationValue.equals(BigInteger.valueOf(-1))) {
            temp = startingItem.getWorryLevel();
        }
        newItem.setWorryLevel((switch (operation) {
            case "+" -> newItem.getWorryLevel().add(temp);
            case "-" -> newItem.getWorryLevel().subtract(temp);
            case "*" -> newItem.getWorryLevel().multiply(temp);
            case "/" -> newItem.getWorryLevel().divide(temp);
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        }).mod(Monkey.kgv));
        return newItem;
    }

    public Monkey getMonkeyToThrowTo(Item item) {
        if(item.getWorryLevel().mod(divisor).equals(BigInteger.ZERO)) {
            return monkeyToThrowToIfTrue;
        } else {
            return monkeyToThrowToIfFalse;
        }
    }

    public BigInteger getDivisor() {
        return divisor;
    }
}
