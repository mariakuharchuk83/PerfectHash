package com.lab1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<RationalNumber> nums = readValues();
        nums = nums.stream().distinct().collect(Collectors.toList());
        HashTable hashTable = new HashTable(nums);
        hashTable.print();
        if(hashTable.contains(new RationalNumber(123, 5))){
            System.out.print("Contains\n");
        }else{
            System.out.print("NO Contains\n");
        }
    }

    private static List<RationalNumber> readValues() {
        try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/song.txt"))) {
            List<RationalNumber> nums = new LinkedList<>();
            String str;
            while ((str = br.readLine()) != null) {
            	String[] numAndDenum = str.split("/");
            	nums.add(new RationalNumber(Integer.parseInt(numAndDenum[0]), Integer.parseInt(numAndDenum[1])));
            }
            return nums;
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}