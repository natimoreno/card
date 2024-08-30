package com.bank.card.controller;

import com.bank.card.ApiExceptions.ApiException;
import com.bank.card.model.PayloadDTO;
import com.bank.card.repository.Card;
import com.bank.card.service.SegmentService;
import com.bank.card.service.CallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bank")
public class SegmentController {

    private final SegmentService segmentService;

    private final CallService callService;

    public SegmentController(SegmentService segmentService, CallService testService) {
        this.segmentService = segmentService;
        this.callService = testService;
    }

    @PostMapping("/segment")
    public ResponseEntity<Optional<Card>> createSegment(@RequestBody PayloadDTO payloadDTO) throws ApiException {

        Optional<Card> createdCard = segmentService.create(payloadDTO);
        return ResponseEntity.ok().body(createdCard);
    }

    @GetMapping("/segment/{id}")
    public ResponseEntity<Optional<Card>> searchCard(@PathVariable Long id) {

        Optional<Card> card = this.callService.retrieveCard(id);
        return ResponseEntity.ok().body(card);
    }
}
