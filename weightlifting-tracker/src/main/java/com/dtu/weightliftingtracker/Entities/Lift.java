package com.dtu.weightliftingtracker.Entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lifts")
public class Lift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long itemID;

    private String liftName;
    private double weight;
    private long reps;
    private long sets;
    private Date logTime;

    public Lift() {

    }

    public Lift(String liftName, double weight, long reps, long sets, Date logTime) {
        this.liftName = liftName;
        this.weight = weight;
        this.reps = reps;
        this.sets = sets;
        this.logTime = logTime;
    }

    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public String getLiftName() {
        return liftName;
    }

    public void setLiftName(String liftName) {
        this.liftName = liftName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public long getReps() {
        return reps;
    }

    public void setReps(long reps) {
        this.reps = reps;
    }

    public long getSets() {
        return sets;
    }

    public void setSets(long sets) {
        this.sets = sets;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
}
