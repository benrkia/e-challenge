package com.benrkia;

import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<Integer> list = Arrays.asList(-9, -18, 0, 25, 4);
        Integer var = list.stream().min(Integer::compare).get();

        System.out.print(var);
    }
}
