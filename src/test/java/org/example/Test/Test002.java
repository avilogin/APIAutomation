package org.example.Test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Test002 {


    @BeforeMethod//This method will run before m2 nd m3 both individually
    public void m1()
    {
        System.out.println("Before Method");
    }

    @Test
    public void m2()
    {
        System.out.println("Method 1");
    }

    @Test
    public void m3()
    {
        System.out.println("Method 2");
    }

    @AfterMethod//This method will run after m2 nd m3 both individually
    public void m4()
    {
        System.out.println("After Method");
    }
}
