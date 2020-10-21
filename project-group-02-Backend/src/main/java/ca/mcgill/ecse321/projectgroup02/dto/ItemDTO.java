package ca.mcgill.ecse321.projectgroup02.dto;

public class ItemDTO{
	private int itemId;
	private String name;
	private double height;
	private double width;
	private double breadth;
	private String creationDate;
	private String description;
	private double price;
	private boolean inStock;
	private ArtistDTO artist;
	private CollectionDTO collection;
	private ArtGallerySystemDTO artGallerySystem;


	public ItemDTO() {}

	public ItemDTO(int itemId,
			String name,
			double height,
			double width,
			double breadth,
			String creationDate,
			String description,
			double price,
			boolean inStock,
			ArtistDTO artist,
			CollectionDTO collection,
			ArtGallerySystemDTO artGallerySystem) {
		
		this.itemId = itemId;
		this.name = name;
		this.height = height;
		this.width = width;
		this.breadth = breadth;
		this.creationDate = creationDate;
		this.description = description;
		this.price = price;
		this.inStock = inStock;
		this.artist = artist;
		this.collection = collection;
		this.artGallerySystem = artGallerySystem;
	}
	
	public ItemDTO(int itemId,
			String name,
			double height,
			double width,
			double breadth,
			String creationDate,
			String description,
			double price,
			boolean inStock,
			ArtistDTO artist,
			CollectionDTO collection) {
		
		this.itemId = itemId;
		this.name = name;
		this.height = height;
		this.width = width;
		this.breadth = breadth;
		this.creationDate = creationDate;
		this.description = description;
		this.price = price;
		this.inStock = inStock;
		this.artist = artist;
		this.collection = collection;
		this.artGallerySystem = null;
	}
	
	public ItemDTO(int itemId,
			String name,
			double height,
			double width,
			double breadth,
			String creationDate,
			String description,
			double price,
			boolean inStock,
			ArtistDTO artist,
			ArtGallerySystemDTO artGallerySystem) {
		
		this.itemId = itemId;
		this.name = name;
		this.height = height;
		this.width = width;
		this.breadth = breadth;
		this.creationDate = creationDate;
		this.description = description;
		this.price = price;
		this.inStock = inStock;
		this.artist = artist;
		this.collection = null;
		this.artGallerySystem = artGallerySystem;
	}
	
	public ItemDTO(int itemId,
			String name,
			double height,
			double width,
			double breadth,
			String creationDate,
			String description,
			double price,
			boolean inStock,
			CollectionDTO collection,
			ArtGallerySystemDTO artGallerySystem) {
		
		this.itemId = itemId;
		this.name = name;
		this.height = height;
		this.width = width;
		this.breadth = breadth;
		this.creationDate = creationDate;
		this.description = description;
		this.price = price;
		this.inStock = inStock;
		this.artist = null;
		this.collection = collection;
		this.artGallerySystem = artGallerySystem;
	}

	public ItemDTO(int itemId,
			String name,
			double height,
			double width,
			double breadth,
			String creationDate,
			String description,
			double price,
			boolean inStock) {
		
		this.itemId = itemId;
		this.name = name;
		this.height = height;
		this.width = width;
		this.breadth = breadth;
		this.creationDate = creationDate;
		this.description = description;
		this.price = price;
		this.inStock = inStock;
		this.artist = null;
		this.collection = null;
		this.artGallerySystem = null;
	}


	public String getName() {
		return this.name;
	}

	public int getItemId() {
		return this.itemId;
	}

	public double getHeight() {
		return this.height;
	}

	public double getWidth() {
		return this.width;
	}

	public double getBreadth() {
		return this.breadth;
	}

	public String getCreationDate() {
		return this.creationDate;
	}

	public String getDescription() {
		return this.description;
	}

	public double getPrice() {
		return this.price;
	}
	private String pathToImage;

	public String getPathToImage() {
		return this.pathToImage;
	}

	public boolean isInStock() {
		return this.inStock;
	}

	public ArtistDTO getArtist() {
		return this.artist;
	}

	public void setArtist(ArtistDTO artist) {
		this.artist = artist;
	}

	public CollectionDTO getCollection() {
		return this.collection;
	}

	public void setCollection(CollectionDTO collection) {
		this.collection = collection;
	}

	public ArtGallerySystemDTO getArtGallerySystem() {
		return this.artGallerySystem;
	}

	public void setArtGallerySystem(ArtGallerySystemDTO artGallerySystem) {
		this.artGallerySystem = artGallerySystem;
	}

}
