package dao;

import java.util.List;

import entities.Erp_Unit_Product;

public interface Erp_Unit_Product_Dao {
	void insert(Erp_Unit_Product obj_Unit_Prod);
	void update(Erp_Unit_Product obj_Unit_Prod);
	void deleteById(Integer id_Unit_Prod);
	Erp_Unit_Product findById(Integer id_Unit_Prod);
	List<Erp_Unit_Product> findAll();
}