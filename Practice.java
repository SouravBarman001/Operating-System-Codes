package operating.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Practice {
    public static void main(String[] args) {
        List<String> pages = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {

            pages.add((scanner.next()));
        }
        scanner.close();

        String[] reference_string = pages.toArray(new String[pages.size()]);

        for(String i : reference_string)
               System.out.print(i+" ");

      //  Integer[] reference = new Integer[pages.size()];
        //reference = pages.toArray(reference);
    }
}
