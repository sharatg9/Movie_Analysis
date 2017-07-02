package task2;


import java.io.IOException;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;


public class Mapper1 extends Mapper <Object, Text, Text, Text>                       //Movie mapper 
	 {
	
	 public void map(Object key, Text value, Context context)throws IOException, InterruptedException 
	 {
		 String record = value.toString();
		 String[] parts = record.split("::");
		 
		 if(parts.length >= 1 ){
		 String movie_id = parts[0]; 
		 String movie_name = parts[1]; 
		
	 
		 context.write(new Text(movie_id), new Text("map1\t" + movie_name));   
		 
		 }
	 }
	
}

