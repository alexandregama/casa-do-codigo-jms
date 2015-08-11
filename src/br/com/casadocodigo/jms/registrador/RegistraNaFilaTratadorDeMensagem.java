package br.com.casadocodigo.jms.registrador;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.casadocodigo.jms.consumer.TratadorDeMensagem;

public class RegistraNaFilaTratadorDeMensagem {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext();
		ConnectionFactory connection = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		Queue queue = (Queue) ic.lookup("jms/FILA.GERADOR");
		
		try (JMSContext context = connection.createContext("jms", "jms2")) {
			JMSConsumer consumer = context.createConsumer(queue);
			consumer.setMessageListener(new TratadorDeMensagem());
			context.start();
			Scanner teclado = new Scanner(System.in);
			System.out.println("Tratador esperando as mensagens na fila do JMS");
			
			teclado.nextLine();
			teclado.close();
			context.stop();
		}
	}
	
}