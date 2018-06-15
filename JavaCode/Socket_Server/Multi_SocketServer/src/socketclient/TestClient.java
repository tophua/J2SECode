package socketclient;

public class TestClient {

	public static byte generateByte(String s) {

		byte tmp = 0;
		char[] arr = s.toCharArray();
		if (arr.length > 2) {
			return tmp;
		}
		int t0 = Integer.parseInt(Character.toString(arr[0]), 16);
		int t1 = Integer.parseInt(Character.toString(arr[1]), 16);
		byte tmp0 = (byte) t0;
		byte tmp1 = (byte) t1;
		tmp = (byte) (tmp0 << 4);
		tmp = (byte) (tmp | tmp1);
		return tmp;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		CloudHead Cloud=new CloudHead();
//		byte[] byVa=new byte[4];
//		byVa="INF".getBytes();
//		Cloud.setcStartCode(byVa);		
//		Cloud.setDwCmdType((byte)163);
//		short sh=1;
//		Cloud.setsPacketSN(sh);
//		Cloud.setsPacketNum(sh);
//		long lo=0;
//		Cloud.setnDataSize(lo);
//		byte[] byVa1=new byte[4];
//		Cloud.setcReserved(byVa1);
        
		byte[] byAll=new byte[16];
		
		byte[] byCode=new byte[3];
		byCode="INF".getBytes();
		byAll[0]=byCode[0];
		byAll[1]=byCode[1];
		byAll[2]=byCode[2];
		byAll[3]=(byte)161;
		byAll[4]=(byte)1;
		byAll[5]=(byte)0;
		byAll[6]=(byte)1;
		byAll[8]=(byte)0;
		
		
		SimpleClient si=new SimpleClient("10.82.18.113",9537);
		si.sendRequest(byAll);
		
//		CloudHead Cloud=new CloudHead();
//		byte[] byVa=new byte[4];
//		byVa="INF".getBytes();
//		Cloud.setcStartCode(byVa);		
//        byte byType=(byte)163;
//        Cloud.setDwCmdType(byType);
//        Cloud.setsPacketSN((short)1);
//        Cloud.setsPacketNum((short)1);
//        Cloud.setnDataSize((long)0);
//        si.sendRequest1(Cloud);
        
		si.getReponse();
	}

}
