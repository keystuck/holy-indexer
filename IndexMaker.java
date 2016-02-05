//IndexMaker
//main class for SermonIndexer project
//handles i/o
//Emily Stuckey
//1-10-16
import java.util.Scanner;
import java.io.*;


public class IndexMaker {
  public IndexMaker(){
    Scanner input = new Scanner(System.in);
    System.out.print("Enter 1 to create a new Sermon index, 2 to create a new ");
    System.out.print("scriptural index, 3 to merge indices, 4 to merge Scriptural ");
    System.out.print("indices, 5 to search a sermon for Scriptural indices ");
    System.out.print("(and make a scriptural index), anything else to exit: ");
    int whatWant = input.nextInt();
    if (whatWant == 1){
      boolean done = false;
notDone:      while (!done){
        //creating new index
        System.out.print("Enter number/title of sermon or EXIT to exit: ");
        String serm = input.next();
        if (serm.equalsIgnoreCase("exit")){
          done = true;
          break notDone;
        }
        System.out.print("Enter name of file (remember extension): ");
        String fName = input.next();
        if (fName.equalsIgnoreCase("exit")){
          done = true;
          break notDone;
        }
        try {
          //Open the given file
          Index sermIndex = new Index(serm, fName);
          //create a new DocumentIndex from the file
          BufferedWriter sermWriter = new BufferedWriter(new FileWriter(serm + "-index.txt"));
          //Create an output file for that sermon: e.g. "1-index.txt"
          //write the DocumentIndex to the output file

          sermWriter.write(sermIndex.toString());
          sermWriter.flush();
          sermWriter.close();
        }
        catch (IOException e){
          System.out.println("Error in IndexMaker: " + e.getMessage());
        }
    }
    }
    else if (whatWant == 2){
      boolean done = false;
notDone2: while (!done){
      System.out.print("Enter number/title of sermon or EXIT to exit: ");
      String serm = input.next();
      if (serm.equalsIgnoreCase("exit")){
        done = true;
        break notDone2;
      }
      System.out.print("Enter name of file (remember extension): ");
      String fName = input.next();
      if (fName.equalsIgnoreCase("exit")){
        done = true;
        break notDone2;
      }
      try {
        //Open the given file
        ScriptureIndex scriptIndex = new ScriptureIndex(serm, fName);
        //create a new DocumentIndex from the file
        BufferedWriter scriptWriter = new BufferedWriter(new FileWriter(serm + "-scr-index.txt"));
        //Create an output file for that sermon: e.g. "1-index.txt"
        //write the DocumentIndex to the output file

        scriptWriter.write(scriptIndex.toString());
        scriptWriter.flush();
        scriptWriter.close();
      }
      catch (IOException e){
        System.out.println("Error in IndexMaker: " + e.getMessage());
      }
    }
  }
    else if (whatWant == 3){
      //merging indices
      boolean done = false;
notDone3: while (!done){
      System.out.print("Enter name of first file (remember extension): ");
      String fName1 = input.next();
      if (fName1.equalsIgnoreCase("exit")){
        done = true;
        break notDone3;
      }
      System.out.print("Enter name of second file (remember extension): ");
      String fName2 = input.next();
      if (fName2.equalsIgnoreCase("exit")){
        done = true;
        break notDone3;
      }
      System.out.print("Enter name of destination (remember extension): ");
      String fName3 = input.next();
      if (fName3.equalsIgnoreCase("exit")){
        done = true;
        break notDone3;
      }
      try {
        //Open the given files
        Index compIndex = new Index(new FileReader(fName1), new FileReader(fName2));
        //create a new DocumentIndex from the file
        BufferedWriter compWriter = new BufferedWriter(new FileWriter(fName3));
        //Create an output file for that sermon: e.g. "1-index.txt"
        //write the DocumentIndex to the output file

        compWriter.write(compIndex.toString());
        compWriter.flush();
        compWriter.close();
      }
      catch (IOException e){
        System.out.println("Error in IndexMaker: " + e.getMessage());
      }
    }
  }
    else if (whatWant == 4){
      //merging scriptural indices
      boolean done = false;
notDone4: while (!done){
      System.out.print("Enter name of first file (remember extension): ");
      String fName1 = input.next();
      if (fName1.equalsIgnoreCase("exit")){
        done = true;
        break notDone4;
      }
      System.out.print("Enter name of second file (remember extension): ");
      String fName2 = input.next();
      if (fName2.equalsIgnoreCase("exit")){
        done = true;
        break notDone4;
      }
      System.out.print("Enter name of destination (remember extension): ");
      String fName3 = input.next();
      if (fName3.equalsIgnoreCase("exit")){
        done = true;
        break notDone4;
      }
      try {
        //Open the given files
        ScriptureIndex compIndex = new ScriptureIndex(new FileReader(fName1), new FileReader(fName2));
        //create a new DocumentIndex from the file
        BufferedWriter compWriter = new BufferedWriter(new FileWriter(fName3));
        //Create an output file for that sermon: e.g. "1-index.txt"
        //write the DocumentIndex to the output file

        compWriter.write(compIndex.toString());
        compWriter.flush();
        compWriter.close();
      }
      catch (IOException e){
        System.out.println("Error in IndexMaker: " + e.getMessage());
      }
    }
    }
/*    else if (whatWant == 5){
      boolean done = false;
notDone5:      while (!done){
  //creating new index
      System.out.print("Enter number/title of sermon or EXIT to exit: ");
      String serm = input.next();
      if (serm.equalsIgnoreCase("exit")){
        done = true;
        break notDone5;
      }
      System.out.print("Enter name of file (remember extension): ");
      String fName = input.next();
      if (fName.equalsIgnoreCase("exit")){
        done = true;
        break notDone5;
      }
      try {
        //Open the given file
        //create an index by scanning for the scriptural references
        ScrStrIndex sermIndex = new ScrStrIndex(serm, fName);
    //create a new DocumentIndex from the file
        BufferedWriter sermWriter = new BufferedWriter(new FileWriter(serm + "-script1-index.txt"));
    //Create an output file for that sermon: e.g. "1-index.txt"
    //write the index to the output file

    sermWriter.write(sermIndex.toString());
    sermWriter.flush();
    sermWriter.close();
  }

  catch (IOException e){
    System.out.println("Error in IndexMaker: " + e.getMessage());
  }
}
}
*/

    else {
      System.out.println("Goodbye.");
    }
  }



}
