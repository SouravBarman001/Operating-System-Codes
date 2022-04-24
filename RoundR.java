package toc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RoundR {

    public static void main(String[] args) throws IOException {

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

        int n = processId.length;

        RoundR.Roundround(n,brustArray);
    }
    public static void Roundround(int n,Integer[] bt){


        int i,j,k,q=2,sum=0;
      //  System.out.println("Enter number of process:");
       // int n=Integer.parseInt(in.readLine());
       // int bt[]=new int[n];
        int wt[]=new int[n];
        int tat[]=new int[n];
        int a[]=new int[n];

        for(i=0;i<n;i++)
            a[i]=bt[i];
        for(i=0;i<n;i++)
            wt[i]=0;
        do{
            for(i=0;i<n;i++){
                if(bt[i]>q){
                    bt[i]-=q;
                    for(j=0;j<n;j++){
                        if((j!=i)&&(bt[j]!=0))
                            wt[j]+=q;
                    }
                }
                else{
                    for(j=0;j<n;j++){
                        if((j!=i)&&(bt[j]!=0))
                            wt[j]+=bt[i];
                    }
                    bt[i]=0;
                }
            }
            sum=0;
            for(k=0;k<n;k++)
                sum=sum+bt[k];
        }
        while(sum!=0);
        for(i=0;i<n;i++)
            tat[i]=wt[i]+a[i];
        System.out.println("process\t\tBT\tWT\tTAT");
        for(i=0;i<n;i++){
            System.out.println("process"+(i+1)+"\t"+a[i]+"\t"+wt[i]+"\t"+tat[i]);
        }
        float avg_wt=0;
        float avg_tat=0;
        for(j=0;j<n;j++){
            avg_wt+=wt[j];
        }
        for(j=0;j<n;j++){
            avg_tat+=tat[j];
        }
        System.out.println("Average waiting time :"+(avg_wt/n)+"\n Average turn around time :"+(avg_tat/n));

    }
    }

