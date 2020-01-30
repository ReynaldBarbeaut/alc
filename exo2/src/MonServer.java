import org.objectweb.fractal.api.Component;
import org.objectweb.fractal.api.control.*;
import org.objectweb.fractal.api.factory.*;
import org.objectweb.fractal.api.type.ComponentType;
import org.objectweb.fractal.api.type.InterfaceType;
import org.objectweb.fractal.api.type.TypeFactory;


import org.objectweb.fractal.util.Fractal;

import comanche.*;
public class MonServer {
  public static void main (String[] args) {
  	
  	try {
  		Component boot = Fractal.getBootstrapComponent();

		TypeFactory tf = (TypeFactory)boot.getFcInterface ("type-factory");
		GenericFactory cf = (GenericFactory)boot.getFcInterface ("generic-factory");
	
		// creer le composite
		// interface server "r" / "java.lang.Runnable"
	    ComponentType rType = tf.createFcType(new InterfaceType[] {
		    tf.createFcItfType("r", "java.lang.Runnable", false, false, false)
		});
		Component rComp = cf.newFcInstance(rType, "composite", null);

		
		// creer le primitif RequestReceiver
		// interface server "r" / "java.lang.Runnable"
		// interface client "rh" / "comanche.RequestHandler"
		// interface client "s" / "comanche.Scheduler"
		// implementation "comanche.RequestReceiver"
	    ComponentType rrType = tf.createFcType(new InterfaceType[] {
		    tf.createFcItfType("r", "java.lang.Runnable", false, false, false),
			tf.createFcItfType("rh", "comanche.RequestHandler", true, false, false),
			tf.createFcItfType("s", "comanche.Scheduler", true, false, false)
		});
		Component rrComp = cf.newFcInstance(rrType, "primitive", "comanche.RequestReceiver");
		
		// creer le primitif RequestAnalyzer
		// interface server "a" / "comanche.RequestHandler"
		// interface client "l" / "comanche.Logger"
		// interface client "rh" / "comanche.RequestHandler"
		// implementation "comanche.RequestReceiver"
	    ComponentType raType = tf.createFcType(new InterfaceType[] {
		    tf.createFcItfType("a", "comanche.RequestHandler", false, false, false),
			tf.createFcItfType("l", "comanche.Logger", true, false, false),
			tf.createFcItfType("rh", "comanche.RequestHandler", true, false, false)
		});
		Component raComp = cf.newFcInstance(raType, "primitive", "comanche.RequestAnalyzer");
		
		// creer le primitif Scheduler
		// interface server "s" / "comanche.Scheduler"
		// implementation "comanche.MultiThreadScheduler"
	    ComponentType sType = tf.createFcType(new InterfaceType[] {
		    tf.createFcItfType("s", "comanche.Scheduler", false, false, false)
		});
		Component sComp = cf.newFcInstance(sType, "primitive", "comanche.MultiThreadScheduler");

		// creer le primitif Logger
		// interface server "l" / "comanche.Logger"
		// implementation "comanche.BasicLogger"
	    ComponentType lType = tf.createFcType(new InterfaceType[] {
		    tf.createFcItfType("l", "comanche.Logger", false, false, false)
		});
		Component lComp = cf.newFcInstance(lType, "primitive", "comanche.BasicLogger");
		
		// creer le primitif FileRequestHandler
		// interface server "rh" / "comanche.RequestHandler"
		// implementation "comanche.FileRequestHandler"
	    ComponentType frhType = tf.createFcType(new InterfaceType[] {
		    tf.createFcItfType("rh", "comanche.RequestHandler", false, false, false)
		});
		Component frhComp = cf.newFcInstance(frhType, "primitive", "comanche.FileRequestHandler");
		
		// ajouter les sous-composants au composite
		Fractal.getContentController(rComp).addFcSubComponent(rrComp);
		Fractal.getContentController(rComp).addFcSubComponent(raComp);
		Fractal.getContentController(rComp).addFcSubComponent(sComp);
		Fractal.getContentController(rComp).addFcSubComponent(lComp);
		Fractal.getContentController(rComp).addFcSubComponent(frhComp);


		// lier les sous-composants
		Fractal.getBindingController(rComp).bindFc("r", rrComp.getFcInterface("r"));

		Fractal.getBindingController(rrComp).bindFc("rh", raComp.getFcInterface("a"));
		Fractal.getBindingController(rrComp).bindFc("s", sComp.getFcInterface("s"));

		Fractal.getBindingController(raComp).bindFc("l", lComp.getFcInterface("l"));
		Fractal.getBindingController(raComp).bindFc("rh", frhComp.getFcInterface("rh"));

		// demarrer
	     Fractal.getLifeCycleController(rComp).startFc();
		((java.lang.Runnable)rComp.getFcInterface("r")).run();

 		
	
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
}
