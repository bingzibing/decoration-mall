package com.mydecoration.decorationmall.dao.common;

import com.mydecoration.decorationmall.common.model.ReadableQueryEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.MapUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhangyu58
 * @Description 查询参数封装类
 * @create 2021-01-25 10:27
 */
@Log4j2
@Getter
@Setter
public class Query extends LinkedHashMap<String, Object> {

    public static final String LABEL_PAGE = "pageIndex";
    public static final String LABEL_PAGE_SIZE = "pageSize";
    private static final String LABEL_PAGE_OFFSET = "offset";


    public Query() {

    }

    public Query(ReadableQueryEntity params) {
        super(getFieldValues(params));
        handlePageAndPageSize();
        //log.info("query info: {}", this);
    }

    public Query(Map<String, Object> params) {
        super(params);
        handlePageAndPageSize();
        log.info("query info: {}", this);
    }

    /**
     * 对page和PageSize的处理
     */
    protected void handlePageAndPageSize() {
        //pageIndex pageSize
        //to pageIndex,offset,pageSize
        //计算出 offset

        /**
         * 当前页码
         */
        int page;
        /**
         * 每页条数
         */
        int pageSize;

        if (get(LABEL_PAGE) != null && get(LABEL_PAGE_SIZE) != null) {
            //分页参数
            page = MapUtils.getIntValue(this, LABEL_PAGE);
            pageSize = MapUtils.getIntValue(this, LABEL_PAGE_SIZE);
            if (this.get(LABEL_PAGE_OFFSET) == null) {
                this.put(LABEL_PAGE_OFFSET, (page - 1) * pageSize);
            }
            this.put(LABEL_PAGE, page);
            this.put(LABEL_PAGE_SIZE, pageSize);
        } else {
            //默认分页参数，不要改
            page = 1;
            pageSize = 10;

            this.put(LABEL_PAGE_OFFSET, (page - 1) * pageSize);
            this.put(LABEL_PAGE, page);
            this.put(LABEL_PAGE_SIZE, pageSize);
        }

    }


    public long getLong(String key) {
        return MapUtils.getLongValue(this, key);
    }

    public int getInt(String key) {
        return MapUtils.getIntValue(this, key);
    }

    private static Map<String, Object> getFieldValues(Object param) {
        if (param == null) {
            return new HashMap<>(2);
        }

        Map<String, Object> fvs = new HashMap<>(8);
        Class<?> currentClass = param.getClass();
        try {
            while (currentClass != null) {
                final Field[] declaredFields = currentClass.getDeclaredFields();
                for (final Field field : declaredFields) {
                    // 静态属性、常亮属性不添加
                    if (Modifier.isStatic(field.getModifiers()) ||
                            Modifier.isFinal(field.getModifiers())) {
                        continue;
                    }
                    field.setAccessible(true);
                    fvs.put(field.getName(), field.get(param));
                }
                currentClass = currentClass.getSuperclass();
            }
        } catch (Exception e) {
            log.error("clz={}, {}", param.getClass().getSimpleName(), e);
        }
        return fvs;
    }

    public static Query valueOf(ReadableQueryEntity params) {
        return valueOf(params,false);
    }

    public static Query valueOf(ReadableQueryEntity params, boolean deleted) {

        Query query = new Query(params);
        query.put("deleted", deleted ? 1 : 0);

        return query;
    }
}

