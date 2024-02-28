package org.example.DDT.ListenersDemo;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListener.class)
public class TC001 {

    @Test(groups = "sanity")
    public void TC1()
    {
        System.out.println("Test Case 01");
        Assert.assertTrue(true);
    }

    @Test(groups = "sanity")
    public void TC2()
    {
        System.out.println("Test Case 02");
        Assert.assertTrue(false);
    }
}
