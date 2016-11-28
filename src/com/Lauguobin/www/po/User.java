package com.Lauguobin.www.po;

import java.io.Serializable;

public class User implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String user;
	private String password;
	private boolean identity; //用于判断权限狗
	
	public User()
	{
		super();
	}

	public User(String user, String password,boolean identity)
	{
		super();
		this.user = user;
		this.password = password;
		this.identity = identity;
	}


	public User(String user, String password)
	{
		super();
		this.user = user;
		this.password = password;
	}

	
	public User(int id, String user, String password, boolean identity)
	{
		super();
		this.id = id;
		this.user = user;
		this.password = password;
		this.identity = identity;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getIdentity()
	{
		return identity;
	}

	public void setIdentity(boolean identity)
	{
		this.identity = identity;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (user == null)
		{
			if (other.user != null)
				return false;
		}
		else if (!user.equals(other.user))
			return false;
		if (password == null)
		{
			if (other.password != null)
				return false;
		}
		else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	
}
