package ua.ruban.db.entity;

public class Category extends Entity {

    private static final long serialVersionUID = -8224050762192919786L;

    private String category;

    private int categoryId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Category [categoryId=" + categoryId + "category=" + category + "]";
    }
}
