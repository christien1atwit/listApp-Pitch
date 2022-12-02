
package edu.wit.scds.ds.list.app ;

import java.io.ByteArrayInputStream ;
import java.util.Scanner ;
import java.util.concurrent.locks.Condition ;
import java.util.concurrent.locks.Lock ;
import java.util.concurrent.locks.ReentrantLock ;

import javax.swing.JTextPane ;

/**
 * @author yeek1
 *
 * @version 1.0.0 2022-11-23 Initial implementation
 */
public class Experiment implements Runnable
    {
    public static void main( String[] args )
        {
        // Creates an entirely new thread to handle constantly updating graphics
        Thread graphics = new Thread( new GUIHandler() ) ;
        graphics.start() ; 
        
        test() ;
        }   // end main()
    
    

    private static void test()
        {
        /*
        synchronized (h)
        {
            try
                {
                h.wait();
                
    
                }
            catch ( InterruptedException e )
                {
                // TODO Auto-generated catch block
                e.printStackTrace() ;
    
                }
        }
        
        System.out.println("sdsa") ;
*/      
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
            GUIHandler.add( card ) ;
            hello[ i ] = card ;
            p.getHand().add( card );

            }
        //rp.add( new Card(Suit.HEARTS, Rank.EIGHT), p, new Team(new Player(new Hand()), new Player(new Hand())) ) ;
        
        StringBuilder h = new StringBuilder() ;
        //GUIHandler.startTurn( 1, hello, 1 ) ;
        playerName = GUIHandler.getPlayerName( 1 );
        
        for (int i = 0; i < 5 ; i++)
            {
            GUIHandler.startTurn(playerName);
            p.getHand().checkPlayableCards( rp );
            if (makeBet)
                {
                bet[0] = GUIHandler.getBet( p ) ;
                
                }
            else
                {
                chosenCard[0] = GUIHandler.showActions( p, rp);
                
            //System.out.println( bet[ 0 ] );
            //System.out.println( chosenCard[ 0 ] ) ;

                rp.add(p.getHand().remove( chosenCard[0] ), p, new Team(new Player(new Hand()), new Player(new Hand())) ) ;
                }
            
            GUIHandler.endTurn() ;
            if (rp.getNumberOfCards() == 4) 
                {
                System.out.println(rp.getHighestCard()) ;
                System.out.println(rp.getHighestTrumpCard()) ;
                System.out.println(rp.getLowestTrumpCard()) ;

                }
            }

        }



    @Override
    public void run()
        {
        test() ;

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