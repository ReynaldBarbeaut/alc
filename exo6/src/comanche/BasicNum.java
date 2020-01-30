package comanche;
import java.io.*;
import org.objectweb.fractal.api.control.BindingController;

public class BasicNum implements BindingController, Num {
  private Document doc;
  private Alimentation ae;
  // configuration aspect
  public String[] listFc () { return new String[] { "do", "cec"}; }
  public Object lookupFc (String itfName) {
    if (itfName.equals("do")) { return doc; }
    else if (itfName.equals("ae")) { return ae; }
    else return null;
  }
  public void bindFc (String itfName, Object itfValue) {
    if (itfName.equals("do")) { doc = (Document)itfValue; }
    else if (itfName.equals("ae")) { ae = (Alimentation)itfValue; }
  }
  public void unbindFc (String itfName) {
    if (itfName.equals("do")) { doc = null; }
    else if (itfName.equals("ae")) { ae = null; }
  }
}
