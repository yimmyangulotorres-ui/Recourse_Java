package edu.unl.cc.succession.business;

import edu.unl.cc.succession.model.Printable;
import edu.unl.cc.succession.model.Successionable;

/**
 * 1. Representa la serie de numeros pares hasta un limite
 * S = 2 + 4 + 6 + 8 + ... N =
 * @author wduck (Jefferson Sarango)
 */
public class EvenNumberCalculatorUpToLimit implements Successionable, Printable {

    private Integer limit;
    private Integer currentTerm;
    private StringBuilder printableTerms;

    public EvenNumberCalculatorUpToLimit(Integer limit) {
        setLimit(limit);
        currentTerm = 0;
        printableTerms = new StringBuilder("S = ");
    }

public EvenNumberCalculatorUpToLimit (Integer start, Integer limit){
    start = validateLimit(start, "Down Limit");
    setLimit(limit);
    this.currentTerm = start % 2 != 0 ? start + 1 : start;
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
    public void setLimit(Number limit) {
        if  (limit == null) {
            throw new IllegalArgumentException("limit cannot be null");
        }
        if  (limit instanceof Integer) {
            if (limit.intValue() < 0) {
                throw new IllegalArgumentException("limit cannot be negative");
            }
            this.limit = limit.intValue();
        } else {
            throw new IllegalArgumentException("limit must be an integer");
        }
    }

    @Override
    public Number calculate() {
        long result = 0;
        this.currentTerm = this.nextTerm(this.currentTerm).intValue();
        while (currentTerm <= limit) {
            this.printableTerms.append(this.currentTerm).append(" + ");
            result += this.currentTerm;
            this.currentTerm = this.nextTerm(this.currentTerm).intValue();
        }
        if (printableTerms.length() > 4) {
            printableTerms.setLength(printableTerms.length() - 3);
        }
        return result;
    }

    @Override
    public Number nextTerm(Number current) {
        return current.intValue() + 2;
    }

    @Override
    public String print() {
        return printableTerms.toString();
    }
}

