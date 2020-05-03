package cyc.interfaces.httpClient;

import cyc.interfaces.constants.Methods;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

/**
 * @author Bruce.Chen
 * @version 1.0
 * @date 2020/5/3 21:21
 */
public class HttpRequests {


    public static HttpRequestBase getRequest(Methods method, String url, String params) {

        switch (method) {
            case CONNECT:
                break;
            case DELETE:
                break;
            case GET:
                return new HttpGet(url + params);
            case HEAD:
                break;
            case OPTIONS:
                break;
            case PATCH:
                break;
            case POST:
                HttpPost post = new HttpPost(url);
                StringEntity entity = null;
                try {
                    entity = new StringEntity(params, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                post.setEntity(entity);
                return post;
            case PUT:
                break;
            case TRACE:
                break;
        }
        return null;
    }


}
