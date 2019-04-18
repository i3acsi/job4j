package ru.job4j.services;

import java.util.Iterator;

public class JaggedArrayIterator implements Iterator {
    private final int[][] array;
    private int counter = 0;
    private IteratorArray ir;


    public JaggedArrayIterator(int[][] array) {
        this.array = array;
        this.ir = new IteratorArray(array[0]);
    }

    @Override
    public boolean hasNext() {
        boolean result = ir.hasNext();
        if (!result & counter < array.length - 1) {
            IteratorArray tempIr = new IteratorArray(array[counter + 1]);
            result = tempIr.hasNext();
        }
        return result;
    }

    @Override
    public Object next() {
        if (!ir.hasNext()) {
            counter++;
            ir = new IteratorArray(array[counter]);
        }
        return ir.next();
    }
}

//        // System.out.println(array[0].length + " " + array[1].length + array.length);
//        int temp = 0;
//        int result = 0;
//        point:
//        for (int i = 0; i < array.length; i++) {
//            for (int j = 0; j < array[i].length; j++) {
//                //System.out.println(array[i][j]);
//                if (temp == counter) {
//                    counter++;
//                    result =  array[i][j];
//                    break point;
//                } else {
//                    temp++;
//                }
//            }
//        }
//
//        return result;

