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

Compiler. Compiler | Formal Languages and Compilers. (n.d.). Retrieved February 6, 2022, from https://brasil.cel.agh.edu.pl/~11sustrojny/en/compiler/index.html 
Deck of cards java. Stack Overflow. (1961, March 1). Retrieved February 6, 2022, from https://stackoverflow.com/questions/15942050/deck-of-cards-java/25092220 
Bernhard, A. (2021, June 21). The lost origins of playing-card symbols. The Atlantic. 
    Retrieved February 6, 2022, from https://www.theatlantic.com/technology/archive/2017/08/the-lost-origins-of-playing-card-symbols/537786/ 


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
    public static final int SIZE_PILES = 21;
    public static boolean check = false;
    public static int user_choice;
    
    public static void EnterKey(){
        System.out.println(ConsoleColors.WHITE_BOLD + "Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    
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
    
    public static void split_card(Main piles_1[], Main piles_2[], Main piles_3[], Main arrayCard[]){
        piles_1 = Arrays.copyOfRange(arrayCard, 0, 6);
        piles_2 = Arrays.copyOfRange(arrayCard, 7, 13);
        piles_3 = Arrays.copyOfRange(arrayCard, 14, 20);
        
        System.out.println(ConsoleColors.WHITE_BOLD + "Piles 1: ");
        display_piles(piles_1);
        
        System.out.println(ConsoleColors.WHITE_BOLD + "\nPiles 2: ");
        display_piles(piles_2);
        
        System.out.println(ConsoleColors.WHITE_BOLD + "\nPiles 3: ");
        display_piles(piles_3);
    }
    
    public static Main[] append_array(Main array1[], Main array2[]) {
        Main array[] = new Main[array1.length + array2.length];
        for(int i = 0; i < array1.length; i++){
            array[i] = array1[i];        
        }
        
        for(int i = array1.length; i < (array1.length + array2.length); i++){
            array[i] = array2[i];        
        }
        return array;
    }
    
    public static Main[] append_card(Main piles_1[], Main piles_2[], Main piles_3[], int user_choice){
        Main array1[] = new Main[14];
        Main new_arrayCard[] = new Main[SIZE];
        array1 = append_array(piles_1, piles_2);
        new_arrayCard = append_array(array1, piles_3);
        return new_arrayCard;
        
    }
    
    // Main method
    public static void main(String[] args) {
        final int SIZE = 21;
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
        Main arrayCard[] = new Main[SIZE];
        
        for(int i =0; i < SIZE; i++){
            random_ranks = (int)Math.floor(Math.random() * (max_ranks - min_ranks + 1) + min_ranks);
            random_suits = (int)Math.floor(Math.random() * (max_suits - min_suits + 1) + min_suits);
            arrayCard[i] = new Main(random_ranks, random_suits);
            
        }
        
        for(int i = 0; i < SIZE; i++ ){
            displayCard(arrayCard[i]);
        }
        
        System.out.println(" ");
        EnterKey();
        System.out.println("The computer will deal 21 cards into 3 different piles, each  piles will have 7 cards.\n");
        
        Main piles_1[] = new Main[SIZE_PILES];
        Main piles_2[] = new Main[SIZE_PILES];
        Main piles_3[] = new Main[SIZE_PILES];
        
        split_card(piles_1, piles_2, piles_3, arrayCard);
        
        //User coice the pilers have his/her card
        Scanner input = new Scanner(System.in); 
        System.out.print(ConsoleColors.WHITE_BOLD + "\nPlease enter the number of piles you see your cards in: ");
         
        user_choice = check_user(user_choice, input);
        System.out.print("user_choice: " + user_choice);
        Main new_arrayCard[] = new Main[SIZE];
        new_arrayCard = append_card(piles_1, piles_2, piles_3, user_choice);
        display_piles(new_arrayCard);
        
        
        
        
        
        
        
    

    
       
        
    
}
}
   






















