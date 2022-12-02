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

import java.awt.Color ;
import java.awt.Component ;
import java.io.File ;
import java.util.ArrayList ;
import java.util.Collections ;
import java.util.LinkedList ;
import java.util.List ;
import java.util.Objects ;
import java.util.Queue ;

import javax.swing.JButton ;
import javax.swing.JFrame ;

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
public class Card extends Entity implements Comparable<Card>
    {
    // data fields
    private static final String FILE_PATH = "./data/cards/" ;
    /** The final width of the card */
    private static final int WIDTH = 110 ;
    /** The final height of the card */
    private static final int HEIGHT = 150 ;
    /** GUI Component that's added if card is unplayable */
    private static final Rectangle UNPLAYABLE_CARD = new Rectangle( new Color( 0, 0, 0, 160 ),
                                                                    0,
                                                                    0,
                                                                    WIDTH + 1,
                                                                    HEIGHT + 1 ) ;
    /** The card's suit */
    public final Suit suit ;
    /** The card's rank within its suit */
    public final Rank rank ;
    /** The card's priority */
    private int priority ;
    /** If the card is playable */
    private boolean isPlayable ;

    /*
     * Constructor 
     */
    
    /**
     * @param theSuit
     *     this card's suit
     * @param theRank
     *     this card's rank
     */
    public Card( final Suit theSuit, final Rank theRank )
        {
        // Initializes the variables
        this.suit = theSuit ;
        this.rank = theRank ;
        this.priority = 0 ;
        // initializes a visual Component class that simulates the card's suit
        // and rank
        this.components.add( new Image( new File( FILE_PATH + this.suit.getGraphic() +
                                                  this.rank.getGraphic() + ".jpg" ),// ♠A
                                        0,
                                        0,
                                        WIDTH,
                                        HEIGHT ) ) ;
        this.components.add( UNPLAYABLE_CARD ) ;
        setX( -WIDTH ) ;
        setY( -HEIGHT ) ;

        }   // end 2-arg constructor
    
    /*
     * utility methods
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


    /**
     * The card comes from the right of the screen to its spot according to
     * ordernumber and has interactive functionality depending on if it's playable;
     * when clicked, the card will try to add itself to the roundPile and removes
     * such option from the rest of the cards for that turn. . RoundPile
     *
     * @param orderNumber
     *     of the card in its hand to organize physical slot accordingly
     * @param chosenCard
     *     variable to be changed when current card is chosen
     * @param player
     *     player displaying the hand / Thread object
     * @param roundPile
     *     a RoundPile that the card can be played into
     * @param frame
     *     to add a component that makes the card interactive
     */
    public void displayCardInHand( final int orderNumber,
                                   int[] chosenCard,
                                   Player player,
                                   RoundPile roundPile,
                                   JFrame frame )
        {
        // sets the position that the card will end up
        this.x = 40 + ( 120 * orderNumber ) ;
        this.y = 550 ;

        // uses many Transform to simulate an animation for these moving cards
        Queue<Transform> result = new LinkedList<>() ;
        for ( int i = -10 ; i <= 0 ; i += 1 )
            {
            result.add( new Transform( 0,
                                       0,
                                       1,
                                       1,
                                       i,
                                       getX() + ( WIDTH / 2 ),
                                       ( getY() + ( HEIGHT / 2 ) ) - 8000 ) ) ;

            }

        this.animation.add( result ) ;

        // Gives the illusion that the card can be pressed/clicked/interacted by
        // creating overlapping button
        JButton interactiveCard = new JButton() ;
        interactiveCard.setBounds( this.x, this.y, WIDTH, HEIGHT ) ;
        interactiveCard.setName( "cardButton" ) ;
        // Makes button completely invisible
        interactiveCard.setBackground( new Color( 0, 0, 0, 0 ) ) ;
        interactiveCard.setBorder( null ) ;
        interactiveCard.setOpaque( false ) ;
        interactiveCard.setContentAreaFilled( false ) ;
        // Adds listener to make button or 'card' interactive if it's playable
        if ( this.isPlayable )
            {
            interactiveCard.addActionListener( e ->
                {

                    {
                    // Plays card if the button on top of the card is clicked
                    play( roundPile.getNumberOfCards() ) ;

                    chosenCard[ 0 ] = orderNumber ;

                    // Sets card to the top of the GUI table hierarchy
                    GUIHandler.toTop( this ) ;

                    // Removes any buttons on window

                    Component[] frameComponents = frame.getRootPane().getComponents() ;
                    for ( int i = frameComponents.length - 1 ; i >= 0 ; i-- )
                        {
                        Component currentComponent = frameComponents[ i ] ;
                        // If button 'belongs' to a card, delete the button/ delete
                        // the
                        // rest of interactive cards
                        if ( ( currentComponent instanceof JButton currentButton ) &&
                             "cardButton".equals( currentButton.getName() ) )
                            {
                            currentButton.removeActionListener( currentButton.getActionListeners()[ 0 ] ) ;
                            frame.getRootPane().remove( currentComponent ) ;

                            }

                        // notify that the player has chose to play a card
                        synchronized ( player )
                            {
                            player.notifyAll() ;

                            }

                        }   // end for loop */

                    }

                } ) ;   // end ActionListener

            }

        // add the interactive feature to the window
        frame.getRootPane().add( interactiveCard, 12 ) ;

        }


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


    /**
     * Goes to the destination of the team that won this card in a neat manner and
     * has its own designated slot depending on the three parameters.
     *
     * @param teamNumber
     *     the team number that won this card
     * @param stackNumber
     *     is the order in the stack that the card belongs to
     * @param roundsWon
     *     the number of RoundPiles that the team won
     */
    public void goToTeam( int teamNumber,
                          int stackNumber,
                          int roundsWon )
        {

        if ( teamNumber == 1 )
            {
            moveTo( 10 + ( roundsWon * 12 ), 100 + ( stackNumber * 40 ) + ( roundsWon * 2 ) ) ;

            }

        if ( teamNumber == 2 )
            {
            moveTo( 520 + ( roundsWon * 12 ), 100 + ( stackNumber * 40 ) + ( roundsWon * 2 ) ) ;

            }

        }   // end goToTeam()


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


    /**
     * Card goes to the left of the screen from its current slot.
     */
    public void hideCard()
        {
        Queue<Transform> result = new LinkedList<>() ;

        // Moves card to the left side of the screen
        for ( int i = 0 ; i <= 10 ; i++ )
            {
            result.add( new Transform( 0,
                                       0,
                                       1,
                                       1,
                                       i,
                                       getX() + ( WIDTH / 2 ),
                                       ( getY() + ( HEIGHT / 2 ) ) - 8000 ) ) ;

            }

        // sets a final position offscreen to make the card seem like it disappeared
        result.add( new Transform( -500, -500, true ) ) ;
        this.animation.add( result ) ;

        }   // end hideCard()


    /**
     * @return if the card is a face card (Jack, Queen, King, Joker)
     */
    public boolean isFaceCard()
        {
        return ( this.rank == Rank.JACK ) || ( this.rank == Rank.QUEEN ) ||
               ( this.rank == Rank.KING ) ;

        }


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


    /**
     * Moves card from wherever its located to the slot of the screen that is
     * designated as the RoundPile
     *
     * @param stackNumber
     *     is the order in the stack that the card belongs to
     */
    public void play( int stackNumber )
        {
        moveTo( 340, 220 + ( stackNumber * 40 ) ) ;

        }   // end play()


    /**
     * @param value
     *     to set the play-ability of the card
     */
    public void setIsPlayable( boolean value )
        {
        this.isPlayable = value ;
        // If true, remove the component that indicates that the card is unplayable
        if ( value && this.components.contains( UNPLAYABLE_CARD ) )
            {
            this.remove( UNPLAYABLE_CARD ) ;

            }

        // If false, add the component that indicates that the card is unplayable
        if ( !value && !this.components.contains( UNPLAYABLE_CARD ) )
            {
            this.add( UNPLAYABLE_CARD ) ;

            }

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

    /*
     * Private utility methods
     */

    /**
     * Uses/creates many Transform classes to simulate an animation that moves the
     * entity from one place to another.
     *
     * @param destinationX
     *     x-coordinate that the card moves to
     * @param destinationY
     *     y-coordinate that the card moves to
     */
    private void moveTo( int destinationX,
                         int destinationY )
        {
        Queue<Transform> result = new LinkedList<>() ;
        // Takes 5 frames to move from current coordinates to the destination
        for ( int i = 0 ; i <= 5 ; i++ )
            {
            result.add( new Transform( (int) ( ( i / 5.0 ) * ( ( getX() * -1 ) + destinationX ) ),
                                       (int) ( ( i / 5.0 ) * ( ( getY() * -1 ) + destinationY ) ),
                                       1,
                                       1 ) ) ;

            }

        // Sets the destination coordinates after the card is done moving
        result.add( new Transform( destinationX, destinationY, true ) ) ;

        this.animation.add( result ) ;

        }   // end moveTo()


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