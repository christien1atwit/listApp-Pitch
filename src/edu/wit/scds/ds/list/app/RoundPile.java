
package edu.wit.scds.ds.list.app ;

/**
 * @author Kai Yee
 *
 * @version 1.0.0 2022-11-19 Initial implementation
 * @version 1.0.1 2022-11-19 Template implementation
 * @version 1.0.2 2022-11-19 Template implementation 2
 * @version 1.1.0 2022-11-29 Draft Implementation 1
 */
public class RoundPile extends Pile
    {

    // TODO implement this

    // Data fields
    /** Player who started the RoundPile */
    private Player creator ;
    /** Number of face cards in the list */
    private int faceCount ;
    /** Highest valued card in the pile */
    private Card highestCard ;
    /** Lowest valued card in the pile */
    private Card lowestCard ;
    /** Current owner of the RoundPile */
    private Team owner ;
    /** Official suitType of the RoundPile */
    private Suit suitType ;
    /** All of the points of the Cards from this Pile */
    private int tallyPoints ;

    // Constructor
    /**
     * Initializes the pile's face and tally counter for cards
     */
    public RoundPile()
        {
        // Initializes faceCount and tallyPoints
        // TODO implement this
        this.faceCount = 0 ;
        this.tallyPoints = 0 ;

        }

    // API methods

    /**
     * Adds a card to the RoundPile while updating the RoundPile's
     * variables.
     * 
     * @param newCard the card that's being added to the RoundPile
     * @param distributer the player that's playing the card
     * @param distributerTeam the team that the player belongs to
     */
    public void add( Card newCard,
                     Player distributer,
                     Team distributerTeam )
        {
        // Adds card to this pile and updates tally points and face counter
        add( newCard ) ;
        this.tallyPoints += newCard.rank.getPoints() ;

        if ( newCard.isFaceCard() )
            {
            this.faceCount++ ;

            }

        // if newCard is the RoundPile's first card, initialize the other variables
        if ( this.cards.size() == 1 )
            {
            this.creator = distributer ;
            this.highestCard = newCard ;
            this.lowestCard = newCard ;
            this.owner = distributerTeam ;
            this.suitType = this.highestCard.suit ;
            newCard.setPriority( 1 ) ;

            return ;

            }
        
        // if newCard isn't the first card, compare the card with the highest one
        if ( this.suitType == newCard.suit )
            {
            newCard.setPriority( 1 ) ;

            }
        else
            {
            newCard.setPriority( 0 ) ;

            }

        if ( this.highestCard.compareTo( newCard ) < 0 )
            {
            // Sets the new highest card and owner if newCard is higher
            this.highestCard = newCard ;
            this.owner = distributerTeam ;

            }
        
        if ( this.lowestCard.compareTo( newCard ) > 0 )
            {
            // Sets the new highest card and owner if newCard is higher
            this.lowestCard = newCard ;

            }

        /*
         * Very important method. If card list is empty: Initialize the rest of the
         * variables with the given args. This includes creator, highestCard, owner,
         * and suitType. If the card list isn't empty: Compares the card's suit(name)
         * with the suitType: If they are the same suit(name), set the card's suit's
         * priority to 1. If they aren't the same suit(name), set the card's suit's
         * priority to 0. ( You can access priority through newCard.setPriority() )
         * Afterwards, compare the newCard with the highestCard. If newCard is
         * better/higher: set the newCard as the highestCard and set the owner from
         * the player's team. Finally, add the card to the list using the add() from
         * the superclass, then increment the faceCount and tallyPoints accordingly.
         * TallyPoints of a card can be found through ( newCard.rank.getPoints() ).
         */

        // TODO implement this

        }   // end add()

    /** @return The first distributer to the RoundPile */
    public Player getCreator()
        {
        return this.creator ;

        }   // end getCreator()

    /** @return how many cards in RoundPile are face cards */
    public int getFaceCount()
        {
        return this.faceCount ;

        }   // end getFaceCount()
    
    /** @return Highest Card in RoundPile */
    public Card getHighestCard()
        {
        return this.highestCard ;

        }   // end getHighestCard()
    
    /** @return Lowest Card in RoundPile */
    public Card getLowestCard()
        {
        return this.lowestCard ;

        }   // end getHighestCard()
    
    /** 
     * @return the current owner of the RoundPile according to 
     * the team that distributed the RoundPile's current 
     * highest card
     */
    public Team getOwner()
        {
        return this.owner ;

        }   // end getOwner()

    /** @return RoundPile's first/primary suit */
    public Suit getSuitType()
        {
        return this.suitType ;

        }   // end getSuitType()


    /** @return tally points */
    public int getTallyPoints()
        {
        return this.tallyPoints ;

        }   // end getTallyPoints()

    }
// end class RoundPile