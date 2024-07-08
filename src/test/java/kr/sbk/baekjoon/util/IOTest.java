package kr.sbk.baekjoon.util;

import java.io.ByteArrayInputStream;

public abstract class IOTest {

    public void systemIn(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
}
