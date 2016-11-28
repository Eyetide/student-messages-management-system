package com.Lauguobin.www.po;

import java.io.Serializable;

public class Score implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int score;
	private int sCno;
	private int sId;
	private int credit;
	
	
	public Score(int score, int sCno, int sId)
	{
		super();
		this.score = score;
		this.sCno = sCno;
		this.sId = sId;
	}

	public int getCredit()
	{
		return credit;
	}

	public void setCredit(int credit)
	{
		this.credit = credit;
	}

	public Score(int score)
	{
		super();
		this.score = score;
	}
	
	public Score()
	{
		super();
	}
	
	public Score(int score, int sCno, int sId,int credit)
	{
		super();
		this.score = score;
		this.sCno = sCno;
		this.sId = sId;
		this.credit = credit;
	}

	public int getCno()
	{
		return sCno;
	}

	public void setCno(int sCno)
	{
		this.sCno = sCno;
	}

	public int getId()
	{
		return sId;
	}

	public void setId(int sId)
	{
		this.sId = sId;
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

}
