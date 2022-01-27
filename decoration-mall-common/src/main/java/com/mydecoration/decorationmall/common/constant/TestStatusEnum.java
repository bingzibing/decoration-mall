package com.mydecoration.decorationmall.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chaibing
 * @Description 测试枚举类
 * @create 2022/1/26 15:16
 */
public enum TestStatusEnum {

    PENDING(0, "PENDING", "审批中"),
    APPROVED (1, "APPROVED", "通过"),
    REJECTED (2, "REJECTED", "拒绝"),
    CANCELED (3,"CANCELED", "撤回"),
    DELETED (4,"DELETED ", "删除"),

    ;

    TestStatusEnum(int code, String tag, String desc) {
        this.code = code;
        this.tag = tag;
        this.desc = desc;
    }

    private Integer code;
    private String tag;
    private String desc;

    private static Map<Integer, TestStatusEnum> cache = new HashMap<>();

    static {
        TestStatusEnum[] values = values();
        for (TestStatusEnum e : values) {
            cache.put(e.code, e);
        }
    }

    public static TestStatusEnum valueOfTag(String tag) {
        for (TestStatusEnum testEnum : TestStatusEnum.values()) {
            if (tag.equals(testEnum.tag)) {
                return testEnum;
            }
        }
        return null;
    }

    public static TestStatusEnum getByCode(Integer code) {
        return cache.get(code);
    }

    public Integer getCode() {
        return code;
    }

    public String getTag() {
        return tag;
    }

    public String getDesc() {
        return desc;
    }
}
