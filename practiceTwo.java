
import java.util.ArrayList;
import java.util.Scanner;


public class practiceTwo {
    public static void main(String[] args) {

        ArrayList<Integer> process = new ArrayList<>();
        ArrayList<Integer> arrivalTime = new ArrayList<>();
        ArrayList<Integer> bTime = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {

            process.add(Integer.parseInt(scanner.next()));
            arrivalTime.add(Integer.parseInt(scanner.next()));
            bTime.add(Integer.parseInt(scanner.next()));
        }
        scanner.close();

        Integer[] processId = new Integer[process.size()];
        processId = process.toArray(processId);

        Integer[] arrivalArray = new Integer[arrivalTime.size()];
        arrivalArray = arrivalTime.toArray(arrivalArray);

        Integer[] brustArray = new Integer[bTime.size()];
        brustArray = bTime.toArray(brustArray);

       //***********************


        int n = processId.length;

        practiceTwo.SJF(n,processId,arrivalArray,brustArray);




      //************************
    }

    private static void SJF(int n, Integer[] processId, Integer[] arrivalArray, Integer[] brustArray) {
        int ct[] = new int[n]; // ct means complete time
        int ta[] = new int[n]; // ta means turn around time
        int wt[] = new int[n];  //wt means waiting time
        int f[] = new int[n];  // f means it is flag it checks process is completed or not
        int st=0, tot=0;
        float avgwt=0, avgta=0;


        boolean a = true;
        while(true)
        {
            int c=n, min=999;
            if (tot == n) // total no of process = completed process loop will be terminated
                break;
            for (int i=0; i<n; i++)
            {
                /*
                 * If i'th process arrival time <= system time and its flag=0 and burst<min
                 * That process will be executed first
                 */
                if ((arrivalArray[i] <= st) && (f[i] == 0) && (brustArray[i]<min))
                {
                    min=brustArray[i];
                    c=i;
                }
            }
            /* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
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
        System.out.println ("\naverage tat is "+ (float)(avgta/n));
        System.out.println ("average wt is "+ (float)(avgwt/n));
    }


}
