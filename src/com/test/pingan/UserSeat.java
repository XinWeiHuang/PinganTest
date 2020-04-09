package com.test.pingan;

public class UserSeat {
	private Integer userId;
	private Integer seatId;
	
	public UserSeat(Integer userId, Integer seatId) {
		super();
		this.userId = userId;
		this.seatId = seatId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getSeatId() {
		return seatId;
	}
	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}
	
}
