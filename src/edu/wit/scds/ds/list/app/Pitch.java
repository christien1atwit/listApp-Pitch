
package edu.wit.scds.ds.list.app;


/**
 * 
 * @author Your Name // TODO
 *
 * @version 1.0.0 2022-11-19 Initial implementation
 * @version 1.0.1 2022-11-19 Template implementation
 *
 */
public class Pitch
    {

    // TODO implement this
    
    // Data fields
    private Player currentPlayer ;
    private RoundPile currentRoundPile ;
    // I have no idea what gameScore is, but if you need it, here it is.
    private int gameScore ;
    private Team[] teams ;
    
    // main
    
    public static void main( final String[] args )
        {
        /*
         * This class is crucial- without it- there would be no game.
         * You need to initialize all of the data fields- from there,
         * I can only offer abstract advice since I'm not very aware of
         * what the game is supposed to be. The rest of the classes were designed
         * to only handle one game at runtime, but if you really want to 
         * redesign other classes to play multiple games during runtime, 
         * the revisions can be made with relative ease.
         * Vague Instructions:
         * 
         * Deck- Deal.
         * Forever While loop:
         *  For loop that iterates through each player in teams-
         *  player order shouldn't have players of the same team go twice in a row:
         *      Set currentPlayer to the player going.
         *      if currentRoundPile is empty:
         *          accesses and displays player's hand
         *          gives opportunity for player to pass or bet
         *          while loop a playCard from hand until user puts a valid card.
         *      if currentRoundPile isn't empty:
         *          Show the currentRoundPile.
         *          checkPlayableCards of Hand with the currentRoundPile.
         *          display hand and emphasize playable cards.
         *          while loop a playCard from hand until user puts a valid card.
         *      if currentRoundPile has 4 cards:
         *          Get the creator of the RoundPile and add scores to teams if bet is successful.
         *          Get the owner of the RoundPile, and add it to the team.
         *          set the currentRoundPile to a new fresh one.
         *          
         *      Check the score of both teams to see if they won?
         *      Check if the hand of the player when the RoundPile is 4,
         *       if it is empty then get score to see who won?
         *      Shuffle and deal deck when RoundPile is 4 and
         *      if player's hand is empty?
         * When a team wins, show something.
         *      
         * I probably missed something, especially considering how I don't even know
         * if the deck ever gets used again along with the basic win conditions. 
         * Whenever showing something to the user directly, I suggest putting the
         * methods into private methods, so they can easily be changed and modified
         * if the way of displaying the information changes. 
         * Either way, whatever techniques you use to integrate/implement this-
         * Godspeed. 
         */
        // TODO implement this
        
        }   // end main()

    }
   // end class Pitch