
package edu.wit.scds.ds.list.app ;

import java.awt.Color ;

/**
 * Distinguishes that the shape that can be drawn on a Frame/Panel is a filled
 * Rectangle.
 *
 * @author Kai Yee
 *
 * @version 1.0.0 2022-11-24 Initial implementation
 */
public class Rectangle extends Shape
    {

    // Data Fields

    /** Serialized ID */
    private static final long serialVersionUID = 1L ;

    /*
     * Constructors
     */

    /**
     * @param setColor
     *     of the shape
     * @param setX
     *     coordinate of the shape
     * @param setY
     *     coordinate of the shape
     * @param setWidth
     *     of the shape
     * @param setHeight
     *     of the shape
     */
    public Rectangle( Color setColor, int setX, int setY, int setWidth, int setHeight )
        {
        this( setColor, setX, setY, setWidth, setHeight, 0, 0, 0 ) ;

        }   // end 5-args Constructor


    /**
     * @param setColor
     *     of the shape
     * @param setX
     *     coordinate of the shape
     * @param setY
     *     coordinate of the shape
     * @param setWidth
     *     of the shape
     * @param setHeight
     *     of the shape
     * @param setRelativeRotation
     *     of the shape according to a pair of coordinates
     * @param setOriginX
     *     x-coordinate of shape's relative rotation
     * @param setOriginY
     *     y-coordinate of shape's relative rotation
     */
    public Rectangle( Color setColor,
                      int setX,
                      int setY,
                      int setWidth,
                      int setHeight,
                      int setRelativeRotation,
                      int setOriginX,
                      int setOriginY )
        {
        super( setColor,
               setX,
               setY,
               setWidth,
               setHeight,
               setRelativeRotation,
               setOriginX,
               setOriginY ) ;

        }   // end 8-args Constructor

    }
// end class Rectangle