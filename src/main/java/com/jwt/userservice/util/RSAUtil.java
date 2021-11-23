package com.jwt.userservice.util;

import com.jwt.userservice.common.exception.TechnicalException;
import lombok.extern.log4j.Log4j2;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Log4j2
public class RSAUtil {

    private RSAUtil(){

    }

    public static PrivateKey getPrivateKey(String base64PrivateKey){
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            log.error("InvalidKeySpecException : "+e.getMessage());
            throw new TechnicalException("80000", "InvalidKeySpecException : "+e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException : "+e.getMessage());
            throw new TechnicalException("80000", "NoSuchAlgorithmException : "+e.getMessage());
        }
        return privateKey;
    }

    public static PublicKey getPublicKey(String base64PublicKey){
        PublicKey publicKey = null;
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
        } catch (InvalidKeySpecException e) {
            log.error("InvalidKeySpecException : "+e.getMessage());
            throw new TechnicalException("80000", "InvalidKeySpecException : "+e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException : "+e.getMessage());
            throw new TechnicalException("80000", "NoSuchAlgorithmException : "+e.getMessage());
        }
        return publicKey;
    }

    private static String decryptChipper(byte[] data, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPPadding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(data));
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException : "+e.getMessage());
            throw new TechnicalException("80000", "NoSuchAlgorithmException : "+e.getMessage());
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException : "+e.getMessage());
            throw new TechnicalException("80000", "NoSuchPaddingException : "+e.getMessage());
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException : "+e.getMessage());
            throw new TechnicalException("80000", "InvalidKeyException : "+e.getMessage());
        } catch (IllegalBlockSizeException e) {
            log.error("IllegalBlockSizeException : "+e.getMessage());
            throw new TechnicalException("80000", "IllegalBlockSizeException : "+e.getMessage());
        } catch (BadPaddingException e) {
            log.error("BadPaddingException : "+e.getMessage());
            throw new TechnicalException("80000", "BadPaddingException : "+e.getMessage());
        }
    }

    private static byte[] encryptChipper(byte[] data, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPPadding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException : "+e.getMessage());
            throw new TechnicalException("80000", "NoSuchAlgorithmException : "+e.getMessage());
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException : "+e.getMessage());
            throw new TechnicalException("80000", "NoSuchPaddingException : "+e.getMessage());
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException : "+e.getMessage());
            throw new TechnicalException("80000", "InvalidKeyException : "+e.getMessage());
        } catch (IllegalBlockSizeException e) {
            log.error("IllegalBlockSizeException : "+e.getMessage());
            throw new TechnicalException("80000", "IllegalBlockSizeException : "+e.getMessage());
        } catch (BadPaddingException e) {
            log.error("BadPaddingException : "+e.getMessage());
            throw new TechnicalException("80000", "BadPaddingException : "+e.getMessage());
        }
    }

    public static String decrypt(String data, String base64PrivateKey) {
        return decryptChipper(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(base64PrivateKey));
    }

    public static String encrypt(String data, String base64PublicKey) {
        return Base64.getEncoder().encodeToString(encryptChipper(data.getBytes(), getPublicKey(base64PublicKey)));
    }

}
