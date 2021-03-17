package com.dtu.weightliftingtracker.Controllers;

import com.dtu.weightliftingtracker.Entities.Lift;
import com.dtu.weightliftingtracker.Repositories.LiftRepository;
import com.dtu.weightliftingtracker.Services.LiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class LiftController {
    private LiftService liftService;
    private LiftRepository liftRepository;

    @Autowired
    public LiftController(LiftService liftService, LiftRepository liftRepository) {
        this.liftService = liftService;
        this.liftRepository = liftRepository;
    }


    @RequestMapping("/lifts")
    public List<Lift> getLifts() {
        return liftService.findAll();
    }

    @RequestMapping("/resetLifts")
    public List<Lift> resetLifts() {
        liftRepository.deleteAll();
        liftRepository.save(new Lift(0, "Deadlift", 80, 3, 5, new Date(System.currentTimeMillis())));
        liftRepository.save(new Lift(0, "Shoulder Press", 50, 2, 5, new Date(System.currentTimeMillis())));
        liftRepository.save(new Lift(0, "Back Squat", 90, 1, 1, new Date(System.currentTimeMillis())));
        liftRepository.save(new Lift(0, "Deadlift", 90, 3, 5, new Date(System.currentTimeMillis())));

        return liftService.findAll();
    }
}