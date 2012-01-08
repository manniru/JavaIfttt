package org.joe.ifttt.server.channel;
/**
 * File: 			MailChannel.java
 * Author: 			Wei Tong (tongwei521@gmail.com)
 * Last modified:	2011/12/10
 * Description:
 * The definition and implement of Mail Channel
 */
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;


import org.apache.commons.mail.*;
import org.joe.ifttt.server.content.CommonContent;
import org.joe.ifttt.server.type.ChannelType;
import org.joe.ifttt.server.user.ChannelUser;
import org.joe.ifttt.server.user.CommonUser;


public class MailChannel extends Channel{
	private String textString = "\0";
	private String attachString = "\0";
	private ChannelUser user;
	
	public MailChannel() {
		/**the default constructor*/
		super.setChannelType(ChannelType.MAIL);
	}
	public MailChannel(ChannelUser user) {
		/**the constructor with parameter user*/
		super.setChannelType(ChannelType.MAIL);
		this.user = new ChannelUser();
		this.user.setUsername(user.getUsername());
		this.user.setPassword(user.getPassword());
		System.out.println(this.user.getUsername() + ":" + this.user.getPassword());
	}

	public void setUser(CommonUser user) {
		/**set the user of the mail channel*/
		this.user = new ChannelUser();
		this.user.setUsername(user.getUsername());
		this.user.setPassword(user.getPassword());
	}
	
	public ChannelUser getUser () {
		/**get the user of the mail channel*/
		return user;
	}
	
	public void write(CommonContent content) {
		/**input the content of the mail*/
		this.textString = content.getTextString();
		this.attachString = content.getAttachString();
	}
	
	public CommonContent read() {
		/**read the content of the mail */
		CommonContent obj = new CommonContent();
		return obj;
	}

	
	public int getMailNumber() {
		return -1;
	}
	
