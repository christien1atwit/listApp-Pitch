
package edu.wit.scds.ds.list.app ;

import java.awt.Color ;
import java.awt.Dimension ;
import java.awt.Font ;
import java.awt.event.ActionEvent ;
import java.awt.event.ActionListener ;
import java.util.ArrayList ;

import javax.swing.JButton ;
import javax.swing.JComponent ;
import javax.swing.JFrame ;
import javax.swing.JLabel ;
import javax.swing.JTextPane ;
import javax.swing.SpringLayout ;
import javax.swing.SwingConstants ;
import javax.swing.WindowConstants ;
import javax.swing.event.DocumentEvent ;
import javax.swing.event.DocumentListener ;

/**
 * Handles most of the GUI related commands for the application. The class also
 * handles the window and its components. When implemented, the Handler should have
 * it's own exclusive thread to constantly update its components; this allows for the
 * proper compartmentalization of the component hierarchy, and allows for the
 * animating of the overall window.
 *
 * @author Kai Yee
 *
 * @version 1.0.0 2022-11-26 Initial implementation
 */
public class DisplayHandler implements Runnable
    {

    /*
     * Data fields
     */

    /** Width of the window */
    private static final int WIDTH = 800 ;
    /** Height of the window */
    private static final int HEIGHT = 825 ;
    /** Frames per Second updating table graphics */
    private static final int FPS = 16 ;
    /** Window */
    private static JFrame frame ;
    /** GUI Label that displays both team points and score */
    private static JLabel[] teamCounterLabels ;
    /** GUI Label that displays the current player */
    private static JLabel currentPlayerLabel ;
    /** GUI Components that are responsible for setting up Player Names */
    private static JComponent[] playerSetup ;
    /** Canvas that holds a black screen */
    private static Canvas blackScreen ;
    /** Buttons that signal the start or end of a turn */
    private static JButton[] turnButtons ;
    /** Continue Label that holds an instruction */
    private static JLabel continueLabel ;
    /** Has caption and 5 various buttons that are either to bet or pass */
    private static JComponent[] makeBetGUI ;
    /** Canvas that contains miscellaneous such as cards */
    private static Canvas table ;
    /** Tracks all drawing components that belong to table for efficiency */
    private static ArrayList<Plus> tableComponents ;
    /** GUI Label that displays which team won a game */
    private static JLabel victoryLabel ;

    /*
     * Constructor
     */

    /**
     * Initializes the most of the GUI components
     */
    public DisplayHandler()
        {
        initFrame() ;
        initTitle() ;
        initTeamPointsLabel() ;
        initCurrentPlayer() ;
        initPlayerName() ;
        initBlackScreen() ;
        initTurnButtons() ;
        initMakeBetGUI() ;
        initVictoryLabel() ;
        initContinueLabel() ;
        initTable() ;
        frame.setVisible( true ) ;
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE ) ;

        }   // end no-arg constructor

    /*
     * API Methods
     */


    /**
     * @param aCard
     *     to add to the table GUI
     */
    public static void add( Card aCard )
        {
        tableComponents.add( aCard ) ;
        table.redraw( tableComponents.toArray( new Plus[ tableComponents.size() ] ) ) ;

        }   // end add()

    /** 
     * Sets up confirmation screen to confirm the end of turn 
     * @param player 
     *      Player that the turn is ending on
     * @param teams 
     *      Team's RoundPiles to save onto GUI
     */
    public static void endTurn( Player player, Team[] teams )
        {
        Object threadHandler = new Object() ;
        // makes continue label and confirm screen visible
        turnButtons[ 1 ].setVisible( true ) ;
        continueLabel.setVisible( true ) ;
        // makes the button functional
        turnButtons[ 1 ].addActionListener( new ActionListener()
            {

            @Override
            public void actionPerformed( ActionEvent e )
                {
                // makes continue label and confirm screen invisible
                turnButtons[ 1 ].setVisible( false ) ;
                continueLabel.setVisible( false ) ;
                // removes current ActionListener
                turnButtons[ 1 ].removeActionListener( this ) ;
                // Signals that user wishes to continue when clicked
                synchronized ( threadHandler )
                    {
                    threadHandler.notifyAll() ;

                    }

                }

            } ) ;   // end ActionListener()
        // waits thread for the user to confirm clicking the screen
        waitFor( threadHandler ) ;
        DisplayHandler.hideHand( player );
        
        try
            {
            Thread.sleep( 650 );

            }
        catch ( InterruptedException e )
            {
            e.printStackTrace() ;

            }
        
        for ( int i = 0 ; i < player.getHand().getNumberOfCards() ; i++ ) 
            {
            tableComponents.remove( player.getHand().get( i ) ) ;
            player.getHand().get( i ).resetAnimation(); 
            }
        
        table.redraw( tableComponents.toArray( new Plus[ tableComponents.size() ] ) ) ;
        
        try
            {
            Thread.sleep( 1000/4 );

            }
        catch ( InterruptedException e )
            {
            e.printStackTrace() ;

            }
        
        }   // end endTurn()


    /**
     * @param player
     *     Player that's making the bet
     * @param minimumBet
     *     The minimum bet that the play must place
     * @param highestBetter
     *     Player with the highest bet
     *
     * @return the bet values for the player
     */
    public static int getBet( Player player,
                              int minimumBet,
                              Player highestBetter )
        {
        // Initializes the changing/result value
        int[] bet = new int[ 1 ] ;
        // Displays Hand
        blackScreen.setVisible( false );
        player.getHand().setUnplayable() ;
        player.getHand().displayHand( new int[ 1 ], player, new RoundPile(), frame ) ;

        // Iterates through to show all of the necessary betting options
        makeBetGUI[ 0 ].setVisible( true ) ;
        if ( highestBetter == null )
            {
            ( (JLabel) makeBetGUI[ 0 ] ).setText( "<html> No Bet holders! <br>&nbsp&nbsp&nbsp Make Bet: " ) ;

            }
        else
            {
            ( (JLabel) makeBetGUI[ 0 ] ).setText( "<html> Bet Holder: " + highestBetter.getName() +
                                                  "<html><br>&nbsp&nbsp&nbsp Make Bet: " ) ;

            }

        for ( int i = 1 ; i < 6 ; i++ )
            {
            final int bettingValue = i ;
            // ensures that user can either pass or must bet more than the minimum
            // amount
            if ( ( i == 1 ) || ( i > minimumBet ) )
                {
                JButton bettingButton = (JButton) makeBetGUI[ i ] ;
                bettingButton.setVisible( true ) ;
                // makes each betting button interactive
                bettingButton.addActionListener( new ActionListener()
                    {

                    @Override
                    public void actionPerformed( ActionEvent e )
                        {
                        // Establishes a difference between passing value and betting
                        if ( bettingValue == 1 )
                            {
                            bet[ 0 ] = 0 ;

                            }
                        else
                            {
                            bet[ 0 ] = bettingValue ;

                            }

                        // When bet is complete- hide the GUI
                        for ( JComponent bettingGUI : makeBetGUI )
                            {
                            bettingGUI.setVisible( false ) ;

                            }

                        // removes interactive GUI as action is completed
                        bettingButton.removeActionListener( this ) ;

                        // notify that action has been taken
                        synchronized ( player )
                            {
                            player.notifyAll() ;

                            }

                        }

                    } ) ;   // end ActionListener()

                }   // end if

            }

        // waits for betting amount from player
        waitFor( player ) ;
        // Hides Hand
        player.getHand().hideHand(); 
        return bet[ 0 ] ;

        }   // getBet()
    
    /**
     * Shows a Caption and a field in which the user can enter text so that can
     * modify/create their name.
     *
     * @param playerNumber
     *     The number/ID of the player
     *
     * @return String of the player's name
     */
    public static String getPlayerName( int playerNumber )
        {
        StringBuilder playerName = new StringBuilder() ;
        // Temporarily sets currentPlayer as their id
        currentPlayerLabel.setText( "<html>Player " + playerNumber ) ;
        ( (JLabel) playerSetup[ 0 ] ).setText( "<html>Team " + ( ( ( playerNumber - 1 ) % 2 ) + 1 ) +
                                               "<html><br>Player " + playerNumber + ":" ) ;
        // Shows a black screen to use as a temporary background
        blackScreen.setVisible( true ) ;
        // Shows GUI for getting player name
        for ( JComponent component : playerSetup )
            {
            component.setVisible( true ) ;

            }

        // Makes interactive text pane
        JTextPane textPane = ( (JTextPane) playerSetup[ 1 ] ) ;
        textPane.getDocument().addDocumentListener( new DocumentListener()
            {

            @Override
            public void insertUpdate( DocumentEvent e )
                {
                // Text in Pane is tested for if the user hit the return key
                String line = ( (JTextPane) playerSetup[ 1 ] ).getText() ;
                if ( line.contains( "\n" ) )
                    {
                    // if user hits return key, set it to the player's name
                    line.replaceAll( "\n", "" ) ;
                    playerName.append( line ) ;
                    
                    synchronized ( playerName )
                        {
                        playerName.notifyAll() ;

                        }

                    // Hides the GUI since update is completed
                    for ( JComponent component : playerSetup )
                        {
                        component.setVisible( false ) ;

                        }
                    textPane.getDocument().removeDocumentListener( this ) ;

                    }

                }


            @Override
            public void removeUpdate( DocumentEvent ignoredE )
                {
                // Empty Block (not needed)

                }


            @Override
            public void changedUpdate( DocumentEvent ignoredE )
                {
                // Empty Block (not needed)

                }

            } ) ;   // end ActionListener()

        // waits for user to type in their name and return
        waitFor( playerName ) ;
        // resets text
        ( (JTextPane) playerSetup[ 1 ] ).setText( "" ) ;

        return playerName.toString() ;

        }   // getPlayerName()

    /**
     * @param player
     *     exits all of the cards from the player's hand from the screen
     */
    public static void hideHand( Player player )
        {
        player.getHand().hideHand() ;

        }   // end hideHand()

    
    /**
     * Forever looping clock that's responsible for constantly updating graphics
     * depending on the FPS.
     */
    public synchronized static void mainClock()
        {
        // Forever loop that updates graphics on a clock
        while ( true )
            {
            try
                {
                Thread.sleep( 1000 / FPS ) ;

                }
            catch ( InterruptedException e )
                {
                e.printStackTrace() ;

                }

            // Redraws graphics according to the table components
            table.redraw( tableComponents.toArray( new Plus[ tableComponents.size() ] ) ) ;
            // Repaints only this part of the frame to prevent redrawing bg
            // constantly
            frame.getContentPane().repaint() ;
            frame.getLayeredPane().repaint() ;

            }

        }   // end mainClock()
    
    /** Resets the table GUI */
    public static void resetTable() 
        {
        tableComponents.clear() ;
        table.redraw( tableComponents.toArray( new Plus[ tableComponents.size() ] ) ) ;
        
        }   // end resetTable()
    
    @Override
    public void run()
        {
        // when starting/running thread, only run the clock.
        mainClock() ;

        }   // end run()



    /**
     * Displays the hand of the player and multiple betting options.
     *
     * @param player
     *     The current player that is responsible for making decisions
     * @param roundPile
     *     The current RoundPile at play
     *
     * @return Index of the card in player's hand that's chosen by the user
     */
    public static int showPlayerHand( Player player,
                                      RoundPile roundPile )
        {
        // Initializes the changing/returning value
        int[] chosenCard = new int[ 1 ] ;

        // If victory Label was ever displayed at the end of the turn, make it
        // invisible
        if ( victoryLabel.isVisible() )
            {
            victoryLabel.setVisible( false ) ;

            }

        // Iterates and displays the cards in hand
        player.getHand().displayHand( chosenCard, player, roundPile, frame ) ;

        // waits for player to play card
        waitFor( player ) ;
        try
            {
            Thread.sleep( 1000/2 );

            }
        catch ( InterruptedException e )
            {
            e.printStackTrace() ;

            }
        return chosenCard[ 0 ] ;

        }   // end showPlayerHand()


    /**
     * Sets the screen for starting the turn and waits for the user to click the
     * screen.
     *
     * @param player
     *     Sets the current player's name in the GUI
     */
    public static void startTurn( Player player )
        {
        // updates current player GUI
        currentPlayerLabel.setText( player.getName() ) ;
        // makes black screen, continue label and confirm screen visible
        blackScreen.setVisible( true ) ;
        turnButtons[ 0 ].setVisible( true ) ;
        continueLabel.setVisible( true ) ;
        // adds interactive button to confirm whether player wants to continue/begin
        turnButtons[ 0 ].addActionListener( new ActionListener()
            {

            @Override
            public void actionPerformed( ActionEvent e )
                {
                // makes black screen, continue label and confirm screen invisible
                blackScreen.setVisible( false ) ;
                turnButtons[ 0 ].setVisible( false ) ;
                continueLabel.setVisible( false ) ;
                // deletes the interactivity of button
                turnButtons[ 0 ].removeActionListener( this ) ;
                // signals that player clicked to confirm action
                synchronized ( player )
                    {
                    player.notifyAll() ;

                    }

                }

            } ) ;   // end ActionListener

        // waits for the user to confirm starting their turn
        waitFor( player ) ;

        }   // end startTurn()


    /**
     * Displays which team won
     *
     * @param teamNumber
     *     team number/ID that won
     */
    public static void teamWon( int teamNumber )
        {
        blackScreen.setVisible( true );
        victoryLabel.setText( "Team " + teamNumber + " WON!" ) ;
        victoryLabel.setVisible( true ) ;

        }   // endScored()


    /**
     * @param aComponent
     *     to order plus component to the highest hierarchy of the table GUI
     *
     * @return True if operation could be executed
     */
    public static boolean toTop( Plus aComponent )
        {
        tableComponents.add( aComponent ) ;
        return tableComponents.remove( aComponent ) ;

        }   // end toTop()


    /**
     * @param teamOnePoints
     *     sets team one's points onto GUI
     * @param teamTwoPoints
     *     sets team two's points onto GUI
     * @param teamOneScore
     *     sets team one's score onto GUI
     * @param teamTwoScore
     *     sets team two's score onto GUI
     */
    public static void updateCounter( int teamOnePoints,
                                      int teamTwoPoints,
                                      int teamOneScore,
                                      int teamTwoScore )
        {
        teamCounterLabels[ 0 ].setText( "<html>Team 1 Score: " + teamOneScore +
                                        " <br> Team 1 Points: " + teamOnePoints ) ;
        teamCounterLabels[ 1 ].setText( "<html>Team 2 Score: " + teamTwoScore +
                                        " <br> Team 2 Points: " + teamTwoPoints ) ;

        }   // end updatePoints()

    /*
     * Private Methods
     */


    /** Initializes and adds blackScreen to frame/window (invisible) */
    private static void initBlackScreen()
        {
        // Initializes the black screen
        Component blackScreenShape = new Rectangle( new Color( 20,
                                                               40,
                                                               0 ),
                                                    0,
                                                    0,
                                                    WIDTH,
                                                    HEIGHT,
                                                    0,
                                                    0,
                                                    0 ) ;
        Plus[] hierarchy = new Plus[ 1 ] ;
        hierarchy[ 0 ] = blackScreenShape ;
        blackScreen = initCanvas( hierarchy ) ;
        // adds black screen to window (is invisible)
        blackScreen.setVisible( false ) ;
        frame.getContentPane().add( blackScreen ) ;

        }   // end initBlackScreen()


    /**
     * @param manyComponentPlus
     *     to draw on Canvas
     *
     * @return Canvas initialized
     */
    private static Canvas initCanvas( Plus[] manyComponentPlus )
        {
        // Initializes Canvas with window width and height
        Canvas result = new Canvas( manyComponentPlus ) ;
        result.setPreferredSize( new Dimension( WIDTH, HEIGHT ) ) ;
        result.setBackground( new Color( 0, 0, 0, 0 ) ) ;
        result.setForeground( new Color( 0, 0, 0, 255 ) ) ;
        return result ;

        }   // end initCanvas()


    /** Initializes Continue Label to the bottom enter of the screen (invisible) */
    private static void initContinueLabel()
        {
        // Initialize continue label and positional coordinates
        continueLabel = new JLabel( "Click Screen to Continue" ) ;
        continueLabel.setBounds( 0, 0, WIDTH, ( HEIGHT * 15 ) / 16 ) ;
        continueLabel.setHorizontalAlignment( SwingConstants.CENTER ) ;
        continueLabel.setVerticalAlignment( SwingConstants.BOTTOM ) ;
        continueLabel.setName( "continueLabel" ) ;
        // Set the Font and colors of the text
        continueLabel.setFont( new Font( "Calibri", Font.BOLD + Font.ITALIC, 40 ) ) ;
        continueLabel.setForeground( new Color( 240, 240, 240 ) ) ;
        continueLabel.setVisible( false ) ;
        // adds it to the window
        frame.getLayeredPane().add( continueLabel ) ;

        }   // end initContinueLabel()


    /** Initialize a GUI Label of the current player */
    private static void initCurrentPlayer()
        {
        currentPlayerLabel = new JLabel( "Player 1" ) ;
        currentPlayerLabel.setFont( new Font( "Calibri", Font.BOLD, 50 ) ) ;
        currentPlayerLabel.setForeground( new Color( 200, 100, 100 ) ) ;
        currentPlayerLabel.setBounds( 0, 100, WIDTH, HEIGHT ) ;
        currentPlayerLabel.setHorizontalAlignment( SwingConstants.CENTER ) ;
        currentPlayerLabel.setVerticalAlignment( SwingConstants.TOP ) ;
        frame.getLayeredPane().add( currentPlayerLabel ) ;

        }   // end initCurrentPlayer()


    /** Initializes Frame/Window */
    private static void initFrame()
        {
        frame = new JFrame() ;
        frame.setLayout( new SpringLayout() ) ;
        frame.setSize( WIDTH, HEIGHT ) ;
        frame.setResizable( false ) ;
        frame.setTitle( "Pitch" ) ;
        frame.setBackground( new Color( 60, 150, 60 ) ) ;

        }   // end initFrame()


    private static void initMakeBetGUI()
        {
        makeBetGUI = new JComponent[ 6 ] ;

        // Initializes the Betting Title/Caption
        JLabel caption = new JLabel( "Make Bet:" ) ;
        caption.setFont( new Font( "Calibri", Font.BOLD, 50 ) ) ;
        caption.setForeground( new Color( 200, 200, 200 ) ) ;
        caption.setBounds( 0, 220, WIDTH, HEIGHT ) ;
        caption.setHorizontalAlignment( SwingConstants.CENTER ) ;
        caption.setVerticalAlignment( SwingConstants.TOP ) ;
        caption.setVisible( false ) ;
        makeBetGUI[ 0 ] = caption ;
        frame.getLayeredPane().add( caption ) ;

        // Iterates to create buttons that have various values of betting
        for ( int i = 1 ; i < 6 ; i++ )
            {
            JButton bettingButton = new JButton() ;
            if ( i == 1 )
                {
                bettingButton.setText( "Pass" ) ;

                }
            else
                {
                bettingButton.setText( "" + ( i ) ) ;

                }

            bettingButton.setFont( new Font( "Calibri", Font.BOLD, 40 ) ) ;
            bettingButton.setForeground( Color.WHITE ) ;
            bettingButton.setBounds( -25 + ( i * 120 ), 240, 120, 500 ) ;
            bettingButton.setName( "bet" ) ;
            // Makes button completely invisible
            bettingButton.setBackground( new Color( 200, 200, 200 ) ) ;
            bettingButton.setBorder( null ) ;
            bettingButton.setOpaque( false ) ;
            bettingButton.setContentAreaFilled( false ) ;
            bettingButton.setVisible( false ) ;
            makeBetGUI[ i ] = bettingButton ;
            frame.getRootPane().add( bettingButton ) ;

            }

        }   // end initMakeBetGUI()


    /** Initializes the GUI for setting up player names */
    private static void initPlayerName()
        {
        playerSetup = new JComponent[ 2 ] ;
        playerSetup[ 0 ] = new JLabel( "Player 1: " ) ;
        playerSetup[ 0 ].setFont( new Font( "Calibri", Font.BOLD, 80 ) ) ;
        playerSetup[ 0 ].setForeground( new Color( 200, 100, 100 ) ) ;
        playerSetup[ 0 ].setBounds( -240, -70, WIDTH, HEIGHT ) ;
        ( (JLabel) playerSetup[ 0 ] ).setHorizontalAlignment( SwingConstants.CENTER ) ;
        ( (JLabel) playerSetup[ 0 ] ).setVerticalAlignment( SwingConstants.CENTER ) ;
        playerSetup[ 0 ].setVisible( false ) ;
        frame.getLayeredPane().add( playerSetup[ 0 ] ) ;
        playerSetup[ 1 ] = new JTextPane() ;
        playerSetup[ 1 ].setFont( new Font( "Calibri", Font.BOLD, 80 ) ) ;
        playerSetup[ 1 ].setForeground( new Color( 200, 100, 100 ) ) ;
        playerSetup[ 1 ].setBackground( new Color( 20, 40, 0 ) ) ;
        playerSetup[ 1 ].setBorder( null ) ;
        playerSetup[ 1 ].setBounds( 310, 344, 1000, 300 ) ;
        playerSetup[ 1 ].setVisible( false ) ;
        frame.getLayeredPane().add( playerSetup[ 1 ] ) ;

        }   // end initPlayerName()


    /** Initializes any components that belongs to the table */
    private static void initTable()
        {
        tableComponents = new ArrayList<>() ;
        table = initCanvas( new Plus[ 0 ] ) ;
        frame.getContentPane().add( table ) ;

        }   // end initTable()


    /** Initializes GUI Labels of the team points */
    private static void initTeamPointsLabel()
        {
        teamCounterLabels = new JLabel[ 2 ] ;
        for ( int i = 0 ; i < 2 ; i++ )
            {
            JLabel teamCounter = new JLabel( "<html>Team " + ( i + 1 ) + " Score: 0 <br> Team " +
                                             ( i + 1 ) + " Points: 0" ) ;
            teamCounter.setFont( new Font( "Verdana", Font.BOLD, 18 ) ) ;
            teamCounter.setForeground( new Color( 60, 60, 60 ) ) ;
            teamCounter.setBounds( 20 + ( 500 * i ), 20, 300, 100 ) ;
            frame.getRootPane().add( teamCounter ) ;
            teamCounterLabels[ i ] = teamCounter ;

            }

        }   // end initTeamPointsLabel()


    /** Initialize GUI Labels of the game title */
    private static void initTitle()
        {
        JLabel gameTitle = new JLabel( "Pitch" ) ;
        gameTitle.setFont( new Font( "Serif", Font.BOLD + Font.ITALIC, 90 ) ) ;
        gameTitle.setForeground( new Color( 240, 230, 00 ) ) ;
        gameTitle.setBounds( 0, 0, WIDTH, HEIGHT ) ;
        gameTitle.setHorizontalAlignment( SwingConstants.CENTER ) ;
        gameTitle.setVerticalAlignment( SwingConstants.TOP ) ;
        frame.getLayeredPane().add( gameTitle ) ;

        }   // end initTitle()


    /** Initializes and adds both start and turn buttons to window (invisible) */
    private static void initTurnButtons()
        {
        turnButtons = new JButton[ 2 ] ;
        for ( int i = 0 ; i < 2 ; i++ )
            {
            JButton turnButton = new JButton() ;
            turnButton.setBounds( 0, 0, WIDTH, HEIGHT ) ;
            turnButton.setName( "turnButton" ) ;
            // Makes button completely invisible
            turnButton.setBackground( new Color( 0, 0, 0, 0 ) ) ;
            turnButton.setBorder( null ) ;
            turnButton.setOpaque( false ) ;
            turnButton.setContentAreaFilled( false ) ;
            turnButton.setVisible( false ) ;
            frame.getRootPane().add( turnButton ) ;
            turnButtons[ i ] = turnButton ;

            }

        }   // end initTurnButtons()


    /** Initializes the victory label GUI */
    private static void initVictoryLabel()
        {
        victoryLabel = new JLabel( "" ) ;
        victoryLabel.setFont( new Font( "Serif", Font.BOLD + Font.ITALIC, 90 ) ) ;
        victoryLabel.setForeground( new Color( 250, 240, 160 ) ) ;
        victoryLabel.setBounds( 0, 0, WIDTH, HEIGHT ) ;
        victoryLabel.setHorizontalAlignment( SwingConstants.CENTER ) ;
        victoryLabel.setVerticalAlignment( SwingConstants.CENTER ) ;
        victoryLabel.setVisible( false ) ;
        frame.getLayeredPane().add( victoryLabel ) ;

        }   // end initVictoryLabel()


    /**
     * @param object
     *     waits the current Thread of the object
     */
    private static void waitFor( Object object )
        {
        synchronized ( object )
            {
            try
                {
                object.wait() ;

                }
            catch ( InterruptedException e )
                {
                e.printStackTrace() ;

                }

            }

        }   // end waitFor()

    }
// end class GUIHandler