package org.example.Test;

import org.testng.annotations.Test;

public class PriorityDemo {

    @Test(priority = 1,groups = {"reg"})
    void demo1(){
        System.out.println("A");
    }

    @Test(priority = 2)
    void demo2(){
        System.out.println("B");
    }

    @Test//This method will be executed first as this doesn't have a priority so TestNG will assign this as priority 0.
    void demo3(){
        System.out.println("C");
    }
}
