/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vpl.gui.viewmodel.properties;

import vpl.gui.viewmodel.properties.binders.RigidBodyPropertyBinder;
import vpl.gui.viewmodel.properties.binders.UniformForcePropertyBinder;
import vpl.gui.viewmodel.properties.binders.ObjectPropertyBinder;
import vpl.gui.viewmodel.properties.binders.ForcePropertyBinder;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import vpl.physics.Force;
import vpl.physics.RigidBody;

public class PropertiesTableModel extends AbstractTableModel {
    
    ObjectPropertyBinder binder;
    
    public void setModelObject(RigidBody rigidBody) {
        binder = new RigidBodyPropertyBinder(rigidBody);
    }

    public void setModelObject(Force force) {
        binder = new UniformForcePropertyBinder(force);
    }

    public void setModelObject(Force force, RigidBody rb) {
        binder = new ForcePropertyBinder(force, rb);
    }

    @Override
    public int getRowCount() {
        return binder != null ? binder.getPropertyCount() : 0;
    }

    @Override
    public int getColumnCount() {
        return 2; //name + value
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnIndex == 0 ? "property" : "value";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return binder != null ? binder.isPropertyEditable(rowIndex) : false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (binder == null) return "";
        return columnIndex == 0 ? binder.getPropertyName(rowIndex) : binder.getProperty(rowIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (binder != null && columnIndex == 1) {
            binder.setProperty(rowIndex, aValue.toString());
        }
    }

}
