package org.example.hw2.myArrayList;

import org.example.hw2.MyList;

import java.util.ArrayList;
import java.util.List;

public class TestMyArrayList {

    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>();
        MyList<String> list = new MyArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("value" + i);
        }
//        System.out.println(list.get(1));
//        System.out.println(list.get(19));
//        System.out.println(list.size());
//        list.add(15, "newValue");
//        System.out.println(list.get(15));
//        System.out.println(list.get(19));
//        System.out.println(list.size());
//        System.out.println(list.indexOf("newValue"));
//        list.replace(15, "superNewValue");
//        System.out.println(list.get(15));


        System.out.println(list);
        list.removeByIndex(0);
        System.out.println(list);
        list.remove("value1");
        System.out.println(list);
    }
}
