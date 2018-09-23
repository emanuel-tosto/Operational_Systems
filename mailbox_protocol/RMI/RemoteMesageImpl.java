/**
 * Time-of-day server listening to port 6013.
 *
 */
 
import java.net.*;
import java.io.*;
import java.util.ArrayList;

class Mensagem
{
	String remetenteID;
	String destinatarioID;
	String mensagem;
	public Mensagem(String remetenteID, String destinatarioID, String mensagem){
		this.remetenteID = remetenteID;
		this.destinatarioID = destinatarioID;
		this.mensagem = mensagem;
	}
	String getMensagem(){
		return this.mensagem;
	}

	String getDestinatarioID(){
		return this.destinatarioID;
	}

	String getRemetenteID(){
		return this.remetenteID;
	}
}

public class RemoteMesageImpl extends UnicastRemoteObject implements RemoteMesage
{
	public RemoteDateImpl() throws RemoteException {  }
	public static ArrayList<Mensagem> caixaEntrada = new ArrayList<Mensagem>();

	public static String mailbox(String in){
		String[] dado;
		String remetenteID;
		String destinatarioID;
		String mensagem = "";
		

		try{
			dado = in.split(":");
			if (dado[0].equals("enviar")) {
				remetenteID = dado[1];
				destinatarioID = dado[2];
				mensagem = dado[3];
				Mensagem novaMensagem = new Mensagem(remetenteID, destinatarioID, mensagem);
				caixaEntrada.add(novaMensagem);
				return "mensagem recebida";
			}else if (dado[0].equals("receber")) {
				destinatarioID = dado[1];
				for (Mensagem i : caixaEntrada) {
					if(i.getDestinatarioID().equals(destinatarioID)){
						mensagem += "\n" +  "remetente " + i.getRemetenteID() +": "+i.getMensagem();
					}
				}
				if(mensagem != ""){
					return "Todas as mensagens para o destinatario "+ destinatarioID + ":" + mensagem;
				}else{
					return "Sem mensagens";
				}
			}else{
				return "ERRO de parâmetros";
			}
		}catch(Exception e){
			return "ERRO de parâmetros";
		}
	}
	public static void main(String[] args) throws IOException {
		try {
            RemoteDate dateServer = new RemoteMesageImpl();

            Naming.rebind("DateServer", dateServer);

            System.out.println("DateServer bound in registry");
        }
        catch (Exception e) {
            System.err.println(e);
        }
	}
}

/* Emanuel Tosto Holanda Vasconcelos-emanueltosto@gmail.com */
