package com.labelinternational.apiinternationallabel.Repository;

import com.labelinternational.apiinternationallabel.Entity.Ink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InkRepository extends JpaRepository<Ink, Long> {

    @Query("SELECT i FROM Ink i WHERE i.remainingVolume > 0")
    List<Ink> findInksWithStock();

}