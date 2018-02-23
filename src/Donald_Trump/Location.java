// Brittany Ross, Morgan Stippa, Bryan Chester
// 4/24/16
// Assignment 4
//Location class that represents the locations in the game

/**
 * This is the Location class. It has several methods that can be called upon
 * 
 * @author Morgan Stippa
 * @author Brittany Ross
 * @author Bryan Chester
 * @version 4
 */
package Donald_Trump;
import java.util.*;

public class Location
{
	String name, description;
	int id, gold;
	ArrayList items = new ArrayList();
		
	//sets up this location with the name, description, and number which is used to calculate currentLocation
	public Location( String name, String description, int number, ArrayList<String> items, int gold)
	{
		this.name = name;
		this.description = description;
		this.id = number;
		this.items = items;	
		this.gold = gold;
	}
		
	// returns a number which represents the user's new location
	// takes's the index of the current Location from an array and the direction moved as arguments
	public static int Move(int num, int direction)
	{
		int mapMatrix[][] = {{1,2,45,45}, 
						  	{6,0,45,45}, 
						  	{0,4,3,5},
						  	{45,45,7,2}, 
						  	{2,45,45,45},
						  	{45,45,2,45},
						  	{45,1,45,45},
						  	{1,1,1,3}};
			
		// will return the same location if user types a direction that he can't go
		if (mapMatrix[num][direction] == 45)
			return num;
			
		// returns the new location
		return mapMatrix[num][direction];
	}
		
	// prints the map
	public static void map()
	{
		System.out.println("                ------------");
		System.out.println("                |Magic Shop|");
		System.out.println("                ----   ----");
		System.out.println("                    |  |");
		System.out.println("                    |  |");
		System.out.println("                    |  |");
		System.out.println("                    |  |");
		System.out.println("                    |  |");
		System.out.println("               -----    ----");
		System.out.println("               |Oval Office|");
		System.out.println("               -----    ----");
		System.out.println("                    |  |");
		System.out.println("               -----   ----");
		System.out.println("               |Hair Salon|");
		System.out.println("               -----   ----");
		System.out.println("                    |  |");
		System.out.println("                    |  |");
		System.out.println("------              -  -        -----------      ---------");
		System.out.println("|Cave ::::::::::::: Lab ::::::: Trump Tower :::: Bathroom|");
		System.out.println("------              ----        -----------      ---------");
		System.out.println("                    |  |");
		System.out.println("                    |  |");
		System.out.println("                    |  |");
		System.out.println("                    |  |");
		System.out.println("                    |  |");
		System.out.println("                    |  |");
		System.out.println("                  --    --");
		System.out.println("                  |Office|");
		System.out.println("                  --------");
			
	}
	
	// returns name
	public String getName()
	{
		return name;
	}
	// returns descripition
	public String getDesc()
	{
		return description;
	}
	// returns items
	public ArrayList getItems()
	{
		return items;
	}
	// returns id
	public int getId()
	{
		return id;
	}
	// removes the item(s) from a location
	public void removeItem()
	{
		items = new ArrayList<String>();
	}
	// removes gold from a location
	public void removeGold()
	{
		gold = 0;
	}
	// returns the name and the description of this location as a string 
	public String toString() 
	{
		return ("You are in " + this.name + this.description + " Item(s) Here: " + this.items 
				+ "  Gold Pieces: " + this.gold);
	}
	
	
}
