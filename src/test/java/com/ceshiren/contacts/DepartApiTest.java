/*
 * @Author: 霍格沃兹测试开发学社-盖盖
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */

package com.ceshiren.contacts;

import com.ceshiren.entity.DepartResponse;
import com.ceshiren.util.FakerUtil;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

public class DepartApiTest {
    DepartApi departApi = new DepartApi();

    static final Logger logger = getLogger(lookup().lookupClass());

    @Test
    public void add() throws IOException {
        //添加部门请求的参数需要放在测试用例内
        DocumentContext context = JsonPath.parse(new File("src/test/resources/depart/add.json"));
        context.set("$.name", FakerUtil.getDepartName() + FakerUtil.getNum(10,100));
        context.set("$.order", FakerUtil.getNum(10,100));

        String depart = context.jsonString();
        logger.info("替换后的depart：{}",depart);

        //添加部门接口业务逻辑在类的方法内
        DepartResponse departResponse = departApi.add(depart);

        //断言使用的是封装的响应的成员变量
        assertAll(
                () -> assertEquals(0,departResponse.getErrcode()) ,
                () -> assertEquals("created",departResponse.getErrmsg())
        );
    }
}
