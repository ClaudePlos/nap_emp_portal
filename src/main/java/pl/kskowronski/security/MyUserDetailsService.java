package pl.kskowronski.security;

import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kskowronski.data.entity.egeria.ek.User;
import pl.kskowronski.data.service.egeria.ek.UserRepo;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService
{

    @Autowired
    private UserRepo userRepo;

    //@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //BigDecimal.valueOf(Long.parseLong(username))
        Optional<User> user = userRepo.findByUsername(username);
        if (user.get() == null) {
            throw new UsernameNotFoundException("Could not find user with that email");
        }
        return new MyUserDetails(user.get());
    }

}
