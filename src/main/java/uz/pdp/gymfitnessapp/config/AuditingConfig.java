//package uz.pdp.gymfitnessapp.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.UUID;
//
//@Configuration
//@EnableJpaAuditing
//public class AuditingConfig {
//
//    @Bean
//    public AuditorAware<UUID> auditorProvider() {
//        return new AuditingAwareImpl();
//    }
//
//    static class AuditingAwareImpl implements AuditorAware<UUID> {
//
//        @Override
//        public Optional<UUID> getCurrentAuditor() {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if (Objects.isNull(authentication)
//                    || !authentication.isAuthenticated()
//                    || Objects.equals("anonymousUser", authentication.getPrincipal()))
//                return Optional.empty();
//
//            UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
//            return Optional.of(principal.getId());
//        }
//    }
//}
