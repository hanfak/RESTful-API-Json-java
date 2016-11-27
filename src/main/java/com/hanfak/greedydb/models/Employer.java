package com.hanfak.greedydb.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employer {
	private String streamName;
	private String timestamp;
	private String id;
	private String name;
	private String surname;
	
	public Employer() {
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
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
}
