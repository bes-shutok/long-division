package ua.com.foxminded.division.calculator;

import ua.com.foxminded.division.dto.LongDivision;

public class LongDivisionCalculator implements Calculator {

    @Override
    public LongDivision calculate(int numerator, int denominator) {
	validate(denominator);
	String numeratorString = String.valueOf(numerator);	
	int numeratorStart = 0;
	int quotient = numerator / denominator;
	int steps = String.valueOf(quotient).replaceAll("0", "").length();
	char[] qChars = String.valueOf(quotient).toCharArray();	
	int[] perStepMinuends = new int[steps];
	int[] perStepSubtrahends = new int[steps];
	int[] perStepDifferences = new int[steps];
	for (int i = 0, longDivisionStep = 0, stepDifference = 0; i < qChars.length; i++) {
	    int multiplier = Character.getNumericValue(qChars[i]);
	    if (multiplier != 0) {
		int stepSubtrahend = denominator * multiplier;
		perStepSubtrahends[longDivisionStep] = stepSubtrahend;
		
		int stepMinuend = getStepMinuend(stepDifference, numeratorString.substring(numeratorStart), stepSubtrahend);
		perStepMinuends[longDivisionStep] = stepMinuend;
		
		stepDifference = stepMinuend - stepSubtrahend;
		perStepDifferences[longDivisionStep] = stepDifference;
		numeratorStart += stepDifference == 0 ? length(stepMinuend) : length(stepDifference);
		longDivisionStep++;
	    }
	}
	return new LongDivision(numerator, denominator, quotient, perStepMinuends, perStepSubtrahends, perStepDifferences);
    }

    private void validate(int denominator) {
	if (denominator == 0) {
	    throw new IllegalArgumentException(DENOMINATOR_ZERO);
	}
    }

    private int getStepMinuend(int stepDifference, String numeratorLeftoverString, int stepSubtrahend) {
	String previouseStepDifferenceString = stepDifference == 0 ? "" : String.valueOf(stepDifference);
	int stepSubtrahendLength = length(stepSubtrahend);
	int minuendLeftoverLength = stepSubtrahendLength - previouseStepDifferenceString.length();
	int thisStepMinuendCandidate = Integer.parseInt(previouseStepDifferenceString + numeratorLeftoverString.substring(0, minuendLeftoverLength));
	if (thisStepMinuendCandidate - stepSubtrahend >= 0) {
	    return thisStepMinuendCandidate;
	} else {
	    return Integer.parseInt(numeratorLeftoverString.substring(0, minuendLeftoverLength + 1));
	}
    }
    
    private int length(int number) {
	return String.valueOf(number).length();
    }
}
