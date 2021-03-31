/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chloe.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
public class Change {
//    private int noOfDollars;   // 100c
//    private int noOfQuarters;  // 25c
//    private int noOfDimes;     // dime = 10 c
//    private int noOfNickels;   // nickel = 5 c
//    private int noOfPennies;   // penny = 1c
    
//    static int[] coins = {Coin.
    //static int[] coins = {100, 25, 10, 5, 1};  //duno why i made this static
    
    //Convert enum value to a string, enum array.values
    
    //static BigDecimal[] coins = {new BigDecimal("25"), new BigDecimal("10"), new BigDecimal("5"), new BigDecimal("1")};
    
    //static List <Enum> enumCoins = Arrays.asList(Coin.values());
    

    
    //private List<BigDecimal> coinList = new ArrayList<BigDecimal>(Arrays.asList(new BigDecimal("100"), new BigDecimal("25"), new BigDecimal("10"), new BigDecimal("5"), new BigDecimal("1"));
    
    
    public static BigDecimal changeDueInPennies (BigDecimal itemCost, BigDecimal money) {
        BigDecimal changeDueInPennies = (money.subtract(itemCost)).multiply(new BigDecimal("100"));
        System.out.println("Change due: $" + (changeDueInPennies.divide(new BigDecimal("100"),2,RoundingMode.HALF_UP).toString()));
        return changeDueInPennies;
    }
    
    public static Map<BigDecimal, BigDecimal> changeDuePerCoin (BigDecimal itemCost, BigDecimal money) {
        Coin[] coinEnumArray = Coin.values();
        ArrayList <String> coinStringList = new ArrayList<>();
        for (Coin coin : coinEnumArray) {
            coinStringList.add(coin.toString());
        }

          ArrayList<BigDecimal> coins = new ArrayList<BigDecimal>(); 
          for (String coin:coinStringList) {
              coins.add(new BigDecimal(coin));
          }
          
        
        BigDecimal changeDueInPennies = changeDueInPennies(itemCost, money);
        //Calculates the number of quarters, dimes, nickels and pennies due 
        //back to the user.
        BigDecimal noOfCoin;
        BigDecimal zero = new BigDecimal("0");
        //Map <coin, noOfCoin>
        Map <BigDecimal, BigDecimal> amountPerCoin = new HashMap<>();
        
        //for every coin in the array:
        for (BigDecimal coin : coins) {
            //if the change is greater than or equal to the coin amount
            if (changeDueInPennies.compareTo(coin) > 0) {
                //If the coin amounts does not exactly divide by the change amount
                if (!changeDueInPennies.remainder(coin).equals(zero)) {
                    //the number of coins of coin[i] required will be the floor division of change amount/coin
                    noOfCoin = changeDueInPennies.divide(coin,0,RoundingMode.DOWN);
                    //add the type of coin and amount of coin to the map
                    amountPerCoin.put(coin, noOfCoin);
                    //the change amount is updated to be the remaining amount
                    changeDueInPennies = changeDueInPennies.remainder(coin);
                    //if the change amount is less than or equal to 0, stop the loop
                    if (changeDueInPennies.compareTo(zero)<0) {  
                        break;
                    }
                    //if the change divided by the coin is an exact number/integer
                } else if (changeDueInPennies.remainder(coin).equals(zero)) {  //could change to just else
                    noOfCoin = changeDueInPennies.divide(coin,0,RoundingMode.DOWN);
                    amountPerCoin.put(coin, noOfCoin);
                    //if the change amount if less than or equal to 0, stop the loop
                    if ((changeDueInPennies.compareTo(zero))<0) {
                        break;
                    }
                }
            } else {
                ;  //"pass"
            }
        }
        return amountPerCoin;
    }
                                     
//    public static void main(String[] args) {
//        BigDecimal itemCost = new BigDecimal("1.27");
//        BigDecimal money = new BigDecimal("2");
//        
//        BigDecimal changeDueInPennies = changeDueInPennies(itemCost,money);
//        Map<BigDecimal, BigDecimal> changeDuePerCoin = changeDuePerCoin(changeDueInPennies);
//
//        System.out.println("the change should add to " + money.subtract(itemCost));
//        System.out.println(changeDuePerCoin.toString());
//        
//    }
}

// BIG DECIMAL:

//CompareTo returns:
// 1: when the first BigDecimal is greater than the secondBigDecimal.
// 0: when the first BigDecimal is equal to the second BigDecimal.
//-1: when the first BigDecimal is less than the second BigDecimal.