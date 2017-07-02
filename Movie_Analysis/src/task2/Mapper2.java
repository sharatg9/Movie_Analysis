package task2;


import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class Mapper2 extends Mapper<Object, Text, Text, Text> {
	
	
	 public void map(Object key, Text value, Context context) throws IOException, InterruptedException 
	 {
		 String record = value.toString();
		 String[] parts = record.split("::");
		 String movie_id = parts[1];
		 String rating = parts[2];
		 
		 String A = "";
		 String B ="";
		 
		 if(rating.equals("5")){
			 
			A = movie_id;
			B = "map2\t"+"1#1" ;
		 }
		 else{
			 A = movie_id ;
			 B = "map2\t"+"1#0" ;
		 }
		 
		 
		 context.write(new Text(A), new Text(B));                 // ( 22 , map2 1 )  note : [count is in text]
		 
		 
	}

}
