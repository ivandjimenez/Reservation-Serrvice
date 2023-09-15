package com.test.coherent.hotel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.test.coherent.hotel.domain.Reservation;
import com.test.coherent.hotel.exception.ReservationNotFoundException;
import com.test.coherent.hotel.service.ReservationService;

import java.net.URI;
import java.util.Optional;
import java.util.List;

@RestController
public class ReservationController {
	
	@Autowired
	ReservationService service;
	
	private static final Logger log = LoggerFactory.getLogger(ReservationService.class); 

	@GetMapping("/reservations")
	public List<Reservation> readAllReservations()
	{
		List<Reservation> reservations = service.findAll();
		log.info("Reservations: "+ reservations.toString());
		return reservations;
	}
	
	@GetMapping("/reservation/{id}")
	public Optional<Reservation> findById(@PathVariable("id") int id)
	{
		Optional<Reservation> reservation = service.findReservationById(id);
		log.info("Reservations: "+ reservation.toString());
		return reservation;
		
	}
	
	
	@PostMapping("/newReservation")
	public ResponseEntity<Object> createNewReservation(@RequestBody Reservation reservation){
		service.createNewReservation(reservation);
		log.info("New reservation created: "+ reservation.toString());

		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest().
		path("/{reservationId}").
		buildAndExpand(reservation.getId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PatchMapping("/modifyReservation/{id}")
	public ResponseEntity<Object> modifyReservation(@PathVariable("id") int id, @RequestBody Reservation reservation){
		
		Optional<Reservation> existingReservation = findById(id);
		service.modifyReservation(reservation, existingReservation.get());
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest().
		path("/{reservationId}").
		buildAndExpand(existingReservation.get().getId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@DeleteMapping("/reservation/{id}")
	@ResponseBody
	public void deleteReservation(@PathVariable int id)
	{
		
		try {
			service.deleteReservation(findById(id).get());
			log.info("Reservation deleted: "+ id);

		}
		catch(IllegalArgumentException e)
		{
			log.error("Reservation with Id# "+ id+" could not be found.");
			throw new ReservationNotFoundException("Employee not found id: "+ id);
		}
		
	}

}
