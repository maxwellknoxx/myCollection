package com.maxwell.myCollection.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.maxwell.myCollection.service.StorageService;
import com.maxwell.myCollection.utils.ResponseUtils;

@RestController
@CrossOrigin("*")
public class ImageUploadController {

	//private final static Logger LOGGER = Logger.getLogger(CategoryController.class.getName());

	ResponseUtils responseUtils = new ResponseUtils();

	private final StorageService storageService;

	@Autowired
	public ImageUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping(path = "/api/image/images")
	public String listUploadedFiles() throws IOException {

		List<String> images = storageService.loadAll()
				.map(path -> MvcUriComponentsBuilder
						.fromMethodName(ImageUploadController.class, "serveFile", path.getFileName().toString()).build()
						.toString())
				.collect(Collectors.toList());

		images.forEach(img -> System.out.println(img));

		return "uploadForm";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping(path =  "/api/image/images")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		storageService.store(file);
		 System.out.println(file.getOriginalFilename() + "!");

		return "redirect:/";
	}

}
