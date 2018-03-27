package hu.deepdata.tenderbase.webapp

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*

/**
 * @author Zsolt Jurányi
 */
@SpringBootApplication
class WebApp {

	// TODO GET paths for record/{ocid}, tender/{tenderId} -> record (generated)
	// TODO export
	// TODO javadoc

	companion object {

		/**
		 * Entry point of the application, it starts the Spring Boot Framework.
		 * @param args Command line arguments
		 */
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(WebApp::class.java, *args)
		}
	}
}