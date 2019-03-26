package ua.ruban.db.entity;

public class Subscriptions extends Entity {

    private String nameEdition;

    private int priceEd;

    private int startMonth;

    private int endMonth;

    private int sum;

    private int yearEd;

    private int userId;

    private int statusId;

    private String date;

    private int editionId;

    public int getEditionId() {
        return editionId;
    }

    public void setEditionId(int editionId) {
        this.editionId = editionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameEdition() {
        return nameEdition;
    }

    public void setNameEdition(String nameEdition) {
        this.nameEdition = nameEdition;
    }

    public int getPriceEd() {
        return priceEd;
    }

    public void setPriceEd(int priceEd) {
        this.priceEd = priceEd;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getYearEd() {
        return yearEd;
    }

    public void setYearEd(int yearEd) {
        this.yearEd = yearEd;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }


    @Override
    public String toString() {
        return "Subscriptions [nameEdition="+nameEdition+", priceEd="+priceEd+", startMonth="
                +startMonth+", endMonth"+endMonth+", sum="+sum+", yearEd="+yearEd
                +", userId="+userId+", statusId="+statusId+", date="+date+"]";
    }
}
