import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	void testDecodeNormal()
	{
		String code = "wrong|wrong|wrong|right|wrong";
		
		assertEquals("right", Main.decode(code, 3));
	}
	
	@Test
	void testDecodeOutOfBounds()
	{
		String code = "wrong|wrong|wrong|wrong|wrong";
		
		assertEquals(null, Main.decode(code, 5));
	}
	
	@Test
	void testDecodeEmptyCode()
	{
		String code = "";
		
		assertEquals(null, Main.decode(code, 3));
	}
	
	@Test
	void testDecodeOnly()
	{
		String code = "right";
		
		assertEquals("right", Main.decode(code, 0));
	}
	
	@Test
	void testDecodeNotString()
	{
		String code = "str|str|str|123|str";
		
		assertNotEquals("str", Main.decode(code, 3));
	}

	@Test
	void testEditCodeNormal()
	{
		String code = "wrong|wrong|wrong";
		String regEx = "[^|]*\\|right\\|[^|]*";
		
		assertTrue(Main.editCode(code, 1, "right").matches(regEx));
	}

	@Test
	void testEditCodeOutOfBounds()
	{
		String code = "i|i|i";
		String regEx = "[^o]*\\|[^o]*\\|[^o]*";
				
		assertTrue(Main.editCode(code, 5, "o").matches(regEx));
	}
	
	@Test
	void testEditCodeEmpty()
	{
		String code = "";
		String regEx = "right";
		
		assertTrue(Main.editCode(code, 0, "right").matches(regEx));
	}
	
	@Test
	void testEditCodeFirst()
	{
		String code = "wrong|wrong|wrong";
		String regEx = "right\\|[^|]*\\|[^|]*";
		
		assertTrue(Main.editCode(code, 0, "right").matches(regEx));
	}
	
	@Test
	void testEditCodeLast()
	{
		String code = "wrong|wrong|wrong";
		String regEx = "[^|]*\\|[^|]*\\|right";
		
		assertTrue(Main.editCode(code, 2, "right").matches(regEx));
	}
}
