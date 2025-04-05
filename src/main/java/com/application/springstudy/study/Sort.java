package com.application.springstudy.study;

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


    public List<String> sortByLength(List<String> list) {
        Collections.sort(list, Comparator.comparingInt(String::length));
        return list;
    }


}
