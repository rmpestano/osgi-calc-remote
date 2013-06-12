package com.calculator.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.calculator.Calculator;

public class Activator implements BundleActivator {

	private static BundleContext context;
	
	private Calculator calculator;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		calculator = new Calculator(bundleContext);
		calculator.start();
		System.out.println("Calculator bundle iniciado");
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		calculator.stopThread();
		calculator.interrupt();
		System.out.println("Calculator bundle parado");
	}

}
