package com.example.shensuo;

import java.util.Random;

public class PasswordBuilder {

    //符号ASCII码数组
    int symbol [] = new int[]{
            33,34,35,36,37,38,39,40,
            41,42,43,44,45,46,47,58,
            59,60,61,62,63,64,91,92,
            93,94,95,96,123,124,125,
            126
    };

    //数字ASCII码数组
    int number [] = new int[]{
            48,49,50,51,52,53,54,55,
            56,57
    };

    //字母ASCII码数组
    int letter[] = new int[]{
            65,66,67,68,69,70,71,72,
            73,74,75,76,77,78,79,80,
            81,82,83,84,85,86,87,88,
            89,90,97,98,99,100,101,
            102,103,104,105,106,107,
            108,109,110,111,112,113,
            114,115,116,117,118,119,
            120,121,122
    };

    //字符和数字ASCII码数组
    int symbolNumber[] = new int[]{
            33,34,35,36,37,38,39,40,
            41,42,43,44,45,46,47,58,
            59,60,61,62,63,64,91,92,
            93,94,95,96,123,124,125,
            126,48,49,50,51,52,53,54,55,
            56,57
    };


    //字符和字母ASCII码数组
    int symbolLetter[] = new int[]{
            33,34,35,36,37,38,39,40,
            41,42,43,44,45,46,47,58,
            59,60,61,62,63,64,91,92,
            93,94,95,96,123,124,125,
            126,
            65,66,67,68,69,70,71,72,
            73,74,75,76,77,78,79,80,
            81,82,83,84,85,86,87,88,
            89,90,97,98,99,100,101,
            102,103,104,105,106,107,
            108,109,110,111,112,113,
            114,115,116,117,118,119,
            120,121,122
    };

    //数字和字母ASCII码数组
    int numberLetter[] = new int[]{
            48,49,50,51,52,53,54,55,
            56,57,
            65,66,67,68,69,70,71,72,
            73,74,75,76,77,78,79,80,
            81,82,83,84,85,86,87,88,
            89,90,97,98,99,100,101,
            102,103,104,105,106,107,
            108,109,110,111,112,113,
            114,115,116,117,118,119,
            120,121,122
    };

    ////字符、数字和字母ASCII码数组
    int symbolNumberLetter[] = new int[]{
            48,49,50,51,52,53,54,55,
            56,57,
            65,66,67,68,69,70,71,72,
            73,74,75,76,77,78,79,80,
            81,82,83,84,85,86,87,88,
            89,90,97,98,99,100,101,
            102,103,104,105,106,107,
            108,109,110,111,112,113,
            114,115,116,117,118,119,
            120,121,122,
            33,34,35,36,37,38,39,40,
            41,42,43,44,45,46,47,58,
            59,60,61,62,63,64,91,92,
            93,94,95,96,123,124,125,
            126
    };

    public String getPW(int s,int n,int l,int pwlength){
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        if (s==1&&n==0&&l==0){
            for (int i = 0; i < pwlength; i++) {
                int index = random.nextInt(symbol.length);
                char ch = (char)symbol[index];
                builder.append(ch);
            }
            return builder.toString();
        }else if (s==0&&n==1&&l==0){
            for (int i = 0; i < pwlength; i++) {
                int index = random.nextInt(number.length);
                char ch = (char)number[index];
                builder.append(ch);
            }
            return builder.toString();
        }else if (s==0&&n==0&&l==1){
            for (int i = 0; i < pwlength; i++) {
                int index = random.nextInt(letter.length);
                char ch = (char)letter[index];
                builder.append(ch);
            }
            return builder.toString();
        }else if (s==1&&n==1&&l==0){
            for (int i = 0; i < pwlength; i++) {
                int index = random.nextInt(symbol.length+number.length);
                char ch = (char)symbolNumber[index];
                builder.append(ch);
            }
            return builder.toString();
        }else if (s==0&&n==1&&l==1){
            for (int i = 0; i < pwlength; i++) {
                int index = random.nextInt(letter.length+number.length);
                char ch = (char)numberLetter[index];
                builder.append(ch);
            }
            return builder.toString();
        }else if (s==1&&n==0&&l==1){
            for (int i = 0; i < pwlength; i++) {
                int index = random.nextInt(letter.length+symbol.length);
                char ch = (char)symbolLetter[index];
                builder.append(ch);
            }
            return builder.toString();
        }else if (s==1&&n==1&&l==1){
            for (int i = 0; i < pwlength; i++) {
                int index = random.nextInt(letter.length+symbol.length+number.length);
                char ch = (char)symbolNumberLetter[index];
                builder.append(ch);
            }
            return builder.toString();
        }else {
            return "";
        }
    }
}
