package com.sampleProject.model;

import com.google.gson.Gson;

public class BaseModel
{
	@Override
	public String toString()
	{
		return new Gson().toJson(this).toString();
	}
	

}
