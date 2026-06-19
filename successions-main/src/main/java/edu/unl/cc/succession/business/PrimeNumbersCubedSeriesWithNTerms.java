package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 3. Representa el calculo de la Serie de primos elevados al cubo  hasta N términos 
 * (S = 1^3 + 3^3 + 5^3 + 7^3 + 11^3 + 13^3 ...): 
 * @author wduck (Yimmy Angulo)
 */

public class PrimeNumbersCubedSeriesWithNTerms implements Successionable, Printable {

    private Integer nTerm;
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public PrimeNumbersCubedSeriesWithNTerms(Integer nTerm) {
        this(1, nTerm);
    }

    public PrimeNumbersCubedSeriesWithNTerms(Integer start, Integer nTerm) {
        start = validateLimit(start, "Down Limit");
        setLimit(nTerm);
        this.currentTerm = nextTerm(start - 1).intValue();
        this.printableTerms = new StringBuilder("S = ");
    }

    private Integer validateLimit(Number value, String label) {
        if (value == null) {
            throw new IllegalArgumentException(label + " cannot be null");
        }

        if (!(value instanceof Integer)) {
            throw new IllegalArgumentException(label + " must be an Integer");
        }

        if (value.intValue() < 0) {
            throw new IllegalArgumentException(label + " cannot be negative");
        }

        return value.intValue();
    }

    @Override
    public void setLimit(Number limit) {
        this.nTerm = validateLimit(limit, "Number of terms");
    }

    @Override
    public Number calculate() {
        double result = 0;
        int countTerm = 0;

        while (countTerm < nTerm) {

            printableTerms.append(currentTerm)
                    .append("^3");

            result += Math.pow(currentTerm, 3);

            countTerm++;

            if (countTerm < nTerm) {
                printableTerms.append(" + ");
            }

            currentTerm = nextTerm(currentTerm).intValue();
        }

        return result;
    }

    @Override
    public Number nextTerm(Number current) {
        int number = current.intValue() + 1;

        while (!isPrime(number)) {
            number++;
        }

        return number;
    }

    private boolean isPrime(Integer number) {

        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String print() {
        return printableTerms.toString();
    }
}
