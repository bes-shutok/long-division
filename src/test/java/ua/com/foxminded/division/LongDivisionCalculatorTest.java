package ua.com.foxminded.division;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class LongDivisionCalculatorTest {
    
    private LongDivisionCalculator calculator;
    
    @BeforeEach
    void testSetUp() {
	calculator = new LongDivisionCalculator();
    }
    
    @Test //@Disabled
    void shouldFailIfDenominatorIsZero() {
	Exception exception = assertThrows(IllegalArgumentException.class, () -> calculator.calculate(1, 0));
	assertEquals(LongDivisionCalculator.DENOMINATOR_ZERO, exception.getMessage());
    } 
    
    @Test //@Disabled
    void shouldCalculateForOneDivision() {
	LongDivision longDivision = new LongDivision(10, 2, 5, new int[] {10}, new int[] {10}, new int[] {0});
	assertEquals(longDivision, calculator.calculate(10, 2));
    }    
    
    @Test //@Disabled
    void shouldCalculateForFewDigitNumeratorAndOneDigitDenominator() {
	LongDivision longDivision = new LongDivision(10, 9, 1, new int[] {10}, new int[] {9}, new int[] {1});
	assertEquals(longDivision, calculator.calculate(10, 9));
    }
    
    @Test //@Disabled
    void shouldCalculateForOneMultidigitDivision() {
	LongDivision longDivision = new LongDivision(312, 312, 1, new int[] {312}, new int[] {312}, new int[] {0});
	assertEquals(longDivision, calculator.calculate(312, 312));
    }
    
    @Test //@Disabled
    void shouldCalculateForOneDigitDenominatorMultiSteps() {
	int[] perStepMinuends = new int[] {7, 38, 29, 14, 25};
	int[] perStepSubtrahends = new int[] {4, 36, 28, 12, 24};
	int[] perStepDifferences = new int[] {3, 2, 1, 2, 1};
	LongDivision longDivision = new LongDivision(78945, 4, 19736, perStepMinuends, perStepSubtrahends, perStepDifferences);
	assertEquals(longDivision, calculator.calculate(78945, 4));
    }    
    
    @Test //@Disabled
    void shouldCalculateForMultidigitDenominatorSeverslSteps() {
	int[] perStepMinuends = new int[] {312, 2600};
	int[] perStepSubtrahends = new int[] {312, 2496};
	int[] perStepDifferences = new int[] {0, 104};
	LongDivision longDivision = new LongDivision(3122600, 312, 10008, perStepMinuends, perStepSubtrahends, perStepDifferences);
	assertEquals(longDivision, calculator.calculate(3122600, 312));
    }
}