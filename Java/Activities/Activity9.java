package activities;

import java.util.ArrayList;

public class Activity9 {
    public static void main(String[] args) {
        ArrayList<String> myList = new ArrayList<String>();
        myList.add("Yellow");
        myList.add("Red");
        myList.add("White");
        myList.add("Black");
        myList.add("Orange");
        myList.add(2, "Purple");
        myList.add(1, "Pink");
        for (String s : myList) {
            System.out.print("\t"+s);
        }
        System.out.println();
        System.out.println(myList.get(3));
        System.out.println(myList.contains("Green"));
        System.out.println(myList.size());
        System.out.println("Item removed successfully? " + myList.remove("Black"));
        System.out.println(myList.size());

    }
}
