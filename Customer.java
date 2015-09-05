//Customer.java 
//Author : Bhaskarjyoti Das
//Dated : 29/09/2014
// M Tech CSE Semester 1 Assignment 


package SleepingBarberIPC;

// This class extends thread as we want all the objects of this class to run
// as a thread

	
class Customer implements Runnable

{
	
private int customerno;
private MySemaphore cust, barb;
private MyParameter p;
long threadId;

//constructor
	
	public Customer(int n, MySemaphore customers, MySemaphore barbers, MyParameter param)
	{
		customerno=n;
		cust = customers;
		barb=barbers;
		p=param;
		System.out.println("Inside the customer constructor(), walking into saloon..customerid ="+ customerno);
	}
	


	public int getcustomerno()
	{
		return customerno;
	}
	
	public void setcustomerno(int n)
	{
		customerno=n;
	}
	
	//work function for the customer object 
	//passed the reference to the mutex, semaphore and class variables of 
	//parameter class as those are outside the scope of this 
	
	public void run()
	{
		threadId=Thread.currentThread().getId();
		System.out.println("Hi, new customer inside the runnable object, yet to link with thread ="+threadId);
		
        //logger.debug("Thread # " + threadId + " is doing this task");
		//do the work here
		try {
			customer_work(cust,barb,p);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.flush();
		System.out.println("Customer Child thread finished!"+threadId);
		return;
	
	}
	
	public void customer_work(MySemaphore customers, MySemaphore barbers, MyParameter param) throws InterruptedException
	{
		System.out.println("Hi, inside customer thread and executing the customer_work method\n");
		if(param.noChairs>0) //Chairs are available at least for waiting
		
		{
    		System.out.println("chairs available= " +param.noChairs);
			// decrease no of free chairs
			param.decrement();
			System.out.println("waiting for barber..Chair remaining = "+param.noChairs);
    				
			customers.up(); // notify barbers that a customer is available

			barbers.down(); //wait for barber to be acquired for hair cut 
						
			System.out.println("Customers"+customerno+"  is getting his haircut");
		}
		else  // there are NO free seats ..unlock mutex and leave 
		{
			
			System.out.println("Customers"+customerno+"  is leaving saloon as no free seats");
			System.out.flush();	
		} 
		
	}


}
