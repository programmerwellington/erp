package dao;

import java.util.List;

import entities.Erp_Order_Product;

public interface Erp_Order_Product_Dao {
	Erp_Order_Product findById(Integer id_Order, Integer id_Prod);
	List<Erp_Order_Product> findAll();
	void insert(Erp_Order_Product obj_Order_Prod);
	void update(Erp_Order_Product obj_Order_Prod);
	void deleteById(Integer id_order, Integer id_prod);
}