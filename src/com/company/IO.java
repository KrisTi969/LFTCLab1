package com.company;

/**
 * Created by crys_ on 22.10.2017.
 */
import java.io.*;
import java.nio.Buffer;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IO {
    private final String INPUT_FILE ;
    private final String OUTPUT_FILE = "resources\\output\\arboreBinar.txt";
    private final String OUTPUT_FIPFILE = "resources\\output\\1.txt";
    private final String OUTPUT_FILETSI = "resources\\output\\arboreBinar2.txt";
    private final String OUTPUT_FILECSI = "resources\\output\\arboreBinar3.txt";
    private BufferedWriter writer;
    private BufferedWriter writer2;
    private BufferedWriter writer3;
    private BufferedWriter writer4;

    public IO(String inputFile, String outputFile) {
        super();
        INPUT_FILE = inputFile;
       // OUTPUT_FILE = outputFile;
        if(outputFile != null){
            initWriter();
        }
    }

    private void initWriter() {
        try {
            writer2 = new BufferedWriter(new FileWriter(OUTPUT_FIPFILE));
            writer = new BufferedWriter(new FileWriter(OUTPUT_FILE));
            writer3 = new BufferedWriter(new FileWriter(OUTPUT_FILETSI));
            writer4 = new BufferedWriter(new FileWriter(OUTPUT_FILETSI));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void write_TS(Integer key, String text) throws IOException {
        writer.write(key +" "+ text);
        writer.newLine();
        //writer.close();
    }

    public void write_TS_Refacere_Arbore(Integer key, String text, Node father, int brother) throws IOException {
        if(father == null) {
            father = new Node(-1,"-1");
        }
        writer3.write(key + " " + text + " Tatal este: " + father + " Fratele este: " + brother);
        writer3.newLine();
        //writer.close();
    }

    public void write_TS_Refacere_Arbore2(Integer key, String text, Node father, int brother) throws IOException {
        if(father == null) {
            father = new Node(-1,"-1");
        }
        writer4.write(key + " " + text + " Tatal este: " + father + " Fratele este: " + brother);
        writer4.newLine();
        //writer.close();
    }

    public void write_FIP(Integer key, String text) throws IOException {
        writer2.write(key +" "+ text);
        writer2.newLine();
        //writer.close();
    }

    public void finishWriting() throws IOException {
        writer.write("-----------");
        writer.close();
    }

    public void finishWritingFIP() throws IOException {
        writer2.write("-----------");
        writer2.close();
    }

    public void finishWritingFIPRefacere() throws IOException {
        writer3.write("Am terminat refacerea arborelui pentru Arborele TS al Identificatorilor");
        writer3.close();
    }

    public void finishWritingFIPRefacere2() throws IOException {
        writer4.write("Am terminat refacerea arborelui pentru Arborele TS al Constantelor \n");
        writer4.close();
    }

    public List<String> read() throws IOException {
        return Files.readAllLines(Paths.get(INPUT_FILE));
    }
    public List<String> readOutput() throws IOException {
        return Files.readAllLines(Paths.get(OUTPUT_FILETSI));
    }
}
