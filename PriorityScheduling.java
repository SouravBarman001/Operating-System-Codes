
import java.util.ArrayList;
import java.util.Scanner;

public class PriorityScheduling {
    public static void main(String[] args) {


        ArrayList<Integer> process = new ArrayList<>();
        ArrayList<Integer> arrivalTime = new ArrayList<>();
        ArrayList<Integer> bTime = new ArrayList<>();
        ArrayList<Integer> Priority = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {

            process.add(Integer.parseInt(scanner.next()));
            arrivalTime.add(Integer.parseInt(scanner.next()));
            bTime.add(Integer.parseInt(scanner.next()));
            Priority.add(Integer.parseInt(scanner.next()));
        }
        scanner.close();

        Integer[] processId = new Integer[process.size()];
        processId = process.toArray(processId);

        Integer[] arrivalArray = new Integer[arrivalTime.size()];
        arrivalArray = arrivalTime.toArray(arrivalArray);

        Integer[] brustArray = new Integer[bTime.size()];
        brustArray = bTime.toArray(brustArray);

        Integer[] PriorityArray = new Integer[bTime.size()];
        PriorityArray = bTime.toArray(PriorityArray);

        int n = processId.length;
        PriorityScheduling.CPUalgo(n,processId,PriorityArray,brustArray);

    }
    public static void CPUalgo(int n,Integer[] p,Integer[] pp,Integer[] bt){

        int x,w[],t[],awt,atat,i;
        w = new int[n];
        t = new int[n];

       //sorting on the basis of priority
        for(i=0;i<n-1;i++)
        {
            for(int j=i+1;j<n;j++)
            {
                if(pp[i]>pp[j])
                {
                    x=pp[i];
                    pp[i]=pp[j];
                    pp[j]=x;
                    x=bt[i];
                    bt[i]=bt[j];
                    bt[j]=x;
                    x=p[i];
                    p[i]=p[j];
                    p[j]=x;
                }
            }
        }
        w[0]=0;
        awt=0;
        t[0]=bt[0];
        atat=t[0];
        for(i=1;i<n;i++)
        {
            w[i]=t[i-1];
            awt+=w[i];
            t[i]=w[i]+bt[i];
            atat+=t[i];
        }

//Displaying the process

        System.out.print("\n\nProcess \t Burst Time \t Wait Time \t Turn Around Time   Priority \n");
        for(i=0;i<n;i++)
            System.out.print("\n   "+p[i]+"\t\t   "+bt[i]+"\t\t     "+w[i]+"\t\t     "+t[i]+"\t\t     "+pp[i]+"\n");
        awt/=n;
        atat/=n;
        System.out.print("\n Average Wait Time : "+awt);
        System.out.print("\n Average Turn Around Time : "+atat);
    }
}
