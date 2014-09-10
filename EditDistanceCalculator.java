
public class EditDistanceCalculator {
	public int editDistance(String a, String b) {
		return editDistanceHelper(a, b, new int[a.length()+1][b.length()+1]);
	}
	
	private int editDistanceHelper(String a, String b, int[][] eds) {
		for ( int i = 1; i <= a.length(); i++ ) {
			eds[i][0] = b.length();
		}
		for ( int j = 1; j <= b.length(); j++ ) {
			eds[0][j] = a.length();
		}
		
		return editDistanceCalculatorHelper(a,b,a.length(),b.length(),eds);
	}
	
	/**
	 * #2.  Add a recursive version here, which uses a memory function.  Change your internal implementation
	 * to use this version. (1 point)
	 */
	private int editDistanceCalculatorHelper(String a, String b, int i, int j, int[][] eds)
	{
		if(i==0 || j==0)
			return eds[i][j];
		
		int delete=editDistanceCalculatorHelper(a,b,i-1,j,eds)+1;
		int insert=editDistanceCalculatorHelper(a,b,i,j-1,eds)+1;
		int remove=editDistanceCalculatorHelper(a,b,i-1,j-1,eds)+(a.charAt(i-1)==b.charAt(j-1)?0:1);
		eds[i][j]=Math.min(delete,  Math.min(insert, remove));
		
		return eds[i][j];
	}
}
