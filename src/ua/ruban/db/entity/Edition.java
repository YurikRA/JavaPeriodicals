package ua.ruban.db.entity;

public class Edition extends Entity {

    private static final long serialVersionUID = -6850658669647495804L;

    private String edName;

    private int priceMonth;

    private int categoryId;

    private int frequency;

    private String category;

    public String getEdName() {
        return edName;
    }

    public void setEdName(String edName) {
        this.edName = edName;
    }

    public int getPriceMonth() {
        return priceMonth;
    }

    public void setPriceMonth(int priceMonth) {
        this.priceMonth = priceMonth;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Edition [edName="+ edName + ", priceMonth="+ priceMonth
                +", categoryId=" + categoryId + ", frequency="+ frequency + "]";
    }
}
