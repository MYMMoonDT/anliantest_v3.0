package cn.edu.tongji.anliantest.document;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.context.WebApplicationContext;

import cn.edu.tongji.anliantest.dao.CYFATableDao;
import cn.edu.tongji.anliantest.model.experiment.CYFATable;
import cn.edu.tongji.anliantest.util.ApplicationContextUtil;

import com.fr.base.FRContext;
import com.fr.base.ModuleContext;
import com.fr.dav.LocalEnv;
import com.fr.io.TemplateWorkBookIO;
import com.fr.io.exporter.WordExporter;
import com.fr.main.TemplateWorkBook;
import com.fr.main.workbook.ResultWorkBook;
import com.fr.report.module.EngineModule;
import com.fr.stable.WriteActor;

public class CYFADocument {  
    public static String generate(Long cyfaTableId) {
    	WebApplicationContext context = ApplicationContextUtil.getContext();
    	CYFATableDao cyfaDao = context.getBean("CYFATableDaoImpl", CYFATableDao.class);
    	CYFATable table = cyfaDao.getCYFATableById(cyfaTableId);
    	String rootPath = context.getServletContext().getRealPath("/");
    	String filePath = "report/" + table.getProject().getNumber();
    	String fileName = table.getProject().getNumber() + '-' + table.getProject().getName() + '-' + "采样方案（有毒物质、粉尘）.doc";
    	return generate(rootPath, filePath, fileName, cyfaTableId);
    }
    
    private static String generate(String rootPath, String filePath, String fileName, Long cyfaTableId) {
    	try {    
            // 首先需要定义执行所在的环境，这样才能正确读取数据库信息    
            String envPath = rootPath+"/WEB-INF";    
            FRContext.setCurrentEnv(new LocalEnv(envPath));    
            ModuleContext.startModule(EngineModule.class.getName());    
            // 读取模板    
            TemplateWorkBook workbook = TemplateWorkBookIO.readTemplateWorkBook(FRContext.getCurrentEnv(),"cyfa.cpt");    
            /*  
             * 生成参数map，注入参数与对应的值，用于执行报表  
             * 获得的参数put进map中，paraMap.put(paraname,paravalue)  
             */    
            java.util.Map<String, Object> paraMap = new java.util.HashMap<String, Object>();    
            paraMap.put("cyfa_table_id", cyfaTableId);    
            // 使用paraMap执行生成结果    
            ResultWorkBook result = workbook.execute(paraMap,new WriteActor());    
            // 使用结果如导出至doc 
            File file = new File(rootPath+'/'+filePath);
            if (!file.exists())
            	file.mkdirs();
            String realPath = rootPath+'/'+filePath + '/' + fileName; 
            FileOutputStream outputStream = new FileOutputStream(realPath);    
            WordExporter excelExporter = new WordExporter();    
            excelExporter.export(outputStream, result);
            System.out.println("[Report]Create: "+realPath);
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