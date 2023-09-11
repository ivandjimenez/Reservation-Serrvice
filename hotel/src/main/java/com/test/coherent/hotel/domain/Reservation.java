package com.test.coherent.hotel.domain;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String clientFullName;
	private Integer roomNumber;
	private List<LocalDate> reservationList;
	
	public Reservation() {
	}
	
	public Reservation(String clientFullName) {
		super();
		this.clientFullName = clientFullName;
	}
	
	public Reservation(String clientFullName, List<LocalDate> reservationList) {
		super();
		this.clientFullName = clientFullName;
		this.reservationList = reservationList;
	}

	public Reservation(List<LocalDate> reservationList) {
		super();
		this.reservationList = reservationList;
	}

	public Reservation(String clientFullName, int roomNumber, List<LocalDate> reservationList) {
		this.clientFullName = clientFullName;
		this.roomNumber = roomNumber;
		this.reservationList = reservationList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientFullName() {
		return clientFullName;
	}

	public void setClientFullName(String clientFullName) {
		this.clientFullName = clientFullName;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public List<LocalDate> getReservationList() {
		return reservationList;
	}

	public void setReservationList(List<LocalDate> reservationList) {
		this.reservationList = reservationList;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", clientFullName=" + clientFullName + ", roomNumber=" + roomNumber
				+ ", reservationList=" + reservationList + "]";
	}
		
}
