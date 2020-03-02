package p12.ocr.web.technical.function;

import p12.ocr.web.technical.date.SimpleDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserEntrepriseFunction {

    @Autowired
    private SimpleDate simpleDate;

    public String getDate(Date date){return simpleDate.getDate( date ); }

    public String getDateTime(Date date){return simpleDate.getDateTime( date ); }

}
