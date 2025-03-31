public class Main
{
    public static void main(String[] args)
    {
        Character leBron = new Character(20, 0, 0, 1);
        Character boJackson = new Character(100, 7, 0, 1);

        Battle battle = new Battle(leBron, boJackson);
        
//        System.out.println(leBron);
//        System.out.println(boJackson);
//
//        boJackson.performAction(boJackson.getAction("block"), null);
//        leBron.performAction(leBron.getAction("hit"), boJackson);
//
//        System.out.println(leBron);
//        System.out.println(boJackson);
    }

    public static String decode(String code, int index)
    {
        int currentIndex = 0;

        for (int i = 0; i < index; i ++)
        {
            currentIndex = code.indexOf("|", currentIndex) + 1;
            
            if (currentIndex == 0) return null;
        }
        
        int endIndex = code.indexOf("|", currentIndex);
        if (endIndex == -1)
            endIndex = code.length();

        return code.substring(currentIndex, endIndex);
    }

    public static int decodeI(String code, int index)
    {
    	try {
        return Integer.parseInt(decode(code, index));
    	} catch (Exception e) {return -1;}
    }

    public static String editCode(String code, int index, Object value)
    {
        int currentIndex = 0;
        String newCode = "";

        for (int i = 0; i < index; i ++)
        {
            currentIndex = code.indexOf("|", currentIndex) + 1;
            
            if (currentIndex == 0) return code;
        }

        int endIndex = code.indexOf("|", currentIndex);

        newCode += code.substring(0, currentIndex) + value;

        if (endIndex != -1)
            newCode += code.substring(endIndex);

        return newCode;
    }
}
