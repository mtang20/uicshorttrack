import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.service.ConflictResolutionMode;
import microsoft.exchange.webservices.data.core.service.item.Appointment;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.ItemId;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

public class AppointmentHelper {
	ExchangeService service;
	ExchangeCredentials credentials;
	String emailAddress;
	String startdate;
	String starttime;
	String enddate;
	String endtime;
	String subject;
	String body;
	ArrayList<String> requiredAttendees;
	
	AppointmentHelper(String email, String password){
		service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
		credentials = new WebCredentials(email, password);
		service.setCredentials(credentials);
	}
	
	public void createAppointment(String emailAddress, String startdate, String starttime, String enddate, String endtime, String subject, String body, ArrayList<String> requiredAttendees) {

		try {
			service.autodiscoverUrl(emailAddress);
			Appointment appointment = new Appointment(service);
			appointment.setSubject(subject);
			appointment.setBody(MessageBody.getMessageBodyFromText(body));
			SimpleDateFormat formatter = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			Date startDate = formatter.parse(startdate+" "+starttime);
			Date endDate = formatter.parse(enddate+" "+endtime);
			appointment.setStart(startDate);//new Date(2010-1900,5-1,20,20,00));
			appointment.setEnd(endDate); //new Date(2010-1900,5-1,20,21,00));
			for(String attendee : requiredAttendees) {
				appointment.getRequiredAttendees().add(attendee);
			}
			appointment.save();
			System.out.println(appointment.getId());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void updateAppointment(String emailAddress, String appointmentId, String startdate, String starttime, String enddate, String endtime, String subject, String body, ArrayList<String> requiredAttendees) {
		try {
			service.autodiscoverUrl(emailAddress);
			Appointment appointment= Appointment.bind(service, new ItemId(appointmentId));
			SimpleDateFormat formatter = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startDate = formatter.parse(startdate+" "+starttime);
			Date endDate = formatter.parse(enddate+" "+endtime);
			
			appointment.setBody(MessageBody.getMessageBodyFromText(body));

			appointment.setStart(startDate);
			appointment.setEnd(endDate);
			appointment.setSubject(subject);
			for(String attendee : requiredAttendees) {
				appointment.getRequiredAttendees().add(attendee);
			}
			appointment.update(ConflictResolutionMode.AutoResolve);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void cancelAppointment(String emailAddress, String appointmentId) {
		try {
			service.autodiscoverUrl(emailAddress);
			Appointment appointment = Appointment.bind(service, new ItemId(appointmentId));
			appointment.cancelMeeting();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
