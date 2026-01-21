package me.shinsunyoung.springbootdeveloper;

import org.junit.jupiter.api.*;

public class JUnitCycleTest {
    @BeforeAll // 전체 테스트 시작 전에 1회 실행. 메서드는 static으로 선언함
    public static void beforeAll() {
        System.out.println("@BeforeAll - 전체 테스트 시작 전 1회 실행");
    }

    @BeforeEach // 테스트 케이스를 시작하기 전마다 실행
    public void beforeEach() {
        System.out.println("@BeforeEach - 각 테스트 시작 전 실행");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }

    @Test
    public void test3() {
        System.out.println("test3");
    }

    @AfterAll // 전체 테스트를 마치고 종료 전 1회 실행. 메서드는 static으로 선언
    public static void afterAll() {
        System.out.println("@AfterAll - 전체 테스트 종료 후 1회 실행");
    }

    @AfterEach // 테스트 케이스를 종료 후마다 실행
    public void afterEach() {
        System.out.println("@AfterEach - 각 테스트 종료 후 실행");
    }
}
