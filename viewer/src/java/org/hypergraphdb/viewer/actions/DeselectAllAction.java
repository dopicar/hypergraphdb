//-------------------------------------------------------------------------
// $Revision: 1.2 $
// $Date: 2006/02/08 19:03:35 $
// $Author: bizi $
//-------------------------------------------------------------------------
package org.hypergraphdb.viewer.actions;
//-------------------------------------------------------------------------
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import org.hypergraphdb.viewer.HGViewer;
import org.hypergraphdb.viewer.util.HGVAction;
//-------------------------------------------------------------------------
public class DeselectAllAction extends HGVAction {
       
    public DeselectAllAction () {
        super("Deselect All Nodes and Edges");
        setPreferredMenu( "Select" );
        setAcceleratorCombo( java.awt.event.KeyEvent.VK_A, ActionEvent.CTRL_MASK|ActionEvent.SHIFT_MASK|ActionEvent.ALT_MASK) ;
    }

    public void actionPerformed(ActionEvent e) {
      //GinyUtils.deselectAllNodes( org.hypergraphdb.viewer.getCurrentNetworkView() );
      //GinyUtils.deselectAllEdges( org.hypergraphdb.viewer.getCurrentNetworkView() );
      HGViewer.getCurrentNetwork().getFlagger().unflagAllEdges();
      HGViewer.getCurrentNetwork().getFlagger().unflagAllNodes();
    }
}

