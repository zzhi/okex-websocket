package com.biup.okex;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhangzhii on 2018/3/15.
 */
public class RestTemplateTest {


    @Test
    public void usdt() {

        List<String> ids = Arrays.asList("1,2,3".split(","));
        List<String> ids2 = Arrays.asList("1".split(","));
        RestTemplate restTemplate = new RestTemplate();
        JSONObject jsonParam = null;
//        jsonParam.put("addr", "177H1vo7piz7CX3fX8fmeT1PAJRymQ5nJN");
//        String json = jsonParam.toJSONString();
//        System.out.println(json);
        String value = "addr=177H1vo7piz7CX3fX8fmeT1PAJRymQ5nJN";
        try {

            String url = "https://api.omniexplorer.info/v1/address/addr/";
            // set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>(value, headers);

            // send request and parse result
            ResponseEntity<String> loginResponse = restTemplate
                    .exchange(url, HttpMethod.POST, entity, String.class);
            if (loginResponse.getStatusCode() == HttpStatus.OK) {
                String json = loginResponse.getBody();
                jsonParam = JSON.parseObject(json);
            } else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                // nono... bad credentials
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    public void ttt() {
        //'addr' field not supplied
        String url = "https://api.omniexplorer.info/v1/address/addr/";
        String value = "addr=177H1vo7piz7CX3fX8fmeT1PAJRymQ5nJN";
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>(value, headers);

            Object response = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);

            System.out.println(response);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    /**
     * 实体接收，可以做验证
     */
    @Test
    public void register() {
        RestTemplate restTemplate = new RestTemplate();

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("state", "1");//状态
        jsonParam.put("compCode", "2");//公司代码
        jsonParam.put("meterCode", "1");//表钢号
        jsonParam.put("firmCode", "1");//厂商编码
        jsonParam.put("materielCode", "1");//表计物料
        jsonParam.put("protocol", "1");//通讯协议类型
        jsonParam.put("readingUnit", "1");
        jsonParam.put("cityId", "1");
        String json = jsonParam.toJSONString();
        System.out.println(json);
        try {

            String url = "http://lpgw.ecej.com/livingpay/register";
            // set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(json, headers);

            // send request and parse result
            ResponseEntity<String> loginResponse = restTemplate
                    .exchange(url, HttpMethod.POST, entity, String.class);
            if (loginResponse.getStatusCode() == HttpStatus.OK) {

            } else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                // nono... bad credentials
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    public void test2_0() {
        RestTemplate restTemplate = new RestTemplate();

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("state", "1");//状态
        jsonParam.put("compCode", "2");//公司代码
        jsonParam.put("meterCode", "1");//表钢号
        jsonParam.put("firmCode", "1");//厂商编码
        jsonParam.put("materielCode", "1");//表计物料
        jsonParam.put("protocol", "1");//通讯协议类型
        jsonParam.put("readingUnit", "1");
        jsonParam.put("cityId", "1");
        String json = jsonParam.toJSONString();
        System.out.println(json);
        try {

            String url = "http://localhost:8080/test2";
            // set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(json, headers);

            // send request and parse result
            ResponseEntity<String> loginResponse = restTemplate
                    .exchange(url, HttpMethod.POST, entity, String.class);
            if (loginResponse.getStatusCode() == HttpStatus.OK) {

            } else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                // nono... bad credentials
            }
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * json 字符串方式接收，需要controller解析对象
     */
    @Test
    public void test2_1() {
        RestTemplate restTemplate = new RestTemplate();

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("state", "1");//状态
        jsonParam.put("compCode", "2");//公司代码
        jsonParam.put("meterCode", "1");//表钢号
        jsonParam.put("firmCode", "1");//厂商编码
        jsonParam.put("materielCode", "1");//表计物料
        jsonParam.put("protocol", "1");//通讯协议类型
        jsonParam.put("readingUnit", "1");
        jsonParam.put("cityId", "1");
        String json = jsonParam.toJSONString();
        System.out.println(json);
        try {

            String url = "http://localhost:8080/test2";
            // send request and parse result
            String result = restTemplate.postForObject(url, json, String.class);
            System.out.println(result);
        } catch (Exception e) {
            throw e;
        }
    }


    @Test
    public void test3() {

    }
}
