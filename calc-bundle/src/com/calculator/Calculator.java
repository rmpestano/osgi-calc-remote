package com.calculator;

import java.util.Scanner;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.calculator.soma.api.Soma;

public class Calculator extends Thread {

	private volatile boolean active = true;
	private Scanner scanner;
	private BundleContext bundleContext;

	public Calculator(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	@Override
	public void run() {
		scanner = new Scanner(System.in);
		while (active) {
				try {
					String input = scanner.nextLine();
					if (input.contains("+")) {
						String[] operandos = input.split("\\+");
						System.out.println("Somando " + operandos[0] + " + "
								+ operandos[1]);
						ServiceReference serviceReference = bundleContext
								.getServiceReference(Soma.class.getName());
						if (serviceReference == null) {
							throw new IllegalArgumentException(
									"Operação não suportada '+', inicie o serviço para poder utilizalo");
						}
						Soma soma = bundleContext.getService(serviceReference);
						System.out.println("resultado = "
								+ soma.sum(Integer.parseInt(operandos[0]),
										Integer.parseInt(operandos[1])));
					}

				} catch (Exception e) {
					System.out.println("Erro inexperado:" + e.getMessage());
				}
			}
		}

	public void stopThread() {
		this.active = false;
	}

}
