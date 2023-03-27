package com.sparta.finalproject.infra.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sparta.finalproject.domain.gallery.entity.Image;
import com.sparta.finalproject.domain.gallery.entity.ImagePost;
import com.sparta.finalproject.domain.gallery.repository.ImageRepository;
import com.sparta.finalproject.global.response.CustomStatusCode;
import com.sparta.finalproject.global.response.exceptionType.S3Exception;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marvin.image.MarvinImage;
import org.marvinproject.image.transform.scale.Scale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

@Component
@Service
@Slf4j
@RequiredArgsConstructor
public class S3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    private final AmazonS3 S3Client;
    private final ImageRepository imageRepository;

    //S3UploadService
    public List<String> uploadAsList(List<MultipartFile> multipartFile, String dirName, ImagePost imagePost) {
        List<String> imageList = new ArrayList<>(); // 리사이징된 이미지를 저장할 공간

        multipartFile.forEach(image -> {
            if(Objects.requireNonNull(image.getContentType()).contains("image")) {  //이미지가 있다면 실행하고 없다면 패스

                String fileName = dirName + "/" + UUID.randomUUID();//중복되지않게 이름을  randomUUID()를 사용해서 생성함
                String fileFormat = image.getContentType().substring(image.getContentType().lastIndexOf("/") + 1); //파일 확장자명 추출

                MultipartFile resizedImage = resizer(fileName, fileFormat, image, 1024); //오늘의 핵심 메서드

//========아래부터는 리사이징 된 후 이미지를 S3에다가 업로드하는 방법이다.=========
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentLength(resizedImage.getSize()); //사이즈를 전달한다.
                objectMetadata.setContentType(resizedImage.getContentType()); //이미지 타입을 전달한다.

                try (InputStream inputStream = resizedImage.getInputStream()) {
                    S3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead));
                } catch (IOException e) {
                    throw new S3Exception(CustomStatusCode.IMAGE_UPLOAD_FAIL);
                }
                String imageUrl = S3Client.getUrl(bucketName, fileName).toString();
                imageList.add(imageUrl);
                imageRepository.save(Image.of(imagePost, imageUrl));
            }
        });
        return imageList;
    }

    @Transactional
    public MultipartFile resizer(String fileName, String fileFormat, MultipartFile originalImage, int width) {

        try {
            BufferedImage image = ImageIO.read(originalImage.getInputStream());// MultipartFile -> BufferedImage Convert

            int originWidth = image.getWidth();
            int originHeight = image.getHeight();

            // origin 이미지가 400보다 작으면 패스
            if(originWidth < width)
                return originalImage;

            MarvinImage imageMarvin = new MarvinImage(image);

            Scale scale = new Scale();
            scale.load();
            scale.setAttribute("newWidth", width);
            scale.setAttribute("newHeight", width * originHeight / originWidth);//비율유지를 위해 높이 유지
            scale.process(imageMarvin.clone(), imageMarvin, null, null, false);

            BufferedImage imageNoAlpha = imageMarvin.getBufferedImageNoAlpha();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(imageNoAlpha, fileFormat, baos);
            baos.flush();

            return new CustomMultipartFile(fileName,fileFormat,originalImage.getContentType(), baos.toByteArray());

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일을 줄이는데 실패했습니다.");
        }
    }

    public void upload(List<MultipartFile> multipartFilelist, String dirName, ImagePost imagePost) throws IOException {

        for (MultipartFile multipartFile : multipartFilelist){
            if (multipartFile != null){
                File uploadFile = convert(multipartFile).orElseThrow(
                        () -> new S3Exception(CustomStatusCode.IMAGE_POST_NOT_FOUND));
                Image image = Image.of(imagePost, upload(uploadFile, dirName));
                imageRepository.save(image);
            }
        }
    }

    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        try {
            File uploadFile = convert(multipartFile).orElseThrow(() -> new S3Exception(CustomStatusCode.IMAGE_POST_NOT_FOUND));
            return upload(uploadFile, dirName);
        } catch (Exception e){
            throw new S3Exception(CustomStatusCode.IMAGE_UPLOAD_FAIL);
        }
    }


    private String upload(File uploadFile, String dirName) {
        String fileName = dirName + "/" + UUID.randomUUID(); // S3에 저장된 파일 이름
        String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }

    private String putS3(File uploadFile, String fileName) {
        S3Client.putObject(new PutObjectRequest(bucketName, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return S3Client.getUrl(bucketName, fileName).toString();
    }


    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            return;
        }
        throw new S3Exception(CustomStatusCode.FILE_DELETE_FAIL);
    }

    private Optional<File> convert(MultipartFile multipartFile) throws IOException {
        File convertFile = new File(multipartFile.getOriginalFilename());
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(multipartFile.getBytes());
            } catch (Exception e){
                throw new S3Exception(CustomStatusCode.FilE_CONVERT_FAIL);
            }
        }
        return Optional.of(convertFile);
    }

    public String getThumbnailPath(String path) {
        return S3Client.getUrl(bucketName, path).toString();
    }

    public void deleteFile(String fileName){
        DeleteObjectRequest request = new DeleteObjectRequest(bucketName, fileName);
        S3Client.deleteObject(request);
    }

}
