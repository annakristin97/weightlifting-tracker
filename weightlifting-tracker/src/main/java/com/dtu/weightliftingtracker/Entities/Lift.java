package com.dtu.weightliftingtracker.Entities;


import javax.persistence.*;
@Entity
@Table(name = "lifts")
public class Lift {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long itemID;

    private long itemSellerID;
    private long itemBuyerID; //0 ef enginn búinn að kaupa
    private String itemName;
    private String itemDescription;
    private long itemPrice;
    private String itemType; //Peysa, buxur,..
    private String itemSize;
    private String itemColor;
    private String itemBrand;
    private String itemCondition; //Gott, slæmt, ágætt,..
    private String itemGender;

    public String image1;
    public String image2;
    public String image3;
    public String image4;

    public Lift() {
    }

    public Lift(long itemSellerID, long itemBuyerID, String itemName, String itemDescription, long itemPrice, String itemType, String itemSize, String itemColor, String itemBrand, String itemCondition, String itemGender) {
        this.itemSellerID = itemSellerID;
        this.itemBuyerID = itemBuyerID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemType = itemType;
        this.itemSize = itemSize;
        this.itemColor = itemColor;
        this.itemBrand = itemBrand;
        this.itemCondition = itemCondition;
        this.itemGender = itemGender;
    }

    public Lift(long itemSellerID, long itemBuyerID, String itemName, String itemDescription, long itemPrice, String itemType, String itemSize, String itemColor, String itemBrand, String itemCondition, String itemGender, String image1, String image2, String image3, String image4) {
        this.itemSellerID = itemSellerID;
        this.itemBuyerID = itemBuyerID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemType = itemType;
        this.itemSize = itemSize;
        this.itemColor = itemColor;
        this.itemBrand = itemBrand;
        this.itemCondition = itemCondition;
        this.itemGender = itemGender;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
    }

    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public long getItemSellerID() {
        return itemSellerID;
    }

    public void setItemSellerID(long itemSellerID) {
        this.itemSellerID = itemSellerID;
    }

    public long getItemBuyerID() {
        return itemBuyerID;
    }

    public void setItemBuyerID(long itemBuyerID) {
        this.itemBuyerID = itemBuyerID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public String getItemColor() {
        return itemColor;
    }

    public void setItemColor(String itemColor) {
        this.itemColor = itemColor;
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public void setItemBrand(String itemBrand) {
        this.itemBrand = itemBrand;
    }

    public String getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(String itemCondition) {
        this.itemCondition = itemCondition;
    }

    public String getItemGender() {
        return itemGender;
    }

    public void setItemGender(String itemGender) {
        this.itemGender = itemGender;
    }*/
}
