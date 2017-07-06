/**
 * 
 */
package emailPkg;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import fileReadingPkg.ReadPropertyFile;
import fileReadingPkg.ReadTextFile;

// TODO: Auto-generated Javadoc
/**
 * @author Anil Pandey
 *
 */
public class EmailSender {

	private static String hostName = null;
	private static String port = null;

	private static String fromAddress = null;
	private static String toAddressList = null;
	private static String emailSubject = null;
	private static String emailBody = null;

	private static String attachmentPath = null;

	private static Properties properties = null;
	private static Session session = null;
	private static Message message = null;

	//private static MimeMessage mimeMessage = null;

	private static MimeBodyPart bodyPart = null;

	private static ReadPropertyFile rpr = null;

	/**
	 * Send email.
	 *
	 * @param sendEmail
	 *            the send email
	 * @param emailConfigFile
	 *            the email config file
	 */
	public static void sendEmail(String sendEmail, String emailConfigFile) {
		if (sendEmail.equalsIgnoreCase("true")) {
			
			Authenticator authenticator = new Authenticator()
	        {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication()
	            {
	                return new PasswordAuthentication("<Email username>", "Email password");
	            }
	        };

			rpr = ReadPropertyFile.getInstance(emailConfigFile);
			setEmailConfiguration(rpr);

			try {
				properties = new Properties();
				
				properties.setProperty("mail.smtp.host", hostName);
				properties.setProperty("mail.smtp.port", port);
				properties.setProperty("mail.smtp.starttls.enable", "true");
				properties.setProperty("mail.smtp.user", "");
				properties.setProperty("mail.smtp.password", "");
				properties.setProperty("mail.debug", "true");
				properties.setProperty("mail.smtp.auth", "true");

				session = Session.getInstance(properties, authenticator);
				//session = Session.getDefaultInstance(properties);

				// Create new Email Message
				message = new MimeMessage(session);

				// Set From Address
				message.setFrom(new InternetAddress(fromAddress));

				// Set Recipients
				String[] toAddressListAray = toAddressList.split(";");
				InternetAddress[] recipients = new InternetAddress[toAddressListAray.length];

				for (int i = 0; i < recipients.length; i++) {
					recipients[i] = new InternetAddress(toAddressListAray[i].trim());
				}

				// InternetAddress[] recipients = { new
				// InternetAddress(toAddressList) };
				message.setRecipients(Message.RecipientType.TO, recipients);

				// Set Email Subject
				message.setSubject(emailSubject);

				// Set Sent date
				message.setSentDate(new Date());

				// Create Message Part
				bodyPart = new MimeBodyPart();
				bodyPart.setContent(emailBody, "text/plain; charset=utf-8");

				// Create Multipart for attachment
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(bodyPart);

				// Add Attachment
				if (attachmentPath != null && attachmentPath.length() > 0) {
					MimeBodyPart includeAttachment = new MimeBodyPart();

					try {
						includeAttachment.attachFile(attachmentPath);
					} catch (IOException ioe) {
						ioe.printStackTrace();
						System.out.println("Error including attachment " + attachmentPath + "to the message \n"
								+ ioe.getMessage());
					}
					multipart.addBodyPart(includeAttachment);
				}else{
					System.out.println("Attachment file not found");
				}

				// Set multipart as email content
				message.setContent(multipart);

				// Send Email
				Transport.send(message);
				
				System.out.println("Email Sent...");

			} catch (MessagingException e) {
				e.printStackTrace();
				System.out.println("Error sending Email...\n" + e.getMessage());
			}
		} else {
			System.out.println("User choosed not to send email");
		}
	}

	/**
	 * Sets the email configuration.
	 *
	 * @param rpr2
	 *            the new email configuration
	 */
	private static void setEmailConfiguration(ReadPropertyFile rpr2) {
		if (rpr != null) {
			hostName = rpr.getKey("hostName");
			port = rpr.getKey("port");
			fromAddress = rpr.getKey("fromAddress");
			toAddressList = rpr.getKey("toAddressList");
			emailSubject = rpr.getKey("emailSubject");
			String emailBodyTextFile = rpr.getKey("emailBodyTextFile");
			attachmentPath = rpr.getKey("attachmentPath");
			
			emailBody = ReadTextFile.getInstance(emailBodyTextFile).getTextFileContent().toString();
		}

	}
}
