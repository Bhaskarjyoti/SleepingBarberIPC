//MySaloon.java 
//Author : Bhaskarjyoti Das
//Dated : 29/09/2014
// M Tech CSE Semester 1 Assignment 


package SleepingBarberIPC;
import java.util.*;

public class MySaloon extends Thread

{
	
	public int hairCutTime; //This is predefined here
	private int noBarbers;
	private int noCustomers;
		
	//not yet used these object arrrays 
	
	private ArrayList<Customer>  theCustomers = new ArrayList<Customer>();
	private ArrayList<Barber>  theBarbers = new ArrayList<Barber>();
	
	public MySemaphore customers;
	public MySemaphore barbers;
	public MyParameter param; 
	
//getter and setter for attributes 		
	
	public void set_customerno(int i)
	{
		noCustomers=i;
	}
	
	public void set_barberno(int i)
	{
		noBarbers=i;
	}

	
	public int  get_barberno()
	{
		return noBarbers;
	}
	
	public int  get_customerno()
	{
		return noCustomers;
	}

	public void set_hairCutTime(int i)
	{
		hairCutTime=i;
	}
	
	public int  get_hairCutTime()
	{
		return hairCutTime;
	}
	//constructors 
	
	public MySaloon(int barber, int customer, int haircuttime )
	{
	
		System.out.println("Saloon constructor : Welcome to the Saloon..good day to all  \n");
		customers = new MySemaphore(0);
		barbers = new MySemaphore(0);
			
		noBarbers=barber; //	
		noCustomers=customer;
		
		hairCutTime=haircuttime;
	}
	
	public void runMySaloon(MyParameter param) throws InterruptedException{
		
		int j; 
		
		
		//Create all the barbers, store the barber objects in the arraylist
				// which is a private attribute of Saloon 
				
					System.out.println("\n\n Now Barbers started arriving..............\n\n\n");	
					
				for (j=1;j<=noBarbers;j++)
							{
					Barber barbernew = new Barber(j,customers, barbers, param);
					theBarbers.add(barbernew);
					
					//for every new object added, we invoke the work method on that object 
					// we pass the parameters that are not defined within the scope of the 
					//barber object as parameters 
					
					Thread tb = new Thread(barbernew);
					System.out.println("New Barber thread started: " + tb); 
					tb.start();
					//barber work is defined in the run function of barber class
					System.out.flush();	
							}
		
		//Create all the customers, store the customer objects in the arraylist
	    // which is a private attribute of Saloon 
		
			System.out.println("\n\n Customers started arriving .............\n\n\n");
				
			for (j=1;j<=noCustomers;j++)
			{
			Customer customernew = new Customer(j,customers, barbers, param); // we get a Runnable object 
			theCustomers.add(customernew);  // we store the object in the Arraylist
			//Customer work is define in the run funcion in customer class
			Thread tc=new Thread(customernew); //we start a separate thread here to continue the execution
			System.out.println("New Customer thread started:" + tc); 
			tc.start();
			}
			
			System.out.flush();	
				
		
		return;
					
	 	}
		
			
					
	
	}

