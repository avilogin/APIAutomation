package org.example.Test;

import org.testng.annotations.Test;

public class Test009 {

    @Test(groups = {"init"}, priority = 1)
    public void getToken()
    {
        System.out.println("Token");
    }

    @Test(groups = {"init"},priority = 2)
    public void getBookingId()
    {
        System.out.println("Booking ID");
    }

    @Test(dependsOnGroups = {"init.*"})//before running this method all the methods that are grouped as init will be executed.
    public void updateReq()
    {

        System.out.println("Updated");
    }
}
