package com.calculator.soma.activator
;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.calculator.soma.api.Soma;
import com.calculator.soma.impl.SomaImpl;

public class Activator implements BundleActivator {

	private static BundleContext context;
	private ServiceRegistration serviceRegistration;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("soma bundle iniciado");
		Dictionary props = new Hashtable();
		props.put("service.exported.interfaces", "*");
	    props.put("service.exported.configs", "org.apache.cxf.ws");
	    props.put("service.exported.intents", "HTTP");
	    props.put("org.apache.cxf.ws.address", "http://localhost:9090/soma");
        serviceRegistration = bundleContext.registerService(Soma.class.getName(), new SomaImpl(), props);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		serviceRegistration.unregister();
		Activator.context = null;
		System.out.println("soma bundle parado");
	}

}
