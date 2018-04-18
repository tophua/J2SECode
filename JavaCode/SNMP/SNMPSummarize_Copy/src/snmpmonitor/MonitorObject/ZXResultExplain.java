package snmpmonitor.MonitorObject;

public class ZXResultExplain {

	static String ExplainShelfInfo(String type,String value){
		String Result="δ֪";
		switch(type){
		case "psuA":
		case "psuB":
		case "fan":
		   {
			 if(value.equals("1")){
				 Result="On";
			 }else if(value.equals("2")){
				 Result="Off";
			 }else
				 Result="δ֪";
		   }
			break;
		case "volA":
		case "volB":
		{
			 if(value.equals("1")){
				 Result="normal";
			 }else if(value.equals("2")){
				 Result="abnormal";
			 }else
				 Result="δ֪";
		}
			break;
		default:
			break;
		}
	   return Result;	
	}
	
	static String ExplainSlotInfo(String type,String value){
		String Result="δ֪";
		switch(type){
		case "CardType":
		   {
			 if(value.equals("0")){
				 Result="no_card";
			 }else if(value.equals("34")){
				 Result="Local_Card";
			 }else if(value.equals("35")){
				 Result="Remote_Card";
			 }else if(value.equals("100")){
				 Result="Management_Card";
			 }else if(value.equals("102")){
				 Result="��֧��";
			 }else
				 Result="δ֪";
		   }
			break;
		default:
			break;
		}
	   return Result;	
	}
	
	static String ExplainCardInfo(String type,String value){
		String Result="δ֪";
		switch(type){
		case "CardType":
		   {
			 if(value.equals("0")){
				 Result="no_card";
			 }else if(value.equals("34")){
				 Result="Local_Card";
			 }else if(value.equals("35")){
				 Result="Remote_Card";
			 }else if(value.equals("100")){
				 Result="Management Card";
			 }else if(value.equals("102")){
				 Result="��֧��";
			 }else
				 Result="δ֪";
		   }
			break;
		case "Fxlink":
		case "Txlink1":
		case "Txlink2":
		case "Txlink3":
		case "Txlink4":
		{
			if(value.equals("0")){
				 Result="no_card";
			} else if(value.equals("1")){
				 Result="up";
			}else if(value.equals("2")){
				 Result="down";
			}else if(value.equals("3")){
				Result="��֧��";
			}else
				 Result="δ֪";
				 
		}
			break;
		case "LFPState":
		{
			if(value.equals("1")){
				 Result="enable";
			}else if(value.equals("2")){
				 Result="disable";
			}else
				 Result="��֧��";
		}
			break;
		case "CurMode1": //��ǰ˫��
		case "CurMode2":
		case "CurMode3":
		case "CurMode4":
		{
			if(value.equals("0")){
				 Result="no_card";
			}else if(value.equals("2")||value.equals("4")){
				 Result="Full";
			}else if(value.equals("3")||value.equals("5")){
				 Result="Half";
			}else if(value.equals("7")){
				Result="��֧��";
			}else
				 Result="δ֪";			
		}
			break;
		case "Mode1": //����˫��ģʽ
		case "Mode2":
		case "Mode3":
		case "Mode4":
		{
			if(value.equals("0")){
				 Result="no_card";
			}else if(value.equals("1")){
				 Result="Auto";
			}else if(value.equals("2")){
				 Result="100M/Full";
			}else if(value.equals("3")){
				 Result="100M/Half";
			}else if(value.equals("4")){
				Result="10M/Full";
			}else if(value.equals("5")){
				Result="10M/Half";
			}else if(value.equals("7")){
				Result="��֧��";
			}else
				 Result="δ֪";			
		}
			break;
		default:
			break;
		}
	   return Result;	
	}
}
