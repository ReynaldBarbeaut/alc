package comanche;
import java.io.*;
import org.objectweb.fractal.api.control.BindingController;

public class Imp implements BindingController, ImpCmd {
  private Cartouche cen;
  private Cartouche cec;
  private Input mp;
  private Alimentation ae;
  private Num in;
  // configuration aspect
  public String[] listFc () { return new String[] { "cen", "cec", "mp", "ae", "in" }; }
  public Object lookupFc (String itfName) {
    if (itfName.equals("cen")) { return cen; }
    else if (itfName.equals("cec")) { return cec; }
    else if (itfName.equals("mp")) { return mp; }
    else if (itfName.equals("ae")) { return ae; }
    else if (itfName.equals("in")) { return in; }
    else return null;
  }
  public void bindFc (String itfName, Object itfValue) {
    if (itfName.equals("cen")) { cen = (Cartouche)itfValue; }
    else if (itfName.equals("cec")) { cec = (Cartouche)itfValue; }
    else if (itfName.equals("mp")) { mp = (Input)itfValue; }
    else if (itfName.equals("ae")) { ae = (Alimentation)itfValue; }
    else if (itfName.equals("in")) { in = (Num)itfValue; }
  }
  public void unbindFc (String itfName) {
    if (itfName.equals("cen")) { cen = null; }
    else if (itfName.equals("cec")) { cec = null; }
    else if (itfName.equals("mp")) { mp = null; }
    else if (itfName.equals("ae")) { ae = null; }
    else if (itfName.equals("in")) { in = null; }
  }
}
