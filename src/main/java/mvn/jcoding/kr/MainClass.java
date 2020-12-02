package mvn.jcoding.kr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class MainClass {

	public static void main(String[] args) {
		File file = new File("rootFiles.txt");
		System.out.println(file.getAbsolutePath());
		if(file.exists()) {
			System.out.println(file.length());
			
			BufferedReader inFile = null;
			try {
				List<FileInfo> list = new ArrayList<FileInfo>();
				inFile = new BufferedReader(new FileReader(file));
				String sLine = null; 
				// int i = 0;
				while( (sLine = inFile.readLine()) != null ) {
					String [] strArr = sLine.split(" ");
					if(strArr.length == 10) {
						FileInfo fileInfo = new FileInfo();
						for(int j =0; j<strArr.length; j++ ) {
							String str = strArr[j];
							if(j == 4) {
								fileInfo.setSize(str);
							}else if(j == 5) {
								fileInfo.setMonth(str);
							}else if(j == 7) {
								fileInfo.setDay(str);
							}else if(j == 8) {
								fileInfo.setTime(str);
							}else if(j == 9) {
								fileInfo.setName(str);
								String []info = fileInfo.getName().split("/");
								
								if(info.length > 1) {
									fileInfo.setUserId(info[1]);
								}
							}
						}
						list.add(fileInfo);
					}else if(strArr.length == 9){
						FileInfo fileInfo = new FileInfo();
						for(int j =0; j<strArr.length; j++ ) {
							String str = strArr[j];
							if(j == 4) {
								fileInfo.setSize(str);
							}else if(j == 5) {
								fileInfo.setMonth(str);
							}else if(j == 6) {
								fileInfo.setDay(str);
							}else if(j == 7) {
								fileInfo.setTime(str);
							}else if(j == 8) {
								fileInfo.setName(str);
								String []info = fileInfo.getName().split("/");
								
								if(info.length > 1) {
									fileInfo.setUserId(info[1]);
								}
							}
						}
						list.add(fileInfo);
					}else if(strArr.length > 10){
						FileInfo fileInfo = new FileInfo();
						for(int j =0; j<strArr.length; j++ ) {
							String str = strArr[j];
							
							if(j == 4) {
								fileInfo.setSize(str);
							}else if(j == 5) {
								fileInfo.setMonth(str);
							}else if(j == 7) {
								fileInfo.setDay(str);
							}else if(j == 8) {
								fileInfo.setTime(str);
							}else if(j >= 9) {
								fileInfo.setName(fileInfo.getName()+str);
								String []info = fileInfo.getName().split("/");
								if(info.length > 1) {
									fileInfo.setUserId(info[1]);
								}
							}
							
						}
						list.add(fileInfo);
					}
					//i++;
					//if(i > 10)break;
				}
				makeExcel(list);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					inFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private static void makeExcel(List<FileInfo> input) {
		// 워크북 생성
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 워크시트 생성
        HSSFSheet sheet = workbook.createSheet();
        // 행 생성
        HSSFRow row = sheet.createRow(0);
        // 쎌 생성
        HSSFCell cell;
        
        // 헤더 정보 구성
        cell = row.createCell(0);
        cell.setCellValue("번호");
        
        cell = row.createCell(1);
        cell.setCellValue("유저아이디");
        
        cell = row.createCell(2);
        cell.setCellValue("파일명");
        
        cell = row.createCell(3);
        cell.setCellValue("날짜");

        cell = row.createCell(4);
        cell.setCellValue("시간");

        cell = row.createCell(5);
        cell.setCellValue("파일크기");
        
        for(int i=0; i<input.size(); i++) {
        	FileInfo info = input.get(i);
        	row = sheet.createRow(i+1);
        	
        	cell = row.createCell(0);
        	cell.setCellValue(i+1);
        	
        	cell = row.createCell(1);
        	cell.setCellValue(info.getUserId());
        	
        	cell = row.createCell(2);
        	cell.setCellValue(info.getName());
        	
        	cell = row.createCell(3);
        	cell.setCellValue(info.getMonth() +info.getDay());
        	
        	cell = row.createCell(4);
        	cell.setCellValue(info.getTime());
        	
        	cell = row.createCell(5);
        	cell.setCellValue(info.getSize());
        }
        
        File file = new File("list.xls");
        FileOutputStream fos = null;
        
        try {
            fos = new FileOutputStream(file);
            workbook.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(workbook!=null) workbook.close();
                if(fos!=null) fos.close();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
