package com.application.springstudy.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.application.springstudy.study
 * fileName       : SortTest
 * author         : NAHAEJUN
 * date           : 2025-04-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-04-05        NAHAEJUN              최초생성
 */
class SortTest {

    Sort sort;
    // @BeforeAll 최초 한번만 수행 (static)
    // @BeforeAll (){}
    @BeforeEach
    void BeforeEach() {
        //테스트 메서드가 실행전에 , 먼저 실행
        // 메서드 들이 실행 되기전에 먼저 수행되고 테스트 메서드 실행
        // 테스트 메서드는 실행 순서가 정해져있지 않음
        sort = new Sort();
    }

    private static Stream<Arguments> data() {
        return Stream.of(
                // Arrays.asList 가변형,
                // List.of 불변
                Arguments.of(Arrays.asList("b","dddd","aaa","cc"),
                        List.of("b","cc","aaa","dddd")), //불변형),
                Arguments.of(Arrays.asList("yyyy","gg","nnn","l"),
                        List.of())
        );

    }

    // TestNg. 의존성 사용. junuit에서 제공함
    @ParameterizedTest
    @ValueSource(strings = {"a","b","c"})
    @CsvSource(value = {"a,x", "b,y","c,z"}, delimiter = ':')
    void sortByLength(String input1 ,String input2) {
        List<String> list = Arrays.asList("b","dddd","aaaa","cc");
        Sort sort = new Sort();
        List<String> result = sort.sortByLength(list);

        Assertions.assertThat(result).containsExactly("b","cc","dddd","aaaa");

    }

    @Test
    void sortByLength2() {
        List<String> list = Arrays.asList("yyyy","gg","nnn","l");
        Sort sort = new Sort();
        List<String> result = sort.sortByLength(list);
        Assertions.assertThat(result).containsExactly("l","gg","nnn","yyyy");
    }

    @Test
    @DisplayName("문자열 길이가 동일하면, 입력된 문자열 순서대로 정렬한다.")
    void sortByLength3() {
        List<String> list = Arrays.asList("aaaa","ddd","bbb","ccc");
        Sort sort = new Sort();
        List<String> result = sort.sortByLength(list);
        //containsExactly는 순서까지 동일해야 한다.
        Assertions.assertThat(result).containsExactly("bbb","ccc","ddd","aaaa");
    }
}