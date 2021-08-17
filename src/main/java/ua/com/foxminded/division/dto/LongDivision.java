package ua.com.foxminded.division.dto;

import java.util.Arrays;
import java.util.Objects;

/**
 * 
 * English terms are taken from wiki pages:
 * <a href="https://en.wikipedia.org/wiki/Long_division">Long_division</a>
 * 
 * <a href="https://en.wikipedia.org/wiki/Division_(mathematics)">Division
 * (mathematics)</a>
 * 
 * <a href="https://en.wikipedia.org/wiki/Subtraction">Subtraction</a>
 *
 */
public class LongDivision {

    private final String numerator;
    private final String denominator;
    private final String quotient;
    private final String[] perStepMinuends;
    private final String[] perStepSubtrahends;
    private final String[] perStepDifferences;

    public LongDivision(int numerator, int denominator, int quotient, int[] perStepMinuends,
	    int[] perStepSubtrahends, int[] perStepDifference) {
	this.numerator = String.valueOf(numerator);
	this.denominator = String.valueOf(denominator);
	this.quotient = String.valueOf(quotient);
	this.perStepMinuends = Arrays.stream(perStepMinuends)
		.mapToObj(String::valueOf)
		.toArray(String[]::new);
	this.perStepSubtrahends = Arrays.stream(perStepSubtrahends)
		.mapToObj(String::valueOf)
		.toArray(String[]::new);	
	this.perStepDifferences = Arrays.stream(perStepDifference)
		.mapToObj(String::valueOf)
		.toArray(String[]::new);
    }

    public String getNumerator() {
        return numerator;
    }

    public String getDenominator() {
        return denominator;
    }

    public String getQuotient() {
        return quotient;
    }

    public String[] getPerStepMinuends() {
        return perStepMinuends;
    }

    public String[] getPerStepSubtrahends() {
        return perStepSubtrahends;
    }

    public String[] getPerStepDifferences() {
        return perStepDifferences;
    }
    
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + Arrays.hashCode(perStepMinuends);
	result = prime * result + Arrays.hashCode(perStepSubtrahends);
	result = prime * result + Arrays.hashCode(perStepDifferences);
	result = prime * result + Objects.hash(numerator, denominator, quotient);
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	LongDivision other = (LongDivision) obj;
	return Objects.equals(numerator, other.numerator) && Objects.equals(denominator, other.denominator) 
		&& Objects.equals(quotient, other.quotient)
		&& Arrays.equals(perStepMinuends, other.perStepMinuends)
		&& Arrays.equals(perStepSubtrahends, other.perStepSubtrahends)
		&& Arrays.equals(perStepDifferences, other.perStepDifferences);
    }

    @Override
    public String toString() {
	return "LongDivision [numerator=" + numerator + ", denominator=" + denominator + ", quotient=" + quotient
		+ ", perStepMinuends=" + Arrays.toString(perStepMinuends)
		+ ", perStepSubtrahends=" + Arrays.toString(perStepSubtrahends) 
		+ ", perStepDifference=" + Arrays.toString(perStepDifferences)
		+ "]";
    }
}