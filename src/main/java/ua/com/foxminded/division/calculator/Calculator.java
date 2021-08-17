package ua.com.foxminded.division.calculator;

import ua.com.foxminded.division.dto.LongDivision;

public interface Calculator {

    String DENOMINATOR_ZERO = "Denominator cannot be 0!";

    LongDivision calculate(int aNumerator, int aDenominator);

}