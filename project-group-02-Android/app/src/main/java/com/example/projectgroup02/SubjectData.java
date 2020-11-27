package com.example.projectgroup02;

public class SubjectData {
    String subjectName;
    String image;
    int itemId = 0;
    String artistUsername = "";
    boolean inStock = true;

    public SubjectData(String subjectName, String image) {
        this.subjectName = subjectName;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public String getArtistUsername() {
        return artistUsername;
    }

    public void setArtistUsername(String username) {
        this.artistUsername = username;
    }

    public boolean getInStock() {
        return this.inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock.equalsIgnoreCase("True");
    }
}
