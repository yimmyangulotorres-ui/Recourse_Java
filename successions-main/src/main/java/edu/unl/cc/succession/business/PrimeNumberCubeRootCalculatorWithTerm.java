package edu.unl.cc.succession.business;
import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

public class PrimeNumberCubeRootCalculatorWithTerm implements Successionable, Printable {

/**
 8. Serie de primos elevados a la raiz cúbica hasta un n términos 
 * (S = 1^(1/3) + 3^(1/3) + 5^(1/3) + 7^(1/3) + 11^(1/3) + 13^(1/3)):
 * @author wduck (Irvin Armijos)
 */
    
    private Integer nTerm;
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public PrimeNumberCubeRootCalculatorWithTerm(Integer limit) {
        this(1, limit);
    }

    public PrimeNumberCubeRootCalculatorWithTerm(Integer start, Integer limit) {
        setLimit(limit);
        this.currentTerm = start;

        if (this.currentTerm < 1) {
            this.currentTerm = 1;
        }

        printableTerms = new StringBuilder("S = ");
    }

    private Integer validateLimit(Number value, String label) {
        if (value == null) {
            throw new IllegalArgumentException(label + " cannot be null");
        }
        if (value instanceof Integer) {
            if (value.intValue() <= 0) {
                throw new IllegalArgumentException(label + " must be positive");
            }
            return value.intValue();
        }
        throw new IllegalArgumentException(label + " must be an integer");
    }

    @Override
    public void setLimit(Number limit) {
        this.nTerm = validateLimit(limit, "nTerm");
    }

    @Override
    public Number calculate() {
        double result = 0;
        int countTerm = 0;

        final double exponent = 1.0 / 3.0;

        while (countTerm < nTerm) {

            printableTerms.append(currentTerm)
                    .append("^(1/3)")
                    .append(" + ");

            result += Math.pow(currentTerm, exponent);

            currentTerm = nextTerm(currentTerm).intValue();
            countTerm++;
        }
        if (printableTerms.length() > 4) {
            printableTerms.setLength(printableTerms.length() - 3);
        }
        return result;
    }

    @Override
    public Number nextTerm(Number current) {
        int value = current.intValue() + 1;

        while (!isPrime(value)) {
            value++;
        }

        return value;
    }

    private boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    @Override
    public String print() {
        return printableTerms.toString();
    }
}
