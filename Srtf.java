import java.util.*;

public class Srtf {
    public static void input(Scanner sc, int n, int ar[], int[] bt, int pid[]) {
        for (int i = 0; i < n; i++) {
            System.out.println("enter process " + (i + 1) + " arrival time: ");
            ar[i] = sc.nextInt();
            System.out.println("enter process " + (i + 1) + " burst time: ");
            bt[i] = sc.nextInt();
            pid[i] = i + 1;
        }
    }

    public static void output(int n, int pid[], int ar[], int bt[], int ct[], int ta[], int wt[], int temp,
            float avgwt, float avgta) {
        System.out.println("\npid  arrival  burst  complete turn waiting");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "  \t " + ar[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + ta[i] + "\t" + wt[i]);
            avgwt += wt[i];
            avgta += ta[i];
        }
        System.out.println("\naverage waiting time: " + (avgwt / n)); // printing average waiting time.
        System.out.println("average turnaround time:" + (avgta / n)); // printing average turnaround time.

    }

    public static void srtf(int n, int pid[], int ar[], int bt[], int ct[], int ta[], int wt[], int temp) {
        int remainingTime[] = new int[n];
        for (int i = 0; i < n; i++) {
            remainingTime[i] = bt[i];
        }

        int complete = 0, t = 0, minm = Integer.MAX_VALUE;
        int shortest = 0, finishTime;

        boolean check = false;

        while (complete != n) {
            for (int j = 0; j < n; j++) {
                if (ar[j] <= t && remainingTime[j] < minm && remainingTime[j] > 0) {
                    minm = remainingTime[j];
                    shortest = j;
                    check = true;
                }
            }

            if (!check) {
                t++;
                continue;
            }

            remainingTime[shortest]--;

            minm = remainingTime[shortest];
            if (minm == 0) {
                minm = Integer.MAX_VALUE;
            }

            if (remainingTime[shortest] == 0) {
                complete++;
                check = false;

                finishTime = t + 1 - remainingTime[shortest];

                ta[shortest] = finishTime - ar[shortest];
                wt[shortest] = ta[shortest] - bt[shortest];
                if (wt[shortest] < 0) {
                    wt[shortest] = 0;
                }

                ct[shortest] = finishTime; 
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes: ");
        int n = sc.nextInt();
        int pid[] = new int[n]; // process ids
        int ar[] = new int[n]; // arrival times
        int bt[] = new int[n]; // burst or execution times
        int ct[] = new int[n]; // completion times
        int ta[] = new int[n]; // turnaround times
        int wt[] = new int[n]; // waiting times
        float avgwt = 0, avgta = 0;

        input(sc, n, ar, bt, pid);
        srtf(n, pid, ar, bt, ct, ta, wt, 0);
        output(n, pid, ar, bt, ct, ta, wt, 0, avgwt, avgta);

        sc.close();
    }
}
