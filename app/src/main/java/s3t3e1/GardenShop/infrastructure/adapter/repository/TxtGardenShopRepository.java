package s3t3e1.GardenShop.infrastructure.adapter.repository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import s3t3e1.GardenShop.application.port.in.AddProduct;
import s3t3e1.GardenShop.application.port.in.RemoveProduct;
import s3t3e1.GardenShop.application.port.out.GardenShopRepository;
import s3t3e1.GardenShop.domain.GardenShop;
import s3t3e1.GardenShop.domain.Product;
import s3t3e1.GardenShop.domain.enums.ProductType;

public class TxtGardenShopRepository implements AddProduct, RemoveProduct, GardenShopRepository {

	private String filePath = "GardenShop.txt";
	
	/* ADD PRODUCT */
	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		
	}
	@Override
//	public void addProduct(ProductType prodType) {
//		String prod = prodType.toString();
//		
//		try (FileWriter writer = new FileWriter(filePath, true)){
//			writer.write(prod.toString() + "\n");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		/*
		String variable;
		switch(prodType) {
		case T:
			variable = "Tree";
			break;
		case F:
			break;
		case D:
			break;
		}
		try(FileWriter writer = new FileWriter(filePath, true)) {
			writer.write(variable.toString() + "\n");
		}
		*/
//	}
	
	public void save(Product product) {
		
	}
	
	/* REMOVE PRODUCT */
	@Override
	public void removeProduct(Product product) {
		// TODO Auto-generated method stub
		
	}
	
	/* GARDEN SHOP REPOSITORY */
	@Override
	public GardenShop save(GardenShop gardenShop) {
		return null; // guardar botiga
	}

	@Override
	public GardenShop findByName(List<GardenShop> shops, String name) {
		Optional<GardenShop> shop = shops.stream()
				.filter(s -> s.getName().equals(name))
				.findFirst()
				.orElse(null);
		return shop.get();
	}

	@Override
	public void delete(List<GardenShop> shops, GardenShop gardenShop) {
		GardenShop shop = findByName(shops, gardenShop.getName());
		if(shop != null) {
			shops.remove(shop);
		} else {
			System.out.println("This shop doesn't exist in our shops' database");
		}
	}

	

	
}
