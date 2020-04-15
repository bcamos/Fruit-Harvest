import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Person extends Actor
{   
    int redApplesCollected;
    int greenApplesCollected;
    public static int applesCollected;
    public static int points;
    int speed;
    int level;
    public static int strawberryCount = 0;
   public static int getNumberOfApples()
   {
       //utility method to be called elsewhere to get apple count
       return applesCollected;
   }
    
    public static int getPoints()
   {
       //utility method to be called elsewhere to get points
       return points;
    }
   
    public static int getStrawberryCount()
   {
       //utility method to be called elsewhere to get strawberry count
       return strawberryCount;
   }
   
    public static void reset()
    {
       applesCollected = 0;
       points = 0;
       strawberryCount = 0;
    }
    
    public void act() 
    {
        level = MyWorld.getLevel();
        setSpeed();
        keyBoardControls();
        collectApples();
        collectStrawberry();
        applesCollected = addApples();
        countPoints();
    }    

    public void keyBoardControls()
    {
        //keyboard controls, restricted to bottom half of screen
        if(Greenfoot.isKeyDown("right"))
        {
            move(speed);
        }
        if(Greenfoot.isKeyDown("left"))
        {
            move(-speed);
        }
        if(Greenfoot.isKeyDown("up") && ((getWorld().getHeight() / 2) < (getY())))
        {
            setLocation(getX(), (getY() - speed));
        }
        if(Greenfoot.isKeyDown("down"))
        {
            setLocation(getX(), (getY() + speed));
        }
    }

    public void collectApples()
    {
        //collects apples when key a is down
        if (Greenfoot.isKeyDown("a"))
        {
            if (isTouching(RedApple.class))
            {
                removeTouching(RedApple.class);
                redApplesCollected++;
            }
            if (isTouching(GreenApple.class))
             {   removeTouching(GreenApple.class);
                greenApplesCollected++;   
             }      
        
        }
    }

   public void setSpeed()
   {
       //sets speed movement based on level
       if (level < 4)
        {
            speed = 4;
        }
        if (level > 3 && (level < 8))
        {
            speed = level;
        }
        else if (level >= 8)
        {
            speed = 8;
        }
    }
    
    public int addApples()
   {
       //counts total number of apples collected
       return greenApplesCollected + redApplesCollected;
    }  
   
   public void countPoints()
   {
       //counts total points
       points = (greenApplesCollected * 5) + (redApplesCollected * 1);
   }

   public void collectStrawberry()
   {
       //collects strawberrys when key s is down
       if(Greenfoot.isKeyDown("s"))
       {
          if(isTouching(Strawberry.class))
          {
           removeTouching(Strawberry.class);
           strawberryCount++;
          }
       }
   }
}
