package activities;

import java.util.HashSet;
import java.util.Set;

public class Activity10 {
    public static void main(String[] args) {
        HashSet<String> hs =new HashSet<String>();
        hs.add("M");
        hs.add("B");
        hs.add("C");
        hs.add("A");
        hs.add("M");
        hs.add("X");

        System.out.println("Original HashSet: " + hs);
        System.out.println(hs.size());
        System.out.println(hs.remove("C"));
        System.out.println(hs.remove("F"));
        System.out.println(hs.contains("C"));
        System.out.println(hs);
    }
}

