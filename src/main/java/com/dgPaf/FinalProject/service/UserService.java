package com.dgPaf.FinalProject.service;

import com.dgPaf.FinalProject.dto.UserActivityDto;
import com.dgPaf.FinalProject.model.Furniture;
import com.dgPaf.FinalProject.model.User;
import com.dgPaf.FinalProject.repository.FurnitureRepository;
import com.dgPaf.FinalProject.repository.ReservationRepository;
import com.dgPaf.FinalProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FurnitureRepository furnitureRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean authenticateUser(String email, String password) {
        Optional<User> userOptional = findByEmail(email);

        // Check if user is present
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Debugging output for validation
            System.out.println("Password from request: " + password);
            System.out.println("Stored hashed password: " + user.getPassword());

            // Verify if the passwords match
            boolean passwordMatches = passwordEncoder.matches(password, user.getPassword());
            System.out.println("Password matches: " + passwordMatches);

            return passwordMatches;
        } else {
            // User not found
            System.out.println("User with email " + email + " not found.");
            return false;
        }
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    // Get count of donated items
    public long getItemsDonatedCount(Long userId) {
        return furnitureRepository.countDonatedItems(userId);
    }

    // Get count of uploaded items
    public long getUploadedItemsCount(Long userId) {
        return furnitureRepository.countUploadedItems(userId);
    }

    // Get count of distinct people helped
    public long getPeopleHelpedCount(Long userId) {
        return reservationRepository.countDistinctPeopleHelped(userId);
    }

    // Fetch uploaded items
    public List<Furniture> getUploadedItems(Long userId) {
        return furnitureRepository.findByOwnerId(userId);
    }

    // Fetch donated items
    public List<Furniture> getDonatedItems(Long userId) {
        // This can be the same as uploaded items if donation and upload are considered the same
        return furnitureRepository.findByOwnerId(userId);
    }

    public UserActivityDto getUserActivity(Long userId) {
        List<Furniture> uploadedItems = getUploadedItems(userId);
        List<Furniture> donatedItems = getDonatedItems(userId);
        long itemsDonatedCount = getItemsDonatedCount(userId);
        long peopleHelpedCount = getPeopleHelpedCount(userId);

        return new UserActivityDto(uploadedItems, donatedItems, itemsDonatedCount, peopleHelpedCount);
    }
}

