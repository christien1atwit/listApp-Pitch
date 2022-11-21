
package edu.wit.scds.ds.list.app;


/**
 * 
 * @author Your Name // TODO
 *
 * @version 1.0.0 2022-11-19 Initial implementation
 * @version 1.0.1 2022-11-19 Template implementation
 * @version 1.0.2 2022-11-19 Template implementation 2
 *
 */
public class RoundPile
    {
    
    // TODO implement this
    
    // Data fields
    /** Player who started the RoundPile */
    private Player creator ;
    /** Number of face cards in the list */
    private int faceCount ;
    /** Highest valued card in the pile */
    private Card highestCard ;
    /** Current owner of the RoundPile */
    private Team owner ;
    /** Official suitType of the RoundPile */
    private Suit suitType ;
    /** All of the points of the Cards from this Pile */
    private int tallyPoints ;
    
    // Constructor
    
    public RoundPile()
        {
        // Initializes faceCount and tallyPoints
        // TODO implement this
        
        }
    
    // API methods

    public void add( Card newCard, Player distributer )
        {
        /*
         * Very important method.
         * If card list is empty: 
         *   Initialize the rest of the variables with the given args. 
         *   This includes creator, highestCard, owner, and suitType.
         * 
         * If the card list isn't empty: 
         *   Compares the card's suit(name) with the suitType:
         *        If they are the same suit(name), set the card's suit's priority to 1.
         *        If they aren't the same suit(name), set the card's suit's priority to 0.
         *        ( You can access priority through newCard.setPriority() )
         *   Afterwards, compare the newCard with the highestCard. 
         *   If newCard is better/higher:
         *          set the newCard as the highestCard and
         *          set the owner from the player's team.
         *  
         * Finally, add the card to the list using the add() from the superclass,
         * then increment the faceCount and tallyPoints accordingly.
         * TallyPoints of a card can be found through ( newCard.rank.getPoints() ).
         * 
         */
        
        // TODO implement this
        
        }   // end add()
    
    public Player getCreator()
        {
        // TODO implement this
        
        }   // end getCreator()
    
    public int getFaceCount()
        {
        // TODO implement this
        
        }   // end getFaceCount()
    
    public Team getOwner()
        {
        // TODO implement this
        
        }   // end getOwner()
    
    public Suit getSuitType()
        {
        // TODO implement this
        
        }   // end getSuitType()
    
    public int getTallyPoints()
        {
        // TODO implement this
        
        }   // end getTallyPoints()
    
    }
   // end class RoundPile