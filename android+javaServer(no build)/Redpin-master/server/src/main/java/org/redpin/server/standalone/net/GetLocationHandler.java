/**
 *  Filename: GetLocationHandler (in org.redpin.server.standalone.net)
 *  This file is part of the Redpin project.
 * 
 *  Redpin is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published
 *  by the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  Redpin is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Redpin. If not, see <http://www.gnu.org/licenses/>.
 *
 *  (c) Copyright ETH Zurich, Pascal Brogle, Philipp Bolliger, 2010, ALL RIGHTS RESERVED.
 * 
 *  www.redpin.org
 */
package org.redpin.server.standalone.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

import org.redpin.server.standalone.core.Location;
import org.redpin.server.standalone.core.Measurement;
import org.redpin.server.standalone.json.GsonFactory;
import org.redpin.server.standalone.locator.LocatorHome;
import org.redpin.server.standalone.net.Response.Status;
import org.redpin.server.standalone.util.Log;

import com.google.gson.JsonElement;

/**
 * @see IHandler
 * @author Pascal Brogle (broglep@student.ethz.ch)
 *
 */
public class GetLocationHandler implements IHandler {

	
	private Logger log;	
	
	public GetLocationHandler() {
		log = Log.getLogger();
	}
	
	
	/**
	 * @see IHandler#handle(JsonElement)
	 */
	@Override
	public Response handle(JsonElement data) {
		Response res;
		Location loc;
		
		Measurement currentMeasurement = GsonFactory.getGsonInstance().fromJson(data, Measurement.class);
		log.finer("got measurement: " + data);
		
		loc = LocatorHome.getLocator().locate(currentMeasurement);
		
			
		if(loc == null) {
			log.fine("no matching location found");
			res = new Response(Status.failed, "no matching location found", null);
			
		} else {
			res = new Response(Status.ok, null, loc);
			log.finer("location found: " + loc + " accuracy: "+loc.getAccuracy());
		}
		log.finer("I am before try");
		try{
		    log.finer("I am in try");
		    sendhttppost(String.valueOf(loc.getId()));

		}catch(Exception e){
			e.printStackTrace();
		}
		
		return res;
	}

	public void sendhttppost(String loc_id) throws IOException{
		log.finer("I am in method for post");
		URL url = new URL("http://localhost:3000/");
	    URLConnection conn = url.openConnection();
	    conn.setDoOutput(true);
	    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
        String s = "navjot";
	   // writer.write("value=1&anotherValue=1");
	    writer.write("locationid="+ loc_id);
	  //  writer.write("&");
	   // writer.write("secondValue="+ s);
	    writer.flush();
	    String line;
	    log.finer("I am before bufferreader: " + loc_id);
	    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    while ((line = reader.readLine()) != null) {
	      System.out.println(line);
	    }
	    log.finer("I am before close");
	    writer.close();
	    reader.close();
	    log.finer("I am after close");

	}

	

}
