import java.util.Scanner;

public class Method {

    public static Scanner scan = new Scanner(System.in);
    
    public static String enterName(){
        String name = scan.nextLine();
        return name;
    }

}