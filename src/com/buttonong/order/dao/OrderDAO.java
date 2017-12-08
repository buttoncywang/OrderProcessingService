package com.buttonong.order.dao;

import java.sql.SQLException;

import com.buttonong.order.model.Order;

public interface OrderDAO {
	public int Create(Order order) throws SQLException;
	public Order Read(int Id) throws SQLException;
	public int Update(Order order)throws SQLException;
	public int Delete(int Id) throws SQLException;
}
