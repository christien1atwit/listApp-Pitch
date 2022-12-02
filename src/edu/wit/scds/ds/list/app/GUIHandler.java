
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
public class GUIHandler implements Runnable
    {

    /*
     * Data fields
     */

    /** Width of the window */
    private static final int WIDTH = 800 ;
    /** Height of the window */
    private static final int HEIGHT = 825 ;
    /** Frames per Second updating table graphics */
    private static final int FPS = 24 ;
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
    public GUIHandler()
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
     * @param player
     *     exits all of the cards from the player's hand from the screen
     */
    public static void hideHand( Player player )
        {
        player.getHand().hideHand() ;

        }   // end hideHand()


    /** Sets up confirmation screen to confirm the end of turn */
    public static void endTurn()
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

        }   // end endTurn()


    /**
     * Shows a Caption and a field in which the user can enter text so that can
     * modify/create their name.
     *
     * @param playerNumber
     *     The number/ID of the player
     * @return String of the player's name
     */
    public static String getPlayerName( int playerNumber )
        {
        StringBuilder playerName = new StringBuilder() ;
        // Sets up the GUI visibility
        ( (JLabel) playerSetup[ 0 ] ).setText( "Player " + playerNumber + ":" ) ;
        blackScreen.setVisible( true ) ;
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

                    // Hides the GUI since update completed
                    for ( JComponent component : playerSetup )
                        {
                        component.setVisible( false ) ;

                        }

                    blackScreen.setVisible( true ) ;

                    // removes current action listener
                    textPane.getDocument().removeDocumentListener( this ) ;

                    }

                }


            @Override
            public void removeUpdate( DocumentEvent ignoredE )
                {
                // Empty Block

                }


            @Override
            public void changedUpdate( DocumentEvent ignoredE )
                {
                // Empty Block

                }

            } ) ;   // end ActionListener()

        // waits for user to type in their name and hit enter
        waitFor( playerName ) ;

        return playerName.toString() ;

        }   // getPlayerName()


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


    @Override
    public void run()
        {
        // when starting/running thread, only run the clock.
        mainClock() ;

        }   // end run()


    /**
     * Displays the hand of the player and multiple betting options.
     *
     * @param chosenCard
     *     The variable that is modified to the card played
     * @param player
     *     The current player that is responsible for making decisions
     * @param roundPile
     *     The current RoundPile at play
     * @param bet
     *     The variable that is modified to the value the player wishes to bet
     * @param makeBet
     *     uses bet variable and sets up interactive GUI for betting if true
     */
    public static void showActions( int[] chosenCard,
                                    Player player,
                                    RoundPile roundPile,
                                    int[] bet,
                                    boolean makeBet )
        {

        // If victory Label was ever displayed at the end of the turn, make it
        // invisible
        if ( victoryLabel.isVisible() )
            {
            victoryLabel.setVisible( false ) ;

            }

        // Iterates and displays the cards in hand
        player.getHand().displayHand( chosenCard, player, roundPile, frame ) ;

        // if wanted to make a bet set GUI as visible and interactive
        if ( makeBet )
            {
            makeBetGUI[ 0 ].setVisible( true ) ;
            for ( int i = 1 ; i < 7 ; i++ )
                {
                final int bettingValue = i ;

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

                        // removes current action listener
                        bettingButton.removeActionListener( this ) ;

                        // notify that action has been taken
                        synchronized ( player )
                            {
                            player.notifyAll() ;

                            }

                        }

                    } ) ;   // end ActionListener()

                }

            }   // end if makeBet

        // waits for player to place bet or play card
        waitFor( player ) ;

        }   // end executeStartTurn()


    /**
     * Sets the screen for starting the turn and waits for the user to click the
     * screen.
     *
     * @param playerName
     *     Sets the current player's name in the GUI
     */
    public static void startTurn( String playerName )
        {
        // updates current player GUI
        currentPlayerLabel.setText( playerName ) ;
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
                // signals that player clicked to confirm
                synchronized ( playerName )
                    {
                    playerName.notifyAll() ;

                    }

                }

            } ) ;   // end ActionListener

        // waits for the user to input their name
        waitFor( playerName ) ;

        }   // end startTurn()


    /**
     * Displays which team scored
     *
     * @param teamNumber
     *     team number/ID that scored
     */
    public static void teamScored( int teamNumber )
        {
        victoryLabel.setText( "Team " + teamNumber + " Scored!" ) ;
        victoryLabel.setVisible( true ) ;

        }   // endScored()


    /**
     * @param aCard
     *     to order card to the highest hierarchy of the table GUI
     *
     * @return True if operation could be executed
     */
    public static boolean toTop( Card aCard )
        {
        tableComponents.add( aCard ) ;
        return tableComponents.remove( aCard ) ;

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
        Canvas result = new Canvas( manyComponentPlus ) ;
        result.setPreferredSize( new Dimension( WIDTH, HEIGHT ) ) ;
        result.setBackground( new Color( 0, 0, 0, 0 ) ) ;
        result.setForeground( new Color( 0, 0, 0, 255 ) ) ;
        return result ;

        }   // end initCanvas()


    /** Initializes Continue Label to the bottom enter of the screen (invisible) */
    private static void initContinueLabel()
        {
        continueLabel = new JLabel( "Click Screen to Continue" ) ;
        continueLabel.setBounds( 0, 0, WIDTH, ( HEIGHT * 15 ) / 16 ) ;
        continueLabel.setHorizontalAlignment( SwingConstants.CENTER ) ;
        continueLabel.setVerticalAlignment( SwingConstants.BOTTOM ) ;
        continueLabel.setName( "continueLabel" ) ;
        continueLabel.setFont( new Font( "Calibri", Font.BOLD + Font.ITALIC, 40 ) ) ;
        continueLabel.setForeground( new Color( 240, 240, 240 ) ) ;
        continueLabel.setVisible( false ) ;
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
        makeBetGUI = new JComponent[ 7 ] ;

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
        for ( int i = 1 ; i < 7 ; i++ )
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
            bettingButton.setBounds( -85 + ( i * 120 ), 240, 120, 500 ) ;
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
        playerSetup[ 0 ] = new JLabel( "Player 4: " ) ;
        playerSetup[ 0 ].setFont( new Font( "Calibri", Font.BOLD, 80 ) ) ;
        playerSetup[ 0 ].setForeground( new Color( 200, 100, 100 ) ) ;
        playerSetup[ 0 ].setBounds( -240, 0, WIDTH, HEIGHT ) ;
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