package br.com.casadocodigo.jms.registrador;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.casadocodigo.jms.consumer.ConsumidorDaFila;

public class RegistraEnvioSomenteDeEbookNaFila {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext();
		ConnectionFactory connection = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		Queue queue = (Queue) ic.lookup("jms/FILA.GERADOR");
		
		try (JMSContext context = connection.createContext("jms", "jms2")) {
			context.setClientID("GeradorEbook");
			
			JMSConsumer consumer = context.createConsumer(queue, "formato='ebook'");
			consumer.setMessageListener(new ConsumidorDaFila());
			
			context.start();
			Scanner teclado = new Scanner(System.in);
			System.out.println("Consumidor esperando as mensagens na fila do JMS");
			System.out.println("Aperte Enter para fechar a conexão");
			
			teclado.nextLine();
			teclado.close();
			context.stop();
		}
	}
	
}
