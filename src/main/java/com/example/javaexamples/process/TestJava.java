package com.example.javaexamples.process;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestJava {

	public static void main(String[] args)
	{
		System.out.println(exec("c:/j2sdk1.4.2_07"));
	}
	private static boolean exec(String jdkpath) 
	{

		String msJarCommand = null;
		String lineSep = System.getProperty("line.separator");
		String msFileSep = System.getProperty("file.separator");

		String javaHome = System.getProperty("java.home");
		msJarCommand = jdkpath + msFileSep + "bin" + msFileSep
				+ "java -version";
		// System.out.println("java command is :" + msJarCommand);

		// String lineSep = System.getProperty("line.separator");

		String errorMsg = "";
		String outputMsg = "";
		String s = null;
		String jdkver = "";
		BufferedReader errorReader = null;
		BufferedReader outputReader = null;

		try {

			// p = Runtime.getRuntime().exec(msJarCommand, env);
			 //System.out.println("java command INSIDE TRY is :" + msJarCommand
			// );
			Process p = Runtime.getRuntime().exec(msJarCommand);

			errorReader = new BufferedReader(new InputStreamReader(p
					.getErrorStream()));
			// outputReader = new BufferedReader(new
			// InputStreamReader(p.getoutputStream()));

			if (errorReader.ready()) {
				while ((s = errorReader.readLine()) != null)
					errorMsg = errorMsg + s + lineSep;

				// System.out.println ("errorReader "+errorReader );
				errorReader.close();
				System.out.println("errorReader " + errorReader);
			}

			while ((s = errorReader.readLine()) != null) {
				outputMsg = outputMsg + s;
			}
			 System.out.println("Output Message ==>:"+ outputMsg);

			// errorReader.close();

			jdkver = outputMsg.substring(14, 17);
			 System.out.println("JDK VERSION ::::::"+ jdkver);
			 //buildNo=outputMsg;
			 //outputReader.close();

			// error has occurred, so display a message
			if (errorMsg.length() > 0) {
				System.out.println("Problem!! ");
				p.waitFor();
			}

			double f = Double.parseDouble(jdkver);
			 System.out.println("TESTING f::::"+f);
			if (f >= 1.4) { 
				 System.out.println("INSIDE TRUE ");
				return true;
			} else {
				 System.out.println("INSIDE FALSE ");
				return false;
			}

		} catch (Exception moE) {
			moE.printStackTrace();
			System.out.println("Inside Catch::::::::::::");
			return false;
		} finally {
		}

	}

}
