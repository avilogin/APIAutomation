package org.example.Test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Test008 {

    /*
    Soft and Hard Assertions
    In case of soft assertions even after the assertion will fail but still the sop statements will gets printed anyway
    but in  case of hard assertions the sop statements will not be executed.

     */

    @Test
    public void m1()
    {
        SoftAssert s = new SoftAssert();
        s.assertEquals(true, false);
        System.out.println("I am soft assertion");
        s.assertAll();
    }

    @Test
    public void m2()
    {
        Assert.assertEquals(true,false ,"Failed");
        System.out.println("I am Hard assertions");
    }

}
