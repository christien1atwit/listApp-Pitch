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
 * An enumeration of card ranks.
 *
 * @author David M Rosenberg
 *
 * @version 1.0.0 2016-03-16 initial version
 * @version 1.1.0 2022-11-06 add switches for standard vs alternate points and order
 *
 * @author Kai Yee
 *
 * @version 1.2.0 2022-11-15 Modifications for use for our game
 * @version 1.3.0 2022-11-20 Draft Implementation
 */
public enum Rank
    {

// @formatter:off
//  Element     Display Name    Graphic     Points      Order 
    /** Ace */
    ACE     (   "Ace",          "A",        4,          14 ),
    /** Two */
    TWO     (   "Duece",        "2",        0,          2 ),
    /** Three */
    THREE   (   "Three",        "3",        0,          3 ),
    /** Four */
    FOUR    (   "Four",         "4",        0,          4 ),
    /** Five */
    FIVE    (   "Five",         "5",        0,          5 ),
    /** Six */
    SIX     (   "Six",          "6",        0,          6 ),
    /** Seven */
    SEVEN   (   "Seven",        "7",        0,          7 ),
    /** Eight */
    EIGHT   (   "Eight",        "8",        0,          8 ),
    /** Nine */
    NINE    (   "Nine",         "9",        0,          9 ),
    /** Ten */
    TEN     (   "Ten",          "10",       10,         10 ),
    /** Jack */
    JACK    (   "Jack",         "J",        1,         11 ),
    /** Queen */
    QUEEN   (   "Queen",        "Q",        2,         12 ),
    /** King */
    KING    (   "King",         "K",        3,         13 )
    ;
// @formatter:on

    // data fields
    /** 'pretty' name for the rank */
    private final String displayName ;
    /** graphic representation of the rank */
    private final String graphic ;
    /** points for a card of this rank */
    private final int points ;
    /** sort order */
    private final int order ;


    /*
     * constructor
     */


    /**
     * @param rankDisplayName
     *     more esthetically pleasing for display
     * @param rankGraphic
     *     the 'standard' icon
     * @param rankPoints
     *     numeric value for the card
     * @param rankOrder
     *     numeric order for the card
     */
    private Rank( final String rankDisplayName,
                  final String rankGraphic,
                  final int rankPoints,
                  final int rankOrder )
        {
        this.displayName = rankDisplayName ; 
        this.graphic = rankGraphic ;
        this.points = rankPoints ;
        this.order = rankOrder ; 

        } // end 4-args constructor


    /*
     * getters
     */


    /**
     * @return the display name
     */
    public String getDisplayName()
        {
        return this.displayName ;

        } // end getDisplayName()


    /**
     * @return the graphic/icon
     */
    public String getGraphic()
        {
        return this.graphic ;

        } // end getGraphic()


    /**
     * @return the order
     */
    public int getOrder()
        {
        return this.order ;

        } // end getOrder()


    /**
     * @return the point value
     */
    public int getPoints()
        {
        return this.points ;

        } // end getPoints()


    /*
     * utility methods
     */


    /**
     * For display, use the graphic/icon
     */
    @Override
    public String toString()
        {
        return this.graphic ;

        }	// end toString()


    /**
     * Test driver
     *
     * @param args
     *     -unused-
     */
    public static void main( final String[] args )
        {
        // display column headers
        System.out.printf( "%-5s %-8s %-8s %-15s %-15s %-6s   %-10s%n",
                           "#",
                           "Rank",
                           "Graphic",
                           "Name",
                           "Display Name",
                           "Points",
                           "Alt Points",
                           "Order",
                           "Alt Order" ) ;

        // display each element of the enumeration
        for ( final Rank aRank : Rank.values() )
            {
            System.out.printf( "%-5d %-8s %-8s %-15s %-15s %-6d   %-10d%n",
                               aRank.ordinal(),
                               aRank,
                               aRank.graphic,
                               aRank.name(),
                               aRank.displayName,
                               aRank.points,
                               aRank.order ) ;

            }	// end for

        }	// end main()

    } // end enum Rank