package com.lazy.httpclient.utils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
/**
 * 
 * @ClassName:  HttpRequest   
 * @Description:TODO(封装http请求的方法)   
 * @author:  zhanglun
 *     
 */
public class HttpRequest {

	private CloseableHttpClient httpClient;
	private CookieStore cookieStore;
	private HttpMethod method;
	private String contentType;
	private String destination;
	private String protocol = "http";
	private String host = "localhost";
	private Integer port;
	private String path = "/";
	private Multimap<String, String> query = ArrayListMultimap.create();
	private Multimap<String, String> form = ArrayListMultimap.create();
	private String data = null;
	private List<Header> headers = new ArrayList<>();

	public HttpRequest() {
		httpClient = HttpClients.custom().build();
	}

	
	private static HttpRequest wrapRequest(String destination) {
		HttpRequest req = new HttpRequest();
		int p1 = destination.indexOf("://");
		if (p1 > 0) {
			req.protocol(destination.substring(0, p1));
		} else {
			destination = req.protocol + "://" + destination;
			req.destination = destination;
		}
		return req;
	}

	public static HttpRequest get(String destination) {
		HttpRequest req = wrapRequest(destination);
		req.method(HttpMethod.GET).dest(destination);
		return req;
	}

	public static HttpRequest post(String destination) {
		HttpRequest req = wrapRequest(destination);
		req.method(HttpMethod.POST).dest(destination);
		return req;
	}

	public static HttpRequest put(String destination) {
		HttpRequest req = wrapRequest(destination);
		req.method(HttpMethod.PUT).dest(destination);
		return req;
	}

	public static HttpRequest delete(String destination) {
		HttpRequest req = wrapRequest(destination);
		req.method(HttpMethod.DELETE).dest(destination);
		return req;
	}

	public static HttpRequest patch(String destination) {
		HttpRequest req = wrapRequest(destination);
		req.method(HttpMethod.PATCH).dest(destination);
		return req;
	}

	private HttpRequest dest(String destination) {
		this.destination = destination;
		return this;
	}

	public HttpMethod method() {
		return method;
	}

	public HttpRequest method(HttpMethod method) {
		this.method = method;
		return this;
	}

	public String protocol() {
		return protocol;
	}

	public HttpRequest protocol(String protocol) {
		this.protocol = protocol;
		return this;
	}

	public String host() {
		return host;
	}

	public HttpRequest host(String host) {
		this.host = host;
		return this;
	}

	public int port() {
		return port;
	}

	public HttpRequest port(int port) {
		this.port = port;
		return this;
	}

	public String path() {
		return path;
	}

	public HttpRequest path(String path) {
		this.path = path;
		return this;
	}

	public Multimap<String, String> query() {
		return query;
	}

	public HttpRequest query(String name, String value) {
		query.put(name, value);
		return this;
	}

	public HttpResponse send() {
		StringBuilder accumation = null;
		if (this.destination == null) {
			accumation = new StringBuilder();
			accumation.append(this.protocol()).append("://")
					.append(this.host());
			if (this.port != null) {
				accumation.append(':').append(this.port());
			}
			accumation.append(this.path());
			this.destination = accumation.toString();
		} else {
			accumation = new StringBuilder(destination);
		}
		if (!query.isEmpty()) {
			if (destination.indexOf('?') > 0) {
				accumation.append('&');
			} else {
				accumation.append('?');
			}
			int i = 0;
			for (Entry<String, String> entry : query.entries()) {
				if (i > 0) {
					accumation.append('&');
				}
				i++;
				accumation.append(entry.getKey()).append('=')
						.append(entry.getValue());
			}
			this.destination = accumation.toString();
		}
		HttpResponse response = null;
		HttpClientContext context = HttpClientContext.create();
		if (cookieStore != null)
			context.setCookieStore(cookieStore.cookieStore());
		switch (method) {
		case GET: {
			HttpGet req = new HttpGet(destination);
			wrapHeader(req);
			try {
				CloseableHttpResponse res = this.httpClient
						.execute(req, context);
				response = new HttpResponse(res);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		case POST: {
			HttpPost req = new HttpPost(destination);
			wrapHeader(req);
			req.setEntity(getReqEntity());
			try {
				CloseableHttpResponse res = this.httpClient
						.execute(req, context);
				response = new HttpResponse(res);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		case PUT: {
			HttpPut req = new HttpPut(destination);
			wrapHeader(req);
			req.setEntity(getReqEntity());
			try {
				CloseableHttpResponse res = this.httpClient
						.execute(req, context);
				response = new HttpResponse(res);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		case PATCH: {
			HttpPatch req = new HttpPatch(destination);
			wrapHeader(req);
			req.setEntity(getReqEntity());
			try {
				CloseableHttpResponse res = this.httpClient
						.execute(req, context);
				response = new HttpResponse(res);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		case DELETE: {
			HttpDelete req = new HttpDelete(destination);
			wrapHeader(req);
			try {
				CloseableHttpResponse res = this.httpClient
						.execute(req, context);
				response = new HttpResponse(res);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		default:
			break;
		}
		return response;
	}

	private void wrapHeader(HttpRequestBase req) {
		for (Header h : headers) {
			req.addHeader(h.header());
		}
	}

	private HttpEntity getReqEntity() {
		HttpEntity entity = null;
		if ("application/x-www-form-urlencoded".equals(contentType)) {
			List<BasicNameValuePair> params = new ArrayList<>();
			// 添加参数
			for (Entry<String, String> entry : form.entries()) {
				params.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
			UrlEncodedFormEntity entity1 = new UrlEncodedFormEntity(params,
					Charset.forName("UTF-8"));
			entity1.setContentType(contentType);
			entity = entity1;
		} else {
			if (data != null) {
				StringEntity entity1 = new StringEntity(data,
						Charset.forName("UTF-8"));// 如果是xml或json请求数据，那么通过StringEntity构造请求数据，同时设置字符集
				if (contentType != null) {
					entity1.setContentType(contentType);
				} else {
					entity1.setContentType("text/plain");
				}
				entity = entity1;
			}
		}
		return entity;
	}

	public HttpRequest form(String name, String value) {
		if (contentType == null) {
			contentType = "application/x-www-form-urlencoded";
		}
		form.put(name, value);
		return this;
	}

	public HttpRequest data(String data) {
		if (contentType == null) {
			contentType = "text/plain";
		}
		this.data = data;
		return this;
	}

	public HttpRequest cookieStore(CookieStore cookieStore) {
		this.cookieStore = cookieStore;
		return this;
	}

	public CookieStore cookieStore() {
		return this.cookieStore;
	}

	public HttpRequest header(String name, String value) {
		headers.add(new Header(name, value));
		return this;
	}

	public HttpRequest header(Header header) {
		headers.add(header);
		return this;
	}

	public HttpRequest contentType(String contentType) {
		this.contentType = contentType;
		return this;
	}
}
