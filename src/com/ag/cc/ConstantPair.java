package com.ag.cc;

public class ConstantPair {
	String type;
	String name;
	Object value;
	
	public ConstantPair(String type, String name, Object value) {
		super();
		this.type = type;
		this.name = name;
		this.value = value;
	}
	public ConstantPair() {
		// TODO Auto-generated constructor stub
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	
}
