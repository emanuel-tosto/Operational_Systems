/**
 * Client program requesting current date from server.
 *
 */
 
import java.net.*;
import java.io.*;

/**
 * The RMI Client
 */

import java.rmi.*;

public class RMIClient 
{  
   public static void main(String args[]) { 
    InputStream in = null;
	BufferedReader bin = null;
    try {
      String host = "rmi://127.0.0.1/DateServer";
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	  String entrada = in.readLine();
      RemoteMesage remoteMesage = (RemoteMesage)Naming.lookup(host);
      System.out.println(remoteMesage.mailbox(entrada));
    }
    catch (Exception e) {
        System.err.println(e);
    }
   }
}


/* Emanuel Tosto Holanda Vasconcelos-emanueltosto@gmail.com */
