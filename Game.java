

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "The Castle" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Ernesto Cuellar
 * @version 2019.04.08
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
   }

   public static void main(String [] args){
    Game mygame=new Game ();
    mygame.play ();
    
    
}

    /**
     *
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r;
      
        // create the rooms
        a = new Room("In Foyer");
        b = new Room("In Dinning Room");
        c = new Room("In Kitchen");
        d = new Room("In Sun Room");
        e = new Room("Outside (Patio)");
        f = new Room("Outside (Patio2)");
        g = new Room("In Potion Room ");
        h = new Room("In Bedroom");
        i = new Room("In Bathroom");
        j = new Room("In Top Deck");
        k = new Room("In Tower");
        l = new Room("In Top Tower");
        m = new Room("In Courtyard");
        n = new Room("In Garage");
        o = new Room("In Garage2");
        p = new Room("In Basement");
        q = new Room("Trapped");
        r = new Room("Reset");
        
       
        // initialise room exits
        a.setExit("north", k);
        a.setExit("south", h);
        a.setExit("east", b);
        a.setExit("west", m);
        
        b.setExit("north", e);
        b.setExit("south", d);
        b.setExit("east", c);
        b.setExit("west", a);
        
        c.setExit("north", f);
        c.setExit("east", g);
        c.setExit("west", b);
        
        d.setExit("north", b);
        
        e.setExit("south", b);
        e.setExit("east", f);
        
        f.setExit("south", c);
        f.setExit("west", e);
     
        g.setExit("west", c);
        
        h.setExit("south", i);
        h.setExit("north", a);
        
        i.setExit("north", h);
        i.setExit("south", j);
        
        j.setExit("north", i);
        
        k.setExit("north", l);
        k.setExit("south", a);
        
        l.setExit("south", k);
        
        m.setExit("east", a);
        m.setExit("west", n);
        
        n.setExit("east", m);
        n.setExit("south", o);
        
        o.setExit("north", n);
        o.setExit("east", p);
        
        p.setExit("west", o);
        p.setExit("south", q);
        
        q.setExit("find out", r);
       
        currentRoom = a;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Castle");
        System.out.println("The Castle is filled with a world of adventure!");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("uhh..I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Find Another Way!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
