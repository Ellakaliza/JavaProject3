package lists;

import java.util.Comparator;

public class CustomComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        int result = o1.compareToIgnoreCase(o2);
        if(result==0)
            result = o1.compareTo(o2);
        return result;
    }

}
