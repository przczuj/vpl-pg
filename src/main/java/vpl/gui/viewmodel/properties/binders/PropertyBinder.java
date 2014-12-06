/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.gui.viewmodel.properties.binders;

public interface PropertyBinder {

    boolean isPropertyEditable();

    String getPropertyName();

    String getProperty();

    void setProperty(String text);
}
