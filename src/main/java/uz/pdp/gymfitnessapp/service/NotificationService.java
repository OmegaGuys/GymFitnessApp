package uz.pdp.gymfitnessapp.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import uz.pdp.gymfitnessapp.common.ApiException;
import uz.pdp.gymfitnessapp.dto.NotificationDto;
import uz.pdp.gymfitnessapp.dto.NotificationResDto;
import uz.pdp.gymfitnessapp.entity.Notification;
import uz.pdp.gymfitnessapp.entity.User;
import uz.pdp.gymfitnessapp.repository.NotificationRepository;
import uz.pdp.gymfitnessapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final UserRepository userRepository;
    private final NotificationRepository repository;
    private final ModelMapper mapper;

    public NotificationResDto notify(boolean notifyAll, NotificationDto notificationDto) {
        Notification notification = mapper.map(notificationDto, Notification.class);
        notification = repository.save(notification);

        if (notifyAll) {
            for (var user : userRepository.findAll()) {
                if (Objects.isNull(user.getNotifications()) ||
                        !user.getNotifications().isEmpty())
                    user.setNotifications(new ArrayList<>());

                user.getNotifications().add(notification);
                userRepository.save(user);
            }
        } else {
            User user = userRepository.findById(notificationDto.getUserId()).orElseThrow(() -> ApiException.throwException("User not found"));
            user.getNotifications().add(notification);
            userRepository.save(user);
        }
        return mapper.map(notification, NotificationResDto.class);
    }

    public void deleteNotifOfUser(UUID notificationId, UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> ApiException.throwException("User not found"));
        Notification notification = get(notificationId);
        user.getNotifications().remove(notification);
        userRepository.save(user);
    }

    protected Notification get(UUID id) {
        return repository.findById(id).orElseThrow(() -> ApiException.throwException("Notification not found"));
    }
}
