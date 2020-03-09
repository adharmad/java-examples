package com.example.javaexamples.dnd;

// TransferableTreePath.java

import java.awt.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;

public class TransferableTreePath implements Transferable {
    public static DataFlavor TREE_PATH_FLAVOR = new DataFlavor(TreePath.class, "Tree Path");
    DataFlavor flavors[] = { TREE_PATH_FLAVOR };
    TreePath path;
    
    public TransferableTreePath(TreePath tp) {
        path = tp;
    }

    public synchronized DataFlavor[] getTransferDataFlavors() {
        return flavors;
    }
    
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return (flavor.getRepresentationClass() == TreePath.class);
    }

    public synchronized Object getTransferData(DataFlavor flavor) 
        throws UnsupportedFlavorException, IOException {
        if (isDataFlavorSupported(flavor)) {
            return (Object)path;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
