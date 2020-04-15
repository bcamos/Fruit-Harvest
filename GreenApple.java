import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class GreenApple extends Actor
{
    public static int deaths = 0;
     public static int getDeaths()
    {
        //utility method to pass death count to be counted elsewhere
        return deaths;
    }
    
    public static void reset()
    {
        deaths = 0;
    }
    
    public void act() 
    {
        falling();
        checkForDeath();
    }    

    public void falling()
    {
        //code that animates apple to fall and spin on the screen
        setLocation(getX(), (getY() + 2));
        turn(1);
    }

    public void checkForDeath()
    {
        //code that updates death count, and then removes itself
        if(getY() == (getWorld().getHeight() - 1))
        {
            deaths = deaths + 1;
            getWorld().removeObject(this);
        }
    }
}
