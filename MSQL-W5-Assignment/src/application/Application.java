package application;

public class Application {
	
	public static void main(String[] args)		{
		/*
		 * This is a simple Potluck List for an annual summer picnic.
		 * It consists of a MYSQL database and Java programming that utilizes the JDBC connector 
		 * to Create, Read, Update and Delete entries.
		 */
		Menu menu = new Menu();
		menu.start();
		System.out.println("Goodbye!  Thank you for using the Potluck List Application!");

		
	}

}