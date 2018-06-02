package demo007;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpClientDemo1 {
	public static void main(String args[]) {
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
			// 响应状态
			System.out.println("status:" + httpResponse.getStatusLine());
			// 判断响应实体是否为空
			if (entity != null) {
				System.out.println("contentEncoding:" + entity.getContentEncoding());
				System.out.println("response content:" + EntityUtils.toString(entity));
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