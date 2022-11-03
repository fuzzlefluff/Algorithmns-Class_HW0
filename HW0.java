// Matthew Prindle Homework 0
// CSC 4520 Fall 2022
import java.math.BigInteger;
import java.util.*;

public class HW0 {

  public static void main(String[] args) {
    System.out.println("Testing Main in new Enviroment");
    // Q1
    int testResult1 = maxOfArray(new int[] {1, 3, 4, 5, 2});
    int testResult2 = maxOfArray(new int[] {-1, -3, -4, -5, -2});

    System.out.println(testResult1); // should output 5
    System.out.println(testResult2); // should output -1
    boolean emptyCaseCorrect = false;
    try {
      maxOfArray(new int[] {});
    } catch (IllegalArgumentException e) {
      emptyCaseCorrect = true;
    }
    if (emptyCaseCorrect) {
      System.out.println("maxOfArray appears to work for the empty array case");
    } else {
      System.out.println("maxOfArray appears to be incorrect for the empty array case");
    }


    // Q2
    int[] testResult3 = twoSum(new int[] {0, 2, 3, 4, 5}, 6);
    int[] testResult4 = twoSum(new int[] {1, 2, 3, 4, 5}, 10);
    int[] testResultMatt_1 = twoSum(new int[] {-1, 2, 3, 4, 0}, -1);

    System.out.println(Arrays.toString(testResult3)); // should output [1, 3]
    System.out.println(Arrays.toString(testResult4)); // should output [-1, -1]
    System.out.println(Arrays.toString(testResultMatt_1)); // should output [0, 4]


    // Q3
    List<Integer> testResult5 = add(Arrays.asList(1, 2, 3), Arrays.asList(2, 4, 2));
    List<Integer> testResult6 = add(Arrays.asList(9, 9, 9), Arrays.asList(1));
    List<Integer> testResult7 = add(Arrays.asList(9, 2, 2, 3, 3, 7, 2, 0, 3, 6, 8, 5, 4, 7, 7, 5, 8, 0, 7), Arrays.asList(1, 2, 3, 4, 5));

    System.out.println(testResult5); // should output [3, 6, 5]
    System.out.println(testResult6); // should output [1, 0, 0, 0]
    System.out.println(testResult7); // should output [9, 2, 2, 3, 3, 7, 2, 0, 3, 6, 8, 5, 4, 7, 8, 8, 1, 5, 2]

  }

  /*
   I simply iterate through the array and set aside the largest integer we have seen in the result variable only updating it if we encounter something larger.
   I also check for empty arrays as per the requirements.
  */
  public static int maxOfArray(int[] arr) {
    //throws a fit when we get a bad array
    if(arr == null || arr.length == 0) {
      throw new IllegalArgumentException("Array is null or contains no elements!");
    }
    //grab the first element of the array as our initial result.
    int r = arr[0];
    //iterate through the array
    for(int i = 0; i < arr.length; i++){
      //check if we have a larger number on this index, and save it if it is in fact larger
      if(r<arr[i]){
        r = arr[i];
      }
    }
    return r;
  }

  /*
  I simply itterate through the array and then have a second sub loop that lets us compare to all the other possible values, breaking as soon as we find what we need.
  To prevent using the same value twice, I simply skip that specific subloop iteration if the indexes are the same. 
  This means if we have 2 different entries that are the same value we could still use them.
  */
  public static int[] twoSum(int[] arr, int targetSum) {
    //create the result array and a store for our sums to check
    int[] r = {-1,-1};
    int sum;

    //iterate through the array and compare it against all other possible values until we find what we need
    for(int i = 0; i < arr.length; i++){
      for(int f = 0; f < arr.length; f++){
        sum = arr[i] + arr[f];
       
        //this forces us to skip and ignore a value so it can't be used twice.
        if(i == f){
          continue;
        }
        //stop if we find the sum we are looking for
        if(sum == targetSum){
          r[0] = i;
          r[1] = f;
          return r;
        }
      }
    }
    return r;
  }

  /**
   I think it is helpful to use strings as a makeshift stack when converting integers from interger-extra structures. 
   Given the insane sized int we are given in the third test case I use BigInt to handle arbitarily large numbers without actually thinking about it.
   Most of this is converting between string stacks, big ints, back to the List we have been asked for. 
   */
  public static List<Integer> add(List<Integer> lst1, List<Integer> lst2) {
    //convert our lists into BigInts
    BigInteger n1 = intListToBigInt(lst1);
    BigInteger n2 = intListToBigInt(lst2); 
    //ask BigInt to handle adding properly for me
    BigInteger r = n1.add(n2);

    //setup a the intList we are going to return
    List<Integer> rLst = new ArrayList<Integer>();
    //convert our summed bigInt to a String so we can iterate through it
    String rStr = r.toString();

    //go through our string and dump it into the list we are going to return
    for(int i = 0; i<rStr.length(); i++){
      rLst.add(Character.getNumericValue(rStr.charAt(i)));
    }
    return rLst;
  }
  //I'm big on making methods when I have to do something twice, thus I set aside my code to convert from the given intLists to BigInt so java can do math for me
  private static BigInteger intListToBigInt(List<Integer> inpLst){
    String nStr = "";
    //we iterate through the list and treat each entry as a character in a great string.
    for (Integer inpI : inpLst) {
      nStr += Integer.toString(inpI);
    }
    //ask biginteger to do the hard work of reading our string and storing it in whatever size is needed.
    BigInteger r = new BigInteger(nStr);
    return r;
  }
 }
