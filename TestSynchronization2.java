package toc;
class Table{
    synchronized void printTable(int n){//synchronized method
        for(int i=1;i<=n;i++){
            System.out.println("Thread-1:"+i);

            if(i==50){
                for(int j=1;j<=50;j++){
                    System.out.println("Thread-2:"+j);
                    try{
                        Thread.sleep(400);
                    }catch(Exception e){System.out.println(e);}
                }
            }

            try{
                Thread.sleep(400);
            }catch(Exception e){System.out.println(e);}
        }

    }
}

class MyThread1 extends Thread{
    Table t;
    MyThread1(Table t){
        this.t=t;
    }
    public void run(){
        t.printTable(50);
    }

}
class MyThread2 extends Thread{
    Table t;
    MyThread2(Table t){
        this.t=t;
    }
    public void run(){
        t.printTable(50);
    }
}

public class TestSynchronization2{
    public static void main(String args[]){
        Table obj = new Table();//only one object
        MyThread1 t1=new MyThread1(obj);
        MyThread2 t2=new MyThread2(obj);
        t1.start();
        t2.start();
    }
}