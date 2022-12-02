
package edu.wit.scds.ds.list.app ;

import java.awt.Color ;

/**
 * Every Class that's a part of the Plus 'plug-in' implements this Interface to
 * indicate that they are all descendants of Plus. The Plus plug-in is necessary to
 * identify a group of classes that contributes GUI without being an exclusive
 * implementation for the application. While classes are able to extend these Plus
 * classes for a more exclusive implementation for the application, these extending
 * classes will/should have the intended purpose of contributing to GUI. The classes
 * that use Plus have the sole purpose of collaborating with Java Swing and Java AWT
 * for the GUI- and is not intended to collaborate with any other GUI mediums.
 *
 * @author Kai Yee
 *
 * @version 1.0.0 2022-11-29 Initial implementation
 */
public interface Plus
    {

    /** Null color value */
    static final Color NULL_COLOR = new Color( 0, 0, 0, 0 ) ;

    }
// end class Plus