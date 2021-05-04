package com.dtu.weightliftingtracker.Controllers;

import com.dtu.weightliftingtracker.Entities.Lift;
import com.dtu.weightliftingtracker.Repositories.LiftRepository;
import com.dtu.weightliftingtracker.Services.LiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

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
     * Get ALL lifts
     * @return
     */
    @RequestMapping("/lifts")
    public List<Lift> getLifts() {
        System.out.println("get lifts");
        return liftService.findAll();
    }

    @RequestMapping("/lifts/search")
    public List<Lift> searchLifts(@RequestParam String timeInterval, @RequestParam String liftName, @RequestParam String sets, @RequestParam String reps) {
        List<Lift> lifts;
        List<Lift> setsLifts;
        List<Lift> repsLifts;
        List<Lift> typeLifts;

        if(sets.equals("All")) {
            setsLifts = liftService.findAll();
            lifts = new ArrayList<>(setsLifts);
        } else {
            long set_nr = Long.parseLong(sets);
            setsLifts = liftService.findBySets(set_nr);
            lifts = new ArrayList<>(setsLifts);
        }

        if(!reps.equals("All")) {
            long rep_nr = Long.parseLong(reps);
            repsLifts = liftService.findBySets(rep_nr);
            lifts.retainAll(repsLifts);
        }

        //contains liftName
        if(!liftName.equals("All")) {
            typeLifts = liftService.findByLiftNameContains(liftName);
            lifts.retainAll(typeLifts);
        }

        Calendar from = Calendar.getInstance();

        if(timeInterval.equals("Week")) from.add(Calendar.WEEK_OF_YEAR, -1);
        else if(timeInterval.equals("Month")) from.add(Calendar.MONTH, -1);
        else if(timeInterval.equals("3 Months")) from.add(Calendar.MONTH, -3);
        else if(timeInterval.equals("Year")) from.add(Calendar.YEAR, -1);
        else if(timeInterval.equals("5 Years")) from.add(Calendar.YEAR, -5);
        else from.add(Calendar.YEAR, -50);
        List<Lift> timeLifts =  liftService.getNewerThan(from.getTimeInMillis());

        lifts.retainAll(timeLifts);

        return lifts;
    }

    @RequestMapping("/lifts/add")
    public Lift addLift(@RequestParam String liftName,
                              @RequestParam double weight,
                              @RequestParam long sets,
                              @RequestParam long reps) {

        Calendar now = Calendar.getInstance();

        Lift newLift = new Lift(liftName, weight, reps, sets, now.getTimeInMillis());
        liftRepository.save(newLift);

        return newLift;
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

        List<String> distinctLifts = new ArrayList<>(Arrays.asList(new String[]{"Deadlift", "Front Squat", "Back Squat", "Bench Press", "Shoulder Press", "Push Press", "Push Jerk", "Snatch", "Clean"}));

        // Read all lines in from CSV file and add to lift repository
        FileReader fileReader = new FileReader(new File("./src/main/resources/static/lifting_data_anna2.csv"));
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

                Lift lift = new Lift(liftName, weight, reps, sets, date.getTime()+(2*Long.parseLong("15525000000")));

                distinctLifts.forEach(name -> {
                    if(lift.getLiftName().contains(name)) {
                        lift.setLiftName(name);
                        // save log as a single Lift in our DB
                        liftRepository.save(lift);
                    }
                });
            }
            lineNr += 1;
        }
        bufferedReader.close();

        return liftService.findAll();
    }

    @RequestMapping("/lifts/pr")
    public List<Lift> getPrLifts() {
        List<Lift> prLifts = new ArrayList<>();

        // distinct lift names i DB
        List<String> distinctLifts = new ArrayList<>(Arrays.asList(getDistinctLiftNames()));
        // iterate i gegnum öll lift names..
        List<Lift> finalPrLifts = prLifts;
        distinctLifts.forEach(liftName -> {
            // ..finna allar liftur með þetta lift name
            List<Lift> lifts = liftRepository.findByLiftNameContains(liftName);

            if(lifts != null && lifts.size() > 0) {
                // ..finna max af þeim
                Lift prLift = Collections.max(lifts, Comparator.comparingDouble(Lift::getWeight));

                prLift.setLiftName(liftName);
                // ..bæta henni við pr listann okkar
                finalPrLifts.add(prLift);
            }
        });

        prLifts = finalPrLifts.stream()
                .sorted(Comparator.comparing(Lift::getLiftName)).collect(Collectors.toList());

        // skila pr listanum
        return prLifts;
    }

    @RequestMapping("/lifts/liftnames")
    public String[] getDistinctLiftNames() {
        return liftRepository.getDistinctLiftNames().toArray(new String[0]);
    }
}