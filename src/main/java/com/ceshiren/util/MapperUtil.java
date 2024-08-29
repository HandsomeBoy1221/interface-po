/*
 * @Author: 霍格沃兹测试开发学社-盖盖
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */

package com.ceshiren.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class MapperUtil<T> implements  java.io.Serializable{
    private static final long serialVersionUID = 2L; // as of 2.9
    ObjectMapper objectMapper;

    T readValue ;


    public T getReadValue(String pathName){
        try {
            //兼容json yaml csv 解析
            if(pathName.endsWith(".json")){
                objectMapper = new ObjectMapper(new JsonFactory());
            }else if (pathName.endsWith(".yaml") | pathName.endsWith(".yml")){
                objectMapper = new ObjectMapper(new YAMLFactory());
            }else if(pathName.endsWith(".csv") ){
                objectMapper = new ObjectMapper(new CsvFactory());
            }
            TypeReference<T> valueTypeRef =
                    new TypeReference<T>() {};
            //解析数据
            readValue = objectMapper.readValue( new File(pathName), valueTypeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readValue;
    }

}