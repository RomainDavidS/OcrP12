package p12.ocr.qualite.technical.date;

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
            return "le " + sdf.format( date );
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
            return "le " + sdfTime.format( date );
        else
            return "";
    }


}
