package com.labelinternational.apiinternationallabel.Service;

import com.labelinternational.apiinternationallabel.DTOs.OutputInkDto;
import com.labelinternational.apiinternationallabel.Entity.OutputInk;
import com.labelinternational.apiinternationallabel.Mappers.OutputInkMapper;
import com.labelinternational.apiinternationallabel.Repository.OutputInkRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OutputInkService {

    @Autowired
    private OutputInkRepository outputInkRepository;

    @Autowired
    private OutputInkMapper outputInkMapper;

    private static final Logger log = LoggerFactory.getLogger(OutputInkService.class);

    @Transactional
    public ResponseEntity<List<OutputInkDto>> findAllOutputs(){
        try{
            List<OutputInk> outs = outputInkRepository.findAll();
            if (!outs.isEmpty()){
                List<OutputInkDto> outList = outputInkMapper.toResponseInks(outs);
                return new ResponseEntity<>(outList, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<List<OutputInkDto>> findToFormDevolucion( List<Long> list) {
        List<OutputInkDto> responseList = new ArrayList<>();
        try {
            for (Long id : list) {
                Optional<OutputInk> o = outputInkRepository.findById(id);
                if (o.isPresent()) {
                    OutputInk outputInk = o.get();
                    responseList.add(outputInkMapper.toDto(outputInk));
                }
            }
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
