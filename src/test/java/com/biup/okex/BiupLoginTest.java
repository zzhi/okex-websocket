package com.biup.okex;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class BiupLoginTest {
    String country = "86";
    String mobile = "18515496613";
    String password = "qazwsx123";
    String HOST = "http://localhost:8080";
    RestTemplate restTemplate = new RestTemplate();

    /**
     * 登陆测试
     */
    @Test
    public void loginTest() {
        String token = getToken();
    }

    /**
     * 下单
     */
    @Test
    public void createOrderTest() {

        String uuid = UUID.randomUUID().toString().replace("-", "");
        String token = getToken();
        Long time = (new Date()).getTime();
        String symbol = "KCS-USDT";
        BigDecimal price = new BigDecimal(0.9).setScale(3, BigDecimal.ROUND_DOWN);
        BigDecimal volume = new BigDecimal(1.1).setScale(2, BigDecimal.ROUND_DOWN);
        String side = "BUY";
        Integer type = 1;

        Map<String, Object> params = new HashMap<>(16);
        params.put("price", price);
        params.put("symbol", symbol);
        params.put("volume", volume);
        params.put("side", side);
        params.put("type", type);
        params.put("time", time);
        params.put("token", token);
        String sign = getSign(params);

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("symbol", symbol);
        jsonParam.put("price", price);
        jsonParam.put("volume", volume);
        jsonParam.put("side", side);
        jsonParam.put("type", type);
        jsonParam.put("token", token);
        jsonParam.put("time", time);
        jsonParam.put("sign", sign);
        String json = jsonParam.toJSONString();
        System.out.println("下单请求:" + json);
        try {
            String url = HOST + "/api/create_order?symbol=" + symbol + "&price=" + price + "&volume=" + volume + "&side=" + side + "&type=" + type + "&token=" + token + "&time=" + time + "&sign=" + sign;
            // set headers
            HttpHeaders headers = getHeaders();
            HttpEntity<String> entity = new HttpEntity<String>(json, headers);
            ResponseEntity<String> response = restTemplate
                    .exchange(url, HttpMethod.POST, entity, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                String body = response.getBody();
                System.out.println("下单返回数据:" + body);

            }
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * 撤单
     */
    @Test
    public void cancelOrderTest() {

        String token = getToken();
        Long time = (new Date()).getTime();
        Long order_id = 57959L;
        String symbol = "BNB-USDT";

        Map<String, Object> params = new HashMap<>(16);
        params.put("order_id", order_id);
        params.put("symbol", symbol);
        params.put("token", token);
        params.put("time", time);
        String sign = getSign(params);

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("order_id", order_id);
        jsonParam.put("symbol", symbol);
        jsonParam.put("token", token);
        jsonParam.put("time", time);
        jsonParam.put("sign", sign);
        String json = jsonParam.toJSONString();
        System.out.println("撤单请求:" + json);
        try {
            String url = HOST + "/api/cancel_order?order_id=" + order_id + "&symbol=" + symbol + "&token=" + token + "&time=" + time + "&sign=" + sign;
            // set headers
            HttpHeaders headers = getHeaders();
            HttpEntity<String> entity = new HttpEntity<String>(json, headers);
            ResponseEntity<String> response = restTemplate
                    .exchange(url, HttpMethod.POST, entity, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                String body = response.getBody();
                System.out.println("撤单返回数据:" + body);

            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 登陆
     */
    private String getToken() {
        String token = "";
        Long time = (new Date()).getTime();
        Map<String, Object> params = new HashMap<>(16);
        params.put("country", country);
        params.put("mobile", mobile);
        params.put("password", password);
        params.put("time", time);
        String sign = getSign(params);

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("country", country);
        jsonParam.put("mobile", mobile);
        jsonParam.put("password", password);
        jsonParam.put("time", time);
        jsonParam.put("sign", sign);
        String json = jsonParam.toJSONString();
        System.out.println("登陆数据:" + json);
        try {
            String url = HOST + "/login_mobile?country=" + country + "&mobile=" + mobile + "&password=" + password + "&time=" + time + "&sign=" + sign;
            // set headers
            HttpHeaders headers = getHeaders();
            HttpEntity<String> entity = new HttpEntity<String>(json, headers);

            // send request and parse result
            ResponseEntity<String> response = restTemplate
                    .exchange(url, HttpMethod.POST, entity, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                String body = response.getBody();
                System.out.println("返回数据:" + body);
                JSONObject object = JSON.parseObject(body);
                if (object.containsKey("code") && object.containsKey("msg") && object.containsKey("data")) {
                    String code = object.getString("code");
                    String msg = object.getString("msg");
                    JSONObject data = object.getJSONObject("data");
                    if (code.equals("0") && msg.equals("成功")) {
                        if (data.containsKey("expire") && data.containsKey("token")) {
                            String expire = data.getString("1534916424569");
                            token = data.getString("token");

                        }

                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return token;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("Build-CU", "120");
        headers.set("SysVersion-CU", "7.0");
        headers.set("SysSDK-CU", "24");
        headers.set("Channel-CU", "app");
        headers.set("Mobile-Model-CU", "huawei");
        headers.set("UUID-CU", "thisismytest001");
        headers.set("Platform-CU", "android");
        headers.set("Network-CU", "wifi");
        headers.set("Language", "zh_CN");
        return headers;
    }

    private String getSign(Map<String, Object> params) {
        String secret = "jiaoyisuo@2017";
        Map<String, Object> sortedParams = new TreeMap<>(params);
        Set<Map.Entry<String, Object>> entrys = sortedParams.entrySet();
        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        for (Map.Entry<String, Object> param : entrys) {
            //去掉签名字段
            if ("sign".equals(param.getKey())) {
                continue;
            }
            basestring.append(param.getKey());
            if (param.getValue() != null) {
                basestring.append(param.getValue().toString());
            }
        }
        basestring.append(secret);
        // 使用MD5对待签名串求签
        String curSign = MD5Util.getMD5(basestring.toString());
        return curSign;
    }

}
