package org.example.hw2.myLinkedList;

import org.example.hw2.MyList;

public class TestMyLinkedList {
    public static void main(String[] args) {
        MyList<String> myList = new MyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            myList.add("value " + i);
        }
//        System.out.println(myList.get(0));
//        System.out.println(myList.get(9));
//        System.out.println(myList.indexOf("value 9"));
//        System.out.println(myList.indexOf("value 0"));
//        System.out.println(myList.size());
//        myList.add(5, "newValue");
//        System.out.println(myList.get(10));
//        System.out.println(myList.size());
//        System.out.println(myList);
//        myList.clear();
        System.out.println(myList);
        myList.remove("value 2");
        System.out.println(myList);
        System.out.println(myList.get(3));
    }
}
