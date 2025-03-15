package com.application.springstudy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * packageName    : com.application.springstudy
 * fileName       : Sort
 * author         : NAHAEJUN
 * date           : 2025-03-15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-15        NAHAEJUN              최초생성
 */
public class Sort {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("b","dddd","aaaa","cc");

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        }); // util클래스에는 명을 s를 붙여서 만든다

        list.forEach(System.out::println);
    }
}
