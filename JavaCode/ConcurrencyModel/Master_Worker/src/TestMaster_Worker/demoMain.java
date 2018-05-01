package TestMaster_Worker;

import java.util.Random;

public class demoMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Master master = new Master(new Worker(), 20);
        Random r = new Random();
        // 100 ������
        for(int i = 1; i <= 100; i++){
            Task t = new Task();
            t.setId(i);
            t.setPrice(r.nextInt(1000));
            master.submit(t);
        }

        master.execute();
        long start = System.currentTimeMillis();

        while(true){
            if(master.isComplete()){
                long end = System.currentTimeMillis() - start;
                int priceResult = master.getResult();
                System.out.println("���ս����" + priceResult + ", ִ��ʱ�䣺" + end);
                break;
            }
        }
	}
}
