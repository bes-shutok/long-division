package ua.com.foxminded.division;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class LongDivisionFormatter {

    private static final char ROW_SEPARATOR = '|';
    private static final char STEP_SEPARATOR = '-';
    private static final char STEP_NUMBER_PREFIX = '_';
    private static final char END_OF_LINE = '\n';

    private String numerator;
    private String denominator;
    private String quotient;
    private String[] perStepMinuends;
    private String[] perStepSubtrahends;
    private String[] perStepDifferences;
    private String leftHeader;
    private int leftHeaderLength;

    private int leftPadding = 0;

    public String format(LongDivision longDivision) {
	this.numerator = longDivision.getNumerator();
	this.denominator = longDivision.getDenominator();
	this.quotient = longDivision.getQuotient();
	this.perStepMinuends = longDivision.getPerStepMinuends();
	this.perStepSubtrahends =longDivision.getPerStepSubtrahends();
	this.perStepDifferences = longDivision.getPerStepDifferences();
	this.leftHeader = STEP_NUMBER_PREFIX + numerator;
	this.leftHeaderLength = leftHeader.length();
	String line1 = getFirstLine();
	String line2 = getSecondLine();
	String line3 = getThirdLine();
	String theRest = getTheRestLines();
	return line1 + line2 + line3 + theRest;
    }

    private String getFirstLine() {
	return new StringJoiner(ROW_SEPARATOR + "").add(leftHeader).add(denominator + END_OF_LINE).toString();
    }

    private String getSecondLine() {
	int subtrahendPadding = perStepMinuends[0].length() - perStepSubtrahends[0].length();
	String leftHeader2 = fillWithSpaces(1 + subtrahendPadding) + perStepSubtrahends[0];
	leftHeader2 = padRight(leftHeader2, leftHeaderLength);
	String rightHeader2 = fillWith(Math.max(quotient.length(), denominator.length()), STEP_SEPARATOR);
	return new StringJoiner(ROW_SEPARATOR + "").add(leftHeader2).add(rightHeader2 + END_OF_LINE).toString();
    }

    private String getThirdLine() {
	String leftHeader3 = " " + fillWith(perStepMinuends[0].length(), STEP_SEPARATOR);
	leftHeader3 = padRight(leftHeader3, leftHeaderLength);
	return new StringJoiner(ROW_SEPARATOR + "").add(leftHeader3).add(quotient + END_OF_LINE).toString();
    }

    private String getTheRestLines() {
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

    public static final void main(String[] args) {
	
    }
}
