//Barber.java 
//Author : Bhaskarjyoti Das
//Dated : 29/09/2014
// M Tech CSE Semester 1 Assignment 



package SleepingBarberIPC;

import java.util.concurrent.*;

// This class extends thread as we want all the objects of this class to run
// as a thread

//class Barber implements Runnable interface as we want to keep the multiple inheritance option open

class Barber implements Runnable

{
	
	private int barberno;
	private MySemaphore cust, barb;
	private MyParameter p;
	long threadId;
	//constructor 
	
	public Barber(int n, MySemaphore customers, MySemaphore barbers, MyParameter param)
	{
		cust = customers;
		barb=barbers;
		p=param;
		barberno=n;
		System.out.printf("Inside the barber constructor(), starting to work barberid=", barberno);
	}

	//getter and setters
	
	public int getbarberno()
	{
		return barberno;
	}
	
	public void setbarberno(int n)
	{
		barberno=n;
	}
	

	//work function for the barber object 
	//passed the reference to the mutex, semaphore and class variables of 
	//parameter class as those are outside the scope of this 
	
	//public void run(MySemaphore mutex, MySemaphore customers, MySemaphore barbers, MyParameter param) throws InterruptedException
	public void run()
	{
		//just make the announcement
		threadId=Thread.currentThread().getId();	

		System.out.println("Hi, Barber inside the Runnable Object..yet to link with Barber threadid="+threadId);
		
		//do the work here
		
		try {
			barber_work(cust,barb,p);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Barber Child thread finished!"+threadId);
		return;
	}
	
	public void barber_work(MySemaphore customers, MySemaphore barbers, MyParameter param) throws InterruptedException
		
	{
			//Barbers will wait till the target number of customers is NOT met 
			// AND the number of customers waiting in non-zero
			//There may be a situation where target is not met but no customer is waiting as they came and left 
		
			while (param.get_noHairCutDone()!=param.get_targetNoCustomers())  
			
					//while(true) 
				//runs in an infinite loop
				// you can put in target customer no here.
				// once achieved, the shop will stop
				
			{
				System.out.println("Hi, inside barber thread and executing the barber_work method");
				System.out.println("Inside barber_Work:target no of customers="+param.get_targetNoCustomers());
				System.out.println("Inside barber_Work:Number of hair cut done="+param.get_noHairCutDone());
				
				//accessing the semaphore ..one customer is taken
				customers.down();
				System.out.println("Barber has taken one customer");
				// produce the chair using producer-consumer paradigm
				TimeUnit.MILLISECONDS.sleep(1000);
				//Barber is available again
				param.increment(); 
				System.out.println("Barber released one chair..Chair remaining = "+param.noChairs);
				barbers.up();
				System.out.println("Barber"+barberno+"  is done with 1 hair cut & is available for next job..");
				System.out.flush();					
			} 
			
			System.out.printf("Hi, inside barber thread and executing the barber_work method");
			System.out.println("Inside barber_Work:target no of customers="+param.get_targetNoCustomers());
			System.out.println("Inside barber_Work:Number of hair cut done="+param.get_noHairCutDone());
			System.out.println("Barber work is over for this barber thread ");
			System.out.flush();
		}
	
}
