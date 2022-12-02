
package edu.wit.scds.ds.list.app ;

import java.awt.Color ;

/**
 * The purpose of the Transform Record is to contribute towards changing the values
 * of an entity for a single 'Animation' frame. This animation frame various
 * depending on the FPS, sometimes only lasting for a hair of a second. When applied
 * to an entity, all of the components in the entity are temporarily modified
 * according to the Transform Record. The Transform Record is also capable of
 * permanently changing the coordinates of entities.
 *
 * @author Kai Yee
 *
 * @version 1.0.0 2022-11-24 Initial implementation
 *
 * @param color
 *     transformation that's additionally applied if used on a Shape instance
 * @param x
 *     multiplier to the original x
 * @param y
 *     multiplier to the original y
 * @param width
 *     multiplier to the original width
 * @param height
 *     multiplier to the original height
 * @param relativeRotation
 *     transformation that's additionally applied
 * @param originX
 *     transformation that's additionally applied
 * @param originY
 *     transformation that's additionally applied
 * @param permanent
 *     true if x and y of transformation applies directly onto Entity's x and y
 */
public record Transform( Color color,
                         int x,
                         int y,
                         double width,
                         double height,
                         int relativeRotation,
                         int originX,
                         int originY,
                         boolean permanent )
    implements Plus

    {

    /**
     * @param x
     *     multiplier to the original x
     * @param y
     *     multiplier to the original y
     * @param permanent
     *     true if x and y of transformation applies directly onto Entity's x and y
     */
    public Transform( int x, int y, boolean permanent )
        {
        this( NULL_COLOR, x, y, 1, 1, 0, 0, 0, permanent ) ;

        }   // end 3-arg Constructor


    /**
     * @param x
     *     multiplier to the original x
     * @param y
     *     multiplier to the original y
     * @param width
     *     multiplier to the original width
     * @param height
     *     multiplier to the original height
     */
    public Transform( int x, int y, double width, double height )
        {
        this( NULL_COLOR, x, y, width, height, 0, 0, 0, false ) ;

        }   // end 4-arg Constructor


    /**
     * @param x
     *     multiplier to the original x
     * @param y
     *     multiplier to the original y
     * @param width
     *     multiplier to the original width
     * @param height
     *     multiplier to the original height
     * @param permanent
     *     true if x and y of transformation applies directly onto Entity's x and y
     */
    public Transform( int x, int y, double width, double height, boolean permanent )
        {
        this( NULL_COLOR, x, y, width, height, 0, 0, 0, permanent ) ;

        }   // end 5-arg Constructor


    /**
     * @param x
     *     multiplier to the original x
     * @param y
     *     multiplier to the original y
     * @param width
     *     multiplier to the original width
     * @param height
     *     multiplier to the original height
     * @param relativeRotation
     *     transformation that's additionally applied
     * @param originX
     *     transformation that's additionally applied
     * @param originY
     *     transformation that's additionally applied
     */
    public Transform( int x,
                      int y,
                      double width,
                      double height,
                      int relativeRotation,
                      int originX,
                      int originY )
        {
        this( NULL_COLOR, x, y, width, height, relativeRotation, originX, originY, false ) ;

        }   // end 7-args Constructor


    /**
     * @param color
     *     transformation that's additionally applied if used on a Shape instance
     * @param x
     *     multiplier to the original x
     * @param y
     *     multiplier to the original y
     * @param width
     *     multiplier to the original width
     * @param height
     *     multiplier to the original height
     * @param relativeRotation
     *     transformation that's additionally applied
     * @param originX
     *     transformation that's additionally applied
     * @param originY
     *     transformation that's additionally applied
     */
    public Transform( Color color,
                      int x,
                      int y,
                      double width,
                      double height,
                      int relativeRotation,
                      int originX,
                      int originY )
        {
        this( color, x, y, width, height, relativeRotation, originX, originY, false ) ;

        }   // end 8-args Constructor

    }
// end class Transform