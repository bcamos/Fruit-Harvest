import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.text.DecimalFormat;
public class MyWorld extends World
{
    double userInstructions;
    int level = 0;
    public static int currentLevel;
    boolean levelSelected = false;
    boolean worldConstructed = false;
    boolean userPlaying = false;
    int treeCount = 0;
    int groundCount = 0;
    public static int lives;
    int applesCollected1 = 0;
    int applesCollected2 = 0;
    int appleCollected;
    int applesCollectedForLevel = 0;
    public static int points;
    String[] difficulty = {"Blank", "Easy 1", "Easy 2", "Moderate 1", "Moderate 2", "Hard 1", "Hard 2", "Master 1", "Master 2", "Impossible"};
    int redAppleDeaths = 0;
    int greenAppleDeaths = 0;
    public static boolean death = false;
    int strawberryCount = 0;
    int treesAxed = 0;
    boolean treeAxed = false;
    boolean newPoints = false;
    int actNumber;
    int seconds = 0;
    int minutes = 0;
    int lastSpawnSeconds = 0;
    int lastSpawnMinutes = 0;
    public static boolean increaseProbability = false;
    public MyWorld()
    {    
        super(800, 600, 1); 
        setPaintOrder(Person.class, Strawberry.class, GreenApple.class, RedApple.class, Ground.class, Tree.class);
    }
   
    public static int getLevel()
    {
        //utility method to be called in other class to get the current level property
        return currentLevel;
    }
    
    public static boolean checkIncreasedProbability()
    {
        return increaseProbability;
    }
    
    public void startUp()
    {
        showText("WELCOME TO FRUIT HARVEST!",
                 getWidth() / 2, getHeight() / 2);
        Greenfoot.delay(200);
        showText("Are You Ready to Play?",
                 getWidth() / 2, getHeight() / 2);
        Greenfoot.delay(140);
        showText("", getWidth() / 2, getHeight() / 2);
        askUserToPlayOrNot();
        showText("Choose a Difficulty:", getWidth() / 2, getHeight() / 2);
        Greenfoot.delay(140);
        showText("", getWidth() / 2, getHeight() / 2);
    }
    
    public void askUserToPlayOrNot()
    {
        userInstructions = Double.parseDouble(Greenfoot.ask("Type 1 to play or 0 for instructions"));
        if(userInstructions != 1 && userInstructions != 0)
        {
            showText("Please Only Type 1 or 0", getWidth() / 2, getHeight() / 2);
            Greenfoot.delay(120);
            showText("", getWidth() / 2, getHeight() / 2);
            askUserToPlayOrNot();            
        }
        showText("", getWidth() / 2, getHeight() / 2);
        userInstructions();
    }
    
    public void userInstructions()
    {
        if(userInstructions == 0)
        {
            showText("INSTRUCTIONS:", getWidth() / 2, getHeight() / 2);
            Greenfoot.delay(160);
            showText("Use the arrow keys to move around", getWidth() / 2, getHeight() / 2);
            Greenfoot.delay(250);
            showText("If an apple falls to the ground you will lose a life", getWidth() / 2, getHeight() / 2);
            Greenfoot.delay(250);
            showText("Press the (a) key to collect apples", getWidth() / 2, getHeight() / 2);
            Greenfoot.delay(250);
            showText("a collected strawberry will add a life", getWidth() / 2, getHeight() / 2);
            Greenfoot.delay(250);
            showText("Press the (s) key to collect strawberries", getWidth() / 2, getHeight() / 2);
            Greenfoot.delay(250);
            showText("To cut down a tree," +
            "\n\nPress the (x) key moving forward and backward to remove it", getWidth() / 2, getHeight() / 2);
            Greenfoot.delay(250);
            showText("Ready to Play?", getWidth() / 2, getHeight() / 2);
            askUserToPlayOrNot();
        }
    }
    
