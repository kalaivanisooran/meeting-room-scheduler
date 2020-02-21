package cts.rabobank.glassdoorscheduler.entity;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "usrEmpId", "fullName", "status", "creadtedDate", "role", "refreshToken" })
@Entity
@Table(name = "USER_INFO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfo implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "USR_EMP_ID",nullable = false,unique = true)
	@Size(max = 7)
	private String usrEmpId;

	@Column(name = "FULL_NAME", nullable = false)
	@Size(max = 50)
	private String fullName;

	//@JsonIgnore
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "STATUS")
	private Boolean status;

	@Column(name = "CREATED_DATE")
	//@Temporal(TemporalType.DATE)
	private Date creadtedDate;

	@Column(name = "ROLE")
	private String role;

	
	@Column(name = "REFRESH_TOKEN")
	private String refreshToken;
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<SimpleGrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(this.role));
		return grantedAuthorities;
	}		
	

	@Override
	public String getUsername() {
		return this.usrEmpId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
