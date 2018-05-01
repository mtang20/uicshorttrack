import java.util.ArrayList;
import java.util.Scanner;

import microsoft.exchange.webservices.data.core.ExchangeService;

public class TestAppointment {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in); 
		System.out.println("Enter your email");
		String email=sc.next();
		System.out.println("Enter your password");
		String password=sc.next();
		AppointmentHelper appointmentHelper = new AppointmentHelper(email, password);
		
		System.out.println("Enter an option: 1.Create appointment 2.Update appointment 3. Cancel appointment");
		int option=sc.nextInt();
		switch(option) {
			case 1:
				
				createAppointmentHelper(appointmentHelper, sc, email);
				break;
				
			case 2:
				
				updateAppointmentHelper(appointmentHelper, sc, email);
				break;
			case 3:
				cancelAppointmentHelper(appointmentHelper, sc, email);
			default:
				System.out.println("Please enter valid input");
				
		}
}
	public static void createAppointmentHelper(AppointmentHelper appointmentHelper, Scanner sc, String emailAddress) {
		System.out.println("Please enter the start date in this format yyyy-mm-dd");
		String startdate = sc.next();
		System.out.println("Please enter the start time in this format 00:00:00");
		String starttime = sc.next();
		System.out.println("Please enter the end date in this format yyyy-mm-dd");
		String enddate = sc.next();
		System.out.println("Please enter the end time in this format 00:00:00");
		String endtime = sc.next();
		System.out.println("Please enter the subject of the appointment");
		String subject = sc.next();
		System.out.println("Please enter the body of the appointment");
		String body = sc.next();
		System.out.println("Please enter the email address of the attendee");
		String attendee = sc.next();
		ArrayList<String> attendeeList = new ArrayList<>();
		attendeeList.add(attendee);
		appointmentHelper.createAppointment(emailAddress, startdate, starttime, enddate, endtime, subject, body, attendeeList);
	}
	public static void updateAppointmentHelper(AppointmentHelper appointmentHelper, Scanner sc, String emailAddress) {
		System.out.println("Enter the appointment ID to update");
		String appointmentId=sc.next();
		System.out.println("Please enter the start date in this format yyyy-mm-dd");
		String startdate = sc.next();
		System.out.println("Please enter the start time in this format 00:00:00");
		String starttime = sc.next();
		System.out.println("Please enter the end date in this format yyyy-mm-dd");
		String enddate = sc.next();
		System.out.println("Please enter the end time in this format 00:00:00");
		String endtime = sc.next();
		System.out.println("Please enter the subject of the appointment");
		String subject = sc.next();
		System.out.println("Please enter the body of the appointment");
		String body = sc.next();
		System.out.println("Please enter the email address of the attendee");
		String attendee = sc.next();
		ArrayList<String> attendeeList = new ArrayList<>();
		attendeeList.add(attendee);
		appointmentHelper.updateAppointment(emailAddress, appointmentId, startdate, starttime, enddate, endtime, subject, body, attendeeList);
	}
	public static void cancelAppointmentHelper(AppointmentHelper appointmentHelper, Scanner sc, String emailAddress) {
		System.out.println("Enter the appointment ID to update");
		String appointmentId=sc.next();
		appointmentHelper.cancelAppointment(emailAddress, appointmentId);
	}
}
