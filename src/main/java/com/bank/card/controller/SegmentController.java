package com.bank.card.controller;

import com.bank.card.ApiExceptions.ApiException;
import com.bank.card.model.PayloadDTO;
import com.bank.card.repository.Card;
import com.bank.card.service.SegmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/bank")
public class SegmentController {

    private final SegmentService segmentService;

    public SegmentController(SegmentService segmentService) {
        this.segmentService = segmentService;
    }

    @PostMapping("/segment")
    public ResponseEntity<Optional<Card>> createSegment(@RequestBody PayloadDTO payloadDTO) throws ApiException {

        Optional<Card> createdCard = segmentService.create(payloadDTO);
        return ResponseEntity.ok().body(createdCard);
    }

}
