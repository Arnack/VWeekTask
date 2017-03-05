package ru.third.inno.task.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yy on 05.03.17.
 */


public class Salter {
    static Logger logger = Logger.getLogger(Salter.class);
    public static String toSalt(String first,String second){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            String changedFirst = new StringBuilder(first).reverse().toString();

            md.update((
                    second.substring(0,1)
                    + changedFirst
                    + second.substring(1,2)
                    + "U41CoWkvIl@UkYA0"
                    + second.substring(2, second.length())
            ).getBytes("UTF-8"));


            String resultPass = DigestUtils.md5Hex(
                    second.substring(0,1)
                            + changedFirst
                            + second.substring(1,2)
                            + "U41CoWkvIl@UkYA0"
                            + second.substring(2, second.length())
            );

            logger.error(" second.substring(0,1): " +  second.substring(0,1));
            logger.error(" second.substring(1,2): " +  second.substring(1,2));
            logger.error(" second.substring(2, second.length()): " +  second.substring(2, second.length()));
            logger.error("hashed" + md.digest().toString());

           // return  DatatypeConverter.printHexBinary(md.digest());
            return resultPass;

        } catch (NoSuchAlgorithmException e) {
            logger.error("cant salt " + e);
        } catch (UnsupportedEncodingException e) {
            logger.error("cant salt " + e);
        }

        return second.substring(0,1)
                + new StringBuilder(first).reverse().toString()
                + second.substring(1,2)
                + "$U41CoWk^vIl@UkYA0@"
                + second.substring(2, second.length());
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }
}
