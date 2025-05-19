package com.labelinternational.apiinternationallabel.Service;

import com.labelinternational.apiinternationallabel.Entity.InkItemOrder;
import com.labelinternational.apiinternationallabel.Repository.InkItemOrderRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InkItemOrderService {

    @Autowired
    private InkItemOrderRepository itemOrderRepository;

    private static final Logger log = LoggerFactory.getLogger(InInkService.class);

    @Transactional
    public boolean createItemOrder(InkItemOrder itemOrder) {
        try {
            itemOrderRepository.save(itemOrder);
            return true;
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    @Transactional
    public boolean updateItemOrder(InkItemOrder itemOrder) {
        try {
            Optional<InkItemOrder> itemOrderTemp = itemOrderRepository.findById(itemOrder.getIdItemOrder());
            if (itemOrderTemp.isPresent()) {
                itemOrderRepository.save(itemOrder);
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean deleteItemOrder(InkItemOrder itemOrder) {
        try {
            Optional<InkItemOrder> itemOrderTemp = itemOrderRepository.findById(itemOrder.getIdItemOrder());
            if (itemOrderTemp.isPresent()) {
                itemOrderRepository.delete(itemOrderTemp.get());
                return true;
            }
            else {
                return false;
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
