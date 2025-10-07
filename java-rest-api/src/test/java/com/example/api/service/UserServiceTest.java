package com.example.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.api.model.User;
import com.example.api.repository.UserRepository;
import com.example.api.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        User user1 = new User(1L, "John Doe", "john@example.com");
        User user2 = new User(2L, "Jane Doe", "jane@example.com");
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.findAll();

        assertEquals(2, users.size());
        assertEquals("John Doe", users.get(0).getName());
        assertEquals("Jane Doe", users.get(1).getName());
    }

    @Test
    void testFindById() {
        User user = new User(1L, "John Doe", "john@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.findById(1L).orElse(null);

        assertNotNull(foundUser);
        assertEquals("John Doe", foundUser.getName());
    }

    @Test
    void testSave() {
        User user = new User(null, "John Doe", "john@example.com");
        when(userRepository.save(any(User.class))).thenReturn(new User(1L, "John Doe", "john@example.com"));

        User savedUser = userService.save(user);

        assertNotNull(savedUser.getId());
        assertEquals("John Doe", savedUser.getName());
    }

    @Test
    void testUpdate() {
        User existingUser = new User(1L, "John Doe", "john@example.com");
        User updatedUser = new User(1L, "John Smith", "johnsmith@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userService.update(1L, updatedUser);

        assertEquals("John Smith", result.getName());
    }

    @Test
    void testDelete() {
        doNothing().when(userRepository).deleteById(1L);

        assertDoesNotThrow(() -> userService.delete(1L));
        verify(userRepository, times(1)).deleteById(1L);
    }
}