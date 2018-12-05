package com.templatecreator.objects;

public class Reservation {
	private String roomNumber;
	private String startTimestamp;
	private String endTimestamp;

	public Reservation() {
		
	}
	
	public Reservation(String roomNumber, String startTimestamp, String endTimestamp) {
		this.roomNumber = roomNumber;
		this.startTimestamp = startTimestamp;
		this.endTimestamp = endTimestamp;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(String startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public String getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(String endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	@Override
	public String toString() {
		return "Reservation [roomNumber=" + roomNumber + ", startTimeStamp=" + startTimestamp + ", endTimeStamp="
				+ endTimestamp + "]";
	}

}
