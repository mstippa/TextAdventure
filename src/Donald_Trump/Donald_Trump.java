// Brittany Ross, Morgan Stippa, Bryan Chester
// 4/24/16
// Assignment 4
// The Adventures of Donald Trump - a text-based adventure game
// Player moves down a hallway, exploring different rooms as he or she goes along
/**
 * In the Adventures of Donald Trump you play as Donald Trump and you explore a dungeon,
 * picking up gold and items, and trying to be as awesome as possible
 * 
 * @author Morgan Stippa
 * @author Brittany Ross
 * @author Bryan Chester
 * @version 5 
 * @throws ioexecption if the inputed file cannot be red
 */

package Donald_Trump;
import java.util.*;
import java.io.*;
public class Donald_Trump 
{
	
	private static String input; // string that gets assigned what the user types
	private static boolean isPlaying = true;
	private static int indexCurrentLoc; // string that gets assigned the index of current Location in an array 
	private static int price; // holds the randomly generated number which represents price of items
	private static int purse = 0; // holds the gold the player picks up during the game, starts at 0
	private static int size; // uninitialized integer 
	
	// the location objects, instances of the Location constructor
	private static Location hairSalon = new Location("the hair salon. ", " White is starting to show in your hair"
											+ ", you need more orange dye so you can keep looking fabulous.", 0, new ArrayList<String>(Arrays.asList("Map")), 5);
	private static Location ovalOffice = new Location("the oval office. ", " Your first order of business, build the best "
												+ "damn wall.", 1, new ArrayList<String>(Arrays.asList("Greatsword")), 10);
	private static Location lab = new Location("the labratory. ", " Some weird experiments have gone on in here.", 2, new ArrayList<String>(Arrays.asList("Flaming Scythe")), 1);
	private static Location trumpTower = new Location("trump tower. ", " So tall, so big, the grandeur is overwhelming.", 3, new ArrayList<String>(Arrays.asList("Ring of Awesome")), 2);
	private static Location office = new Location("your office", " The walls are covered with pictures of your beautiful face.", 4, new ArrayList<String>(Arrays.asList("Frail Wooden Shield")), 3);
	private static Location cave = new Location("a cave.", " Stalagmites and stalactites are everywhere, be careful.", 5, new ArrayList<String>(Arrays.asList( "Potion of Strength")), 10);
	private static Location magicShop = new Location("the magic shop.", " There is a ton of things to choose from.", 6, new ArrayList<String>(Arrays.asList("Secret wand that is very secret")), 4);
	private static Location bathroom = new Location("the bathroom.", " Just look into the mirror and you'll have proof that you're the"
											+ "prettiest man to ever live.", 7, new ArrayList<String>(Arrays.asList("Spray Tan")), 5);
	
