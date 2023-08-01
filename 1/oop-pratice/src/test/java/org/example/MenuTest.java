package org.example;

import org.assertj.core.api.Assertions;
import org.example.customer.Menu;
import org.example.customer.MenuItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MenuTest {
    @DisplayName("메뉴판에서 메뉴이름에 해당하는 메뉴를 반환한다.")
    @Test
    void chooseTest1() {
        Menu menu = new Menu(List.of(new MenuItem("돈까스", 5000), new MenuItem("냉면",7000)));
        MenuItem menuItem = menu.choose("돈까스");

        assertThat(menuItem).isEqualTo(new MenuItem("돈까스", 5000));
    }

    @DisplayName("메뉴판에 없는 메뉴를 선택할 시 메뉴를 반환한다.")
    @Test
    void chooseTest2() {
        Menu menu = new Menu(List.of(new MenuItem("돈까스", 5000), new MenuItem("냉면",7000)));

        assertThatCode(() -> menu.choose("치킨"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 메뉴 이름입니다.");
    }
}
