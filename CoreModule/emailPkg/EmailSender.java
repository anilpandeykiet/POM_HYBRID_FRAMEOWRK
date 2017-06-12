/**
 * 
 */
package emailPkg;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author cpandeak
 *
 */
public class EmailSender {

	private static String hostName = "webmail.jci.com";
	private static String port = null;
	private static String fromAddress = null;
	private static String toAddressList = "";
	private static String emailSubject = "";
	private static String emailBody = "";
	private static String attachmentPath = "";

	private static Properties properties = null;
	private static Session session = null;
	private static Message message = null;
	private static MimeMessage mimeMessage = null;
	private static MimeBodyPart bodyPart = null;

	public static void sendEmail(boolean sendEmail) {
		if (sendEmail) {

			try {
				properties = new Properties();
				properties.setProperty("mail.smtp.host", hostName);
				session = Session.getDefaultInstance(properties);
				
				
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
						System.out.println(
								"Error including attachment " + attachmentPath + "to the message \n" + ioe.getMessage());
					}
					multipart.addBodyPart(includeAttachment);
				}
				
				// Set multipart as email content
				message.setContent(multipart);

				// Send Email
				Transport.send(message);

			} catch (MessagingException e) {
				System.out.println("Error sending Email...\n"+e.getMessage());
			}
		}else{
			System.out.println("User choosed not to send email");
		}
	}
}