	private static int currentLocation = lab.id; // sets the currentLocation
	static String[] locationNames = {hairSalon.name, ovalOffice.name, lab.name, trumpTower.name, office.name, cave.name,
									magicShop.name, bathroom.name}; // array  holds the names of the locations
	static int[] locationIds = {hairSalon.id, ovalOffice.id, lab.id, trumpTower.id, office.id, // arrays hold location ids 
							  cave.id, magicShop.id, bathroom.id};
	 
	
	public static void main(String[] args) throws IOException
	{
		//constants that represent the direction the player has moved
		final int N  = 0;
		final int S = 1;
		final int E = 2;
		final int W = 3;
		
		Queue<String> locationQueue = new LinkedList<String>(); // queue that will hold location names player visits
		ArrayStack<String> stack = new ArrayStack<String>(100); // ArrayStack that will hold location name player visits, set with an initial size of 100
		ArrayList<String> itemsArrayList = new ArrayList<String>();// used to hold the items only briefly
		Random rand = new Random();// will be used to generate a random number
		
		// arraylist that holds the locations
		ArrayList Locations = new ArrayList();
		Locations.add(hairSalon);
		Locations.add(ovalOffice);
		Locations.add(lab);
		Locations.add(trumpTower);
		Locations.add(office);
		Locations.add(cave);
		Locations.add(magicShop);
		Locations.add(bathroom);
				
		// arraylist that holds items available in the dungeon
		ArrayList mapItems = new ArrayList();
		mapItems.add("Map");
		mapItems.add("Greatsword");
		mapItems.add("Flaming Scythe");
		mapItems.add("Spray Tan");
		mapItems.add("Ring of Awesome");
		mapItems.add("Frail Wooden Shield");
		mapItems.add("Potion of Strength");
		mapItems.add("Steel Helm");
				
		// the player's inventory
		ArrayList inventory = new ArrayList();
		
		
		Scanner scan = null; // declaring the the parser for magicitems file
		// try catch block that catches if there is a problem reading the text file
		try
		{
		// reads the magic items into the program 
		scan = new Scanner(new File("magicitems.txt"));
		}
		catch (Exception IOException)
		{
			System.out.println("Input exception");
		}
		
		// scans every line in the text file and adds it to the itemsArrayList
		while(scan.hasNext())
		{
			String item = scan.nextLine();
			itemsArrayList.add(item);
			
		}
		
		size = itemsArrayList.size(); // the number of elements in itemsArrayList assigned to size
		String[] items = new String[size]; // fixed length array of string type
		
		// iterates through items array and adds a magic item at every iteration
		for(int i=0;i < itemsArrayList.size(); i++)
		{
			items[i]=itemsArrayList.get(i);
		}
		
		// sorts the elements of items
		Arrays.sort(items);
		
		System.out.println("The Adventures of Donald Trump");
		System.out.println("------------------------------");
		System.out.println("       'Oh what a man'        ");
		System.out.println("");
		System.out.println("  press [enter] to continue");
		
		// checks if user presses enter to start the game
		Scanner scan1 = new Scanner (System.in);
		input = scan1.nextLine();
		if (input.isEmpty())
			System.out.println(lab);
		else
			System.out.println("You did not press enter, you cannot play the game");
		
		(locationQueue).add(((Location) Locations.get(currentLocation)).getName()); // adds the starting location name to the queue
		stack.push(((Location) Locations.get(currentLocation)).getName()); // adds the starting location name to the stack
		
		// enters the loop only if isPlaying is true 
		// will exit loop if user types q
		while (isPlaying)
		{	
			input = scan1.nextLine(); // scans keyboard for user input
			
			// tests to see if the user wants to move
			if (input.toUpperCase().equals("N") || input.toUpperCase().equals("S") ||
			input.toUpperCase().equals("E") || input.toUpperCase().equals("W"))
			{	
				//loops indexes where the currentLocation is located in the arraylist
				for (int i = 0; i < Locations.size(); i++)
				{	
					if (((Location) Locations.get(i)).id == currentLocation)	
					indexCurrentLoc = i;
				}	
				
				// tests to see what direction user wants to move
				// and then calls Move method on the class Location
				if (input.toUpperCase().equals("N"))
					currentLocation = (Location.Move(indexCurrentLoc, N));// assigns the returned value of Move to currentLocation	
				else if (input.toUpperCase().equals("S"))
					currentLocation = (Location.Move(indexCurrentLoc, S));
				else if (input.toUpperCase().equals("E"))
					currentLocation = (Location.Move(indexCurrentLoc, E));
				else if (input.toUpperCase().equals("W"))
					currentLocation = (Location.Move(indexCurrentLoc, W));
				
				// prints the new location
				System.out.println(Locations.get(currentLocation)); 
				
				(locationQueue).add(((Location) Locations.get(currentLocation)).getName()); // adds location name to queue
				stack.push(((Location) Locations.get(currentLocation)).getName()); // adds location name to stack
			}
			
			// sees if the user is at the magicShop and wants to buy anything
			if (currentLocation == magicShop.id)
			{
				System.out.println("Do you want to shop?");
				input = scan1.nextLine();
				if (input.toUpperCase().equals("YES"))
				{	
					String boughtItem = new String(); // uninitialized variable that will hold what the item the user wants to buy
					System.out.println("What do you want to buy? ");
					input = scan1.nextLine();
					
					// enters test if what the player wants to buy is in the shop by doing a binary search
					// and if the user didn't buy the item already
					if (Arrays.binarySearch(items,input) >= 0 && inventory.indexOf(input) < 0)
					{	
						boughtItem = input; // what the user wants to buy
						price = rand.nextInt(20) + 1; // generating a random number between 1 and 20
						System.out.println(boughtItem + " Price: " + price + " gold pieces");
						System.out.println("You can buy it if you have enough or leave. You have " + purse + " gold pieces");
						input = scan1.nextLine();
						
						//enters test if user wants to buy item and has enough gold
						if (input.toUpperCase().equals("B") && purse >= price) 
						{
							System.out.println("Item Bought");
							inventory.add(boughtItem); // adds the boughtItem to the player's inventory
							purse = purse - price; // takes the price of the bought item and subtracts it from the player's purse
						}
						
						// enters test if user wants to buy item put doesn't have enough gold
						else if (input.toUpperCase().equals("B") && purse < price)
							System.out.println("Come back later when you have enough");
					}
					else 
						System.out.println("Sorry we are out of that item");
				}		
			}
				
			// uses the map method if user enters m
			if (input.toUpperCase().equals("M") && hairSalon.items.size() == 0)
				Location.map();
			else if (input.toUpperCase().equals("M") && hairSalon.items.size() > 0)
						System.out.println("You don't have the map");
					
				
			
			// prints the valid commands if user enters h
			if (input.toUpperCase().equals("H"))
				System.out.println("Valid commands: (n)orth, (s)outh, (e)ast, (w)est, (m)ap, (t)ake, (i)nventory,"
								+ " (p)urse, (b)uy, (f)orward, (b)ackward");
			
			// changes isPlaying to false if user enters q and then it will leave the while loop
			if (input.toUpperCase().equals("Q"))
			{
				System.out.println("Would you like to review your walk-through forwards or backwards");
				input = scan1.nextLine();
				
				// enters test if user wants to review walk-through forwards
				if (input.toUpperCase().equals("F"))
				{
					System.out.println(locationQueue);
					isPlaying = false;
				}
				
				// enters test if user wants to review walk-through backwards
				else if (input.toUpperCase().equals("B"))
				{
					// stays in while loop until every element is removed for stack
					while(!stack.isEmpty())
					System.out.println(stack.pop());
					isPlaying = false;
				}
				else
					isPlaying = false;
			}
			
			// adds item to inventory array if user enters t
			if (input.toUpperCase().equals("T"))
			{	
				if (!((Location) Locations.get(currentLocation)).items.isEmpty()) // checks to see if item was taken already
				{	
					System.out.println("Item Taken and gold added");
					inventory.add(((Location) Locations.get(currentLocation)).items); // adds item player's inventory(arrayList)
					((Location) Locations.get(currentLocation)).removeItem(); // removes item from location
					purse = purse + ((Location)Locations.get(currentLocation)).gold; // adds gold to player's purse
					((Location) Locations.get(currentLocation)).removeGold(); // removes gold from location
				}	
				
				// enters test if items from location were taken
				else if (((Location) Locations.get(currentLocation)).items.isEmpty())  
					System.out.println("Nothing to take");
			}	
			
			// displays the gold in the user's purse
			if (input.toUpperCase().equals("P"))
				System.out.println(purse + " gold pieces");
			
			// displays the user's inventory
			if (input.toUpperCase().equals("I"))
				System.out.println(inventory);
		}	
		System.out.println("Till next time");
	}	
}
