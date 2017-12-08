package com.buttonong.order.bo;

import com.buttonong.order.bo.Exception.BOException;
import com.buttonong.order.model.Order;

public interface OrderBO {
	public boolean placeOrder(Order order) throws BOException;
	public boolean cancelOrder(int Id) throws BOException;
	public boolean deleteOrder(int Id) throws BOException;
	
	
}
