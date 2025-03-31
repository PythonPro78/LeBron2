import java.util.ArrayList;

public class Character
{
    public static final Action HIT_ACT = new Action("hit", 2, 0, 0, 0,0);
    public static final Action BLOCK_ACT = new Action("block", 0, 3, 0, 0, 0);

    public ArrayList<Action> actionList;

    // Health|DmgAdd|BlockAdd|PointPer
    private String characterCode;
    private String curCharacterCode;

    public int currentPoints = 0;

    // CONSTRUCTOR
    public Character(int health, int dmgAdd, int blockAdd, int pointPer, ArrayList<Action> actionList)
    {
        this.actionList = actionList;

        characterCode = String.format("%d|%d|%d|%d",
                health, dmgAdd, blockAdd, pointPer);
        curCharacterCode = String.format("%d|%d|%d|%d",
                health, dmgAdd, blockAdd, pointPer);
    }

    public Character(int health, int dmgAdd, int blockAdd, int pointPer)
    {
        actionList = new ArrayList<Action>();

        actionList.add(HIT_ACT);
        actionList.add(BLOCK_ACT);

        characterCode = String.format("%d|%d|%d|%d",
                health, dmgAdd, blockAdd, pointPer);
        curCharacterCode = String.format("%d|%d|%d|%d",
                health, dmgAdd, blockAdd, pointPer);
    }

    public Character(ArrayList<Action> actionList)
    {
        actionList = new ArrayList<Action>();

        actionList.add(HIT_ACT);
        actionList.add(BLOCK_ACT);

        characterCode = String.format("%d|%d|%d|%d",
                10, 0, 0, 1);
        curCharacterCode = String.format("%d|%d|%d|%d",
                10, 0, 0, 1);
    }

    public Character()
    {
        actionList = new ArrayList<Action>();

        actionList.add(HIT_ACT);
        actionList.add(BLOCK_ACT);

        characterCode = String.format("%d|%d|%d|%d",
                10, 0, 0, 1);
        curCharacterCode = String.format("%d|%d|%d|%d",
                10, 0, 0, 1);
    }

    // GETTERS/SETTERS
    public String getCode() {return characterCode;}
    public String getCurrentCode() {return curCharacterCode;}
    public int getHealth() {return Main.decodeI(characterCode, 0);}
    public int getCurrentHealth() {return Main.decodeI(curCharacterCode, 0);}
    public int getDamage() {return Main.decodeI(characterCode, 1);}
    public int getCurrentDamage() {return Main.decodeI(curCharacterCode, 1);}
    public int getBlock() {return Main.decodeI(characterCode, 2);}
    public int getCurrentBlock() {return Main.decodeI(curCharacterCode, 2);}
    public int getPointPer() {return Main.decodeI(characterCode, 3);}
    public int getCurrentPointPer() {return Main.decodeI(curCharacterCode, 3);}
    public ArrayList<Action> getActionList() {return actionList;}

    public void setCode(String code) {characterCode = code;}
    public void setCurrentCode(String code) {curCharacterCode = code;}
    public void setHealth(int health) {characterCode = Main.editCode(characterCode, 0, health);}
    public void setCurrentHealth(int health) {curCharacterCode = Main.editCode(curCharacterCode, 0, health);}
    public void setDamage(int damage) {characterCode = Main.editCode(characterCode, 1, damage);}
    public void setCurrentDamage(int damage) {curCharacterCode = Main.editCode(curCharacterCode, 1, damage);}
    public void setBlock(int block) {characterCode = Main.editCode(characterCode, 2, block);}
    public void setCurrentBlock(int block) {curCharacterCode = Main.editCode(curCharacterCode, 2, block);}
    public void setPointPer(int pointPer) {characterCode = Main.editCode(characterCode, 3, pointPer);}
    public void setCurrentPointPer(int pointPer) {curCharacterCode = Main.editCode(curCharacterCode, 3, pointPer);}
    public void setActionList(ArrayList<Action> list) {actionList = list;}
    public void addAction(Action action) {actionList.add(action);}

    // BRAIN METHODS
    public boolean performAction(Action action, Character target)
    {
        if (action.getCost() > currentPoints)
        {
            currentPoints++;
            return false;
        }

        currentPoints -= action.getCost();

        this.setCurrentDamage(this.getCurrentDamage() + action.getDamageCharge());

        int totalDamage = 0;

        if (action.getDamage() != 0)
            totalDamage = this.getCurrentDamage() + action.getDamage();

        if (target != null && target.getCurrentBlock() < totalDamage)
            target.setCurrentHealth(target.getCurrentHealth() - totalDamage + target.getCurrentBlock());

        this.setCurrentBlock(action.getBlock());

        this.setCurrentPointPer(this.getCurrentPointPer() + action.getPointsCharge());

        currentPoints += getCurrentPointPer();
        
        return true;
    }

    public Action getAction(String action)
    {
        int index = checkAction(action);

        if (index == -1) return null;

        return actionList.get(index);
    }

    public int checkAction(String action)
    {
        for (int i = 0; i < actionList.size(); i ++)
            if (action.equals(actionList.get(i).getName()))
                return i;

        return -1;
    }
    
    public void resetStats()
    {
    	curCharacterCode = characterCode;
    }

    public String toString()
    {
        return  "Health: " + getCurrentHealth() +
                "\nDamage: " + getCurrentDamage() +
                "\nBlock: " + getCurrentBlock() +
                "\nPointPer: " + getPointPer() +
                "\nPoints: " + currentPoints;
    }
}
