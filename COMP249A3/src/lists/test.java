package lists;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class test {

	public static void main(String[] args) {
		BufferedWriter wriite;
		try {
			wriite=new BufferedWriter(new FileWriter("test.pdf"));
			wriite.write("something");
			wriite.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static int recursivetest(int[] array) {
		if(array.length==1)
			return 0;
		else if(array.length==2)
			return array[1];
		else {
			int[] newarray=new int[array.length-2];
			for(int i=0;i<newarray.length;i++) {
				newarray[i]=array[i+2];
			}
			return array[1]+recursivetest(newarray);
		}
	}

}
