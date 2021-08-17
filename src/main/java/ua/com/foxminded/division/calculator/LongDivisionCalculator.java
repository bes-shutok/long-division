package ua.com.foxminded.division.calculator;

import java.util.Arrays;

import ua.com.foxminded.division.dto.LongDivision;

public class LongDivisionCalculator implements Calculator {

    @Override
    public LongDivision calculate(int numerator, int denominator) {
	String numeratorString = String.valueOf(numerator);	
	int numeratorStart = 0;
	
	if (denominator == 0) {
	    throw new IllegalArgumentException(DENOMINATOR_ZERO);
	}
	
	int quotient = numerator / denominator;
	char[] qChars= String.valueOf(quotient).toCharArray();	
	int[] perStepMinuends = new int[qChars.length];
	int[] perStepSubtrahends = new int[qChars.length];
	int[] perStepDifferences = new int[qChars.length];
	
	int longDivisionStep = 0;
	String previouseStepDifferenceString = "";
	
	for (int i = 0; i < qChars.length; i++) {
	    int multiplier = Character.getNumericValue(qChars[i]);
	    if (multiplier != 0) {
		int stepSubtrahend = denominator * multiplier;
		perStepSubtrahends[longDivisionStep] = stepSubtrahend;
		
		int stepMinuend = getStepMinuend(previouseStepDifferenceString, numeratorString.substring(numeratorStart), stepSubtrahend);
		perStepMinuends[longDivisionStep] = stepMinuend;
		
		int stepDifference = stepMinuend - stepSubtrahend;
		perStepDifferences[longDivisionStep] = stepDifference;
		previouseStepDifferenceString = stepDifference == 0 ? "" : String.valueOf(stepDifference);
		if (stepDifference == 0) {
		numeratorStart += String.valueOf(stepMinuend).length();
		} else {
		    numeratorStart += String.valueOf(stepDifference).length();
		}
		longDivisionStep++;
	    }
	}
	perStepSubtrahends = Arrays.copyOf(perStepSubtrahends, longDivisionStep);
	perStepMinuends = Arrays.copyOf(perStepMinuends, longDivisionStep);
	perStepDifferences = Arrays.copyOf(perStepDifferences, longDivisionStep);
	return new LongDivision(numerator, denominator, quotient, perStepMinuends, perStepSubtrahends, perStepDifferences);
    }
    
    private int getStepMinuend(String previouseStepDifferenceString, String numeratorLeftoverString, int stepSubtrahend) {
	int stepSubtrahendLength = String.valueOf(stepSubtrahend).length();
	int minuendLeftoverLength = stepSubtrahendLength - previouseStepDifferenceString.length();
	int thisStepMinuendCandidate = Integer.parseInt(previouseStepDifferenceString + numeratorLeftoverString.substring(0, minuendLeftoverLength));
	if (thisStepMinuendCandidate - stepSubtrahend >= 0) {
	    return thisStepMinuendCandidate;
	} else {
	    return Integer.parseInt(numeratorLeftoverString.substring(0, minuendLeftoverLength + 1));
	}
    }
}
