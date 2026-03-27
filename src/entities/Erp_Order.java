package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Erp_Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id_Order;
	private Integer id_Cust_Order;
	private Date moment_Order;
	private Double total_Order;
	private Integer status_Order;
	
	private Set<Erp_Order_Product> products = new HashSet<>();
	
	public Erp_Order() {
	}

	public Erp_Order(Integer id_Order, Integer id_Cust_Order, Date moment_Order,
					Double total_Order,	Integer status_Order) {
		this.id_Order = id_Order;
		this.id_Cust_Order = id_Cust_Order;
		this.moment_Order = moment_Order;
		this.total_Order = total_Order;
		this.status_Order = status_Order;
	}

	public Integer getId_Order() {
		return id_Order;
	}

	public void setId_Order(Integer id_Order) {
		this.id_Order = id_Order;
	}

	public Integer getId_Cust_Order() {
		return id_Cust_Order;
	}

	public void setId_Cust_Order(Integer id_Cust_Order) {
		this.id_Cust_Order = id_Cust_Order;
	}

	public Date getMoment_Order() {
		return moment_Order;
	}

	public void setMoment_Order(Date moment_Order) {
		this.moment_Order = moment_Order;
	}

	public Double getTotal_Order() {
		return total_Order;
	}

	public void setTotal_Order(Double total_Order) {
		this.total_Order = total_Order;
	}

	public Integer getStatus_Order() {
		return status_Order;
	}

	public void setStatus_Order(Integer status_Order) {
		this.status_Order = status_Order;
	}
	
	public Set<Erp_Order_Product> getProducts() {
		return products;
	}
	
	public Double getTotal() {
		double sum = 0.;
		for (Erp_Order_Product x : products) {
			sum += x.getSubTotal();
		}
		return sum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_Cust_Order, id_Order, moment_Order, status_Order, total_Order);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Erp_Order other = (Erp_Order) obj;
		return Objects.equals(id_Cust_Order, other.id_Cust_Order) && Objects.equals(id_Order, other.id_Order)
				&& Objects.equals(moment_Order, other.moment_Order) && Objects.equals(status_Order, other.status_Order)
				&& Objects.equals(total_Order, other.total_Order);
	}

	@Override
	public String toString() {
		return "\nErp_Order [id_Order=" + id_Order +
						  ", id_Cust_Order=" + id_Cust_Order +
						  ", moment_Order=" + moment_Order +
						  ", total_Order=" + total_Order +
						  ", status_Order=" + status_Order + "]";
	}
}