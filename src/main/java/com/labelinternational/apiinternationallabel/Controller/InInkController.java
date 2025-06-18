package com.labelinternational.apiinternationallabel.Controller;


import com.labelinternational.apiinternationallabel.DTOs.Entry.InkEntryDto;
import com.labelinternational.apiinternationallabel.DTOs.HistoryEntriesInk;
import com.labelinternational.apiinternationallabel.Entity.InInk;
import com.labelinternational.apiinternationallabel.Service.InInkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inink")
public class InInkController {

    @Autowired
    private InInkService inInkService;

    @PostMapping
    public ResponseEntity<?> createInInk(@RequestBody List<InkEntryDto> inInks) {
        return inInkService.createInInk(inInks);
    }

    @GetMapping
    public ResponseEntity<List<HistoryEntriesInk>> getAllInInk() {
        return inInkService.getAllInInk();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InInk> getInInkById(@PathVariable Long id) {
        return inInkService.getInInkById(id);
    }

    @PutMapping
    public ResponseEntity<InInk> updateInInk(@RequestBody InInk inInk) {
        return inInkService.updateInInk(inInk);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InInk> deleteInInk(@PathVariable Long id) {
        return inInkService.deleteInInk(id);
    }

}
