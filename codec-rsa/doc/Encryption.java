package com.hua.test.aes;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;
 
public class Encryption
{
    public static String key ;
    public static String iv ;
	static {
		Encryption.key = "weidu23weidu23_2";
		Encryption.iv = "weidu23weidu23_2";
	}
	
    public static void main(String args[]) throws Exception {
    	String encryptString = encrypt("user＝15063142270,pwd＝110120") ;
        System.out.println(encryptString);
        System.out.println(desEncrypt(encryptString));
        System.out.println(desEncrypt("G9rLkysrIzJr6hp3oylHX/RSu73NK8abzc5rxvGNwhKAurw6j/tZBC9CopEIdtMf"));
    }
    
    public static String encrypt(String data/*内容*/) throws Exception {
        try {
        	
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
 
            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
 
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
             
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
 
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
 
            return new BASE64Encoder().encode(encrypted);
 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
    public static String desEncrypt(String data) throws Exception {
        try
        {
             
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(data);
             
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
             
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
  
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}