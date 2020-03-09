package com.example.javaexamples.dnd;

// AutoScrollingJTree.java

import java.awt.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;

public class AutoScrollingJTree extends JTree implements Autoscroll {
    private int margin = 12;
    
    public AutoScrollingJTree() {
        super();
    }

    public void autoscroll(Point p) {
        int realrow = getRowForLocation(p.x, p.y);
        Rectangle outer = getBounds();
        
        realrow = (p.y + outer.y <= margin ?
                   realrow < 1 ? 0 : realrow - 1 :
                   realrow < getRowCount() - 1 ? realrow + 1 : realrow);
        scrollRowToVisible(realrow);
    }

    public Insets getAutoscrollInsets() {
        Rectangle outer = getBounds();
        Rectangle inner = getParent().getBounds();
        return new Insets(
            inner.y - outer.y + margin, inner.x - outer.x + margin, 
            outer.height - inner.height - inner.y + outer.y + margin,
            outer.width - inner.width - inner.x + outer.x + margin);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Rectangle outer = getBounds();
        Rectangle inner = getParent().getBounds();
        g.setColor(Color.red);
        g.drawRect(-outer.x + 12, -outer.y + 12,
                   inner.width - 24, inner.height - 24);
    }
    
    public static void main(String [] args) {
        new TreeDragTest1();
    }
}
