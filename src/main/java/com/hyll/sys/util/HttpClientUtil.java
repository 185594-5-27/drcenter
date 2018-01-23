package com.hyll.sys.util;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/*
* 类描述：
* @auther linzf
* @create 2017/12/28 0028 
*/
public class HttpClientUtil {

    /**
     * 后台接口调用
     * @param uriAPI 接口地址
     * @param json 包含入参
     * @return
     */
    public static String doPost(String uriAPI,JSONObject json) {
        String result = "";
        CloseableHttpClient httpclient = null;

        try {
            HttpPost httpRequst = new HttpPost(uriAPI);// 创建HttpPost对象
            ByteArrayEntity bae = new ByteArrayEntity(AES.encrypt(json.toString().getBytes(StandardCharsets.UTF_8)));
            httpRequst.setEntity(bae);
            httpclient = HttpClients.createDefault();
            HttpResponse httpResponse = httpclient.execute(httpRequst);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);// 取出应答字符串
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            result = e.getMessage().toString();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            result = e.getMessage().toString();
        } catch (IOException e) {
            e.printStackTrace();
            result = e.getMessage().toString();
        }finally{
            if(httpclient != null){
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
