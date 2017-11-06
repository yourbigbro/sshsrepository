package service;

import java.sql.SQLException;
import java.util.List;

import dao.ProductDao;
import domain.Product;

public class ProductService {

	public List<Product> showAllProduct() throws SQLException {
		ProductDao productDao=new ProductDao();
		List<Product> pd=productDao.showAllProduct();
		return pd;
	}

}
