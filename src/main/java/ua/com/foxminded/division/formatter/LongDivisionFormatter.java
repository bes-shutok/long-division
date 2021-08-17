package ua.com.foxminded.division.formatter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import ua.com.foxminded.division.dto.LongDivision;

public class LongDivisionFormatter implements Formatter {

    private static final char ROW_SEPARATOR = '|';
    private static final char STEP_SEPARATOR = '-';
    private static final char STEP_NUMBER_PREFIX = '_';
    private static final char END_OF_LINE = '\n';

    @Override
    public String format(LongDivision longDivision) {
	String denominator = longDivision.getDenominator();
	String quotient = longDivision.getQuotient();
	String[] perStepMinuends = longDivision.getPerStepMinuends();
	String[] perStepSubtrahends =longDivision.getPerStepSubtrahends();
	String[] perStepDifferences = longDivision.getPerStepDifferences();
	String leftHeader = STEP_NUMBER_PREFIX + longDivision.getNumerator();
	int leftHeaderLength = leftHeader.length();
	
	String line1 = getFirstLine(leftHeader, denominator);
	String line2 = getSecondLine(
		perStepMinuends[0],
		perStepSubtrahends[0],
		leftHeaderLength,
		Math.max(quotient.length(), denominator.length())
		);
	String line3 = getThirdLine(perStepMinuends[0].length(), leftHeaderLength, quotient);
	String theRest = getTheRestLines(perStepMinuends, perStepSubtrahends, perStepDifferences);
	return line1 + line2 + line3 + theRest;
    }

    private String getFirstLine(String leftHeader, String denominator) {
	return new StringJoiner(ROW_SEPARATOR + "").add(leftHeader).add(denominator + END_OF_LINE).toString();
    }

    private String getSecondLine(String firstMinuend, String firstSubtrahend, int leftHeaderLength, int rightHeaderLength) {
	int subtrahendPadding = firstMinuend.length() - firstSubtrahend.length();
	String leftHeader2 = fillWithSpaces(1 + subtrahendPadding) + firstSubtrahend;
	leftHeader2 = padRight(leftHeader2, leftHeaderLength);
	String rightHeader2 = fillWith(rightHeaderLength, STEP_SEPARATOR);
	return new StringJoiner(ROW_SEPARATOR + "").add(leftHeader2).add(rightHeader2 + END_OF_LINE).toString();
    }

    private String getThirdLine(int firstMinuendLength, int leftHeaderLength, String quotient) {
	String leftHeader3 = " " + fillWith(firstMinuendLength, STEP_SEPARATOR);
	leftHeader3 = padRight(leftHeader3, leftHeaderLength);
	return new StringJoiner(ROW_SEPARATOR + "").add(leftHeader3).add(quotient + END_OF_LINE).toString();
    }

    private String getTheRestLines(String[] perStepMinuends, String[] perStepSubtrahends, String[] perStepDifferences) {
	int leftPadding = 0;
	List<String> stepDivisionLines = new ArrayList<>();
	int lastIndex = perStepDifferences.length - 1;
	
	for (int i = 1; i <= lastIndex; i++) {
	    leftPadding += perStepMinuends[i-1].length() - perStepDifferences[i-1].length();
	    if (Integer.parseInt(perStepDifferences[i-1]) == 0) {
		leftPadding++;
	    }
	    StringJoiner divisionBlock = new StringJoiner(END_OF_LINE + "");
	    String stepMinuend = perStepMinuends[i];
	    String stepSubtrahend = perStepSubtrahends[i];
	    divisionBlock.add(fillWithSpaces(leftPadding) + STEP_NUMBER_PREFIX + stepMinuend);
	    divisionBlock.add(fillWithSpaces(leftPadding + 1) + stepSubtrahend);
	    divisionBlock.add(fillWithSpaces(leftPadding + 1) + fillWith(stepSubtrahend.length(), STEP_SEPARATOR));
	    stepDivisionLines.add(divisionBlock.toString());
	}
	
	leftPadding += perStepMinuends[lastIndex].length() - perStepDifferences[lastIndex].length();
	String lastDivisionLine = fillWithSpaces(leftPadding + 1) + perStepDifferences[lastIndex];
	stepDivisionLines.add(lastDivisionLine);

	return stepDivisionLines.stream().collect(Collectors.joining("\n"));
    }

    private String padRight(String inputString, int length) {
	return String.format("%1$-" + length + "s", inputString);
    }

    private String fillWithSpaces(int length) {
	if (length < 1) {
	    return "";
	}
	return String.format("%1$" + length + "s", "");
    }

    private String fillWith(int length, char padSymbol) {
	return fillWithSpaces(length).replace(' ', padSymbol);
    }
}