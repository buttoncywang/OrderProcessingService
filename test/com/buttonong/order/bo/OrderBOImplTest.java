package com.buttonong.order.bo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import com.buttonong.order.bo.Exception.BOException;
import com.buttonong.order.dao.OrderDAO;
import com.buttonong.order.model.Order;

class OrderBOImplTest {
	@Mock
	OrderDAO dao; //使用mockito作為dao的mock up
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void placeOrder_Shoudl_Create_An_Order() throws SQLException, BOException {
		OrderBOImpl bo = new OrderBOImpl();
		bo.setOrderDAO(dao);
		Order order =new Order();
		//Mockito模仿dao class中的create方法
		when(dao.Create(order)).thenReturn(new Integer(1));
		
		boolean result = bo.placeOrder(order);
		
		assertTrue(result);
		verify(dao).Create(order);
	}

}
