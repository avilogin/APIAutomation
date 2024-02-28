package org.example.DDT;

import org.testng.annotations.Test;

public class DDT01 {


    @Test(dataProvider = "getData",dataProviderClass = UtilFromExcel.class)
    public void testLoginData(String username, String password)
    {
        System.out.println("Username - "+username);
        System.out.println("Password - "+password);
    }
}
