package br.com.casadocodigo.jms.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumidorDoTopico implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage text = (TextMessage) message;
		try {
			System.out.println("Consumidor do topico recebendo nota fiscal para financeiro: " + text.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
