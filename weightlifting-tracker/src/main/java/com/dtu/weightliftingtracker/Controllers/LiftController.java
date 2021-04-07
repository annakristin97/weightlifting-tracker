package com.dtu.weightliftingtracker.Controllers;

import com.dtu.weightliftingtracker.Entities.Lift;
import com.dtu.weightliftingtracker.Repositories.LiftRepository;
import com.dtu.weightliftingtracker.Services.LiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
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
    public List<Lift> resetLifts() throws IOException {
        liftRepository.deleteAll();

        String line;
        int lineNr = 0;

        // Read all lines in from CSV file and add to lift repository
        FileReader fileReader = new FileReader(new File("./src/main/resources/static/lifting_data_anna.csv"));
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while ((line = bufferedReader.readLine()) != null) {
            if(lineNr != 0) {
                String[] temp = line.split(";");
                String liftName = temp[1];
                String result = temp[4];
                System.out.println(result);
                double weight = Double.parseDouble(result.split("x")[1].split("@")[1].split("kg")[0].replaceAll("\\s", ""));
                long reps = Integer.parseInt(result.split("x")[1].split("@")[0].replaceAll("\\s", ""));
                long sets = Integer.parseInt(result.split("x")[0].replaceAll("\\s", ""));
                Date date = new Date(temp[0]);
                liftRepository.save(new Lift(liftName, weight, reps, sets, date));
            }
            lineNr += 1;
        }
        bufferedReader.close();

        return liftService.findAll();
    }
}