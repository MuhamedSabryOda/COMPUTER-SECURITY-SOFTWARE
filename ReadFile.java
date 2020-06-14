import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.IOException;

public class ReadFile {

  public static void main(String[] args) {
  String plaintext ="" ;
  String key ="";
  String condition ="";

// opening the text file (input.txt) to take the three lines 
// line 1 : e or d to determine weither encryption or decryption
// line 2 : the message
// line 3 : the key

    try {
      File myObj = new File("input.txt");
      Scanner myReader = new Scanner(myObj);
      condition = myReader.nextLine();
      plaintext = myReader.nextLine();
      key = myReader.nextLine(); 
      myReader.close();
    } 
    catch (FileNotFoundException e) {
      System.out.println("An error occurred while openning the txt file");
      e.printStackTrace();
    }

    int plaintext_len = plaintext.length();
    int key_len = key.length();

    char[] plain_CharArray = plaintext.toCharArray();
    char[] key_CharArray = key.toCharArray();
    char[] condition_array = condition.toCharArray();
    int[] key_int_array = new int[1000]; 
    char[] cipher = new char[1000]; 

// in case the length of key is less than the messsage so we repeate it

    if (plaintext_len > key_len)
    {
      int counter = 0 ;
      for (int i =0 ; i < plaintext_len ;i++)
      {
         if (counter == key_len)
         {
           counter = 0 ;
         }
       key_int_array[i] = key_CharArray[counter]-48;
       counter ++;
      }

    }
    else 
    {
       for (int i =0 ; i < plaintext_len ;i++)
       {
         key_int_array[i] = key_CharArray[i]-48;
       }
    }
 // checking the condition flag (the first line in input file) to encrypt or decrypt
    int temp =0 ;
    if (condition_array[0] == 'e')
    {
    for (int i =0 ; i < plaintext_len ;i++) {
       temp = plain_CharArray[i] + key_int_array [i];
       if (temp > 122)
       {
         temp = temp-26;
       }

      cipher[i]= (char)temp ;
			
		  }
    }
    else 
    {
      for (int i =0 ; i < plaintext_len ;i++) {
       temp = plain_CharArray[i] - key_int_array [i];
       if (temp < 97)
       {
         temp = temp+26;
       }

      cipher[i]= (char)temp ;
			
		  }
    }

// store the result in string and print it in output.txt
      String str = String.valueOf(cipher);

    try {
      FileWriter   myWriter = new FileWriter  ("output.txt");
      myWriter.write(str);
      myWriter.close();
    }
  
    catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}