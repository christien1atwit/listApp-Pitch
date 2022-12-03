
package edu.wit.scds.ds.list.app ;

import javax.swing.JComponent ;

/**
 * A class intended to be a parent class that allows components/children to 
 * be shown/displayed/drawn onto a window (or panels of a window). By 
 * providing variables, this class allows for children classes to have the
 * standard coordinates and scale- along with a new important feature:
 * Rotation.
 * @author Kai Yee
 *
 * @version 1.0.0 2022-11-24 Initial implementation
 */
public class Component extends JComponent implements Plus
    {
    /*
     * Data fields
     */
    /** Serialized ID */
    private static final long serialVersionUID = 1L ;
    /** x-coordinate of Component */
    private int x ;
    /** y-coordinate of Component */
    private int y ;
    /** width of Component */
    private int width ;
    /** height of Component */
    private int height ;
    /** Relative Rotation of Component */
    private int relativeRotation ;
    /** x-coordinate that the relative rotation relies on for Component */
    private int originX ;
    /** y-coordinate that the relative rotation relies on for Component */
    private int originY ;

    /*
     * Constructors
     */
    
    /**
     * @param setX coordinate of the component
     * @param setY coordinate of the component
     * @param setWidth of the component
     * @param setHeight of the component
     * @param setRelativeRotation of the component according to a pair of coordinates
     * @param setOriginX x-coordinate of component's relative rotation
     * @param setOriginY y-coordinate of component's relative rotation
     */
    protected Component( int setX, int setY, int setWidth, int setHeight, int setRelativeRotation, int setOriginX, int setOriginY )
        {
        this.x = setX ;
        this.y = setY ;
        this.width = setWidth ;
        this.height = setHeight ;
        this.relativeRotation = setRelativeRotation ;
        this.originX = setOriginX ;
        this.originY = setOriginY ;

        }   // end 7-args constructor()

    /*
     * Utility methods
     */
    @Override
    public int getHeight()
        {
        return this.height ;

        }   // end getHeight()


    /**
     * @return the x-coordinate that the relative rotation relies on
     */
    public int getOriginX()
        {
        return this.originX ;

        }   // end getOriginX()

    /**
     * @return the y-coordinate that the relative rotation relies on
     */
    public int getOriginY()
        {
        return this.originY ;

        }   // end getOriginY()


    /**
     * @return the Relative Rotation of the component according to a pair of coordinates
     */
    public int getRelativeRotation()
        {
        return this.relativeRotation ;

        }   // end getRelativeRotation()

    @Override
    public int getWidth()
        {
        return this.width ;

        }   // end getWidth()


    @Override
    public int getX()
        {
        return this.x ;

        }   // end getX()


    @Override
    public int getY()
        {
        return this.y ;

        }   // end getY()


    /** @param value to set the height */
    public void setHeight( int value )
        {
        this.height = value ;

        }   // end setHeight()

    /** @param value to set the x-coordinate origin of relative rotation */
    public void setOriginX( int value )
        {
        this.originX = value ;

        }   // end setOriginX()

    /** @param value to set the y-coordinate origin of relative rotation */
    public void setOriginY( int value )
        {
        this.originY = value ;

        }   // end setOriginY()

    /** @param value to set the relative rotation in degrees */
    public void setRelativeRotation( int value )
        {
        this.relativeRotation = value ;

        }   // end setRelativeRotation()

    /** @param value to set the width */
    public void setWidth( int value )
        {
        this.width = value ;

        }   // end setWidth()

    /** @param value to set the x-coordinate */
    public void setX( int value )
        {
        this.x = value ;

        }   // end setX()

    /** @param value to set the y-coordinate */
    public void setY( int value )
        {
        this.y = value ;

        }   // end setY()

    }
// end class JComponentPlus