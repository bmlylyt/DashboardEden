package com.example.UserCeneter.common.strategy;

public enum OperatorStrategyEnum {

    SUCCESS("SUCCESS", "success"),

    FAIL("FAIL", "fail"),

    UN_KNOWN("UN_KNOWN", "un_known"),

    EMAIL_FAIL("EMAIL_FAIL", "fail sending");

    /**
     * Operation code
     */
    private String code;

    /**
     * Operation description
     */
    private String desc;

   OperatorStrategyEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
