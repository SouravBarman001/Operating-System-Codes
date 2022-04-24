package toc;

class PrintCount{

    //print thread counter
    public void printCounter() {

        try {
            for(int i = 1; i <= 50; i++) {
                System.out.println(Thread.currentThread().getName()+":"+i );

                if(i==50){
                    if(Thread.currentThread().getName().contains("Thread_2")){

                        try {
                            Main.NextThread();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }


        } catch (Exception e) {
            System.out.println("Thread  interrupted.");
        }

    }

    }




//thread class
class ThreadCounter extends Thread {
    private Thread t;
    private String threadName;
    PrintCount  PD;
    //class constructor for initialization
    ThreadCounter( String name,  PrintCount pd) {
        threadName = name;
        PD = pd;
    }
    //run () method for thread with synchronized block
    public void run() {
        synchronized(PD) {
            PD.printCounter();
        }

    }
    //start () method for thread
    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();

        }
    }
}
public class Main {
    public static void main(String args[]) throws Exception{
        PrintCount PD = new PrintCount();
        //create thread instances
        ThreadCounter T1 = new ThreadCounter( "Thread_1 ", PD );
        ThreadCounter T2 = new ThreadCounter( "Thread_2 ", PD );


        //start both the threads
        T1.start();
        T2.start();      // wait for threads to end
        try {
            T1.join();
            T2.join();
        } catch ( Exception e) {
            System.out.println("Interrupted");
        }




    }
    public static void NextThread()throws Exception{

        Thread t1 = new Thread(()->
        {
            for(int i=51;i<=100;i++){
                System.out.println(" Thread_1:"+i);
                try{Thread.sleep(1000);}catch(Exception e){}
            }

        });

        Thread t2 = new Thread(()->
        {
            for(int i=51;i<=100;i++){
                System.out.println("Thread_2:"+i);
                try{Thread.sleep(1000);}catch(Exception e){}
            }

        });

        t1.start();
        try{Thread.sleep(10);}catch(Exception e){}
        t2.start();

        t1.join();
        t2.join();
    }
}