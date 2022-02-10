/******************************************************************************
Languages programMing: JAVA

Project: Homework 2 -  21-Card Trick - Numberphile
Name: Tan Nguyen
Class: CS4500 - Intro to Software Profession
Date: Feb 8, 2022
External files: None

Description: 

Here is a mapping for suits:
Clubs	↦	0
Diamonds↦	1
Hearts	↦	2
Spades	↦	3

Each of the numerical ranks (2 through 10) maps to the corresponding integer, and for face cards:
Ace	    ↦	1
Jack	↦	11
Queen	↦	12
King	↦	13


Outside Sources:

- Compiler. Compiler | Formal Languages and Compilers. (n.d.). Retrieved February 6, 2022, from https://brasil.cel.agh.edu.pl/~11sustrojny/en/compiler/index.html 
- Deck of cards java. Stack Overflow. (1961, March 1). Retrieved February 6, 2022, from https://stackoverflow.com/questions/15942050/deck-of-cards-java/25092220 
- Bernhard, A. (2021, June 21). The lost origins of playing-card symbols. The Atlantic. 
    Retrieved February 6, 2022, from https://www.theatlantic.com/technology/archive/2017/08/the-lost-origins-of-playing-card-symbols/537786/ 
- Java program to merge two arrays. GeeksforGeeks. (2020, October 15). Retrieved February 10, 2022, from https://www.geeksforgeeks.org/java-program-to-merge-two-arrays/ 


*******************************************************************************/
import java.util.*;

