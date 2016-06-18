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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dlw.common.BaseObject;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

/**
 * @author <a href="mailto:david@ciwise.com">David L. Whitehurst</a>
 *
 */
public class OrdersTransformSingleton extends BaseObject implements Callable {

	private List<OrdersValueObject> transportObj;
	
	public OrdersTransformSingleton() {
		// wipe the transport object with every call
	}
	
	/* (non-Javadoc)
	 * @see org.mule.api.lifecycle.Callable#onCall(org.mule.api.MuleEventContext)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		// freshen
		this.transportObj = null;
		this.transportObj = new ArrayList<OrdersValueObject>();
		
		MuleMessage res = eventContext.getMessage();
		List<Map> list = (LinkedList) res.getPayload();

			for (int i=0; i<list.size(); i++) {
				// check orderId and search transportObj to determine if staged
				Map map = list.get(i);
				if (map != null) {
					if (!orderExists(map)) {
						OrdersValueObject valueObj = new OrdersValueObject();
						valueObj.setOrderId((Integer)map.get("orderId"));
						valueObj.setCustomerName((String)map.get("customerName"));
						valueObj.setPreparedDate((String)map.get("preparedDate"));
				
						createAddOrderItem(map, valueObj);
				
						// add valueObj to transportObj
						this.transportObj.add(valueObj);
					} else {
						OrdersValueObject valueObj = null;
						Integer id = (Integer) map.get("orderId");
						for (OrdersValueObject valObj: transportObj) {
							Integer valId = valObj.getOrderId();
							if (valId != null && id.intValue() == valId.intValue()) {
								valueObj = valObj;
							}
						}
						// check valueObj and add orderItem to valueObj orderItem list
						if (valueObj != null) {
							
							createAddOrderItem(map, valueObj);
							
						}
					}

				}
			}
		
		
		res.setPayload(transportObj);
		return res;
	}

	/**
	 * @param map
	 * @param valueObj
	 */
	private void createAddOrderItem(Map map, OrdersValueObject valueObj) {
		// create orderItem for list
		OrderItemsValueObject itemValueObj = new OrderItemsValueObject();
//		itemValueObj.setOrderId(valueObj.getOrderId());
		itemValueObj.setOrderItemId((Integer)map.get("orderItemId"));
		itemValueObj.setItemId((Integer)map.get("itemId"));
		itemValueObj.setItemName((String)map.get("itemName"));
		itemValueObj.setItemCost((String)map.get("itemCost"));
		itemValueObj.setItemCount((Integer)map.get("itemCount"));

		// add to list
		valueObj.getItems().add(itemValueObj);
	}


	/**
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private boolean orderExists(Map map) {
		boolean retVal = false;
		Integer id = (Integer) map.get("orderId");
		for (OrdersValueObject valObj: transportObj) {
			Integer valId = valObj.getOrderId();
			if (valId != null && id.intValue() == valId.intValue()) {
				retVal = true;
			}
		}
		return retVal;
	}

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

}
