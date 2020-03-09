package javasamples.reflection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TrySerializable {
	public static void main(String[] args) throws Exception {
		SerObj o = new SerObj();
		ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(new File("c:/foo.obj")));
		oout.writeObject(o);
		oout.close();
		
		ObjectInputStream oin = new ObjectInputStream(new FileInputStream(new File("c:/foo.obj")));
		Object oo = oin.readObject();
		System.out.println(oo.toString());
	}

}
