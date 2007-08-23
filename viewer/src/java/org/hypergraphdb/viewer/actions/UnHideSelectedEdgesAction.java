//-------------------------------------------------------------------------
// $Revision: 1.1 $
// $Date: 2005/12/25 01:22:41 $
// $Author: bobo $
//-------------------------------------------------------------------------
package org.hypergraphdb.viewer.actions;
//-------------------------------------------------------------------------
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import org.hypergraphdb.viewer.util.HGVAction;
import org.hypergraphdb.viewer.HGViewer;
//-------------------------------------------------------------------------
public class UnHideSelectedEdgesAction extends HGVAction  {

    public UnHideSelectedEdgesAction () {
        super ("Show All");
        setPreferredMenu( "Select.Edges" );
        setAcceleratorCombo( java.awt.event.KeyEvent.VK_H, ActionEvent.ALT_MASK| ActionEvent.SHIFT_MASK) ;
    }

    public void actionPerformed (ActionEvent e) {
      //GinyUtils.unHideSelectedEdges( org.hypergraphdb.viewer.getCurrentNetworkView() );
      GinyUtils.unHideAll( HGViewer.getCurrentView() );
    }//action performed
}

