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
 * @author William Sten
 *
 * @version 1.0.0 2022-11-15 Initial implementation
 * @version 1.0.1 2022-11-19 Template implementation
 */
public class Hand extends Pile
    {
    // Constructor

    /**
     * Initializes pile of cards
     */
    public Hand()
        {
        super() ;

        }   // end Hand()
    // API methods


    /**
     * @param aRoundPile
     *     Checks to see if a card is of dominant suit in a hand
     */
    public void checkPlayableCards( RoundPile aRoundPile )
        {
        /*
         * If the hand contains the roundPiles SuitType: Checks the SuitType of the
         * roundPile and compares each card with the SuitType(name). If the
         * SuitType(name) is equal, set the playable of the card to true- If not, set
         * the playable of the card to false. If the hand doesn't contain the
         * roundPiles SuitType: set all cards to playable.
         */
        // DONE implement this

        // If hand has any cards that is the same Suit as the roundPile:
        Suit roundPileSuit = aRoundPile.getSuitType() ;
        if ( containsSuit( roundPileSuit ) )
            {
            // Iterate through cards
            for ( Card card : this.cards )
                {
                // If card is the same suit, make it playable
                if ( roundPileSuit == card.getSuit() )
                    {
                    card.setIsPlayable( true ) ;

                    }
                else
                    {
                    // if it isn't the same suit, make it unplayable
                    card.setIsPlayable( false ) ;

                    }

                }

            }
        else
            {
            // If hand doesn't have the suit of the RoundPile make all cards playable
            for ( Card card : this.cards )
                {
                card.setIsPlayable( true ) ;

                }

            }

        }   // end checkPlayableCards()


    /**
     * @param index
     *     index of the card to play in this.hand
     * @param aRoundPile
     *     checks if hand has playable cards of suit if hand hasSuit, then it will
     *     check the card attempting to be played if card is of suit it will return
     *     true if not it will return false and nothing will be added to round pile
     *
     * @return true or fale depending on if a card is playable
     */
    public boolean playCard( int index,
                             Player distributer,
                             Team distributerTeam,
                             RoundPile aRoundPile )
        {
        /*
         * (index of the card to play) If the card is playable, add it to the
         * roundPile. If the card isn't playable return false.
         */
        // DONE implement this
        if ( this.cards.get( index ).getIsPlayable() )
            {
            aRoundPile.add( cards.get( index ), distributer, distributerTeam ) ;
            return true ;
            } // end if

        // if hand does not have RoundSuit, then you can add any card, therefore
        // playCard will always be true
        // aRoundPile.add( this.currentCard, null ) ;
        return false ;

        }   // end playCard()

    // Private Methods


    private boolean containsSuit( Suit aSuit )
        {
        /*
         * Iterates through cards to see if there's a card with the given suit
         * (name).
         */
        // DONE implement this
        // checks if the currentCard has the roundPile suit
        for ( Card card : this.cards )
            {
            if ( aSuit == card.getSuit() )
                {
                return true ;

                }

            }

        return false ;

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

        }   // end main()

    }   // end class Hand