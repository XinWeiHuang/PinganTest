package com.test.pingan;

public class Seat {
	private Integer id;
	private String name;
	private Integer status;
	
//	private Map<Integer, Object> rowLocks = new HashMap<Integer, Object>();
	
	@Override
	public String toString() {
		return "Seat [id=" + id + ", name=" + name + "]";
	}

	public Seat(Integer id, String name, Integer status) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
	}
	
	public Seat() {
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
