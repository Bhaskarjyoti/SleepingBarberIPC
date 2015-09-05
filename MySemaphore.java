//MySemaphore.java 
//Author : Bhaskarjyoti Das
//Dated : 29/09/2014
//M Tech CSE Semester 1 Assignment 


package SleepingBarberIPC;

// Alternatively we could have used java Semaphore class

class MySemaphore 
{
	private int count;
	
	
	public MySemaphore(int startingCount)
	
	{
		count=startingCount;
		
		System.out.println(" Semaphore : getting instantiated with="+count);
		
	}
	
	public void down()
	{
		
		//Synchronize at the  block level in instance method
		//Barbers will come here for Customer sempahore and acquire the customers. If no customers,
		//Customers will come here for acquiring Barbers
		
		synchronized(this) 
		{
			while(count<=0) //go to sleep if so 
			{
				try
				{
					wait();
				}
				catch(InterruptedException ex)
				{}
			}
			count--;
		}
	}
	
	
	public void up()
	{
		//Synchronize at the  block level in instance method
		//Customer will come  here and notify the waiting barbers;
		//Barbers will come here and notify the waiting/sleeping customers
		synchronized(this)
		{
			count++;
		    notifyAll();
	
		}
	}
}
