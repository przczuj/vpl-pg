/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.gui.viewmodel.properties.binders;

public interface ObjectPropertyBinder {

    boolean isPropertyEditable(int index);

    String getPropertyName(int index);

    String getProperty(int index);

    void setProperty(int index, String text);

    int getPropertyCount();
}
