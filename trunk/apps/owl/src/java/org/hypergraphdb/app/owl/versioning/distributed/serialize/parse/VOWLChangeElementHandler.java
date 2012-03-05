package org.hypergraphdb.app.owl.versioning.distributed.serialize.parse;

import org.coode.owlapi.owlxmlparser.OWLXMLParserException;
import org.coode.owlapi.owlxmlparser.OWLXMLParserHandler;
import org.hypergraphdb.app.owl.versioning.change.VOWLChange;
import org.semanticweb.owlapi.io.OWLParserException;
import org.semanticweb.owlapi.model.UnloadableImportException;

/**
 * VOWLChangeElementHandler.
 * @author Thomas Hilpold (CIAO/Miami-Dade County)
 * @created Feb 29, 2012
 */
public class VOWLChangeElementHandler extends AbstractVOWLElementHandler<VOWLChange> {

	/**
	 * @param handler
	 */
	public VOWLChangeElementHandler(OWLXMLParserHandler handler) {
		super(handler);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.coode.owlapi.owlxmlparser.OWLElementHandler#endElement()
	 */
	@Override
	public void endElement() throws OWLParserException, UnloadableImportException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.coode.owlapi.owlxmlparser.OWLElementHandler#getOWLObject()
	 */
	@Override
	public VOWLChange getOWLObject() throws OWLXMLParserException {
		// TODO Auto-generated method stub
		return null;
	}
	
}