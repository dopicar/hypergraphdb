package org.hypergraphdb.app.owl.model.axioms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGLink;
import org.hypergraphdb.HyperGraph;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAxiomVisitor;
import org.semanticweb.owlapi.model.OWLAxiomVisitorEx;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLHasKeyAxiom;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectVisitor;
import org.semanticweb.owlapi.model.OWLObjectVisitorEx;
import org.semanticweb.owlapi.model.OWLPropertyExpression;

/**
 * OWLHasKeyAxiomHGDB.
 * @author Thomas Hilpold (CIAO/Miami-Dade County)
 * @created Nov 9, 2011
 */
public class OWLHasKeyAxiomHGDB extends OWLLogicalAxiomHGDB implements HGLink, OWLHasKeyAxiom {

//  private OWLClassExpression expression;
//	private Set<OWLPropertyExpression<?,?>> propertyExpressions;

	private HGHandle expressionHandle;
    private List<HGHandle> propertyExpressionsHandles;
    
    public OWLHasKeyAxiomHGDB(HGHandle...args) {    
        //TODO assert arg[0] type OWLClassExpression, args[1...length-1] type OWLPropertyExpression
    	super(Collections.<OWLAnnotation>emptySet());    	
    	assert (args.length >= 2);
    	Set<HGHandle> propertyExpressionsFromArgs = new HashSet<HGHandle>();
    	for(int i = 1; i < args.length; i++) {
    		propertyExpressionsFromArgs.add(args[i]);
    	}
        this.expressionHandle = args[0];
        this.propertyExpressionsHandles = new ArrayList<HGHandle>(propertyExpressionsFromArgs);    	
    }

    public OWLHasKeyAxiomHGDB(HGHandle expression, Set<? extends HGHandle> propertyExpressions, Collection<? extends OWLAnnotation> annotations) {
    	//OWLClassExpression expression, Set<? extends OWLPropertyExpression<?,?>> propertyExpressions, Collection<? extends OWLAnnotation> annotations
        super(annotations);
        this.expressionHandle = expression;
        this.propertyExpressionsHandles = new ArrayList<HGHandle>(propertyExpressions);
    }

    public OWLHasKeyAxiom getAxiomWithoutAnnotations() {
        if (!isAnnotated()) {
            return this;
        }
        return getOWLDataFactory().getOWLHasKeyAxiom(getClassExpression(), getPropertyExpressions());
    }

    public OWLHasKeyAxiom getAnnotatedAxiom(Set<OWLAnnotation> annotations) {
        return getOWLDataFactory().getOWLHasKeyAxiom(getClassExpression(), getPropertyExpressions(), mergeAnnos(annotations));
    }

    public AxiomType<?> getAxiomType() {
        return AxiomType.HAS_KEY;
    }

    @Override
	public boolean isLogicalAxiom() {
        return true;
    }

    public OWLClassExpression getClassExpression() {
        return getHyperGraph().get(expressionHandle);
    }

    public Set<OWLPropertyExpression<?,?>> getPropertyExpressions() {
    	HyperGraph g = getHyperGraph();
    	Set<OWLPropertyExpression<?,?>> s = new TreeSet<OWLPropertyExpression<?,?>>();
    	for (HGHandle h : propertyExpressionsHandles) {
    		s.add((OWLPropertyExpression<?,?>) g.get(h));    		
    	}
    	return s;
        //return CollectionFactory.getCopyOnRequestSet(propertyExpressions);
    }

    public Set<OWLDataPropertyExpression> getDataPropertyExpressions() {
        Set<OWLDataPropertyExpression> props = new TreeSet<OWLDataPropertyExpression>();
        for (OWLPropertyExpression<?,?> prop : getPropertyExpressions()) {
            if (prop.isDataPropertyExpression()) {
                props.add((OWLDataPropertyExpression) prop);
            }
        }
        return props;
    }

    public Set<OWLObjectPropertyExpression> getObjectPropertyExpressions() {
        Set<OWLObjectPropertyExpression> props = new TreeSet<OWLObjectPropertyExpression>();
        for (OWLPropertyExpression<?,?> prop : getPropertyExpressions()) {
            if (prop.isObjectPropertyExpression()) {
                props.add((OWLObjectPropertyExpression) prop);
            }
        }
        return props;
    }

    @Override
	protected int compareObjectOfSameType(OWLObject object) {
        OWLHasKeyAxiom other = (OWLHasKeyAxiom) object;
        int diff = getClassExpression().compareTo(other.getClassExpression());
        if (diff != 0) {
            return diff;
        }
        return compareSets(getPropertyExpressions(), other.getPropertyExpressions());
    }

    public void accept(OWLObjectVisitor visitor) {
        visitor.visit(this);
    }

    public <O> O accept(OWLObjectVisitorEx<O> visitor) {
        return visitor.visit(this);
    }

    public void accept(OWLAxiomVisitor visitor) {
        visitor.visit(this);
    }

    public <O> O accept(OWLAxiomVisitorEx<O> visitor) {
        return visitor.visit(this);
    }

    @Override
	public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OWLHasKeyAxiom)) {
            return false;
        }
        OWLHasKeyAxiom other = (OWLHasKeyAxiom) obj;
        return getClassExpression().equals(other.getClassExpression()) && getPropertyExpressions().equals(other.getPropertyExpressions()) && other.getAnnotations().equals(getAnnotations());
    }
    
    /* (non-Javadoc)
	 * @see org.hypergraphdb.HGLink#getArity()
	 */
	@Override
	public int getArity() {
		return 1 + propertyExpressionsHandles.size();
	}

	/* (non-Javadoc)
	 * @see org.hypergraphdb.HGLink#getTargetAt(int)
	 */
	@Override
	public HGHandle getTargetAt(int i) {
		if (!(i >= 0 && i < getArity())) throw new IllegalArgumentException("Index has to be 0 and less than " + getArity());
		if (i == 0) {
			return expressionHandle;
		} else {
			return propertyExpressionsHandles.get(i - 1);
		}
	}

	/* (non-Javadoc)
	 * @see org.hypergraphdb.HGLink#notifyTargetHandleUpdate(int, org.hypergraphdb.HGHandle)
	 */
	@Override
	public void notifyTargetHandleUpdate(int i, HGHandle handle) {
		assert(getHyperGraph().get(handle) instanceof OWLClassExpression);
		
		if (!(i >= 0 && i < getArity())) throw new IllegalArgumentException("Index has to be 0 and less than " + getArity()); 
		if (handle == null) throw new IllegalArgumentException("handle null"); 
		if (i == 0) {
			expressionHandle = handle;
		} else {
			propertyExpressionsHandles.set(i - 1, handle);
		}
	}

	/* (non-Javadoc)
	 * @see org.hypergraphdb.HGLink#notifyTargetRemoved(int)
	 */
	@Override
	public void notifyTargetRemoved(int i) {
		if (!(i >= 0 && i < getArity())) throw new IllegalArgumentException("Index has to be 0 and less than " + getArity()); 
		if (i == 0) {
			expressionHandle = null;
		} else {
			propertyExpressionsHandles.set(i - 1, null);
		}
	}
}