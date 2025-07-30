package com.labelinternational.apiinternationallabel.Repository.Filters;

import com.labelinternational.apiinternationallabel.DTOs.OutputsDto.OutputInkDto;
import com.labelinternational.apiinternationallabel.Entity.InInk;
import com.labelinternational.apiinternationallabel.Entity.Ink;
import com.labelinternational.apiinternationallabel.Entity.OutputInk;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class OutputInkRepositoryImpl implements OutputInkRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OutputInkDto> filterOutputInk(Instant minRequestedDate,
                                              Instant maxRequestedDate,
                                              Long idInk, String type,
                                              String internalBatch,
                                              BigDecimal minKgRequested,
                                              BigDecimal maxKgRequested,
                                              BigDecimal minKgDelivered,
                                              BigDecimal maxKgDelivered,
                                              String whoDelivered,
                                              String whoRecibed) {

        {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<OutputInkDto> cq = cb.createQuery(OutputInkDto.class);

            Root<OutputInk> root = cq.from(OutputInk.class);
            Join<Object, Ink> inkJoin = root.join("ink");
            Join<Object, InInk> inInkJoin = inkJoin.join("inInk");

            List<Predicate> predicates = new ArrayList<>();

            if (minRequestedDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("date"), Date.from(minRequestedDate)));
            }
            if (maxRequestedDate != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("date"), Date.from(maxRequestedDate)));
            }
            if (idInk != null) {
                predicates.add(cb.equal(inkJoin.get("idInk"), idInk));
            }
            if (type != null && !type.isBlank()) {
                predicates.add(cb.like(cb.lower(inInkJoin.get("typeMaterial")), "%" + type.toLowerCase() + "%"));
            }
            if (internalBatch != null && !internalBatch.isBlank()) {
                predicates.add(cb.like(cb.lower(inInkJoin.get("internalBatch")), "%" + internalBatch.toLowerCase() + "%"));
            }
            if (minKgRequested != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("kilogramsRequired"), minKgRequested));
            }
            if (maxKgRequested != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("kilogramsRequired"), maxKgRequested));
            }
            if (minKgDelivered != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("kilogramsDelivered"), minKgDelivered));
            }
            if (maxKgDelivered != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("kilogramsDelivered"), maxKgDelivered));
            }
            if (whoDelivered != null && !whoDelivered.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("whoDelivers")), "%" + whoDelivered.toLowerCase() + "%"));
            }
            if (whoRecibed != null && !whoRecibed.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("whoReceives")), "%" + whoRecibed.toLowerCase() + "%"));
            }

            // Selección del constructor de DTO
            cq.select(cb.construct(
                    OutputInkDto.class,
                    root.get("idOutputInk"),
                    root.get("date"),
                    root.get("production"),
                    inkJoin.get("idInk"),
                    inInkJoin.get("typeMaterial"),
                    inInkJoin.get("internalBatch"),
                    root.get("kilogramsRequired"),
                    root.get("kilogramsDelivered"),
                    root.get("whoDelivers"),
                    root.get("whoReceives"),
                    root.get("returnedKilogramsRequired")
            ));

            cq.where(cb.and(predicates.toArray(new Predicate[0])));
            cq.orderBy(cb.desc(root.get("date")));

            return entityManager.createQuery(cq).getResultList();
        }
    }
}
