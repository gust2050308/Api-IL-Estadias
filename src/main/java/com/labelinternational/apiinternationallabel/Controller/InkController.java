package com.labelinternational.apiinternationallabel.Controller;

import com.labelinternational.apiinternationallabel.DTOs.DevolutionInkDto;
import com.labelinternational.apiinternationallabel.DTOs.Entry.InkDto;
import com.labelinternational.apiinternationallabel.DTOs.ExistenceDto;
import com.labelinternational.apiinternationallabel.DTOs.InkSeleccionFormDto;
import com.labelinternational.apiinternationallabel.DTOs.OutputInkDto;
import com.labelinternational.apiinternationallabel.DTOs.inksToProduction.useInkDto;
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
    public ResponseEntity<List<ExistenceDto>> findInksWithStock() {
        return inkService.getAvialableInks();
    }

    @PostMapping("/findSelectedInks")
    public ResponseEntity<List<InkSeleccionFormDto>> findSelectedInks(@RequestBody List<Long> selectedInkIds) {
        return inkService.inkToProduccion(selectedInkIds);
    }

    @PostMapping("/inksRequiredToProduction")
    public ResponseEntity<List<OutputInkDto>> inksRequiredToProduction(@RequestBody useInkDto selectedInkIds) {
        return inkService.inksRequiredToProduction(selectedInkIds);
    }

    @PostMapping("/OutputInkDevolution")
    public ResponseEntity<List<InkDto>> inkDevolution(@RequestBody List<DevolutionInkDto> devolutionInkDtos){
        return inkService.processInkDevolution(devolutionInkDtos);
    }
}
