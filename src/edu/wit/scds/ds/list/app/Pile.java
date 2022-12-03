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

package edu.wit.scds.ds.list.app ;

import java.util.LinkedList ;
import java.util.List ;

/**
 * Representation of a pile of card
 * <p>
 * the bottom card is at position 0
 *
 * @author Nathan Christie
 *
 * @version 1.0.0 2022-11-15 Initial implementation
 * @version 1.0.1 2022-11-19 Template implementation
 * @version 1.0.2 2022-11-20 Template implementation 2
 */

public class Pile
    {
    
    // Data fields
    /** the list of cards - directly accessible by subclasses */
    protected List<Card> cards ;   // instantiate an ArrayList or LinkedList in the constructor
    /** Number of cards in List<Card> cards */
    protected int numberOfCards ;
    private static Suit trumpSuit ;
    
    // Constructor
    
    /**
     * 
     */
    public Pile()
        {
        // instantiate an ArrayList or LinkedList in the constructor and numberOfCards
        // DONE implement this
        this.cards = new LinkedList<>() ;
        this.numberOfCards = 0 ;
        
        }   // end no-arg constructor
    
    /**
     * 
     * @param newCard
     *      card to be added to List<Card> cards
     */
    // API methods
    
    public void add( Card newCard )
        {
        // increment number of cards as well
        // DONE implement this
        this.cards.add( 0, newCard ) ; //Adds the new card to the 'top' of the pile
        this.numberOfCards++ ;
        
        }   // end add()
    
   
    
    /**
     * 
     * 
     * @return
     *      number of cards in List<Card> cards
     */
    public int getNumberOfCards()
        {
        // DONE implement this
        return this.numberOfCards;
        }   // end getNumberOfCards()
    
    /**
     * 
     * 
     * @return
     *      true is numberOfCards equals 0
     */
    public boolean isEmpty()
        {
        // DONE implement this
        return this.numberOfCards == 0 ;
        }   // end isEmpty()
    
    /**
     * Removes the card from a specified index
     * 
     * @param cardNum
     *      index of card to remove
     * @return Card
     *      card removed
     */
    public Card remove(int cardNum )
        {
        // decrement number of cards as well
        if ((cardNum>=0)&&(cardNum<this.numberOfCards))
            {
            Card removedCard=this.cards.get( cardNum );
            this.cards.remove( cardNum );
            this.numberOfCards--;
            return removedCard;
            }//end if
        return null;
       
        
        }   // end remove()
    
    /**
     * Removes the first card in the pile (The top card)
     * Useful for dealing cards from a deck
     * 
     * @return Card
     */
    public Card remove()
        {
        
        if(!isEmpty())
            {
            Card removedCard=this.cards.get( 0 );
            this.cards.remove( 0 );
            this.numberOfCards--;
            return removedCard;
            }
        return null;
       
        
        }   // end 0-arg remove()
    
    /**
     * Set trump suit to suit
     * 
     * @param suit - suit to set
     */
    public static void setTrumpSuit( Suit suit )
        {
        trumpSuit = suit ;
        
        }
    
    /**
     * 
     * @return
     *      trump suit for set
     */
    public static Suit getTrumpSuit()
        {
        return trumpSuit ;
        
        }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()            // debugging aid
        {
        return this.cards.toString() ;

        }	// end toString()


    /**
     * (optional) test driver
     *
     * @param args
     *     -unused-
     */
    public static void main( final String[] args )
        {
        // OPTIONAL for testing and debugging

        }	// end main()

    }	// end class Pile