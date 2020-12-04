package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.PotluckDao;
import entity.Potluck;

public class Menu {
	
/*
 * This is a simple Potluck List for an annual summer picnic.
 * It consists of a MYSQL database and Java programming that utilizes the JDBC connector 
 * to Create, Read, Update and Delete entries.
 */
	private PotluckDao potluckDao = new PotluckDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display Potluck List: ",
			"Add an entry to the Potluck List: ",
			"Delete an Entry from the Potluck List: ",
			"Update a Dish Entry in the Potluck List: ");
	
	public void start()		{
		String selection = "";
		
		do		{
			printMenu();
			selection = scanner.nextLine();
			
			try	{
				if(selection.equals("1"))	 {
					displayList();
				}	else if (selection.equals("2"))		{
						createList();
				}	else if (selection.equals("3"))		{
						deleteDish();
				}	else if (selection.equals("4"))		{
						updateDish();
				}
				
			}	catch (SQLException e)	{
				e.printStackTrace();
			}
	
			
			System.out.println("Press Enter to continue ...");
			scanner.nextLine();
			
			
		}	while(!selection.equals("-1"));
	}
	
	/*
	 * Method below is used while deleting an entire entry of the attendee.
	 * It utilizes the deleteDish method in the PotluckDao class.
	 */
	private void deleteDish() throws SQLException {
		System.out.print("Please enter record ID: ");
		int id = Integer.parseInt(scanner.nextLine());
		
		List<Potluck> potluckInfo = potluckDao.getPotluckById(id);
		String record = "";
		for (Potluck items1 : potluckInfo)	{
			record = ("Deleting entry: \n" + ((Potluck) items1).getId() + ": " + ((Potluck) items1).getFirst_name() + " " + ((Potluck) items1).getLast_name() + "--> " + ((Potluck) items1).getDish_type() + "\n");
		}
		
		potluckDao.deleteEntry(id);
		System.out.println(record);
	}

	/*
	 * Method below is used while updating the dish type of an attendee's entry.
	 * It utilizes the updateDish method in the PotluckDao class.
	 */
	private void updateDish() throws SQLException {

		System.out.print("Please enter record ID: ");
		int id = Integer.parseInt(scanner.nextLine());
		System.out.print("Please enter New Dish Type: ");
		String dish_type = (scanner.nextLine());
	
		potluckDao.updateDish(id, dish_type);
		
		System.out.println("Dish has been Updated");
		
		String record = ("ID: " + id + ". \tDish: " + dish_type);
		System.out.println(record + "\n");	
	}

	/*
	 * Method below is used while adding a new attendee entry.
	 * It utilizes the createPotluck method in the PotluckDao class.
	 */
	private void createList() throws SQLException {
		
		System.out.print("Enter first name: ");
		String fname = (scanner.nextLine());
		System.out.print("Enter last name: ");
		String lname = (scanner.nextLine());
		System.out.print("Enter phone number: ");
		String phoneno = scanner.nextLine();
		System.out.print("Enter dish details: ");
		String dish = scanner.nextLine();
		String record = ("Name: " + fname + " " + lname + "\t, Phone: " + phoneno + ", \tDish: " + dish);
		potluckDao.createPotluck(fname, lname, phoneno, dish);

		System.out.println("Dish Has Been Added!!");
		System.out.println(record);	
	}

	/*
	 * Method below is used while displaying the complete listing of attendees and the dish type.
	 * It utilizes the getPotluck method in the PotluckDao class.
	 */
	private void displayList() throws SQLException {
		List<Potluck> potluckInfo = potluckDao.getPotluck();
		System.out.printf("%4s  %-21s   %-21s   %-7s%n", "ID", "Name", "Phone", "Dish Type");
		for (Potluck items1 : potluckInfo)	{
			System.out.printf("%4d  %-21s   %-21s   %-21s%n", ((Potluck) items1).getId(),
					((Potluck) items1).getFirst_name() + " " + ((Potluck) items1).getLast_name(), 
					((Potluck) items1).getPhone_number(), 
					((Potluck) items1).getDish_type());
		}
		
	}

	/*
	 * 		//Presents the options for the user to select action.
	 */
	private  void printMenu()		{
		System.out.println("*****************************************************");
		System.out.println("*                                                   *");
		System.out.println("* Welcome to the Annual Summer Picnic Potluck Event *");
		System.out.println("*                                                   *");
		System.out.println("*****************************************************\n");
		System.out.println("Select an option:\n-----------------------------");
		for (int i = 0; i < options.size(); i++)	{
			System.out.println(i + 1 +") " + options.get(i));
		}
	}
	

}
