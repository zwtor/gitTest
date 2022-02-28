package com.lazy.httpclient.utils;

import org.apache.http.message.BasicHeader;
/**
 * 
 * @ClassName:  Header   
 * @Description:TODO(请求添加头部信息)   
 * @author:  zhanglun
 *     
 */
public class Header {

	private BasicHeader header = null;

	public Header(String name, String value) {
		this.header = new BasicHeader(name, value);
	}

	public BasicHeader header() {
		return header;
	}

	public void header(BasicHeader header) {
		this.header = header;
	}

}
