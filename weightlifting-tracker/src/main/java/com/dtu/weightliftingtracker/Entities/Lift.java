package com.dtu.weightliftingtracker.Entities;


import javax.persistence.*;
@Entity
@Table(name = "lifts")
public class Lift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long itemID;

    private String liftName;

    public Lift() {

    }

    public Lift(long itemID, String liftName) {
        this.itemID = itemID;
        this.liftName = liftName;
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
}
