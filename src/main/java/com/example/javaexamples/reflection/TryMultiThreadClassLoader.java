package com.example.javaexamples.reflection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TryMultiThreadClassLoader extends ClassLoader {
	public static TryMultiThreadClassLoader clsLoader;

	private TryMultiThreadClassLoader() {
		super();
	}

	public static void initialize() {
		clsLoader = new TryMultiThreadClassLoader();
	}

	public static synchronized TryMultiThreadClassLoader getInstance() {
		if (clsLoader == null) {
			initialize();
		}
		return clsLoader;
	}

	public Class loadClass(String name) throws ClassNotFoundException {
		Class c = findLoadedClass(name);
		if (c == null) {
			try {
				c = getParent().loadClass(name);
			} catch (ClassNotFoundException e) {
			}
			if (c == null)
				c = findClass(name);
		}
		return c;
	}

	public /* synchronized */ Class loadAdapterFromFile(String psClassName)
			throws ClassNotFoundException {
		Class c = findLoadedClass(psClassName);

		if (c != null) {
			return c;
		}

		// Load the adapter from the file and add the filename to the watch list
		try {
			byte[] mabAdapterBytes = getClassFileBytes(psClassName);
			int mnByteCount = mabAdapterBytes.length;
			return defineClass(psClassName, mabAdapterBytes, 0, mnByteCount);
		} catch (Exception exception) {
			throw new ClassNotFoundException(psClassName);
		}

	}

	private byte[] getClassFileBytes(String psClassName) throws Exception,
			ClassNotFoundException, IOException {
		String fileSep = System.getProperty("file.separator");
		FileInputStream fileinputstream = null;
		String msClassName = psClassName.replace('.', fileSep.charAt(0));
		String msFile = msClassName + ".class";

		File f = new File("c:/classfiles", msFile);

		if (f.exists() == false) {
			throw new ClassNotFoundException(msFile + " not found");
		}

		byte[] mabAdapterBytes;

		try {
			fileinputstream = new FileInputStream(f);

			int mnBytes = fileinputstream.available();

			if (mnBytes == 0) {
				throw new ClassNotFoundException(msFile + " has 0 length");
			}

			mabAdapterBytes = new byte[mnBytes];
			fileinputstream.read(mabAdapterBytes);
		} catch (Exception e) {
			throw new ClassNotFoundException(msFile);
		} finally {
			fileinputstream.close();
		}
		return mabAdapterBytes;
	}

}
