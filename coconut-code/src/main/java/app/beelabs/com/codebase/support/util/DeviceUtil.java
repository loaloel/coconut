package app.beelabs.com.codebase.support.util;

import android.os.Build;

import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;

/**
 * Created by arysuryawan on 11/15/16.
 */

public class DeviceUtil {

    public static boolean isAndroidVersion(int androidVersion, String operator){
        int version = Build.VERSION.SDK_INT;
        boolean result = false;
        switch(operator){
            case ">=":
                result = version >= androidVersion;
                break;
            case "==":
                result = version == androidVersion;
                break;
        }

        return result;
    }

    public static String getUUID() {

        return UUID.randomUUID().toString();
    }


    public static byte[] encryptedKey64() {
        // security key to encrypted the Realm
        byte[] key = new byte[64];
        new Random(42).nextBytes(key);
        return key;
    }

    // generate random string in 15 chars
    public static String randomString() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random r = new Random(); // perhaps make it a class variable so you don't make a new one every time
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            char c = chars.charAt(r.nextInt(chars.length()));
            sb.append(c);
        }
        return sb.toString();
    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
