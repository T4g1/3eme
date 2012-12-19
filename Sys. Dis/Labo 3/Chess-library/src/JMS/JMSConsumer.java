package JMS;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author T4g1
 */
public class JMSConsumer {
    Session session;
    Connection connection;
    MessageConsumer consumer;
    Queue queue;

    public JMSConsumer(String messageSelector) {
        Context jndiContext;
        
        try {
            jndiContext = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory)
                    jndiContext.lookup("jms/javaee6/ConnectionFactory");
            queue = (Queue)jndiContext.lookup("jms/javaee6/Queue");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            if(messageSelector == null) {
                consumer = session.createConsumer(queue);
            }else {
                consumer = session.createConsumer(queue, "id like'" + messageSelector + "'");
            }
        } catch (NamingException | JMSException ex) {
            Logger.getLogger(JMSConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addListener(MessageListener listener) {
        try {
            consumer.setMessageListener(listener);
            connection.start();
        } catch (JMSException ex) {
            Logger.getLogger(JMSConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
