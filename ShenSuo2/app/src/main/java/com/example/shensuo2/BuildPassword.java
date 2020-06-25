package com.example.shensuo2;

import java.util.Random;

/**
 * 目标实现，通过这个模块实现随机生成只有数字、字母、字符
 * 数字和字母组合、数字和字符组合、字母和字符组合
 * 数字、字母和字符的三个字符的组合
 */

public class BuildPassword {

    //100 010 001 110 101 011 111有七种可能
    int shuzi;
    int zimu;
    int fuhao;
    int pwLength; //密码长度
    private Random RANDOM = new Random();//随机数对象
    // 因为伪随机数random在nextInt()的时候会出现向更小的数偏离的情况，所以用一个randomMax来修正
    private static final int randomMax = 100000000;
    // ascii码表数字从48开始
    private static final int nunberIndex = 48;
    // 数组下标0-9代表10个数字
    private static final int numberMax = 9;
    // 一共26个字母
    private static final int numberOfLetter = 26;
    // 大写字母ascii码表从65开始
    private static final int capitalIndex = 65;
    // 小写字母ascii码表从65开始
    private static final int lowercaseIndex = 97;
    // 特殊字符集，第一个数字代表ascii码表序号，第二个代表从这里开始一共使用多少个字符
    private static final int[][] specialCharacter = {{33, 14}, {58, 6}, {91, 5}, {123, 3}};
    public BuildPassword(int shuzi,int zimu,int fuhao,int pwLength){
        this.shuzi = shuzi;
        this.zimu = zimu;
        this.fuhao = fuhao;
        this.pwLength = pwLength;
    }

    public String returnPassword(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < pwLength ; i++) {
            builder.append((char)getNextChar());
        }
        return builder.toString();
    }

    private int getNextChar(){
        if (shuzi == 1&&zimu == 0&&fuhao == 0){
            //这个是只有数字的情况
            int justNumber = RANDOM.nextInt(randomMax)%10 + 48;
            return justNumber;
        }else if (shuzi == 0&&zimu == 1&&fuhao == 0){
            //只有字母的情况
            int justLetter = RANDOM.nextInt(randomMax)%26;
            if (justLetter%2 == 0){
                //如果justLetter是双数就返回大写字母
                return justLetter+65;
            }else {
                //如果justLetter是单数就返回小写字母
                return justLetter+97;
            }
        }else if (shuzi == 0&&zimu == 0&&fuhao == 1){
            //这个是只有符号的情况
            int justSymbol;
            int whichIsSelect = RANDOM.nextInt(randomMax)%4;
            if (whichIsSelect == 0){
                justSymbol = RANDOM.nextInt(randomMax)%specialCharacter[0][1]+specialCharacter[0][0];
                return justSymbol;
            }else if (whichIsSelect == 1){
                justSymbol = RANDOM.nextInt(randomMax)%specialCharacter[1][1]+specialCharacter[1][0];
                return justSymbol;
            }else if (whichIsSelect == 2){
                justSymbol = RANDOM.nextInt(randomMax)%specialCharacter[2][1]+specialCharacter[2][0];
                return justSymbol;
            }else {
                justSymbol = RANDOM.nextInt(randomMax)%specialCharacter[3][1]+specialCharacter[3][0];
                return justSymbol;
            }
        }else if (shuzi == 1&&zimu == 1&&fuhao == 0){
            //数字和字母的情况
            int whichIsSelect = RANDOM.nextInt(randomMax)%3;
            if(whichIsSelect == 0){
                //如何whichIsSelect等于“0”的话，就选中数字
                return RANDOM.nextInt(randomMax)%10 + 48;
            }else if (whichIsSelect == 1){
                //如何whichIsSelect等于“1”的话，就选中大写字母
                return RANDOM.nextInt(randomMax)%26+65;
            }else {
                //如何whichIsSelect等于“0”的话，就选中小写字母
                return RANDOM.nextInt(randomMax)%26+97;
            }
        }else if (shuzi == 1&&zimu == 0&&fuhao == 1){
            //只有数字和符号的情况
            int whichIsSelect = RANDOM.nextInt(randomMax)%5;
            if (whichIsSelect == 0){
                return RANDOM.nextInt(randomMax)%10+48;
            }else if (whichIsSelect == 1){
                return RANDOM.nextInt(randomMax)%specialCharacter[0][1]+specialCharacter[0][0];
            }else if (whichIsSelect == 2){
                return RANDOM.nextInt(randomMax)%specialCharacter[1][1]+specialCharacter[1][0];
            }else if (whichIsSelect == 3){
                return RANDOM.nextInt(randomMax)%specialCharacter[2][1]+specialCharacter[2][0];
            }else {
                return RANDOM.nextInt(randomMax)%specialCharacter[3][1]+specialCharacter[3][0];
            }
        }else if (shuzi == 0&&zimu == 1&&fuhao == 1){
            //只有字母和符号的情况
            int whichIsSelect = RANDOM.nextInt(randomMax)%6;
            if (whichIsSelect == 0){
                //选大写字母
                return RANDOM.nextInt(randomMax)%26+65;
            }else if (whichIsSelect == 1){
                //选小写字母
                return RANDOM.nextInt(randomMax)%26+97;
            }else if (whichIsSelect == 2){
                return RANDOM.nextInt(randomMax)%specialCharacter[0][1]+specialCharacter[0][0];
            }else if (whichIsSelect == 3){
                return RANDOM.nextInt(randomMax)%specialCharacter[1][1]+specialCharacter[1][0];
            }else if (whichIsSelect == 4){
                return RANDOM.nextInt(randomMax)%specialCharacter[2][1]+specialCharacter[2][0];
            }else {
                return RANDOM.nextInt(randomMax)%specialCharacter[3][1]+specialCharacter[3][0];
            }
        }else if(shuzi == 1&&zimu == 1&&fuhao == 1){
            //三种字符都有的情况
            int whichIsSelect = RANDOM.nextInt(randomMax)%7;
            if (whichIsSelect == 0){
                //选择数字
                return RANDOM.nextInt(randomMax)%10 + 48;
            }else if (whichIsSelect == 1){
                //选择大写字母
                return RANDOM.nextInt(randomMax)%26+65;
            }else if (whichIsSelect == 2){
                //选择小写字母
                return RANDOM.nextInt(randomMax)%26+97;
            }else if (whichIsSelect == 3){
                return RANDOM.nextInt(randomMax)%specialCharacter[0][1]+specialCharacter[0][0];
            }else if (whichIsSelect == 4){
                return RANDOM.nextInt(randomMax)%specialCharacter[1][1]+specialCharacter[1][0];
            }else if (whichIsSelect == 5){
                return RANDOM.nextInt(randomMax)%specialCharacter[2][1]+specialCharacter[2][0];
            }else{
                return RANDOM.nextInt(randomMax)%specialCharacter[3][1]+specialCharacter[3][0];
            }
        }
        return 0;
    }
}
