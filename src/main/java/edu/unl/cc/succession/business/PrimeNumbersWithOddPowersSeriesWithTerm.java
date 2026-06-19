package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 * Representa el cálculo de la 5. Serie de primos elevados a impares hasta n térmimos
 * (S=S=7^]+ 3 ^ 3 + 5 ^ 5 + 7 ^ 7 + 17 ^ 9 +13^77..):
 * @author wduck (Diyer Torres)
 */

public class PrimeNumbersWithOddPowersSeriesWithTerm implements Successionable, Printable {

    private Integer nTerm;  //limit
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public PrimeNumbersWithOddPowersSeriesWithTerm(Integer limit) {
        this(1, limit);
    }

    public PrimeNumbersWithOddPowersSeriesWithTerm(Integer start, Integer limit) {
        start = validateLimit(start, "Downn limit");
        setLimit(limit);
        this.currentTerm = nextTerm(start-1).intValue();
        printableTerms = new StringBuilder("S = ");
    }


    private Integer validateLimit(Number value, String label) {
        if  (value == null) {
            throw new IllegalArgumentException(label + " cannot be null");
        }
        if  (value instanceof Integer) {
            if (value.intValue() < 0) {
                throw new IllegalArgumentException(label + " cannot be negative");
            }
            return value.intValue();
        } else {
            throw new IllegalArgumentException(label + " must be an integer");
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

        int exponent = 1;

        while (countTerm < nTerm) {

            this.printableTerms
                    .append(currentTerm)
                    .append("^")
                    .append(exponent)
                    .append(" + ");
            result = result + Math.pow(currentTerm, exponent);

            currentTerm = nextTerm(currentTerm).intValue();
            exponent += 2;
            countTerm++;
        }
        if (printableTerms.length() > 4) {
            printableTerms.setLength(printableTerms.length() - 3);
        }
        return result;
    }

    /**
     * Representar el término solo de la base de la serir
     * @param current
     * @return
     */
    @Override
    public Number nextTerm(Number current) {
        current = current.intValue() + 1;
        boolean isPrime = false;
        while (!isPrime){
            isPrime = isPrime(current.intValue());
            if (!isPrime){
                current = current.intValue() + 1;
            }
        }
        return current;
    }

    private boolean isPrime(Integer number) {
        if (number < 1) {
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

