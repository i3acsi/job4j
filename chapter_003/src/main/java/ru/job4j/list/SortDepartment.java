package ru.job4j.list;

import java.util.*;

public class SortDepartment {
    private String[] departs;

    public SortDepartment(String[] departs) {
        this.departs = departs;
    }

    public String[] ascendingSort() {
        List<String> list = completeStructure(departs);
        list.sort(String::compareTo);
        return list.toArray(new String[0]);
    }

    public String[] descendingSort() {
        List<String> list = completeStructure(departs);
        return list.stream().sorted(((o1, o2) -> {
            int min = Math.min(o1.length(), o2.length());
            int result;
            result = o2.substring(0, min).compareTo(o1.substring(0, min));
            if (result == 0) {
                result = Integer.compare(o1.length(), o2.length());
            }
            return result;
        })).toArray(String[]::new);
    }


    private List<String> completeStructure(String[] departs) {
        List<String> list = new ArrayList<>(Arrays.asList(departs));
        Set<String> allDeparts = new HashSet<>();
        for (String currentDept : list) {
            StringBuilder newDept = new StringBuilder();
            String[] temp = currentDept.split("\\\\");
            for (String string : temp) {
                newDept.append(string);
                allDeparts.add(newDept.toString());
                newDept.append("\\");
            }
        }
        return new ArrayList<>(allDeparts);
    }
}
