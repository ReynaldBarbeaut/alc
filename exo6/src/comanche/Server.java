package comanche;

public class Server {
  public static void main (String[] args) {
    User us = new User();
    BasicNum cn = new BasicNum();
    Imp ci = new Imp();

    BasicDocument doc = new BasicDocument();
    BasicAlimentation ae = new BasicAlimentation();
    BasicCartouche cen = new BasicCartouche();
    BasicCartouche cec = new BasicCartouche();
    BasicInput mp = new BasicInput();



    us.bindFc("cn", cn);
    us.bindFc("ci", ci);

	cn.bindFc("do",doc);
	cn.bindFc("ae",ae);

	ci.bindFc("cen",cen);
	ci.bindFc("cec",cec);
	ci.bindFc("mp",mp);
	ci.bindFc("ae",ae);
	ci.bindFc("in",cn);	

   

    us.run();
  }
}
