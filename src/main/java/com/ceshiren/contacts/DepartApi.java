/*
 * @Author: 霍格沃兹测试开发学社-盖盖
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */

package com.ceshiren.contacts;

import com.ceshiren.api.BaseApi;
import com.ceshiren.entity.DepartResponse;
import io.restassured.filter.Filter;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ceshiren.api.WeWorkAPI.CREATDEPART;
import static io.restassured.RestAssured.given;

//部门相关
public class DepartApi extends BaseApi {
    DepartResponse departResponse = new DepartResponse();

    //添加部门
    public DepartResponse add(String depart) throws IOException {
/*        List<Filter> filterList = new ArrayList<>();
        filterList.add(new TokenFilter());*/
        List<Filter> filterList = new ArrayList<>(){{
            add(new TokenFilter());
        }};
        Response response = run(filterList, CREATDEPART,null,"application/json; charset=UTF-8",null,depart);
        Integer errcode = response.path("errcode");
        String errmsg = response.path("errmsg");
        Integer id = 0==errcode ? response.path("id") : 0;
        logger.info("新增部门id:{}",id);
        departResponse.setErrcode(errcode);
        departResponse.setErrmsg(errmsg);
        departResponse.setId(id);
        return departResponse;
    }
}
