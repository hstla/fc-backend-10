package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class UserTest {

    @DisplayName("패스워드를 초기화한다.")
    @Test
    public void passwordTest1() throws Exception {
        // given
        User user = new User();
        // when
        user.initPassword(() -> "abcdefgh");
        // then
        assertThat(user.getPassword()).isNotNull();
    }

    @DisplayName("패스워드가 요구사항에 부합되지 않아 초기화가 되지 않는다.")
    @Test
    public void passwordTest2() throws Exception {
        // given
        User user = new User();
        // when
        user.initPassword(new WrongFixedPasswordGenerator());
        // then
        assertThat(user.getPassword()).isNull();
    }
}