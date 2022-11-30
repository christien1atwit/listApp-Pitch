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

/**
 * Representation of a player
 *
 * @author Your Name // William Sten
 *
 * @version 1.0.0 2022-11-15 Initial implementation
 * @version 1.0.1 2022-11-19 Template implementation
 */
public class Player
    {
    // DONE implement this
    
    // Data fields
    private Hand myHand ;
    private int bet ;
    private String playerName ; 
    
    // Constructor
    
    /**
     * @param giveHand 
     * giveHand is the hand the player has been dealt 
     * 
     */
    public Player(Hand giveHand)
        {
        /*
         * Initializes Hand
         */
        // TODO implement this
        this.myHand = giveHand ; 
        
        
        
        }   // end constructor
    
    /**
     * 
     * 
     * @param value
     * set value of a players bet
     */
    // API methods
    
    public void setBet( int value )
        {
        // DONE implement this
        this.bet = value ; 
        }   // end 1-arg setBet()
    
    
    /**
     * 
     * 
     * @return this.bet
     */
    public int getBet()
        {
        // DONE implement this
        return this.bet ; 
        
        }   // end getBet()
    
    /**
     * 
     * 
     * @return a players hand
     */
    public Hand getHand()
        {
        // DONE implement this
        return this.myHand ; 
        
        }   // end getHand()
    
    /**
     * 
     * 
     * @param name
     * sets players name
     */
    public void setName( String name )
        {
        // DONE implement this
        this.playerName = name ; 
        }   
    
   
    /**
     * 
     * 
     * @return player name
     */
    public String getName()
        {
        // DONE implement this
        return this.playerName ; 
        }   
    
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

    }	// end class Player