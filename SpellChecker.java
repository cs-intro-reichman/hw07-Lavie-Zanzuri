
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		// Your code goes here
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		// Your code goes here
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		int length1 = word1.length();
		int length2 = word2.length();
		int count = 0;
		if(length2 == 0) {
			return length1;
		}
		if(length1 == 0) {
			return length2;
		}
		if(word1.charAt(0) != word2.charAt(0)) {
			count = 1 ;
		}
		if(word1.charAt(0) == word2.charAt(0)) {
			count = 0 ;
		}
		int substitute = levenshtein(tail(word1), tail(word2)) + count ;
		int delete = levenshtein(tail(word1), word2) + 1;
		int insert = levenshtein(word1,tail(word2)) + 1;
		return Math.min(Math.min(delete,substitute),insert);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		// Your code here
		for(int i = 0 ; i < dictionary.length; i ++) {
			dictionary [i] = in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// Your code goes here
		String closer = dictionary [0] ;
		int temp = levenshtein(word,closer);
		for(int i = 1 ; i < dictionary.length; i ++) {
			if(temp >= levenshtein(word,dictionary[i])) {
				temp = levenshtein(word,dictionary[i]);
				closer = dictionary[i];
			}
		}
		if(temp > threshold){
			return word;
		}
		return closer ;
	}

}
