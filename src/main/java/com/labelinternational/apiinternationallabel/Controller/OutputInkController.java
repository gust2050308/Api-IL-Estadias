package com.labelinternational.apiinternationallabel.Controller;

import com.labelinternational.apiinternationallabel.DTOs.OutputsDto.OutputInkDto;
import com.labelinternational.apiinternationallabel.Service.OutputInkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/outputInks")
public class OutputInkController {

    @Autowired
    private OutputInkService outputInkService;

    @GetMapping("/findAll")
    public ResponseEntity<List<OutputInkDto>> findAll() {
        return outputInkService.findAllOutputs();
    }

    @PostMapping("/findToFormDevolucion")
    public ResponseEntity<List<OutputInkDto>> findToFormDevolucion(@RequestBody List<Long> lista) {
        return outputInkService.findToFormDevolucion(lista);
    }

    @GetMapping("findByFilter")public ResponseEntity<List<OutputInkDto>> getOutputInksFiltered(
            @RequestParam(required = false) String minRequestedDate,
            @RequestParam(required = false) String maxRequestedDate,
            @RequestParam(required = false) Long idInk,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String internalBatch,
            @RequestParam(required = false) BigDecimal minKgRequested,
            @RequestParam(required = false) BigDecimal maxKgRequested,
            @RequestParam(required = false) BigDecimal minKgDelivered,
            @RequestParam(required = false) BigDecimal maxKgDelivered,
            @RequestParam(required = false) String whoDelivered,
            @RequestParam(required = false) String WhoRecibed
    ) {
        Instant minDate = (minRequestedDate != null && !minRequestedDate.isBlank()) ? Instant.parse(minRequestedDate) : null;
        Instant maxDate = (maxRequestedDate != null && !maxRequestedDate.isBlank()) ? Instant.parse(maxRequestedDate) : null;

        return outputInkService.findAllByOrderByDateDesc(
                minDate,
                maxDate,
                idInk,
                type,
                internalBatch,
                minKgRequested,
                maxKgRequested,
                minKgDelivered,
                maxKgDelivered,
                whoDelivered,
                WhoRecibed
        );
    }
}
