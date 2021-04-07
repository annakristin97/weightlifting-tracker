package com.dtu.weightliftingtracker.Services.Implementations;

import com.dtu.weightliftingtracker.Entities.Lift;
import com.dtu.weightliftingtracker.Repositories.LiftRepository;
import com.dtu.weightliftingtracker.Services.LiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LiftServiceImplementation implements LiftService {
    LiftRepository repository;

    @Autowired
    public LiftServiceImplementation(LiftRepository repository) {
        this.repository = repository;
    }

    @Override
    public Lift save(Lift lift) {
        return repository.save(lift);
    }

    @Override
    public void delete(Lift lift) {
        repository.delete(lift);
    }

    @Override
    public void deleteAll(){
        repository.deleteAll();
    }

    @Override
    public List<Lift> findAll(){
        return repository.findAll();
    }

    @Override
    public List<Lift> findByliftName(String liftName) {
        return repository.findByliftName(liftName);
    }

    /*@Override
    public Optional<Item> findByitemID(long itemID){
        return repository.findByitemID(itemID);
    }*/
}
