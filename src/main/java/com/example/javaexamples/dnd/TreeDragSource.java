package com.example.javaexamples.dnd;

// TreeDragSource.java

import java.awt.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;

public class TreeDragSource 
implements DragSourceListener, DragGestureListener {
    DragSource source;
    DragGestureRecognizer recognizer;
    TransferableTreePath transferable;
    DefaultMutableTreeNode oldNode;
    JTree sourceTree;
    
    public TreeDragSource(JTree tree, int actions) {
        sourceTree = tree;
        source = new DragSource();
        recognizer = source.createDefaultDragGestureRecognizer(
            sourceTree, actions, this);
    }

    public void dragGestureRecognized(DragGestureEvent dge) {
        TreePath path = sourceTree.getSelectionPath();
        if ((path == null) || (path.getPathCount() < 1)) {
            return;
        }

        oldNode = (DefaultMutableTreeNode)path.getLastPathComponent();
        
        transferable = new TransferableTreePath(path);
        dge.startDrag(DragSource.DefaultMoveNoDrop, transferable, this);
        
    }

    public void dragEnter(DragSourceDragEvent dsde) { }
    public void dragExit(DragSourceEvent dsde) { }
    public void dragOver(DragSourceDragEvent dsde) { }
    public void dropActionChanged(DragSourceDragEvent dsde) { }
    
    public void dragDropEnd(DragSourceDropEvent dsde) {
        //if (dsde.getDropSuccess()) {
        //    ((DefaultTreeModel)sourceTree.getModel())
        //        .removeNodeFromParent(oldNode);
        //}
        if (dsde.getDropSuccess() && 
            (dsde.getDropAction() == DnDConstants.ACTION_MOVE)) {
            ((DefaultTreeModel)sourceTree.getModel())
                .removeNodeFromParent(oldNode);            
        }
    }
}

    
