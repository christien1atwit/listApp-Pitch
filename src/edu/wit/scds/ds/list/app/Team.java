
package edu.wit.scds.ds.list.app ;

import java.util.ArrayList ;
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
    private Card highestTrumpCard ;
    private Card lowestTrumpCard ;
    private boolean hasTrumpJack ;


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
        this.players = new Player[2] ;
        this.players[ 0 ] = player1 ;
        this.players[ 1 ] = player2 ;
        this.tallyPoints = 0 ;
        this.score = 0 ;
        this.roundPiles = new ArrayList<>() ;
        this.hasTrumpJack = false ;

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
        
        // if RoundPile has the trump Jack, update field
        if ( aRoundPile.existsTrumpJack() )
            {
            this.hasTrumpJack = true ;
            
            }

        
        // If the RoundPile doesn't have a card of the same suit as the trump card, skip comparisons
        if ( aRoundPile.getHighestTrumpCard() == null )
            {
            return ;
            
            }
        
        // Initialize highest and lowest Trump cards if the RoundPile has the team's first 
        if ( this.highestTrumpCard == null )
            {
            this.highestTrumpCard = aRoundPile.getHighestTrumpCard() ;
            this.lowestTrumpCard = aRoundPile.getLowestTrumpCard() ;
            
            }
        // replace card as the new highest card of trumpSuit if true
        else if ( ( this.highestTrumpCard.compareTo( aRoundPile.getHighestTrumpCard() ) > 0 ) )
            {
            // Sets the new lowest card and owner if newCard is lower
            this.highestTrumpCard = aRoundPile.getHighestTrumpCard() ;

            }
        
        // replace card as the new lowest card of trumpSuit if true
        if ( ( this.lowestTrumpCard.compareTo( aRoundPile.getLowestTrumpCard() ) > 0 ) )
            {
            // Sets the new lowest card and owner if newCard is lower
            this.lowestTrumpCard = aRoundPile.getLowestTrumpCard() ;

            }
            
        

        }   // end addRoundPile()
    
    /** @param value to add to to tally points */
    public void addTallyPoints( int value )
        {
        this.tallyPoints += value ;

        }

    /**
     * @param value
     *     adds value to team score
     */
    public void addScore( int value )
        {
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
     * @return the highest trump card out of all of the RoundPiles in the current team
     */
    public Card getHighestTrumpCard()
        {
        return this.highestTrumpCard ;

        }   // end HighestTrumpCard()


    /**
     * @return the lowest trump card out of all of the RoundPiles in the current team
     */
    public Card getLowestTrumpCard()
        {
        return this.lowestTrumpCard ;

        }   // end LowestTrumpCard()


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

        }   // end getTallyPoints()
    
    /** @return true if the current team has the trump jack */
    public boolean getHasTrumpJack()
        {
        return this.hasTrumpJack ;
        
        }   // end getHasTrumpJack()


    /**
     * refreshes tallyPoints to 0
     */
    public void refresh()
        {
        /*
         * Refreshes every variable except players and score
         */
        // DONE implement this
        this.tallyPoints = 0 ;
        this.roundPiles = new ArrayList<>() ;
        this.hasTrumpJack = false ;

        }   // end refresh()

    }
// end class Team