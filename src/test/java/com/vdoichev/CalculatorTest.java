package com.vdoichev;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Target;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Додавання двох цифр (2+3)")
    void editionTwoNumbers() {
        String expression = "2+3";
        double result = Main.calculate(expression);
        assertEquals(5,result,"2+3 should be equal 5");
    }
    @Test
    @DisplayName("Додавання двох цифр (5+8)")
    void editionOtherTwoNumbers() {
        String expression = "5+8";
        double result = Main.calculate(expression);
        assertEquals(13,result,"5+8 should be equal 13");
    }

    @Test
    @DisplayName("Додавання двох чисел (11+25)")
    void editionTwoDecimalNumbers() {
        String expression = "11+25";
        double result = Main.calculate(expression);
        assertEquals(36,result,"11+25 should be equal 36");
    }



}
