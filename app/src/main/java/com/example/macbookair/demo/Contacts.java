package com.example.macbookair.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by macbookair on 12/17/17.
 */

public class Contacts {

    private static List<Integer> images;
    private static List<String> phone_numbers;
    private static List<String> e_mails;
    private static List<String> names;

    static {

        images=new ArrayList<>();
        phone_numbers=new ArrayList<>();
        e_mails=new ArrayList<>();
        names=new ArrayList<>();

        images.addAll(Arrays.asList(R.drawable.martin, R.drawable.yervand, R.drawable.bob, R.drawable.bucky, R.drawable.steve, R.drawable.joney_ive));
        names.addAll(Arrays.asList("Martin", "Yervand", "Bob", "Bucky", "Steve", "Joney"));
        phone_numbers.addAll(Arrays.asList("098179899", "094880619", "041179899", "077727088", "098524242", "077587088"));
        e_mails.addAll(Arrays.asList("mart9899@mail.ru", "yervandalexanyan@gmail.com", "bobhamster@gmail.com", "buckyroberts@gmail.com", "jobs@icloud.com", "joneyive@icloud.com"));


    }

    public static void addItem(String name, String phone) {
        names.add(name);
        phone_numbers.add(phone);
        e_mails.add(name+"@inbox.ru");
        images.add(R.drawable.unknown_person);
    }

    public static List<Integer> getImages() {
        return images;
    }

    public static List<String> getPhone_numbers() {
        return phone_numbers;
    }

    public static List<String> getE_mails() {
        return e_mails;
    }

    public static List<String> getNames() {
        return names;
    }
}
