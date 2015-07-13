package org.ingram.color.entity;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.ingram.color.util.HibernateUtil;
import org.ingram.color.web.ColorValidationException;

@Entity
//@Table(name = "COLOR", uniqueConstraints = {@UniqueConstraint(columnNames = "COLOR_ID")})
@Table(name = "COLOR")
public class ColorEntity {

	@Column(name = "NAME", unique = true, nullable = false, length = 100)
	private String name = null;
	
	@Id
	@GeneratedValue
	@Column(name = "COLOR_ID", nullable = false)
	private Integer id = null;
	
	@Column(name = "X", unique = false, nullable = false)
	private Double x = null;
	
	@Column(name = "Y", unique = false, nullable = false)
	private Double y = null;
	
	@Column(name = "Z", unique = false, nullable = false)
	private Double z = null;
	
	public ColorEntity(){
	}
	
	public ColorEntity(String name, double x, double y, double z){
		setName(name);
		setX(x);
		setY(y);
		setZ(z);
	}
	
	public ColorEntity(ColorEntity e){
		this(e.getName(), e.getX(), e.getY(), e.getZ());
		setId(e.getId());
	}
	
	
	public void save(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			session.save(this);
			
			/*
			 * The final version of this web service will support multiple color lists.
			 * Some of the plumbing is already in place for this.
			 * In the mean time, the below hackily inserts the new color into the color/color list link table for
			 * the only current list -- id 1.
			 */
			SQLQuery sqlQuery = session.createSQLQuery("INSERT INTO COLOR_COLORLIST (COLORLIST_ID, COLOR_ID) VALUES (?,?)");
			sqlQuery.setInteger(0, 1);
			sqlQuery.setInteger(1, getId());
			sqlQuery.executeUpdate();
			
			session.getTransaction().commit();
		}finally{
			session.close();
		}
	}
	
	
	public String getName() {
		return name;
	}

	private static final String NAME_REGEX = "^[A-Za-z0-9 \\.]+$";
	
	public void setName(String name) {
		if(!Pattern.matches(NAME_REGEX, name)){
			throw new ColorValidationException("Color name " + name + " does not match regex string " + NAME_REGEX );
		}
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getZ() {
		return z;
	}

	public void setZ(Double z) {
		this.z = z;
	}

	
	protected static ColorEntity fetchByName(String name){
		ColorEntity entity = null;
		
		if(!StringUtils.isBlank(name)){
			Session session = HibernateUtil.getSessionFactory().openSession();
			try{
				entity = (ColorEntity)session.createQuery(
						"from ColorEntity where lower(name) = ?")
						.setString(0, name.trim().toLowerCase()).uniqueResult();
			}finally{
				session.close();
			}
		}
		
		return entity;
	}
	
	protected static ColorEntity fetchById(int id){
		ColorEntity entity = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			entity = (ColorEntity)session.createQuery(
					"from ColorEntity where id = ?")
					.setInteger(0, id).uniqueResult();
		}finally{
			session.close();
		}

		return entity;
	}
	
	public static boolean deleteById(int id){
		boolean deleted = false;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			ColorEntity entity = fetchById(id);
			if(entity != null){
				session.delete(entity);

				//See comment in ColorEntity.save()
				SQLQuery sqlQuery = session.createSQLQuery("DELETE FROM COLOR_COLORLIST WHERE COLOR_ID=?");
				sqlQuery.setInteger(0, id);
				sqlQuery.executeUpdate();
				
				deleted = true;
			}
		}finally{
			session.close();
		}
		
		return deleted;
	}


}
