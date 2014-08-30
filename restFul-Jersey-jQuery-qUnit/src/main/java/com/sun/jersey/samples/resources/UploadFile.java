package com.sun.jersey.samples.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataParam;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/nov/2010
 */
@Path("/uploadfile")
public class UploadFile {

	private static int MAX_FILE_SIZE = 3*(1024)*(1024);

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String post(@FormDataParam("name") String name, @FormDataParam("myfile") FormDataBodyPart input ) throws Exception {
		//	public String post(@FormDataParam("name") String name, @FormDataParam("myfile") List<FormDataBodyPart> input ) {

		System.out.println("name is " + name);
		String filename = input.getContentDisposition().getFileName();
		long filesize = input.getContentDisposition().getSize();
		System.out.println("file name is " + filename);
		System.out.println("file size is " + filesize);

		File output = salvaFile(input.getValueAs(InputStream.class), "out_" + new Date().getTime() + "_" + input.getContentDisposition().getFileName(), "target");

		return "uploaded file " + filename + "<br/>" +
		"to " + output.getCanonicalPath() + "<br/>" +
		"size: " + output.length();

	}

	public File salvaFile(InputStream inputStream, String nomeFile, String path) throws Exception {
		File f = new File(path + "/" + nomeFile);
		OutputStream out = new FileOutputStream(f);
		try {
			byte buf[]=new byte[1024];
			int len = 0;
			int fileSize = 0;
			while((len=inputStream.read(buf))>0) {
				out.write(buf,0,len);
				fileSize += len;
				System.out.println("file byte read: " + fileSize);
				if (fileSize>MAX_FILE_SIZE) {
					throw new Exception("file too large!");
				}
			}
			return f;
		} finally {
			out.close();
			inputStream.close();
		}

	}
}

