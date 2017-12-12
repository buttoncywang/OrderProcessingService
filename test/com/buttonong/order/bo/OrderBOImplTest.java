package com.buttonong.order.bo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import com.buttonong.order.bo.Exception.BOException;
import com.buttonong.order.dao.OrderDAO;
import com.buttonong.order.model.Order;

class OrderBOImplTest {
	@Mock
	OrderDAO dao; //使用mockito作為dao的mock up
	OrderBOImpl bo;
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		bo= new OrderBOImpl();
		bo.setOrderDAO(dao);
	}
	
	@Test
	void placeOrder_Shoudl_Create_An_Order() throws SQLException, BOException {

		Order order =new Order();
		//Mockito模仿dao class中的create方法
		when(dao.Create(order)).thenReturn(new Integer(1));
		boolean result = bo.placeOrder(order);
		assertTrue(result);
		verify(dao).Create(order);
	}
	
	/*建立BO失敗時的情況*/
	@Test
	public void placeOrder_Should_not_Create_An_Order()throws SQLException,BOException {
		Order order =new Order();
		//Mockito模仿dao class中的create方法
		when(dao.Create(order)).thenReturn(new Integer(0));
		
		boolean result = bo.placeOrder(order);
		
		assertFalse(result);
		verify(dao).Create(order);
	}
	
	/*測試function丟出Exception的情形*/
	@Test
	public void placeOrder_Should_throw_BOException()throws SQLException,BOException {
		Order order =new Order();
		//Mockito模仿dao class中的create方法
		assertThrows(BOException.class,() -> {
			when(dao.Create(order)).thenThrow(SQLException.class);
			boolean result = bo.placeOrder(order);
		});
	}
	
	@Test
	public void cancelOrder_Should_Cancel_The_Order() throws SQLException, BOException {
		Order order=new Order();
		when(dao.Read(123)).thenReturn(order); //先利用id取出要取消的BO單資訊
		when(dao.Update(order)).thenReturn(1);//將取出的BO單狀態更新為Cancel
		boolean result = bo.cancelOrder(123);
		
		assertTrue(result);
		verify(dao).Read(123);//驗證dao.read方法是否有執行
		verify(dao).Update(order);//驗證dao.update方法是否有執行
	}
	
	@Test
	public void cancelOrder_Should_not_Cancel_The_Order() throws SQLException, BOException {
		Order order=new Order();
		when(dao.Read(123)).thenReturn(order); //先利用id取出要取消的BO單資訊
		when(dao.Update(order)).thenReturn(0);//將取出的BO單狀態更新為Cancel
		boolean result = bo.cancelOrder(123);
		
		assertFalse(result);
		verify(dao).Read(123);//驗證dao.read方法是否有執行
		verify(dao).Update(order);//驗證dao.update方法是否有執行
	}
	
	@Test
	public void cancelOrder_Should_Throw_BO_Exception_On_Read() throws SQLException, BOException {
		Order order=new Order();
		assertThrows(BOException.class,()->{
			when(dao.Read(123)).thenThrow(SQLException.class);
			bo.cancelOrder(123);
		});

	}

	@Test
	public void cancelOrder_Should_Throw_A_BOException_On_Update() throws SQLException, BOException {
		Order order=new Order();
		assertThrows(BOException.class,()->{
			when(dao.Read(123)).thenReturn(order); //先利用id取出要取消的BO單資訊
			when(dao.Update(order)).thenThrow(SQLException.class);//將取出的BO單狀態更新為Cancel
			bo.cancelOrder(123);
		});
	}
	
}
