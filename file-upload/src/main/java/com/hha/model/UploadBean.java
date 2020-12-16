package com.hha.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;


@Named
@ViewScoped
public class UploadBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Part file;
	
	private List<Division> list;
	
	public void upload() {
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))){
			
			String line = null;
			
			list = new ArrayList<>();
			
			while(null != (line = br.readLine())) {
				
				Division division = new Division();
				division.setName(line);
				list.add(division);
				division.setId(list.size());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public List<Division> getList() {
		return list;
	}
	
}
