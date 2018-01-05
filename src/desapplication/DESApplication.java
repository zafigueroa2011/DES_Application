/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desapplication;

import java.util.List;
import java.util.Scanner;

/**
 * Main interface for DES encryption/decryption application.
 * @author Zaylin Arata
 */
public class DESApplication {

    /**
     * Main function.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ECB ecb = new ECB();
        String secretKey;
        String plaintext;
        String ciphertext;
        boolean repeat = false;
        try (
                Scanner reader = new Scanner(System.in).useDelimiter("\n")
        ) {
            do {
                System.out.println("Main menu.\n 1---To Encrypt Message\n 2---To Decrypt Message.");
                int ch1;
                if (System.console() == null) {
                    ch1 = reader.nextInt();
                } else {
                    ch1 = Integer.parseInt(System.console().readLine());
                }
                if (ch1 == 1) {
                    //encrypt
                    System.out.println("Please enter message to encrypt.If message is in hex, please add # to the begining.");
                    if(System.console()==null){
                        plaintext = reader.next();
                    }
                    else{
                        plaintext = System.console().readLine();
                    }
                    System.out.println("Please enter secret key. It must be a hexadecimal string.");
                    if(System.console()==null){
                        secretKey = reader.next();
                    }
                    else{
                        secretKey = System.console().readLine();
                    }
                    encrypt(plaintext, secretKey, ecb);
                    System.out.println("C---To continue. S---To stop.");
                    String ch2;
                    if(System.console()==null){
                        ch2 = reader.next();
                    }
                    else{
                        ch2 = System.console().readLine();
                    }
                    repeat = checkRepeat(ch2);
                }
                else if (ch1 == 2) {
                    //decrypt
                    System.out.println("Please enter message to decrypt. Message must be a hexadecimal string.");
                    if (System.console() == null) {
                        ciphertext = reader.next();
                    } else {
                        ciphertext = System.console().readLine();
                    }
                    System.out.println("Please enter secret key. It must be a hexadecimal string.");
                    if (System.console() == null) {
                        secretKey = reader.next();
                    } else {
                        secretKey = System.console().readLine();
                    }
                    decrypt(ciphertext, secretKey, ecb);
                    System.out.println("C---To continue. S---To stop.");
                    String ch2;
                    if (System.console() == null) {
                        ch2 = reader.next();
                    } else {
                        ch2 = System.console().readLine();
                    }
                    repeat = checkRepeat(ch2);
                } else {
                    System.out.println("Please select a correct option. Encrypt = 1; Decrypt = 2.");
                    repeat = true;
                }
            } while (repeat);            
            reader.close();
        }
    }
    /**
     * Encrypts message using DES.
     * @param plaintext text to encrypt. For hex string add # at the beginning.
     * @param secretKey secret key to use for encryption. Must be in hexadecimal form.
     * @param ecb electronic codebook.
     * @return Returns -1 if the plaintext and secretKey are not empty. Otherwise returns 0.
     */
    public static int encrypt(String plaintext, String secretKey, ECB ecb){
        if (plaintext != "" && secretKey != "") {
            List<String> blocks;
            KeyGenerator keyGen = new KeyGenerator(secretKey);
            String[] enckeys = keyGen.generateEKeys();
            BlockEncoder be = new BlockEncoder(enckeys);
            if (plaintext.startsWith("#")) {
                plaintext = plaintext.substring(1);
                blocks = ecb.createHexBlocks(plaintext);
                System.out.println("Hexadecimal blocks: " + blocks.toString());
            } else {
                blocks = ecb.createStringBlocks(plaintext);
                System.out.println("Plaintext in hexadecimal blocks: " + blocks.toString());
            }
            String cipherText = "";
            for (int i = 0; i < blocks.size(); i++) {
                String temp = be.encodeBlock(blocks.get(i));
                cipherText += temp;
            }
            System.out.println("The ciphertext is: " + cipherText);
            return 0;
        }
        else{
            return -1;
        }
    }
    /**
     * Decrypts message using DES.
     * @param ciphertext text to decrypt. Must be in hexadecimal form.
     * @param secretKey secret key to use for encryption. Must be in hexadecimal form.
     * @param ecb electronic codebook.
     * @return Returns -1 if the plaintext and secretKey are not empty. Otherwise returns 0.
     */
    public static int decrypt(String ciphertext,String secretKey, ECB ecb){
        if (ciphertext != "" && secretKey != "") {
            List<String> blocks;
            KeyGenerator keyGen = new KeyGenerator(secretKey);
            String[] deckeys = keyGen.generateDKeys();
            BlockDecoder bd = new BlockDecoder(deckeys);
            blocks = ecb.createHexBlocks(ciphertext);
            System.out.println("Hexadecimal blocks: " + blocks.toString());
            String plainText = "";
            for (int i = 0; i < blocks.size(); i++) {
                String temp = bd.decodeBlock(blocks.get(i));
                plainText += temp;
            }
            plainText = ecb.hexToString(plainText);
            System.out.println("This is the plaintext: " + plainText);
            return 0;
        }
        else{
            return -1;
        }
    }
    /**
     * Checks if the program will continue or stop.
     * @param s option selected.
     * @return Returns true if s="C" or "c". Otherwise returns false;
     */
    public static boolean checkRepeat(String s){
        if (null == s) {
            return false;
        } else {
            switch (s.toUpperCase()) {
                case "C":
                    return true;
                case "S":
                    return false;
                default:
                    return false;
            }
        } 
    }
}
