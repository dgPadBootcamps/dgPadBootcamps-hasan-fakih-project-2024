package com.dgPaf.FinalProject.service;

import com.dgPaf.FinalProject.model.Furniture;
import com.dgPaf.FinalProject.model.Reservation;
import com.dgPaf.FinalProject.model.User;
import com.dgPaf.FinalProject.repository.FurnitureRepository;
import com.dgPaf.FinalProject.repository.ReservationRepository;
import com.dgPaf.FinalProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private FurnitureRepository furnitureRepository;

    @Autowired
    private UserRepository userRepository;

    public Reservation requestFurniture(Long furnitureId, Long requesterId) {
        Furniture furniture = furnitureRepository.findById(furnitureId)
                .orElseThrow(() -> new RuntimeException("Furniture not found"));
        User requester = userRepository.findById(requesterId)
                .orElseThrow(() -> new RuntimeException("Requester not found"));

        // Create a new reservation
        Reservation reservation = new Reservation();
        reservation.setFurniture(furniture);
        reservation.setRequester(requester);
        reservation.setOwner(furniture.getOwner());
        reservation.setRequestDate(LocalDateTime.now());
        reservation.setStatus("PENDING");

        return reservationRepository.save(reservation);
    }

    public List<Reservation> getRequestsByOwner(Long ownerId) {
        return reservationRepository.findByOwnerId(ownerId);
    }

    public void updateRequestStatus(Long reservationId, String status) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.setStatus(status);
        reservationRepository.save(reservation);
    }
    public Long countRequestsByOwner(Long ownerId) {
        return reservationRepository.countByOwnerId(ownerId);
    }
    public List<Reservation> getRequestsByRequester(Long requesterId) {
        return reservationRepository.findByRequesterId(requesterId);
    }

    // New method to count requests by the requester for badge content
    public Long countRequestsByRequester(Long requesterId) {
        return reservationRepository.countByRequesterId(requesterId);
    }
}
