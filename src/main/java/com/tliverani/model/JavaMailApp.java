package model;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.NoSuchProviderException;

import java.util.Calendar;
import exceptions.MissAuthenticationException;

public class JavaMailApp{
	 private Properties props;
	 private String	 user;
	 private String  pass;
	 private boolean authenticated;
	 private Transport transport;
	 private Session session;
	 //stat
	 private long max;
	 private long min;
	 private double avg; //avg=tot/n
	 private long tot;
	 private long n;
	 
	  public  JavaMailApp(){
		  this.props= new Properties();
		  props.put("mail.transport.protocol", "smtp");
          props.put("mail.smtp.host", "smtp.live.com");
          props.put("mail.smtp.socketFactory.port", "587");
          props.put("mail.smtp.socketFactory.fallback", "false");
          props.put("mail.smtp.starttls.enable", "true");
          props.put("mail.smtp.auth", "true");
          props.put("mail.smtp.port", "587");  
          this.authenticated=false;
		  this.n=0;
		  this.tot=0;
		  this.max=-Integer.MIN_VALUE;
		  this.min=Integer.MAX_VALUE;
          
	  }
	  
	  public void authenticate(final String user, final String pass)throws AddressException, MessagingException, NoSuchProviderException{
		  this.user=user;
		  this.pass=pass;
		  this.authenticated=true;
		  this.session = Session.getInstance(this.props,
                new javax.mail.Authenticator() {
                     protected PasswordAuthentication getPasswordAuthentication()
                     {
                           return new PasswordAuthentication(user, pass);
                     }
                });
		 this.session.setDebug(true);
		 this.transport=session.getTransport("smtp");
		 this.transport.connect();
	  }
	 
	  
	 public void send(String recipient, String subject,String text) throws AddressException, MessagingException, MissAuthenticationException{ 
		  if(!this.authenticated)
			  throw new MissAuthenticationException();
		  Message message = new MimeMessage(this.session);
          message.setFrom(new InternetAddress(user)); 
          message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recipient));
          message.setSubject(subject);
          message.setText(text);
		  Long start_time = Calendar.getInstance().getTimeInMillis();
		  this.transport.send(message,message.getAllRecipients());
		  Long end_time = Calendar.getInstance().getTimeInMillis();
		  updateStats(end_time-start_time);
		  
	 }
	 
	 public void sendNewConnection(String recipient, String subject,String text) throws AddressException, MessagingException, MissAuthenticationException{
		 if(!this.authenticated)
			  throw new MissAuthenticationException();
		 this.transport.close();
		 this.transport.connect();
		 send(recipient, subject,text);
	 }
	 
	 private void updateStats(long time){
		 if(time> this.max)
			this.max=time;
		 if(time<this.min)
			this.min=time;
		this.tot=this.tot+time;
		this.n=this.n+1;
		this.avg= this.tot/this.n; 
	 }
	 
	 private void closeConnection()throws MessagingException {
		 this.transport.close();
	 }
	 
	 public void printStats(){
		 System.out.println("[STATS] number of message:" + this.n);
		 System.out.println("[STATS] max time:" + this.max);
		 System.out.println("[STATS] min time:" + this.min);
		 System.out.println("[STATS] avg time:" + this.avg);
	 }
	 
	 public void resetStats(){
		 this.n=0;
		 this.tot=0;
		 this.max=-Integer.MIN_VALUE;
		 this.min=Integer.MAX_VALUE;
	 }
	 
}