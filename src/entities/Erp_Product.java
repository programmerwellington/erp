package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Erp_Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id_Prod;
	private String name_Prod;
	private String unit_Prod;
	private Double price_Prod;
	
	private Set<Erp_Order_Product> products = new HashSet<>();
	
	public Erp_Product() {
	}
	
	public Erp_Product(Integer id_Prod, String name_Prod,
					   String unit_Prod,Double price_Prod) {
		this.id_Prod = id_Prod;
		this.name_Prod = name_Prod;
		this.unit_Prod = unit_Prod;
		this.price_Prod = price_Prod;
	}

	public Integer getId_Prod() {
		return id_Prod;
	}

	public void setId_Prod(Integer id_Prod) {
		this.id_Prod = id_Prod;
	}

	public String getName_Prod() {
		return name_Prod;
	}

	public void setName_Prod(String name_Prod) {
		this.name_Prod = name_Prod;
	}

	public String getUnit_Prod() {
		return unit_Prod;
	}

	public void setUnit_Prod(String unit_Prod) {
		this.unit_Prod = unit_Prod;
	}

	public Double getPrice_Prod() {
		return price_Prod;
	}

	public void setPrice_Prod(Double price_Prod) {
		this.price_Prod = price_Prod;
	}
	
	public Set<Integer> getOrders() {
		Set<Integer> set_Order = new HashSet<>();
		for (Erp_Order_Product op : products) {
			set_Order.add(op.getId_Order());
		}
		return set_Order;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_Prod, name_Prod, price_Prod, unit_Prod);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Erp_Product other = (Erp_Product) obj;
		return Objects.equals(id_Prod, other.id_Prod) && Objects.equals(name_Prod, other.name_Prod)
				&& Objects.equals(price_Prod, other.price_Prod) && Objects.equals(unit_Prod, other.unit_Prod);
	}

	@Override
	public String toString() {
		return "\nErp_Product [id_Prod=" + id_Prod +
						  ", name_Prod=" + name_Prod +
						  ", unit_Prod=" + unit_Prod +
						  ", price_Prod=" + price_Prod +
						  "]";
	}
}