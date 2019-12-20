package cn.com.kaituo.smart_community.common;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

@Service
public class RandomGenerator {
    private static final Random random=new Random(System.currentTimeMillis());

    public static String generateToken(String username) {
        return UUID.nameUUIDFromBytes((username + String.valueOf(Calendar.getInstance().getTimeInMillis())).getBytes()).toString();
    }
    public static int getRandom(int max){
        return random.nextInt(max);
    }
    public static boolean getRandomBoolean(){
        return random.nextBoolean();
    }
}