    public void selectLevel()
    {
        //Forces User to choose difficulty between 1 and 9
        double tL = Double.parseDouble(Greenfoot.ask("Type a Number of Difficulty: (1 - 2 Easy) (3 - 4 Moderate) (5 - 6 Hard) (7 - 8 Master) (9 Impossible)"));
        if (tL >= 1 && tL <= 9)
        {
            level = (int) tL;
            levelSelected = true;
        }
        else
        {
            showText("Please select an integer between 1 and 9", 
                (getWidth() / 2), (getHeight() / 2));
            Greenfoot.delay(120);
            showText("", getWidth() / 2, getHeight() / 2);
            selectLevel();
        }
    }
    
    public void treeConstructor()
    {
        //constructs tree instances
        Tree tree1 = new Tree(level);
        addObject(tree1, Greenfoot.getRandomNumber(getWidth()), 
        Greenfoot.getRandomNumber(getHeight() / 3));
    }

    private void addPerson()
    {
        //constructs people
         Person person1 = new Person();
         addObject (person1, 400, 550); 
    }
    
    public void addGround()
    {
        //constucts ground
        Ground ground1 = new Ground();
        addObject (ground1, Greenfoot.getRandomNumber(getWidth()), 
        Greenfoot.getRandomNumber(getHeight() / 2) + (getHeight() / 2));
        groundCount++;
    }
    
    public void showDifficultyLevel()
    {
        //method to show difficulty level
        if (worldConstructed == false)
        {
            showText("" + difficulty[level], getWidth() - 100, 20);
            level = 1;
            currentLevel = 1;
        }
    } 
    
    private void beginningSetup()
    {
        if(worldConstructed == false)
        {
            lives = 3;
            startUp();
        }       
        
        if(levelSelected == false)
        {
            selectLevel();
        }
        while((level * 5) > treeCount && (worldConstructed == false))
          {
            treeConstructor();
            treeCount++;
         }
        if(worldConstructed == false)
        {
            addPerson();
        }
        while((50 - (level * 5)) > groundCount && (worldConstructed == false))
        {
            addGround();
        }
        showDifficultyLevel();
        worldConstructed = true; 
        userPlaying = true;
    }
   
    public void act()
    {
        if(worldConstructed == false)
        {
            beginningSetup();
        }        
        
       if (userPlaying == true)
       {
         applesCollected1 = Person.getNumberOfApples();
         appleCollected = applesCollected1;
         countApplesForLevel();
         checkForLevelUp();
         points = Person.getPoints();       
         checkForTreePoints();
         addTreePoints();
       
         countTime();
         showStats();         
         currentLevel = level;
         loss();
         checkDeath();
         countDeath();         
         checkForAddLife();
         increaseAppleSpawn();
         actNumber++;
       }
    }

    

    public void countApplesForLevel()
    {
        //updates apple count
        if(appleCollected > 0 && (applesCollected1 != applesCollected2))
        {
            applesCollectedForLevel++;
            appleCollected = 0;
            applesCollected2 = applesCollected1;
        }
        
    }
   
    public void checkForLevelUp()
    {
        //code to level up when apple count hits 26
        if(applesCollectedForLevel > 25)
        {
            level++;
            applesCollectedForLevel = 1;
        }
    }

    
    public void showStats()
    {
        //code to output apple count, level number, and points
        DecimalFormat time = new DecimalFormat("00");
        showText ("Apples this Level: " + applesCollectedForLevel + 
                 "\nTotal Apples: " + applesCollected1 +
                 "\nLevel: " + level +
                 "\nPoints: " + points,
                 100, (getHeight() - 75));
        showText("Lives: " + lives, getWidth() - 75, getHeight() - 25);
        showText("Time: " + minutes + ":" + time.format(seconds), 100, 40);        
    }
    