public class Main{
    public static final String[] RANKS = {null, "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    public static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    public static final String[] ICON  = {"♣", "♦", "♥", "♠"};
    
    public class ConsoleColors {
        // Reset
        public static final String RESET = "\033[0m";  // Text Reset

        // Bold
        public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
        public static final String RED_BOLD = "\033[1;31m";    // RED
        public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    }
    
    /***************************************************************************/
    private int rank;
    private int suit;
    private int icon;

    public Main(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
        //this.icon = icon;
    }
    
    public String toString() {
         return RANKS[this.rank] + " of " + SUITS[this.suit] + " " + ICON[this.suit];
    }
    
    public int cardColor(){
        return this.suit;
    }
    
    //display single card
    public static void displayCard(Main card){
        if(card.cardColor() == 0 | card.cardColor() == 3 ){
            System.out.println(ConsoleColors.BLACK_BOLD + card + ConsoleColors.BLACK_BOLD);
        }   
        else{
            System.out.println(ConsoleColors.RED_BOLD + card + ConsoleColors.RED_BOLD);
        } 
    }
    /***************************************************************************/
    
    public static final int SIZE = 21;
    public static final int SIZE_PILES = 7;
    public static boolean check = false;
    public static int user_choice;
    public static int user_choice1;
    public static int user_choice2;
    
    
    public static void EnterKey(){
        System.out.println(ConsoleColors.WHITE_BOLD + "Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    
    // display piles
    public static void display_piles(Main card[]){
        for (int i = 0; i < card.length; i++){
            displayCard(card[i]);
        }
    }
    
    public static int check_user(int user_choice, Scanner input){
        while(!check){
            if(input.hasNextInt()){
                user_choice = input.nextInt();
                if(user_choice < 1 || user_choice > 3){
                System.out.print("\nYou must enter an integer - X from 1 to 3.");
                System.out.println("Please try again!");
                System.out.print("Enter the number of piles from 1 to 3: ");
                input = new Scanner(System.in);
                check = false;
            }
            else{
                check = true;
                }
            }
            else{
                System.out.println("\nThe number is not an integer");
                System.out.print("You must Enter an integer.");
                System.out.println("Please try again!");
                System.out.print("Enter the number of piles from 1 to 3:  ");
                input = new Scanner(System.in);
                check = false;
            }
        }
        return user_choice;
    }
    
    
    public static Main[] append(Main[] a, Main[] b){
        int a1 = a.length;
        int b1 = b.length;
        int c1 = a1 + b1;
        Main[] c = new Main[c1];
        
        for (int i = 0; i < a1; i = i + 1) {
            c[i] = a[i];
        }
 
        for (int i = 0; i < b1; i = i + 1) {
            c[a1 + i] = b[i];
        }
        return c;
    }
    
    
    public static Main[] append_card(Main[] piles_1, Main[] piles_2, Main[] piles_3, int user_choice){
        Main[] array1 = new Main[piles_1.length + piles_2.length];
        Main[] new_arrayCard = new Main[piles_1.length + piles_2.length + piles_3.length];
        
        if(user_choice == 1){
            array1 = append(piles_2, piles_1);
            new_arrayCard = append(array1, piles_3);
        }
        else if (user_choice == 2){
            array1 = append(piles_1, piles_2);
            new_arrayCard = append(array1, piles_3);
        }
        else{
            array1 = append(piles_1, piles_3);
            new_arrayCard = append(array1, piles_2);
        }
        return new_arrayCard;
        
    }
        
    
    // Main method
    public static void main(String[] args) {
        
        final int SIZE = 21;
        char again;
        do{
        Scanner input = new Scanner(System.in);
        System.out.println("Wellcome to the Game - Magic Card");
        System.out.println("You will randomly choose 1 card from 21 cards, then the computer will use magic to guess the card you have chosen.");
        System.out.println("Remember, keep your chosen card in mind and don't tell the computer about it.\n");
        EnterKey();
        
        System.out.println("Please choose 1 card at random and keep it in mind."); 
        System.out.println("21 Playing Crad: \n");
        
        int min_ranks = 1;
        int max_ranks = 13;
        int random_ranks;
        
        int min_suits = 0;
        int max_suits = 3;
        int random_suits;
        
        boolean check_random = false;
        
        Main[] arrayCard = new Main[SIZE];
        Main[] piles_1 = new Main[SIZE_PILES];
        Main[] piles_2 = new Main[SIZE_PILES];
        Main[] piles_3 = new Main[SIZE_PILES];
        Main[] new_arrayCard = new Main[SIZE];
        Main[] new_arrayCard1 = new Main[SIZE];
        Main[] new_arrayCard2 = new Main[SIZE];
        int[] rank = new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 5, 9, 10, 3, 2, 11, 7, 9 };
        int[] suits = new int[]{ 0, 1, 2, 3, 3, 2, 3, 1, 0, 1, 1, 2, 3, 2, 1, 2, 3, 2, 3, 2, 1 };
       
        
        for(int i =0; i < SIZE; i++){
            arrayCard[i] = new Main(rank[i], suits[i]);
        }
        
        for(int i = 0; i < SIZE; i++ ){
            displayCard(arrayCard[i]);
        }
        
        System.out.println(" ");
        EnterKey();
        /*******************************************************************************************************************/
        //First Round
        System.out.println("The computer will deal 21 cards into 3 different piles, each  piles will have 7 cards.");
        System.out.print(ConsoleColors.WHITE_BOLD + "Find your card in these 3 piles.\n");
        
        int count = 0;
        int count1 = 0;
        int count2 =0;
        for(int i = 0; i < SIZE ; i = i + 3){
            piles_1[count] = arrayCard[i];
            count++;
        }
        for(int i = 1; i < SIZE ; i = i + 3){
            piles_2[count1] = arrayCard[i];
            count1++;
        }
        for(int i = 2; i < SIZE ; i = i + 3){
            piles_3[count2] = arrayCard[i];
            count2++;
        }
        
        System.out.println(ConsoleColors.WHITE_BOLD + "Piles 1: ");
        display_piles(piles_1);
        
        System.out.println(ConsoleColors.WHITE_BOLD + "\nPiles 2: ");
        display_piles(piles_2);
        
        System.out.println(ConsoleColors.WHITE_BOLD + "\nPiles 3: ");
        display_piles(piles_3);
        
        //User choice the pilers have his/her card
        System.out.print(ConsoleColors.WHITE_BOLD + "\nPlease enter the number of piles you see your cards in: ");
        user_choice = check_user(user_choice, input);
        System.out.println("you choose pile "+ user_choice);
        new_arrayCard = append_card(piles_1, piles_2, piles_3, user_choice); //New order of cards after user selects piles
        System.out.println(" ");
        EnterKey();
    
        /************************************************************************************************************/
        //Second role
        check = false;
        System.out.println("The computer will deal 21 cards into 3 different piles, each  piles will have 7 cards.");
        System.out.println(ConsoleColors.WHITE_BOLD + "Find your card in these 3 piles.");
        count = count1 = count2 =0;
        for(int i = 0; i < SIZE ; i = i + 3){
            piles_1[count] = new_arrayCard[i];
            count++;
        }
        for(int i = 1; i < SIZE ; i = i + 3){
            piles_2[count1] = new_arrayCard[i];
            count1++;
        }
        for(int i = 2; i < SIZE ; i = i + 3){
            piles_3[count2] = new_arrayCard[i];
            count2++;
        }
        
        System.out.println(ConsoleColors.WHITE_BOLD + "Piles 1: ");
        display_piles(piles_1);
        
        System.out.println(ConsoleColors.WHITE_BOLD + "\nPiles 2: ");
        display_piles(piles_2);
        
        System.out.println(ConsoleColors.WHITE_BOLD + "\nPiles 3: ");
        display_piles(piles_3);
        
        System.out.print(ConsoleColors.WHITE_BOLD + "\nPlease enter the number of piles you see your cards in: ");
        user_choice = check_user(user_choice, input);
        System.out.println("you choose pile "+ user_choice);
        new_arrayCard1 = append_card(piles_1, piles_2, piles_3, user_choice);
        System.out.println(" ");
        EnterKey();
        
        /*************************************************************************************************************/
        //Third round
        check = false;
        System.out.println("The computer will deal 21 cards into 3 different piles, each  piles will have 7 cards.");
        System.out.println(ConsoleColors.WHITE_BOLD + "Find your card in these 3 piles.");
        count = count1 = count2 =0;
        for(int i = 0; i < SIZE ; i = i + 3){
            piles_1[count] = new_arrayCard1[i];
            count++;
        }
        for(int i = 1; i < SIZE ; i = i + 3){
            piles_2[count1] = new_arrayCard1[i];
            count1++;
        }
        for(int i = 2; i < SIZE ; i = i + 3){
            piles_3[count2] = new_arrayCard1[i];
            count2++;
        }
        
        System.out.println(ConsoleColors.WHITE_BOLD + "Piles 1: ");
        display_piles(piles_1);
        
        System.out.println(ConsoleColors.WHITE_BOLD + "\nPiles 2: ");
        display_piles(piles_2);
        
        System.out.println(ConsoleColors.WHITE_BOLD + "\nPiles 3: ");
        display_piles(piles_3);
        
        System.out.print(ConsoleColors.WHITE_BOLD + "Please enter the number of piles you see your cards in: ");
        user_choice = check_user(user_choice, input);
        System.out.println("you choose pile "+ user_choice);
        new_arrayCard2 = append_card(piles_1, piles_2, piles_3, user_choice);
        System.out.println(" ");
        EnterKey();
        
        
        System.out.print(ConsoleColors.WHITE_BOLD + "\nYour card: ");
        displayCard(new_arrayCard2[10]);
        
        
        System.out.print(ConsoleColors.WHITE_BOLD + "Do you want to play again(Y/N): " );
        again = input.next().charAt(0);
        }while(again == 'y' || again == 'Y');
        System.out.print(ConsoleColors.WHITE_BOLD + "Thanks for playing!" );
    }
}
   

























