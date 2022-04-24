
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CPUSchedule {

    static void findWaitingTime(Integer processes[], int n,
                                Integer bt[], int wt[]) {

        wt[0] = 0;

        for (int i = 1; i < n; i++) {
            wt[i] = bt[i - 1] + wt[i - 1];
        }
    }

    static void findTurnAroundTime(Integer processes[], int n,
                                   Integer bt[], int wt[], int tat[]) {

        for (int i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];
        }
    }


    static void findavgTime(Integer processes[], int n, Integer bt[]) {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;

        findWaitingTime(processes, n, bt, wt);

        findTurnAroundTime(processes, n, bt, wt, tat);

        System.out.printf("Processes Burst time Waiting"
                +" time Turn around time\n");

        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.printf(" %d ", (i + 1));
            System.out.printf("     %d ", bt[i]);
            System.out.printf("     %d", wt[i]);
            System.out.printf("     %d\n", tat[i]);
        }
        float s = (float)total_wt /(float) n;
        int t = total_tat / n;
        System.out.printf("Average waiting time = %f", s);
        System.out.printf("\n");
        System.out.printf("Average turn around time = %d ", t);
    }

    public static void main(String[] args) {

        List<Integer> process = new ArrayList<>();
        List<Integer> arrivalTime = new ArrayList<>();
        List<Integer> bTime = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {

            process.add(Integer.parseInt(scanner.next()));
            arrivalTime.add(Integer.parseInt(scanner.next()));
            bTime.add(Integer.parseInt(scanner.next()));
        }
        scanner.close();

        System.out.println(" First Come First Serve :");

        Integer[] processes = new Integer[process.size()];
        processes = process.toArray(processes);

        Integer[] at = new Integer[arrivalTime.size()];
        at = arrivalTime.toArray(at);

        Integer[] burst_time = new Integer[bTime.size()];
        burst_time = bTime.toArray(burst_time);


        int n = processes.length;
        //First Come First Serve ***********
        findavgTime(processes, n, burst_time);
        System.out.println("\n");
        //RoundRobin ***********

        int quantum = 2;
        CPUSchedule.findavgTimeRR(processes, n, burst_time, quantum);

        System.out.println("\n");
        //SJF********
        CPUSchedule.SJF(n,processes,at,burst_time);

    }

    static void findWaitingTimeRR(Integer processes[], int n,
                                Integer bt[], int wt[], int quantum)
    {

        int rem_bt[] = new int[n];
        for (int i = 0 ; i < n ; i++)
            rem_bt[i] =  bt[i];

        int t = 0; // Current time


        while(true)
        {
            boolean done = true;

            // Traverse all processes one by one repeatedly
            for (int i = 0 ; i < n; i++)
            {

                if (rem_bt[i] > 0)
                {
                    done = false;

                    if (rem_bt[i] > quantum)
                    {

                        t += quantum;

                        rem_bt[i] -= quantum;
                    }

                    else
                    {

                        t = t + rem_bt[i];

                        wt[i] = t - bt[i];


                        rem_bt[i] = 0;
                    }
                }
            }

            if (done == true)
                break;
        }
    }

    static void findTurnAroundTimeRR(Integer processes[], int n,
                                   Integer bt[], int wt[], int tat[])
    {

        for (int i = 0; i < n ; i++)
            tat[i] = bt[i] + wt[i];
    }

    // Method to calculate average time
    static void findavgTimeRR(Integer processes[], int n, Integer bt[],
                            int quantum)
    {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;

        System.out.println("Round Robin*****");

        // Function to find waiting time of all processes
        findWaitingTimeRR(processes, n, bt, wt, quantum);

        // Function to find turn around time for all processes
        findTurnAroundTimeRR(processes, n, bt, wt, tat);

        // Display processes along with all details
        System.out.println("Processes " + " Burst time " +
                " Waiting time " + " Turn around time");


        for (int i=0; i<n; i++)
        {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(" " + (i+1) + "\t\t" + bt[i] +"\t " +
                    wt[i] +"\t\t " + tat[i]);
        }

        System.out.println("Average waiting time = " +
                (float)total_wt / (float)n);
        System.out.println("Average turn around time = " +
                (float)total_tat / (float)n);
    }

    private static void SJF(int n, Integer[] processId, Integer[] arrivalArray, Integer[] brustArray) {
        int ct[] = new int[n];
        int ta[] = new int[n];
        int wt[] = new int[n];
        int f[] = new int[n];
        int st=0, tot=0;
        float avgwt=0, avgta=0;

        System.out.println("SJF non-preemptive:");

        boolean a = true;
        while(true)
        {
            int c=n, min=999;
            if (tot == n)
                break;
            for (int i=0; i<n; i++)
            {

                if ((arrivalArray[i] <= st) && (f[i] == 0) && (brustArray[i]<min))
                {
                    min=brustArray[i];
                    c=i;
                }
            }

            if (c==n)
                st++;
            else
            {
                ct[c]=st+brustArray[c];
                st+=brustArray[c];
                ta[c]=ct[c]-arrivalArray[c];
                wt[c]=ta[c]-brustArray[c];
                f[c]=1;
                tot++;
            }
        }
        System.out.println("\npid  arrival brust  complete turn waiting");
        for(int i=0;i<n;i++)
        {
            avgwt+= wt[i];
            avgta+= ta[i];
            System.out.println(processId[i]+"\t  "+arrivalArray[i]+"\t      "+brustArray[i]+"\t     "+ct[i]+"\t     "+ta[i]+"\t     "+wt[i]);
        }
        System.out.println ("\naverage Turnaround time is :"+ (avgta/n));
        System.out.println ("average waiting time is :"+ (avgwt/n));
    }

}
