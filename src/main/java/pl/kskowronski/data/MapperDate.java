package pl.kskowronski.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MapperDate {

    public SimpleDateFormat dtYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
    public SimpleDateFormat dtYYYY = new SimpleDateFormat("yyyy");

    public String getCurrentlyYear(){
        Date today = new Date();
        return dtYYYY.format(today);
    }

}
