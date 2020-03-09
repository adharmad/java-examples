package com.example.javaexamples.dnd;

// DropTest1.java

import java.awt.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class DropTest1 extends JFrame implements DropTargetListener {
	DropTarget dt;
	JTextArea ta;

	public DropTest1() {
		super("Drop Test");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	
		getContentPane().add(
			new Label("Drop a list from your file chooser here:"),
			BorderLayout.NORTH);
		ta = new JTextArea();
		ta.setBackground(Color.white);
		getContentPane().add(ta, BorderLayout.CENTER);

		dt = new DropTarget(ta, this);
		setVisible(true);

	}

	public void dragEnter(DropTargetDragEvent dtde) {
		System.out.println("Drag Enter");
	}

	public void dragExit(DropTargetEvent dte) {
		System.out.println("Drag Exit");
	}

	public void dragOver(DropTargetDragEvent dtde) {
		System.out.println("Drag Over");
	}

	public void dropActionChanged(DropTargetDragEvent dtde) {
		System.out.println("Drop Action Changed");
	}

	public void drop(DropTargetDropEvent dtde) {
		try {
			Transferable tr = dtde.getTransferable();
			DataFlavor[] flavors = tr.getTransferDataFlavors();
			for (int i=0 ; i<flavors.length ; i++) {
				System.out.println("Possible flavor: " + flavors[i].getMimeType());
				if (flavors[i].isFlavorJavaFileListType()) {
					dtde.acceptDrop(DnDConstants.ACTION_COPY);
					ta.setText("Successful file list drop.\n\n");

					java.util.List list = (java.util.List)tr.getTransferData(flavors[i]);
					for (int j=0 ; j<list.size() ; j++) {
						ta.append(list.get(j) + "\n");
					}

					dtde.dropComplete(true);
					return;
				} else if (flavors[i].isFlavorSerializedObjectType()) {
					dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
					ta.setText("Successful Object drop.\n\n");
					Object o = tr.getTransferData(flavors[i]);
					ta.append("Object: " + o);
					dtde.dropComplete(true);
					return;
				} else if (flavors[i].isRepresentationClassInputStream()) {
					dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
					ta.setText("Successful Stream drop.\n\n");
					ta.read(new InputStreamReader(
						(InputStream)tr.getTransferData(flavors[i])),
						"from a drop");
					dtde.dropComplete(true);
					return;
				}
			}
			
			System.out.println("Drop failed: " + dtde);
			dtde.rejectDrop();
		} catch (Exception e) {
			e.printStackTrace();
			dtde.rejectDrop();
		}
	}

	public static void main(String [] args) {
		new DropTest1();
	}
	
}
