
package edu.wit.scds.ds.list.app;

import java.util.Scanner ;


/**
 * 
 * @author Dennis Field // TODO
 *
 * @version 1.0.0 2022-11-19 Initial implementation
 * @version 1.0.1 2022-11-19 Template implementation
 *
 */
public class Pitch
    {

    // TODO implement this
    
    // Data fields
    private static Player currentPlayer ;
    private static Team currentTeam ;
    private static RoundPile currentRoundPile ;
    // I have no idea what gameScore is, but if you need it, here it is.
    private int gameScore ;
    private Team[] teams ;
    private static int bet = 1;
    private static Player betHolder ;
    
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
        
        // Initialize Scanner
        Scanner pInput = new Scanner(System.in) ;
        
        // Initialize two teams
        Team team1 = new Team( new Player( new Hand) , new Player( new Hand ) ) ;
        Team team2 = new Team( new Player( new Hand) , new Player( new Hand ) ) ;
        
        // Initialize currentRoundPile
        currentRoundPile = new RoundPile() ;
        
        System.out.printf("%nWelcome to Pitch!%nPlease input player one's name: ") ;
        // team1.getPlayers()[0].setName( input.Next() ) ;
        System.out.printf("%nPlease input player two's name: ") ;
        // team2.getPlayers()[0].setName( input.Next() ) ;
        System.out.printf("%nPlease input player three's name: ") ;
        // team1.getPlayers()[1].setName( input.Next() ) ;
        System.out.printf("%nPlease input player four's name: ") ;
        // team2.getPlayers()[1].setName( input.Next() ) ;
        
        currentPlayer = team1.getPlayers()[0] ;
        currentTeam = team1 ;
        
        startBet( team1, team2, pInput ) ;
        
        startRound( team1, team2, pInput ) ;
        
        
        
        }   // end main()
    
        private static void startSet()
            {
            
            
        
            }
        
        private static void startBet( Team team1, Team team2, Scanner pInput )
            {
            for( int i = 0; i < 4; i++ )
                {
                if( bet == 1 )
                    {
                    System.out.printf( "&nNo bets placed. Bet set to 2. Bet holder: %s", currentPlayer.getName() ) ;
                    break ;
                    
                    }
                takeBet( currentPlayer, pInput) ;
                nextPlayer( team1, team2) ;
                
                }
            currentPlayer = betHolder ;
        
            }
        
        private static void startRound( Team team1, Team team2, Scanner pInput )
            {
            for( int i = 0; i < 4; i++ )
                {
                System.out.printf( "Round Pile: %s" + "Hand: %s" +
                                   "%n%s, its your turn. What card would you like to play? (# from left in hand) ", 
                                   currentRoundPile.toString(), currentPlayer.getHand().toString(), currentPlayer.getName() ) ;
                Boolean gotCard = false ;
                do
                    {
                    // Player input an integer
                    if( pInput.hasNextInt() )
                        {
                        // input integer is within bounds of hand size
                        if( pInput.nextInt() > 0 && pInput.nextInt() < currentPlayer.getHand().numberOfCards + 1 )
                            {
                            Boolean cardPlayed = currentPlayer.getHand().playCard( pInput.nextInt() - 1, currentRoundPile ) ;
                            // makes sure card was played
                            if ( cardPlayed )
                                {
                                gotCard = true ;
                                System.out.printf( "%nCard played" ) ;
                                break ;
                                }
                            System.out.printf( "%nCannot play this card." ) ;
                            
                            }
                        
                        }
                    // Player didn't input integer
                    else
                        {
                        System.out.printf("Please input a card number between 1 and the number of cards in your hand." ) ;
                        
                        }
                    
                    } while( !gotCard ) ;
                
                }
        
            }   
        
        private static void takeBet( Player player, Scanner pInput)
            {
            System.out.printf( "%nHand: " + player.getHand().toString() + "%n%s, What is your bet? (Current Bet: %f) ", player.getName(), bet ) ;
            Boolean betTaken = false ;
            do
                {
                if( pInput.hasNextInt() )
                    {
                    if ( pInput.nextInt() > bet && pInput.nextInt() < 6 )
                        {
                        bet = pInput.nextInt() ;
                        betHolder = currentPlayer ;
                        betTaken = true ;
                        }
                    }
                else if ( pInput.next().contentEquals( "Skip" ) )
                    {
                    System.out.printf( "%nBet skipped." ) ;
                    betTaken = true ;
                    
                    }
                else
                    {
                    System.out.printf( "%nInvalid input. Please enter a number 2 - 5" ) ;
                    
                    }
                
                } while( !betTaken ) ;
            
            }
        
        private static void nextPlayer( Team team1, Team team2 )
            {
            if( currentPlayer == team1.getPlayers()[0] )
                {
                currentPlayer = team2.getPlayers()[0] ;
                currentTeam = team2 ;
                
                }
            else if( currentPlayer == team2.getPlayers()[0] )
                {
                currentPlayer = team1.getPlayers()[1] ;
                currentTeam = team1 ;
                                                
                }
            else if( currentPlayer == team1.getPlayers()[1] )
                {
                currentPlayer = team2.getPlayers()[1] ;
                currentTeam = team2 ;
                
                }
            else if( currentPlayer == team2.getPlayers()[1] )
                {
                currentPlayer = team1.getPlayers()[0] ;
                currentTeam = team1 ;
                
                }
            
            
            }
        
        

    }
   // end class Pitch