package com.blogspot.evilnerdyowl.tasksequence;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Task {
	
	private String id;
	private String name;
	private Set<String> required;

	public Task() {
		setId( null );
		setName( null );
		this.setRequired( new LinkedHashSet<String>() );
	}
	
	public Task( String id ) {
		setId( id );
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}
	
	public Set<String> getRequired() {
		return required;
	}

	public void setRequired( Set<String> required ) {
		this.required = required;
	}
	
	public void addRequired( String taskId ) {
		if( taskId != null && ! taskId.isEmpty() ) {
			this.required.add( taskId );	
		}
	}

	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : 0;
	};

	@Override
	public boolean equals( Object anotherTask ) {
		if ( anotherTask == null || getClass() != anotherTask.getClass() ) {
			return false;
		} else if( this == anotherTask ) {
			return true;
		}
		
		return new EqualsBuilder().append( this.getId(), ((Task)anotherTask).getId() ).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder( this ).append( "id", this.getId() ).append( "name", this.getName() ).toString();
	}

}
