package p12.ocr.web.technical.date;

import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Permet mettre en forme les dates
 */
@NoArgsConstructor
public class SimpleDate {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

    private static final SimpleDateFormat sdfTime = new SimpleDateFormat("dd MMM yyyy à HH:mm");

    /**
     * Permet de mettre en forme une date
     * @param date La date à mettre en forme
     * @return La date mise en forme "dd MMM yyyy"
     */
    public String getDate(Date date){
        if ( date != null)
            return  sdf.format( date );
        else
            return "";
    }
    /**
     * Permet de mettre en forme une date
     * @param date La date à mettre en forme
     * @return La date mise en forme "dd MMM yyyy à HH:mm"
     */
    public String getDateTime(Date date){
        if ( date != null)
            return  sdfTime.format( date );
        else
            return "";
    }

    public boolean isDate1BeforeDate2(Date date1, Date date2) {

        if(date1 == null )
            return true;

        return date2 != null ? date1.compareTo(date2) <= 0 : true;
    }


}
