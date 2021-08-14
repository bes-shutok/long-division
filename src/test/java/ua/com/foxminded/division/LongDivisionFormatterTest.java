package ua.com.foxminded.division;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.StringJoiner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LongDivisionFormatterTest {
    
    final static String RESULT_10_BY_2 = 
	    new StringJoiner("\n")
	    .add("_10|2")
	    .add(" 10|-")
	    .add(" --|5")
	    .add("  0")
	    .toString();
    
    final static String RESULT_312_BY_312 = 
	    new StringJoiner("\n")
	    .add("_312|312")
	    .add(" 312|---")
	    .add(" ---|1")
	    .add("   0")
	    .toString();
    
    
    final static String RESULT_10_BY_9 = 
	    new StringJoiner("\n")
	    .add("_10|9")
	    .add("  9|-")
	    .add(" --|1")
	    .add("  1")
	    .toString();
    
    final static String RESULT_78945_BY_4 = 
	    new StringJoiner("\n")
	    .add("_78945|4")
	    .add(" 4    |-----")
	    .add(" -    |19736")
	    .add("_38")
	    .add(" 36")
	    .add(" --")
	    .add(" _29")
	    .add("  28")
	    .add("  --")
	    .add("  _14")
	    .add("   12")
	    .add("   --")
	    .add("   _25")
	    .add("    24")
	    .add("    --")
	    .add("     1")
	    .toString();
    
    final static String RESULT_3122600_BY_312 = 
	    new StringJoiner("\n")
	    .add("_3122600|312")
	    .add(" 312    |-----")
	    .add(" ---    |10008")
	    .add("   _2600")
	    .add("    2496")
	    .add("    ----")
	    .add("     104")
	    .toString();
    
    private LongDivisionFormatter formatter;
    
    @BeforeEach
    void testSetUp() {
	formatter = new LongDivisionFormatter();
    }

    @Test //@Disabled
    void shouldFormatForOneDivision() {
	LongDivision longDivision = new LongDivision(10, 2, 5, new int[] {10}, new int[] {10}, new int[] {0});
	assertEquals(RESULT_10_BY_2, formatter.format(longDivision));
    }
    
    @Test //@Disabled
    void shouldFormatForMultidigitDivision() {
	LongDivision longDivision = new LongDivision(312, 312, 1, new int[] {312}, new int[] {312}, new int[] {0});
	assertEquals(RESULT_312_BY_312, formatter.format(longDivision));
    }    

    @Test //@Disabled
    void shouldFormatForFewDigitNumeratorAndOneDigitDenominator() {
	LongDivision longDivision = new LongDivision(10, 9, 1, new int[] {10}, new int[] {9}, new int[] {1});
	assertEquals(RESULT_10_BY_9, formatter.format(longDivision));
    }
    
    @Test //@Disabled
    void shouldFormatForOneDigitDenominatorMultiSteps() {
	int[] perStepMinuends = new int[] {7, 38, 29, 14, 25};
	int[] perStepSubtrahends = new int[] {4, 36, 28, 12, 24};
	int[] perStepDifferences = new int[] {3, 2, 1, 2, 1};
	LongDivision longDivision = new LongDivision(78945, 4, 19736, perStepMinuends, perStepSubtrahends, perStepDifferences);
	assertEquals(RESULT_78945_BY_4, formatter.format(longDivision));
    }    
    
    @Test //@Disabled
    void shouldFormatForMultidigitDenominatorMultiSteps() {
	int[] perStepMinuends = new int[] {312, 2600};
	int[] perStepSubtrahends = new int[] {312, 2496};
	int[] perStepDifferences = new int[] {0, 104};
	LongDivision longDivision = new LongDivision(3122600, 312, 10008, perStepMinuends, perStepSubtrahends, perStepDifferences);
	String expected = RESULT_3122600_BY_312;
	String result = formatter.format(longDivision);
	assertEquals(expected, result);
    }
}