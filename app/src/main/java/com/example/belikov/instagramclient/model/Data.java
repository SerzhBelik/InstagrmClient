package com.example.belikov.instagramclient.model;

import java.util.ArrayList;
import java.util.List;

class Data {
    private List<String> fruitList;
    private List<String> vegetablesList;
    private List<String> natureList;

    private int[] fruit;
    private int[] vegetables;
    private int[] nature;

    public int[] getFruit() {
        return fruit;
    }

    public int[] getVegetables() {
        return vegetables;
    }

    public int[] getNature() {
        return nature;
    }

    public Data(){
        fruitList = new ArrayList<>();
        fruitList.add("Ананас");
        fruitList.add("Апельсин");
        fruitList.add("Киви");
        fruitList.add("Манго");
        fruitList.add("Яблоко");
        fruitList.add("Лимон");
        fruitList.add("Груша");
        fruitList.add("Мандарин");

        vegetablesList = new ArrayList<>();
        vegetablesList.add("Морковь");
        vegetablesList.add("Огурец");
        vegetablesList.add("Капуста");
        vegetablesList.add("Картофель");


        natureList = new ArrayList<>();
        natureList.add("Горы");
        natureList.add("Лес");
        natureList.add("Море");
        natureList.add("Долина");
//
//        fruit = new int[]{R.drawable.fruit1, R.drawable.fruit2, R.drawable.fruit3, R.drawable.fruit4};
//        vegetables = new int[]{R.drawable.veg1, R.drawable.veg2, R.drawable.veg3, R.drawable.veg4};
//        nature = new int[]{R.drawable.nat1, R.drawable.nat2, R.drawable.nat3, R.drawable.nat4};

    }

    public List<String> getNatureList() {
        return natureList;
    }

    public List<String> getVegetablesList() {

        return vegetablesList;
    }

    public List<String> getFruitList(){
        return fruitList;
    }
}
