package org.hypergraphdb.peer.jxta;

import java.io.IOException;
import java.io.OutputStream;

import net.jxta.document.Advertisement;
import net.jxta.peer.PeerID;
import net.jxta.peergroup.PeerGroup;
import net.jxta.protocol.PipeAdvertisement;
import net.jxta.socket.JxtaSocket;

import org.hypergraphdb.peer.PeerRelatedActivity;
import org.hypergraphdb.peer.protocol.Message;
import org.hypergraphdb.peer.protocol.Protocol;

/**
 * @author Cipri Costa
 *
 * An Activity that will send the attached message to the sent destination. If the ReplyTo address of the message is 
 * null, the address of the current peer will be set.
 */

public class JXTASendActivity extends PeerRelatedActivity
{
	private PeerGroup peerGroup;
	private Advertisement pipeAdv;
	
	public JXTASendActivity(PeerGroup peerGroup, Advertisement pipeAdv)
	{
		this.peerGroup = peerGroup;
		this.pipeAdv = pipeAdv;
	}


	public void run()
	{
		try
		{
			PipeAdvertisement targetPipeAdv = null;
			PeerID peerId = null;
			
			//recreate destination advertisement based on the type of the target attribute
			if (target instanceof PipeAdvertisement)
			{
				targetPipeAdv = (PipeAdvertisement)target;
			}
			
			System.out.println("Sending " + msg + " to adv: " + targetPipeAdv.getName());
			
 			JxtaSocket socket = new JxtaSocket(peerGroup, peerId, targetPipeAdv, 5000, true);

	        OutputStream out = socket.getOutputStream();

	        Protocol protocol = new Protocol();
	        //send message
	        Message msg = getMessage();
	        
	        //set our self as replyTo if that was not already set.
	        if (msg.getReplyTo() == null)
	        {
	        	msg.setReplyTo(pipeAdv);
	        }
	        
	        //
	        protocol.writeMessage(out, getMessage());
	        out.flush();


		} catch (IOException e)
		{
			e.printStackTrace();
		} 
	}

}
