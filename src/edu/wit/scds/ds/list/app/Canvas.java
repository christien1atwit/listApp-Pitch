
package edu.wit.scds.ds.list.app ;

import java.awt.Color ;
import java.awt.Graphics ;
import java.awt.Graphics2D ;
import java.awt.Image ;
import java.awt.geom.AffineTransform ;
import java.awt.image.BufferedImage ;
import java.io.IOException ;

import javax.imageio.ImageIO ;
import javax.swing.ImageIcon ;
import javax.swing.JPanel ;

/**
 * Canvas is able to handle multiple components 
 * (which should consist of shapes and entities),
 * and draws them onto the Canvas. Since Canvas extends JPanel, 
 * the Canvas is also capable of repainting its graphics.
 * However, since Canvas has the sole purpose of drawing, it 
 * has little to no handling over other components like JButton
 * or JTextPane. This class is capable of handling the transformations
 * of entities, and further contributes to giving entities the 
 * illusion of animation.
 * 
 * @author Kai Yee
 *
 * @version 1.0.0 2022-11-23 Initial implementation
 */
public class Canvas extends JPanel implements Plus
    {
    // Data Fields
    /** Should consist of Objects that are Component or Entity */
    private Plus[] components ;
    private static final long serialVersionUID = -7442982158639175060L ;
    
    /*
     * Constructor
     */
    
    /**
     * Prepares Canvas for drawing components
     * @param manyComponents
     *     classes that are either entities or shape components.
     */
    public Canvas( final Plus[] manyComponents )
        {
        this.components = manyComponents ;

        }   // end 1-arg constructor

    /*
     * API Methods
     */
    
    /**
     * Updates the current state of the Canvas consisting of more
     * or less or updated entries. By calling this method
     * multiple times, the Canvas can play through entity transformations
     * and therefore give the illusion of animation if any.
     * 
     * @param manyComponents
     *      redraws classes that are either entities or shape components.
     */
    public void redraw( final Plus[] manyComponents )
        {
        this.components = manyComponents ;
        updateUI() ;
        this.repaint( 0, 0, getPreferredSize().width, getPreferredSize().height ) ;

        }   // end redraw()

    
    @Override
    public void paintComponent( Graphics g )
        {
        // prepares graphics to 2d to handle rotation and images
        super.paintComponent( g ) ;
        Graphics2D g2d = (Graphics2D) g ;

        for ( Plus currentComponent : this.components )
            {
            // draws entity or component onto window
            if ( currentComponent instanceof Entity currentEntityPlus )
                {
                draw( g2d, currentEntityPlus, currentEntityPlus.getTransformationFrame() ) ;
                // once done with drawing the entity, removes the animation frame
                // from the entity.
                currentEntityPlus.removeTransformationFrame() ;

                }
            
            else if ( currentComponent instanceof Component currentComponentPlus )
                {
                draw( g2d, currentComponentPlus ) ;

                }

            }

        }   // end paintComponent()


    /** @return Canvas's drawing objects */
    public Plus[] getCanvasComponents()
        {
        return this.components ;

        }   // end getCanvasComponents()
    
    /*
     * private utility methods
     */
    
    /**
     * Draws all of the components in the entity according to the current
     * (possibly multiple) transformation 'frames'.
     * 
     * @param g2d Graphics to draw on Canvas.
     * @param currentEntity
     *     Entity to draw onto Graphics
     * @param transformFrame
     *     Transformation to be applied to the components in entity
     */
    private static void draw( Graphics2D g2d,
                              Entity currentEntity,
                              Transform[] transformFrame )
        {
        Color blendedColors = NULL_COLOR ;
        double totalScaleWidth = 1 ;
        double totalScaleHeight = 1 ;
        // Prepares all modifications for the drawing of the entity
        for ( Transform transformation : transformFrame )
            {
            // Prepares the Relative Rotation before drawing the components
            AffineTransform txRelativeRotation = new AffineTransform() ;
            txRelativeRotation.rotate( Math.toRadians( transformation.relativeRotation() ),
                                       transformation.originX(),
                                       transformation.originY() ) ;
            g2d.transform( txRelativeRotation ) ;

            // If the transformation wants to permanently apply xy-coordinates, do so
            if ( transformation.permanent() )
                {
                currentEntity.setX( transformation.x() ) ;
                currentEntity.setY( transformation.y() ) ;

                }
            else
                {
                // Prepares translating coordinates before drawing the components
                AffineTransform txTranslate = new AffineTransform() ;
                txTranslate.translate( transformation.x(), transformation.y() ) ;
                g2d.transform( txTranslate ) ;

                }

            // Prepares scaling before drawing the components
            totalScaleWidth *= transformation.width() ;
            totalScaleHeight *= transformation.height() ;

            // Blends Colors with any previous transformations
            blendedColors = blend( blendedColors, transformation.color() ) ;

            }

        // Prepares entity coordinates before drawing the components
        AffineTransform txEntityTranslate = new AffineTransform() ;
        txEntityTranslate.translate( currentEntity.getX(), currentEntity.getY() ) ;
        g2d.transform( txEntityTranslate ) ;

        // Iterates through all the components that make up the entity
        Component[] entityComponent = currentEntity.getComponents() ;
        for ( Component subComponentObj : entityComponent )
            {
            // draws all the components considering the transformation blended color
            draw( g2d, subComponentObj, blendedColors, totalScaleWidth, totalScaleHeight ) ;

            }

        // Undoes entity coordinates applied
        AffineTransform txTranslateInv = new AffineTransform() ;
        txTranslateInv.translate( currentEntity.getX() * -1, currentEntity.getY() * -1 ) ;
        g2d.transform( txTranslateInv ) ;

        // Undoes any transformation applied
        for ( int i = transformFrame.length - 1 ; i >= 0 ; i-- )
            {
            Transform transformation = transformFrame[ i ] ;

            if ( !transformation.permanent() )
                {
                // Undoes translating coordinates applied
                AffineTransform txTranslate = new AffineTransform() ;
                txTranslate.translate( transformation.x() * -1, transformation.y() * -1 ) ;
                g2d.transform( txTranslate ) ;

                }

            // Undoes the Relative Rotation applied
            AffineTransform txRelativeRotation = new AffineTransform() ;
            txRelativeRotation.rotate( Math.toRadians( transformation.relativeRotation() * -1 ),
                                       transformation.originX(),
                                       transformation.originY() ) ;
            g2d.transform( txRelativeRotation ) ;

            }

        }   // end 3-arg draw()

    /**
     * Draws the component (shape or image) onto the Canvas.
     * @param g2d Graphics to draw on the Canvas
     * @param currentComponent
     *     Component to draw onto Canvas
     */
    private static void draw( Graphics2D g2d,
                              Component currentComponent )
        {
        draw( g2d, currentComponent, NULL_COLOR, 1, 1 ) ;

        } // end 2-arg draw()

    
    /**
     * Draws the component (shape or image) onto the Canvas.
     * @param g2d Graphics to draw on the Canvas
     * @param currentComponent
     *     Component to draw onto Canvas
     * @param color 
     *     Color to blend with the Shape Component's colors with.
     * @param scaleWidth 
     *     Scale of width of drawing
     * @param scaleHeight 
     *     Scale of Height of drawing
     */
    private static void draw( Graphics2D g2d,
                              Component currentComponent,
                              Color color,
                              double scaleWidth,
                              double scaleHeight )
        {

        // Prepares the Relative Rotation before drawing to graphics
        AffineTransform txRelativeRotation = new AffineTransform() ;
        txRelativeRotation.rotate( Math.toRadians( currentComponent.getRelativeRotation() ),
                                   currentComponent.getOriginX(),
                                   currentComponent.getOriginY() ) ;
        g2d.transform( txRelativeRotation ) ;

        // Draws a rectangle with the specific properties if a rectangle
        if ( currentComponent instanceof Rectangle RectCurrentComponent )
            {
            g2d.setColor( blend( RectCurrentComponent.getColor(), color ) ) ;
            g2d.fillRect( RectCurrentComponent.getX(),
                          RectCurrentComponent.getY(),
                          (int) ( RectCurrentComponent.getWidth() * scaleWidth ),
                          (int) ( RectCurrentComponent.getHeight() * scaleHeight ) ) ;

            }
        // Draws an ellipse with the specific properties if an ellipse
        else if ( currentComponent instanceof Ellipse EllipseCurrentComponent )
            {
            g2d.setColor( blend( EllipseCurrentComponent.getColor(), color ) ) ;
            g2d.fillOval( EllipseCurrentComponent.getX(),
                          EllipseCurrentComponent.getY(),
                          (int) ( EllipseCurrentComponent.getWidth() * scaleWidth ),
                          (int) ( EllipseCurrentComponent.getHeight() * scaleHeight ) ) ;

            }
        // Draws an image with the specific properties if an image
        else if ( currentComponent instanceof edu.wit.scds.ds.list.app.Image ImagecurrentComponent )
            {
            BufferedImage raw_image ;
            try
                {
                raw_image = ImageIO.read( ImagecurrentComponent.getFile() ) ;
                ImageIcon imageicon = new ImageIcon( raw_image ) ;
                Image scaled_image = imageicon.getImage()
                                              .getScaledInstance( (int) ( ImagecurrentComponent.getWidth() *
                                                                          scaleWidth ),
                                                                  (int) ( ImagecurrentComponent.getHeight() *
                                                                          scaleHeight ),
                                                                  Image.SCALE_DEFAULT ) ;
                BufferedImage Final = toBufferedImage( scaled_image ) ;
                g2d.drawImage( Final,
                               null,
                               ImagecurrentComponent.getX(),
                               ImagecurrentComponent.getY() ) ;

                }
            catch ( IOException e )
                {
                e.printStackTrace() ;

                }

            }   // end 2-arg draw()

        // resets any relative rotation applied
        AffineTransform txRelativeRotationInv = new AffineTransform() ;
        txRelativeRotationInv.rotate( Math.toRadians( currentComponent.getRelativeRotation() *
                                                      -1 ),
                                      currentComponent.getOriginX(),
                                      currentComponent.getOriginY() ) ;
        g2d.transform( txRelativeRotationInv ) ;

        }

    /**
     * @param image Image to be casted to a BufferedImage
     * @return Image that has type BufferedImage
     */
    private static BufferedImage toBufferedImage( Image image )
        {
        if ( image instanceof BufferedImage )
            {
            return (BufferedImage) image ;

            }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage( image.getWidth( null ),
                                                  image.getHeight( null ),
                                                  BufferedImage.TYPE_INT_ARGB ) ;

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics() ;
        bGr.drawImage( image, 0, 0, null ) ;
        bGr.dispose() ;

        // Return the buffered image
        return bimage ;

        }   // end toBufferedImage()


    /**
     * @param colorOne
     *     first color to blend
     * @param colorTwo
     *     second color to blend
     *
     * @return the blended colors of both parameters
     */
    private static Color blend( Color colorOne,
                                Color colorTwo )
        {
        // Checks if the colors are worth blending, and returns a color if not
        if ( ( colorOne.getAlpha() == 0 ) && ( colorTwo.getAlpha() == 0 ) )
            {
            return colorOne ;

            }
        else if ( colorOne.getAlpha() == 0 )
            {
            return colorTwo ;

            }
        else if ( colorTwo.getAlpha() == 0 )
            {
            return colorOne ;

            }

        // Uses the alpha (visibility) of colors as a weighted variable to mix the
        // colors
        double totalAlpha = colorOne.getAlpha() + colorTwo.getAlpha() ;
        double alphaWeightOne = colorOne.getAlpha() / totalAlpha ;
        double alphaWeightTwo = colorTwo.getAlpha() / totalAlpha ;
        // fuses the rgb alpha values into one color
        int r = (int) ( ( alphaWeightOne * colorOne.getRed() ) +
                        ( alphaWeightTwo * colorTwo.getRed() ) ) ;
        int g = (int) ( ( alphaWeightOne * colorOne.getGreen() ) +
                        ( alphaWeightTwo * colorTwo.getGreen() ) ) ;
        int b = (int) ( ( alphaWeightOne * colorOne.getBlue() ) +
                        ( alphaWeightTwo * colorTwo.getBlue() ) ) ;
        int alpha = Math.max( colorOne.getAlpha(), colorTwo.getAlpha() ) ;

        return new Color( r, g, b, alpha ) ;

        }

    } // end class JCanvas