package com.testUtilities;

public class TestData {

    private static String [] originPlace = {"DXB","AUH","SHJ","JED","RUH"};

    private static String [] destinationPlace = {"AMM","CAI","DEL","KHI","PAR"};

    public static String generateOrigin(){
        int randomValue = (int) (Math.random() * 5);
        return originPlace[randomValue];
    }

    public static String generateDestination(){
        int randomValue = (int) (Math.random() * 5);
        return destinationPlace[randomValue];
    }
}
