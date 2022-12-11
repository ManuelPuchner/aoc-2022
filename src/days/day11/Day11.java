package days.day11;

import days.Day;
import util.FileUtils;
import util.ListSeparationUtil;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day11 extends Day {

    private final List<Monkey> monkeys = new ArrayList<>();

    private void parseInput() {
        monkeys.clear();
        //input = FileUtils.readAllLines("input/11/example");

        List<List<String>> separated = ListSeparationUtil.separate(input);

        separated.forEach(monkeyString -> monkeys.add(new Monkey(monkeyString)));



        List<BigInteger> divisors = new ArrayList<>();
        for (Monkey m : monkeys) {
            divisors.add(m.getDivisor());
        }

        BigInteger kgv = kgv(divisors);

        for (Monkey monkey : monkeys) {
            monkey.setMonkeyToThrowToIfFalse(monkeys.get(monkey.monkeyToThrowToIfFalseIndex));
            monkey.setMonkeyToThrowToIfTrue(monkeys.get(monkey.monkeyToThrowToIfTrueIndex));
            Monkey.setKgv(kgv);
        }
    }

    @Override
    public Object solvePart1() {
        parseInput();
        for (int i = 0; i < 20; i++) {
            for (Monkey monkey : monkeys) {
                List<Item> monkeyItems = monkey.items;

                while(monkeyItems.size() != 0) {
                    Item item = monkeyItems.get(0);
                    Item value = monkey.getNew(item).clone();
                    value.setWorryLevel(value.getWorryLevel().divide(new BigInteger("3")));

                    Monkey monkeyToThrowTo = monkey.getMonkeyToThrowTo(value);

                    monkey.removeItem(item);
                    monkeyToThrowTo.addItem(value);
                    monkey.numberOfInspections = monkey.numberOfInspections.add(BigInteger.ONE);
                }
            }


            for (Monkey m : monkeys) {
                System.out.println(m);
            }
            System.out.println();
        }

        monkeys.sort((m1, m2) -> -1*m1.numberOfInspections.compareTo(m2.numberOfInspections));
        return monkeys.get(0).numberOfInspections.multiply(monkeys.get(1).numberOfInspections);
    }


    @Override
    public Object solvePart2() {
        parseInput();
        for (int i = 1; i <= 10000; i++) {
            for (Monkey monkey : monkeys) {
                while(monkey.items.size() != 0) {
                    Item item = monkey.items.get(0);
                    Item value = monkey.getNew(item);

                    Monkey monkeyToThrowTo = monkey.getMonkeyToThrowTo(value);

                    monkey.removeItem(item);
                    monkeyToThrowTo.addItem(value);
                    monkey.numberOfInspections = monkey.numberOfInspections.add(BigInteger.ONE);
                }
            }
        }

        monkeys.sort((m1, m2) -> -1*m1.numberOfInspections.compareTo(m2.numberOfInspections));

        return monkeys.get(0).numberOfInspections.multiply(monkeys.get(1).numberOfInspections);
    }

    private BigInteger kgv(List<BigInteger> numbers) {
        BigInteger kgv = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            BigInteger x = numbers.get(i);
            kgv = kgv.gcd(x);
        }
        for (BigInteger x : numbers) {
            kgv = kgv.multiply(x.divide(kgv.min(BigInteger.valueOf(1))));
        }
        return kgv;
    }
}
