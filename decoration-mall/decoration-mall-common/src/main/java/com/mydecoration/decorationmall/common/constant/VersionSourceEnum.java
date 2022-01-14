package com.mydecoration.decorationmall.common.constant;

public enum VersionSourceEnum {
    UNKNOWN(0, "UNKNOWN", "未知"),
    CORGI_ATHENA(1, "corgi_athena","柯基平台"),
    MANUAL(10, "MANUAL", "手动上传"),
    ;

    VersionSourceEnum(int code, String tag, String desc ) {
        this.code = code;
        this.tag = tag;
        this.desc = desc;
    }

    public static VersionSourceEnum valueOfCode(int code) {
        for (VersionSourceEnum sourceEnum : VersionSourceEnum.values()) {
            if (code == sourceEnum.code) {
                return sourceEnum;
            }
        }
        return null;
    }

    private Integer code;
    private String tag;
    private String desc;

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
