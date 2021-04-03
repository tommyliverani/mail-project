package com.tliverani.app;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import model.JavaMailApp;
import exceptions.MissAuthenticationException;
import java.util.Calendar;
/**
 * Unit test for simple App.
 */
public class AppTest 
{
	
	private final String user="t.liverani@hotmail.it";
	private final String pass="Canecanis_200396";
   
   /*
    @Test
    public void authenticateTest()
    {
		try{
			JavaMailApp app=new JavaMailApp();
			app.authenticate(user, pass);
		}catch (Exception e){
			assert(false);
		}
		
		assert(true);
		
    }
	
	
	@Test
    public void sendMessageTest()
    {
        JavaMailApp app=new JavaMailApp();
		try{
			app.authenticate(user, pass);
			app.send("tommi.lverani@gmail.com","PROVA","CONTENUTO DEL MESSAGGIO");
		}catch (Exception e){
			System.out.println(e);
			assert(false);
		}
		assert(true);
	
    }
	

	
	@Test
    public void sendMessageBeforeAuthentication()
    {
        JavaMailApp app=new JavaMailApp();
		try{
			app.send("tommi.lverani@gmail.com","PROVA","CONTENUTO DEL MESSAGGIO");
			assert(false);
		}catch (MissAuthenticationException e){
			System.out.println(e);
			assert(true);
		}catch (Exception e){
			System.out.println(e);
			assert(false);
		}
    }
	
	
	@Test
    public void sendMessageNewConnection()
    {
        JavaMailApp app=new JavaMailApp();
		try{
			app.authenticate(user, pass);
			app.sendNewConnection("tommi.lverani@gmail.com","PROVA NUOVA CONNESSIONE","CONTENUTO DEL MESSAGGIO");
			assert(true);
		}catch (Exception e){
			System.out.println(e);
			
			assert(false);
		}
    }
	
	@Test
	public void dimensionImpactTest(){
		//sending 5 message with the same dimension
		JavaMailApp app=new JavaMailApp();
		try{
			app.authenticate(user, pass);
			String msg="Messaggio di dimensione molto piccola";
			for(int i=0; i<5 ; i++)
				app.send("tommi.lverani@gmail.com","OGGETTO",msg);

		}catch (Exception e){
			System.out.println(e);
			assert(false);
		}
		System.out.println("[DIMENSION]"+"Messaggio di dimensione molto piccola".length());
		app.printStats();
		//sending 5 message with growing dimension
		try{
			app=new JavaMailApp();
			app.authenticate(user, pass);
			String msg="Messaggio di dimensione molto piccola";
			for(int i=0; i<5 ; i++){
				//increase the message dimension
				for (int j=0; j<i; j++)
					msg=msg+msg;
				System.out.println("[DIMENSION " +i+" ]"+msg.length());
				app.send("tommi.lverani@gmail.com","OGGETTO",msg);
			}
			app.printStats();

		}catch (Exception e){
			System.out.println(e);
			assert(false);
		}
	}
	*/
		
		@Test 
		public void connectionImpactTest(){
			//sending 5 message with the same connection
			JavaMailApp app=new JavaMailApp();
			String msg="Messaggio di dimensione molto piccola";
			/*
			for (int j=0; j<3; j++)
					msg=msg+msg;
				*/
			try{
				app.authenticate(user, pass);
				for(int i=0; i<5 ; i++)
					app.send("tommi.lverani@gmail.com","OGGETTO",msg);

			}catch (Exception e){
				System.out.println(e);
				assert(false);
			}
			app.printStats();
			//sending 5 message with new connection
			app.resetStats();
			try{
				for(int i=0; i<5 ; i++)
					app.sendNewConnection("tommi.lverani@gmail.com","OGGETTO",msg);
				
				app.printStats();

			}catch (Exception e){
				System.out.println(e);
				assert(false);
			}
	}
		
		
	
	
	
	
	
	
	
}
