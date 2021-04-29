package org.fis2021.services;


import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.fis2021.exceptions.UsernameAlreadyExistsException;
import org.fis2021.exceptions.UsernameNotFoundException;
import org.fis2021.models.User;
import org.dizitart.no2.objects.Cursor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static org.fis2021.services.FileSystemService.getPathToFile;

public class UserService {

    private static ObjectRepository<User> userRepository;

    private static Nitrite database;

    private static int id = 0;
    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("cbglapp.db").toFile())
                .openOrCreate("admin", "admin");

        userRepository = database.getRepository(User.class);
    }

    public static void closeDatabase(){
        database.close();
    }

    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        checkUserOrPasswordIsNull(username,password);
        userRepository.insert(new User(username, encodePassword(username, password), role,id));
        id++;
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    public static User getUser(String username) throws UsernameNotFoundException {
        Cursor<User> cursor = userRepository.find(ObjectFilters.eq("username",username));
        for(User user : cursor)
            return user;
        throw new UsernameNotFoundException(username);
    }



    public static String getHashedPassword(String username) throws UsernameNotFoundException {
        return getUser(username).getPassword();
    }

    private static void checkUserOrPasswordIsNull(String username, String password) throws UsernameAlreadyExistsException{
        if(username.equals("")  || password.equals(""))
            throw new UsernameAlreadyExistsException(username,password);
    }

    private static void checkUserOrPasswordIsNullLogin(String username, String password) throws UsernameNotFoundException{
        if(username.equals("") || username.isEmpty())
            throw new UsernameNotFoundException(username,password);
    }

    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", "");
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Fail!");
        }
        return md;
    }


}