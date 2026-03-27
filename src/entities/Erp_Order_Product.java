package entities;

import java.io.Serializable;
import java.util.Objects;

import entities.pk.Erp_Order_Product_Pk;

public class Erp_Order_Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Erp_Order_Product_Pk id_Order_Product_Pk = new Erp_Order_Product_Pk();
	
	private String unit_Prod;
	private Double quant_Prod;
	private Double price_Prod;
	
	public Erp_Order_Product() {
	}

	public Erp_Order_Product(Integer id_Order, Integer id_Product, String unit_Prod, Double quant_Prod, Double price_Prod) {
		super();
		id_Order_Product_Pk.setId_Order(id_Order);
		id_Order_Product_Pk.setId_Product(id_Product);
		this.unit_Prod = unit_Prod;
		this.quant_Prod = quant_Prod;
		this.price_Prod = price_Prod;
	}
	
	public Integer getId_Order() {
		return id_Order_Product_Pk.getId_Order();
	}
	
	public void setId_Order(Integer id_Order) {
		id_Order_Product_Pk.setId_Order(id_Order);
	}
	
	public Integer getId_Product() {
		return id_Order_Product_Pk.getId_Product();
	}
	
	public void setId_Product(Integer id_Product) {
		id_Order_Product_Pk.setId_Product(id_Product);
	}
	

	public String getUnit_Prod() {
		return unit_Prod;
	}

	public void setUnit_Prod(String unit_Prod) {
		this.unit_Prod = unit_Prod;
	}

	public Double getQuant_Prod() {
		return quant_Prod;
	}

	public void setQuant_Prod(Double quant_Prod) {
		this.quant_Prod = quant_Prod;
	}

	public Double getPrice_Prod() {
		return price_Prod;
	}

	public void setPrice_Prod(Double price_Prod) {
		this.price_Prod = price_Prod;
	}
	
	public Double getSubTotal() {
		return quant_Prod * price_Prod; 
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_Order_Product_Pk);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Erp_Order_Product other = (Erp_Order_Product) obj;
		return Objects.equals(id_Order_Product_Pk, other.id_Order_Product_Pk);
	}

	@Override
	public String toString() {
		return "Erp_Order_Product\n" +
				id_Order_Product_Pk +
				", unit_Prod=" + unit_Prod +
				", quant_Prod=" + quant_Prod +
				", price_Prod=" + price_Prod +
				"]";
	}
}