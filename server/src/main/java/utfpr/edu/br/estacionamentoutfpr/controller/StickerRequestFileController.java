package utfpr.edu.br.estacionamentoutfpr.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utfpr.edu.br.estacionamentoutfpr.minio.payload.FileResponse;
import utfpr.edu.br.estacionamentoutfpr.minio.service.MinioService;
import utfpr.edu.br.estacionamentoutfpr.minio.util.FileTypeUtils;
import utfpr.edu.br.estacionamentoutfpr.model.StickerRequest;
import utfpr.edu.br.estacionamentoutfpr.model.StickerRequestFile;
import utfpr.edu.br.estacionamentoutfpr.service.CrudService;
import utfpr.edu.br.estacionamentoutfpr.service.StickerRequestFileService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

@RestController
@RequestMapping("stickerRequestFile")
public class StickerRequestFileController extends CrudController<StickerRequestFile, UUID> {

    private final StickerRequestFileService stickerRequestFileService;

    private final MinioService minioService;

    public StickerRequestFileController(StickerRequestFileService stickerRequestFileService, MinioService minioService) {
        this.stickerRequestFileService = stickerRequestFileService;
        this.minioService = minioService;
    }

    @Override
    protected CrudService<StickerRequestFile, UUID> getService() {
        return this.stickerRequestFileService;
    }

    @PostMapping(value = "upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public StickerRequestFile saveFilename(@RequestPart("stickerRequest") @Valid StickerRequest stickerRequest,
                                       @RequestPart("file") @Valid MultipartFile file) {
        String fileType = FileTypeUtils.getFileType(file);
        StickerRequestFile entity = new StickerRequestFile();
        if (fileType != null) {
            FileResponse fileResponse = minioService.putObject(file, "commons",
                    fileType);
            entity.setFileName(fileResponse.getFilename());
            entity.setContentType(fileResponse.getContentType());
            entity.setStickerRequest(stickerRequest);
        }
        return stickerRequestFileService.save(entity);
    }

    @GetMapping(value = "download/{id}")
    public void downloadFile(@PathVariable("id") UUID id, HttpServletResponse response) {
        InputStream in = null;
        try {
            StickerRequestFile stickerRequestFile = stickerRequestFileService.findOne(id);
            in = minioService.downloadObject("commons", stickerRequestFile.getFileName());
            response.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(stickerRequestFile.getFileName(), "UTF-8"));
            response.setCharacterEncoding("UTF-8");
            // Remove bytes from InputStream Copied to the OutputStream .
            IOUtils.copy(in, response.getOutputStream());
        } catch (UnsupportedEncodingException e) {

        } catch (IOException e) {

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
