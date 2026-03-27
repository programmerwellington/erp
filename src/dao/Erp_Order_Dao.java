package dao;

import java.util.List;

import entities.Erp_Order;

public interface Erp_Order_Dao {
	void insert(Erp_Order obj_Order);
	void update(Erp_Order obj_Order);
	void deleteById(Integer id_Order);
	Erp_Order findById(Integer id_Order);
	List<Erp_Order> findAll();
}