package org.example;

public class User {
    private String password;

    public void initPassword(PasswordGenerator passwordGenerator) {
        // As-is 방식 // 내부에서 생성하면 강한 결합을 받을 수 밖에 없음.
//        RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();

        // to-be 방식
        String password = passwordGenerator.generatePassword();
        /**
         * 비밀번호는 최소 8자 이상 12자 이하여야 한다.
         */
        if (password.length() >= 8 && password.length() <= 12) {
            this.password = password;
        }



    }

    public String getPassword() {
        return password;
    }
}
