package com.example.lib.d01start;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JavaCode {
    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("aaa","bbb","ccc"));
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
