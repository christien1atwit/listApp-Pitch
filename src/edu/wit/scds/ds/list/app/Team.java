
package edu.wit.scds.ds.list.app ;

import java.util.List ;

/**
 * @author Your Name // William Sten
 *
 * @version 1.0.0 2022-11-19 Initial implementation
 * @version 1.0.1 2022-11-19 Template implementation
 * @version 1.0.2 2022-11-19 Template implementation 2
 */
public class Team
    {

    // DONE implement this

    // Data fields
    private Player[] players ;
    private List<RoundPile> roundPiles ;
    private int tallyPoints ;
    private int score ;
    private Card highestCard ;
    private Card lowestCard ;
    private RoundPile highestRoundPile ;
    private RoundPile lowestRoundPile ;

    /**
     * @param player1
     *     2 players on each team
     * @param player2
     *     adds player1 and player2 to team
     */
    // Constructor
    public Team( Player player1, Player player2 )
        {
        // Initializes the variables, and adds two players to the players array.
        // DONE implement this
        this.players[ 0 ] = player1 ;
        this.players[ 1 ] = player2 ;
        this.tallyPoints = 0 ;
        this.score = 0 ;

        }   // end 2-args constructor


    /**
     * @param aRoundPile
     *     tallys points and returns roundPiles
     */
    public void addRoundPile( RoundPile aRoundPile )
        {
        /*
         * When adding a RoundPile, get the tally points of the RoundPile, and add
         * the points to the score- If the number of face cards of the RoundPile
         * matters you have access to that too. Of course, add the RoundPile
         * afterwards.
         */
        // DONE implement this
        // Adds tally points to the total tally points a team has if they won the
        // round
        // adds the cards from the roundpile to that teams pool of won round piles
        // Does not need number of face cards, since the points of associated ranks
        // should
        // be tallied in aRoundPile

        this.tallyPoints += aRoundPile.getTallyPoints() ;
        this.roundPiles.add( aRoundPile ) ;
        // If this is the first RoundPile:
        if ( this.roundPiles.size() == 1 )
            {
            this.highestCard = aRoundPile.getHighestCard() ;
            this.lowestCard = aRoundPile.getLowestCard() ;
            this.highestRoundPile = aRoundPile ;
            this.lowestRoundPile = aRoundPile ;
            return ;

            }

        // If this isn't the first RoundPile, compare RoundPile's cards
        if ( this.highestCard.compareTo( aRoundPile.getHighestCard() ) < 0 )
            {
            // Sets the new highest card and owner if newCard is higher
            this.highestCard = aRoundPile.getHighestCard() ;
            this.highestRoundPile = aRoundPile ;

            }

        if ( this.lowestCard.compareTo( aRoundPile.getLowestCard() ) > 0 )
            {
            // Sets the new lowest card and owner if newCard is lower
            this.lowestCard = aRoundPile.getLowestCard() ;
            this.lowestRoundPile = aRoundPile ;

            }

        }   // end addRoundPile()


    /**
     * @param value
     *     adds value to team score
     */
    public void addScore( int value )
        {
        // DONE implement this
        this.score += value ;

        }   // end addScore()


    /**
     * @param deck
     *     deals each card in each RoundPile back to a deck
     */
    public void dealBack( Deck deck )
        {
        for ( RoundPile roundPile : this.roundPiles )
            {
            for ( int i = 0 ; i < 4 ; i++ )
                {
                deck.add( roundPile.remove() ) ;

                }

            }

        }   // end dealBack()


    /**
     * @return the highest card out of all of the RoundPiles in the current team
     */
    public Card getHighestCard()
        {
        return this.highestCard ;

        }   // end HighestCard()


    /**
     * @return the RoundPile that's responsible for the highest card in the current
     *     Team
     */
    public RoundPile getHighestRoundPile()
        {
        return this.highestRoundPile ;

        }   // end HighestRoundPile()


    /**
     * @return the lowest card out of all of the RoundPiles in the current team
     */
    public Card getLowestCard()
        {
        return this.lowestCard ;

        }   // end LowestCard()


    /**
     * @return the RoundPile that's responsible for the lowest card in the current
     *     Team
     */
    public RoundPile getLowestRoundPile()
        {
        return this.lowestRoundPile ;

        }   // end lowestRoundPile()


    /**
     * @return players on a team
     */
    public Player[] getPlayers()
        {
        // DONE implement this
        return this.players ;

        }   // end getPlayers()


    /**
     * @return teams current score
     */
    public int getScore()
        {
        // DONE implement this
        return this.score ;

        }   // end getScore()


    /** @return tallyPoints of current team */
    public int getTallyPoints()
        {
        return this.tallyPoints ;

        }


    /**
     * refreshes tallyPoints to 0
     */
    public void refresh()
        {
        /*
         * Refreshes every variable except players
         */
        // DONE implement this
        this.tallyPoints = 0 ;

        }   // end refresh()

    }
// end class Team