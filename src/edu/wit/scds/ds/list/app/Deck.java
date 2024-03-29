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

import java.util.Collections ;

/**
 * Representation of a deck of cards
 *
 * @author Nathan Christie
 *
 * @version 1.0.0 2022-11-15 Initial implementation
 * @version 1.0.1 2022-11-19 Template implementation
 * @version 1.0.2 2022-11-19 Template implementation 2
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
    	 *  ( Example of initializing a card: new Card(Suit.SPADES, Rank.ACE ).
    	 */
    	// TODO implement this
    	for( Suit aSuit : Suit.values() )
    	    {
    	    for( Rank aRank : Rank.values() )
    	        {
    	        this.add( new Card( aSuit, aRank ) ) ;
    	        
    	        
    	        }
    	    
    	    }
    	
    	}  // end no-arg deck()
	
	// API methods
	
	/**
	 * 
	 * 
	 * @param teams
	 */
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
        shuffle();
        for(Team currentTeam: teams)
            {
            for(Player currentPlayer: currentTeam.getPlayers())
                {
                for(int i=0;i<6;i++)
                    {
                    currentPlayer.getHand().add( remove() );
                    }
                }
            }
        
        
        }  // end deal()
	
	/**
	 * 
	 * 
	 */
	public void shuffle()
    	{
    	/*
    	 * Shuffles the deck- obviously. The deck shuffles
    	 * the cards that it currently has. The shuffle can be
    	 * thorough or it can be as simple as our lab assignment-
    	 * go ham.
    	 */
    	Collections.shuffle( this.cards );
    	
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
		   System.out.println( "YES" );
		   
		   Deck testDeck = new Deck() ;
		   for( Card currentCard : testDeck.cards )
		       {
		       System.out.println( currentCard.toString() ) ;
		       
		       }
		   
		}	// end main()

	}	// end class Deck