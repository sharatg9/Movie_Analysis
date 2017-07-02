package task1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;




public class Driver {

	public static void main(String[] args) throws Exception{
		
		// Create a new Job
		
	
		Configuration conf =new Configuration();
		
		// Create a  Job
		Job job = new Job(conf,"task1");
	
	
		 job.setJarByClass(Driver.class);
		 job.setMapperClass(Mapper1.class);
		 job.setMapperClass(Mapper2.class);
		 job.setReducerClass(Reducer1.class);
		 job.setOutputKeyClass(Text.class);
		 job.setOutputValueClass(Text.class);
		  
		 MultipleInputs.addInputPath(job, new Path(args[0]),TextInputFormat.class, Mapper1.class);
		 
		
		 MultipleInputs.addInputPath(job, new Path(args[1]),TextInputFormat.class, Mapper2.class);
		 Path outputPath = new Path(args[2]);
		  
		 FileOutputFormat.setOutputPath(job, outputPath);
		 
		 System.exit(job.waitForCompletion(true) ? 0 : 1);
		 
		 
	}
	
}
