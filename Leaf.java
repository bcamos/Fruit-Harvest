import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Leaf extends Actor
{
    int floatAmount = 0;
    boolean left = false;
    boolean turned = false;
    int turnCount = 0;
    boolean doneFloating = false;
    boolean directionChosen = false;
    boolean moveRight;
    public void act() 
    {
        chooseDirection();
        floatDown();
        if(doneFloating == true)
        {
            addTree();
            getWorld().removeObject(this);
        }
    }    

    public void chooseDirection()
    {
        //code to choose direction
        int number = Greenfoot.getRandomNumber(2);
        if(directionChosen == false)
        {
            if (number == 0)
            {
                moveRight = true;
            }
            else
            {
                moveRight = false;
            }
            directionChosen = true;
        }
    }
    
    public void floatDown()
    {
       //code for how the leaf will float
        if(floatAmount < 60)
        {
            setLocation(getX(), getY() + 1);
            if (left == false && turned == false)
            {
                turn(-Greenfoot.getRandomNumber(75) - 10);
                left = true;
                turned = true;
            }
            else if(left == true && turned == false)
            {
                turn(Greenfoot.getRandomNumber(75) + 10);
                left = false;
                turned = true;
            }
            turnCount++;
            if(turnCount == 17)
            {
                turned = false;
                turnCount = 0;
            }
            floatAmount++;
            if(moveRight == true)
            {
                move(1);
            }
            else
            {
                move(-1);
            }
        }
       else
        {
            doneFloating = true;
        }
    }

    public void addTree()
    {
        //code for constructing tree instance
        Tree tree1 = new Tree(MyWorld.getLevel());
        getWorld().addObject(tree1, getX(), getY());
    }
}
