/*
 * @Author: 霍格沃兹测试开发学社-盖盖
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */

package com.ceshiren.contacts;

import com.ceshiren.api.BaseApi;
import com.ceshiren.entity.TokenResponse;
import com.ceshiren.util.MapperUtil;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

import static com.ceshiren.api.WeWorkAPI.GETTOKEN;
import static io.restassured.RestAssured.given;
import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class GetTokenApi extends BaseApi {
    TokenResponse getTokenResponse = new TokenResponse();
    //log日志

    //封装一个方法，为获取token的方法
    //调用的URL：GETTOKEN
    public TokenResponse getToken() throws IOException {
        logger.info("调用GetTokenAPI");
        //yaml文件读取解析  HashMap<String, HashMap<String, HashMap<String,String>>>
        MapperUtil<HashMap<String, HashMap<String, HashMap<String,Object>>>> mapMapperUtil
                = new MapperUtil<HashMap<String, HashMap<String, HashMap<String,Object>>>>();
        HashMap<String, HashMap<String, HashMap<String, Object>>> readValue = mapMapperUtil.getReadValue("src/test/resources/token.yaml");

        logger.info("token解析对象为：{}",readValue);
        HashMap<String, Object> tokenQuerys = readValue.get("master").get("contacts");
        //1.token 请求
//        (String URL,
//                HashMap<String, Object> querys,
//                String contentType,
//                HashMap<String, Object> headers)
        //请求参数的准备
        Response response = run(null, GETTOKEN, tokenQuerys, null, null);
        /*Response response = given()
                        .log().all()
                        .queryParams(tokenQuerys)
                         //发送具体请求
                    .when()
                        .request("get",GETTOKEN)
                    //获取响应结果
                    .then()
                        .log().all()
                        //校验HTTPcode码200;
                        .statusCode(200)
                        .extract().response();*/

        Integer errcode = response.path("errcode");//gpath
        String errmsg = response.path("errmsg").toString();
        //三目运算
        String accessToken = 0==errcode ? response.path("access_token").toString() : errmsg ;

        //2.token 的文件写入
        HashMap<String,Object> token = new HashMap<>();
        token.put("access_token",accessToken);
        //json文件保存
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        //new File(路径)     -------->     Paths.get(路径).toFile()
        mapper.writeValue(Paths.get("token.json").toFile(), token);
        getTokenResponse.setErrcode(errcode);
        getTokenResponse.setErrmsg(errmsg);
        getTokenResponse.setAccessToken(accessToken);
        return getTokenResponse;
    }
}
