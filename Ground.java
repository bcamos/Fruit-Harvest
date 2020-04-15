import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Ground extends Actor
{
    int appleCount;
    boolean counted = false;
    public void act() 
    {
       countApples();
       addStrawberry();
    }    

    public void countApples ()
    {
        //code to update apple count when an apple has gone over ground
        if(((isTouching(RedApple.class)) || (isTouching(GreenApple.class))) && counted == false)
        {
            appleCount++;
            counted = true;
        }
        if ((isTouching(RedApple.class) == false) && (isTouching(GreenApple.class) == false))
        {
            counted = false;
        }
    }

    public void addStrawberry()
    {
        //code to add a strawberry when apple count hits 25
        if(appleCount == 25)
        {
            Strawberry strawberry1 = new Strawberry();
            getWorld().addObject(strawberry1, getX(), getY());
            appleCount = 0;
        }
    }
}
