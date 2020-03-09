package com.example.javaexamples.dnd;

// TreeDragTest.java

import java.awt.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;

public class TreeDragTest extends JFrame {
    TreeDragSource ds;
    TreeDropTarget dt;
    JTree tree;
    
    public TreeDragTest() {
        super("Rearrangable Tree");
        setSize(300, 200);
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        
        tree = new JTree();
        getContentPane().add(new JScrollPane(tree), BorderLayout.CENTER);
        
        ds = new TreeDragSource(tree, DnDConstants.ACTION_MOVE);
        dt = new TreeDropTarget(tree);
        setVisible(true);
    }

    public static void main(String [] args){
        new TreeDragTest();
    }
}
