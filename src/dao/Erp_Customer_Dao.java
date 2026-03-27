package dao;

import java.util.List;

import entities.Erp_Customer;

public interface Erp_Customer_Dao {
	void insert(Erp_Customer obj_Cust);
	void update(Erp_Customer obj_Cust);
	void deleteById(Integer id_Cust);
	Erp_Customer findById(Integer id_Cust);
	List<Erp_Customer> findAll();
}