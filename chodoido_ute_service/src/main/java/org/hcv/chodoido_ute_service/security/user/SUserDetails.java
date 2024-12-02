package org.hcv.chodoido_ute_service.security.user;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hcv.chodoido_ute_service.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SUserDetails implements UserDetails {
    Long id;
    String email;
    String password;
    Collection<GrantedAuthority> grantedAuthorities;

    public static UserDetails userDetailsBuilder(User user) {
        return SUserDetails.builder().id(user.getId()).email(user.getEmail()).password(user.getPassword())
                .grantedAuthorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))).build();
    }
    public List<String> getRoles(){
        return this.grantedAuthorities.stream().map(GrantedAuthority::getAuthority).toList();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
