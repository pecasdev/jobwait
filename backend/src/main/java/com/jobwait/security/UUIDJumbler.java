package com.jobwait.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class UUIDJumbler {
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // NoSuchAlgorithmException should never throw because the MessageDigest
    // algorithm exists...
    public static String jumbleUUID(UUID someID) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            byte[] hashbytes = digest.digest(
                    someID.toString().getBytes(StandardCharsets.UTF_8));
            final String sha3Hex = bytesToHex(hashbytes);
            return sha3Hex;
        } catch (NoSuchAlgorithmException exception) {
            throw SecurityFaults.GenericAuthenticationFault();
        }
    }
}
