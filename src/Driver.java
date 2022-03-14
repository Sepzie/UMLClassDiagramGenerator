import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException {
        final Scanner fileScan =
                new Scanner(new File("C:\\Users\\zohoo\\IdeaProjects\\UMLClassDiagramGenerator\\src\\Tree.java"));
        while(fileScan.hasNext()) {
            System.out.println(fileScan.next());
        }
    }
}


