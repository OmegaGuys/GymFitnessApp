package uz.pdp.gymfitnessapp.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.gymfitnessapp.common.AppConstants;
import uz.pdp.gymfitnessapp.entity.Attachment;
import uz.pdp.gymfitnessapp.repository.AttachmentRepository;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final Storage storage;

    public Attachment upload(@RequestPart(name = "file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        BlobId blobId = BlobId.of(AppConstants.BUCKET_NAME, Objects.requireNonNull(fileName));
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
        storage.create(blobInfo, file.getBytes());

        Attachment attachment = new Attachment(
                UUID.randomUUID(),
                file.getName(),
                file.getOriginalFilename(),
                file.getContentType(),
                file.getSize());

        attachment = attachmentRepository.save(attachment);
        return attachment;
    }

    public byte[] retrieve(UUID id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow();
        BlobId blobId = BlobId.of(AppConstants.BUCKET_NAME, attachment.getOriginalName());
        Blob blob = storage.get(blobId);
        return blob.getContent();
    }
}
