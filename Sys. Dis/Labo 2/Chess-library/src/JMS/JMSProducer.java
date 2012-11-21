package JMS;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author T4g1
 */
public class JMSProducer {
    MessageProducer producer;
    Session session;
    Connection connection;

    public JMSProducer() {
        Context jndiContext;
        
        try {
            jndiContext = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory)
                    jndiContext.lookup("jms/javaee6/ConnectionFactory");
            Queue queue = (Queue)jndiContext.lookup("jms/javaee6/Queue");
            
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(queue);
        } catch (NamingException | JMSException ex) {
            Logger.getLogger(JMSProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Envoi un message au joueur dont l'id est donné
     * @param message       Message que l'on veut envoyer
     * @param id            Id du joueur
     */
    public void sendMessage(Long id, String message) {
        TextMessage textMessage;
        
        try {
            textMessage = session.createTextMessage(message);
            textMessage.setStringProperty("id", String.valueOf(id));
            
            producer.send(textMessage);
        } catch (JMSException ex) {
            Logger.getLogger(JMSProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(JMSProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
