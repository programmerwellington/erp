package application;

import java.util.HashSet;
import java.util.Set;

import dao.Dao_Factory;
import dao.Erp_Order_Dao;
import dao.Erp_Order_Product_Dao;
import entities.Erp_Order;
import entities.Erp_Order_Product;

public class Program {
	
	public static void main(String[] args) {
		
		String dashed = "\n================================================================================\n";
		
		// SUBTOTAL AND TOTAL
		
		// SUBTOTAL
		Erp_Order_Product_Dao erp_Order_Product_Dao = Dao_Factory.create_Erp_Order_Product_Dao();
		Set<Erp_Order_Product> products = new HashSet<>();
		products.addAll(erp_Order_Product_Dao.findAll());
		
		System.out.println("id_order || " +
							"id_prod || " +
							"unit_prod || " +
							"quant_prod || " +
							"price_prod || " +
							"getSubTotal() || " +
							"quant * price || "
							);
		for(Erp_Order_Product p : products) {
			System.out.println(p.getId_Order() + "        || " +
								p.getId_Product() + "       || " +
								p.getUnit_Prod() + "        || " +
								p.getQuant_Prod() + "        || " +
								p.getPrice_Prod() + "        || " +
								p.getSubTotal() + "      || " +
								p.getQuant_Prod() * p.getPrice_Prod() + "     || "
								);
		}
		System.out.println(dashed);
		
		// TOTAL
		Erp_Order_Dao erp_Order_Dao	= Dao_Factory.create_Erp_Order_Dao();
		Set<Erp_Order> orders = new HashSet<>();
		orders.addAll(erp_Order_Dao.findAll());
		
		System.out.println("id_order || " +
							"id_cust_order || " +
							"moment_order || " +
							"total_order || " +
							"status_order || " +
							"getTotal() || "
							);
		
		for(Erp_Order y : orders) {
			double sum = 0.;
			for (Erp_Order_Product x : products) {
				if(y.getId_Order() == x.getId_Order()) {
					sum += x.getSubTotal();
				}
			}
			System.out.println(y.getId_Order()			+ "        || " +
								y.getId_Cust_Order()	+ "             || " +
								y.getMoment_Order()		+ "   || " +
								y.getTotal_Order()		+ "      || " +
								y.getStatus_Order()		+ "            || " +
								sum						+ "	       ||"
								);
		}
		
		
		/*
		// ORDER_PRODUCT
		Erp_Order_Product_Dao erp_Order_Product_Dao = Dao_Factory.create_Erp_Order_Product_Dao();
		*/
		
		/*
		//findById()
		int id_Order = 4;
		int id_Prod = 2;
		System.out.println("findById()\n" +
							erp_Order_Product_Dao.findById(id_Order, id_Prod) +
							dashed
							);
		*/
		
		/*
		//findAll()
		System.out.println("findAll()\n" +
							erp_Order_Product_Dao.findAll() +
							dashed
							);
		*/
		
		/*
		//insert()
		int id_Order = 5;
		int id_Prod = 1;
		String unit_Prod = "un";
		Double quant_Prod = 1.;
		Double price_Prod = 2500.;
		
		Erp_Order_Product erp_Order_Product_New = new Erp_Order_Product(id_Order, id_Prod, unit_Prod, quant_Prod, price_Prod);
		erp_Order_Product_Dao.insert(erp_Order_Product_New);
		
		System.out.println("insert()" +
							erp_Order_Product_Dao.findById(erp_Order_Product_New.getId_Order(), erp_Order_Product_New.getId_Product()) +
							dashed +
							erp_Order_Product_Dao.findAll() +
							dashed
							);
		*/
		
		/*
		//update()
		int id_Order = 5;
		int id_Prod = 1;
		Double quant_Prod = 3.;
		
		Erp_Order_Product erp_Order_Product = erp_Order_Product_Dao.findById(id_Order, id_Prod);
		erp_Order_Product.setQuant_Prod(quant_Prod);
		erp_Order_Product_Dao.update(erp_Order_Product);
		System.out.println("update()\n" +
							erp_Order_Product_Dao.findAll() +
							dashed
							);
		*/
		
		/*
		//delete()
		int id_Order = 5;
		int id_Prod = 1;
		erp_Order_Product_Dao.deleteById(id_Order, id_Prod);
		System.out.println("deleteById(" +
							id_Order +
							", " +
							id_Prod +
							")\n" +
							erp_Order_Product_Dao.findAll()
							);
		*/
		
		/*
		// ORDER
		Erp_Order_Dao erp_Order_Dao = Dao_Factory.create_Erp_Order_Dao();
		*/
		
		/*
		// findById()
		int id = 1;
		System.out.println("findById(" +
							id +
							")\n" +
							erp_Order_Dao.findById(id) +
							dashed
							);
		*/
		
		/*
		// findAll()
		System.out.println("findAll()\n" +
							erp_Order_Dao.findAll() +
							dashed
							);
		*/
		
		/*
		// insert()
		int id_Cust_Order = 4;
		Date moment_Order = new Date();
		Double total_Order = 500.;
		int status_Order = 1;
		
		Erp_Order erp_Order_New = new Erp_Order(null, id_Cust_Order, moment_Order, total_Order, status_Order);
		erp_Order_Dao.insert(erp_Order_New);
		System.out.println("insert()" +
							erp_Order_Dao.findById(erp_Order_New.getId_Order()) +
							dashed +
							erp_Order_Dao.findAll() +
							dashed
							);
		*/
		
		/*
		// update()
		int id = 3;
		System.out.println("Record before being updated:\n" +
							erp_Order_Dao.findById(id)
							);
		int status_Order = 2;
		Erp_Order erp_Order = erp_Order_Dao.findById(id);
		erp_Order.setStatus_Order(status_Order);
		erp_Order_Dao.update(erp_Order);
		System.out.println("update()\n" +
							erp_Order_Dao.findById(id) +
							dashed +
							erp_Order_Dao.findAll()
							);
		*/
		
		/*
		// deleteById()
		int id01 = 7;
		System.out.println("Record before being deleted:\n" +
							erp_Order_Dao.findById(id01)
							);
		erp_Order_Dao.deleteById(id01);
		System.out.println("Record after being deleted:\n" +
				erp_Order_Dao.findById(id01) +
				dashed +
				erp_Order_Dao.findAll()
				);
		*/
		
		/*
		// PRODUCT
		Erp_Product_Dao erp_Product_Dao = Dao_Factory.create_Erp_Product_Dao();
		*/
		
		/*
		// findById()
		int id = 1;
		System.out.println("findById(" +
							id +
							")\n" +
							erp_Product_Dao.findById(id) +
							dashed
							);
		*/
		
		/*
		// findAll()
		System.out.println("findAll()\n" +
							erp_Product_Dao.findAll() +
							dashed
							);
		*/
		
		/*
		// insert()
		String name_prod = "Think";
		String unit_prod = "un";
		Double price_prod = 1500.;
		Erp_Product erp_Product_New = new Erp_Product(null, name_prod, unit_prod, price_prod);
		erp_Product_Dao.insert(erp_Product_New);
		System.out.println("insert()" +
							erp_Product_Dao.findById(erp_Product_New.getId_Prod()) +
							dashed +
							erp_Product_Dao.findAll() +
							dashed
							);
		*/
		
		/*
		// update()
		int id = 8;
		System.out.println("Record before being updated:\n" +
							erp_Product_Dao.findById(id)
							);
		String name_prod = "Compaq (discontinued)";
		Erp_Product erp_Product = erp_Product_Dao.findById(id);
		erp_Product.setName_Prod(name_prod);
		erp_Product_Dao.update(erp_Product);
		System.out.println("update()\n" +
							erp_Product_Dao.findById(id) +
							dashed +
							erp_Product_Dao.findAll()
							);
		*/
		
		/*
		// deleteById()
		int id = 10;
		System.out.println("Record before being deleted:\n" +
							erp_Product_Dao.findById(id)
							);
		erp_Product_Dao.deleteById(id);
		System.out.println("Record after being deleted:\n" +
				erp_Product_Dao.findById(id) +
				dashed +
				erp_Product_Dao.findAll()
				);
		*/
		
		/*
		// UNIT_PRODUCT
		Erp_Unit_Product_Dao erp_Unit_Product_Dao = Dao_Factory.create_Erp_Unit_Product_Dao();
		*/
		
		/*
		// findById()
		int id = 1;
		Erp_Unit_Product erp_Unit_Product = erp_Unit_Product_Dao.findById(id);
		System.out.println("findById(" +
							id +
							")\n" +
							erp_Unit_Product +
							dashed
							);
		*/
		
		/*
		// findAll()
		List<Erp_Unit_Product> list_Erp_Unit_Product = erp_Unit_Product_Dao.findAll();
		System.out.println("findAll()\n" +
							list_Erp_Unit_Product +
							dashed
							);
		*/
		
		/*
		// insert()
		String desc = "minute";
		String abbr = "min";
		Erp_Unit_Product erp_Unit_Product_New = new Erp_Unit_Product(null, desc, abbr);
		erp_Unit_Product_Dao.insert(erp_Unit_Product_New);
		System.out.println("insert(" +
							desc +
							", " +
							abbr +
							")\n" +
							erp_Unit_Product_Dao.findById(erp_Unit_Product_New.getId_Unit_Prod()) +
							dashed
							);
		*/
		
		/*
		// update()
		int id = 13;
		Erp_Unit_Product erp_Unit_Product = erp_Unit_Product_Dao.findById(id);
		erp_Unit_Product.setDesc_Unit_Prod("mile");
		erp_Unit_Product_Dao.update(erp_Unit_Product);
		System.out.println(erp_Unit_Product + dashed);
		*/
		
		/*
		// deleteById()
		int id = 20;
		erp_Unit_Product_Dao.deleteById(id);
		List<Erp_Unit_Product>list_Erp_Unit_Product = erp_Unit_Product_Dao.findAll();
		System.out.println("deleteById()\n" +
							list_Erp_Unit_Product +
							dashed
							);
		*/
		
		/*
		// CUSTOMER
		Erp_Customer_Dao customer_Dao = Dao_Factory.create_Erp_Customer_Dao();
		
		
		// findById()
		int id = 4;
		Erp_Customer customer = customer_Dao.findById(id);
		System.out.println(customer +
						   dashed
						   );
		
		
		// findAll()
		List<Erp_Customer> list = customer_Dao.findAll();
		System.out.println(list +
						   dashed
						   );
		
		
		// insert()
		Erp_Customer customer_New = new Erp_Customer(null, "Tamires P", "tami@gmail.com");
		customer_Dao.insert(customer_New);
		System.out.println("Inserted record: " +
						    customer_Dao.findById(customer_New.getId_Cust()) +
						    dashed
						    );
		
		
		// update()
		int id = 21;
		Erp_Customer customer = customer_Dao.findById(id);
		System.out.println("Record before update:\n" +
							customer
							);
		customer.setName_Cust("Iara");
		customer_Dao.update(customer);
		System.out.println("Record after update: \n" +
						    customer_Dao.findById(customer.getId_Cust()) +
						    dashed
							);
		
		
		// deleteById()
		int id = 22;
		Erp_Customer customer = customer_Dao.findById(id);
		System.out.println("Record before being deleted:\n" +
						    customer +
						    "\n"
						    );
		customer_Dao.deleteById(customer.getId_Cust());
		System.out.println("Record after being deleted:\n" +
							customer_Dao.findById(customer.getId_Cust()) +
							dashed +
							"\n" +
							customer_Dao.findAll()
							);
		*/
	}
}