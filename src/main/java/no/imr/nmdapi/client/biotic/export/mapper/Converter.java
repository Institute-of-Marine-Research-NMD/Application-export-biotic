package no.imr.nmdapi.client.biotic.export.mapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.imr.nmdapi.generic.nmdbiotic.domain.v1_4.StringDescriptionType;

public class Converter {
    
    public static final Logger logger = LoggerFactory.getLogger(Converter.class);    
    
    
    public static StringDescriptionType toStringDescriptionType(String val) {
        StringDescriptionType strDesc = null;
        if (val != null) { 
            strDesc = new StringDescriptionType();
            strDesc.setValue(""+val);
        }
        return strDesc; 
    }
    
    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) 
                return false;
        }
        return true;
    }
    
    
    public static Integer stringToInt(String str) {
        Integer replyInt = null;
        if ((str != null) && (str.matches("\\b\\-?\\d+\\b"))) {
            try {
                replyInt = Integer.parseInt(str);
            }
            catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return replyInt;
    } 
    
    public static Double stringToDouble(String str) {
        Double replyInt = null;
        if (str != null) {
            try {
                replyInt = Double.parseDouble(str);
            }
            catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return replyInt;
    }  
    
    
    public static Double bigDecimalToDouble(BigDecimal val) {
        Double result = null;
        if (val != null) {
            result = val.doubleValue();
        }
        return result;
    }      
    
    public static Integer bigDecimalToInt(BigDecimal val) {
        Integer result = null;
        if (val != null) {
            result = val.intValue();
        }
        return result;
    }  
    
    public static Integer bigIntegerToInt(BigInteger val) {
        Integer result = null;
        if (val != null) {
            result = val.intValue();
        }
        return result;
    }    
    
    public static String bigIntegerToString(BigInteger val) {
        String result = null;
        if (val != null) {
            result = val.toString();
        }
        return result;
    }
    
    public static String integerToString(Integer val) {
        String result = "-1";
        String.valueOf(val);
        if (val != null) {
            try {
                result = String.valueOf(val);
            }
            catch (Exception exp) {
                logger.info("bigIntegerToInt converter error");
            }
        }
        return result;
    }    
    
    
    public static BigDecimal toDouble(BigDecimal val) {
        BigDecimal result = null;
        try {
            if (val != null) {
                result = BigDecimal.valueOf(val.doubleValue());
            }
        }
        catch (Exception exp) {
            // TODO: handle exception
//            logger.debug(val.toPlainString());
//            result = BigDecimal.valueOf(val.doubleValue());
            logger.info("error : not a legal BigDecimal");
        }

        return result;
        //return val;
    }
    
    public static BigInteger toInteger(BigDecimal val) {
        BigInteger result = null;
        if (val != null) {
            result = BigInteger.valueOf(val.longValue());
        }
        return result;
    }    
    
 

}
