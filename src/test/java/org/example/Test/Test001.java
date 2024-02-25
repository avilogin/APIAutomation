package org.example.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Test001 {

    @Test
    public void test1()
    {
        Assert.assertEquals(true, true);
    }

    @Test
    public void test2()
    {
        Assert.assertEquals(false, true);
    }
}
