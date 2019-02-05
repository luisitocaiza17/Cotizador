package com.qbe.cotizador.util;
import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;

public class ManejoColas {

	static Connection connection = null;
	
	// Variables cola local
    public static String userColaProdutor= "admin";
    public static String passColaProductor = "admin";
    public static String urlColaProductor = "tcp://10.10.21.116:61616";
    
    // Variables cola destino
    public static String userColaConsumo= "system";
    public static String passColaConsumo= "manager";
    public static String urlColaConsumo = "tcp://10.57.94.31:61616";    
    
    public static void productorMensajes(String mensaje){
		   try {
			   
			   Properties props = new Properties();
		       props.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		       props.put("java.naming.provider.url", urlColaProductor);
		       props.put("java.naming.security.principal", userColaProdutor);
		       props.put("java.naming.security.credentials", passColaProductor);
		       props.put("connectionFactoryNames", "QueueCF");
		       props.put("queue.LoanRequestQ", "jms.LoanRequestQ");
		       props.put("queue.LoanResponseQ", "jms.LoanResponseQ");		       
		       props.put("queue.Maintenance", "jms.Maintenance");
		       
		       // Inicializa el InitialContext
		       InitialContext ic = new InitialContext(props);		      
				
				// Realiza el lookup del connetionFactory
				QueueConnectionFactory connectionFactory = (QueueConnectionFactory) ic.lookup("QueueCF");
				
				// Crea la conexion
				QueueConnection connection = connectionFactory.createQueueConnection();
				
				// Inicia la conexion 
				connection.start();

				// Obtener la cola en la que se publicarán los mensajes
				Queue destino = (Queue) ic.lookup("LoanRequestQ");

				// Crea la sesion
				QueueSession session = connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
				
				// Preparar el publicador al Queue
				MessageProducer producer = session.createProducer(destino);

				// Preparar el receptor
				QueueReceiver receiver = session.createReceiver(destino);

				// Crea el mensaje
				TextMessage message = session.createTextMessage(mensaje);
				
				// Envia el mensaje
				producer.send(message);
							
				// Recibe el mensaje con un timeout de 10 segundos
				Message msg = receiver.receive(10000);
				
				// Verifica que el mensaje recivido sea de tipo TextMessage
				if (msg instanceof TextMessage) {
					
					System.out.println("Mensaje recibido: " + ((TextMessage)msg).getText());
					
				}
				// Cierra la sesion
				session.close();
				
				// Cierra la conexion
				connection.close();

	        } catch (JMSException ex) {
	        	System.out.println(ex.getMessage());
	            //Logger.getLogger(Utilitarios.class.getName()).log(Level.ALL, null, ex);
	        } catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static String consumiMensajes(){
	
		 try {
			   
			   Properties props = new Properties();
		       props.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		       props.put("java.naming.provider.url", urlColaConsumo);
		       props.put("java.naming.security.principal", userColaConsumo);
		       props.put("java.naming.security.credentials", passColaConsumo);
		       props.put("connectionFactoryNames", "QueueCF");
		       props.put("queue.LoanRequestQ", "jms.LoanRequestQ");
		       props.put("queue.LoanResponseQ", "jms.LoanResponseQ");		       
		       props.put("queue.Maintenance", "jms.Maintenance");
		       
		       // Inicializa el InitialContext
		       InitialContext ic = new InitialContext(props);		      
				
				// Realiza el lookup del connetionFactory
				QueueConnectionFactory connectionFactory = (QueueConnectionFactory) ic.lookup("QueueCF");
				
				// Crea la conexion
				QueueConnection connection = connectionFactory.createQueueConnection();
				
				// Inicia la conexion 
				connection.start();

				// Obtener la cola en la que se consume los mensajes
				Queue destino = (Queue) ic.lookup("LoanResponseQ");								

				// Crea la sesion
				QueueSession session = connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
				
				// Preparar el consumidor al Queue								
				MessageConsumer consumer = session.createConsumer(destino);
				
				// Verificamos el mensaje en Queue
				Message message = consumer.receive(10000);
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					String text = textMessage.getText();					
					System.out.println("Mensaje Consumido"+text);									
				}
				

				// Cierra la sesion
				session.close();
				
				// Cierra la conexion
				connection.close();

	        } catch (JMSException ex) {
	        	System.out.println(ex.getMessage());
	            //Logger.getLogger(Utilitarios.class.getName()).log(Level.ALL, null, ex);
	        } catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		


//					String text = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"+"<cotizaciones>"+
//"<cotizacion id=\"1\">"+
//"<lista_parametro>"+
//"<parametros tipo=\"vehiculo\" >"+
//"<parametro nombre=\"id\" valor=\"22\"/>"+
//"<parametro nombre=\"rc\" valor=\"8\"/>"+
//"<parametro nombre=\"todoriesgo\" valor=\"909.9\"/>"+
//"<parametro nombre=\"robototal\" valor=\"890.0\"/>"+
//"<parametro nombre=\"danototal\" valor=\"788.9\"/>"+
//"</parametros>"+
//"</lista_parametro>"+
//"</cotizacion>"+
//"<cotizacion id=\"2\">"+
//"<lista_parametro>"+
//"<parametros tipo=\"vehiculo\" >"+
//"<parametro nombre=\"id\" valor=\"23\"/>"+
//"<parametro nombre=\"rc\" valor=\"800.4\"/>"+
//"<parametro nombre=\"todoriesgo\" valor=\"700.8\"/>"+
//"<parametro nombre=\"robototal\" valor=\"200\"/>"+
//"<parametro nombre=\"danototal\" valor=\"500\"/>"+
//"</parametros>"+
//"</lista_parametro>"+
//"</cotizacion>"+
//"</cotizaciones>";
					
					
					
				//	try {
												
//						DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//				        //Use document builder factory
//				        DocumentBuilder builder;
//						builder = factory.newDocumentBuilder();
//						 //Parse the document
//				        Reader reader = new CharArrayReader(text.toCharArray());
//				        Document doc = builder.parse(new org.xml.sax.InputSource(reader));
//				        
//				        // Elementos del XML
//			            NodeList nodoListas = doc.getDocumentElement().getElementsByTagName("cotizacion");
//			            Node nodoLista = nodoListas.item(0);
			            
//			            String 
//			            
//			            if (nodoLista.getNodeType() == Node.ELEMENT_NODE) {
//			            	Element detalle = (Element) nodoLista;
//			            	identificador = detalle.getElementsByTagName("identificador").item(0).getChildNodes().item(0).getNodeValue();
////			            }
//			            <cotizacion id="1">
//			            <lista_parametro>
//			            <parametros tipo="vehiculo" >
//			            <parametro nombre="id" valor="22"/>
//			            <parametro nombre="rc" valor="8"/>
//			            <parametro nombre="todoriesgo" valor="909.9"/>
//			            <parametro nombre="robototal" valor="890.0"/>
//			            <parametro nombre="danototal" valor="788.9"/>
//			            </parametros>
//			            </lista_parametro>
//			            </cotizacion>
//			            <cotizacion id="2">
//			            <lista_parametro>
//			            <parametros tipo="vehiculo" >
//			            <parametro nombre="id" valor="23"/>
//			            <parametro nombre="rc" valor="800.4"/>
//			            <parametro nombre="todoriesgo" valor="700.8"/>
//			            <parametro nombre="robototal" valor="200"/>
//			            <parametro nombre="danototal" valor="500"/>
//			            </parametros>
//			            </lista_parametro>
//			            </cotizacion>
//			            
			            
			            
//					} catch (ParserConfigurationException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (SAXException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
			       
					
			
		   return ""; 
	}
    
}
