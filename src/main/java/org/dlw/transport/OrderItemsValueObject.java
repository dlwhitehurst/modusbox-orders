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
public class OrderItemsValueObject extends BaseObject {
	private int orderItemId;
//	private int orderId; // parent
	private int itemId;
	private String itemName;
	private String itemCost;
	private int itemCount;
	/* (non-Javadoc)
	 * @see org.ciwise.common.BaseObject#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see org.ciwise.common.BaseObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see org.ciwise.common.BaseObject#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * @return the orderItemId
	 */
	public int getOrderItemId() {
		return orderItemId;
	}
	/**
	 * @param orderItemId the orderItemId to set
	 */
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	/**
	 * @return the orderId
	 */
//	public int getOrderId() {
//		return orderId;
//	}
	/**
	 * @param orderId the orderId to set
	 */
//	public void setOrderId(int orderId) {
//		this.orderId = orderId;
//	}
	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * @return the itemCost
	 */
	public String getItemCost() {
		return itemCost;
	}
	/**
	 * @param itemCost the itemCost to set
	 */
	public void setItemCost(String itemCost) {
		this.itemCost = itemCost;
	}
	/**
	 * @return the itemCount
	 */
	public int getItemCount() {
		return itemCount;
	}
	/**
	 * @param itemCount the itemCount to set
	 */
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	
	
}
