package fastdfsTest;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.taotao.utils.FastDFSClient;



public class FastdfsClientTest {

	
	@Test
	public void uploadFile(){
//		public String conf_filename=;
//		public String local_filename=;
		try {
			ClientGlobal.init("G:/eclipse-mars1-workspace/taotao-manager-web/src/main/resources/resource/client.conf");
			TrackerClient trackerClient=new TrackerClient();
			TrackerServer trackerServer=trackerClient.getConnection();
			StorageServer storageServer=null;
			StorageClient storageClient=new StorageClient(trackerServer, storageServer);
			String[] str=storageClient.upload_file("G:/eclipse-mars1-workspace/taotao-manager-web/src/main/webapp/image/11189226_131514058457_2.jpg", "jpg", null);
			for(String s:str){
				System.out.println(s);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@Test
	public void uploadFile2() throws Exception{
		FastDFSClient fastDFSClient=new FastDFSClient("G:/eclipse-mars1-workspace/taotao-manager-web/src/main/resources/resource/client.conf");
		String str=fastDFSClient.uploadFile("G:/eclipse-mars1-workspace/taotao-manager-web/src/main/webapp/image/1487162022369.jpg");
		System.out.println(str);
	}

}
