
package edu.wit.scds.ds.list.app ;

import java.awt.Color ;

/**
 * Distinguishes that the component to be drawn on a Frame/Panel is a shape. This
 * shape can be any color, though it can only be one whole color at a time. The shape
 * class cannot be initialized, it needs children classes to distinguish what shape
 * the shape is.
 *
 * @author Kai Yee
 *
 * @version 1.0.0 2022-11-24 Initial implementation
 */
public class Shape extends Component
    {

    /*
     * Data fields
     */
    /** Serialized ID */
    private static final long serialVersionUID = 1L ;
    /** Color of the Shape */
    protected Color color ;

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
     * @param setRelativeRotation
     *     of the shape according to a pair of coordinates
     * @param setOriginX
     *     x-coordinate of shape's relative rotation
     * @param setOriginY
     *     y-coordinate of shape's relative rotation
     */
    protected Shape( Color setColor,
                     int setX,
                     int setY,
                     int setWidth,
                     int setHeight,
                     int setRelativeRotation,
                     int setOriginX,
                     int setOriginY )
        {
        super( setX, setY, setWidth, setHeight, setRelativeRotation, setOriginX, setOriginY ) ;
        this.color = setColor ;

        }   // end 8-arg Constructor

    /*
     * utility methods
     */


    /**
     * @return shape's color
     */
    public Color getColor()
        {
        return this.color ;

        }   // end getColor()


    /**
     * @param value
     *     to set color
     */
    public void setColor( Color value )
        {
        this.color = value ;

        }   // end setColor()

    }
// end class Shape