package uz.pdp.gymfitnessapp.common;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.gymfitnessapp.entity.User;

@RequiredArgsConstructor
public class CommonUtils {

    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
