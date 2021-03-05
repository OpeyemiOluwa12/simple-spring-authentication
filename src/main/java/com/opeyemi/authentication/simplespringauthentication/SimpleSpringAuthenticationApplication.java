package com.opeyemi.authentication.simplespringauthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@SpringBootApplication
public class SimpleSpringAuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleSpringAuthenticationApplication.class, args);
        generatePassword();
    }

    public static void generatePassword() {

        System.out.println("Opeyemi MD5: " + createMD5SecurePassword("opeyemi", null));
        System.out.println("Opeyemi MD5: " + createMD5SecurePassword("opeyemi", null));
        System.out.println("Opeyemi MD5: " + createMD5SecurePassword("deoluwa", generateSalt()));
        System.out.println("Opeyemi MD5: " + createMD5SecurePassword("deoluwa", generateSalt()));


        System.out.println("Opeyemi SHA-256: " + createSHA256SecurePassword("opeyemi", null));
        System.out.println("Opeyemi SHA-256: " + createSHA256SecurePassword("opeyemi", null));
        System.out.println("Opeyemi SHA-256: " + createSHA256SecurePassword("deoluwa", generateSalt()));
        System.out.println("Opeyemi SHA-256: " + createSHA256SecurePassword("deoluwa", generateSalt()));
    }

    private static byte[] generateSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    private static String createMD5SecurePassword(String passwordToHash, byte[] salt) {

        String generatedPassword = null;

        try {
            MessageDigest messageDigest;
            messageDigest = MessageDigest.getInstance("MD5");

            if (salt != null)
                messageDigest.update(salt);
            byte[] bytes = messageDigest.digest(passwordToHash.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static String createSHA256SecurePassword(String passwordToHash, byte[] salt) {

        String generatedPassword = null;

        try {
            MessageDigest messageDigest;
            messageDigest = MessageDigest.getInstance("SHA-256");

            if (salt != null)
                messageDigest.update(salt);
            byte[] bytes = messageDigest.digest(passwordToHash.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

}


