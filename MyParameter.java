//MyParameter.java 
//Author : Bhaskarjyoti Das
//Dated : 29/09/2014
// M Tech CSE Semester 1 Assignment 


package SleepingBarberIPC;

public class MyParameter {
	
	public int noChairs; 
	public int noHairCutDone; 
	public int noCustomerVisit ;// not yet used 
	public int targetNoCustomers; // target no of customers ..we can set this parameter after getting it from cmdline
	// protect access to shared data for block level lock
	//private Object lock; 
	
	
	//constructors
	
	public MyParameter(int a)
	{
		noChairs=a;
		noHairCutDone=0;//anyway init at 0
		noCustomerVisit=0; //anyway init at 0
		
		// protect access to shared data
		//following are set with the constructor 
		 
	}

	public synchronized void set_targetNoCustomers(int i)
	{
		targetNoCustomers=i;
		//set the target no of customers
	}
	
	
	public synchronized int get_targetNoCustomers()
	{
		return targetNoCustomers;
		//return the target no of customers
	}

	
	
	public synchronized int get_noChairs()
	{
		return noChairs;
		//return the current no of chairs (empty)
	}
	
	
	public synchronized int get_noHairCutDone()
	{
		return noHairCutDone;
		//return the total no of hair cut done so far 
		
	}
	
	public synchronized void increment()  /*method level synch */
	// Typically barbers will come here as they want to release the chair 
	//producer produces (essentially completes haircut and 
	//releases a chair  and informs all waiting consumers (customers)

	{
	        		{
        			
                noChairs++; 
                noHairCutDone++;
                System.out.println("Chair remaining = "+noChairs);
                System.out.println("No of hair cut done = "+noHairCutDone);
                notifyAll();
					}
	        		
		}
	
	public synchronized void decrement() throws InterruptedException {
     
	   	// Typically the customers will come to this method as they want to use the chair 
		// if there is no stock to consume, better release the lock for producer
    	//Otherwise there will be producer/consumer deadlock
    	

        {
           	if(noChairs==0) 
        	{
        		noCustomerVisit++; //all (visited) customers may not get hair cut
        		System.out.println("No of customers visited saloon = "+noCustomerVisit);
        		wait(); //producer (barber) has to produce chairs
        	}
        	else
        	{
        		noChairs--;
        		noCustomerVisit++; 
        	}
        }
	}
		

}
