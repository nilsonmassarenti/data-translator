package com.nilsonmassarenti.application.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.nilsonmassarenti.application.controller.DataTransformationController;

public class DataTranslatorApplication {

	public static void main(String[] args) throws InterruptedException, IOException {
		Logger logger = Logger.getLogger("com.nilsonmassarenti.application.view.DataTranslatorApplication");
		logger.info("Starting process");
		Integer statusReturn = 0;
		if (args.length != 4) {
			logger.log(Level.SEVERE, "Insufficient Arguments");
			statusReturn = -1;
		} else {
			DataTransformationController controller = new DataTransformationController();
			if (controller.converterFile(args)) {
				statusReturn = 1;
			} else {
				logger.log(Level.SEVERE, "Processing error");
				statusReturn = -2;
			}
		}
		logger.info("Closing process");
		System.exit(statusReturn);
	}

}
