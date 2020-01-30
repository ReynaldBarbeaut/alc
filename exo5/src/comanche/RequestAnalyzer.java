package comanche;
import java.io.*;
import org.objectweb.fractal.api.control.BindingController;

public class RequestAnalyzer implements RequestHandler, BindingController {
  private RequestHandler rh;
  private BindingController rd;
  private Logger l;
  private boolean isHttp = false;
	
  // configuration aspect
  public String[] listFc () { return new String[] { "l", "rh", "rd" }; }
  public Object lookupFc (String itfName) {
    if (itfName.equals("l")) { return l; }
    else if (itfName.equals("rh")) { return rh; }
	else if (itfName.equals("rd")) { return rd; }
    else return null;
  }
  public void bindFc (String itfName, Object itfValue) {
    if (itfName.equals("l")) { l = (Logger)itfValue; }
    else if (itfName.equals("rh")) { rh = (RequestHandler)itfValue; }
	else if (itfName.equals("rd")) { rd = (BindingController)itfValue; }
  }
  public void unbindFc (String itfName) {
    if (itfName.equals("l")) { l = null; }
    else if (itfName.equals("rh")) { rh = null; }
	else if (itfName.equals("rd")) { rd = null; }
  }
  // functional aspect
  public void handleRequest (Request r) throws IOException {
    r.in = new InputStreamReader(r.s.getInputStream());
    r.out = new PrintStream(r.s.getOutputStream());
    String rq = new LineNumberReader(r.in).readLine();
    l.log(rq);

	if (rq.startsWith("GET ")) {
		r.url = rq.substring(5, rq.indexOf(' ', 4));
		if(rq.contains("reconf")){
			if(this.isHttp){
				RequestHandler fh = new FileRequestHandler();
				try{
						rd.unbindFc("h0");
						rd.bindFc("h0",fh);
						this.isHttp = false;
				}catch(Exception e){
						System.err.println(e);
				}
			}else{
				RequestHandler hh = new HttpHandler();
				try{
					rd.unbindFc("h0");
					rd.bindFc("h0",hh);
					this.isHttp = true;
				}catch(Exception e){
					System.err.println(e);
				} 
			}
		}
		rh.handleRequest(r);	
	}	
    r.out.close();
    r.s.close();
  }
}
