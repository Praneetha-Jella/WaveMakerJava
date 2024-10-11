package com.wavemaker;
import java.io.FileWriter;
import java.io.IOException;
public class WriteToFile {
    public static void main(String [] args){
        try{
            FileWriter writer = new FileWriter("MyFile.txt");
            writer.write("Missisippi");
            writer.close();
            System.out.println("Successfully wrote to the file");
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

