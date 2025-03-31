public class Action
{
    // Damage|Block|AttackCharge|BlockCharge|PointCharge|PointCost|Potency
    public static final String ACTION_REGEX = "[a-b]+\\|\\d+\\|\\d+\\|\\d+\\|\\d+\\|\\d+";

    private String actionCode;

    // CONSTRUCTORS
    public Action(String name, int dmg, int block, int dmgChrg, int pointsChrg, int cost)
    {
        actionCode = String.format("%s|%d|%d|%d|%d|%d",
                name, dmg, block, dmgChrg, pointsChrg, cost);
    }

    public Action(String actionCode)
    {
        this.actionCode = actionCode;

        if (!actionCode.matches(ACTION_REGEX))
            throw new IllegalArgumentException();
    }

    public Action()
    {
        actionCode = "";
    }

    // GETTERS
    public String getActionCode()
    {
        return actionCode;
    }
    public String getName() {return Main.decode(actionCode, 0);}
    public int getDamage() {return Main.decodeI(actionCode, 1);}
    public int getBlock() {return Main.decodeI(actionCode, 2);}
    public int getDamageCharge() {return Main.decodeI(actionCode, 3);}
    public int getPointsCharge() {return Main.decodeI(actionCode, 4);}
    public int getCost() {return Main.decodeI(actionCode, 5);}

    /// BRAIN METHODS
    public String toString()
    {
        return getName() +
                (getDamage()!=0 ? "\ndmg: " + getDamage() : "" ) +
                (getBlock()!=0 ? "\nblock: " + getBlock() : "" ) +
                (getDamageCharge()!=0 ? "\ndmgChrg: " + getDamageCharge() : "" ) +
                (getPointsCharge()!=0 ? "\npointChrg: " + getPointsCharge() : "" ) +
                (getCost()!=0 ? "\ncost: " + getCost() : "" );
    }
}
