package entities;

import java.io.Serializable;
import java.util.Objects;

public class Erp_Unit_Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id_Unit_Prod;
	private String desc_Unit_Prod;
	private String abbrev_Unit_Prod;
	
	public Erp_Unit_Product() {
	}

	public Erp_Unit_Product(Integer id_Unit_Prod, String desc_Unit_Prod,
							String abbrev_Unit_Prod) {
		this.id_Unit_Prod = id_Unit_Prod;
		this.desc_Unit_Prod = desc_Unit_Prod;
		this.abbrev_Unit_Prod = abbrev_Unit_Prod;
	}

	public Integer getId_Unit_Prod() {
		return id_Unit_Prod;
	}

	public void setId_Unit_Prod(Integer id_Unit_Prod) {
		this.id_Unit_Prod = id_Unit_Prod;
	}

	public String getDesc_Unit_Prod() {
		return desc_Unit_Prod;
	}

	public void setDesc_Unit_Prod(String desc_Unit_Prod) {
		this.desc_Unit_Prod = desc_Unit_Prod;
	}

	public String getAbbrev_Unit_Prod() {
		return abbrev_Unit_Prod;
	}

	public void setAbbrev_Unit_Prod(String abbrev_Unit_Prod) {
		this.abbrev_Unit_Prod = abbrev_Unit_Prod;
	}

	@Override
	public int hashCode() {
		return Objects.hash(abbrev_Unit_Prod, desc_Unit_Prod, id_Unit_Prod);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Erp_Unit_Product other = (Erp_Unit_Product) obj;
		return Objects.equals(abbrev_Unit_Prod, other.abbrev_Unit_Prod)
				&& Objects.equals(desc_Unit_Prod, other.desc_Unit_Prod)
				&& Objects.equals(id_Unit_Prod, other.id_Unit_Prod);
	}

	@Override
	public String toString() {
		return "\nErp_Unit_Product [id_Unit_Prod=" + id_Unit_Prod +
							   ", desc_Unit_Prod=" + desc_Unit_Prod	+
							   ", abbrev_Unit_Prod=" + abbrev_Unit_Prod +
							   "]";
	}
}