package activities;

import java.util.HashMap;
import java.util.Map;

public class Activity11 {
    public static void main(String[] args) {
        Map<Integer,String> colours = new HashMap<Integer, String>();
        colours.put(1, "Yellow");
        colours.put(2, "Red");
        colours.put(3, "White");
        colours.put(4, "Black");
        colours.put(5, "Orange");

        System.out.println(colours);
        System.out.println(colours.remove(3));
        System.out.println(colours.containsValue("Green"));
        System.out.println(colours.size());

    }
}
