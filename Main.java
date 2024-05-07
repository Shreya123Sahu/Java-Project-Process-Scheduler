import java.util.*;
class Main{
    public static void input(Scanner sc,int n,int ar[],int[] bt,int pid[]){
        for(int i = 0; i < n; i++){
        System.out.println("enter process " + (i+1) + " arrival time: ");
        ar[i] = sc.nextInt();
        System.out.println("enter process " + (i+1) + " brust time: ");
        bt[i] = sc.nextInt();
        pid[i] = i+1;
    }
}
public static void output(int n,int pid[],int ar[],int bt[] ,int ct[],int ta[],int wt[] ,int temp,float avgwt,float avgta){
    System.out.println("\npid  arrival  brust  complete turn waiting");
    for(int  i = 0 ; i< n;  i++)
    {
    System.out.println(pid[i] + "  \t " + ar[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + ta[i] + "\t"  + wt[i] ) ;
    avgwt+=wt[i];
    avgta+=ta[i];
    }
    System.out.println("\naverage waiting time: "+ (avgwt/n));     // printing average waiting time.
    System.out.println("average turnaround time:"+(avgta/n));    // printing average turnaround time.

}
public static void best(int n,int pid[],int ar[],int bt[] ,int ct[],int ta[],int wt[] ,int temp,float avgwt,float avgta){
    float[] avgwait=new float[2];
    Fcfs object1=new Fcfs(); 
    object1.fcfs(n,pid,ar,bt ,ct, ta,wt,temp);
    for(int  i = 0 ; i< n;  i++)
    {
        avgwait[0]+=wt[i];
    }
    avgwait[0]/=n;
    Sjf object2=new Sjf(); 
    object2.sjf(n,pid,ar,bt ,ct, ta,wt,temp);
    for(int  i = 0 ; i< n;  i++)
    {
        avgwait[1]+=wt[i];
    }
    avgwait[1]/=n;
    float minavg=Float.MAX_VALUE;
    int best=0;
    for(int i=0;i<2;i++){
        if(avgwait[i]<minavg){
        minavg=avgwait[i];
        best=i;}
    }
    if(best==0){
        System.out.println("FCFS with avg waiting time : "+minavg);
    }
    if(best==1){
        System.out.println("SJF with avg waiting time : "+minavg);
    }

}
    public static void main(String[] args) {
        System.out.println("Java Process Scheduler");
        Scanner sc = new Scanner(System.in);
        System.out.println("enter no of process: ");
        int n = sc.nextInt();
        int pid[] = new int[n];   // process ids
        int ar[] = new int[n];     // arrival times
        int bt[] = new int[n];     // burst or execution times
        int ct[] = new int[n];     // completion times
        int ta[] = new int[n];     // turn around times
        int wt[] = new int[n];     // waiting times
        int temp=0;
        float avgwt=0,avgta=0;
        input(sc,n,ar,bt,pid);
        System.out.println("1: FCFS\n2: SJF\n3: Best");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
            Fcfs object1=new Fcfs(); 
            object1.fcfs(n,pid,ar,bt ,ct, ta,wt,temp);
            output(n,pid,ar,bt ,ct, ta,wt,temp,avgwt,avgta);
            break;
            case 2:
            Sjf object2=null;
            object2.sjf(n,pid,ar,bt ,ct, ta,wt,temp);
            output(n,pid,ar,bt ,ct, ta,wt,temp,avgwt,avgta);
            break;
            case 3:
            best(n,pid,ar,bt ,ct, ta,wt,temp,avgwt,avgta);
            break;
            case 4:
            Srtf object4=new Srtf(); 
            object4.srtf(n,pid,ar,bt ,ct, ta,wt,temp);
            output(n,pid,ar,bt ,ct, ta,wt,temp,avgwt,avgta);
            break;
            default:
            System.out.println("wrong choice.");
        }
        sc.close();
    }
}