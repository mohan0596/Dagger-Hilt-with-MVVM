package com.example.sampletest;

import java.util.Scanner;

public class ChoiceMatch {

    public static void main(String[] args){
        System.out.println("Enter Bob String");
        Scanner sc = new Scanner(System.in);
        String bobChoice = sc.next();

        System.out.println("Enter Alice String");
        String aliceChoice = sc.next();
        int match = 0;

        for (int i = 0 ; i < aliceChoice.length() ; i++){
            if(bobChoice.charAt(i) == aliceChoice.charAt(i)){
                match++;
            }
        }

        System.out.println(match);
    }
}
