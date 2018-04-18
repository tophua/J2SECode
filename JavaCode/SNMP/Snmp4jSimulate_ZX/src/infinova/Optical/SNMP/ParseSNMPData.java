package infinova.Optical.SNMP;

import infinova.Otical.ShelfTypeResult;
import infinova.Otical.SlotInfo;

public class ParseSNMPData {
	
    public static String ParseSlotRequest(SlotInfo slotinfo,ShelfTypeResult result){
    	String  strVbs=   result.getVbsValue();
    	return strVbs;
    }
}
