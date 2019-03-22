package ru.job4j.list;

import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (int) Math.ceil(((double) list.size() / rows));
        int[][] array = new int[rows][cells];
        int c = 0, r = 0;
        for (Integer value : list) {
            array[r][c] = value;
            if (c < cells - 1) {
                c++;
            } else {
                c = 0;
                r++;
            }
//            for (int j = 0; j < rows; j++) {
//                for (int k = 0; k < cells; k++) {
//                    try {
//                        array[j][k]=list.get(j*rows+k);
//                    }catch (Exception e) {
//                        array[j][k]=0;
//                    }
//                }
           }
        return array;
    }
}
