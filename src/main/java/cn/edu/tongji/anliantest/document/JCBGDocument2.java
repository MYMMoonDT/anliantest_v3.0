package cn.edu.tongji.anliantest.document;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.context.WebApplicationContext;

import cn.edu.tongji.anliantest.dao.JCBGTableDao;
import cn.edu.tongji.anliantest.model.experiment.JCBGTable;
import cn.edu.tongji.anliantest.util.ApplicationContextUtil;

import com.fr.base.FRContext;
import com.fr.base.ModuleContext;
import com.fr.dav.LocalEnv;
import com.fr.io.TemplateWorkBookIO;
import com.fr.io.exporter.PDFExporter;
import com.fr.io.exporter.WordExporter;
import com.fr.io.exporter.WordTableExporter;
import com.fr.main.TemplateWorkBook;
import com.fr.main.workbook.ResultWorkBook;
import com.fr.report.module.EngineModule;
import com.fr.stable.WriteActor;

public class JCBGDocument2 {  
    public static String generate(Long tableId) {
    	WebApplicationContext context = ApplicationContextUtil.getContext();
    	JCBGTableDao dao = context.getBean("JCBGTableDaoImpl", JCBGTableDao.class);
    	JCBGTable table = dao.getJCBGTableById(tableId);
    	String rootPath = context.getServletContext().getRealPath("/");
    	String filePath = "report/" + table.getProject().getNumber();
    	String fileName = table.getProject().getNumber() + '-' + table.getProject().getName() + '-' + "检测报告";
    	return generate(rootPath, filePath, fileName, tableId);
    }
    
    private static String generate(String rootPath, String filePath, String fileName, Long tableId) {
    	try {    
            // 首先需要定义执行所在的环境，这样才能正确读取数据库信息    
            String envPath = rootPath+"/WEB-INF";    
            FRContext.setCurrentEnv(new LocalEnv(envPath));    
            ModuleContext.startModule(EngineModule.class.getName());    
            // 读取模板    
            TemplateWorkBook workbook = TemplateWorkBookIO.readTemplateWorkBook(FRContext.getCurrentEnv(),"jcbg.cpt");    
            /*  
             * 生成参数map，注入参数与对应的值，用于执行报表  
             * 获得的参数put进map中，paraMap.put(paraname,paravalue)  
             */    
            java.util.Map<String, Object> paraMap = new java.util.HashMap<String, Object>();    
            paraMap.put("table_id", tableId);    
            // 使用paraMap执行生成结果    
            ResultWorkBook result = workbook.execute(paraMap,new WriteActor());    
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
    
//	public static void main(String[] args) {    
//        try {    
//            // 首先需要定义执行所在的环境，这样才能正确读取数据库信息    
//            String envPath = "/Users/sausage/Projects/j2ee/anliantest_v3.0/src/main/webapp/WEB-INF";    
//            FRContext.setCurrentEnv(new LocalEnv(envPath));    
//            ModuleContext.startModule(EngineModule.class.getName());    
//            // 读取模板    
//            TemplateWorkBook workbook = TemplateWorkBookIO.readTemplateWorkBook(FRContext.getCurrentEnv(),"cyfa.cpt");    
//            /*  
//             * 生成参数map，注入参数与对应的值，用于执行报表 该模板中只有一个参数地区，给其赋值华北  
//             * 若参数在发送请求时传过来，可以通过req.getParameter(name)获得  
//             * 获得的参数put进map中，paraMap.put(paraname,paravalue)  
//             */    
//            java.util.Map paraMap = new java.util.HashMap();    
//            paraMap.put("cyfa_table_id", 1);    
//            // 使用paraMap执行生成结果    
//            ResultWorkBook result = workbook.execute(paraMap,new WriteActor());    
//            // 使用结果如导出至excel    
//            FileOutputStream outputStream = new FileOutputStream(new File(    
//                    "/Users/sausage/Projects/j2ee/anliantest_v3.0/src/main/webapp/cyfa.doc"));    
//            WordExporter excelExporter = new WordExporter();    
//            excelExporter.export(outputStream, result);    
//        } catch (Exception e) {    
//            e.printStackTrace();    
//        }    
//    }    
}  