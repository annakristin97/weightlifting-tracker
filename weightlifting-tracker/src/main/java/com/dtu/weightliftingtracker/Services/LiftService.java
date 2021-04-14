package com.dtu.weightliftingtracker.Services;


import com.dtu.weightliftingtracker.Entities.Lift;

import java.util.List;
import java.util.Optional;

public interface LiftService {
    Lift save(Lift lift);
    void delete(Lift lift);
    void deleteAll();
    List<Lift> findAll();
    List<Lift> findByliftName(String liftName);
    List<Lift> findBySets(long sets);
    List<Lift> findByReps(long reps);
    List<Lift> getNewerThan(long milliseconds);
    List<Lift> findByLiftNameContains(String title);
}
