package com.application.springstudy.study;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sort {
        public List<String> sortByLength(List<String> list) {
            Collections.sort(list, Comparator.comparingInt(String::length));
            return list;
        }
    }
