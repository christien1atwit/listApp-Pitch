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
 * Representation of a hand of cards
 *
 * @author Your Name
 *
 * @version 1.0.0 2022-11-15 Initial implementation
 * @version 1.0.1 2022-11-19 Template implementation
 */
public class Hand extends Pile
    {
    // TODO implement this
    
    // API methods
    
    public void checkPlayableCards( RoundPile aRoundPile )
        {
        /*
         * If the hand contains the roundPiles SuitType:
         *   Checks the SuitType of the roundPile and compares each card with
         *   the SuitType(name). 
         *   If the SuitType(name) is equal, set the playable of the card to true-
         *   If not, set the playable of the card to false.
         * If the hand doesn't contain the roundPiles SuitType:
         *   set all cards to playable.
         */
        // TODO implement this
        
        }   // end checkPlayableCards()
    
    public boolean playCard( int index , RoundPile aRoundPile ) 
        {
        /*
         * (index of the card to play)
         * If the card is playable, add it to the roundPile.
         * If the card isn't playable return false.
         */
        // TODO implement this
        
        }   // end playCard()
    
    // Private Methods
    
    private boolean containsSuit( Suit aSuit )
        {
        /*
         * Iterates through cards to see if there's a card with the given suit (name).
         */
        // TODO implement this
        
        }   // end containsSuit()
    
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

    }	// end class Hand