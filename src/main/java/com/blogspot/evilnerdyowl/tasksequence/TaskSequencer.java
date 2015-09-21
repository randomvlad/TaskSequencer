package com.blogspot.evilnerdyowl.tasksequence;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class TaskSequencer {

	// TODO: exception to account for tasks with a cyclical dependency
	public List<Task> sequence( List<Task> tasks ) {
		List<Task> orderedTasks = new ArrayList<>();

		topoSort( orderedTasks, tasks );

		return orderedTasks;
	}

	private void topoSort( List<Task> sorted, List<Task> outstanding ) {
		if ( outstanding.isEmpty() ) {
			return;
		}

		for ( Task task : outstanding ) {
			Set<String> requiredTaskIds = task.getRequired();

			boolean satisfied = requiredTaskIds.stream()
					.allMatch( requiredTaskId -> sorted.contains( new Task( requiredTaskId ) ) );

			if ( satisfied ) {
				sorted.add( task );
			}
		}

		for ( Task task : sorted ) {
			if ( outstanding.contains( task ) ) {
				outstanding.remove( task );
			}
		}

		topoSort( sorted, outstanding );
	}
}