package com.dtu.weightliftingtracker.Controllers;

import com.dtu.weightliftingtracker.Entities.Lift;
import com.dtu.weightliftingtracker.Repositories.LiftRepository;
import com.dtu.weightliftingtracker.Services.LiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
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

    /**
     * Get all lifts
     * @return
     */
    @RequestMapping("/lifts")
    public List<Lift> getLifts() {
        System.out.println("get lifts");
        return liftService.findAll();
    }

    /**
     * Get lifts by lift name
     * @param liftName "Deadlift", "Shoulder Press",..
     * @return
     */
    @RequestMapping("/lifts/liftname")
    public List<Lift> getLiftsByLiftName(@RequestParam String liftName) {
        System.out.println("get lifts by name");
        return liftService.findByliftName(liftName);
    }

    /**
     * Read in csv file and initialize db with content
     * @return
     * @throws IOException
     */
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
                double weight = Double.parseDouble(result.split("x")[1].split("@")[1].split("kg")[0].replaceAll("\\s", ""));
                long reps = Integer.parseInt(result.split("x")[1].split("@")[0].replaceAll("\\s", ""));
                long sets = Integer.parseInt(result.split("x")[0].replaceAll("\\s", ""));

                Date date = new Date(temp[0]);

                // save log as a single Lift in our DB
                liftRepository.save(new Lift(liftName, weight, reps, sets, date.getTime()));
            }
            lineNr += 1;
        }
        bufferedReader.close();

        return liftService.findAll();
    }
}