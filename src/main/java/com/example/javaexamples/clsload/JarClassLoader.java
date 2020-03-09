package com.example.javaexamples.clsload;

import java.io.DataInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class JarClassLoader extends ClassLoader {
	private String jarFile;
	
	public JarClassLoader() {	}
	public JarClassLoader(String jarFile) {	
		this.jarFile = jarFile;
	}
	
	private String convert(String name) {
		String name1 = name.replaceAll("\\.", "/");
		return name1 + ".class";
	}
	public Class findClass(String name) throws ClassNotFoundException {
		String clsName = convert(name);
		
		try {
			ZipFile zf = new ZipFile(jarFile);
			ZipEntry ze = zf.getEntry(clsName);
						
			DataInputStream is = new DataInputStream(zf.getInputStream(ze));
            byte[] class_bytes = new byte[is.available()];
            is.readFully(class_bytes);
            return defineClass(name, class_bytes, 0, class_bytes.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
    
	}

	public static void main(String[] args) throws Exception {
		JarClassLoader jc = new JarClassLoader("c:/abc1.jar");
		Class c = jc.findClass("javasamples.clsload.ABC");
		Object o1 = c.newInstance();
		
		jc = new JarClassLoader("c:/abc2.jar");
		c = jc.findClass("javasamples.clsload.ABC");
		Object o2 = c.newInstance();
	}
}
