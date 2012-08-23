package controllers;

import play.mvc.Http.WebSocketClose;
import play.mvc.Http.WebSocketEvent;
import play.mvc.Http.WebSocketFrame;
import play.mvc.WebSocketController;

public class BuscaminasSocket extends WebSocketController {
	
//	public static void hello(String name) {
//		System.out.println("Me estan conectando");
//		outbound.send("Hola %s!", name);
////		outbound.close();
//	}

		 
	    public static void hello() {
	    	System.out.println("Thread " + Thread.currentThread().getId() + ": conectado");
	        while(inbound.isOpen()) {
	             WebSocketEvent e = await(inbound.nextEvent());
	             if(e instanceof WebSocketFrame) {
	                  WebSocketFrame frame = (WebSocketFrame)e;
	                  if(!frame.isBinary) {
	                      if(frame.textData.equals("quit")) {
	                    	  outbound.send("Bye!");
	                          disconnect();
	                      } else {
	                          System.out.println("Servidor: Recibido: " + frame.textData);
	                          outbound.send("Thread " + Thread.currentThread().getId() + ": %s", frame.textData);
	                      }
	                  }
	             }
	             if(e instanceof WebSocketClose) {
	            	 System.out.println("Thread " + Thread.currentThread().getId() + ": cerrado");
	             }
	        }
	    }
	 
	
}
