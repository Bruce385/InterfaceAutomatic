package cyc.testng.cases;

import cyc.interfaces.constants.Weather;
import cyc.interfaces.constants.Methods;
import cyc.interfaces.okHttp.HttpUtils;
import cyc.interfaces.okHttp.JsonParse;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author Bruce.Chen
 * @version 1.0
 * @date 2020/5/3 7:31
 */
public class WeatherCases {

    @Test(dataProvider = "datas")
    public void getCityWeather(String cityName, int cityCode) throws IOException {
        Response response = HttpUtils.call(Methods.GET, Weather.BASE_URL, cityCode + Weather.SUFFIX);
        //System.out.println(response.code() + "\n" + response.headers() + "\n" + response.body().string());
        String jsonString = response.body().string();
        System.out.println(jsonString);
        String weatherInfo = JsonParse.getJsonValue(jsonString, "weatherinfo");
        String city = JsonParse.getJsonValue(weatherInfo, "city");
        System.out.println("用例结果: resultCode=>expected: " + cityName + " ,actual: " + city);
        Assert.assertEquals(city, cityName);
    }

    @DataProvider
    public Object[][] datas() {
        return new Object[][]{{"深圳", 101280601},
                {"北京", 101010100},
                {"上海", 101020100},
        };
    }

}
