package org.example;


import org.example.calculator.Calculator;
import org.example.calculator.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class calculatorTest {
    /**
     * 요구사항
     * 간단한 사칙연산을 할 수 있다.
     * 양수로만 계산할 수 있다.
     * 나눗셈에서 0을 나누는 경우 illegalArgumentException 예외를 발생시킨다.
     * MVC패턴(Model-View-Controller)기반으로 구현한다.
     */

    @DisplayName("덧셈연산을 수행한다.")
    @Test
    void additionTest() {
        int result = Calculator.calculate(new PositiveNumber(1), "+", new PositiveNumber(2));
        assertThat(result).isEqualTo(3);
    }

    @DisplayName("뺄셈연산을 수행한다.")
    @Test
    void subtractionTest() {
        int result = Calculator.calculate(new PositiveNumber(1), "-", new PositiveNumber(2));
        assertThat(result).isEqualTo(-1);
    }

    @DisplayName("4칙연산 한번에 조회")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void calculateTest(int operand1, String operator, int operand2, int result) {
        int calculateResult = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
        assertThat(calculateResult).isEqualTo(result);
    }

    public static Stream<Arguments> formulaAndResult() {
        return Stream.of(
                arguments(1, "+", 2, 3),
                arguments(1, "-", 2, -1),
                arguments(2, "*", 4, 8),
                arguments(4, "/", 2, 2)
        );
    }
    
//    @DisplayName("나눗셈에서 0을 나누는 경우 illegalArgumentException 예외를 발생시킨다.")
//    @Test
//    public void calculateExceptionTest() throws Exception {
//        //given
//        assertThatCode(() -> Calculator.calculate(new PositiveNumber(10), "/",new PositiveNumber(0)))
//                .isInstanceOf(IllegalArgumentException.class);
//    }
}
