
import java.util.*;

class FIFO
{
    // Method to find page faults using indexes
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

    public static void main(String args[])
    {

        ArrayList<Integer> ArrayList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {

            ArrayList.add(Integer.parseInt(scanner.next()));

        }

        Integer[] pages = new Integer[ArrayList.size()];
        pages = ArrayList.toArray(pages);

        int totalPage = pages.length;
        int memoryCapacity = 3;

        int faults = pageFaults(pages, memoryCapacity);
        System.out.println("Number of Miss:"+faults);
        System.out.println("Number of Hits:"+(totalPage-faults));
    }
}