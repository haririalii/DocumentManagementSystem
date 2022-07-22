package com.docuservice.client;

import com.docuservice.client.exception.MinioServerException;
import io.minio.*;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
@RequiredArgsConstructor
@Slf4j
public class MinioDao {

    private final MinioClient minioClient;

    public ObjectWriteResponse put(String bucket, String object, InputStream inputStream, String contentType) {
        return this.put(bucket, object, inputStream, null, contentType);
    }

    public ObjectWriteResponse put(String bucket, String object, InputStream inputStream,
                                   Long objectSize, String contentType) {
        try {
            long partSize = -1L;
            if (objectSize == null) {
                objectSize = -1L;
                partSize = 5368709100L;
            }

            PutObjectArgs args = PutObjectArgs.builder().bucket(bucket).object(object)
                    .stream(inputStream, objectSize, partSize)
                    .contentType(contentType)
                    .build();
            return minioClient.putObject(args);
        } catch (NoSuchAlgorithmException | IOException | InvalidKeyException |
                ErrorResponseException | InsufficientDataException | InternalException |
                InvalidResponseException | ServerException | XmlParserException e) {
            throw new MinioServerException(e);
        }
    }

    public void createBucketIfNotExist(String name) {
        if (!isBucketExist(name)) {
            log.debug("Bucket {} does not exist, so we try to create it!", name);
            this.makeBucket(name);
        } else
            log.trace("Bucket {} already exist!", name);

    }

    public void makeBucket(String name) {
        try {
            MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder()
                    .bucket(name)
                    .build();
            minioClient.makeBucket(makeBucketArgs);
        } catch (ErrorResponseException | InsufficientDataException | InternalException |
                InvalidKeyException | InvalidResponseException | IOException |
                NoSuchAlgorithmException | ServerException | XmlParserException e) {
            throw new MinioServerException(e);
        }
    }

    public boolean isBucketExist(String name) {
        try {
            BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder()
                    .bucket(name)
                    .build();
            return minioClient.bucketExists(bucketExistsArgs);
        } catch (ErrorResponseException | InsufficientDataException | InternalException |
                InvalidKeyException | InvalidResponseException | IOException |
                NoSuchAlgorithmException | ServerException | XmlParserException e) {
            throw new MinioServerException(e);
        }
    }

}
