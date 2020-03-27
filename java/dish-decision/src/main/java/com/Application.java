package com;

import com.dmn.DMNRunner;

public class Application {

    public static void main(String[] args){
        DMNRunner dmn = new DMNRunner();
        //System.out.println(dmn.run("Summer",4));
        //dmn.runNew("Summer",4);
        System.out.println(dmn.runBmi(58,90));
    }
}
