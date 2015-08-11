package br.com.casadocodigo.jms.enviador;

import java.util.Scanner;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EnviadorParaTopico {

	public static void main(String[] args) throws NamingException {
		InitialContext ic = new InitialContext();
		ConnectionFactory connection = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
		Topic topic = (Topic) ic.lookup("jms/TOPIC.LIVRARIA");
		
		try (JMSContext context = connection.createContext("jms", "jms2")) {
			JMSProducer producer = context.createProducer();
			
			System.out.println("Digite para enviar para o Topico: ");
			Scanner teclado = new Scanner(System.in);
			while (teclado.hasNextLine()) {
				String mensagem = teclado.nextLine();
				
				producer.send(topic, mensagem);
			}
			teclado.close();
		}
	}
	
}
