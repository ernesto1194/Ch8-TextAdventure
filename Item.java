
/**
 * Write a description of class Item here.
 *
 * @author (Ernesto Cuellar)
 * @version (4-22-19)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String name;
    private String description; 
    private int weight;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description, int weight)
    {
        // initialise instance variables
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    public Item(String name)
    {
        this(name, name, 0);
    }
    
    public String  getName()
    {
        return name;
    }   

    public String  getDescription()
    {
        return description;
    }   
    
     public int  getWeight()
    {
        return weight;
    }  
  


}  