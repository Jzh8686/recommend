package com.gyj.gx.base.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AUserDetailsService implements UserDetailsService {

//    @Autowired
//    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User mUser = userRepository.findByUsername(username);
//        if (mUser==null)
//            throw new UsernameNotFoundException("User does not exist");
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        if (mUser.getRole()==null)
//            authorities.add(new SimpleGrantedAuthority("MEMBER"));
//        else {
//            String[] roles = mUser.getRole().split("[|]");
//            for (String role : roles)
//                authorities.add(new SimpleGrantedAuthority(role));
//        }
//        return new SecurityUser(mUser.getId(),mUser.getUsername(), mUser.getPassword(),authorities);
        return null;
    }
}
