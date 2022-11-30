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

import java.util.ArrayList ;
import java.util.Collections ;
import java.util.List ;
import java.util.Objects ;

/**
 * Representation of a playing card with a suit and rank.
 * <p>
 * The suit and rank are immutable.
 *
 * @author Dave Rosenberg
 *
 * @version 2.0.0 2021-12-08
 *     <ul>
 *     <li>add support for face up/down
 *     <li>add {@code matches()}
 *     </ul>
 * @version 2.1.0 2022-11-06 support dynamic switching to compare cards based on suit
 *     and rank or rank alone
 *
 * @author Kai Yee
 *
 * @version 2.2.0 2022-11-15 Modifications for use for our game
 * @version 2.3.0 2022-11-20 Draft Implementation
 * @version 2.4.0 2022-11-29 Draft Implementation 2
 */
public class Card implements Comparable<Card>
    {

    // data fields
    /** The card's suit */
    public final Suit suit ;
    /** The card's rank within its suit */
    public final Rank rank ;
    /** The card's priority */
    private int priority ;
    /** If the card is playable */
    private boolean isPlayable ;

    /*
     * constructors
     */

    /**
     * @param theSuit
     *     this card's suit
     * @param theRank
     *     this card's rank
     */
    public Card( final Suit theSuit, final Rank theRank )
        {
        this.suit = theSuit ;
        this.rank = theRank ;
        this.priority = 0 ;

        }   // end 2-arg constructor

    /*
     * utility methods
     */


    /**
     * Compares two cards to see if they match, which may be different from them
     * being {@code equal()}
     * <p>
     * The {@code Pile} class requires this method.
     *
     * @param otherCard
     *     the card to compare against this card
     *
     * @return {@code true} if the cards match, {@code false} otherwise
     */
    public boolean matches( final Card otherCard )
        {
        // for now, delegate to equals() - may change based on game's requirements
        return equals( otherCard ) ;

        }  // end matches()

    /*
     * general methods
     */


    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo( final Card otherCard )
        {
        final int suitComparison = this.priority - otherCard.getPriority() ;

        // check suit first, if necessary
        if ( suitComparison != 0 )
            {
            return suitComparison ;

            }

        // either didn't need to compare suits or both cards are of the same suit
        // check rank

        return this.rank.getOrder() - otherCard.rank.getOrder() ;

        }	// end compareTo()


    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#equals(java.lang.Object)
     */
    @Override
    public boolean equals( final Object otherObject )
        {

        // same object?
        if ( this == otherObject )
            {
            return true ;

            }

        // another Card? false if otherObject is null
        if ( otherObject instanceof final Card otherCard )
            {
            return ( this.suit.equals( otherCard.suit ) &&
                     ( this.rank.getOrder() == otherCard.rank.getOrder() ) ) ;

            }

        // no match
        return false ;

        }	// end equals()


    /**
     * @return if the card is playable
     */
    public boolean getIsPlayable()
        {
        return this.isPlayable ;

        }   // end getIsPlayable()


    /**
     * @return the priority
     */
    public int getPriority()
        {
        return this.priority ;

        }   // end getPriority()


    /**
     * @return card's rank
     */
    public Rank getRank()
        {
        return this.rank ;

        }   // end getRank()


    /**
     * @return card's suit
     */
    public Suit getSuit()
        {
        return this.suit ;

        }   // end getSuit()


    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
        {
        return Objects.hash( this.suit,   // 2xCk does this work properly?
                             this.rank ) ;

        }   // end hashCode()
    
    
    /** @return if the card is a face card (Jack, Queen, King, Joker)
     */
    public boolean isFaceCard()
        {
        return this.rank == Rank.JACK ||
               this.rank == Rank.QUEEN ||
               this.rank == Rank.KING ;
        }

    /**
     * @param value
     *     to set the play-ability of the card
     */
    public void setIsPlayable( boolean value )
        {
        this.isPlayable = value ;

        }   // end setIsPlayable()


    /**
     * @param value
     *     to set the priority
     */
    public void setPriority( int value )
        {
        this.priority = value ;

        }   // end getPriority()


    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
        {
        return this.rank.toString() + this.suit.toString() ;

        }	// end toString()


    /**
     * Sample demo program
     *
     * @param args
     *     -unused-
     */
    public static void main( final String[] args )
        {
        final Suit[] suits = Suit.values() ;
        final Rank[] ranks = Rank.values() ;

        final List<Card> cards = new ArrayList<>( suits.length * ranks.length ) ;

        // generate a deck of cards
        System.out.printf( "New cards:%n" ) ;

        for ( final Suit suit : suits )
            {

            // skip placeholder suit

            for ( final Rank rank : ranks )
                {

                // skip non-playing card(s) - Joker

                // build a card
                final Card newCard = new Card( suit, rank ) ;
                System.out.printf( " %s", newCard ) ;

                // keep track of it
                cards.add( newCard ) ;

                }

            }

        // display all the cards
        System.out.printf( "%n%nAll cards:%n%s%n%n", cards.toString() ) ;

        // display all the cards
        System.out.printf( "%n%nAll cards:%n%s%n%n", cards.toString() ) ;

        // shuffled
        Collections.shuffle( cards ) ;
        System.out.printf( "%nShuffled:%n%s%n%n", cards.toString() ) ;

        // sorted
        Collections.sort( cards ) ;
        System.out.printf( "%nSorted:%n%s%n%n", cards.toString() ) ;

        // shuffled
        Collections.shuffle( cards ) ;
        System.out.printf( "%nShuffled:%n%s%n%n", cards.toString() ) ;

        // sorted
        Collections.sort( cards ) ;
        System.out.printf( "%nSorted:%n%s%n%n", cards.toString() ) ;

        // sorted
        Collections.sort( cards ) ;
        System.out.printf( "%nSorted:%n%s%n%n", cards.toString() ) ;

        // sorted
        Collections.sort( cards ) ;
        System.out.printf( "%nSorted:%n%s%n%n", cards.toString() ) ;

        // compare some cards against each other
        Card card1 = cards.get( 15 ) ;
        Card card2 = cards.get( 43 ) ;
        System.out.printf( "%s.compareTo(%s) = %,d%n", card1, card2, card1.compareTo( card2 ) ) ;

        card2 = cards.get( 4 ) ;
        System.out.printf( "%s.compareTo(%s) = %,d%n", card1, card2, card1.compareTo( card2 ) ) ;

        card2 = cards.get( 20 ) ;
        System.out.printf( "%s.compareTo(%s) = %,d%n", card1, card2, card1.compareTo( card2 ) ) ;

        System.out.printf( "%n" ) ;
        System.out.printf( "%s.equals(%s) = %b%n", card1, card1, card1.equals( card1 ) ) ;
        System.out.printf( "%s.equals(%s) = %b%n", card1, card2, card1.equals( card2 ) ) ;

        card1 = new Card( Suit.DIAMONDS, Rank.FOUR ) ;
        card2 = new Card( Suit.HEARTS, Rank.FOUR ) ;
        System.out.printf( "%s.equals(%s) = %b%n", card1, card2, card1.equals( card2 ) ) ;

        System.out.printf( "%n" ) ;

        // compare some cards against each other
        card1 = cards.get( 15 ) ;
        card2 = cards.get( 43 ) ;
        System.out.printf( "%s.compareTo(%s) = %,d%n", card1, card2, card1.compareTo( card2 ) ) ;

        card2 = cards.get( 4 ) ;
        System.out.printf( "%s.compareTo(%s) = %,d%n", card1, card2, card1.compareTo( card2 ) ) ;

        card2 = cards.get( 20 ) ;
        System.out.printf( "%s.compareTo(%s) = %,d%n", card1, card2, card1.compareTo( card2 ) ) ;

        System.out.printf( "%n" ) ;
        System.out.printf( "%s.equals(%s) = %b%n", card1, card1, card1.equals( card1 ) ) ;
        System.out.printf( "%s.equals(%s) = %b%n", card1, card2, card1.equals( card2 ) ) ;

        card1 = new Card( Suit.DIAMONDS, Rank.FOUR ) ;
        card2 = new Card( Suit.HEARTS, Rank.FOUR ) ;
        System.out.printf( "%s.equals(%s) = %b%n", card1, card2, card1.equals( card2 ) ) ;

        }	// end main()

    }	// end class Card