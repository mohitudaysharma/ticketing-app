package com.example.demo.entity;

public class DataEvent {
    private String message;
    private Object object;
    
    public DataEvent() {}

    public DataEvent(String message, Object object) {
		super();
		this.message = message;
		this.object = object;
	}
	
    public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
}