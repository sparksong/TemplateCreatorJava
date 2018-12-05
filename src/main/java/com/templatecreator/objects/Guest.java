package com.templatecreator.objects;

public class Guest {

	private String id;
	private String firstName;
	private String lastName;
	private Reservation reservation;
	
	public Guest() {	
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Reservation getReservation() {
		return reservation;
	}
	
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}


	@Override
	public String toString() {
		return "Guest name: " + firstName + " " + lastName;
	}	
	
	
	
}
