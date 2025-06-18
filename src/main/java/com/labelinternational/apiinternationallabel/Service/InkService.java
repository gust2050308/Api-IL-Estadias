package com.labelinternational.apiinternationallabel.Service;

import com.labelinternational.apiinternationallabel.DTOs.DevolutionInkDto;
import com.labelinternational.apiinternationallabel.DTOs.Entry.InkDto;
import com.labelinternational.apiinternationallabel.DTOs.ExistenceDto;
import com.labelinternational.apiinternationallabel.DTOs.InkSeleccionFormDto;
import com.labelinternational.apiinternationallabel.DTOs.OutputInkDto;
import com.labelinternational.apiinternationallabel.DTOs.inksToProduction.inkUse;
import com.labelinternational.apiinternationallabel.DTOs.inksToProduction.useInkDto;
import com.labelinternational.apiinternationallabel.Entity.Ink;
import com.labelinternational.apiinternationallabel.Entity.OutputInk;
import com.labelinternational.apiinternationallabel.Mappers.InkMapper;
import com.labelinternational.apiinternationallabel.Mappers.OutputInkMapper;
import com.labelinternational.apiinternationallabel.Repository.InkRepository;
import com.labelinternational.apiinternationallabel.Repository.OutputInkRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InkService {

    @Autowired
    private InkRepository inkRepository;

    @Autowired
    private OutputInkRepository outputInkRepository;

    @Autowired
    private OutputInkMapper outputInkMapper;

    @Autowired
    private InkMapper inkMapper;

    private static final Logger log = LoggerFactory.getLogger(InkService.class);

    @Transactional
    public ResponseEntity<Ink> createInk(Ink ink) {
        try{
            inkRepository.save(ink);
            return new ResponseEntity<>(ink, HttpStatus.CREATED);
        }catch(Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<Ink> findInkById(Long id) {
        try{ Optional<Ink> ink = inkRepository.findById(id);
            if(ink.isPresent()){
                return new ResponseEntity<>(ink.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<List<Ink>> findAllInk() {
        try {
            List<Ink> inks = inkRepository.findAll();
            if(!inks.isEmpty()){
                return new ResponseEntity<>(inks, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<Ink> updateInk(Ink ink) {
        try {
            Optional<Ink> search = inkRepository.findById(ink.getIdInk());
            if(search.isPresent()){
                Ink ink1 = search.get();
                Long id = ink1.getIdInk();
                ink.setIdInk(id);
                inkRepository.save(ink);
                return new ResponseEntity<>(ink, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<Ink> deleteInkById(Long id) {
        Optional<Ink> ink = inkRepository.findById(id);
        if(ink.isPresent()){
            inkRepository.delete(ink.get());
            return new ResponseEntity<>(ink.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<List<ExistenceDto>> getAvialableInks() {
        try{
            List<Ink> inks = inkRepository.findInksWithStock();
            if(!inks.isEmpty()){
                List<ExistenceDto> existenceDtos = new ArrayList<>();
                for(Ink ink : inks){
                    ExistenceDto existenceDto = ExistenceDto.builder()
                            .idInInk(ink.getIdInk())
                            .code(ink.getInInk().getItemOrder().getCodeItem())
                            .internalBatch(ink.getInInk().getInternalBatch())
                            .provider(ink.getInInk().getItemOrder().getPurchaseOrder().getProvider().getProvider_Name())
                            .remainingKilograms(ink.getRemainingVolume())
                            .totalKilograms(ink.getTotalKilograms())
                            .typeOfMaterial(ink.getInInk().getTypeMaterial())
                            .usedKilograms(ink.getVolumeUsed())
                            .batchProvider(ink.getInInk().getBatchProvider())
                            .id(ink.getIdInk())
                            .build();

                    existenceDtos.add(existenceDto);
                }
                return new ResponseEntity<>(existenceDtos, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<List<InkSeleccionFormDto>> inkToProduccion (List<Long> list){
        try{
            List<InkSeleccionFormDto> listInksSelected = new ArrayList<>();
            for (Long id : list) {
                Optional<Ink> ink = inkRepository.findById(id);
                if(ink.isPresent()){
                    Ink ink1 = ink.get();
                    InkSeleccionFormDto seleccionFormDto = InkSeleccionFormDto.builder()
                            .id(ink1.getIdInk())
                            .provider(ink1.getInInk().getItemOrder().getPurchaseOrder().getProvider().getProvider_Name())
                            .typeMateria(ink1.getInInk().getTypeMaterial())
                            .volumenRemaiming(ink1.getRemainingVolume())
                            .build();
                    listInksSelected.add(seleccionFormDto);
                }
            }
            return new ResponseEntity<>(listInksSelected, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<List<OutputInkDto>> inksRequiredToProduction(useInkDto inksData){
        List<OutputInk> inksOut = new ArrayList<>();
        try{
            List<inkUse> inks = inksData.getInks();
            for (inkUse ink : inks) {

                Optional<Ink> inkStockOptional = inkRepository.findById(ink.getId());
                if(inkStockOptional.isPresent()){

                    Ink inkStock = inkStockOptional.get();

                    OutputInk outputInk = OutputInk.builder()
                            //id auto
                            .date(new Date())
                            .production(inksData.getProduction())
                            .ink(inkStock)
                            .kilogramsRequired(ink.getKilogramsRequired())
                            .kilogramsDelivered(ink.getKilogramsDelivered())
                            .whoDelivers(inksData.getWhoDelivers())
                            .whoReceives(inksData.getWhoReceives())
                            .returnedKilogramsRequired(BigDecimal.ZERO)
                        .build();

                    outputInkRepository.save(outputInk);
                    inksOut.add(outputInk);

                    inkStock.setRemainingVolume(inkStock.getRemainingVolume().subtract(ink.getKilogramsDelivered()));
                    inkStock.setVolumeUsed(inkStock.getVolumeUsed().add(ink.getKilogramsDelivered()));
                    inkRepository.save(inkStock);
                }
            }
            return new ResponseEntity<>(outputInkMapper.toResponseInks(inksOut), HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<List<InkDto>> processInkDevolution(List<DevolutionInkDto> devolutions){
        try{
            List<InkDto> responseList = new ArrayList<>();
            for (DevolutionInkDto devolution : devolutions) {
                Optional<OutputInk> inkOptional = outputInkRepository.findById(devolution.getIdOutputInk());
                if(inkOptional.isPresent()){
                    OutputInk outputInk = inkOptional.get();
                    Ink ink = outputInk.getInk();
                    ink.setRemainingVolume(ink.getRemainingVolume().add(devolution.getDevolutionQuantity()));
                    ink.setVolumeUsed(ink.getVolumeUsed().subtract(devolution.getDevolutionQuantity()));
                    inkRepository.save(ink);
                    responseList.add(inkMapper.toDto(ink));

                    outputInk.setReturnedKilogramsRequired(outputInk.getReturnedKilogramsRequired().add(devolution.getDevolutionQuantity()));
                    outputInkRepository.save(outputInk);
                }else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            }
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}