	//get the num of mails
	public int numOfMail () throws MessagingException {
		/**get the number of mails*/
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.pop3.socketFactory.fallback", "false");
        props.setProperty("mail.pop3.port", "995");
        props.setProperty("mail.pop3.socketFactory.port", "995");
        
        Session session = Session.getDefaultInstance(props, null);  
        URLName urln = new URLName("pop3", "pop.gmail.com", 995, null,  
                user.getUsername(), user.getPassword());  
        
        Store store = null;
        Message message[] = null;
		try {
			store = session.getStore(urln);
			store.connect();  
	        Folder inbox = store.getFolder("INBOX");  
	        inbox.open(Folder.READ_ONLY);  
	        message= inbox.getMessages();  
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return message.length;
	}
	
	//receive mail form mail box
	public void receiveMail () throws Exception {
		/**receive mails from mailbox*/
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.pop3.socketFactory.fallback", "false");
        props.setProperty("mail.pop3.port", "995");
        props.setProperty("mail.pop3.socketFactory.port", "995");
        
        Session session = Session.getDefaultInstance(props, null);  
        URLName urln = new URLName("pop3", "pop.gmail.com", 995, null,  
                user.getUsername(), user.getPassword());  
        
        Store store;
        Message message[] = null;
        
		try {
			store = session.getStore(urln);
			store.connect();  
	        Folder inbox = store.getFolder("INBOX");  
	        inbox.open(Folder.READ_ONLY);  
	        
	        message= inbox.getMessages(); 
	        
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
        System.out.println("Messages's length: " + message.length);  
        
        for (int i = 0; i < message.length; i++) { 
        	MimeMessage msg = (MimeMessage) message[i];
        	if (!isRead(msg)) {
				System.out.println("i = " + "SEND: " + getSender(msg) + ", SUBJECT: "
        	+ getSubject(msg) + ", DATE: "  + getSentDate(msg, "yy/MM/dd HH:mm"));
			}
        }   
	}
	
    private String getSender(MimeMessage mimeMessage) throws Exception {  
    	/**get the sender of the mail*/
        InternetAddress address[] = (InternetAddress[]) mimeMessage.getFrom();   
        String from = address[0].getAddress();   
        if (from == null)   
            from = "";   
        String personal = address[0].getPersonal();   
        if (personal == null)   
            personal = "";   
        String fromaddr = personal + "<" + from + ">";   
        return fromaddr;   
    }  
    
    private String getSubject(MimeMessage mimeMessage) throws MessagingException {   
        /**get the subject of the mail*/
    	String subject = "";   
        try {   
            subject = MimeUtility.decodeText(mimeMessage.getSubject());   
            if (subject == null)   
                subject = "";   
        } catch (Exception exce) {}   
        return subject;   
    }   
    
    private String getSentDate(MimeMessage mimeMessage, String dateformat) throws Exception {   
    	/**get the send date of the mail*/
    	Date sentdate = mimeMessage.getSentDate();   
        SimpleDateFormat format = new SimpleDateFormat(dateformat);   
        return format.format(sentdate);   
    }   
    
    private String getBodyText(Object bodytext) {  
    	/**get the body text of the mail*/
        return bodytext.toString();   
    }   
    
    private boolean isRead(MimeMessage mimeMessage) throws MessagingException {   
        /**if the mail read?*/
    	boolean isread = false;   
        Flags flags = ((Message) mimeMessage).getFlags();   
        Flags.Flag[] flag = flags.getSystemFlags();   
        System.out.println("flags's length: " + flag.length);  
        
        for (int i = 0; i < flag.length; i++) {   
            if (flag[i] == Flags.Flag.SEEN) {   
                isread = true;
                break;   
            }   
        }   
        return isread;   
    }  
    
    public void sendSimpleMail(CommonContent content) throws Exception { 
    	/**send a simple mail*/
    	SimpleEmail email = new SimpleEmail ();
    	email.setHostName("smtp.gmail.com");  
        email.setSSL(true);  
        email.setSmtpPort(465);   
        email.setTLS(true);    //gmail  
    	email.setAuthentication (user.getUsername(), user.getPassword() );
    	email.addTo ( "Joetung1991@gmail.com", "Joe" );
		email.setFrom ( "tongwei521@gmail.com", "Joetung" );
		email.setSubject ( "Test message" );
 		email.setMsg (content.getTextString());
 		email.send ();
		System.out.println ( "Send email successful!" );
    }
    
    public void sendSimpleMail(CommonContent content, String receiver) throws Exception { 
    	/**send a simple mail*/
    	SimpleEmail email = new SimpleEmail ();
    	email.setHostName("smtp.gmail.com");  
        email.setSSL(true);  
        email.setSmtpPort(465);   
        email.setTLS(true);    //gmail  
    	email.setAuthentication (user.getUsername(), user.getPassword() );
    	email.addTo ( "receiver", "Joe" );
		email.setFrom ( "tongwei521@gmail.com", "Joetung" );
		email.setSubject ( "Test message" );
 		email.setMsg (content.getTextString());
 		email.send ();
		System.out.println ( "Send email successful!" );
    }
    public void sendAttachMail() throws Exception {  
    	/**send a mail wiht attach*/
        MultiPartEmail email = new MultiPartEmail();  
    	email.setHostName("smtp.gmail.com");  
        email.setSSL(true);  
        email.setSmtpPort(465);   
        email.setTLS(true);    //gmail  
    	email.setAuthentication (user.getUsername(), user.getPassword());
    	email.addTo ( "Joetung@live.com", "Joe" );
		email.setFrom ( "tongwei521@gmail.com", "Joetung" );
		email.setSubject ( "Test message" );
 		email.setMsg ( "This is a attach test of commons-email" );
        email.setCharset("gbk");  
          
        EmailAttachment attach = new EmailAttachment();  
        attach.setName("attachFileName");  
        attach.setPath("f:\\ok.txt");  
        attach.setDescription(EmailAttachment.ATTACHMENT);  
      
        email.attach(attach );  
        email.send();  
        System.out.println ( "Send email successful!" );
    }  
}
