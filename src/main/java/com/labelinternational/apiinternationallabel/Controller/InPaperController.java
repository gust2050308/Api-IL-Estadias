package com.labelinternational.apiinternationallabel.Controller;

import com.labelinternational.apiinternationallabel.Entity.InPaper;
import com.labelinternational.apiinternationallabel.Service.InPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inPaper")
public class InPaperController {

    @Autowired
    private InPaperService inPaperService;

    @PostMapping
    public ResponseEntity<?> createInPaper(@RequestBody InPaper inPaper){
        return inPaperService.createInPaper(inPaper);
    }

    @GetMapping
    public ResponseEntity<?> getAllInPaper(){
        return inPaperService.getAllInPaper();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInPaperById(@PathVariable Long id){
        return inPaperService.getInPaperById(id);
    }

    @PutMapping
    public ResponseEntity<?> updateInPaper(@RequestBody InPaper inPaper){
        return inPaperService.updateInPaper(inPaper);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInPaper(@PathVariable Long id){
        return inPaperService.deleteInPaper(id);
    }
}
