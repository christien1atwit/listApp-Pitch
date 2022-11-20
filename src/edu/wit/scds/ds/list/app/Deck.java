/* @formatter:off
 *
 * Dave Rosenberg
 * Comp 2000 - Data Structures
 * Lab: List application - card game
 * Fall, 2022
 * 
 * Usage restrictions:
 * 
 * You may use this code for exploration, experimentation, and furthering your
 * learning for this course. You may not use this code for any other
 * assignments, in my course or elsewhere, without explicit permission, in
 * advance, from myself (and the instructor of any other course).
 * 
 * Further, you may not post (including in a public repository such as on github)
 * nor otherwise share this code with anyone other than current students in my 
 * sections of this course. Violation of these usage restrictions will be considered 
 * a violation of the Wentworth Institute of Technology Academic Honesty Policy.
 *
 * Do not remove this notice.
 *
 * @formatter:on
 */

package edu.wit.scds.ds.list.app;

/**
 * Representation of a deck of cards
 *
 * @author Your Name // TODO
 *
 * @version 1.0.0 2022-11-15 Initial implementation
 * @version 1.0.1 2022-11-19 Template implementation
 *
 */
public class Deck extends Pile
	{
	// TODO implement this
	
	// Constructor
	
	public Deck()
    	{
    	/*
    	 *  Initializes and adds all of the cards that will be used in the game.
    	 *  (See the Enums Suit and Rank).
    	 */
    	// TODO implement this
    	
    	}  // end no-arg deck()
	
	// API methods
	
	public void deal( Player[] players )
    	{
    	/*
    	 * Accesses each of the players' hands and 
    	 * adds a group of random cards from the deck
    	 * to each hand (distributed equally). 
    	 * Each player is supposed to get 6 random cards?
    	 * ( Use thePlayerNameExample.getHand() to access the hand )
    	 */
    	// TODO implement this
    	
    	}  // end deal()
	
	public void deal( Team[] teams )
        {
        /*
         * Accesses each of the teams' players' hands and 
         * adds a group of random cards from the deck
         * to each hand (distributed equally). 
         * Each player is supposed to get 6 random cards?
         * ( Use thePlayerNameExample.getHand() to access the hand )
         */
        // TODO implement this
        
        }  // end deal()
	
	public void shuffle()
    	{
    	/*
    	 * Shuffles the deck- obviously. The deck shuffles
    	 * the cards that it currently has. The shuffle can be
    	 * thorough or it can be as simple as our lab assignment-
    	 * go ham.
    	 */
    	// TODO implement this
    	
    	}  // end shuffle
	
	/**
     * (optional) test driver
     * 
     * @param args
     *     -unused-
     */
	public static void main( String[] args )
		{
        // OPTIONAL for testing and debugging
		   System.out.print( "YES" );
		}	// end main()

	}	// end class Deck