package s3t3e1.GardenShop.infrastructure.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import s3t3e1.GardenShop.application.port.in.AddProduct;
import s3t3e1.GardenShop.application.port.out.GardenShopRepository;
import s3t3e1.GardenShop.application.port.out.ProductRepository;
import s3t3e1.GardenShop.domain.GardenShop;
import s3t3e1.GardenShop.domain.Product;
import s3t3e1.GardenShop.domain.enums.ProductType;

public class TxtGardenShopRepository implements ProductRepository, GardenShopRepository {

	private String filePath = "GardenShop.txt";
	
	public TxtGardenShopRepository(String filePath) {
		this.filePath = filePath;
	}

	/* PRODUCT REPOSITORY (shop stock) */
	@Override
	public Product save(Product product) {
		try (FileWriter writer = new FileWriter(filePath, true)) {
			writer.write(product.toString() + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public Product findProdById(GardenShop gardenShop, int prodId) {
		Product product = gardenShop.findKeys().stream()
				.filter(p -> p.getId() == prodId)
				.findFirst()
				.orElse(null);

		return product;
	}

	@Override
	public void delete(GardenShop gardenShop, int prodId, int quantity) {
		Product prodFound = findProdById(gardenShop, prodId);
		if (prodFound != null) {
			gardenShop.getGardenProducts().remove(prodFound, quantity);
		} else {
			System.out.println("Sorry, we can't delete this product. It doesn't exist in our shop's stock");
		}
	}

	/* GARDEN SHOP REPOSITORY */
	@Override
	public GardenShop save(GardenShop gardenShop) {
		try (FileWriter writer = new FileWriter(filePath, true)) {
			writer.write(gardenShop.toString() + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return gardenShop;
	}

	@Override
	public GardenShop findByName(List<GardenShop> shops, String name) {
		GardenShop shop = shops.stream()
				.filter(s -> s.getName().equals(name))
				.findFirst()
				.orElse(null);
		return shop.get();
	}

	@Override
	public void delete(GardenShop gardenShop) {
		List<GardenShop> inFileShops = new ArrayList<GardenShop>();
		File file = new File(filePath);
		
		// Read all shops into a list
		try(Scanner scanner = new Scanner(file)) {
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				GardenShop gs = convertLineToGardenShop(line);
				inFileShops.add(gs);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Remove the shop from the list
		inFileShops.removeIf(gs -> gs.getName().equalsIgnoreCase(gardenShop.getName()));
		
		// Clear the file
		try (PrintWriter writer = new PrintWriter(file)) {
			writer.print("");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Write the remaining tickets back into the file
		try(FileWriter writer = new FileWriter(filePath, true)) {
			for(GardenShop gs : inFileShops) {
				writer.write(gs.toString() + "\n");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private GardenShop convertLineToGardenShop(String line) {
		return null;
	}
	
	/*
	public void delete(List<GardenShop> shops, GardenShop gardenShop) {
		GardenShop shop = findByName(shops, gardenShop.getName());
		if (shop != null) {
			shops.remove(shop);
			System.out.println("The shop " + shop.getName() + " has been successfully removed from the database.");
		} else {
			System.out.println("This shop doesn't exist in our shops' database");
		}
	}
	*/
}
