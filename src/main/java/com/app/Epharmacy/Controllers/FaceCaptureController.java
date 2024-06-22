package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.FaceImage;
import com.app.Epharmacy.Entity.Login;
import com.app.Epharmacy.Repository.FaceImageRepository;
import com.app.Epharmacy.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.util.Base64;
import java.util.Map;

@Controller
public class FaceCaptureController {

    @Autowired
    private FaceImageRepository faceImageRepository;

    @Autowired
    private LoginRepository loginRepository;

    @PostMapping("/capture-face")
    public String captureFace(@RequestParam("imageData") String imageData, Principal principal) throws IOException {
        String username = principal.getName();
        Login login = loginRepository.findByUsername(username);

        if (login != null) {
            FaceImage faceImage = new FaceImage();
            faceImage.setLogin(login);
            faceImage.setImage(imageData.getBytes());
            faceImageRepository.save(faceImage);
        }

        return "redirect:admin/dashboard";
    }

    @PostMapping("/face-login")
    public String captureFace(@RequestBody Map<String, String> requestBody) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            // Handle unauthenticated request (e.g., redirect to login page)
            return "redirect:/login";
        }

        String username = authentication.getName();
        Login login = loginRepository.findByUsername(username);

        if (login != null && requestBody.containsKey("imageData")) {
            String imageData = requestBody.get("imageData");

            // Convert base64 image data to byte array
            byte[] imageBytes = Base64.getDecoder().decode(imageData.split(",")[1]);

            // Load captured image as OpenCV Mat
            Mat capturedImage = Imgcodecs.imdecode(new MatOfByte(imageBytes), Imgcodecs.IMREAD_COLOR);

            // Load stored face image as OpenCV Mat
            FaceImage storedFaceImage = faceImageRepository.findByLogin(login);
            if (storedFaceImage != null) {
                byte[] storedImageBytes = storedFaceImage.getImage();
                Mat storedFaceImageMat = Imgcodecs.imdecode(new MatOfByte(storedImageBytes), Imgcodecs.IMREAD_COLOR);

                // Perform image comparison using OpenCV
                Mat diff = new Mat();
                Core.absdiff(capturedImage, storedFaceImageMat, diff);
                Mat gray = new Mat();
                Imgproc.cvtColor(diff, gray, Imgproc.COLOR_BGR2GRAY);

                Scalar scalar = Core.sumElems(gray);
                double diffScore = scalar.val[0] / (capturedImage.rows() * capturedImage.cols());

                // Threshold for considering images as same
                double threshold = 10.0;
                if (diffScore < threshold) {
                    // Images are similar, proceed with authentication
                    return "redirect:admin/dashboard";
                } else {
                    // Images are not similar, handle accordingly (e.g., show error message)
                    // You can customize this part based on your application's logic
                    return "redirect:/login?error=imageMismatch";
                }
            }
        }

        // Handle case where no stored face image is found or other errors
        return "redirect:/login?error=unknownError";
    }
}


