
import java.util.ArrayList;
import java.util.Scanner;

public class practiceOne {
    public static void main(String[] args) {

        ArrayList<Integer> process = new ArrayList<>();
        ArrayList<Integer> arrivalTime = new ArrayList<>();
        ArrayList<Integer> bTime = new ArrayList<>();
        ArrayList<Integer> Priority = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            //  System.out.println(scanner.nextLine());

            process.add(Integer.parseInt(scanner.next()));
            arrivalTime.add(Integer.parseInt(scanner.next()));
            bTime.add(Integer.parseInt(scanner.next()));
            Priority.add(Integer.parseInt(scanner.next()));
        }
        scanner.close();


        Integer[] processId = new Integer[process.size()];
        processId = process.toArray(processId);

       // for (int p : processId)
         //   System.out.println(" Process:"+p);

        // arrivalTime to array arrivalTime
        Integer[] arrivalArray = new Integer[arrivalTime.size()];
        arrivalArray = arrivalTime.toArray(arrivalArray);

       // for (Integer a : arrivalArray)
      //      System.out.println(" arrivalTime:"+a);



        // brustTime to array burstTime
        Integer[] bArray = new Integer[bTime.size()];
        bArray = bTime.toArray(bArray);

        //  for (Integer b : bArray)
       //     System.out.println(" brushTime:"+b);
        Integer[] PriorityArray = new Integer[Priority.size()];
        PriorityArray = Priority.toArray(PriorityArray);

      //  for (int p : PriorityArray)
        //    System.out.println(" Priority:"+p);

//**********************************************************************
        int numberOfProcess = processId.length;

         practiceOne.FirstComeFirstServe(numberOfProcess,processId,arrivalArray,bArray);

        System.out.println("--------------------------------");

        int n = processId.length;

        practiceOne.ShortestJobFast(n,processId,arrivalArray,bArray);

        System.out.println("--------------------------------");

        practiceOne.roundRobin(n,bArray);
        //System.out.println("--------------------------------");

