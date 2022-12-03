
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
    private static Player startingPlayer ;
    private static Team currentTeam ;
    private static RoundPile currentRoundPile ;
        
    private static Team winner ;
    
    private static Team[] teams = new Team[ 2 ] ;
    
    private static int bet = 1;
    private static Player betHolder ;
    private static Team betTeam ;
    
    private static Scanner pInput = new Scanner( System.in ) ;
    
    // main
    
    /**
     * 
     * 
     * @param args main
     */
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
        
        // Initialize Deck
        Deck deck = new Deck() ;
        
        // Initialize two teams
        teams[0] = new Team( new Player( new Hand() ) , new Player( new Hand() ) ) ;
        teams[1] = new Team( new Player( new Hand() ) , new Player( new Hand() ) ) ;
        
        // Initialize currentRoundPile
        currentRoundPile = new RoundPile() ;
        
        System.out.printf("%nWelcome to Pitch!%nPlease input player one's name: ") ;
        teams[0].getPlayers()[0].setName( pInput.next() );
        System.out.printf("%nPlease input player two's name: ") ;
        teams[1].getPlayers()[0].setName( pInput.next() ) ;
        System.out.printf("%nPlease input player three's name: ") ;
        teams[0].getPlayers()[1].setName( pInput.next() ) ;
        System.out.printf("%nPlease input player four's name: ") ;
        teams[1].getPlayers()[1].setName( pInput.next() ) ;
        
        System.out.printf( "%nTeam 1: %s and %s", teams[0].getPlayers()[0].getName(), teams[0].getPlayers()[1].getName() ) ;
        System.out.printf( "%nTeam 2: %s and %s%n", teams[1].getPlayers()[0].getName(), teams[1].getPlayers()[1].getName() ) ;
        
        currentPlayer = teams[0].getPlayers()[0] ;
        currentTeam = teams[0] ;
        
        Boolean gameWon = false ;
        
        while( !gameWon )
            {
            System.out.printf( "%nScore:%nTeam 1: %d%nTeam 2: %d%n", teams[0].getScore(), teams[1].getScore() ) ;
            deck.deal( teams ) ;
            gameWon = startSet() ;
            setReset( deck ) ;
            
            }
        System.out.printf("%nCongratulations %s and %s! You won!", winner.getPlayers()[0], winner.getPlayers()[1] ) ;
        
        }   // end main()
    
        private static boolean startSet()
            {
            startBet() ;
            for( int i = 0; i < 6; i++ )
                {
                startRound( i ) ;
                
                }
            teams[0].toString() ;
            return giveScore() ;
            
            }
        
        private static void startBet()
            {
            for( int i = 0; i < 4; i++ )
                {
                if( bet == 1 && i == 3)
                    {
                    System.out.printf( "%nNo bets placed. Bet set to 2. Bet holder: %s%n", currentPlayer.getName() ) ;
                    betHolder = currentPlayer ;
                    betTeam = currentTeam ;
                    break ;
                    
                    }
                takeBet( currentPlayer) ;
                nextPlayer() ;
                
                }
            startingPlayer = betHolder ;
            currentPlayer = betHolder ;
            currentTeam = betTeam ;
        
            }
        
        private static void startRound( int round )
            {
            RoundPile roundPile = new RoundPile() ;
            currentRoundPile = roundPile ;
            for( int i = 0; i < 4; i++ )
                {
                currentPlayer.getHand().checkPlayableCards( roundPile );
                if( i > 0 || round > 0 )
                    {
                System.out.printf( "%nRound Pile: %s Trump Suit: %s" + "%nHand: %s" +
                                   "%n%s, its your turn. What card would you like to play? (# from left in hand): ", 
                                       currentRoundPile.toString(), currentRoundPile.getTrumpSuit().toString(),
                                       currentPlayer.getHand().toString(), currentPlayer.getName() ) ;
                    }
                else 
                    {
                    System.out.printf( "%nRound Pile: %s Trump Suit: Undefined" + "%nHand: %s" +
                                       "%n%s, its your turn. What card would you like to play? (# from left in hand): ", 
                                       currentRoundPile.toString(), currentPlayer.getHand().toString(), currentPlayer.getName() ) ;
                    }
                Boolean gotCard = false ;
                do
                    {
                    // Player input an integer
                    if( pInput.hasNextInt() )
                        {
                        int x = pInput.nextInt() ;
                        // input integer is within bounds of hand size
                        if( x > 0 && x < currentPlayer.getHand().numberOfCards + 1 )
                            {
                            Boolean cardPlayed = currentPlayer.getHand().playCard( x - 1, currentPlayer, currentTeam, currentRoundPile, round ) ;
                            // makes sure card was played
                            if ( cardPlayed )
                                {
                                gotCard = true ;
                                System.out.printf( "%nCard played%n" ) ;
                                nextPlayer() ;
                                break ;
                                }
                            System.out.printf( "%nCannot play this card. What card would you like to play? " ) ;
                            
                            }
                        else
                            {
                            System.out.printf( "%nNot withnin bounds of hand size. Please input a new card number: " ) ;
                            }
                        
                        }
                    // Player didn't input integer
                    else if( pInput.hasNext() )
                        {
                        System.out.printf("%nPlease input a card number between 1 and the number of cards in your hand: " ) ;
                        
                        }
                    
                    } while( !gotCard ) ;
                
                }
            currentRoundPile.getOwner().addRoundPile( currentRoundPile ) ;
            currentPlayer = currentRoundPile.getCreator() ;
            
            }   
        
        private static void takeBet( Player player)
            {
            System.out.printf( "%nHand: " + player.getHand().toString() + "%n%s, What is your bet? (Current Bet: %d): ", player.getName(), bet ) ;
            Boolean betTaken = false ;
            do
                {
                if( pInput.hasNextInt() )
                    {
                    int x = pInput.nextInt() ;
                    if ( x > bet && x < 6 )
                        {
                        bet = x ;
                        betHolder = currentPlayer ;
                        betTeam = currentTeam ;
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
                    System.out.printf( "%nInvalid input. Please enter a number %d - 5", bet+1 ) ;
                    
                    }
                
                } while( !betTaken ) ;
            
            }
        
        private static void nextPlayer()
            {
            if( currentPlayer == teams[0].getPlayers()[0] )
                {
                currentPlayer = teams[1].getPlayers()[0] ;
                currentTeam = teams[1] ;
                
                }
            else if( currentPlayer == teams[1].getPlayers()[0] )
                {
                currentPlayer = teams[0].getPlayers()[1] ;
                currentTeam = teams[0] ;
                                                
                }
            else if( currentPlayer == teams[0].getPlayers()[1] )
                {
                currentPlayer = teams[1].getPlayers()[1] ;
                currentTeam = teams[1] ;
                
                }
            else if( currentPlayer == teams[1].getPlayers()[1] )
                {
                currentPlayer = teams[0].getPlayers()[0] ;
                currentTeam = teams[0] ;
                
                }
            
            
            }
        
        private static boolean giveScore()
            {
            int scoreTeam1 = 0 ;
            int scoreTeam2 = 0 ;
            int temp = teams[0].getHighestTrumpCard().getRank().getOrder() - teams[1].getHighestTrumpCard().getRank().getOrder() ;
            if( temp > 1 ) 
                {
                scoreTeam1++ ;
                
                }
            else
                {
                scoreTeam2++ ;
                
                }
            temp = teams[0].getLowestTrumpCard().getRank().getOrder() - teams[1].getLowestTrumpCard().getRank().getOrder() ;
            if( temp < 1 ) 
                {
                scoreTeam1++ ;
                
                }
            else
                {
                scoreTeam2++ ;
                
                }
            if( teams[0].getHasTrumpJack() || teams[1].getHasTrumpJack() )
                {
                if( teams[0].getHasTrumpJack() )
                    {
                    scoreTeam1++ ;
                    
                    }
                else
                    {
                    scoreTeam2++ ;
                    
                    }
                
                }
            temp = teams[0].getTallyPoints() - teams[1].getTallyPoints() ;
            if( temp > 1 )
                {
                scoreTeam1++ ;
                
                }
            else if( temp < 1 )
                {
                scoreTeam2++ ;
                
                }
            
            if( betTeam == teams[0] && scoreTeam1 >= bet )
                {
                teams[0].addScore( scoreTeam1 ) ;
                teams[1].addScore( scoreTeam2 ) ;
                if( teams[0].getScore() == teams[1].getScore() )
                    {
                    return false ;
                    
                    }
                else if( teams[0].getScore() >= 13 )
                    {
                    winner = teams[0] ;
                                                    
                    }
                else
                    {
                    winner = teams[1] ;
                    
                    }
                
                }
            else if( betTeam == teams[0] && scoreTeam2 >= bet )
                {
                if( teams[0].getScore() == teams[1].getScore() )
                    {
                    return false ;
                    
                    }
                else if( teams[0].getScore() >= 13 )
                    {
                    winner = teams[0] ;
                                                    
                    }
                else
                    {
                    winner = teams[1] ;
                    
                    }
                
                }
            
            return false ;
        
            }

        private static void setReset( Deck deck )
            {
            teams[0].dealBack( deck ) ;
            teams[1].dealBack( deck ) ;
            
            teams[0] = new Team( teams[0].getPlayers()[0], teams[0].getPlayers()[1] ) ;
            teams[1] = new Team( teams[1].getPlayers()[0], teams[1].getPlayers()[1] ) ;
            
            teams[0].getPlayers()[0].setBet( 0 ) ;
            teams[0].getPlayers()[1].setBet( 0 ) ;
            teams[1].getPlayers()[0].setBet( 0 ) ;
            teams[1].getPlayers()[1].setBet( 0 ) ;
            bet = 0 ;
            
            currentPlayer = startingPlayer ;
            nextPlayer() ;
            startingPlayer = currentPlayer ;
            
            }
    }
   // end class Pitch