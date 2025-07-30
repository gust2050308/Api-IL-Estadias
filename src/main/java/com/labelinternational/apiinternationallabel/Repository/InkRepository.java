package com.labelinternational.apiinternationallabel.Repository;

import com.labelinternational.apiinternationallabel.DTOs.ExistenceDto;
import com.labelinternational.apiinternationallabel.DTOs.OrderDesInkDto;
import com.labelinternational.apiinternationallabel.Entity.Ink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface InkRepository extends JpaRepository<Ink, Long> {

    @Query("SELECT i FROM Ink i WHERE i.remainingVolume > 0")
    List<Ink> findInksWithStock();

    @Query("""
            
            SELECT new com.labelinternational.apiinternationallabel.DTOs.ExistenceDto(
                 i.idInk,
                 io.codeItem,
                 ii.internalBatch,
                 p.providerName,
                 i.remainingVolume,
                 i.totalKilograms,
                 ii.typeMaterial,
                 i.volumeUsed,
                 ii.batchProvider,
                 i.idInk
             )
             FROM Ink i
             JOIN i.inInk ii
             JOIN ii.itemOrder io
             JOIN io.purchaseOrder po    
             JOIN po.provider p
             WHERE (:idProvider IS NULL OR p.idProvider = :idProvider)
             AND (:batchProvider IS NULL OR ii.batchProvider LIKE %:batchProvider%)
             AND (:internalBatch IS NULL OR ii.internalBatch LIKE %:internalBatch%)
             AND (:typeMaterial IS NULL OR ii.typeMaterial LIKE %:typeMaterial%)
             AND (:codeItem IS NULL OR io.codeItem LIKE %:codeItem%)
             AND (:minRemaining IS NULL OR i.remainingVolume >= :minRemaining)
             AND (:maxRemaining IS NULL OR i.remainingVolume <= :maxRemaining)
             AND (i.remainingVolume > 0.0)
            """)
    List<ExistenceDto> filterExistences(
            @Param("idProvider") Long idProvider,
            @Param("batchProvider") String batchProvider,
            @Param("internalBatch") String internalBatch,
            @Param("typeMaterial") String typeMaterial,
            @Param("codeItem") String codeItem,
            @Param("minRemaining") BigDecimal minRemaining,
            @Param("maxRemaining") BigDecimal maxRemaining
    );

    @Query("SELECT COUNT(i) FROM Ink i WHERE i.remainingVolume > 0")
    Long countInksWithStock();

    @Query("""
               SELECT DISTINCT ii.typeMaterial
            FROM Ink i
               JOIN i.inInk ii
               WHERE i.remainingVolume > 0
            """)
    List<String> getTypeMaterials();

    @Query("SELECT SUM(i.remainingVolume) FROM Ink i WHERE i.remainingVolume > 0")
    BigDecimal sumInksWithStock();

    @Query("""
                SELECT new com.labelinternational.apiinternationallabel.DTOs.OrderDesInkDto(
                    ii.typeMaterial,
                    SUM(i.remainingVolume)
                )
                FROM Ink i
                JOIN i.inInk ii
                WHERE i.remainingVolume > 0
                GROUP BY ii.typeMaterial
                ORDER BY SUM(i.remainingVolume) DESC
            """)
    List<OrderDesInkDto> getInksWithStockGroupedByType();



}