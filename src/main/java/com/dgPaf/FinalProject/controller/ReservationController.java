package com.dgPaf.FinalProject.controller;

import com.dgPaf.FinalProject.model.Reservation;
import com.dgPaf.FinalProject.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/request/{furnitureId}/{requesterId}")
    public ResponseEntity<Reservation> requestFurniture(@PathVariable Long furnitureId, @PathVariable Long requesterId) {
        Reservation reservation = reservationService.requestFurniture(furnitureId, requesterId);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Reservation>> getRequestsForOwner(@PathVariable Long ownerId) {
        List<Reservation> reservations = reservationService.getRequestsByOwner(ownerId);
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("/update-status/{reservationId}")
    public ResponseEntity<Void> updateRequestStatus(@PathVariable Long reservationId, @RequestParam String status) {
        reservationService.updateRequestStatus(reservationId, status);
        return ResponseEntity.ok().build();
    }
}

