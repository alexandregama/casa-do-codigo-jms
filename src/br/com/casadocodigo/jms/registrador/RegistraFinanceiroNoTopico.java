package br.com.casadocodigo.jms.registrador;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.casadocodigo.jms.consumer.ConsumidorDoTopico;

public class RegistraFinanceiroNoTopico {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext();
		ConnectionFactory connection = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		Topic topic = (Topic) ic.lookup("jms/TOPIC.LIVRARIA");
		
		try (JMSContext context = connection.createContext("jms", "jms2")) {
			context.setClientID("Financeiro");
			JMSConsumer consumer = context.createDurableConsumer(topic, "AssinaturaNotas");
			consumer.setMessageListener(new ConsumidorDoTopico());
			
			context.start();
			
			Scanner teclado = new Scanner(System.in);
			System.out.println("Financeiro esperando as mensagens do JMS");
			System.out.println("Aperte Enter para fechar a conexão");
			teclado.nextLine();
			teclado.close();
			
			context.stop();
		}
	}
	
}
