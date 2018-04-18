package TestPackage;

public class MainServerMonitor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  try {
	        	SnmpServerMonitor.setIpStr("10.82.17.15");  
	        //	SnmpServerMonitor.getMemoryUse();
				System.out.println(SnmpServerMonitor.getCpuUse().get("cpuLoad").toString().equals("-1"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	}

}
