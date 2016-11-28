package com.Lauguobin.www.po;

public class Course
{
	private int cno;
	private String Cname;
	private int Ccredit;
	
	public Course(int cno, String courseName, int credit)
	{
		super();
		this.cno = cno;
		this.Cname = courseName;
		this.Ccredit = credit;
	}

	public Course()
	{
		super();
	}

	public int getCno()
	{
		return cno;
	}

	public void setCno(int cno)
	{
		this.cno = cno;
	}

	public String getCName()
	{
		return Cname;
	}

	public void setCName(String courseName)
	{
		this.Cname = courseName;
	}

	public int getCcredit()
	{
		return Ccredit;
	}

	public void setCcredit(int credit)
	{
		this.Ccredit = credit;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + cno;
		result = prime * result + ((Cname == null) ? 0 : Cname.hashCode());
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
		Course other = (Course) obj;
		if (cno != other.cno)
			return false;
		if (Cname == null)
		{
			if (other.Cname != null)
				return false;
		}
		else if (!Cname.equals(other.Cname))
			return false;
		return true;
	}


	
	
	
}
