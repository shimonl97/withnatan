package com.brahalla.Cerberus.model.json.response;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.brahalla.Cerberus.model.json.response.GetGroupsAndUsersResponse.DatedObjects;


/**
 * Created by Me on 9/29/2016.
 */
public class GetGroupsAndUsersResponse {
    private List<DatedObjects> groupsAndUsers = new ArrayList<>();

    

	public List<DatedObjects> getGroupsAndUsers() {
		return groupsAndUsers;
	}



	public void setGroupsAndUsers(List<DatedObjects> groupsAndUsers) {
		this.groupsAndUsers = groupsAndUsers;
	}
	
	public void addObject(Date d,Object o) {
		DatedObjects dateObject=new DatedObjects() ;
		dateObject.setDate(d);
		dateObject.setObject(o);
		getGroupsAndUsers().add(dateObject);
	}
	
	public class DatedObjects implements Comparable<DatedObjects>  {
		private Date date;
		private Object object;
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public Object getObject() {
			return object;
		}
		public void setObject(Object object) {
			this.object = object;
		}
		@Override
		public int compareTo(DatedObjects o) {
			if (getDate()==null && o.getDate()==null) {
				return 0;
			} else if (getDate()==null) {
				return -1;
			} else if (o.getDate()==null) {
				return 1; 
			} else 
				return getDate().compareTo(o.getDate())*-1;
		}
		
	}

	
}
