package cyc.testng.cases;

import cyc.interfaces.constants.Methods;
import cyc.interfaces.httpClient.HttpRequests;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author Bruce.Chen
 * @version 1.0
 * @date 2020/5/3 20:40
 */
public class BaiduCase {
    @Test
    public void visitBaidu() throws IOException {
        //定义一个result  用来存放我们的结果
        String result;
        String url = "http://www.baidu.com";
        HttpClient client = new DefaultHttpClient();
        //定义一个 response，用来接收结果，类型是HttpResponse
        HttpResponse response = client.execute(HttpRequests.getRequest(Methods.GET, url, ""));
        //getEntity()获取到响应的全体信息，返回的是HttpEntity,用EntityUtils工具把结果转换成字符串
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        Assert.assertTrue(result.contains("STATUS OK"));
        System.out.println(result);
    }
}
