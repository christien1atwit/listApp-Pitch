
package edu.wit.scds.ds.list.app ;

/**
 * RoundPile is a pile tracks the tally points and the face count of cards in the
 * respective pile. While also having recording the 'properties' of the first card
 * (who dealt it, etc.), the RoundPile also tracks the dealers of the highest/lowest
 * cards in the RoundPile.
 *
 * @author Kai Yee
 *
 * @version 1.0.0 2022-11-19 Initial implementation
 * @version 1.0.1 2022-11-19 Template implementation
 * @version 1.0.2 2022-11-19 Template implementation 2
 * @version 1.1.0 2022-11-29 Draft Implementation 1
 */
public class RoundPile extends Pile
    {

    // Data fields
    /** Suit that trumps all the other suits */
    private static Suit trumpSuit ;
    /** Player who started the RoundPile */
    private Player creator ;
    /** Highest valued card in the current RoundPile */
    private Card highestCard ;
    /** Highest valued card in the pile of the trump suit */
    private Card highestTrumpCard ;
    /** Lowest valued card in the pile of the trump suit */
    private Card lowestTrumpCard ;
    /** If the Jack of the Trump suit exists in the current RoundPile */
    private boolean existsTrumpJack ;
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
        // Initializes faceCount and tallyPoints and exists Jack
        this.tallyPoints = 0 ;
        this.existsTrumpJack = false ;
        this.highestTrumpCard = null ;
        this.lowestTrumpCard = null ;
        
        }

    // API methods


    /**
     * Adds a card to the RoundPile while updating the RoundPile's variables.
     *
     * @param newCard
     *     the card that's being added to the RoundPile
     * @param distributer
     *     the player that's playing the card
     * @param distributerTeam
     *     the team that the player belongs to
     * @param round 
     *     round of set (0-5)
     */
    public void add( Card newCard,
                     Player distributer,
                     Team distributerTeam )
        {
        // If there is no Trump suit, make this card's suit be the trump suit
        if ( trumpSuit == null )
            {
            trumpSuit = newCard.getSuit() ;
            newCard.setTrumpCard( true ) ;
            
            }

        // Adds card to this pile and updates tally points and face counter
        add( newCard ) ;
        this.tallyPoints += newCard.getRank().getPoints() ;

        // if newCard is the RoundPile's first card, initialize the other variables
        if ( this.cards.size() == 1 )
            {
            this.creator = distributer ;
            this.highestCard = newCard ;
            this.owner = distributerTeam ;
            this.suitType = newCard.getSuit() ;
            
            }
        // Checks if card is the the trump suit's Jack
        if ( newCard.getSuit() == trumpSuit && newCard.getRank().getOrder() == 11 )
            {
            this.existsTrumpJack = true ;
            
            }
        
        // Sets the priorities of the card before comparison
        if ( trumpSuit == newCard.getSuit() )
            {
            newCard.setPriority( 2 ) ;

            }
        else if ( this.suitType == newCard.getSuit() )
            {
            newCard.setPriority( 1 ) ;

            }
        else
            {
            newCard.setPriority( 0 ) ;

            }

        // compare the card with the highest one in current RoundPile
        if ( this.highestCard.getRank().getOrder() < newCard.getRank().getOrder() )
            {
            // Sets the new highest card and owner if newCard is higher
            this.highestCard = newCard ;
            if( this.highestTrumpCard == null )
                {
                this.owner = distributerTeam ;
                this.creator = distributer ;
                }
            
            }
        
        // If card has the same suit as the trumpSuit
        if ( newCard.getPriority() == 2 )
            {
            // Initialize highest and lowest Trump cards if card is first of the trump suit of current RoundPile
            if ( this.highestTrumpCard == null )
                {
                this.highestTrumpCard = newCard ;
                this.lowestTrumpCard = newCard ;
                this.creator = distributer ;
                this.owner = distributerTeam ;
                
                }
            // replace card as the new highest card of trumpSuit if true
            else if ( this.highestTrumpCard.getRank().getOrder() < newCard.getRank().getOrder() )
                {
                // Sets the new lowest card and owner if newCard is lower
                this.highestTrumpCard = newCard ;
                this.creator = distributer ;
                this.owner = distributerTeam ;
    
                }
            
            // replace card as the new lowest card of trumpSuit if true
            else if ( this.lowestTrumpCard.getRank().getOrder() > newCard.getRank().getOrder() )
                {
                // Sets the new lowest card and owner if newCard is lower
                this.lowestTrumpCard = newCard ;
    
                }
            }

        }   // end add()


    /** @return The first distributer to the RoundPile */
    public Player getCreator()
        {
        return this.creator ;

        }   // end getCreator()


    /** @return Highest Card in RoundPile */
    public Card getHighestCard()
        {
        return this.highestCard ;

        }   // end getHighestCard()

    /** @return Highest Trump Card in RoundPile */
    public Card getHighestTrumpCard()
        {
        return this.highestTrumpCard ;

        }   // end getHighestTrumpCard()
    
    /** @return Lowest Trump Card in RoundPile */
    public Card getLowestTrumpCard()
        {
        return this.lowestTrumpCard ;

        }   // end getLowestTrumpCard()


    /**
     * @return the current owner of the RoundPile according to the team that
     *     distributed the RoundPile's current highest card
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
    
    /** @return Gets the trump suit of all RoundPiles */
    public static Suit getTrumpSuit()
        {
        return trumpSuit ;

        }   // endgetTrumpSuit()

    /** Resets the trump suit of all RoundPiles */
    public static void resetTrumpSuit()
        {
        trumpSuit = null ;

        }   // end resetTrumpSuit()


    /**
     * @return Boolean that indicates if the current RoundPile has TrumpJack
     */
    public boolean existsTrumpJack()
        {
        return this.existsTrumpJack ;

        }   // end existsTrumpJack()
    

    }
// end class RoundPile