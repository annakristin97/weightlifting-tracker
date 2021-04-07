package com.dtu.weightliftingtracker.Entities;

import javax.persistence.*;

/**
 * Entity for a single Lift
 */
@Entity
@Table(name = "lifts")
public class Lift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long itemID;
    @Column
    private String liftName;
    @Column
    private double weight;
    @Column
    private long reps;
    @Column
    private long sets;
    @Column
    private long logTime;

    public Lift() {

    }

    public Lift(String liftName, double weight, long reps, long sets, long logTime) {
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

    public long getLogTime() {
        return logTime;
    }

    public void setLogTime(long logTime) {
        this.logTime = logTime;
    }
}
