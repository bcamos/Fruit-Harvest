import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Tree extends Actor
{
    int level;
    int treeHealth = 100;
    public static boolean treeAxed = false;
    boolean axed = false;
    public static boolean redAppleSpawned = false;
    public static boolean greenAppleSpawned = false;
    boolean increaseProbability = false;
    public Tree(int levelAssigned)
    {
        //constructor for tree
        level = levelAssigned;
    }
    
    public static boolean isTreeAxed()
    {
        return treeAxed;
    }
    
    public static boolean checkRedAppleSpawn()
    {
        return redAppleSpawned;
    }
    
    public static boolean checkGreenAppleSpawn()
    {
        return greenAppleSpawned;
    }
    
    public void act() 
    {
        level = MyWorld.getLevel();
        increaseProbability = MyWorld.checkIncreasedProbability();
        redAppleSpawn();
        greenAppleSpawn();
        leafSpawn();
        axeTree();
    }    

    public void redAppleSpawn()
    {
        //code that spawns falling red apples randomly, probability increased with increasing levels
        int number = Greenfoot.getRandomNumber(30000);
        redAppleSpawned = false;
        int additionalProb = 0;
        if(increaseProbability == true)
        {
            additionalProb = 20;
        }
        if(number < (((2 * level) + 3) + additionalProb))
        {
            RedApple apple1 = new RedApple();
            getWorld().addObject(apple1, getX(), getY());
            redAppleSpawned = true;
        }
    }

    public void greenAppleSpawn()
    {
        //code that spawns falling green apples randomly, probability increased with increasing levels
        int number = Greenfoot.getRandomNumber(30000);
        greenAppleSpawned = false;
        if (number < (level))
        {
            GreenApple greenApple1 = new GreenApple();
            getWorld().addObject(greenApple1, getX(), getY());
            greenAppleSpawned = true;
        }
    }

    public void leafSpawn()
    {
        //code that spawns leaves after level 4 with a probability of 2 and 29999
        int number = Greenfoot.getRandomNumber(30000);
        if(level > 4 && (number < 2))
        {
            Leaf leaf1 = new Leaf();
            getWorld().addObject(leaf1, getX(), getY());
        }
    }

    public void axeTree()
    {
        if(Greenfoot.isKeyDown("x"))
        {
            if(isTouching(Person.class) && axed == false)
            {
                treeHealth = treeHealth - (Greenfoot.getRandomNumber(31) + 20);
                axed = true;
            }
            if(isTouching(Person.class) == false)
            {
                axed = false;
            }
            if(treeHealth <= 0)
            {
                treeAxed = true;
                getWorld().removeObject(this);
            }
            
        
        }
    }
}
