package com.hanfak.greedydb.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Click {
	private String streamName;
	private String timestamp;
	private String page;
	private Origin origin;
	
	public static class Origin {
		private String brand;
		private String post;
		
		public String getBrand() {
			return brand;
		}
		public void setBrand(String brand) {
			this.brand = brand;
		}
		public String getPost() {
			return post;
		}
		public void setPost(String post) {
			this.post = post;
		}
	}
	
	public Click() {
	}
	
	public String getStreamName() {
		return streamName;
	}
	
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String num) {
		this.timestamp = num;
	}
	
	public String getPage() {
		return page;
	}
	
	public void setPage(String page) {
		this.page = page;
	}
	
	public Origin getOrigin() {
		return origin;
	}
	
	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
}

