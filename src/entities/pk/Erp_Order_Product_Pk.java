package entities.pk;

import java.io.Serializable;
import java.util.Objects;

public class Erp_Order_Product_Pk implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id_Order;
	private Integer id_Product;
	
	public Integer getId_Order() {
		return id_Order;
	}
	
	public void setId_Order(Integer id_Order) {
		this.id_Order = id_Order;
	}
	
	public Integer getId_Product() {
		return id_Product;
	}
	
	public void setId_Product(Integer id_Product) {
		this.id_Product = id_Product;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_Order, id_Product);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Erp_Order_Product_Pk other = (Erp_Order_Product_Pk) obj;
		return Objects.equals(id_Order, other.id_Order) && Objects.equals(id_Product, other.id_Product);
	}

	@Override
	public String toString() {
		return "[id_Order=" + id_Order +
				", id_Product=" + id_Product;
	}
}