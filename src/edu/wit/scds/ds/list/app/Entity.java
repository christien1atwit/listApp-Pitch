
package edu.wit.scds.ds.list.app ;

import java.util.ArrayList ;
import java.util.LinkedList ;
import java.util.List ;
import java.util.Queue ;

/**
 * Each Entity can hold multiple components that all calculated/positioned according
 * to the entity's coordinates. While the entity itself cannot be drawn, the
 * components that belongs to the entity can be. The entity also integrates animation
 * through multiple queues of Transform Records- and animates all of its components
 * at once.
 *
 * @author Kai Yee
 *
 * @version 1.0.0 2022-11-24 Initial implementation
 */
public class Entity implements Plus
    {

    /*
     * Data Fields
     */

    /**
     * Keeps track of multiple different animations that contain transformations for
     * each frame
     */
    protected List<Queue<Transform>> animation ;
    /** Tracks components relative to the instances' coordinates */
    protected List<Component> components ;
    /** Coordinate of Instance on the x-axis */
    protected int x ;
    /** Coordinate of Instance on the y-axis */
    protected int y ;

    /*
     * Constructor
     */

    /**
     * Initializes all of the variables including animation, components, and
     * coordinates.
     */
    public Entity()
        {
        this.animation = new LinkedList<>() ;
        this.components = new ArrayList<>() ;
        this.x = 0 ;
        this.y = 0 ;

        }   // end no-arg Constructor

    /*
     * API Methods
     */


    /**
     * @param aComponent
     *     that's added to the entity's visual rendition.
     *
     * @see java.util.List#add
     */
    public void add( Component aComponent )
        {
        this.components.add( aComponent ) ;

        }   // end 1-arg add()


    /**
     * @param index
     *     that the component is added to.
     * @param aComponent
     *     that's added to the entity's visual rendition.
     *
     * @see java.util.List#add
     */
    public void add( int index,
                     Component aComponent )
        {
        this.components.add( index, aComponent ) ;

        }   // end 2-arg add()


    /**
     * @return array of Component that belong to the instance
     */
    public Component[] getComponents()
        {
        return this.components.toArray( new Component[ this.components.size() ] ) ;

        }   // end getComponents()


    /**
     * @return all transformations applied to the first animation frame of the
     *     entity.
     */
    public Transform[] getTransformationFrame()
        {

        LinkedList<Transform> result = new LinkedList<>() ;
        // Iterates through all animations
        for ( Queue<Transform> animatedTransformation : this.animation )
            {
            // Returns the first transformation of every Animation
            result.add( animatedTransformation.peek() ) ;

            }

        return result.toArray( new Transform[ result.size() ] ) ;

        }   // end getTransformationFrame()


    /**
     * @return Entity's x-coordinate
     */
    public int getX()
        {
        return this.x ;

        }   // end getX()


    /**
     * @return Entity's y-coordinate
     */
    public int getY()
        {
        return this.y ;

        }   // end getY()


    /**
     * @param aComponent
     *     that's removed from the entity's visual rendition.
     *
     * @return true if removed successfully
     *
     * @see java.util.List#add
     */
    public boolean remove( Component aComponent )
        {
        return this.components.remove( aComponent ) ;

        }   // end remove()


    /**
     * @param index
     *     that a component is removed from.
     *
     * @return Component that was removed
     *
     * @see java.util.List#add
     */
    public Component remove( int index )
        {
        return this.components.remove( index ) ;

        }   // end remove()


    /**
     * Removes the first animation frame that has applied transformations
     */
    public void removeTransformationFrame()
        {
        // Iterates through all animations
        for ( Queue<Transform> animatedTransformation : this.animation )
            {
            // Removes the first transformation of every Animation
            animatedTransformation.remove() ;

            }

        // Removes any empty animations
        for ( int i = this.animation.size() - 1 ; i >= 0 ; i-- )
            {
            Queue<Transform> animatedTransformation = this.animation.get( i ) ;
            if ( animatedTransformation.isEmpty() )
                {
                this.animation.remove( animatedTransformation ) ;

                }

            }

        }   // end removeTransformationFrame()


    /**
     * @param value
     *     to entity's x-coordinate
     */
    public void setX( int value )
        {
        this.x = value ;

        }   // end setX()


    /**
     * @param value
     *     to entity's y-coordinate
     */
    public void setY( int value )
        {
        this.y = value ;

        }   // end setY()

    }
// end class Entity