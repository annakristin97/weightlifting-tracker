package com.dtu.weightliftingtracker.Services;


import com.dtu.weightliftingtracker.Entities.Lift;

import java.util.List;
import java.util.Optional;

public interface LiftService {
    Lift save(Lift lift);
    void delete(Lift lift);
    void deleteAll();
    //Optional<Item> findByitemID(long itemID);
    List<Lift> findAll();
    //List<Item> findByitemPriceLessThanEqual(long price);
}
