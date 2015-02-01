package cn.edu.tongji.anliantest.document;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.web.context.WebApplicationContext;

import cn.edu.tongji.anliantest.dao.SYSYJLTableDao;
import cn.edu.tongji.anliantest.model.experiment.SYSYJLDay;
import cn.edu.tongji.anliantest.model.experiment.SYSYJLTable;
import cn.edu.tongji.anliantest.util.ApplicationContextUtil;

import com.fr.base.FRContext;
import com.fr.base.ModuleContext;
import com.fr.dav.LocalEnv;
import com.fr.io.TemplateWorkBookIO;
import com.fr.io.exporter.PDFExporter;
import com.fr.io.exporter.WordExporter;
import com.fr.main.TemplateWorkBook;
import com.fr.main.workbook.PageWorkBook;
import com.fr.report.module.EngineModule;
import com.fr.report.report.PageReport;
import com.fr.stable.PageActor;

public class SYSYJLDocument {  
    public static String generate(Long cyfaTableId) {
    	WebApplicationContext context = ApplicationContextUtil.getContext();
    	SYSYJLTableDao cyfaDao = context.getBean("SYSYJLTableDaoImpl", SYSYJLTableDao.class);
    	SYSYJLTable table = cyfaDao.getSYSYJLTableById(cyfaTableId);
    	String rootPath = context.getServletContext().getRealPath("/");
    	String filePath = "report/" + table.getProject().getNumber();
    	String fileName = table.getProject().getNumber() + '-' + table.getProject().getName() + '-' + "送样、收样记录表";
    	return generate(rootPath, filePath, fileName, cyfaTableId, table.getItems());
    }
    
    private static String generate(String rootPath, String filePath, String fileName, Long sysyjlTableId, List<SYSYJLDay> list) {
    	try {    
            // 首先需要定义执行所在的环境，这样才能正确读取数据库信息    
            String envPath = rootPath+"/WEB-INF";    
            FRContext.setCurrentEnv(new LocalEnv(envPath));    
            ModuleContext.startModule(EngineModule.class.getName());    
            // 读取模板    
            TemplateWorkBook workbook = TemplateWorkBookIO.readTemplateWorkBook(FRContext.getCurrentEnv(),"sysyjl.cpt");    
            /*  
             * 生成参数map，注入参数与对应的值，用于执行报表  
             * 获得的参数put进map中，paraMap.put(paraname,paravalue)  
             */    
            java.util.Map<String, Object> paraMap = new java.util.HashMap<String, Object>();    
            paraMap.put("table_id", sysyjlTableId);    
            // 使用paraMap执行生成结果    
            PageWorkBook result=null;
            for (SYSYJLDay day : list) {
            	paraMap.put("day_id", day.getId());
            	PageWorkBook tempWB = (PageWorkBook)workbook.execute(paraMap,new PageActor());      
                PageReport tempPR = tempWB.getPageReport(0);
                if (result==null)
                	result=tempWB;
                else
                	result.addReport(day.getSampleDate().toString(), tempPR); 
            }
            // 使用结果如导出至doc 
            File file = new File(rootPath+'/'+filePath);
            if (!file.exists())
            	file.mkdirs();
            String realPath = rootPath+'/'+filePath + '/' + fileName; 
            FileOutputStream outputStream = new FileOutputStream(realPath+".doc");    
            WordExporter wordExporter = new WordExporter();    
            wordExporter.export(outputStream, result);
            outputStream.close();  
            System.out.println("[Report]Create: "+realPath+".doc");
            outputStream = new FileOutputStream(realPath+".pdf");    
            PDFExporter pdfExporter = new PDFExporter();    
            pdfExporter.export(outputStream, result);
            System.out.println("[Report]Create: "+realPath+".pdf");
            outputStream.close();    
            ModuleContext.stopModules(); 
            return realPath;
        } catch (Exception e) {    
            e.printStackTrace();  
            return null;
        }    
    } 
}  