/**
 * The remote interface
 */


import java.rmi.*;

public interface RemoteMesage extends Remote
{
	public abstract String mailbox(String in) throws RemoteException;
}
/* Emanuel Tosto Holanda Vasconcelos-emanueltosto@gmail.com */
