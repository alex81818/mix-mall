package com.calvin.mixmall.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Jackson Json处理工具类
 */
@Slf4j
public final class JsonUtil {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将对象转为json字符串
     *
     * @param value 待转换的对象
     * @return json字符串
     * @throws JsonProcessingException 转换异常
     */
    public static final String toJsonString(Object value) {
        if (value == null) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat(DEFAULT_DATE_FORMAT));
        // objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("json反序列化异常", e);
        }
    }

    /**
     * json字符串转对象
     *
     * @param context json字符串
     * @param clazz   类型
     * @param <T>     类型
     * @return 返回对象
     */
    public static <T> T parseObject(String context, Class<T> clazz) {
        if (context == null || "".equals(context) || clazz == null) {
            return null;
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat(DEFAULT_DATE_FORMAT));
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(context, clazz);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("json反序列化异常", e);
        }
    }

    /**
     * json字符串转list集合
     *
     * @param context json字符串
     * @param clazz   集合中的类型
     * @param <T>     集合中的类型
     * @return list集合
     */
    public static <T> List<T> parseList(String context, Class<T> clazz) {
        if (context == null || "".equals(context) || clazz == null) {
            return null;
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat(DEFAULT_DATE_FORMAT));
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
            return objectMapper.readValue(context, javaType);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("json反序列化异常", e);
        }
    }

    /**
     * json字符串转map对象
     *
     * @param context  json字符串
     * @param keyClazz key类型
     * @param valClazz value类型
     * @param <K>      key类型
     * @param <V>      value类型
     * @return map对象
     */
    public static <K, V> Map<K, V> parseMap(String context, Class<K> keyClazz, Class<V> valClazz) {
        if (context == null || "".equals(context) || keyClazz == null || valClazz == null) {
            return null;
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat(DEFAULT_DATE_FORMAT));
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Map.class, keyClazz, valClazz);
            return objectMapper.readValue(context, javaType);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("json反序列化异常", e);
        }
    }

    /**
     * Bean对象转JSON
     *
     * @param object
     * @param dataFormatString
     * @return
     */
    public static final String beanToJson(Object object, String dataFormatString) {
        if (object != null) {
            if (StringUtils.isEmpty(dataFormatString)) {
                return JSONObject.toJSONString(object);
            }
            return JSON.toJSONStringWithDateFormat(object, dataFormatString);
        } else {
            return null;
        }
    }

}
