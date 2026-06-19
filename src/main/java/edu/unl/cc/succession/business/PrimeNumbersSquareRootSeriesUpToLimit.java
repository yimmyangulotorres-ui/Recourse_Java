package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 * Representa la serie de números primos elevados a la raíz cuadrada.
 * S = 1^(1/2) + 3^(1/2) + 5^(1/2) + 7^(1/2) + 11^(1/2) + 13^(1/2) + ...
 *
 * El límite representa la cantidad de términos que desea generar el usuario.
 *
 * @author
 */
public class PrimeNumbersSquareRootSeriesUpToLimit implements Successionable, Printable {

    private Integer limit;
    private Integer currentTerm;
    private StringBuilder printableTerms;


    public PrimeNumbersSquareRootSeriesUpToLimit(Integer limit) {
        setLimit(limit);
        this.currentTerm = 1;
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
    public void setLimit(Number limit) {
        this.limit = validateLimit(limit, "Limit");
    }

    @Override
    public Number calculate() {

        double result = 0;
        int countTerm = 0;

        while (countTerm < limit) {

            printableTerms.append(currentTerm)
                    .append("^(1/2)");

            result += Math.pow(currentTerm, 1.0 / 2.0);

            countTerm++;

            if (countTerm < limit) {
                printableTerms.append(" + ");
            }

            if (currentTerm == 1) {
                currentTerm = 3;
            } else {
                currentTerm = nextTerm(currentTerm).intValue();
            }
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
        return printableTerms.toString();
    }
}