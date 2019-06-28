package cc.southseast.test;


import java.util.Date;
import java.util.Random;

/**
 * @Author: Southseast
 * @Date: 2019/1/7 10:57 AM
 * @Version 1.0
 */
public class test {

    public static void main(String[] args) {

        Random random = new Random();

        long code = random.nextLong();

        long time = new Date().getTime() / 1000;

        System.out.println(time ^ code);

    }

}
