
package edu.wit.scds.ds.list.app ;

import java.io.File ;

/**
 * Distinguishes that the component to be drawn on a Frame/Panel is an image. This
 * image needs to be contained and accessible through a file.
 *
 * @author Kai Yee
 *
 * @version 1.0.0 2022-11-24 Initial implementation
 */
public class Image extends Component
    {
    // Data Fields

    /** File that the image is rendered on */
    protected File file ;
    /** Serialized ID */
    private static final long serialVersionUID = 1L ;

    /*
     * Constructors
     */

    /**
     * @param setFile
     *     of the image
     * @param setX
     *     coordinate of the image
     * @param setY
     *     coordinate of the image
     * @param setWidth
     *     of the image
     * @param setHeight
     *     of the image
     */
    public Image( File setFile, int setX, int setY, int setWidth, int setHeight )
        {
        this( setFile, setX, setY, setWidth, setHeight, 0, 0, 0 ) ;

        }   // end 5-arg Constructor


    /**
     * @param setFile
     *     of the image
     * @param setX
     *     coordinate of the image
     * @param setY
     *     coordinate of the image
     * @param setWidth
     *     of the image
     * @param setHeight
     *     of the image
     * @param setRelativeRotation
     *     of the image according to a pair of coordinates
     * @param setOriginX
     *     x-coordinate of image's relative rotation
     * @param setOriginY
     *     y-coordinate of image's relative rotation
     */
    public Image( File setFile,
                  int setX,
                  int setY,
                  int setWidth,
                  int setHeight,
                  int setRelativeRotation,
                  int setOriginX,
                  int setOriginY )
        {
        super( setX, setY, setWidth, setHeight, setRelativeRotation, setOriginX, setOriginY ) ;
        this.file = setFile ;

        }   // end 8-arg Constructor

    /*
     * utility methods
     */


    /** @return image's file */
    public File getFile()
        {
        return this.file ;

        }   // end getFile()


    /**
     * @param value
     *     to set the Image's file to
     */
    public void setFile( File value )
        {
        this.file = value ;

        }   // end setFile()

    }
// end class Image