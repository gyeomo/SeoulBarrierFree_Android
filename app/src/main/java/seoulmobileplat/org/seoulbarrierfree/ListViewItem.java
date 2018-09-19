package seoulmobileplat.org.seoulbarrierfree;

public class ListViewItem {
    private String distance ;
    private String titleStr ;
    private String descStr ;

    public void setDistance(String distance) {
        this.distance = distance ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }

    public String getDistance() {
        return this.distance ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
}
