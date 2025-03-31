import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CharacterTest {

	@Test
	void testPerformActionDamage()
	{
		Character char1 = new Character(15, 0, 0, 0);
		Character char2 = new Character(15, 0, 0, 0);
		
		Action action = new Action("damage", 5, 0, 0, 0, 0);
		
		char1.performAction(action, char2);
		
		assertEquals("10|0|0|0", char2.getCurrentCode());
	}

	@Test
	void testPerformActionBlock()
	{
		Character char1 = new Character(15, 0, 0, 0);
		Character char2 = new Character(15, 0, 0, 0);
		
		Action actionD = new Action("damage", 5, 0, 0, 0, 0);
		Action actionB = new Action("block", 0, 2, 0, 0, 0);
		
		char2.performAction(actionB, char1);
		char1.performAction(actionD, char2);
		
		assertEquals("12|0|2|0", char2.getCurrentCode());
	}
	
	@Test
	void testPerformActionDamageCharge()
	{
		Character char1 = new Character(15, 0, 0, 0);
		Character char2 = new Character(15, 0, 0, 0);
		
		Action actionD = new Action("damage", 5, 0, 0, 0, 0);
		Action actionC = new Action("charge", 0, 0, 3, 0, 0);
		
		char1.performAction(actionC, char1);
		char1.performAction(actionD, char2);
		
		assertEquals("7|0|0|0", char2.getCurrentCode());
		assertEquals("15|3|0|0", char1.getCurrentCode());
	}

	@Test
	void testPerformActionNoPoints()
	{
		Character char1 = new Character(15, 0, 0, 0);
		Character char2 = new Character(15, 0, 0, 0);
		
		Action action = new Action("damage", 5, 0, 0, 0, 1);
		
		char1.performAction(action, char2);
		
		assertEquals("15|0|0|0", char2.getCurrentCode());
	}
	
	@Test
	void testPerformActionHasPoints()
	{
		Character char1 = new Character(15, 0, 0, 0);
		Character char2 = new Character(15, 0, 0, 0);
		
		Action actionP = new Action("points", 0, 0, 0, 1, 0);
		Action actionD = new Action("damage", 5, 0, 0, 0, 1);
		
		char1.performAction(actionP, char2);
		char1.performAction(actionD, char2);
		
		assertEquals("10|0|0|0", char2.getCurrentCode());
	}
	
	@Test
	void testPerformActionHasManyPoints()
	{
		Character char1 = new Character(15, 0, 0, 0);
		Character char2 = new Character(15, 0, 0, 0);
		
		Action actionP = new Action("points", 0, 0, 0, 10, 0);
		Action actionD = new Action("damage", 5, 0, 0, 0, 1);
		
		char1.performAction(actionP, char2);
		char1.performAction(actionD, char2);
		
		assertEquals("10|0|0|0", char2.getCurrentCode());
	}
	
	@Test
	void testPerformActionBlockAndDamage()
	{
		Character char1 = new Character(15, 0, 0, 0);
		Character char2 = new Character(15, 0, 0, 0);
		
		Action actionD = new Action("damage", 5, 0, 0, 0, 0);
		Action actionB = new Action("block", 0, 2, 0, 0, 0);
		
		char1.performAction(actionD, char2);
		char1.performAction(actionB, char2);
		char2.performAction(actionD, char1);
		
		assertEquals("10|0|0|0", char2.getCurrentCode());
		assertEquals("12|0|2|0", char1.getCurrentCode());
	}
	
	@Test
	void testPerformActionDamagePointsDamage()
	{
		Character char1 = new Character(15, 0, 0, 0);
		Character char2 = new Character(15, 0, 0, 0);
		
		Action actionD = new Action("damage", 5, 0, 0, 0, 1);
		Action actionP = new Action("points", 0, 0, 0, 1, 0);
		
		char1.performAction(actionD, char2);
		char1.performAction(actionP, char2);
		char1.performAction(actionD, char2);
		
		assertEquals("10|0|0|0", char2.getCurrentCode());
	}
	
	@Test
	void testPerformActionPassivePoints()
	{
		Character char1 = new Character(15, 0, 0, 1);
		Character char2 = new Character(15, 0, 0, 0);
		
		Action actionD = new Action("damage", 5, 0, 0, 0, 1);
		Action actionN = new Action("nothing", 0, 0, 0, 0, 0);
		
		char1.performAction(actionN, char2);
		char1.performAction(actionD, char2);
		
		assertEquals("10|0|0|0", char2.getCurrentCode());
	}
	
	@Test
	void testPerformActionPassivePointsFew()
	{
		Character char1 = new Character(15, 0, 0, 1);
		Character char2 = new Character(15, 0, 0, 0);
		
		Action actionD = new Action("damage", 5, 0, 0, 0, 5);
		Action actionN = new Action("nothing", 0, 0, 0, 0, 0);
		
		char1.performAction(actionN, char2);
		char1.performAction(actionD, char2);
		
		assertEquals("15|0|0|0", char2.getCurrentCode());
	}
}
