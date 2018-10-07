package com.fxcalc;

import static java.lang.System.exit;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fxcalc.model.ConversionRequest;
import com.fxcalc.service.CurrencyConversionService;
import com.fxcalc.service.InputValidationService;

/**
 * 
 * @author Aditya Bhanose
 * @version 1.0
 * Starter class to receive the input from comnsole and process the request 
 */


@SpringBootApplication
public class FxcalculatorStarter implements CommandLineRunner {

	@Autowired
	private InputValidationService inputValidationService;

	@Autowired
	private ConversionRequest convRequest;

	@Autowired
	CurrencyConversionService currencyConversionService;

	public static void main(String[] args) throws Exception {

		SpringApplication app = new SpringApplication(FxcalculatorStarter.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	/**
	 * Implementation of run method.
	 */
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("\n *************** Welcome to FX Calculator **********************");
		System.out.println("\n * 1.Type command in Fromcurrency Amount in TOCurrency sequence *");
		System.out.println("\n * 2.Type Quit to exit the programm  							  *");
		System.out.println("\n ****************************************************************");
		Scanner scanner = new Scanner(System.in);
		String inputCommand = "";
		while (!inputCommand.equalsIgnoreCase("quit")) {
			inputCommand = scanner.nextLine();
			if (inputCommand.equalsIgnoreCase("quit")) {
				System.out.println("\n Thanks for using FX Calculator");
				exit(0);
			} else {
				boolean isValid = inputValidationService.validateInput(inputCommand, convRequest);
				if (isValid) {
					//System.out.println("\n Valid Input");
					currencyConversionService.processRequest(convRequest);
				}
			}
		}
		scanner.close();
	}
}