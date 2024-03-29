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

import javax.swing.JFrame ;

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

        // If roundPile is empty/doesn't have a suit, set all cards to playable
        if ( aRoundPile.isEmpty() )
            {
            for ( Card card : this.cards )
                {
                card.setIsPlayable( true ) ;

                }

            return ;

            }

        // If hand has any cards that is the same Suit as the roundPile:
        Suit roundPileSuit = aRoundPile.getSuitType() ;
        if ( containsSuit( roundPileSuit ) )
            {
            // Iterate through cards
            for ( Card card : this.cards )
                {
                // If card is the same suit with RoundPile or Trump Card, make it
                // playable
                if ( ( roundPileSuit == card.getSuit() ) ||
                     ( RoundPile.getTrumpSuit() == card.getSuit() ) )
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
     * Displays all the cards currently in hand
     *
     * @param chosenCard
     *     variable that indicates the index of the card chosen (in array to be
     *     modifiable)
     * @param player
     *     displaying the hand / Thread object
     * @param roundPile
     *     RoundPile that any one of the cards can be dealt to
     * @param frame
     *     Window to add each card's interactive options
     */
    public void displayHand( int[] chosenCard,
                             Player player,
                             RoundPile roundPile,
                             JFrame frame )
        {
        for ( int i = 0 ; i < this.cards.size() ; i++ )
            {
            DisplayHandler.add( this.cards.get( i ) ) ;
            this.cards.get( i ).displayCardInHand( i, chosenCard, player, roundPile, frame ) ;

            }

        }   // end displayHand()


    /**
     * Hides all the cards currently in hand
     */
    public void hideHand()
        {
        for ( Card element : this.cards )
            {
            element.hideCard() ;

            }

        }   // end hideHand()


    /**
     * @param index
     *     index of the card to play in this.hand
     * @param distributer
     *     player playing card
     * @param distributerTeam
     *     team that the player belongs to
     * @param aRoundPile
     *     checks if hand has playable cards of suit if hand hasSuit, then it will
     *     check the card attempting to be played if card is of suit it will return
     *     true if not it will return false and nothing will be added to round pile
     * @param round
     *     round of set (0-5)
     *
     * @return true or false depending on if a card is playable
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
            aRoundPile.add( this.cards.get( index ), distributer, distributerTeam ) ;
            this.cards.remove( index ) ;
            this.numberOfCards-- ;
            return true ;

            } // end if

        // if hand does not have RoundSuit, then you can add any card, therefore
        // playCard will always be true
        // aRoundPile.add( this.currentCard, null ) ;
        return false ;

        }   // end playCard()


    /** Sets all of the cards in hand as unplayable */
    public void setUnplayable()
        {
        for ( Card card : this.cards )
            {
            card.setIsPlayable( false ) ;

            }

        }   // setUnplayable()

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