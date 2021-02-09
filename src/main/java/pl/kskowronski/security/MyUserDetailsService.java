package pl.kskowronski.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kskowronski.data.entity.egeria.ek.User;
import pl.kskowronski.data.entity.egeria.global.NapUser;
import pl.kskowronski.data.service.egeria.ek.UserRepo;
import pl.kskowronski.data.service.egeria.global.NapUserRepo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService
{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private NapUserRepo napUserRepo;

    //@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = null;
        try {
            user = userRepo.findByUsername(username);
            user.get().setPassword(getMd5(user.get().getPassword()));
            if (user.get().getPrcDgKodEk().equals("EK04")) {// check, maybe he is in EK04
                user.get().setPassword("");
            }
        } catch (Exception ex){
            Optional<NapUser> napUser = napUserRepo.findByUsername(username);
            if (napUser.isPresent()) {
                user = userRepo.findById(napUser.get().getPrcId());
                user.get().setPassword(napUser.get().getPassword());
            }
        }

        if (user.get() == null) {
            throw new UsernameNotFoundException("Could not find user with this username and pass");
        }
        return new MyUserDetails(user.get());
    }

    private static String getMd5(String input) {
        try {
            // Static getInstance method is called with hashing SHA
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method called
            // to calculate message digest of an input
            // and return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown"
                    + " for incorrect algorithm: " + e);
            return null;
        }
    }

}
