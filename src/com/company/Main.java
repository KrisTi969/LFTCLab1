package com.company;

import java.io.PrintWriter;

public class Main{

    public static void main(String[] args) throws Exception {
        /*Interpretator interpretator = new Interpretator();
        try {
            interpretator.interpret(Constante.INPUT_PB1,Constante.OUTPUT_PB1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/

        /*BinaryTree2 copacu = new BinaryTree2(20);
        int[] nums = {15, 200, 25, -5, 0, 100, 20, 12, 126, 1000, -150};
        for (int i : nums) {
            copacu.addNode(i);
        }
        copacu.traverseInOrder();*/
        InterpretCris interprett = new InterpretCris();
        interprett.interpret(Constante.INPUT_PB1,Constante.OUTPUT_PB1);
        //System.out.println(Constante.OUTPUT_PB1);
        BinaryTree2 copac = new BinaryTree2();
        /*copac.addNode(40,"sal");
        copac.addNode(45,"sal2");
        copac.addNode(44,"sal3");
        copac.addNode(43,"sal4");
        copac.addNode(42,"sal5");
        copac.findNode(42);*/


        Node parent = new Node(-1,"-1");
        copac.addNodeLexicographic(1,"max");
        copac.addNodeLexicographic(2,"i");
        copac.addNodeLexicographic(3,"j");
        copac.addNodeLexicographic(4,"r");
        copac.addNodeLexicographic(5,"abc");
        System.out.println(copac.findKeyByName("sdadad"));
       // copac.inOrderTraverseTree(copac.root);
        //System.out.println(copac.root);
        //System.out.println(copac.findParent("r",copac.root, parent));
      //  System.out.println(copac.findParent("i", copac.root, parent));
       // System.out.println(copac.findParent("max", copac.root, parent));
        System.out.println(copac.findSibling("max", copac.root, parent));
       // System.out.println(copac.findKeyByName("xx"));
       //System.out.println(copac.findNodeLexico("1"));
        /*PrintWriter writer = new PrintWriter("resources\\output\\1.txt", "UTF-8");

        writer.println("ceva");
        writer.close();*/
    }


}
