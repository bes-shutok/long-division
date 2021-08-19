package ua.com.foxminded.division;

import ua.com.foxminded.division.calculator.Calculator;
import ua.com.foxminded.division.calculator.LongDivisionCalculator;
import ua.com.foxminded.division.formatter.Formatter;
import ua.com.foxminded.division.formatter.LongDivisionFormatter;

public class CalculatorFacade {

    private final Calculator calculator;
    private final Formatter formatter;
    
    public CalculatorFacade(LongDivisionCalculator calculator, LongDivisionFormatter formatter) {
	this.calculator = calculator;
	this.formatter = formatter;
    }
    
    String division(int numerator, int denominator) {
	return formatter.format(calculator.calculate(numerator, denominator));
    }
}
