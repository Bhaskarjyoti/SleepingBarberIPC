// MySleepingBarber.java 
//Author : Bhaskarjyoti Das
//Dated : 29/09/2014
// M Tech CSE Semester 1 Assignment 


package SleepingBarberIPC;

public class MySleepingBarber {

	public static void main(String[] args) throws InterruptedException 
	{
		int ch, barb, cust, htime;
		Thread t = Thread.currentThread(); 
		t.setName("My Main Thread"); 
		System.out.println("Main thread running MySleepingBarber: " + t); 
		//change the name of the thread 
		System.out.println("\n\n");
		
		ch=barb=cust=htime=0; // we initialize with 0 values 
		
		// The Main function of the program
		System.out.println("***************************************************************************");
		System.out.println("*                                                                         *");
		System.out.println("*          Welcome to the Sleeping Barber IPC Problem                      ");
		System.out.println("                                                                           ");
		System.out.println("*          By Bhaskarjyoti Das - CSE M Tech 2014 batch                     ");
		System.out.println("                                                                           ");
		System.out.println("                                                                           ");
		System.out.println("***************************************************************************");
	
		
		//initialize to test the system..otherwise comment out 
		
		ch=6;
		barb=4;
		cust=10;
		htime =1000; // in millisecond
		
	
	
		System.out.println("\nStarting Multiple Barber Multiple Customer Simulation with following parameters:");
		System.out.println("No of Barbers="+barb);
		System.out.println("No of Customers="+barb);
		System.out.println("No of Chairs in waiting room="+ch);
		System.out.println("Hair Cut time in millisec="+htime);
		System.out.println("");
		
		System.out.println("setting parameters in MyParameter type object for synchronized access");
		MyParameter param = new MyParameter(ch); //Let's say n chairs 		
	
		//Instantiate MyParameter with no of chairs
		
		//initialize the parameters in param for synchronized access
		
		param.set_targetNoCustomers(cust);
		//Note that noChairs and noHairCutDone are getting set as part of constructor
		
		MySaloon saloon = new MySaloon(barb,cust,htime); //instantiate the saloon object
		//no of barbers, customers and hair cut time (this is not used)
			
		saloon.runMySaloon(param); // Run Saloon simulation 
		System.out.flush();	
		System.out.println("\n\n**********************************************************\n");
		System.out.println("\n MySaloon : All the barber and customer threads are NOW up and working");
		System.out.println("\n**********************************************************\n");
		System.out.println("\n\n\n");
		
		System.out.println("Good Bye ... from main thread running MySleepingBarber "+t);
		System.out.flush();	
		return;
	}

}

 