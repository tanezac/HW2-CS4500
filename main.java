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

        // Underline
        public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
        public static final String RED_UNDERLINED = "\033[4;31m";    // RED

        // Background
        public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
        public static final String RED_BACKGROUND = "\033[41m";    // RED
      
        // High Intensity backgrounds
        public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
        public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    }
    
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
        System.out.println(ConsoleColors.BLACK_BACKGROUND_BRIGHT + card);
        }   
    else{
        System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + card);
        } 
    }
    
    // Main method
    public static void main(String[] args) {
    
    System.out.println("Wellcome to the Game.");
   
    Main card = new Main(9, 3);
    Main card1 = new Main(10, 1);
    
    displayCard(card);
    displayCard(card1);
    
}
}
   
