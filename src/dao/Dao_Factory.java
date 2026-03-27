package dao;

import dao.impl.Erp_Customer_Dao_Impl_JDBC;
import dao.impl.Erp_Order_Dao_Impl_JDBC;
import dao.impl.Erp_Order_Product_Dao_Impl_JDBC;
import dao.impl.Erp_Product_Dao_Impl_JDBC;
import dao.impl.Erp_Unit_Product_Dao_Impl_JDBC;
import db.DB;

public class Dao_Factory {
	public static Erp_Customer_Dao create_Erp_Customer_Dao() {
		return new Erp_Customer_Dao_Impl_JDBC(DB.getConnection());
	}
	
	public static Erp_Unit_Product_Dao create_Erp_Unit_Product_Dao() {
		return new Erp_Unit_Product_Dao_Impl_JDBC(DB.getConnection());
	}
	
	public static Erp_Product_Dao create_Erp_Product_Dao() {
		return new Erp_Product_Dao_Impl_JDBC(DB.getConnection());
	}
	
	public static Erp_Order_Dao create_Erp_Order_Dao() {
		return new Erp_Order_Dao_Impl_JDBC(DB.getConnection());
	}
	
	public static Erp_Order_Product_Dao create_Erp_Order_Product_Dao() {
		return new Erp_Order_Product_Dao_Impl_JDBC(DB.getConnection());
	}
}