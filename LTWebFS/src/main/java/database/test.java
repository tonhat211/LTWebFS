package database;

import controller.ControllerProduct;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
    	ControllerProduct qly1 = new ControllerProduct("a");
    	ControllerProduct qly2 = new ControllerProduct("b");
    	ControllerProduct qly3 = new ControllerProduct("c");
    	System.out.println(qly1.getUnitList().size() + qly2.getUnitList().size() + qly3.getUnitList().size() );

    	ArrayList<Integer> testt= new ArrayList<>();
    	System.out.println(testt.equals(new ArrayList<Integer>()));
    }

}