    public void loss()
    {
        //code for when the user loses
        if (lives <= 0) 
        {
            DecimalFormat time = new DecimalFormat("00");
            removeObjects(getObjects(Tree.class));
            removeObjects(getObjects(RedApple.class));
            removeObjects(getObjects(GreenApple.class));
            removeObjects(getObjects(Person.class));
            showText("GAME OVER" + 
            "\n\nFinal Time: " + minutes + ":" + time.format(seconds) +
            "\n\nYou made it to level " + level +
            "\n\nYou Scored: " + points, 
            getWidth() / 2, getHeight() /2);
            userPlaying = false;
            Greenfoot.delay(1000);
            restart();
        }
    }

    private void restart()
    {
        int userResponse = Integer.parseInt(Greenfoot.ask("Type 1 to restart"));
        if (userResponse == 1)
        {
            reset();
            beginningSetup();
        }
        else
        {
            restart();
        }
    }
    
    private void reset()
    {
        worldConstructed = false;
        levelSelected = false;
        Person.reset();
        GreenApple.reset();
        RedApple.reset();
        removeObjects(getObjects(Ground.class));
        removeObjects(getObjects(Leaf.class));
        treeCount = 0;
        groundCount = 0;
        applesCollected1 = 0;
        applesCollected2 = 0;
        level = 0;
        redAppleDeaths = 0;
        greenAppleDeaths = 0;
        applesCollectedForLevel = 0;
        strawberryCount = 0;
        treesAxed = 0;
        actNumber = 0;
        seconds = 0;
        minutes = 0;
        lastSpawnSeconds = 0;
        lastSpawnMinutes = 0;
        showText ("", 100, (getHeight() - 75));
        showText("", getWidth() - 75, getHeight() - 25);
        showText("", 100, 40);
        showText("", getWidth() - 100, 20);
    }
    
    public void checkDeath()
    {
        //code to determine whether an apple has hit the bottom of screen or not
        if((GreenApple.getDeaths() != greenAppleDeaths) || (RedApple.getDeaths() != redAppleDeaths))
        {
            death = true;            
            greenAppleDeaths = GreenApple.getDeaths();
            redAppleDeaths = RedApple.getDeaths();
        }
    
    }
    
    public void countDeath()
    {
        //code to update lives count when an apple hits the bottom of the screen
        if (death == true)
        {
            lives = lives - 1;            
            death = false;
        }
    }

    public void checkForAddLife()
    {
        //code to check and add a life whenever a person has eaten a strawberry
        if(Person.getStrawberryCount() != strawberryCount)
        {
            lives++;            
            strawberryCount = Person.getStrawberryCount();
        }        
    }

    public void checkForTreePoints()
    {
        treeAxed = Tree.isTreeAxed();
        if(treeAxed == true)
        {
            treesAxed++;
            treeAxed = false;
            newPoints = true;
        }      
        
    }

    public void addTreePoints()
    {
        if(newPoints == true)
        {
            points = points + 100;
            newPoints = false;
        }
    }

    public void countTime()
    {
        if(actNumber == 60)
        {
            seconds++;
            actNumber = 0;
        }
        if(seconds == 60)
        {
            minutes++;
            seconds = 0;
        }
    }

    public void increaseAppleSpawn()
    {
        boolean redAppleSpawn = Tree.checkRedAppleSpawn();
        boolean greenAppleSpawn = Tree.checkGreenAppleSpawn();
        if(redAppleSpawn == true || greenAppleSpawn == true)
        {
            lastSpawnSeconds = seconds;
            lastSpawnMinutes = minutes;
        }
        int secondsDifferance;
        boolean noMinuteDifferance;
        if((minutes - lastSpawnMinutes) == 0)
        {
            noMinuteDifferance = true;
        }
        else
        {
            noMinuteDifferance = false;
        }
        
        if(noMinuteDifferance == true)
        {
            secondsDifferance = seconds - lastSpawnSeconds;
        }
        else
        {
            secondsDifferance = (60 - lastSpawnSeconds) + seconds;
        }
    
        if(secondsDifferance >= 6)
        {
            increaseProbability = true;
        }
        else
        {
            increaseProbability = false;
        }
    }
}
