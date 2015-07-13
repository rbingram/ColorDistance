package org.ingram.color.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "COLORLIST", uniqueConstraints = {@UniqueConstraint(columnNames = "COLORLIST_ID"), @UniqueConstraint(columnNames = "NAME")})
public class ColorListEntity {

	@Id
	//@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "COLORLIST_ID", nullable = false)
	private int id = 1;
	
	@Column(name = "NAME", unique = true, nullable = false, length = 100)
	private String name = "";
	
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "COLOR_COLORLIST", joinColumns = { @JoinColumn(name = "COLORLIST_ID") }, inverseJoinColumns = { @JoinColumn(name = "COLOR_ID") })
	List<ColorEntity> colors = new ArrayList<ColorEntity>();
	
	
	public ColorListEntity(){
		
	}
	
	public ColorListEntity(String name){
		this.name = name;
	}
	
	public ColorListEntity(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public void addColor(ColorEntity c){
		colors.add(c);
	}
	
	public void setColors(List<ColorEntity> colors){
		this.colors = colors;
	}
	
	public List<ColorEntity> getColors(){
		return colors;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
