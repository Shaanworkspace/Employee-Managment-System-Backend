package com.employee.Controller;
import com.employee.Controller.tester;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class testerTest {
    @BeforeClass
    public static void sha(){
        System.out.println("shaan");
    }

    tester t = new tester();
    @Test
    @DisplayName("test 1 ")
    public void getEmployeeByIdTest() {
        int Result = t.getEmployeeById(3);
        int expectedResult = 8;
        Assert.assertEquals(expectedResult,Result);
    }
}
