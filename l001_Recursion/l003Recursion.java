public class l003Recursion {

  static String s1 = "send";
  static String s2 = "more";
  static String s3 = "money";

  static boolean[] usedNumber = new boolean[10]; //max 10 number we can use

  //2nd) this function is for finding the combinations
  public static int crypto(String str, int idx) {
    if (idx == str.length()) {
      int x = convertStringToNumber(s1);
      int y = convertStringToNumber(s2);
      int z = convertStringToNumber(s3);

      if (x + y == z) {
        return 1;
      }
      return 0;
    }
    int count = 0;
    char ch = str.charAt(idx);
    for (int i = 0; i <= 9; i++) { //means each character will have 9 options
      if (!usedNumber[i]) {
        usedNumber[i] = true;
        mapping[ch] = i;  //uss particular mapping ke across number store krdiya
        count += crypto(str, idx + 1);
        usedNumber[i] = false;
        mapping[i] = -1;
      }
    }
    return count;
  }

  //1) this function seperate the unique character
  public static int crypto() {
    String str = s1 + s2 + s3; //merge the whole string and make their frequency map
    int[] freq = new int[26];
    for (int i = 0; i < 26; i++) {
      freq[str.charAt(i) - 'a']++; //'a' - 'a' = 0
    }
    str = ""; //unique string generate krni h thats why

    for (int i = 0; i < 26; i++) { //we want that character whose freq is more than 1
      if (freq[i] > 0) {
        str += (char) (i + 'a'); // 2 + 'a' = 2 + 1 = 3
      }
    }

    Arrays.fill(mapping,-1);
    System.out.println(crypto(str,0));
  }

  public static void main(String[] args) {}
}
