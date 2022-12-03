
package edu.wit.scds.ds.list.app ;

/**
 * @author Kai Yee
 *
 * @version 1.0.0 2022-11-23 Initial implementation
 */
public class Experiment
    {
    public static void main( String[] args )
        {
        // Creates an entirely new thread to handle constantly updating graphics
        Thread graphics = new Thread( new DisplayHandler() ) ;
        graphics.start() ; 
        
        test() ;
        }   // end main()
    
    

    private static void test()
        {

        Player p = new Player( new Hand() ) ;
        int[] bet = new int[1] ;
        int[] chosenCard = new int[1] ;
        boolean makeBet = false ;
        RoundPile rp = new RoundPile() ;
        String playerName ;
        (new RoundPile()).add( new Card(Suit.DIAMONDS, Rank.EIGHT), p, new Team(new Player(new Hand()), new Player(new Hand())) ) ;
        
        Card[] hello = new Card[ 6 ] ;

        for ( int i = 0 ; i <= 5 ; i++ )
            {
            Card card = new Card(Suit.values()[i/2], Rank.values()[i] ) ;
            DisplayHandler.add( card ) ;
            hello[ i ] = card ;
            p.getHand().add( card );

            }
        //rp.add( new Card(Suit.HEARTS, Rank.EIGHT), p, new Team(new Player(new Hand()), new Player(new Hand())) ) ;
        
        StringBuilder h = new StringBuilder() ;
        //GUIHandler.startTurn( 1, hello, 1 ) ;
        playerName = DisplayHandler.getPlayerName( 1 );
        
        for (int i = 0; i < 5 ; i++)
            {
            DisplayHandler.startTurn(playerName);
            p.getHand().checkPlayableCards( rp );
            if (makeBet)
                {
                bet[0] = DisplayHandler.getBet( p, 3 ) ;
                
                }
            else
                {
                chosenCard[0] = DisplayHandler.showPlayerHand( p, rp);
                
            //System.out.println( bet[ 0 ] );
            //System.out.println( chosenCard[ 0 ] ) ;

                rp.add(p.getHand().remove( chosenCard[0] ), p, new Team(new Player(new Hand()), new Player(new Hand())) ) ;
                }
            
            DisplayHandler.endTurn() ;
            if (rp.getNumberOfCards() == 4) 
                {
                System.out.println(rp.getHighestCard()) ;
                System.out.println(rp.getHighestTrumpCard()) ;
                System.out.println(rp.getLowestTrumpCard()) ;

                }
            }

        }



    
    /**
     * @param obj
     *     waits the current Thread of the object
     */
    private static void waitFor( Object obj )
        {
        synchronized ( obj )
            {
            try
                {
                obj.wait() ;

                }
            catch ( InterruptedException e )
                {
                e.printStackTrace() ;

                }

            }

        }   // end waitFor()

    }
// end class Exp