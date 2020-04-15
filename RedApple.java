import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class RedApple extends Actor
{
    public static int deaths = 0;
    boolean dead = false;
    int roleCount = 0;
    public static int getDeaths()
    {
        //utility method to be called elsewhere to return death count
        return deaths;
    }
    
    public static void reset()
    {
        deaths = 0;
    }
    
    public void act() 
    {
       if(dead == false)
       {
         falling();
         checkForDeath();
       }
       else
       {
           if(roleCount <= 240)
           {
               if(roleCount < 61)
               {
                   turn(1);
               }
               else if(roleCount < 181)
               {
                   turn(-1);
               }
               else
               {
                   turn(1);
               }
           }
           else
           {
               getWorld().removeObject(this);
           }
       }
    }
    
    public static void addDeath()
    {
        deaths++;
    }

    public void falling()
    {
        //code for an apple falling
        setLocation(getX(), (getY() + 1));
    }

     public void checkForDeath()
    {
        //code that updates death count, and then removes itself
        if(getY() >= (getWorld().getHeight() - 1))
        {
            addDeath();
            dead = true;
        }
    }

    
}
