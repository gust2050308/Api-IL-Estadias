package com.labelinternational.apiinternationallabel.Repository;

import com.labelinternational.apiinternationallabel.DTOs.OutputsDto.OutputInkDto;
import com.labelinternational.apiinternationallabel.Entity.OutputInk;
import com.labelinternational.apiinternationallabel.Repository.Filters.OutputInkRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public interface OutputInkRepository extends JpaRepository<OutputInk, Long>, OutputInkRepositoryCustom {
}