package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.Erp_Customer_Dao;
import db.DB;
import db.DbException;
import entities.Erp_Customer;

public class Erp_Customer_Dao_Impl_JDBC implements Erp_Customer_Dao {
	
	private Connection conn;
	
	public Erp_Customer_Dao_Impl_JDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Erp_Customer findById(Integer id_Cust) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
					"select * " +
					"from `erp`.`erp_customer` " +
					"where erp_customer.id_Cust = ?"
					);
			
			ps.setInt(1, id_Cust);
			rs = ps.executeQuery();
			if (rs.next() ) {
				Erp_Customer erp_Customer = instantiate_Erp_Customer(rs);
				return erp_Customer;
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
	public List<Erp_Customer> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
					"select * " +
					"from `erp`.`erp_customer` " +
					"order by id_Cust"
					);
			
			rs = ps.executeQuery();
			List<Erp_Customer> list_Erp_Customer = new ArrayList<>();
			while (rs.next() ) {
				Erp_Customer erp_Customer = instantiate_Erp_Customer(rs);
				list_Erp_Customer.add(erp_Customer);
			}
			return list_Erp_Customer;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void insert(Erp_Customer obj_Cust) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"insert " +
					"into `erp`.`erp_customer` " +
					"(`name_cust`, `email_cust`) " +
					"values " +
					"(			?,			  ?)",
					Statement.RETURN_GENERATED_KEYS
					);
			
			ps.setString(1, obj_Cust.getName_Cust());
			ps.setString(2, obj_Cust.getEmail_Cust());
			
			int insertedRecords = ps.executeUpdate();
			if (insertedRecords > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj_Cust.setId_Cust(id);
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
	public void update(Erp_Customer obj_Cust) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"update " +
					"`erp`.`erp_customer` " +
					"set `name_cust` = ?, `email_cust` = ? " +
					"where `id_cust` = ?"
					);
			
			ps.setString(1, obj_Cust.getName_Cust());
			ps.setString(2, obj_Cust.getEmail_Cust());
			ps.setInt(3, obj_Cust.getId_Cust());
			
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
	public void deleteById(Integer id_Cust) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(
					"delete " +
					"from `erp`.`erp_customer` " +
					"where id_cust = ?"
					);
			
			ps.setInt(1, id_Cust);
			
			int deletedRecord = ps.executeUpdate();
			if(deletedRecord > 0) {
				System.out.println("THIS RECORD:\nid_Cust = " +
								   id_Cust +
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

	private Erp_Customer instantiate_Erp_Customer(ResultSet rs) throws SQLException {
		Erp_Customer erp_Customer = new Erp_Customer();
		erp_Customer.setId_Cust(rs.getInt("id_cust"));
		erp_Customer.setName_Cust(rs.getString("name_cust"));
		erp_Customer.setEmail_Cust(rs.getString("email_cust"));
		return erp_Customer;
	}
}