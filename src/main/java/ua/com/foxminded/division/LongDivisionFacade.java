package ua.com.foxminded.division;

import ua.com.foxminded.division.calculator.LongDivisionCalculator;
import ua.com.foxminded.division.formatter.LongDivisionFormatter;

public class LongDivisionFacade {

    private final LongDivisionCalculator longDivisionCalculator;
    private final LongDivisionFormatter longDivisionFormatter;
    
    public LongDivisionFacade(LongDivisionCalculator longDivisionCalculator, LongDivisionFormatter longDivisionFormatter) {
	this.longDivisionCalculator = longDivisionCalculator;
	this.longDivisionFormatter = longDivisionFormatter;
    }
    
    String longDivision(int numerator, int denominartor) {
	return longDivisionFormatter.format(longDivisionCalculator.calculate(numerator, denominartor));
    }
}
