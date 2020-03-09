package com.example.javaexamples.dnd;

// TreeDropTarget.java

import java.awt.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;

public class TreeDropTarget implements DropTargetListener {
    DropTarget target;
    JTree targetTree;
    
    public TreeDropTarget(JTree tree) {
        targetTree = tree;
        target = new DropTarget(targetTree, this);
    }

    public void dragEnter(DropTargetDragEvent dtde) { 
        TreeNode node = getNodeForEvent(dtde);
        if (node.isLeaf()) {
            dtde.rejectDrag();
        } else {
            dtde.acceptDrag(DnDConstants.ACTION_MOVE);
        }
    }

    public void dragExit(DropTargetEvent dtde) { }

    public void dragOver(DropTargetDragEvent dtde) { 
        TreeNode node = getNodeForEvent(dtde);
        if (node.isLeaf()) {
            dtde.rejectDrag();
        } else {
            dtde.acceptDrag(DnDConstants.ACTION_MOVE);
        }
    }

    public void dropActionChanged(DropTargetDragEvent dtde) { }

    private TreeNode getNodeForEvent(DropTargetDragEvent dtde) {
        Point p = dtde.getLocation();
        DropTargetContext dtc = dtde.getDropTargetContext();
        JTree tree = (JTree)dtc.getComponent();
        TreePath path = tree.getClosestPathForLocation(p.x, p.y);
        return (TreeNode)path.getLastPathComponent();
    }

    public void drop(DropTargetDropEvent dtde) {
        Point pt = dtde.getLocation();
        DropTargetContext dtc = dtde.getDropTargetContext();
        JTree tree = (JTree)dtc.getComponent();
        TreePath parentPath = tree.getClosestPathForLocation(pt.x, pt.y);
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode)parentPath.getLastPathComponent();
        
        if (parent.isLeaf()) {
            dtde.rejectDrop();
            return;
        }
        
        try {
            Transferable tr = dtde.getTransferable();
            DataFlavor[] flavors = tr.getTransferDataFlavors();
            for (int i=0 ; i<flavors.length ; i++) {
                if (tr.isDataFlavorSupported(flavors[i])) {
                    dtde.acceptDrop(DnDConstants.ACTION_MOVE);
                    TreePath p = (TreePath)tr.getTransferData(flavors[i]);
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)p.getLastPathComponent();
                    DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
                    model.insertNodeInto(node, parent, 0);
                    
                    dtde.dropComplete(true);
                    return;
                }
            }
            dtde.rejectDrop();
        } catch (Exception e) {
            e.printStackTrace();
            dtde.rejectDrop();
        }
    }
}
