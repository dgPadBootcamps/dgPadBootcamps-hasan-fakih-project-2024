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
    @GetMapping("/count/{ownerId}")
    public ResponseEntity<Long> countRequestsByOwner(@PathVariable Long ownerId) {
        Long requestCount = reservationService.countRequestsByOwner(ownerId);
        return ResponseEntity.ok(requestCount);
    }
    @GetMapping("/requester/{requesterId}")
    public ResponseEntity<List<Reservation>> getRequestsForRequester(@PathVariable Long requesterId) {
        List<Reservation> reservations = reservationService.getRequestsByRequester(requesterId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/count/requester/{requesterId}")
    public ResponseEntity<Long> countRequestsByRequester(@PathVariable Long requesterId) {
        Long requestCount = reservationService.countRequestsByRequester(requesterId);
        return ResponseEntity.ok(requestCount);
    }
}

