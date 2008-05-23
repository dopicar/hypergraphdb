package org.hypergraphdb.app.wordnet.data;

/*
 * A <code>VerbFrameLink</code> is the frame of a sentence in which 
 * it is proper to use a given verb.
 */
public class VerbFrame {

	private String text;
    //frame index as defined in frames.verb
	private int index;
	
	public VerbFrame(){}
	
	public VerbFrame(String text, int index){
		this.text = text;
		this.index = index;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public boolean equals(Object obj)
	{
		return (obj instanceof VerbFrame) && (index == ((VerbFrame) obj).index) 
		&& (text.equals(((VerbFrame) obj).text));
	}
	
}
