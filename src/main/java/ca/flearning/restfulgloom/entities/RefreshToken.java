package ca.flearning.restfulgloom.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * For persisting auth refresh tokens to the db.
 */
@Entity
@Table(name="REFRESHTOKENS")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long tokenId;

    private String token;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiry;

    private String username;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return token;
    }


    public void setRefreshToken(String refreshToken) { this.token = refreshToken; }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date issuedAt) {
        this.expiry = issuedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	@Override
	public String toString() {
		return "RefreshToken [tokenId=" + tokenId + ", token=" + token + ", expiry=" + expiry + ", username=" + username
				+ "]";
	}
}
