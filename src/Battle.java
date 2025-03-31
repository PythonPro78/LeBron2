import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Battle
{	
	private Character player;
	private Character enemy;
	
	private Scanner scan;
	
	public Battle(Character player, Character enemy)
	{
		this.player = player;
		this.enemy = enemy;
		
		scan = new Scanner(System.in);
		
		player.resetStats();
		enemy.resetStats();
		
		Boolean playerWon = battle();
		
		scan.close();
		
		System.out.println( (playerWon ? "King LeBron":"Bo Jackson") + " won the battle!");
	}
	
	public boolean battle()
	{
		boolean playerTurn = true;
		
		System.out.println("Begining Battle!\n");
		
		while (player.getCurrentHealth() > 0 && enemy.getCurrentHealth() > 0)
		{	
			if (playerTurn)
			{
				System.out.println("LeBron:\n" + player + "\n");
				
				System.out.println("BoJackson:\n" + enemy + "\n");
				
				wait_();
				
				ArrayList<Action> actionList = player.getActionList();
				
				System.out.println("Your actions: ");
				for (Action a : actionList)
				{
					System.out.println(a + "\n");
				}
				
				Action action;
				
				do
				{
					action = getUserAction();
				} while (action != null && !player.performAction(action, enemy));
			}
			else
			{
				Action action = getAIAction();
				
				enemy.performAction(action, player);
				
				System.out.println("Bo used: " + action.getName() + "\n");
			}
			
			playerTurn = !playerTurn;
			
			wait_();
		}
		
		return enemy.getCurrentHealth() <= 0;
	}
	
	public Action getUserAction()
	{
		System.out.print("What action would you like to perform: ");
		String input = scan.nextLine();
		System.out.println();
		
		return player.getAction(input);
	}
	
	public Action getAIAction()
	{
		ArrayList<Action> actionList = enemy.getActionList();
		
		return actionList.get( (int)(Math.random() * actionList.size()) );
	}
	
	public void wait_()
	{
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {e.printStackTrace();}
		
		System.out.println("------------------------------------------------------------\n");
	}
}
