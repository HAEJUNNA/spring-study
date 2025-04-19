package com.application.springstudy.study;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SortTest {

    Sort sort;
    // @BeforeAll 최초 한번만 수행 (static)
    @BeforeAll
    static void beforeAll() {
    }

    @BeforeEach
    void beforeEach() {
        //테스트 메서드가 실행전에 , 먼저 실행
        // 메서드 들이 실행 되기전에 먼저 수행되고 테스트 메서드 실행
        // 테스트 메서드는 실행 순서가 정해져있지 않음
        sort = new Sort();
        System.out.println(">>> sort=" + sort);
    }

    private static Stream<Arguments> generateData() {
        return Stream.of(
                // Arrays.asList 가변형,
                // List.of 불변
                Arguments.of(Arrays.asList("b", "dddd", "aaa", "cc"),
                        List.of("b", "cc", "aaa", "dddd")),
                Arguments.of(Arrays.asList("yyyy", "gg", "nnn", "l"),
                        List.of("l", "gg", "nnn", "yyyy"))
        );
    }
    // TestNg. 의존성 사용. junuit에서 제공함
    @DisplayName("기본 문자열 정렬 확인")
    @ParameterizedTest
    @MethodSource("generateData")
    void sortByLength(List<String> source, List<String> expected) {
        List<String> result = sort.sortByLength(source);
        assertThat(result).containsExactlyElementsOf(expected);
    }

    @DisplayName("문자열 길이가 동일하면, 입력된 문자열 순대로 정렬한다.")
    @Test
    void sortByLength3() {
        List<String> list = Arrays.asList("aaaa", "ddd", "bbb", "ccc");
        List<String> result = sort.sortByLength(list);
        //containsExactly는 순서까지 동일해야 한다.
        assertThat(result).containsExactly("ddd", "bbb", "ccc", "aaaa");
    }
}