package com.example.javaexamples.log;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TryLogger {
	public static void main(String[] args) throws Exception {
		Logger logger = Logger.getLogger("test");
		Handler h = new FileHandler("test.log", 50000, 1);
		h.setFormatter(new SimpleFormatter());

		Logger rootLogger = Logger.getLogger("");
		rootLogger.addHandler(h);

		// Now use the named logger and...

		// log a FINE message
		logger.fine("done");

		// log a INFO message.
		logger.info("doing stuff");

		// log a WARNING
		logger.log(Level.WARNING, "something may be wrong");

		// we can log a WARNING as follows
		logger.warning("something may be wrong");

	}

}
