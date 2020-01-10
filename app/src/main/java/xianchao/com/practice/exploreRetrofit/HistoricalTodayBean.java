package xianchao.com.practice.exploreRetrofit;

import java.util.ArrayList;

public class HistoricalTodayBean {
    public String error_code;
    public String reason;
    public ArrayList<Item> result;

    public static class Item {
        public String _id;
        public String title;
        public String pic;
        public String year;
        public String month;
        public String day;
        public String des;
        public String lunar;
    }
}
