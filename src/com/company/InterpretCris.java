package com.company;

import java.io.IOException;
import java.util.List;

/**
 * Created by crys_ on 22.10.2017.
 */

public class InterpretCris {
    private BinaryTree2 symbolTable;
    private BinaryTree2 Fip;
    private BinaryTree2 TSConstants;
    private BinaryTree2 TSIdentifiers;
    private Integer id = 0;
    private Integer id2 = 0;
    private Integer id3 = 0;
    private Integer id4 = 0;
    private Integer id5 = 0;
    public InterpretCris() {
        symbolTable = new BinaryTree2();
        TSConstants = new BinaryTree2();
        TSIdentifiers = new BinaryTree2();
        Fip = new BinaryTree2();
        populateSymbolTable();
    }

    private void populateSymbolTable() {
        IO io = new IO(Constante.TABLE_SYMBOL, null);
        try {
            List<String> lines = io.read();
            //System.out.println(lines);//aray symbols_table
            for (String line : lines) {
                String[] parts = line.split(" ");
                //System.out.println(Integer.parseInt(parts[1]));
                symbolTable.addNodeLexicographic(Integer.parseInt(parts[1]), parts[0]);
            }
            //symbolTable.inOrderTraverseTree(symbolTable.root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void interpret(String input, String output) throws Exception {
        IO io = new IO(input, output);
        int lineIndex = 1;
        try {
            List<String> content = io.read();
            for (String line : content) {
                StringBuffer buffer = new StringBuffer();
                String[] parts = line.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    if (isConstant(parts[i])) {
                        if (TSConstants.findNodeLexico(parts[i]) == null) {
                            Integer id = generateValue();
                            TSConstants.addNodeLexicographic(id, parts[i]);
                            io.write_TS(id, parts[i]);
                        }
                    }
                    //System.out.println(parts[i]);
                    if (isIdentificator(parts[i])) {
                        if (TSIdentifiers.findNodeLexico(parts[i]) == null) {

                            Integer id2 = generateValue2();
                            TSIdentifiers.addNodeLexicographic(id2,parts[i]);
                            io.write_TS(id2,parts[i]);
                        }
                    }
                }
            }
            System.out.println("0000000000000000000000");
            TSIdentifiers.inOrderTraverseTree(TSIdentifiers.root);
            System.out.println("1111111111111111111111");
            int continuam = 0;
            for (String line : content) {
               // System.out.println("Aici incepe for");
                StringBuffer buffer = new StringBuffer();
                String[] parts = line.split(" ");
             //   System.out.println("" +parts[0]);
                if (continuam == 0) {
                    if (parts[0].equals("function")) {
                        continuam = 1;
                    } else {
                       // System.out.println("verificam pe" + parts[0]);
                        System.out.println("Program should start with function declaration");
                        System.exit(1);
                    }
                }
                for (int i = 0; i < parts.length; i++) {
                    //System.out.println("----- " +parts[i]);
                    if (isConstant(parts[i])) {
                        int poz = TSConstants.findKeyByName(parts[i]);
                        Fip.addNodeLexicographic(1, String.valueOf(poz));
                        io.write_FIP(1, String.valueOf(poz));
                    }
                    else if (isIdentificator(parts[i])) {
                        int poz = TSIdentifiers.findKeyByName(parts[i]);
                        Fip.addNodeLexicographic(0, String.valueOf(poz));
                        io.write_FIP(0, String.valueOf(poz));
                    }
                    else if(symbolTable.findKeyByName(parts[i])!=-1) {
                        int poz = symbolTable.findKeyByName(parts[i]);
                      //  System.out.println(parts[i]);
                      //  System.out.println("pozitia din fip pentru non: " + poz);
                        Fip.addNodeLexicographic(poz, String.valueOf(-1));
                        io.write_FIP(poz, String.valueOf(-1));
                    }
                    else{
                        System.out.println(parts[i]);
                        if(i>0 && (parts[i-1].equalsIgnoreCase("var") || parts[i-1].equalsIgnoreCase("const") || parts[i-1].equalsIgnoreCase("function"))){
                            if(parts[i].length() > 4){
                                throw new Exception("Syntax error at line " + lineIndex + " ,column " + i +": identificator prea lung");
                            }
                            else{
                                if(isConstant(parts[i]) || symbolTable.findKeyByName(parts[i])!=-1){
                                    System.out.println(parts[i]);
                                }else{
                                    // symbolTable.inOrderTraverseTree(symbolTable.root);
                                    System.out.println(symbolTable.findKeyByName("eroare la: "+ parts[i]));
                                    System.out.println(parts[i]);
                                    throw new Exception("Syntax error at line " + lineIndex + " ,column " + i);
                                }
                            }
                        }
                    }
                }
            }
            //TSConstants.inOrderTraverseTree(TSConstants.root);
            //TSIdentifiers.inOrderTraverseTree(TSIdentifiers.root);
            io.finishWriting();
            io.finishWritingFIP();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> content = io.read();
        for (String line : content) {
            StringBuffer buffer = new StringBuffer();
            String[] parts = line.split(" ");
            for (int j = 0; j < parts.length; j++) {
                Node parent = new Node(-1,"-1");
                if (isIdentificator(parts[j]) && TSIdentifiers.findKeyByName(parts[j])>0) {
                   // System.out.println("identificatorul " + parts[j]);
                    // if (TSIdentifiers.findNodeLexico(parts[j]) != null) {
                    Integer id3 = generateValue3();
                         io.write_TS_Refacere_Arbore(TSIdentifiers.findKeyByName(parts[j]), parts[j], TSIdentifiers.findParent(parts[j], TSIdentifiers.root, parent),
                          TSIdentifiers.findSibling(parts[j], TSIdentifiers.root, parent));
                }
            }
        }

        for (String line : content) {
            StringBuffer buffer = new StringBuffer();
            String[] parts = line.split(" ");
            for (int j = 0; j < parts.length; j++) {
                Node parent2 = new Node(-1,"-1");
                if (isConstant(parts[j])) {
                    List<String> content2 = io.readOutput();
                    Integer gasit = 0;
                    // System.out.println(parts[j]);
                    // if (TSIdentifiers.findNodeLexico(parts[j]) != null) {
                    Integer id4 = generateValue4();
                   // System.out.println("constata:" +  TSConstants.findParent(parts[j], TSConstants.root, parent2));
                        io.write_TS_Refacere_Arbore2(TSConstants.findKeyByName(parts[j]), parts[j], TSConstants.findParent(parts[j], TSConstants.root, parent2),
                                TSConstants.findSibling(parts[j], TSConstants.root, parent2));
                }
            }
        }
        io.finishWritingFIPRefacere();
        io.finishWritingFIPRefacere2();
    }



    private boolean isIdentificator(String string) {
        if (Character.isDigit(string.charAt(0))) {
            return false;
        }
        boolean containsDigit = false;
        for (int i = 0; i < string.length(); i++) {
            if (Character.isDigit(string.charAt(i)))
                containsDigit = true;
            if (!Character.isLetter(string.charAt(i))) {
                if (!Character.isDigit(string.charAt(i)))
                    return false;
            }
        }
        return containsDigit;
    }

    private boolean isConstant(String string) {
        for (int i = 0; i < string.length(); i++) {
            if ((string.charAt(i) > '9' || string.charAt(i) < '0') && string.charAt(i) != '.') {
                return false;
            }
        }
        return true;
    }
    private Integer generateValue() {
        id = id + 1;
        return id;
    }
    private Integer generateValue2() {
        id2 = id2 + 1;
        return id2;
    }
    private Integer generateValue3() {
        id3 = id3 + 1;
        return id3;
    }
    private Integer generateValue4() {
        id4 = id4 + 1;
        return id4;
    }
    private Integer generateValue5() {
        id5 = id5 + 1;
        return id5;
    }
}