/*
 * This file is part of the XSD for HyperGraphDB source distribution. This is copyrighted
 * software. For permitted uses, licensing options and redistribution, please see
 * the LicensingInformation file at the root level of the distribution.
 *
 * Copyright (c) 2007
 * Kobrix Software, Inc.  All rights reserved.
 */
package org.hypergraphdb.app.xsd;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.type.HGAtomType;
import org.hypergraphdb.type.HGProjection;
import org.hypergraphdb.type.RecordType;
import org.hypergraphdb.type.Slot;
import org.xml.sax.Attributes;
import java.util.Collection;
import java.util.ArrayList;
import org.hypergraphdb.HGValueLink;
import org.hypergraphdb.HGPlainLink;
import org.hypergraphdb.HyperGraph;


/**
 * Imports XSD complexType's into the HGDB's type system.
 */
public class ComplexTypeImporter
{
    private SchemaImporter importer;
    private String name;
    private RecordType recordType;

    /**
     *
     * @param importer SchemaImporter
     */
    /*package*/ ComplexTypeImporter(SchemaImporter importer)
    {
       this.importer = importer;
    }

    /**
     *
     * @param attributes Attributes
     */
    public void startDefinition(Attributes attributes)
    {
    	/**@todo form the full name.*/
    	name = attributes.getValue("name");

    	recordType = new RecordType();
    	
    	Slot slot = new Slot();
    	slot.setLabel(name);
    	slot.setValueType(importer.hg.getTypeSystem().getTypeHandle(String.class));
    	HGHandle slotHandle = importer.hg.add(slot);
    	recordType.addSlot(slotHandle);
    }

    /**
     *
     */
    public void endDefinition()
    {
    	HGHandle theHandle = importer.hg.add(null, ComplexTypeConstructor.HANDLE);

    	HGValueLink link = new HGValueLink(recordType,
                new HGHandle[]
                {theHandle});
    	importer.hg.add(link);
    	
    	HGAtomType theType = importer.hg.getTypeSystem().getType(theHandle);

    	//HGHandle theHandle = importer.hg.add(recordType, ComplexTypeConstructor.HANDLE);
    	//HGAtomType theType = importer.hg.getTypeSystem().getType(theHandle);
    	
//        HGHandle[] elementsHandle = new HGHandle[elements.size()];
//
//        for(int i = 0; i<elementsHandle.length; i++)
//        {
//            elementsHandle[i] = importer.hg.add(elements[i]);
//        }

//        HGPlainLink elementsLink = new HGValueLink(elements, new HGHandle[] {aHandle} );
//        importer.hg.add(elementsLink);
//
//        HGPlainLink attsLink = new HGValueLink(atts, new HGHandle[] {aHandle} );
//        importer.hg.add(attsLink);

        importer.hg.getTypeSystem().addAlias(theHandle, name);
    }

    /**
     *
     * @param attributes Attributes
     */
    public void startSequence(Attributes attributes)
    {
    }

    /**
     *
     * @param attributes Attributes
     */
    public void startElement(Attributes attributes)
    {
        String name = attributes.getValue("name");
        String type = attributes.getValue("type");

        /**@todo hcoded to String temporarily.*/
    	Slot slot = new Slot(name,importer.hg.getTypeSystem().getTypeHandle(String.class));
    	HGHandle slotHandle = importer.hg.add(slot);
    	recordType.addSlot(slotHandle);
    }

    /**
     *
     * @param attributes Attributes
     */
    public void doAttribute(Attributes attributes)
    {
        String name = attributes.getValue("name");
        String type = attributes.getValue("type");

        /**@todo hcoded to String temporarily.*/
    	Slot slot = new Slot(name,importer.hg.getTypeSystem().getTypeHandle(String.class));
    	HGHandle slotHandle = importer.hg.add(slot);
    	recordType.addSlot(slotHandle);
    }

} //ComplexTypeImporter.
