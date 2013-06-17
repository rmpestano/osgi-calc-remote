package com.calculator.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.*;

import com.calculator.Calculator;
import com.calculator.soma.api.Soma;

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
		ServiceTracker serviceTracker = new ServiceTracker(context, Soma.class.getName(), null) {
		      @Override
		      public Object addingService(ServiceReference reference) {
		        Object result = super.addingService(reference);
//		        Object svc = context.getService(reference);
//		        if (svc instanceof Soma) {
//		          final Soma soma = (Soma) svc;
//		          System.out.println(soma.sum(666, 1));
//		        }
		        //retorna service via serviceDiscovery
		        return result;
		      }
		     };
		    serviceTracker.open();
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
