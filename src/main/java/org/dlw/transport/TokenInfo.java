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

import org.dlw.common.BaseObject;

/**
 * @author <a href="mailto:david@ciwise.com">David L. Whitehurst</a>
 *
 */
public class TokenInfo extends BaseObject {

	private String user;
	private String token;
    private String issued;
    private String expires;

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
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the issued
	 */
	public String getIssued() {
		return issued;
	}
	/**
	 * @param issued the issued to set
	 */
	public void setIssued(String issued) {
		this.issued = issued;
	}
	/**
	 * @return the expires
	 */
	public String getExpires() {
		return expires;
	}
	/**
	 * @param expires the expires to set
	 */
	public void setExpires(String expires) {
		this.expires = expires;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
}
