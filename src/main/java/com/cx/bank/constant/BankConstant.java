package com.cx.bank.constant;

/**
 * @ClassName Identity
 * @Description 用户身份枚举类
 * @Author Bruce Xu
 * @Date 2021/9/9 20:11
 * @Version 1.0
 */
public class BankConstant {
    public enum Identity {
        /**
         * 管理员身份
         */
        ADMIN(0, "admin"),
        /**
         * 用户身份
         */
        USER(1, "user");

        private int code;
        private String identity;

        Identity(int code, String identity) {
            this.code = code;
            this.identity = identity;
        }

        public int getCode() {
            return code;
        }

        public String getIdentity() {
            return identity;
        }
    }

    public enum status {
        /**
         * 激活状态
         */
        ACTIVE(0, "active"),
        /**
         * 冻结状态
         */
        FROZEN(1, "frozen");

        private int code;
        private String identity;

        status(int code, String identity) {
            this.code = code;
            this.identity = identity;
        }

        public int getCode() {
            return code;
        }

        public String getIdentity() {
            return identity;
        }
    }


}
