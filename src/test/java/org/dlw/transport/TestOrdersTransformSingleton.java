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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dlw.transport.OrdersTransformSingleton;
import org.dlw.transport.OrdersValueObject;
import org.junit.Test;
import org.mule.DefaultMuleEventContext;
import org.mule.api.MuleEvent;
import org.mule.tck.junit4.AbstractMuleContextTestCase;

/**
 * @author <a href="mailto:david@ciwise.com">David L. Whitehurst</a>
 *
 */
public class TestOrdersTransformSingleton extends AbstractMuleContextTestCase {
	
	@Test
	public void testOrdersTransformSingleton() {
		OrdersTransformSingleton obj = new OrdersTransformSingleton(); // Spring controlling the singleton aspect
		assertNotNull(obj);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testCallableInterface() {
		 // Create test data
		  LinkedList<Map> payload = new LinkedList<Map>();
		  payload.add(getMap());
		  MuleEvent event = null;
		  List<OrdersValueObject> list = null;
		  try {
			event = getTestEvent(payload, muleContext);
			list = (List<OrdersValueObject>) new OrdersTransformSingleton().onCall(new DefaultMuleEventContext(event));
		  } catch (Exception e) {
			e.printStackTrace();
		 }
	      
	      // Assert test data
		  if (list != null) {
			  OrdersValueObject obj = list.get(0);
			  assertEquals(obj.getCustomerName(), "Popeye TheSailor");
		  }
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map getMap() {
		Map map = new HashMap();
		map.put("orderId", 1);
		map.put("orderItemId", 2);
		map.put("itemId", 2);
		map.put("itemName", "Salt Water Taffy");
		map.put("itemCost", "$3.50");
		map.put("itemCount", "5");
		map.put("customerName", "Popeye TheSailor");
		map.put("placementDate", 1234556677);
		
		return map;
	}
}
