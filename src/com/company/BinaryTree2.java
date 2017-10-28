package com.company;

/**
 * Created by crys_ on 22.10.2017.
 */
public class BinaryTree2 {

    Node root;

    public void addNodeLexicographic(int key, String name) {

        // Create a new Node and initialize it

        Node newNode = new Node(key, name);

        // If there is no root this becomes root

        if (root == null) {

            root = newNode;

        } else {

            // Set root as the Node we will start
            // with as we traverse the tree

            Node focusNode = root;

            // Future parent for our new Node

            Node parent;

            while (true) {

                // root is the top parent so we start
                // there

                parent = focusNode;

                // Check if the new node should go on
                // the left side of the parent node
                int result = name.compareTo(focusNode.name);
                //System.out.println("aici " +result);
                if ((result<0)) {
                  //  System.out.println(name);
                   // System.out.println(" "+focusNode.name);

                    // Switch focus to the left child

                    focusNode = focusNode.leftChild;

                    // If the left child has no children

                    if (focusNode == null) {

                        // then place the new node on the left of it

                        parent.leftChild = newNode;
                        return; // All Done

                    }

                } else { // If we get here put the node on the right

                    focusNode = focusNode.rightChild;

                    // If the right child has no children

                    if (focusNode == null) {

                        // then place the new node on the right of it

                        parent.rightChild = newNode;
                        return; // All Done

                    }

                }

            }
        }

    }


    public void addNode(int key, String name) {

        // Create a new Node and initialize it

        Node newNode = new Node(key, name);

        // If there is no root this becomes root

        if (root == null) {

            root = newNode;

        } else {

            // Set root as the Node we will start
            // with as we traverse the tree

            Node focusNode = root;

            // Future parent for our new Node

            Node parent;

            while (true) {

                // root is the top parent so we start
                // there

                parent = focusNode;

                // Check if the new node should go on
                // the left side of the parent node

                if (key < focusNode.key) {

                    // Switch focus to the left child

                    focusNode = focusNode.leftChild;

                    // If the left child has no children

                    if (focusNode == null) {

                        // then place the new node on the left of it

                        parent.leftChild = newNode;
                        return; // All Done

                    }

                } else { // If we get here put the node on the right

                    focusNode = focusNode.rightChild;

                    // If the right child has no children

                    if (focusNode == null) {

                        // then place the new node on the right of it

                        parent.rightChild = newNode;
                        return; // All Done

                    }

                }

            }
        }

    }

    // All nodes are visited in ascending order
    // Recursion is used to go to one node and
    // then go to its child nodes and so forth

    public void inOrderTraverseTree(Node focusNode) {

        if (focusNode != null) {

            // Traverse the left node

            inOrderTraverseTree(focusNode.leftChild);

            // Visit the currently focused on node

            System.out.println(focusNode);

            // Traverse the right node

            inOrderTraverseTree(focusNode.rightChild);

        }

    }

    public void preorderTraverseTree(Node focusNode) {

        if (focusNode != null) {

            System.out.println(focusNode);

            preorderTraverseTree(focusNode.leftChild);
            preorderTraverseTree(focusNode.rightChild);

        }

    }

    public void postOrderTraverseTree(Node focusNode) {

        if (focusNode != null) {

            postOrderTraverseTree(focusNode.leftChild);
            postOrderTraverseTree(focusNode.rightChild);

            System.out.println(focusNode);

        }

    }

    public Node findNode(int key) {

        // Start at the top of the tree

        Node focusNode = root;

        // While we haven't found the Node
        // keep looking

        while (focusNode.key != key) {

            // If we should search to the left

            if (key < focusNode.key) {

                // Shift the focus Node to the left child

                focusNode = focusNode.leftChild;

            } else {

                // Shift the focus Node to the right child

                focusNode = focusNode.rightChild;

            }

            // The node wasn't found

            if (focusNode == null)
                return null;

        }

        return focusNode;

    }

    public Node findNodeLexico(String name) {

        // Start at the top of the tree

        Node focusNode = root;

        if (focusNode == null)
            return null;


        // While we haven't found the Node
        // keep looking

        while (!focusNode.name .equals(name)) {

            // If we should search to the left
            int result = name.compareTo(focusNode.name);
            if (result<0) {

                // Shift the focus Node to the left child

                focusNode = focusNode.leftChild;

            } else {

                // Shift the focus Node to the right child

                focusNode = focusNode.rightChild;

            }

            // The node wasn't found

            if (focusNode == null)
                return null;

        }
        return focusNode;
    }


    public int findKeyByName(String name) {

        // Start at the top of the tree
        Node focusNode = root;
        // While we haven't found the Node
        // keep looking
        while (!focusNode.name .equals(name)) {
            // If we should search to the left
            int result = name.compareTo(focusNode.name);
            if (result<0) {
                // Shift the focus Node to the left child
                focusNode = focusNode.leftChild;
            } else {
                // Shift the focus Node to the right child
                focusNode = focusNode.rightChild;
            }
            // The node wasn't found

            if (focusNode == null)
                return -1;

        }
        return focusNode.key;
    }

    public Node findParent(String name, Node node, Node parent)
    {
       // Node parent = new Node(-1,"1");
        // if this node is null, return null, cause this
        // is not the path you are looking for
        if (node == null) {
            return null;
            // if this is not the node we are looking for,
        } else if (!(node.name.equals(name))) {
            // We look in the left node.
            parent = findParent(name, node.leftChild, node);
            // If its not found parent will be null
            if (parent == null) {
                // So we go look to the right
                parent = findParent(name, node.rightChild, node);
            }
        }
        // Eventually we can return the parent.
        // If this was the node we were looking for,
        // We can return parent without changing it.
        // If it was not, this algorithm searched in its subtrees
        // If its not there than parent is null.
        return parent;
    }


    public int findSibling(String name, Node node, Node parent)
    {
        // Node parent = new Node(-1,"1");
        // if this node is null, return null, cause this
        // is not the path you are looking for
        if (node == null) {
            return -1;
            // if this is not the node we are looking for,
        } else if (!(node.name.equals(name))) {
            // We look in the left node.
            parent = findParent(name, node.leftChild, node);
            // If its not found parent will be null
            if (parent == null) {
                // So we go look to the right
                parent = findParent(name, node.rightChild, node);
            }
        }
        // Eventually we can return the parent.
        // If this was the node we were looking for,
        // We can return parent without changing it.
        // If it was not, this algorithm searched in its subtrees
        // If its not there than parent is null.
        if (parent.key == -1 && parent.name == "-1") {
            return -1;
        }

        if(parent.leftChild == null)
        {
            return -1;
        }
        if(parent.rightChild == null)
        {
            return -1;
        }

        if(parent.leftChild.name.equals(name)) {
            return parent.rightChild.key;
        }else if (parent.rightChild.name.equals(name)) {
            return parent.leftChild.key;
        }else
            return -1;
    }

}

class Node {

    int key;
    String name;

    Node leftChild;
    Node rightChild;

    Node(int key, String name) {
        this.key = key;
        this.name = name;
    }

    public String toString() {
        return name + " " + key;
		/*
		 * return name + " has the key " + key + "\nLeft Child: " + leftChild +
		 * "\nRight Child: " + rightChild + "\n";
		 */
    }
}
