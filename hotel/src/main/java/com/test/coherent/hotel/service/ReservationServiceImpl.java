package com.test.coherent.hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.coherent.hotel.exception.ReservationNotFoundException;
import com.test.coherent.hotel.dao.ReservationDAO;
import com.test.coherent.hotel.domain.Reservation;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationDAO repo;
	@Override
	public List<Reservation> findAll() {
		return (List<Reservation>) repo.findAll();
	}

	@Override
	public void createNewReservation(Reservation reservation) {
		repo.save(reservation);

	}

	@Override
	public void deleteReservation(Reservation reservation) {
		repo.delete(reservation);
	}

	@Override
	public Optional<Reservation> findReservationById(Integer id) {
		
		Optional<Reservation> reservation = repo.findById(id);  
		if (!reservation.isPresent())
			throw new ReservationNotFoundException("Reservation not found id: "+ id);
			
		return reservation;
		
	}

	@Override
	public void modifyReservation(Reservation reservation, Reservation existingReservation) {
		if(reservation.getClientFullName()!="")
			existingReservation.setClientFullName(reservation.getClientFullName());
		if(reservation.getRoomNumber()!= null)
			existingReservation.setRoomNumber(reservation.getRoomNumber());
		if(reservation.getReservationList()!=null)
			existingReservation.setReservationList(reservation.getReservationList());
			
		repo.save(existingReservation);
					
	}
}
 