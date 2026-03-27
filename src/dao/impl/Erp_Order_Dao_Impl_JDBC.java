package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.Erp_Order_Dao;
import db.DB;
import db.DbException;
import entities.Erp_Order;

public class Erp_Order_Dao_Impl_JDBC implements Erp_Order_Dao {
	
	private Connection conn;
	
	public Erp_Order_Dao_Impl_JDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Erp_Order findById(Integer id_order) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
					"select * " +
					"from `erp`.`erp_order` " +
					"where id_order = ?");
			
			ps.setInt(1, id_order);
			rs = ps.executeQuery();
			if (rs.next() ) {
				Erp_Order erp_Order = instantiate_Erp_Order(rs);
				return erp_Order;
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
	public List<Erp_Order> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
					"select * " +
					"from `erp`.`erp_order` " +
					"order by id_order");
			
			rs = ps.executeQuery();
			List<Erp_Order> list_Erp_Order = new ArrayList<>();
			while (rs.next() ) {
				Erp_Order erp_Order = instantiate_Erp_Order(rs);
				list_Erp_Order.add(erp_Order);
			}
			return list_Erp_Order;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void insert(Erp_Order obj_Order) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"insert " +
					"into `erp`.`erp_order` " +
					"(`id_cust_order`, `moment_order`, `total_order`, `status_order`) " +
					"values " +
					"(				?,				?,			   ?,			   ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, obj_Order.getId_Cust_Order());
			ps.setDate(2, new java.sql.Date(obj_Order.getMoment_Order().getTime()));
			ps.setDouble(3, obj_Order.getTotal_Order());
			ps.setInt(4, obj_Order.getStatus_Order());
			
			int insertedRecords = ps.executeUpdate();
			if (insertedRecords > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj_Order.setId_Order(id);
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
	public void update(Erp_Order obj_Order) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"update " +
					"`erp`.`erp_order` " +
					"set `id_cust_order` = ?, `moment_order` = ?, `total_order` = ?, `status_order` = ? " +
					"where id_order = ?"
					);
			
			ps.setInt(1, obj_Order.getId_Cust_Order());
			ps.setDate(2, new java.sql.Date(obj_Order.getMoment_Order().getTime()));
			ps.setDouble(3, obj_Order.getTotal_Order());
			ps.setInt(4, obj_Order.getStatus_Order());
			ps.setInt(5, obj_Order.getId_Order());
			
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
	public void deleteById(Integer id_order) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(
					"delete " +
					"from `erp`.`erp_order` " +
					"where id_order = ?"
					);
			
			ps.setInt(1, id_order);
			
			int deletedRecord = ps.executeUpdate();
			if(deletedRecord > 0) {
				System.out.println("THIS RECORD:\nid_order = " +
								   id_order +
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

	private Erp_Order instantiate_Erp_Order(ResultSet rs) throws SQLException {
		Erp_Order erp_Order = new Erp_Order();
		erp_Order.setId_Order(rs.getInt("id_order"));
		erp_Order.setId_Cust_Order(rs.getInt("id_cust_order"));
		erp_Order.setMoment_Order(rs.getDate("moment_order"));
		erp_Order.setTotal_Order(rs.getDouble("total_order"));
		erp_Order.setStatus_Order(rs.getInt("status_order"));
		return erp_Order;
	}
}