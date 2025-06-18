package com.labelinternational.apiinternationallabel.Controller;

import com.labelinternational.apiinternationallabel.DTOs.OutputInkDto;
import com.labelinternational.apiinternationallabel.Service.OutputInkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
