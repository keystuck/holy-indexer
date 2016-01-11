//IndexMaker
//main class for SermonIndexer project
//handles i/o
//Emily Stuckey
//1-10-16
import java.util.Scanner;


public class IndexMaker {
  public IndexMaker(){
    Scanner input = new Scanner(System.in);
    System.out.print("Enter 1 to create a new Sermon index, 2 to create a new Scriptural index, 3 to merge indices: ");
    int whatWant = input.nextInt();
    if (whatWant == 1){
      //creating new index
    }
    else if (whatWant == 2){
      //creating new scriptural index (automatically?)
    }
    else if (whatWant == 3){
      //merging indices
    }
    else {
      System.out.println("Goodbye.");
    }
  }



}
