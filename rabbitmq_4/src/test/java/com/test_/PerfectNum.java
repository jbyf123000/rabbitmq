package com.test_;

import org.junit.Test;

import java.util.*;

public class PerfectNum {
    @Test
    public void test1() {
        System.out.println(isPerfectNum(2));
        System.out.println(isPerfectNum(6));
        System.out.println(isPerfectNum(28));
        System.out.println(isPerfectNum(496));
        System.out.println(isPerfectNum(8128));

    }

    public static boolean isPerfectNum(Integer num) {
        int count = 0;

        for (int i = 1; i <= num; i++) {
            for (int j = 1; j <= num; j++) {
                if (i * j == num) {
                    count += i;
                    count += j;
                    break;
                }
            }
        }
        return count / num == 4;
    }

    public static boolean isPerfectNum2(Integer num) {
        if(num == 6){
            return true;
        }else {

        }
        return false;
    }
}
