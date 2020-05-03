package cyc.interfaces.okHttp;

import cyc.interfaces.constants.Methods;
import okhttp3.*;

import java.io.IOException;

/**
 * @author Bruce.Chen
 * @version 1.0
 * @date 2020/5/3 6:48
 */
public class HttpUtils {

    static OkHttpClient client = new OkHttpClient();

    public static Request get(String url, String params) {
        return new Request.Builder().url(url + params).get().build();
    }

    public static Request post(String url, String params) {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody requestBody = RequestBody.create(mediaType, params);
        return new Request.Builder().url(url).post(requestBody).build();
    }


    public static Response call(Methods method, String url, String params) {
        Request request = null;
        switch (method) {
            case GET:
                request = get(url, params);
                break;
            case HEAD:
                break;
            case POST:
                request = post(url, params);
                break;
            case PUT:
                break;
            case DELETE:
                break;
            case CONNECT:
                break;
            case OPTIONS:
                break;
            case TRACE:
                break;
            case PATCH:
                break;
            default:
                break;
        }
        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
