package com.blogspot.evilnerdyowl.tasksequence;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

public class TaskSequenceLauncher {

	public static void main( String[] args ) {

		Process process;
		try {
			process = getProcess(
					"/Users/vlad/Documents/Workspace/TaskSequencer/src/main/resources/process/get-out-of-bed.json" );
		} catch ( Exception e ) {
			e.printStackTrace();
			return;
		}
		
		List<Task> tasks = process.getTasks();
		
		TaskSequencer sequencer = new TaskSequencer();

		List<Task> orderedTasks = sequencer.sequence( tasks );

		System.out.println( process.getName() + ":" );

		int counter = 1;
		for ( Task task : orderedTasks ) {
			System.out.println( counter + ") " + task.getName() );
			counter++;
		}
	}

	public static Process getProcess( String fileName )
			throws FileNotFoundException, JsonProcessingException, IOException {
		List<Task> tasks = new ArrayList<>();

		BufferedReader fileReader = new BufferedReader( new FileReader( fileName ) );

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode rootNode = objectMapper.readTree( fileReader );

		for ( JsonNode taskNode : rootNode.path( "tasks" ) ) {
			Task task = new Task();

			task.setId( taskNode.get( "id" ).getTextValue() );
			task.setName( taskNode.get( "name" ).getTextValue() );

			for ( JsonNode requiredNode : taskNode.path( "required" ) ) {
				task.addRequired( requiredNode.getTextValue() );
			}

			tasks.add( task );
		}

		return new Process( rootNode.get( "name").getTextValue(), tasks );
	}

}
