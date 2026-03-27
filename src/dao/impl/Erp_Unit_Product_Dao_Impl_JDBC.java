package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.Erp_Unit_Product_Dao;
import db.DB;
import db.DbException;
import entities.Erp_Unit_Product;

public class Erp_Unit_Product_Dao_Impl_JDBC implements Erp_Unit_Product_Dao {
	
	private Connection conn;
	
	public Erp_Unit_Product_Dao_Impl_JDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Erp_Unit_Product findById(Integer id_unit_prod) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
					"select * " +
					"from `erp`.`erp_unit_product` " +
					"where Erp_Unit_Product.id_unit_prod = ?"
					);
			
			ps.setInt(1, id_unit_prod);
			rs = ps.executeQuery();
			if (rs.next() ) {
				Erp_Unit_Product erp_Unit_Product = instantiate_Erp_Unit_Product(rs);
				return erp_Unit_Product;
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
	public List<Erp_Unit_Product> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
					"select * " +
					"from `erp`.`erp_unit_product` " +
					"order by id_unit_prod"
					);
			
			rs = ps.executeQuery();
			List<Erp_Unit_Product> list_Erp_Unit_Product = new ArrayList<>();
			while (rs.next() ) {
				Erp_Unit_Product erp_Unit_Product = instantiate_Erp_Unit_Product(rs);
				list_Erp_Unit_Product.add(erp_Unit_Product);
			}
			return list_Erp_Unit_Product;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void insert(Erp_Unit_Product obj_Unit) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"insert " +
					"into `erp`.`erp_unit_product` " +
					"(`desc_unit_prod`, `abbrev_unit_prod`) " +
					"values " +
					"(				?,					?)",
					Statement.RETURN_GENERATED_KEYS
					);
			
			ps.setString(1, obj_Unit.getDesc_Unit_Prod());
			ps.setString(2, obj_Unit.getAbbrev_Unit_Prod());
			
			int insertedRecords = ps.executeUpdate();
			if (insertedRecords > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj_Unit.setId_Unit_Prod(id);
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
	public void update(Erp_Unit_Product obj_Unit) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"update " +
					"`erp`.`erp_unit_product` " +
					"set desc_unit_prod = ?, abbrev_unit_prod = ? " +
					"where id_unit_prod = ?"
					);
			
			ps.setString(1, obj_Unit.getDesc_Unit_Prod());
			ps.setString(2, obj_Unit.getAbbrev_Unit_Prod());
			ps.setInt(3, obj_Unit.getId_Unit_Prod());
			
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
	public void deleteById(Integer id_unit_prod) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(
					"delete " +
					"from `erp`.`erp_unit_product` " +
					"where id_unit_prod = ?"
					);
			
			ps.setInt(1, id_unit_prod);
			
			int deletedRecord = ps.executeUpdate();
			if(deletedRecord > 0) {
				System.out.println("THIS RECORD:\nid_unit_product = " +
								   id_unit_prod +
								   "\nWAS DELETED!.\n"
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

	private Erp_Unit_Product instantiate_Erp_Unit_Product(ResultSet rs) throws SQLException {
		Erp_Unit_Product erp_Unit_Product = new Erp_Unit_Product();
		erp_Unit_Product.setId_Unit_Prod(rs.getInt("id_unit_prod"));
		erp_Unit_Product.setDesc_Unit_Prod(rs.getString("desc_unit_prod"));
		erp_Unit_Product.setAbbrev_Unit_Prod(rs.getString("abbrev_unit_prod"));
		return erp_Unit_Product;
	}
}