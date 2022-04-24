
import java.util.*;

public class OptimalAndFifo{

    static int search(int key, int frame[], int frame_size){
        int x = 0;
        for(int i = 0; i < frame_size; i++){
            if(key == frame[i]){
                x = 1;
            }
        }
        return x;
    }

    static int predict(Integer pages[], int frame[], int n_pages, int frame_size, int start){
        int pos = Integer.MIN_VALUE;
        int res = -1;
        for(int i = 0; i < frame_size; i++){
            int j;
            for(j = start; j < n_pages; j++){
                if(pages[j] == frame[i]){
                    if(pos < j){
                        pos = j;
                        res = i;
                    }
                    break;
                }
            }
            if(j == n_pages){
                return i;
            }
        }
        return (res == -1) ? 0 : res;
    }

    static void optimalPage(int n_pages, Integer pages[], int frame_size){

        System.out.print("Optimal Page Replacement Algorithm:");
        System.out.print("\n");

        int frame[] = new int[frame_size];
        int f_sz = 0; // to count frame size

        int hit = 0;
        for(int i = 0; i < n_pages; i++){

            //Page Found : HIT
            if(search(pages[i], frame, frame_size) == 1){
                hit++;
                continue;
            }

            // Page not Found : MISS
            if(f_sz < frame_size){
                frame[f_sz] = pages[i];
                f_sz++;
            }
            else{
                int pos = predict(pages, frame, n_pages, frame_size, i + 1);
                frame[pos] = pages[i];
            }
        }

        System.out.println("Number of Hits: " + hit);
        System.out.println("Number of Miss:" + (n_pages - hit));
    }

    public static void main(String[] args){

        //*****************************

        ArrayList<Integer> ArrayList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {

            ArrayList.add(Integer.parseInt(scanner.next()));

        }

        Integer[] pages = new Integer[ArrayList.size()];
        pages = ArrayList.toArray(pages);

        //****************************
        int n_pages = pages.length, frame_size = 3;

        OptimalAndFifo.optimalPage(n_pages, pages, frame_size);

        // FIFO
        int totalPage = pages.length;
        int memoryCapacity = 3;

        int faults = OptimalAndFifo.pageFaults(pages, memoryCapacity);
        System.out.println("\n");
        System.out.println("First Come First Out algorithm:");

        System.out.println("Number of Miss:"+faults);
        System.out.println("Number of Hits:"+(totalPage-faults));

    }

    // Static method for FIFO
    static int pageFaults(Integer pages[], int capacity)
    {
        if (capacity == 0)
            return 0;

        Queue<Integer> memory = new LinkedList<>() ;

        HashSet<Integer> pagesInFrames = new HashSet<>(capacity);

        int faults = 0;

        for (int page : pages)
        {
            if(!pagesInFrames.contains(page))
            {

                if (memory.size() == capacity)
                {
                    int firstPage = memory.poll();
                    pagesInFrames.remove(firstPage);

                }

                memory.add(page);
                pagesInFrames.add(page);
                faults++;
            }
        }
        return faults;
    }

}