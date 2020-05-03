package cyc.testng.cases;

import cyc.interfaces.constants.Methods;
import cyc.interfaces.httpClient.HttpRequests;
import cyc.interfaces.okHttp.HttpUtils;
import okhttp3.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author Bruce.Chen
 * @version 1.0
 * @date 2020/5/4 1:04
 */
public class PostCases {

    private String addUrl, searchUrl;
    private ResourceBundle bundle;

    @BeforeTest
    public void beforeTest() {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        addUrl = bundle.getString("BASE_URL") + "account/save";
    }

    @Test(dataProvider = "input")
    public void okPost(String name, double money) {
        String params = "name=" + name + "&money=" + money;
        Response response = HttpUtils.call(Methods.POST, addUrl, params);
    }

    @Test(dataProvider = "input")
    public void clientPost(String name, double money) throws IOException, JSONException {
        String params = "name=" + name + "&money=" + money;
        HttpClient client = new DefaultHttpClient();
        //添加参数
//        JSONObject param = new JSONObject();
//        param.put("name", name);
//        param.put("money", money);
//        System.out.println(param);
        //定义一个 response，用来接收结果，类型是HttpResponse
        HttpResponse response = client.execute(HttpRequests.getRequest(Methods.POST, addUrl, params));
    }

    @DataProvider(name = "input")
    public Object[][] datas(Method method) {
        if (method.getName().equals("okPost")) {
            return new Object[][]{{"Mate40", 7000},
                    {"P40", 6000}
            };
        } else if (method.getName().equals("clientPost")) {
            return new Object[][]{{"一加8", 5000},
                    {"Find", 6000}
            };
        }
        return null;
    }

    @AfterTest
    public void afterTest() throws IOException {
        HttpClient client = new DefaultHttpClient();
        searchUrl = bundle.getString("BASE_URL") + "account/findAll";
        HttpResponse response = client.execute(HttpRequests.getRequest(Methods.GET, searchUrl, ""));
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
    }
}
