package dao;

import java.util.List;

import entities.Erp_Product;

public interface Erp_Product_Dao {
	void insert(Erp_Product obj_Prod);
	void update(Erp_Product obj_Prod);
	void deleteById(Integer id_Prod);
	Erp_Product findById(Integer id_Prod);
	List<Erp_Product> findAll();
}