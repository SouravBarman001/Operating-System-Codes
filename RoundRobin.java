package toc;
import java.util.ArrayList;
import java.util.Scanner;

 class RoundRobinCPU {
    public static void main(String[] args) {
        ArrayList<Integer> Process = new ArrayList<>();// 1 2 3 4 5 6
        ArrayList<Integer> Arrival = new ArrayList<>(); //  4 7 2 6
        ArrayList<Integer> Brust = new ArrayList<>();
        ArrayList<Integer> Priority = new ArrayList<>();


        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {

            Process.add( Integer.parseInt(scanner.next())); // 1
            Arrival.add(Integer.parseInt(scanner.next())); //0
            Brust.add(Integer.parseInt(scanner.next())); // 2
            Priority.add(Integer.parseInt(scanner.next())); // 6
        }
        scanner.close();

        Integer[] processId = new Integer[Process.size()]; //6

        processId = Process.toArray(processId);// 1 2 3 4 5 6


        Integer[] ArrivalArray = new Integer[Arrival.size()]; // 4 7 2 6 8 8
        ArrivalArray = Arrival.toArray(ArrivalArray);



        //***********************
        int n = processId.length; // 6
        int quantum = 2;
        RoundRobinCPU.findavgTime(processId, n, ArrivalArray, quantum);

        nonPriorityALgo(n,arrival,brust);
    }


    static void findWaitingTime(Integer processes[], int n,
                                Integer bt[], int wt[], int quantum)
    {

        int rem_bt[] = new int[n];
        for (int i = 0 ; i < n ; i++)
            rem_bt[i] =  bt[i];

        int t = 0; // Current time

        // Keep traversing processes in round robin manner
        // until all of them are not done.
        while(true)
        {
            boolean done = true;

            // Traverse all processes one by one repeatedly
            for (int i = 0 ; i < n; i++)
            {
                // If burst time of a process is greater than 0
                // then only need to process further
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

    static void findTurnAroundTime(Integer processes[], int n,
                                   Integer bt[], int wt[], int tat[])
    {

        for (int i = 0; i < n ; i++)
            tat[i] = bt[i] + wt[i];
    }


    static void findavgTime(Integer processes[], int n, Integer bt[],
                            int quantum)
    {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;

        findWaitingTime(processes, n, bt, wt, quantum);

        findTurnAroundTime(processes, n, bt, wt, tat);

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
}
