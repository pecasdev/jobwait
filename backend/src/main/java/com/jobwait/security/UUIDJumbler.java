package com.jobwait.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import com.jobwait.security.exchange.OAuthExchange;
import com.jobwait.util.Tuple;

public class UUIDJumbler {
    String someCode;
    OAuthExchange somExchange;

    UUIDJumbler(String someCode, OAuthExchange someExchange) {
        this.someCode = someCode;
        this.somExchange = someExchange;
    }

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
    public Tuple<String, UUID> getUUIDAndHash() throws NoSuchAlgorithmException {
        UUID userID = this.somExchange.getUserUUID(this.someCode);

        MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        byte[] hashbytes = digest.digest(
                userID.toString().getBytes(StandardCharsets.UTF_8));
        final String sha3Hex = bytesToHex(hashbytes);

        return new Tuple<String, UUID>(sha3Hex, userID);
    }
}
