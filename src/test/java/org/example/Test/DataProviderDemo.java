package org.example.Test;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class DataProviderDemo {


    @DataProvider
    public Object[][] getData()
    {
        return new Object[][]
                {
                        new Object[]{"admin","password1"},
                        new Object[]{"admin","password2"},
                        new Object[]{"admin","password3"},
                        new Object[]{"admin","password4"},
                        new Object[]{"admin","password5"},
                        new Object[]{"admin","password6"}
                };
    }

    @Test(dataProvider = "getData")//this will take the data from the above array and run for each objects
    public void loginTest(String username, String Password)
    {
        System.out.println(username);
        System.out.println(Password);
    }
}
