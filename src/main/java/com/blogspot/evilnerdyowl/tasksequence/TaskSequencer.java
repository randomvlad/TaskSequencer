package com.blogspot.evilnerdyowl.tasksequence;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class TaskSequencer {

	// TODO: exception to account for tasks with a cyclical dependency
	public List<Task> sequence( List<Task> tasks ) {
		List<Task> orderedTasks = new ArrayList<>();

		orderedTasks.add( new Task( "1", "Wake Up" ) );
		orderedTasks.add( new Task( "2", "Brush Teeth" ) );
		orderedTasks.add( new Task( "3", "Floss Teeth" ) );
		orderedTasks.add( new Task( "4", "Take Shower" ) );

		return orderedTasks;
	}

	// TODO: convert to Task objects
	private void topoSort( List<String> sorted, Map<String, List<String>> outstanding ) {
		if ( outstanding.isEmpty() ) {
			return;
		}

		for ( Map.Entry<String, List<String>> entry : outstanding.entrySet() ) {
			String taskId = entry.getKey();
			List<String> requiredTasks = entry.getValue();

			boolean satisfied = requiredTasks.stream().allMatch( requiredTask -> sorted.contains( requiredTask ) );
			if ( satisfied ) {
				sorted.add( taskId );
			}
		}

		for ( String taskId : sorted ) {
			if ( outstanding.containsKey( taskId ) ) {
				outstanding.remove( taskId );
			}
		}

		topoSort( sorted, outstanding );
	}
}