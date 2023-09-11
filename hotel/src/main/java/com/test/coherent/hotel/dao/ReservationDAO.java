package com.test.coherent.hotel.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.test.coherent.hotel.domain.Reservation;

@Repository
public interface ReservationDAO extends CrudRepository<Reservation, Integer> {

}
