package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;
/**
 * 4. Representa el cálculo de la Serie de primos elevados por pares hasta un limite
 * (S= 1 ^ 2 + 3 ^ 4 + 5 ^ 6 + 7 ^ 8 + 11 ^ 10 +13^(12)...+N):
 * @author wduck (Jefferson Sarango)
 */

public class PrimeNumbersEvenPowerCalculatorUpToLimit implements Successionable, Printable {
    private Integer limit;
    private Integer currentTerm;
    private Integer exponent;
    private StringBuilder printableTerms;

    public PrimeNumbersEvenPowerCalculatorUpToLimit(Integer limit) {
        this(1, limit);
    }

    public PrimeNumbersEvenPowerCalculatorUpToLimit(Integer start, Integer limit) {
        start = validateLimit(start, "Down Limit");
        setLimit(limit);

        this.currentTerm = nextTerm(start - 1).intValue();
        this.exponent = 2;

        printableTerms = new StringBuilder("S = ");
    }

    private Integer validateLimit(Number value, String label) {
        if (value == null) {
            throw new IllegalArgumentException(label + " cannot be null");
        }

        if (value instanceof Integer) {
            if (value.intValue() < 0) {
                throw new IllegalArgumentException(label + " cannot be negative");
            }
            return value.intValue();
        }

        throw new IllegalArgumentException(label + " must be an Integer");
    }

    @Override
    public String print() {
        return printableTerms.toString();
    }

    @Override
    public void setLimit(Number limit) {
        this.limit = validateLimit(limit, "Upper Limit");
    }

    @Override
    public Number calculate() {

        double sum = 0;
        while (currentTerm <= limit) {

            sum += Math.pow(currentTerm, exponent);
            printableTerms
                    .append(currentTerm)
                    .append("^")
                    .append(exponent);
            int nextPrime = nextTerm(currentTerm).intValue();
            if (nextPrime <= limit) {
                printableTerms.append(" + ");
            }

            currentTerm = nextPrime;
            exponent += 2;
        }

        return sum;
    }

    @Override
    public Number nextTerm(Number current) {

        current = current.intValue() + 1;
        boolean isPrime = false;

        while (!isPrime) {
            isPrime = isPrime(current.intValue());

            if (!isPrime) {
                current = current.intValue() + 1;
            }
        }

        return current;
    }

    private boolean isPrime(Integer number) {

        if (number < 2) {
            return false;
        }

        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
