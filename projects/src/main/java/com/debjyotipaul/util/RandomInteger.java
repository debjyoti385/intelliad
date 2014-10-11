package com.debjyotipaul.util;

/**
 * Created with IntelliJ IDEA.
 * User: debjyoti.paul
 * Date: 10/11/14
 * Time: 12:12 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.Random;

/** Generate 10 random integers in the range 0..99. */
public final class RandomInteger {

    public static final void main(String... aArgs){
        log("Generating 10 random integers in range 0..49.");

        //note a single Random object is reused here
        Random randomGenerator = new Random();
        for (int idx = 1; idx <= 10; ++idx){
            int randomInt = randomGenerator.nextInt(500);
            log("Generated : " + randomInt);
        }

        log("Done.");
    }

    private static void log(String aMessage){
        System.out.println(aMessage);
    }
}
