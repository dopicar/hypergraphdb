/*
 * This file is part of the HyperGraphDB source distribution. This is copyrighted
 * software. For permitted uses, licensing options and redistribution, please see 
 * the LicensingInformation file at the root level of the distribution. 
 *
 * Copyright (c) 2005-2006
 *  Kobrix Software, Inc.  All rights reserved.
 */
package org.hypergraphdb;

/**
 * <p>
 * A default <code>HGLink</code> implementation provided by hypergraph. 
 * </p>
 * 
 * @author Borislav Iordanov
 */
public class HGPlainLink implements HGLink
{
	/**
	 * An array of <code>HGHandle</code>s representing the target set
	 * of this link. The array should never be <code>null</code>. An
	 * empty target set should be represented by an 0 length array.
	 */	
    protected HGHandle [] outgoingSet;
    
    /**
     * <p>
     *  A default constructor is needed when using Java serialization, but it should
     *  never be called by the outside world.
     * </p>
     */
    protected HGPlainLink()
    {
        outgoingSet = new HGHandle [0];
    }
    
    /**
     * <p>Construct a new link with the specified outgoing set.</p>
     * 
     * @param outgoingSet A non-null array of valid <code>HGHandle</code>s of length > 0.
     */
    public HGPlainLink(HGHandle...outgoingSet)
    {
        if (outgoingSet == null)
            throw new HGException("Attempt to construct a link with a null outgoing set. If the link has arity 0, please constructor with a 0 length array of atoms.");
        else
        {
            this.outgoingSet = outgoingSet;
        }
    }

    /**
     * <p>Return the number of elements in the target set of this link.</p>
     */
    public int getArity()
    {
        return outgoingSet.length;
    }
    
    /**
     * <p>Return the <code>i</code>th target of this link.</p>
     * 
     * @throws ArrayOutOfBoundsException if i >= <code>getArity()</code>.
     */
    public HGHandle getTargetAt(int i)
    {
        return outgoingSet[i];
    }    
    
    public void notifyTargetHandleUpdate(int i, HGHandle handle)
    {
        outgoingSet[i] = handle;
    } 
    
    public void notifyTargetRemoved(int i)
    {
    	HGHandle [] newOutgoing = new HGHandle[outgoingSet.length - 1];
    	System.arraycopy(outgoingSet, 0, newOutgoing, 0, i);
    	System.arraycopy(outgoingSet, i + 1, newOutgoing, i, outgoingSet.length - i -1);
    	outgoingSet = newOutgoing;
    }
}