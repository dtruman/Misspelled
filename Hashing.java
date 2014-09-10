
public class Hashing
{
	private static int hash(String str)
	{
		int value=0;
		
		for(int i=0; i<str.length(); i++)
		{
			value+=str.charAt(i)*i;
		}
		
		return value;
	}
	
	public static void main(String[] args)
	{
		Soundex s=new Soundex("f273");
		System.out.println(s.hashCode());
		
		Soundex s2=new Soundex("f237");
		System.out.println(s2.hashCode());
	}
}