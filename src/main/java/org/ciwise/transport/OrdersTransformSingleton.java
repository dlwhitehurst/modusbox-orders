/**
 * Anypoint Connector
 *
 * Copyright (c) CI Wise Inc.  All rights reserved.  http://www.ciwise.com
 *
 * The software in this package is published under the terms of the Apache
 * version 2.0 license, a copy of which has been included with this distribution 
 * in the LICENSE.md file.
 */
package org.ciwise.transport;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.ciwise.common.BaseObject;
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
		this.transportObj = new ArrayList<OrdersValueObject>();
	}
	
	/* (non-Javadoc)
	 * @see org.mule.api.lifecycle.Callable#onCall(org.mule.api.MuleEventContext)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {

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
		itemValueObj.setOrderId(valueObj.getOrderId());
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
