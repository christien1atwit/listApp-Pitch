
package edu.wit.scds.ds.list.app;

import java.util.Scanner ;


/**
 * 
 * @author Dennis Field // DONE
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
        // DONE implement this
        
        Thread graphics = new Thread( new DisplayHandler() ) ;
        graphics.start() ; 
        
        // Initialize Deck
        Deck deck = new Deck() ;
        
        // Initialize two teams
        teams[0] = new Team( new Player( new Hand() ) , new Player( new Hand() ) ) ;
        teams[1] = new Team( new Player( new Hand() ) , new Player( new Hand() ) ) ;
        
        // Initialize currentRoundPile
        currentRoundPile = new RoundPile() ;
        currentPlayer = teams[0].getPlayers()[0] ;
        currentTeam = teams[0] ;
        
        for (int i = 0 ; i < 4 ; i++ )
            {
            currentPlayer.setName( DisplayHandler.getPlayerName( i + 1 ) );
            nextPlayer() ;
            
            }  
        
        Boolean gameWon = false ;
        
        while( !gameWon )
            {
            deck.deal( teams ) ;
            gameWon = startSet() ;
            setReset( deck ) ;
            
            }
        // Checks what team number won
        if (winner == teams[ 0 ] )
            {
            DisplayHandler.teamWon( 1 );
            }
        
        if (winner == teams[ 1 ] )
            {
            DisplayHandler.teamWon( 2 );
            
            }
        
        System.out.printf("%nCongratulations %s and %s! You won!", winner.getPlayers()[0].getName(), winner.getPlayers()[1].getName() ) ;
        
        }   // end main()
    
        private static boolean startSet()
            {
            startBet() ;
            for( int i = 0; i < 6; i++ )
                {
                startRound() ;
                
                }
            return giveScore() ;
            
            }   // end startSet()
        

        private static void startBet()
            {
            for( int i = 0; i < 4; i++ )
                {
                DisplayHandler.startTurn( currentPlayer );
                if( bet == 1 && i == 3)
                    {
                    betHolder = currentPlayer ;
                    betTeam = currentTeam ;
                    break ;
                    
                    }
                takeBet( currentPlayer ) ;
                nextPlayer() ;
                DisplayHandler.endTurn( currentPlayer, teams ) ;
                
                }
            startingPlayer = betHolder ;
            currentPlayer = betHolder ;
            currentTeam = betTeam ;
        
            }
        
        private static void startRound()
            {
            currentRoundPile = new RoundPile() ;
            for( int i = 0; i < 4; i++ )
                {
                DisplayHandler.startTurn( currentPlayer );
                currentPlayer.getHand().checkPlayableCards( currentRoundPile );
                currentPlayer.getHand().playCard( DisplayHandler.showPlayerHand( currentPlayer, currentRoundPile ), currentPlayer, currentTeam, currentRoundPile ) ;
                if ( i == 3 )
                    {
                    
                    // Moves cards of RoundPiles to the correlating team 
                    if ( currentRoundPile.getOwner() == teams[0] )
                    for ( int j = 0 ; j < 4 ; j++ )
                        {
                        currentRoundPile.get( j ).goToTeam( 1, j, teams[0].getRoundPiles().size() );
                        
                        }
                    if ( currentRoundPile.getOwner() == teams[1] )
                        for ( int j = 0 ; j < 4 ; j++ )
                            {
                            currentRoundPile.get( j ).goToTeam( 2, j, teams[1].getRoundPiles().size() );
                            
                            }
                    
                    // adds RoundPile to the owner of the team
                    currentRoundPile.getOwner().addRoundPile( currentRoundPile ) ;
                    
                    DisplayHandler.updateCounter( teams[0].getTallyPoints(), teams[1].getTallyPoints(), teams[0].getScore(), teams[1].getScore() );
                    
                    }
                
                DisplayHandler.endTurn( currentPlayer, teams );
                nextPlayer() ;
                }
                DisplayHandler.updateCounter( teams[0].getTallyPoints(), teams[1].getTallyPoints(), teams[0].getScore(), teams[1].getScore() );
            
            }   
        
        private static void takeBet( Player player)
            {
            int bettingValue ;
            bettingValue = DisplayHandler.getBet( player, bet, betHolder ) ;
            if ( bettingValue != 0 )
                {
                bet = bettingValue ;
                betHolder = currentPlayer ;
                betTeam = currentTeam ;
                }
            
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
            
            if ( teams[0].getHighestTrumpCard() != null && teams[1].getHighestTrumpCard() != null )
                {
                
                int temp = teams[0].getHighestTrumpCard().getRank().getOrder() - teams[1].getHighestTrumpCard().getRank().getOrder() ;
                if( temp > 0) 
                    {
                    scoreTeam1++ ;
                
                    }
                else
                    {
                    scoreTeam2++ ;
                
                    }
                temp = teams[0].getLowestTrumpCard().getRank().getOrder() - teams[1].getLowestTrumpCard().getRank().getOrder() ;
                if( temp < 0 ) 
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
            
                }
            else if (teams[0].getHighestTrumpCard() == null )
                {
                if ( teams[1].getHasTrumpJack() )
                    {
                    scoreTeam2 += 3 ;
                    
                    }
                else
                    {
                    scoreTeam2 += 2 ;
                    
                    }
                
                }
            else
                {
                if ( teams[0].getHasTrumpJack() )
                    {
                    scoreTeam1 += 3 ;
                    
                    }
                else
                    {
                    scoreTeam1 += 2 ;
                    
                    }
                
                }
            
            int tallyCompare = teams[0].getTallyPoints() - teams[1].getTallyPoints() ;
            if( tallyCompare > 1 )
                {
                scoreTeam1++ ;
                
                }
            else if( tallyCompare < 1 )
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
                    return true ;
                                                    
                    }
                else if( teams[1].getScore() >= 13 )
                    {
                    winner = teams[1] ;
                    return true ;
                    
                    }
                
                }
            else if( betTeam == teams[1] && scoreTeam2 >= bet )
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
                    return true ;
                                                    
                    }
                else if (teams[1].getScore() >= 13 )
                    {
                    winner = teams[1] ;
                    return true ;
                    
                    }
                
                }
            else if( betTeam == teams[0] )
                {
                teams[1].addScore( scoreTeam2 ) ;
                
                }
            else if( betTeam == teams[1] )
                {
                teams[0].addScore( scoreTeam1 ) ;
                
                }
            
            DisplayHandler.updateCounter( teams[0].getTallyPoints(), teams[1].getTallyPoints(), teams[0].getScore(), teams[1].getScore() );
            
            return false ;
        
            }

        private static void setReset( Deck deck )
            {
            DisplayHandler.resetTable() ;
            
            teams[0].dealBack( deck ) ;
            teams[1].dealBack( deck ) ;
            
            // stops current thread to take some time to process dealing back to the deck
            try
                {
                Thread.sleep( 1000/4 );

                }
            catch ( InterruptedException e )
                {
                e.printStackTrace() ;

                }
            
            int scoreTeam1 = teams[0].getScore() ;
            int scoreTeam2 = teams[1].getScore() ;
            
            teams[0] = new Team( teams[0].getPlayers()[0], teams[0].getPlayers()[1] ) ;
            teams[1] = new Team( teams[1].getPlayers()[0], teams[1].getPlayers()[1] ) ;
            
            teams[0].addScore( scoreTeam1 ) ;
            teams[1].addScore( scoreTeam2 ) ;
            
            teams[0].getPlayers()[0].setBet( 0 ) ;
            teams[0].getPlayers()[1].setBet( 0 ) ;
            teams[1].getPlayers()[0].setBet( 0 ) ;
            teams[1].getPlayers()[1].setBet( 0 ) ;
            bet = 1 ;
            RoundPile.resetTrumpSuit();
            currentPlayer = startingPlayer ;
            nextPlayer() ;
            startingPlayer = currentPlayer ;
            
            }
    }
   // end class Pitch