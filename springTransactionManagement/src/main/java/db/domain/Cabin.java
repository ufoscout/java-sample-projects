package db.domain;

public class Cabin {

	private int id;
	private int shipId;
	private int bedCount;
	private String name;
	private int deckLevel;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShipId() {
		return shipId;
	}
	public void setShipId(int shipId) {
		this.shipId = shipId;
	}
	public int getBedCount() {
		return bedCount;
	}
	public void setBedCount(int bedCount) {
		this.bedCount = bedCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDeckLevel() {
		return deckLevel;
	}
	public void setDeckLevel(int deckLevel) {
		this.deckLevel = deckLevel;
	}
}


