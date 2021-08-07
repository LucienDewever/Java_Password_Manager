/*
Author: Lucien Dewever
Date: 7/08/2021
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class PassGen {
    private final String lowerChars = "abcdefghijklmnopqrstuvqxyz";
    private final String upperChars = "ABCDEFGHIJKLMNOPQRSTUVQXYZ";
    private final String ambiguousChars = "{}[]()/\\'\"`~,;:.<>";
    private final String specialChars = "!#$%&*+-=?@^_";
    private final String numbers = "123456789";

    public String generate(boolean num, boolean spec, boolean ambig, boolean upper, boolean lower, String bannedChars, int length){
        boolean[] boolArray = {num, spec, ambig, upper, lower};
        int criteriaUsed = 0, charType, index;
        boolean activeType = false, passed = false, isbanned;
        char toAdd =' ';
        String passwordTransfer = "";


//        for (boolean b : boolArray) {
//            if (b) {
//                criteriaUsed++;
//            }
//        }

        while(!passed){
            System.out.println("while loop entered");
            StringBuilder Password = new StringBuilder();
            for (int i = 0; i < length; i++){
                while(!activeType){
                    charType = ThreadLocalRandom.current().nextInt(0, boolArray.length);
                    isbanned = true;
                    if(boolArray[charType]){
                        switch (charType) {
                            case (0) -> {
                                while (isbanned) {
                                    index = ThreadLocalRandom.current().nextInt(0, numbers.length());
                                    if (bannedChars.indexOf(numbers.charAt(index)) == -1) {
                                        System.out.println("tried to add a number, added: " + toAdd);
                                        toAdd = numbers.charAt(index);
                                        isbanned = false;

                                    }
                                }
                                Password.append(toAdd);
                            }
                            case (1) -> {
                                while (isbanned) {
                                    index = ThreadLocalRandom.current().nextInt(0, specialChars.length());
                                    if (bannedChars.indexOf(specialChars.charAt(index)) == -1) {
                                        System.out.println("tried to add a spec, added: " + toAdd);
                                        toAdd = specialChars.charAt(index);
                                        isbanned = false;

                                    }
                                }
                                Password.append(toAdd);
                            }
                            case (2) -> {
                                while (isbanned) {
                                    index = ThreadLocalRandom.current().nextInt(0, ambiguousChars.length());
                                    if (bannedChars.indexOf(ambiguousChars.charAt(index)) == -1) {
                                        System.out.println("tried to add an ambig, added: " + toAdd);
                                        toAdd = ambiguousChars.charAt(index);
                                        isbanned = false;

                                    }
                                }
                                Password.append(toAdd);
                            }
                            case (3) -> {
                                while (isbanned) {
                                    index = ThreadLocalRandom.current().nextInt(0, upperChars.length());
                                    if (bannedChars.indexOf(upperChars.charAt(index)) == -1) {
                                        System.out.println("tried to add an upper, added: " + toAdd);
                                        toAdd = upperChars.charAt(index);

                                        isbanned = false;

                                    }
                                }
                                Password.append(toAdd);
                            }
                            case (4) -> {
                                while (isbanned) {
                                    index = ThreadLocalRandom.current().nextInt(0, lowerChars.length());
                                    if (bannedChars.indexOf(lowerChars.charAt(index)) == -1) {
                                        System.out.println("tried to add a lower, added: " + toAdd);
                                        toAdd = lowerChars.charAt(index);
                                        //System.out.println("adding: " + toAdd);
                                        isbanned = false;

                                    }
                                }
                                Password.append(toAdd);
                            }
                        }
                        activeType = true;
                    }
                }
                activeType = false;
            }
            passwordTransfer = Password.toString();

            passed = checkPass(Password, boolArray);
        }
        System.out.println("while loop exited");

        System.out.println(passwordTransfer);

        return passwordTransfer;
    }

    public boolean checkPass(StringBuilder createdPass, boolean[] criteria){
        int nums = 0, specs = 0, ambigs = 0, uppers = 0, lowers = 0;
        System.out.println("checkpass entered");
        System.out.println("testing: " + createdPass);

        for(int i = 0; i < createdPass.length(); i++){
            if(numbers.indexOf(createdPass.charAt(i)) != -1){
                nums++;
            }else if(specialChars.indexOf(createdPass.charAt(i)) != -1){
                specs++;
            }else if(ambiguousChars.indexOf(createdPass.charAt(i)) != -1){
                ambigs++;
            }else if(upperChars.indexOf(createdPass.charAt(i)) != -1){
                uppers++;
            }else if(lowerChars.indexOf(createdPass.charAt(i)) != -1){
                lowers++;
            }
        }

        System.out.println("num lowers: " + lowers);

        for(int i = 0; i < criteria.length; i++){
            if(i == 0 && criteria[i] && nums < 1){
                System.out.println("failed on 0");
                return false;
            }
            if(i == 1 && criteria[i] && specs < 1){
                System.out.println("failed on 1");
                return false;
            }
            if(i == 2 && criteria[i] && ambigs < 1){
                System.out.println("failed on 2");
                return false;
            }
            if(i == 3 && criteria[i] && uppers < 1){
                System.out.println("failed on 3");
                return false;
            }
            if(i == 4 && criteria[i] && lowers < 1){
                System.out.println("failed on 4");
                return false;
            }
        }
        return true;
    }

    public void saveToFile(File saveTo, String Password){
        try{
            FileWriter pWriter = new FileWriter(saveTo, true);
            pWriter.write("" + Password +"\n");
            pWriter.close();
        }catch (IOException e){
            System.out.println("an error occurred");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        GUI passGUI = new GUI();


    }
}
