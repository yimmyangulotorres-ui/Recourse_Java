package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

public class PrimeNumbersCubedSeriesUpToLimit implements Successionable, Printable {
    private Integer limit;
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public PrimeNumbersCubedSeriesUpToLimit(Integer limit) {
        this(1, limit);
    }

    public PrimeNumbersCubedSeriesUpToLimit (Integer start, Integer limit){
        start = validateLimit(start, "Down Limit");
        setLimit(limit);
        this.currentTerm = nextTerm(start -1).intValue() ;
        printableTerms = new StringBuilder("S =");

    }

    private Integer validateLimit(Number value, String label){
        if(value == null){
            throw new IllegalArgumentException((label + "cannot be null"));
        }
        if(value instanceof Integer){
            if(value.intValue() < 0){
                throw new IllegalArgumentException((label + "cannot be negative"));
            }
            return value.intValue();
        }else{
            throw new IllegalArgumentException(label + "must be an Integer");
        }
    }
    @Override
    public String print() {
        return printableTerms.toString();
    }

    @Override
    public void setLimit(Number limit) {
        this.limit = validateLimit(limit, "Upper limit");
    }

    @Override
    public Number calculate() {
        double result = 0;
        int countTerm = 0;

        final double exponent = 3.0;

        while (currentTerm <= limit) {

            printableTerms.append(currentTerm)
                    .append("^3")
                    .append(" + ");

            result += Math.pow(currentTerm, exponent);

            if (currentTerm == 1) {
                currentTerm = 3;
            } else {
                currentTerm = nextTerm(currentTerm).intValue();
            }

            countTerm++;
        }
        if (printableTerms.length() > 4) {
            printableTerms.setLength(printableTerms.length() - 3);
        }
        return result;
    }


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
}


