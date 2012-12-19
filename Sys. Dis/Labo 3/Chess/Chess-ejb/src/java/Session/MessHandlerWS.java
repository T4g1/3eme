/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author delskev
 */
public class MessHandlerWS implements SOAPHandler<SOAPMessageContext> {
    private static Handler h;
    private static Logger l;
    static{
        try {
            h = new FileHandler("WSTest.log");
            l = Logger.getLogger("SOAPWS");
            l.addHandler(h);
        } catch (IOException ex) {
            Logger.getLogger(SOAPWS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(SOAPWS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean handleMessage(SOAPMessageContext messageContext) {
        String login = "";
        String password = "";
        try {
            SOAPHeader headers = messageContext.getMessage().getSOAPHeader();
            if(headers != null){
                NodeList nodeList = headers.getChildNodes();
                for(int i=0; i<nodeList.getLength(); i++){
                    Element element = (Element)nodeList.item(i);
                    if(element.getLocalName().equals("username")){
                        login = element.getFirstChild().getNodeValue();
                    }else if(element.getLocalName().equals("password")){
                        password = element.getFirstChild().getNodeValue();
                    }
                }
            }
        } catch (SOAPException ex) {
            System.err.println("Exception perso: "+ex);
        }
        
        if(login.equals("admin") && password.equals("admin")){ 
            l.log(Level.INFO, "Input message");
            return true;
        }
        l.log(Level.INFO, "Output message");
        return false;
    }
    
    public Set<QName> getHeaders() {
        l.log(Level.INFO, "getHeaders message");
        return Collections.EMPTY_SET;
    }
    
    public boolean handleFault(SOAPMessageContext messageContext) {
        l.log(Level.INFO, "handleFault message");
        return true;
    }
    
    public void close(MessageContext context) {
        l.log(Level.INFO, "Close message");
    }
}
