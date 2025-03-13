package lk.tdm.Chatify.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique User ID

    @Column(nullable = false, unique = true)
    private String username; // Unique username (for login & display)

    @Column(nullable = false, unique = true)
    private String email; // Unique email (for authentication)

    @Column(nullable = false)
    private String password; // Hashed password (Bcrypt)

    private String profilePictureUrl; // URL for profile image

    @Column(nullable = false)
    private boolean onlineStatus; // True if the user is online, False otherwise

    private String about; // Short bio or status message

    @Column(nullable = false)
    private LocalDateTime createdAt; // Account creation date

    @Column(nullable = false)
    private LocalDateTime updatedAt; // Last profile update date

    @Column(nullable = true)
    private String lastSeen; // Last active time (for offline users)

    public User() {
    }

    public User(Long id, String username, String email, String password, String profilePictureUrl, boolean onlineStatus, String about, LocalDateTime createdAt, LocalDateTime updatedAt, String lastSeen) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePictureUrl = profilePictureUrl;
        this.onlineStatus = onlineStatus;
        this.about = about;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastSeen = lastSeen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public boolean isOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(boolean onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }
}
