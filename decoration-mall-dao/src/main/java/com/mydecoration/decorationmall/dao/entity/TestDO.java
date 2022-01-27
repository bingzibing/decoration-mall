package com.mydecoration.decorationmall.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chaibing
 * @Description
 * @create 2022/1/27 15:28
 */
@Data
public class TestDO implements Serializable {
    /**
     * 自增id
     */
    private Long id;

    /**
     * 调用放名
     */
    private String clientName;

    /**
     * 设备号码
     */
    private Long deviceId;

    /**
     * app_id
     */
    private String appId;

    /**
     * app_key
     */
    private String appKey;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 1-无效；0-有效
     */
    private Integer deleted;

    private static final long serialVersionUID = 1L;
}
