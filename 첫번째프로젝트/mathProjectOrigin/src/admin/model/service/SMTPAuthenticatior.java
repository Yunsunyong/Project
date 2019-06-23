package admin.model.service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticatior extends Authenticator {
	 @Override
	    protected PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication("aftersay3@gmail.com","ek2tleostm");
	    }
}
