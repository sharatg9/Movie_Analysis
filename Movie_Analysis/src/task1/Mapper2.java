package task1;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class Mapper2 extends Mapper<Object, Text, Text, Text> {
	
	
	 public void map(Object key, Text value, Context context) throws IOException, InterruptedException 
	 {
		 String record = value.toString();
		 String[] parts = record.split("::");
		 String movie_id = parts[1];
	 
		 context.write(new Text(movie_id), new Text("map2\t" + "1"));                 // ( 22 , map2 1 )  note : [count is in text]
	 }

}
