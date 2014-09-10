
public class Soundex {
	private final String soundex;
	
	public Soundex(String word) {
		this.soundex = toSoundex(word);
	}
	
	public String getSoundex() {
		return soundex;
	}
	
	/**
	 * #1. Make a better, more uniform hashcode (1 point)
	 */
	public int hashCode() {
		int value=0;
		
		value+=(soundex.charAt(1)-48)*100000;
		value+=(soundex.charAt(2)-48)*10000;
		value+=(soundex.charAt(3)-48)*1000;
		value+=soundex.charAt(0);
		
		return value;
	}
	
	public boolean equals(Object that) {
		if ( that instanceof Soundex ) {
			return this.soundex == null ? ((Soundex)that).soundex == null : this.soundex.equals(((Soundex) that).soundex);
		}
		return false;
	}
	
	private String toSoundex(String word) {
		StringBuilder soundex = new StringBuilder("0000");//char[] soundex = new char[] { '0', '0', '0', '0' };
		int j = 0;
		for ( int i = 0 ; i < word.length(); i++ ) {
			char ch = word.charAt(i);
			if ( ch == '\'' || ch == '\"' || ch == '-' ) {
				continue;
			}
			if ( i == 0 || !( ch == '�' || ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'y' || ch == 'h' || ch == 'w' ) ) {
				if ( i + 1 < word.length() ) {
					if ( characterToSoundexIndex(word.charAt(i+1)) == characterToSoundexIndex(ch) ) { // code double letters as one
						i++;
					} else if ( i + 2 < word.length() ) { // code double letters separated by an h or w as one
						if ( (word.charAt(i+1) == 'h' || word.charAt(i+1) == 'w') && characterToSoundexIndex(word.charAt(i+2)) == characterToSoundexIndex(ch) ) {
							i += 2;
						}
					}
				}
				
				if ( j == 0 ) {
					soundex.setCharAt(j++, ch);
				} else if ( j < 4 ) {
					soundex.setCharAt(j++, characterToSoundexIndex(ch));
				}
			}
			
		}
		
		return soundex.toString();
	}

	private char characterToSoundexIndex(char ch) {
		switch(ch) {
		case 'b': case 'f': case 'p': case 'v':
			return '1';
		case 'c': case 'g': case 'j': case 'k': case 'q': case 's': case 'x': case 'z':
			return '2';
		case 'd': case 't':
			return '3';
		case 'l':
			return '4';
		case 'm': case 'n':
			return '5';
		case 'r':
			return '6';
		default:
			return ch;
		}
	}
}
