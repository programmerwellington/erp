package entities;

import java.io.Serializable;
import java.util.Objects;

public class Erp_Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id_Cust;
	private String name_Cust;
	private String email_Cust;
	
	public Erp_Customer() {
	}
	
	public Erp_Customer(Integer id_Cust, String name_Cust, String email_Cust) {
		this.id_Cust = id_Cust;
		this.name_Cust = name_Cust;
		this.email_Cust = email_Cust;
	}

	public Integer getId_Cust() {
		return id_Cust;
	}

	public void setId_Cust(Integer id_Cust) {
		this.id_Cust = id_Cust;
	}

	public String getName_Cust() {
		return name_Cust;
	}

	public void setName_Cust(String name_Cust) {
		this.name_Cust = name_Cust;
	}

	public String getEmail_Cust() {
		return email_Cust;
	}

	public void setEmail_Cust(String email_Cust) {
		this.email_Cust = email_Cust;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email_Cust, id_Cust, name_Cust);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Erp_Customer other = (Erp_Customer) obj;
		return Objects.equals(email_Cust, other.email_Cust) && Objects.equals(id_Cust, other.id_Cust)
				&& Objects.equals(name_Cust, other.name_Cust);
	}

	@Override
	public String toString() {
		return "Erp_Customer [id_Cust = " + id_Cust +
						   ", name_Cust = " + name_Cust +
						   ", email_Cust = " + email_Cust +
						   "] \n";
	}
}