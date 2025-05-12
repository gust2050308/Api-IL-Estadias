package com.labelinternational.apiinternationallabel.Controller;


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
    InInkService inInkService;

    @PostMapping
    public ResponseEntity<?> createInInk(@RequestBody InInk inInk) {
        return inInkService.createInInk(inInk);
    }

    @GetMapping
    public ResponseEntity<List<InInk>> getAllInInk() {
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

    @PostMapping("/several")
    public ResponseEntity<?> createInInks(@RequestBody List<InInk> inInks) {
        return inInkService.createSeveral(inInks);
    }

}