       // practiceOne.PrioritySchedule(n,processId,PriorityArray,bArray);

    }

    public static void roundRobin(int n,Integer[] bt) {


        int i, j, k, q = 2, sum = 0;
        //  System.out.println("Enter number of process:");
        // int n=Integer.parseInt(in.readLine());
        // int bt[]=new int[n];
        int wt[] = new int[n];
        int tat[] = new int[n];
        int a[] = new int[n];

        System.out.println("Round Robinson CPU Scheduling:");

        for (i = 0; i < n; i++)
            a[i] = bt[i];
        for (i = 0; i < n; i++)
            wt[i] = 0;
        do {
            for (i = 0; i < n; i++) {
                if (bt[i] > q) {
                    bt[i] -= q;
                    for (j = 0; j < n; j++) {
                        if ((j != i) && (bt[j] != 0))
                            wt[j] += q;
                    }
                } else {
                    for (j = 0; j < n; j++) {
                        if ((j != i) && (bt[j] != 0))
                            wt[j] += bt[i];
                    }
                    bt[i] = 0;
                }
            }
            sum = 0;
            for (k = 0; k < n; k++)
                sum = sum + bt[k];
        }
        while (sum != 0);
        for (i = 0; i < n; i++)
            tat[i] = wt[i] + a[i];
        System.out.println("process\t\tBT\tWT\tTAT");
        for (i = 0; i < n; i++) {
            System.out.println("process" + (i + 1) + "\t" + a[i] + "\t" + wt[i] + "\t" + tat[i]);
        }
        float avg_wt = 0;
        float avg_tat = 0;
        for (j = 0; j < n; j++) {
            avg_wt += wt[j];
        }
        for (j = 0; j < n; j++) {
            avg_tat += tat[j];
        }
        System.out.println("Average waiting time :" + (avg_wt / n) + "\n Average turn around time :" + (avg_tat / n));

    }
        public static void FirstComeFirstServe ( int numberOfProcess, Integer[] processId, Integer[] arrivalArray, Integer[] bArray){

            int ct[] = new int[numberOfProcess];     // completion times
            int ta[] = new int[numberOfProcess];     // turn around times
            int wt[] = new int[numberOfProcess];     // waiting times
            int temp;
            float avgwt = 0, avgta = 0;

            System.out.println("FCFS CPU Scheduling:");
            System.out.println("______________________");
            //sorting according to arrival times
            for (int i = 0; i < numberOfProcess; i++) {
                for (int j = 0; j < numberOfProcess - (i + 1); j++) {
                    if (arrivalArray[j] > arrivalArray[j + 1]) {
                        temp = arrivalArray[j];
                        arrivalArray[j] = arrivalArray[j + 1];
                        arrivalArray[j + 1] = temp;
                        temp = bArray[j];
                        bArray[j] = bArray[j + 1];
                        bArray[j + 1] = temp;
                        temp = processId[j];
                        processId[j] = processId[j + 1];
                        processId[j + 1] = temp;
                    }
                }
            }

            for (int i = 0; i < numberOfProcess; i++) {
                if (i == 0) {
                    ct[i] = arrivalArray[i] + bArray[i];
                } else {
                    if (arrivalArray[i] > ct[i - 1]) {
                        ct[i] = arrivalArray[i] + bArray[i];
                    } else
                        ct[i] = ct[i - 1] + bArray[i];
                }
                ta[i] = ct[i] - arrivalArray[i];          // turnaround time= completion time- arrival time
                wt[i] = ta[i] - bArray[i];          // waiting time= turnaround time- burst time
                avgwt += wt[i];               // total waiting time
                avgta += ta[i];               // total turnaround time
            }
            System.out.println("\npid  arrival  brust  complete turn waiting");
            for (int i = 0; i < numberOfProcess; i++) {
                System.out.println(processId[i] + "  \t   " + arrivalArray[i] + " \t   " + bArray[i] + " \t   " + ct[i] + " \t   " + ta[i] + " \t   " + wt[i]);
            }

            System.out.println("\naverage waiting time: " + (avgwt / numberOfProcess));     // printing average waiting time.
            System.out.println("average turnaround time:" + (avgta / numberOfProcess));    // printing average turnaround time.


        }

        private static void ShortestJobFast ( int n, Integer[] processId, Integer[]arrivalArray, Integer[]brustArray){

            int ct[] = new int[n]; // ct means complete time
            int ta[] = new int[n]; // ta means turn around time
            int wt[] = new int[n];  //wt means waiting time
            int f[] = new int[n];  // f means it is flag it checks process is completed or not
            int st = 0, tot = 0;
            float avgwt = 0, avgta = 0;

            System.out.println("SJF CPU Scheduling :");

            boolean a = true;
            while (true) {
                int c = n, min = 999;
                if (tot == n)
                    break;
                for (int i = 0; i < n; i++) {

                    if ((arrivalArray[i] <= st) && (f[i] == 0) && (brustArray[i] < min)) {
                        min = brustArray[i];
                        c = i;
                    }
                }
                if (c == n)
                    st++;
                else {
                    ct[c] = st + brustArray[c];
                    st += brustArray[c];
                    ta[c] = ct[c] - arrivalArray[c];
                    wt[c] = ta[c] - brustArray[c];
                    f[c] = 1;
                    tot++;
                }
            }
            System.out.println("\npid  arrival brust  complete turn waiting");
            for (int i = 0; i < n; i++) {
                avgwt += wt[i];
                avgta += ta[i];
                System.out.println(processId[i] + "\t " + arrivalArray[i] + "\t      " + brustArray[i] + "\t     " + ct[i] + "\t     " + ta[i] + "\t     " + wt[i]);
            }
            System.out.println("\naverage Turnaround time is :" + (avgta / n));
            System.out.println("average waiting time is :" + (avgwt / n));
        }

    }
