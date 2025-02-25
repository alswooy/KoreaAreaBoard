package com.min.koreaareaboard.attachments.repository;

import com.nimbusds.openid.connect.sdk.assurance.evidences.attachment.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Long> {
}
