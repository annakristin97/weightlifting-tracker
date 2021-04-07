package com.dtu.weightliftingtracker.Repositories;

import com.dtu.weightliftingtracker.Entities.Lift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface LiftRepository extends JpaRepository<Lift, Long> {
    Lift save(Lift item);
    void delete(Lift item);

    @Override
    void deleteAll();

    List<Lift> findAll();
    List<Lift> findByliftName(String liftName);
    //Optional<Item> findByitemID(long itemID);
    //List<Item> findByitemPriceLessThanEqual(long price);

    /*@Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Item i set i.itemBuyerID = :buyerID where i.itemID = :itemID")
    void changeBuyerID(@Param("itemID") long itemID, @Param("buyerID") long buyerID);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Item i set i.itemBuyerID = :buyerID where i.itemBuyerID = :oldBuyerID")
    void removeBuyerID(@Param("buyerID") long buyerID, @Param("oldBuyerID") long oldBuyerID);*/
}
