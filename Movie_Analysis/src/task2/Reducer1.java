package task2;



import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class Reducer1 extends Reducer<Text, Text, Text, IntWritable>{

	HashMap<String, Integer> map = new HashMap<String, Integer>();
	
	 public void reduce(Text key, Iterable<Text> values, Context context)throws IOException, InterruptedException 
	 {
		 
		 String movie_name ="";
		 int count_view = 0;
		 int count_ranting =0;
	
		 
		 for (Text t : values){
			 
			 
			 String[] token = t.toString().split("\t");    
		//	 String[] token_1 = token[0].split(","); 
			 
			 if (token[0].equals("map1")){
				 
				 movie_name = token[1];
				
			 }else if (token[0].equals("map2")){
				 
				String[] sub_token = token[1].split("#") ;
				
				count_view = count_view + Integer.parseInt(sub_token[0]);
				count_ranting = count_ranting + Integer.parseInt(sub_token[1]);
				
				 
			 }
		
		 		
		 }	
		 
		 	if (count_view > 39 ){
	 			
	 			
		 		map.put(movie_name,count_ranting);
	 		}
		 
	}
	 
	 @Override
     protected void cleanup(Context context) throws IOException, InterruptedException {
		 
		 Object[] a = map.entrySet().toArray();
			Arrays.sort( a , new Comparator() {
			    public int compare(Object o1, Object o2) {
			        return ((Map.Entry<String, Integer>) o2).getValue()
			                   .compareTo(((Map.Entry<String, Integer>) o1).getValue());
			    }
			});
			
			int count = 0 ;
			for (Object e : a) {
				String movie_name = ((Map.Entry<String, Integer>) e).getKey() ;
				int count_ranting =  ((Map.Entry<String, Integer>) e).getValue(); 		
				
				if(count == 20 ){ break ; }
				
				context.write(new Text(movie_name), new IntWritable(count_ranting));
				count++;
				
				
				  
			}
			
			 	
		 
	 	}
}

