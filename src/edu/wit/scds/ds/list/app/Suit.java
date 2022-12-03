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
 * An enumeration of card suits. (Listing C-2 of Appendix C.)
 * <p>
 * You may want/need to adjust the priorities for your game. As provided, suits are ordered by
 * priority:<br>
 *
 * <pre>
 * Spades (highest) -> Diamonds -> Hearts -> Clubs -> none (lowest)
 * </pre>
 *
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 *
 * @version 4.0
 *
 * @author David M Rosenberg
 *
 * @version 4.1.0 2016-03-16
 *     <ul>
 *     <li>enhanced definition: added display name and graphic
 *     <li>added test driver main()
 *     </ul>
 * @version 4.1.1 2021-11-19
 *     <ul>
 *     <li>fill in Javadoc comments
 *     <li>add Comparator/compare()
 *     </ul>
 * @version 4.2.0 2022-11-06 add switch for standard vs alternate priority
 *
 * @author Kai Yee
 *
 * @version 4.3.0 2022-11-15 Modifications for use for our game
 * @version 4.4.0 2022-11-20 Draft Implementation
 */
public enum Suit
    {

// @formatter:off
//  Element         Display Name    Graphic 
    /** Spades suit */
    SPADES      (   "Spades",       "♠" ),
    /** Diamonds suit */
    DIAMONDS    (   "Diamonds",     "♦" ),
    /** Hearts suit */
    HEARTS      (   "Hearts",       "♥" ),
    /** Clubs suit */
    CLUBS       (   "Clubs",        "♣" )
    ;
// @formatter:on

    // data fields
    /** 'pretty' name for the suit */
    private final String displayName ;
    /** graphic representation of the suit */
    private final String graphic ;
    
    /**
     * @param suitDisplayName
     *     more esthetically pleasing for display
     * @param suitGraphic
     *     the 'standard' icon for the suit
     */
    private Suit( final String suitDisplayName,
                  final String suitGraphic )
        {
        this.displayName = suitDisplayName ;
        this.graphic = suitGraphic ;

        }   // end constructor


    /**
     * @return the display name
     */
    public String getDisplayName()
        {
        return this.displayName ;

        }   // end getDisplayName()


    /**
     * @return the graphic/icon
     */
    public String getGraphic()
        {
        return this.graphic ;

        }   // end getGraphic()


    /*
     * utility methods
     */


    /**
     * For display, use the graphic/icon
     */
    @Override
    public String toString()
        {
        return this.displayName ;

        }   // end toString()


    /**
     * Test driver
     *
     * @param args
     *     -unused-
     */
    public static void main( final String[] args )
        {
        // display column headers
        System.out.printf( "%-5s %-8s %-8s %-15s %-15s %-10s%n",
                           "#",
                           "Suit",
                           "Graphic",
                           "Name",
                           "Display Name",
                           "Color" ) ;

        // display each element of the enumeration
        for ( final Suit aSuit : Suit.values() )
            {
            System.out.printf( "%-5d %-8s %-8s %-15s %-15s %-10s%n",
                               aSuit.ordinal(),
                               aSuit,
                               aSuit.graphic,
                               aSuit.name(),
                               aSuit.displayName ) ;
            }	// end for

        }	// end main()

    }   // end enum Suit