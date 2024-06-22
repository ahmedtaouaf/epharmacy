package com.app.Epharmacy.Repository;

import com.app.Epharmacy.Entity.FaceImage;
import com.app.Epharmacy.Entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaceImageRepository extends JpaRepository<FaceImage, Integer> {
    FaceImage findByLogin(Login login);
}

