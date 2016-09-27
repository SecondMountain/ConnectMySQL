package course;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static Print.Print.println;

/**
 * Created by zyf on 2016/3/1.
 */
public class UniqueWorld {
    public static void main(String[] args) {
        //TODO write your code here
        Set<String> word = new TreeSet<String>(new TextFile("src/course/UniqueWorld.java","\\W+"));
        println(word);
        println("-------------------------------");
        List<String> words = new ArrayList<String>(new TextFile("src/course/UniqueWorld.java","\\W+"));
        println(words);

    }
}
