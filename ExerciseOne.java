package toc;
/*
Problem:
Begins with a 1 and ends with a 0

input symbol{0,1}
State Diagram of the problem:
______________________
state    1      0
 A     | B   | D
 B     | B  |  C
 C     | B  |  C
 D     | D  |  D
------------------------
inital state : A
accepting state : C
strap state : D

 */


import java.util.Scanner;

public class ExerciseOne {

    public static boolean Begins1_0Ends(String input){
        boolean p = true;
       return p;
    }


    public static void main(String[] args) {

        Scanner inputScanner= new Scanner(System.in);
        System.out.println("Enter a String which is starting with a 1 ends ends with a 0 :");
        String input = inputScanner.nextLine();
        System.out.println(Begins1_0Ends(input));

    }
}
