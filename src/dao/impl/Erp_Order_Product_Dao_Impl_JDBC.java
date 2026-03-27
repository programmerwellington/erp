package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Erp_Order_Product_Dao;
import db.DB;
import db.DbException;
import entities.Erp_Order_Product;

public class Erp_Order_Product_Dao_Impl_JDBC implements Erp_Order_Product_Dao {
	
	private Connection conn;
	
	public Erp_Order_Product_Dao_Impl_JDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Erp_Order_Product findById(Integer id_Order, Integer id_Prod) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
					"select * " +
					"from `erp`.`erp_order_product` " +
					"where id_order = ? " +
					"and id_prod = ?"
					);
			
			ps.setInt(1, id_Order);
			ps.setInt(2, id_Prod);
			rs = ps.executeQuery();
			if (rs.next() ) {
				Erp_Order_Product erp_Order_Product = instantiate_Erp_Order_Product(rs);
				return erp_Order_Product;
			}
			return null;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Erp_Order_Product> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
					"select * " +
					"from `erp`.`erp_order_product` " +
					"order by id_order, id_prod"
					);
			
			rs = ps.executeQuery();
			List<Erp_Order_Product> list_Erp_Order_Product = new ArrayList<>();
			while (rs.next() ) {
				Erp_Order_Product erp_Order_Product = instantiate_Erp_Order_Product(rs);
				list_Erp_Order_Product.add(erp_Order_Product);
			}
			return list_Erp_Order_Product;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void insert(Erp_Order_Product obj_Erp_Order_Product) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"insert " +
					"into `erp`.`erp_order_product` " +
					"(`id_order`, `id_prod`, `unit_prod`, `quant_prod`, `price_prod`) " +
					"values " +
					"(		   ?,		  ?,		   ?,			 ?,			   ?)"
					);
			
			ps.setInt(1, obj_Erp_Order_Product.getId_Order());
			ps.setInt(2, obj_Erp_Order_Product.getId_Product());
			ps.setString(3, obj_Erp_Order_Product.getUnit_Prod());
			ps.setDouble(4, obj_Erp_Order_Product.getQuant_Prod());
			ps.setDouble(5, obj_Erp_Order_Product.getPrice_Prod());
			
			int insertedRecords = ps.executeUpdate();
			if (insertedRecords <= 0) {
				throw new DbException("Error: No inserted records!");
			}
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void update(Erp_Order_Product obj_Erp_Order_Product) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"update " +
					"`erp`.`erp_order_product` " +
					"set `id_order` = ?, `id_prod` =  ?, `unit_prod` =  ?, `quant_prod` =  ?, `price_prod` = ? " +
					"where id_order = ? " +
					"and id_prod = ?"
					);
			
			ps.setInt(1, obj_Erp_Order_Product.getId_Order());
			ps.setInt(2, obj_Erp_Order_Product.getId_Product());
			ps.setString(3, obj_Erp_Order_Product.getUnit_Prod());
			ps.setDouble(4, obj_Erp_Order_Product.getQuant_Prod());
			ps.setDouble(5, obj_Erp_Order_Product.getPrice_Prod());
			ps.setInt(6, obj_Erp_Order_Product.getId_Order());
			ps.setInt(7, obj_Erp_Order_Product.getId_Product());
			
			int updatedRecord = ps.executeUpdate();
			
			if(updatedRecord > 0) {
				System.out.println("Updated record!");
			} else {
				throw new DbException("This record don't exist!");
			}
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void deleteById(Integer id_Order, Integer id_Prod) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(
					"delete " +
					"from `erp`.`erp_order_product` " +
					"where id_order = ? " +
					"and id_prod = ?"
					);
			
			ps.setInt(1, id_Order);
			ps.setInt(2, id_Prod);
			
			int deletedRecord = ps.executeUpdate();
			if(deletedRecord > 0) {
				System.out.println("THIS RECORD:\nid_order = " +
								   id_Order +
								   " and id_prod = " +
								   id_Prod +
								   "\nWAS DELETED!\n"
								   );
			} else {
				throw new DbException("This record don't exist!");
			}
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
		}
		
	}

	private Erp_Order_Product instantiate_Erp_Order_Product(ResultSet rs) throws SQLException {
		Erp_Order_Product erp_Order_Product = new Erp_Order_Product();
		erp_Order_Product.setId_Order(rs.getInt("id_order"));
		erp_Order_Product.setId_Product(rs.getInt("id_prod"));
		erp_Order_Product.setUnit_Prod(rs.getString("unit_prod"));
		erp_Order_Product.setQuant_Prod(rs.getDouble("quant_prod"));
		erp_Order_Product.setPrice_Prod(rs.getDouble("price_prod"));
		
		return erp_Order_Product;
	}
}