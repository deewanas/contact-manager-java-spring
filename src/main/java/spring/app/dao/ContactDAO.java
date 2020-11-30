package spring.app.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import spring.app.models.Contact;

@Component
public class ContactDAO {

	  
	
		
		  private DataSource dataSource; 
		  private JdbcTemplate jdbcTemplate;
		  
		  @Autowired public void setDataSource(DataSource dataSource) { 
			  this.dataSource= dataSource; 
			  this.jdbcTemplate = new JdbcTemplate(dataSource); 
			  }
		  
		  public List<Contact> index() { 
			  return jdbcTemplate.query("select * from contacts", 
					  new BeanPropertyRowMapper<>(Contact.class) ); }
	  
		
		  public void save(Contact contact) { 
			  jdbcTemplate.update("insert contacts values(default, ?, ?, ?)", 
					  contact.getName(), contact.getPhoneNumber(), contact.getEmail() ); 
			  }
		  
		  public void delete(int id) { 
			  jdbcTemplate.update("delete from contacts where id = ?", id); 
			  }
		  
		  public void update(int id, Contact contact) { 
		        	  
			  jdbcTemplate.update("update contacts set name=? where id=?", contact.getName(), id);
			  jdbcTemplate.update("update contacts set phone_number=? where id=?", contact.getPhoneNumber(), id);
			  jdbcTemplate.update("update contacts set email=? where id=?", contact.getEmail(), id);
		  }
		 
				

		public Contact show(int id) { 
			  
			  String SQL = "SELECT * FROM contacts";
		      List<Contact> contactList =  jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Contact.class));
		      for (Contact contact : contactList) {
		          if (contact.getId() == id) {
		              return contact;
		          }
		      }
		      return null;
		  
		  }
}
