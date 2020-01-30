package comanche;
import java.io.*;
import org.objectweb.fractal.api.control.BindingController;

public class User implements Runnable, BindingController {
  private Num cn;
  private ImpCmd ci;
  // configuration aspect
  public String[] listFc () { return new String[] { "cn", "ci" }; }
  public Object lookupFc (String itfName) {
    if (itfName.equals("cn")) { return cn; }
    else if (itfName.equals("ci")) { return ci; }
    else return null;
  }
  public void bindFc (String itfName, Object itfValue) {
    if (itfName.equals("cn")) { cn = (Num)itfValue; }
    else if (itfName.equals("ci")) { ci = (ImpCmd)itfValue; }
  }
  public void unbindFc (String itfName) {
    if (itfName.equals("cn")) { cn = null; }
    else if (itfName.equals("ci")) { ci = null; }
  }


  public void run(){
  
  }
}
