package com.test;

import cn.wanghaomiao.xpath.model.JXDocument;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import java.io.IOException;
import java.util.List;

public class TestJsoup {
    @Test
    public void test1(){
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient httpClient = httpClientBuilder.build();
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36");
        try {
            // 执行get请求
            HttpResponse httpResponse = httpClient.execute(httpGet);
            // 获取响应消息实体
            HttpEntity entity = httpResponse.getEntity();
            String content = EntityUtils.toString(entity,"utf8");
//            System.err.println("content="+content);
            Document doc = Jsoup.parse(content, "UTF-8");
            Elements u_sp = doc.getElementById("u1").children();
            for (Element u:u_sp) {
                System.err.println(u.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流并释放资源
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test2(){
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient httpClient = httpClientBuilder.build();
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36");
        try {
            // 执行get请求
            HttpResponse httpResponse = httpClient.execute(httpGet);
            // 获取响应消息实体
            HttpEntity entity = httpResponse.getEntity();
            String content = EntityUtils.toString(entity,"utf8");
//            System.err.println("content="+content);
            Document doc = Jsoup.parse(content, "UTF-8");
            JXDocument jxDocument = new JXDocument(doc);
            List<Object> rs = jxDocument.sel("//*[@id=\"u1\"]/a/text()");
            for (Object o:rs){
            if (o instanceof Element){
                int index = ((Element) o).siblingIndex();
                System.out.println("index="+index);
            }
                System.out.println("o="+o.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流并释放资源
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

