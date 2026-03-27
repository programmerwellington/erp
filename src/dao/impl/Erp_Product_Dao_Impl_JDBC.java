package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.Erp_Product_Dao;
import db.DB;
import db.DbException;
import entities.Erp_Product;

public class Erp_Product_Dao_Impl_JDBC implements Erp_Product_Dao {
	
	private Connection conn;
	
	public Erp_Product_Dao_Impl_JDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Erp_Product findById(Integer id_prod) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
					"select * " +
					"from `erp`.`erp_product` " +
					"where id_prod = ?");
			
			ps.setInt(1, id_prod);
			rs = ps.executeQuery();
			if (rs.next() ) {
				Erp_Product erp_Product = instantiate_Erp_Product(rs);
				return erp_Product;
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
	public List<Erp_Product> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
					"select * " +
					"from `erp`.`erp_product` " +
					"order by id_prod");
			
			rs = ps.executeQuery();
			List<Erp_Product> list_Erp_Product = new ArrayList<>();
			while (rs.next() ) {
				Erp_Product erp_Product = instantiate_Erp_Product(rs);
				list_Erp_Product.add(erp_Product);
			}
			return list_Erp_Product;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void insert(Erp_Product obj_Prod) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"insert " +
					"into `erp`.`erp_product` " +
					"(`name_prod`, `unit_prod`, `price_prod`) " +
					"values " +
					"(			?,			 ?,			   ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, obj_Prod.getName_Prod());
			ps.setString(2, obj_Prod.getUnit_Prod());
			ps.setDouble(3, obj_Prod.getPrice_Prod());
			
			int insertedRecords = ps.executeUpdate();
			if (insertedRecords > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj_Prod.setId_Prod(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Error: No inserted records!");
			}
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void update(Erp_Product obj_Prod) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"update " +
					"`erp`.`erp_product` " +
					"set `name_prod` = ?, `unit_prod` = ?, `price_prod` = ? " +
					"where id_prod = ?"
					);
			
			ps.setString(1, obj_Prod.getName_Prod());
			ps.setString(2, obj_Prod.getUnit_Prod());
			ps.setDouble(3, obj_Prod.getPrice_Prod());
			ps.setInt(4, obj_Prod.getId_Prod());
			
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
	public void deleteById(Integer id_prod) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(
					"delete " +
					"from `erp`.`erp_product` " +
					"where id_prod = ?"
					);
			
			ps.setInt(1, id_prod);
			
			int deletedRecord = ps.executeUpdate();
			if(deletedRecord > 0) {
				System.out.println("THIS RECORD:\nid_product = " +
								   id_prod +
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

	private Erp_Product instantiate_Erp_Product(ResultSet rs) throws SQLException {
		Erp_Product erp_Product = new Erp_Product();
		erp_Product.setId_Prod(rs.getInt("id_prod"));
		erp_Product.setName_Prod(rs.getString("name_prod"));
		erp_Product.setUnit_Prod(rs.getString("unit_prod"));
		erp_Product.setPrice_Prod(rs.getDouble("price_prod"));
		return erp_Product;
	}
}