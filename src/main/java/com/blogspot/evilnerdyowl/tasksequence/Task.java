package com.blogspot.evilnerdyowl.tasksequence;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Task {

	private String id;
	private String name;
	
	// TODO: add support for required tasks
	// private List<String> required;

	public Task() {
		this( null, null );
	}

	public Task( String id, String name ) {
		this.setId( id );
		this.setName( name );
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
