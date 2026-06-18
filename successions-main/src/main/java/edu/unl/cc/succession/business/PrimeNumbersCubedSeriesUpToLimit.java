package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 * Representa la serie de números primos elevados a la raíz cuadrada.
 * S = 2^(1/2) + 3^(1/2) + 5^(1/2) + 7^(1/2) + 11^(1/2) + ...
 *
 * El límite representa la cantidad de términos.
 */
public class PrimeNumbersSquareRootSeriesUpToLimit implements Successionable, Printable {

    private Integer nTerm;
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public PrimeNumbersSquareRootSeriesUpToLimit(Integer limit) {
        this(1, limit);
    }

    public PrimeNumbersSquareRootSeriesUpToLimit(Integer start, Integer limit) {
        start = validateLimit(start, "Down Limit");
        setLimit(limit);
        this.currentTerm = nextTerm(start - 1).intValue();
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
        } else {
            throw new IllegalArgumentException(label + " must be an Integer");
        }
    }

    @Override
    public void setLimit(Number limit) {
        this.nTerm = validateLimit(limit, "nTerm");
    }

    @Override
    public Number calculate() {

        double result = 0;
        int countTerm = 0;

        while (countTerm < nTerm) {

            printableTerms.append(currentTerm)
                    .append("^(1/2) + ");

            result += Math.pow(currentTerm, 1.0 / 2.0);

            currentTerm = nextTerm(currentTerm).intValue();

            countTerm++;
        }

        return result;
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

    @Override
    public String print() {
        return this.printableTerms.toString();
    }
}

