package com.example.ltwebfs;

public class test {
    public static void main(String[] args) {
        String str= "41/13/41";
        String[] tokens = str.split("/");
        for(int i =0;i<tokens.length; i++) {
            System.out.println(tokens[i]);
        }
        System.out.println(tokens.length);
    }

}
