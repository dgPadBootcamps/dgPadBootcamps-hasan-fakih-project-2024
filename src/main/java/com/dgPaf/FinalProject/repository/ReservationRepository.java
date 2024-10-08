package com.dgPaf.FinalProject.repository;

import com.dgPaf.FinalProject.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByOwnerId(Long ownerId);

    @Query("SELECT COUNT(DISTINCT r.requester.id) FROM Reservation r WHERE r.furniture.owner.id = :userId")
    long countDistinctPeopleHelped(@Param("userId") Long userId);

    Long countByOwnerId(Long ownerId);

    List<Reservation> findByRequesterId(Long requesterId);

    // New method to count reservations by requester
    Long countByRequesterId(Long requesterId);
}

