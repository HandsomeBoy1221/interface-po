/*
 * @Author: 霍格沃兹测试开发学社-盖盖
 * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
 */

package com.ceshiren.contacts;

import com.ceshiren.entity.TokenResponse;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.io.IOException;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

public class GetTokenApiTest {
    static final Logger logger = getLogger(lookup().lookupClass());

    @Test
    public void getToken() throws IOException {
        GetTokenApi getTokenApi = new GetTokenApi();
        TokenResponse getTokenResponse = getTokenApi.getToken();
        logger.info("access_token:{}", getTokenResponse.getAccessToken());
        assertAll(
                () -> assertEquals(0,getTokenResponse.getErrcode()),
                () -> assertEquals("ok",getTokenResponse.getErrmsg())
        );
    }
}
