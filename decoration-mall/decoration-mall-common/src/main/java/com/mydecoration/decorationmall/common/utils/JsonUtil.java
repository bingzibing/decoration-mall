package com.mydecoration.decorationmall.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转Json格式字符串
     *
     * @param obj 对象
     * @return Json格式字符串
     */
    public static <T> String obj2Json(T obj) {
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.warn("Parse Object to String error : {}", e.getMessage());
            throw new IllegalArgumentException();
        }
    }

    /**
     * 对象转Json格式字符串(格式化的Json字符串)
     *
     * @param obj 对象
     * @return 美化的Json格式字符串
     */
    public static <T> String obj2JsonPretty(T obj) {
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.warn("Parse Object to String error : {}", e.getMessage());
            throw new IllegalArgumentException();
        }
    }

    /**
     * 字符串转换为自定义对象
     *
     * @param str   要转换的字符串
     * @param clazz 自定义对象的class对象
     * @return 自定义对象
     */
    public static <T> T json2obj(String str, Class<T> clazz) {
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            log.warn("Parse String({}) to Object error : {}.", str, ExceptionUtils.getStackTrace(e));
            throw new IllegalArgumentException();
        }
    }

    /**
     * json字符串转换为map
     * @param jsonString
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> json2map(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonString, Map.class);
        } catch (IOException e) {
            log.warn("Parse String to Map error : {}", e.getMessage());
            throw new IllegalArgumentException();
        }
    }

    public static <T> T json2obj(String str, TypeReference<T> typeReference) {
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        } catch (IOException e) {
            log.warn("Parse String to Object error", e);
            throw new IllegalArgumentException();
        }
    }

    public static <T> T json2obj(String str, Class<?> collectionClazz, Class<?>... elementClazzes) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClazz, elementClazzes);
        try {
            return objectMapper.readValue(str, javaType);
        } catch (IOException e) {
            log.warn("Parse String to Object error : {}" + e.getMessage());
            throw new IllegalArgumentException();
        }
    }

    /**
     * Json数组字符串转化成list<T>
     *
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> json2listT(String jsonStr, Class<T> clazz) {
        //json字符串不能为空
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }
        //json字符串必须为数组的形式
        if (!(jsonStr.startsWith("[") && jsonStr.endsWith("]"))) {
            return null;
        }
        List<T> listT = new ArrayList<T>();
        try {
            //创建泛型对象
            T t =  clazz.newInstance();
            //利用类加载来加载泛型的具体类型
            Class<T> classT = (Class<T>) Class.forName(t.getClass().getName());
            List<Object> listObj = new ArrayList<Object>();
            //将数组节点中json字符串转换为object对象到Object的list集合
            listObj = new GsonBuilder().create().fromJson(jsonStr, new TypeToken<List<Object>>(){}.getType());
            //如果转换不成功返回空
            if (CollectionUtils.isEmpty(listObj)) {
                return null;
            }
            //将listObj的每一个元素中的json字符串转换为泛型并加入listT泛型集合返回
            listObj.stream().forEach(obj -> {
                T perT = new GsonBuilder().create().fromJson(new GsonBuilder().create().toJson(obj), classT);
                listT.add(perT);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listT;
    }

}
