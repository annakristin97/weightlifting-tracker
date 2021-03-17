package com.dtu.weightliftingtracker.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LiftController {
    /*private ItemService itemService;
    private ItemRepository itemRepository;

    @Autowired
    public LiftController(ItemService itemService, ItemRepository itemRepository) {
        this.itemService = itemService;
        this.itemRepository = itemRepository;
    }


    @RequestMapping("/Items")
    public List<Item> itemsGET() {
        return itemService.findAll();
    }

    @RequestMapping(value = "/newItem", method = RequestMethod.POST)
    public Item newItem(@RequestParam String itemSellerID,
                        @RequestParam String itemBuyerID,
                        @RequestParam String itemName,
                        @RequestParam String itemDescription,
                        @RequestParam String itemPrice,
                        @RequestParam String itemType,
                        @RequestParam String itemSize,
                        @RequestParam String itemColor,
                        @RequestParam String itemBrand,
                        @RequestParam String itemCondition,
                        @RequestParam String itemGender) {
        return itemRepository.save(new Item(Long.parseLong(itemSellerID), Long.parseLong(itemBuyerID), itemName, itemDescription, Long.parseLong(itemPrice), itemType, itemSize, itemColor, itemBrand, itemCondition, itemGender, "new_photo", "new_photo", "new_photo", "new_photo"));
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<Item> searchItems(@RequestParam String itemPrice,
                                  @RequestParam String itemType,
                                  @RequestParam String itemSize,
                                  @RequestParam String itemColor,
                                  @RequestParam String itemBrand,
                                  @RequestParam String itemGender) {

        List<Item> priceItems;
        List<Item> typeItems;
        List<Item> sizeItems;
        List<Item> colorItems;
        List<Item> brandItems;
        List<Item> genderItems;

        if (itemPrice.equals("ALL")) {
            priceItems = itemService.findAll();
        } else {
            priceItems = itemService.findByitemPriceLessThanEqual(Long.parseLong(itemPrice));
        }

        if (itemType.equals("ALL")) {
            typeItems = itemService.findAll();
        } else {
            typeItems = itemService.findByitemType(itemType);
        }

        if (itemSize.equals("ALL")) {
            sizeItems = itemService.findAll();
        } else {
            sizeItems = itemService.findByitemSize(itemSize);
        }

        if (itemColor.equals("ALL")) {
            colorItems = itemService.findAll();
        } else {
            colorItems = itemService.findByitemColor(itemColor);
        }

        if (itemBrand.equals("ALL")) {
            brandItems = itemService.findAll();
        } else {
            brandItems = itemService.findByitemBrand(itemBrand);
        }

        if (itemGender.equals("ALL")) {
            genderItems = itemService.findAll();
        } else {
            genderItems = itemService.findByitemGender(itemGender);
        }

        List<Item> items = new ArrayList<>(priceItems);

        items.retainAll(priceItems);
        items.retainAll(typeItems);
        items.retainAll(sizeItems);
        items.retainAll(colorItems);
        items.retainAll(brandItems);
        items.retainAll(genderItems);

        return items;
    }

    @RequestMapping(value = "/itemPage/{pid}", method = RequestMethod.GET)
    public Item itemPage(@PathVariable("pid") long pid) {
        Item item = itemService.findByitemID(pid).orElseThrow(() -> new IllegalArgumentException("Invalid item ID"));
        return item;
    }

    @Transactional
    @RequestMapping(value = "/deleteBybuyerID", method = RequestMethod.POST)
    public String deleteBybuyerID(@RequestParam String buyerID) {
        itemRepository.deleteByitemBuyerID(Long.parseLong(buyerID));
        return "Items deleted!!";
    }

    @Transactional
    @RequestMapping(value = "/emptyBybuyerID", method = RequestMethod.POST)
    public String emptyBybuyerID(@RequestParam String buyerID) {
        itemRepository.removeBuyerID(0, Long.parseLong(buyerID));
        return "Items removed from cart!!";
    }

    @RequestMapping(value = "/changeBuyerID", method = RequestMethod.POST)
    public String changeBuyerID(@RequestParam String itemID, @RequestParam String buyerID) {
        itemRepository.changeBuyerID(Long.parseLong(itemID), Long.parseLong(buyerID));
        return "BuyerID changed";
    }

    @RequestMapping("/resetItems")
    public List<Item> resetItems() {
        itemRepository.deleteAll();
        itemRepository.save(new Item(1, 0, "Tommy Sweater", "Tommy sweater  in good condition.", 3000, "Sweaters", "Medium", "Grey", "Tommy Hilfiger", "Good", "Female", "tommy1_photo1", "tommy1_photo2", "tommy1_photo3", "tommy1_photo4"));
        itemRepository.save(new Item(1, 0, "Nike Sweater", "Nike sweater, small stain on shoulder.", 2000, "Sweaters", "Small", "Grey", "Nike", "OK", "Male", "nike_photo2", "nike_photo2", "nike_photo3", "nike_photo4"));
        itemRepository.save(new Item(2, 0, "Ralph Lauren Pants", "Ralph Lauren pants in medium.", 4000, "Pants", "Medium", "Brown", "Ralph Lauren", "Good", "Male", "rl1_photo1", "rl1_photo2", "rl1_photo3", "rl1_photo4"));
        itemRepository.save(new Item(3, 0, "Calvin Klein Shirt", "CK shirt. Good Condition.", 1000, "Shirts", "Large", "Blue", "Calvin Klein", "OK", "Male", "ck1_photo1", "ck1_photo2", "ck1_photo3", "ck1_photo4"));
        itemRepository.save(new Item(3, 0, "New Balance Shoes", "New Balance shoes in size 38. Old but in OK condition.", 500, "Shoes", "Shoes 38", "Red", "New Balance", "Bad", "Unisex", "nb1_photo1", "nb1_photo2", "nb1_photo3", "nb1_photo4"));
        itemRepository.save(new Item(2, 0, "Yellow Nike Shirt", "Yellow Nike shirt - never used.", 2500, "Shirts", "Small", "Yellow", "Nike", "New", "Male", "nike2_photo1", "nike2_photo2", "nike2_photo3", "nike2_photo4"));
        itemRepository.save(new Item(1, 0, "Red Nike Shirt", "Red Nike shirt - small tear on shoulder", 500, "Shirts", "Medium", "Red", "Nike", "Bad", "Male", "nike3_photo1", "nike3_photo2", "nike3_photo3", "nike3_photo4"));
        itemRepository.save(new Item(3, 0, "Blue Nike Shirt", "Blue Nike shirt in good condition.", 1000, "Shirts", "Extra Large+", "Blue", "Nike", "Good", "Male", "nike4_photo1", "nike4_photo2", "nike4_photo3", "nike4_photo4"));
        itemRepository.save(new Item(4, 0, "Timberland Boots", "Timberland shoes. 3 years old but always been in good care.", 5000, "Shoes", "Shoes 40", "Brown", "Timberland", "OK", "Female", "Timberland_photo1", "Timberland_photo2", "Timberland_photo3", "Timberland_photo4"));
        itemRepository.save(new Item(4, 0, "66 North Jacket", "66 North Snaefell jacket in red. Size Large but is more like medium. Been used for 2 years but in good condition.", 20000, "Outerwear", "Large", "Red", "66 north", "Good", "Female", "661_photo1", "661_photo2", "661_photo3", "661_photo4"));
        itemRepository.save(new Item(2, 0, "66 North Cap", "66 North cap in white.", 500, "Outerwear", "One-size", "White", "66 north", "Good", "Unisex", "662_photo1", "662_photo2", "662_photo3", "662_photo4"));
        itemRepository.save(new Item(5, 0, "66 North Pants", "66 North pants in black - a bit worn out.", 7000, "Pants", "Extra small", "Black", "66 north", "OK", "Female", "663_photo1", "663_photo2", "663_photo3", "663_photo4"));
        itemRepository.save(new Item(1, 0, "66 North Pants", "66 North pants in red - never used and still in packaging. Bought for 59.000 ISK but selling them for 45.000 ISK", 45000, "Outerwear", "Medium", "Red", "66 north", "New", "Unisex", "664_photo1", "664_photo2", "664_photo3", "664_photo4"));
        itemRepository.save(new Item(1, 0, "Birkenstock Sandals", "Birkenstock sandals in Brown - in good condition.", 3000, "Shoes", "Shoes 42", "Brown", "Birkenstock", "Good", "Unisex", "Birkenstock_photo1", "Birkenstock_photo2", "Birkenstock_photo3", "Birkenstock_photo4"));
        itemRepository.save(new Item(2, 0, "Adidas Sweater", "Adidas sweater in red. In OK condition.", 2500, "Sweater", "Small", "Red", "Adidas", "OK", "Female", "adidas1_photo1", "adidas1_photo2", "adidas1_photo3", "adidas1_photo4"));

        return itemService.findAll();
    }

    @RequestMapping(value = "/Image/{id}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
//        System.out.println("Image id " + id);
        File fi = new File("./src/main/resources/static/img/" + id + ".jpg");
        byte[] image = Files.readAllBytes(fi.toPath());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        return ResponseEntity.ok(image);

    }*/
}