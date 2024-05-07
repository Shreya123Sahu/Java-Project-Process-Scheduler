public class Best {
    public static void best(){
        int n=0;
        int pid[] = new int[n];   // process ids
        int ar[] = new int[n];     // arrival times
        int bt[] = new int[n];     // burst or execution times
        int ct[] = new int[n];     // completion times
        int ta[] = new int[n];     // turn around times
        int wt[] = new int[n];     // waiting times
        int temp=0;
        float avgwt=0,avgta=0;
        int avgwaittime[]=new int[2];
        Fcfs object1=new Fcfs(); 
        object1.fcfs(n,pid,ar,bt ,ct, ta,wt,temp);
        Sjf object2=null;
        object2.sjf(n,pid,ar,bt ,ct, ta,wt,temp);
    }
}
