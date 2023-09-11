package com.test.coherent.hotel.service;

import java.util.List;
import java.util.Optional;

import com.test.coherent.hotel.domain.Reservation;

public interface ReservationService {
	
	public List<Reservation> findAll();
	public void createNewReservation(Reservation reservation);
	public void deleteReservation(Reservation reservation);
	public Optional<Reservation> findReservationById(Integer id);
	public void modifyReservation(Reservation reservation, Reservation existingReservation);
}
