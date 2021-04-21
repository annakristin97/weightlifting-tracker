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
    List<Lift> findBySets(long sets);
    List<Lift> findByReps(long reps);
    List<Lift> findByLiftNameContains(String liftName);

    @Modifying
    @Transactional
    @Query("SELECT l FROM Lift l WHERE l.logTime >= :milliseconds")
    List<Lift> getNewerThan(@Param("milliseconds") long milliseconds);

    @Query("SELECT DISTINCT l.liftName FROM Lift l")
    List<String> getDistinctLiftNames();
}
