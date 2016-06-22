/**
 * Copyright (c) 2016 David L. Whitehurst
 *
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated documentation 
 * files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, 
 * publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, 
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included 
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL 
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER 
 * DEALINGS IN THE SOFTWARE.
 */

package org.dlw.transport;

import java.util.StringTokenizer;

import org.dlw.common.BaseObject;
import org.dlw.util.AuthHelper;
import org.joda.time.DateTime;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

/**
 * @author <a href="mailto:david@ciwise.com">David L. Whitehurst</a>
 *
 */
public class AuthenticationSingleton extends BaseObject implements Callable {

	/* (non-Javadoc)
	 * @see org.mule.api.lifecycle.Callable#onCall(org.mule.api.MuleEventContext)
	 */
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		MuleMessage msg = eventContext.getMessage();
		TokenInfo obj = null;
		
		if (msg.getInboundProperty("http.relative.path").equals("/api/v1/auth")) {
			// authenticate and return TokenInfo
			if (validateBasicAuthenticationAttempt(msg)) {
				String user = getBasicAuthenticationUser(msg);
				String password = getBasicAuthenticationPassword(msg);
				if (authenticate(user,password)) {
					// success!
					System.out.println("SUCCESS: Authenticated!");
					
					// get token and return TokenInfo
					//String token = AuthHelper.createJsonWebToken(user, new Long(1));
					String token = "A5Fff444000H3d5j.3d8guh9G0w563BN2aD.2RtiR784Nbplw24";
					obj = new TokenInfo();
					obj.setUser(user);
					obj.setToken(token);
					DateTime now = new DateTime();
					obj.setIssued(now.toString());
					DateTime expires = now.plusDays(1);
					obj.setExpires(expires.toString());
					msg.setPayload(obj);
				} else {
					// nothing yet
					msg.setPayload(null);
				}
			} else {
				// return correct error response
				msg.setPayload(null);

			}
		} else {
			// verify authentication/authorization
			if (validateTokenAuthorizationAttempt(msg)) {
				//String auth = msg.getInboundProperty("authorization");
				//String basic = auth.substring(0, 6); // should be "Basic"
				//StringTokenizer toker = new StringTokenizer(auth, " ");
				//toker.nextToken(); // waste first token
				//String token = toker.nextToken();
				String token = "A5Fff444000H3d5j.3d8guh9G0w563BN2aD.2RtiR784Nbplw24";
				if (token.equals("A5Fff444000H3d5j.3d8guh9G0w563BN2aD.2RtiR784Nbplw24")) {
					// get token and return TokenInfo
					obj = new TokenInfo();
					obj.setUser("David");
					obj.setToken(token);
					DateTime now = new DateTime();
					obj.setIssued(now.toString());
					DateTime expires = now.plusDays(1);
					obj.setExpires(expires.toString());
					msg.setPayload(obj);
				
				} else {
					// return error
					msg.setPayload(null);
				}
			} else {
				// return correct error response
				msg.setPayload(null);
			}
		}
		return msg;
	}
	
	private boolean authenticate(String user, String password) {
		boolean retVal = false;
		
		if (user.equals("david") && password.equals("whitehurst")) {
			retVal = true;
		}
		
		return retVal;
	}
	
	private String getBasicAuthenticationUser(MuleMessage msg) {
		String auth =  msg.getInboundProperty("authorization");
		String encoded = auth.substring(6);
		String unencoded = AuthHelper.decodeBase64encoded(encoded);
		
		return getUserPassToken(unencoded, 1);
	}
	
	private String getBasicAuthenticationPassword(MuleMessage msg) {
		String auth =  msg.getInboundProperty("authorization");
		String encoded = auth.substring(6);
		String unencoded = AuthHelper.decodeBase64encoded(encoded);
		
		return getUserPassToken(unencoded, 2);
	}

	private String getUserPassToken(String userPass, int index) {
		
		String retStr = null;
		StringTokenizer toker = new StringTokenizer(userPass, ":");

		if (index == 1) {
			retStr = toker.nextToken();
		}
		if (index == 2) {
			String waste = toker.nextToken();
			retStr = toker.nextToken();
		}
		
		return retStr;
	}
	
	private boolean validateBasicAuthenticationAttempt(MuleMessage msg) {
		boolean retVal = false;
		String auth = msg.getInboundProperty("authorization");
		String basic = auth.substring(0, 6); // should be "Basic"
		if (basic.equals("Basic ") && auth.length() > 6) {
			retVal = true;
		}
		return retVal;
	}

	@SuppressWarnings("unused")
	private boolean validateTokenAuthorizationAttempt(MuleMessage msg) {
		boolean retVal = false;
		retVal = true;
		return retVal;
		
		// Postman converting Authorization header to "Basic Og===" Not sure why.
		
		/*
		String auth = msg.getInboundProperty("authorization");
		StringTokenizer toker = new StringTokenizer(auth, ":");
		String check = toker.nextToken().substring(0, 5);
		if (check.equals("Bearer")) {
			retVal = true;
		}
		return retVal;
		*/
	}
	
	/* (non-Javadoc)
	 * @see org.dlw.common.BaseObject#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.dlw.common.BaseObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.dlw.common.BaseObject#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
