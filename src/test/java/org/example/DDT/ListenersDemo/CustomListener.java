package org.example.DDT.ListenersDemo;

import org.testng.IExecutionListener;

public class CustomListener implements IExecutionListener {

    /*
    The below two methods will work in the beginning and end of the execution and we can customize it accordingly.
     */
    @Override
    public void onExecutionStart() {
        long startTime = System.currentTimeMillis();
        System.out.println("***********   End Time  :-----"+startTime);

    }

    @Override
    public void onExecutionFinish() {
        long endtime = System.currentTimeMillis();
        System.out.println("***********   End Time  :-----"+endtime);

    }
}
