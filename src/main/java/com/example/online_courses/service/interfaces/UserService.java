package com.example.online_courses.service.interfaces;

import com.example.online_courses.dto.UserData;
import com.example.online_courses.exceptions.UserAlreadyExistException;

public interface UserService {

    void register(final UserData user) throws UserAlreadyExistException;
    boolean checkIfUserExist(final String email);

    //void sendRegistrationConfirmationEmail(final UserEntity user);
    //boolean verifyUser(final String token) throws InvalidTokenException;
    //UserEntity getUserById(final String id) throws UnkownIdentifierException;
    //MfaTokenData mfaSetup(final String email) throws UnkownIdentifierException, QrGenerationException;

}
