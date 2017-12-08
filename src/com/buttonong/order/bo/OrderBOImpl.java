package com.buttonong.order.bo;

import java.sql.SQLException;

import com.buttonong.order.bo.Exception.BOException;
import com.buttonong.order.dao.OrderDAO;
import com.buttonong.order.model.Order;

public class OrderBOImpl implements OrderBO {
	private OrderDAO orderDAO;

	@Override
	public boolean placeOrder(Order order) throws BOException {
		try {
			int result = orderDAO.Create(order);
			if(result==0) {
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		return true;
	}

	@Override
	public boolean cancelOrder(int Id) throws BOException {
		// TODO Auto-generated method stub
		try {
			Order order = orderDAO.Read(Id);
			order.setStatus("cancelled");
			int result = orderDAO.Update(order);
			if(result==0) {
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		return true;
	}

	@Override
	public boolean deleteOrder(int Id) throws BOException {
		try {
			int result = orderDAO.Delete(Id);
			if(result==0) {
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		return true;
	}

	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

}
