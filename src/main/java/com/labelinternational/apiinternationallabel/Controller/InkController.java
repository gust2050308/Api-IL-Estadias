package com.labelinternational.apiinternationallabel.Controller;

import com.labelinternational.apiinternationallabel.Entity.Ink;
import com.labelinternational.apiinternationallabel.Service.InkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ink")
public class InkController {


    @Autowired
    private InkService inkService;

    @PostMapping
    public ResponseEntity<Ink> create(@RequestBody Ink ink) {
        return inkService.createInk(ink);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ink> getInk(@PathVariable Long id) {
        return inkService.findInkById(id);
    }

    @GetMapping
    public ResponseEntity<List<Ink>> getAll() {
        return inkService.findAllInk();
    }

    @PutMapping
    public ResponseEntity<Ink> update(@RequestBody Ink ink) {
        return inkService.updateInk(ink);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ink> delete(@PathVariable Long id) {
        return inkService.deleteInkById(id);
    }

    @GetMapping("/findInksWithStock")
    public ResponseEntity<List<Ink>> findInksWithStock() {
        return inkService.getAvialableInks();
    }

}
