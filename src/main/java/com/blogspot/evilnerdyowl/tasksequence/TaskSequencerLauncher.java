package com.blogspot.evilnerdyowl.tasksequence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

public class TaskSequencerLauncher {

	public static void main( String[] args ) {
		
		List<Task> tasks = new ArrayList<>();
		
		TaskSequencer sequencer = new TaskSequencer();
		
		List<Task> orderedTasks = sequencer.sequence( tasks );
		
		System.out.println( "Ordered Tasks:" );
		
		int counter = 1;
		for( Task task : orderedTasks ) {
			System.out.println( counter + ") " + task.getName() );
			counter++;
		}
	}
	
	// TODO: create separate class TaskRepository or TasksFileReader ?
	public static List<Task> getTasks( String fileName ) {
		List<Task> tasks = new ArrayList<>();

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			BufferedReader fileReader = new BufferedReader( new FileReader( fileName ) );

			JsonNode rootNode = objectMapper.readTree( fileReader );

			for ( JsonNode taskNode : rootNode.path( "tasks" ) ) {
				String taskId = taskNode.get( "id" ).getTextValue();
				String taskName = taskNode.get( "name" ).getTextValue();
				
				// TODO: add support for required
//				List<String> required = new ArrayList<>();
//				for ( JsonNode requiredNode : taskNode.path( "required" ) ) {
//					required.add( requiredNode.getTextValue() );
//				}

				tasks.add(  new Task( taskId, taskName ) );
			}
		} catch ( Exception e ) {
			// TODO: better error handling for invalid file name and possibly invalid json format
			
			throw new RuntimeException( e );
		}

		return tasks;
	}

}
