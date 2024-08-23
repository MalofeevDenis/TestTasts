package com.kps.sb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.FileHandler;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.SimpleFormatter;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class DemoApplication {
    public static String extract(String s,String lb, String rb){
        String res=s.substring(s.indexOf(lb) + lb.length(), s.indexOf(rb));
        return res;
    }
    public static String HeadersGet(HttpServletRequest req){
        Enumeration headerNames = req.getHeaderNames();
        String hjson="";
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = req.getHeader(key);
            hjson=hjson+"{"+key+":"+value+"};";

        }
        return hjson;

    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

/////////////EMULATING
    @PostMapping(value = "CreditRegistry_SPARK", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_05_50_Spark(@RequestBody String rq) {
        String soapAction=extract(rq,"soapAction=\"/","\">");
        String main_response="";
        //для DS_05_50_Spark
        if(soapAction.equals("CreditRegistry_SPARK")){
            //========================log

            try {
                Logger logger = Logger.getLogger("MyLog_05_51");
                FileHandler fh2 = new FileHandler("C:/Program Files/Apache Software Foundation/Tomcat 9.0/logs/MyLogFile05_51.log");
                logger.addHandler(fh2);
                SimpleFormatter l_formatter = new SimpleFormatter();
                fh2.setFormatter(l_formatter);
                logger.info("=========================Request body:===================================");
                logger.info(rq);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //=============================


            String INN=extract(rq,"<con1:inn xmlns:con1=\"http://connector.xws.mbtc.ru\">","</con1:inn>");
            String current_date_0=extract(rq,"<con1:dateOfReport xmlns:con1=\"http://connector.xws.mbtc.ru\">","</con1:dateOfReport>");
            String current_date=current_date_0.substring(0,current_date_0.indexOf("T"));
            main_response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                    "<soap:Body>"+
                    "<ConnectorOutput>\n" +
                    " <Response>\n" +
                    "     <CompanyStructureReport>\n" +
                    "         <ResultInfo ResultType=\"True\" ExecutionTime=\"30\" />\n" +
                    "         <Data>\n" +
                    "             <Report>\n" +
                    "                 <SparkID>6074001</SparkID>\n" +
                    "                 <ShortName>АО \"БАШКИРСКОЕ МОЛОКО\"</ShortName>\n" +
                    "                 <INN>"+INN+"</INN>\n" +
                    "                 <OGRN>1070273004713</OGRN>\n" +
                    "                 <OKPO>82047250</OKPO>\n" +
                    "                 <OKATO>80401385000</OKATO>\n" +
                    "                 <EGRPOIncluded>true</EGRPOIncluded>\n" +
                    "                 <Status IsActing=\"true\" Code=\"24\" Text=\"Действующее\" GroupId=\"1\" GroupName=\"Действующее\" Date=\""+current_date+"\" />\n" +
                    "             </Report>\n" +
                    "         </Data>\n" +
                    "     </CompanyStructureReport>\n" +
                    "     <ResultMsg>\n" +
                    "         <ResultCode>1</ResultCode>\n" +
                    "         <ResultMessage>DONE. </ResultMessage>\n" +
                    "     </ResultMsg>\n" +
                    " </Response>\n" +
                    //"</ConnectorOutput>"+
                    "</soap:Body>\n" +
                    "</soap:Envelope>";

            return ResponseEntity.ok().body(main_response);

        } else {//DS_05_50

            //==============================LOG=================

            try {
                Logger logger = Logger.getLogger("MyLog_05_50");
                FileHandler fh2 = new FileHandler("C:/Program Files/Apache Software Foundation/Tomcat 9.0/logs/MyLogFile05_50.log");
                logger.addHandler(fh2);
                SimpleFormatter l_formatter = new SimpleFormatter();
                fh2.setFormatter(l_formatter);
                logger.info("=========================Request body:===================================");
                logger.info(rq);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //===================================================

            String InquiryCode = extract(rq, "<InquiryCode>", "</InquiryCode>");
            String ProcessCode = extract(rq, "<ProcessCode>", "</ProcessCode>");
            String LayoutVersion = extract(rq, "<LayoutVersion>", "</LayoutVersion>");
            String IN_CUSTOMER_BASICSALARY_0 = extract(rq, "<IN_CUSTOMER_BASICSALARY>", "</IN_CUSTOMER_BASICSALARY>");
            String IN_CUSTOMER_BASICSALARY = IN_CUSTOMER_BASICSALARY_0.substring(0, IN_CUSTOMER_BASICSALARY_0.indexOf("."));
            String IN_APPL_REQUEST_DATE = extract(rq, "<IN_APPL_REQUEST_DATE>", "</IN_APPL_REQUEST_DATE>");//Current YYYY-MM-DD
            String IN_CUSTOMER_ID = extract(rq, "<IN_CUSTOMER_ID>", "</IN_CUSTOMER_ID>");
            Locale locale = Locale.US;
            DateFormat dateFormat = new SimpleDateFormat("E MMM d yyyy HH:mm:ss 'GMT'Z (z)", locale);
            Date date = new Date();
            String LONG_DATA = dateFormat.format(date);
            DateFormat dateFormat2 = new SimpleDateFormat("dd-M-YYYY");
            String IN_APPL_CREATE_DATE = dateFormat2.format(date);//Current DD-M-YYYY
            Date date2 = new Date();
            DateFormat dateFormat3 = new SimpleDateFormat("YYYY-MM-dd");//Current YYYY-MM-dd
            String current_date_simple = dateFormat3.format(date2);


            main_response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                    "<soap:Body>\n" +
                    " <Response>" +
                    //"<ConnectorOutput>\n" +
                    " <StrategyOneResponseT5>\n" +
                    "     <Header>\n" +
                    "         <InquiryCode>" + InquiryCode + "</InquiryCode>\n" +
                    "         <ProcessCode>" + ProcessCode + "</ProcessCode>\n" +
                    "         <OrganizationCode>CRDS</OrganizationCode>\n" +
                    "         <ProcessVersion>293</ProcessVersion>\n" +
                    "         <LayoutVersion>" + LayoutVersion + "</LayoutVersion>\n" +
                    "     </Header>\n" +
                    "     <Body>\n" +
                    "         <Application>\n" +
                    "             <Variables>\n" +
                    "                 <OUT_APPL_APPLICATIONID>" + InquiryCode + "</OUT_APPL_APPLICATIONID>\n" +
                    "                 <OUT_APPL_REQUEST_TYPE>BASE</OUT_APPL_REQUEST_TYPE>\n" +
                    "                 <OUT_APPL_DEC_RESULT>PREAPPROVE</OUT_APPL_DEC_RESULT>\n" +
                    "                 <OUT_APPL_DEC_TIMEOUT>0</OUT_APPL_DEC_TIMEOUT>\n" +
                    "                 <OUT_APPL_DEC_PD>0.06535847331527</OUT_APPL_DEC_PD>\n" +
                    "                 <OUT_APPL_DEC_SCORE>241</OUT_APPL_DEC_SCORE>\n" +
                    "                 <OUT_APPL_PTI>0.0768</OUT_APPL_PTI>\n" +
                    "             </Variables>\n" +
                    "             <Categories>\n" +
                    "                 <CUSTOMER>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_CUSTOMER_ID>" + IN_CUSTOMER_ID + "</OUT_CUSTOMER_ID>\n" +
                    "                         <OUT_CUSTOMER_PTI>0.0768</OUT_CUSTOMER_PTI>\n" +
                    "                         <OUT_CUSTOMER_DEC_RESULT>ACCEPT</OUT_CUSTOMER_DEC_RESULT>\n" +
                    "                         <OUT_CUSTOMER_DEC_PD>0.06535847331527</OUT_CUSTOMER_DEC_PD>\n" +
                    "                         <OUT_CUSTOMER_DEC_SCORE>241</OUT_CUSTOMER_DEC_SCORE>\n" +
                    "                         <OUT_CUSTOMER_DEC_CATEGORY>S</OUT_CUSTOMER_DEC_CATEGORY>\n" +
                    "                         <OUT_CUSTOMER_DEC_CATEGORY_EXT>S</OUT_CUSTOMER_DEC_CATEGORY_EXT>\n" +
                    "                         <OUT_CUSTOMER_DEC_CATEGORY_PC>S</OUT_CUSTOMER_DEC_CATEGORY_PC>\n" +
                    "                         <OUT_CUSTOMER_DEC_CREDITHISTORY_TYPE>КИ отсутствует</OUT_CUSTOMER_DEC_CREDITHISTORY_TYPE>\n" +
                    "                         <OUT_CUSTOMER_DEC_CREDITHISTORY_POS_MAXLIMIT>0</OUT_CUSTOMER_DEC_CREDITHISTORY_POS_MAXLIMIT>\n" +
                    "                         <OUT_CUSTOMER_TOTALINCOME>" + IN_CUSTOMER_BASICSALARY + "</OUT_CUSTOMER_TOTALINCOME>\n" +
                    "                     </Variables>\n" +
                    "                     <Categories>\n" +
                    "                         <OUT_CUSTOMER_DEC_VERIFICATION>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUSTOMER_DEC_VERIFICATION_PART>CM_DOC_MAIN</OUT_CUSTOMER_DEC_VERIFICATION_PART>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VERIFICATION>\n" +
                    "                         <OUT_CUSTOMER_DEC_RULES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUSTOMER_DEC_RS_CODE>AppValue</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                    "                                 <OUT_CUSTOMER_DEC_RS_TYPE>SI</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                    "                                 <OUT_CUSTOMER_DEC_RS_NAME>Значение суммарного дохода участника сделки</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                    "                                 <OUT_CUSTOMER_DEC_RS_DESCRIPTION>" + IN_CUSTOMER_BASICSALARY + ".00</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_RULES>\n" +
                    "                         <OUT_CUSTOMER_DEC_RULES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUSTOMER_DEC_RS_CODE>S008_fsr</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                    "                                 <OUT_CUSTOMER_DEC_RS_TYPE>I</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                    "                                 <OUT_CUSTOMER_DEC_RS_NAME>По осн. заемщику число запросов в БКИ за последние 3 месяца &lt;25. Если не было проверки ССБ по заявке. Кроме заемщиков Банка, сотрудников ФК и Банка</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                    "                                 <OUT_CUSTOMER_DEC_RS_DESCRIPTION>По осн. заемщику число запросов в БКИ за последние 3 месяца &lt;25. Если не было проверки ССБ по заявке. Кроме заемщиков Банка, сотрудников ФК и Банка</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_RULES>\n" +
                    "                         <OUT_CUSTOMER_DEC_RULES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUSTOMER_DEC_RS_CODE>CM005</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                    "                                 <OUT_CUSTOMER_DEC_RS_TYPE>I</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                    "                                 <OUT_CUSTOMER_DEC_RS_NAME>Проверка основного документа (паспорт). Результаты проверки сброшены</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                    "                                 <OUT_CUSTOMER_DEC_RS_DESCRIPTION>Проверка основного документа (паспорт). Результаты проверки сброшены</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_RULES>\n" +
                    "                         <OUT_CUSTOMER_DEC_RULES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUSTOMER_DEC_RS_CODE>CM006</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                    "                                 <OUT_CUSTOMER_DEC_RS_TYPE>I</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                    "                                 <OUT_CUSTOMER_DEC_RS_NAME>Проверка документов, подтверждающий доход. Результаты проверки сброшены</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                    "                                 <OUT_CUSTOMER_DEC_RS_DESCRIPTION>Проверка документов, подтверждающий доход. Результаты проверки сброшены</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_RULES>\n" +
                    "                         <OUT_CUSTOMER_DEC_RULES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUSTOMER_DEC_RS_CODE>CM011</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                    "                                 <OUT_CUSTOMER_DEC_RS_TYPE>I</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                    "                                 <OUT_CUSTOMER_DEC_RS_NAME> Проверка документов: документ, косвенно подтверждающий доход. В случае предоставления.</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                    "                                 <OUT_CUSTOMER_DEC_RS_DESCRIPTION> Проверка документов: документ, косвенно подтверждающий доход. В случае предоставления.</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_RULES>\n" +
                    "                         <OUT_CUSTOMER_DEC_SCORES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUSTOMER_DEC_SCORES_SCORE>-5.33858937638346</OUT_CUSTOMER_DEC_SCORES_SCORE>\n" +
                    "                                 <OUT_CUSTOMER_DEC_SCORES_SCORE_SCALED>241</OUT_CUSTOMER_DEC_SCORES_SCORE_SCALED>\n" +
                    "                                 <OUT_CUSTOMER_DEC_SCORES_PD>0.06535847331527</OUT_CUSTOMER_DEC_SCORES_PD>\n" +
                    "                                 <OUT_CUSTOMER_DEC_SCORES_CUTOFF>0.421</OUT_CUSTOMER_DEC_SCORES_CUTOFF>\n" +
                    "                                 <OUT_CUSTOMER_DEC_SCORES_PRODUCT_ID>110</OUT_CUSTOMER_DEC_SCORES_PRODUCT_ID>\n" +
                    "                             </Variables>\n" +
                    "                             <Categories />\n" +
                    "                         </OUT_CUSTOMER_DEC_SCORES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>1</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_Max_Limit_CFC00_CFD01_CFP02</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>480000</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>2</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_INQUIRY12MONTH_TO_INQUIRY6MONTH_RATIO</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>1.66666666666666</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>3</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_Max_delay_PFP01_max_cfa00_cfm02_CFN00_CFU02</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>-1000</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>4</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_CNT_cfa01_CFO02_CFT07</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>5</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_client_type</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>3</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>6</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_segment</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>4</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>7</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_OUTS_to_LIMIT_wavg_cfa01_CFT12</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>1000</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>8</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_INQUIRY3MONTH_TO_INQUIRY1MONTH_RATIO</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>1</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>9</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_Months_since_first_opened_CFM02_CFN00</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>126</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>10</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_CNTp_PFD03_PFP01_sum_CFM00_CFN02</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>11</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_CNTp_PFD01_PFP03_sum_CFM01_CFN00</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>12</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_Months_since_last_opened_cfa00_cfd00_CFT09_CFU01</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>1000</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>1</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_INQUIRY12MONTH_TO_INQUIRY1MONTH_RATIO</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>15</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>2</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_CNTp_PFD01_PFP00_sum_CFM00_CFN00_TO_CNTp_PFD00_PFP00_sum_CFM00</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>1</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>3</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_INQUIRY1WEEK_TO_INQUIRY1WEEK_avg_RATIO</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>4</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_CNTp_PFD02_PFP03_sum_CFM00_CFN00_TO_CNTp_PFD00_PFP03_sum_CFM00</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>10000</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>5</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_Months_since_first_opened_CFM01_CFN02</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>126</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>6</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_INQUIRY3MONTH_TO_INQUIRY1MONTH_RATIO</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>1</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>7</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_OUTS_to_LIMIT_wavg_cfa01_CFT08</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>0.4006</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>8</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_CNTp_PFD00_PFP00_sum_CFM00_CFN02</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>148</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>9</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_INQUIRY6MONTH_TO_INQUIRY9MONTH_RATIO</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>1</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>10</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_CNT_CFC00_CFD01_CFP01</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>8</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>11</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_Months_since_last_opened_CFM00_CFN02</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>36</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>12</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_INQUIRY3MONTH_TO_INQUIRY3MONTH_avg_RATIO</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>0.0177162527751402</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>13</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_OUTS_to_LIMIT_wavg_cfa00_CFT04</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>14</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_Max_Limit_cfa00_CFO01_CFT05</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>15</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_OUTS_to_LIMIT_wavg_cfa00_CFT03</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>0.04497224820704709</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>16</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_OUTS_to_LIMIT_wavg_cfa00_CFT09</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>17</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_Max_Limit_cfa01_cfc02_CFD01_cft03</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>180000</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>18</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_Max_Limit_CFO01_CFU02</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>19</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_CNT_cfa00_CFO03_CFU02</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>20</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_INQUIRY1WEEK_TO_INQUIRY1MONTH_RATIO</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>21</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_Max_Limit_cfa01_CFO02_CFU02</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>22</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_Months_since_first_opened_cfa00_CFT11_CFU02</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>-10000</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>23</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_Months_since_last_opened_cfa02_cfd00_CFT08_CFU00</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>43</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>24</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_Months_since_last_opened_CFM01_CFN00</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>36</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>25</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_Months_since_last_opened_CFM03_CFN01</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>46</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>26</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_Max_Limit_CFC03_CFD01_CFP00</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>27</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_Months_since_last_opened_cfa01_cfd01_CFT00_CFU02</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>-10000</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUS_DEC_VAR_NAME>28</OUT_CUS_DEC_VAR_NAME>\n" +
                    "                                 <OUT_CUS_DEC_VAR_DESCR>v_OUTS_to_LIMIT_wavg_cfa00_CFT08</OUT_CUS_DEC_VAR_DESCR>\n" +
                    "                                 <OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                    "                                 <OUT_CUS_DEC_VAR_VALUE>0.0285</OUT_CUS_DEC_VAR_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_CUSTOMER_DEC_VARIABLES>\n" +
                    "                         <OUT_CUSTOMER_SERVICES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUSTOMER_SERVICES_NAME>BIAS_FSSP</OUT_CUSTOMER_SERVICES_NAME>\n" +
                    "                                 <OUT_CUSTOMER_SERVICES_STATUS>0</OUT_CUSTOMER_SERVICES_STATUS>\n" +
                    "                             </Variables>\n" +
                    "                             <Categories />\n" +
                    "                         </OUT_CUSTOMER_SERVICES>\n" +
                    "                         <OUT_CUSTOMER_SERVICES>\n" +
                    "                             <Variables>\n" +
                    "                                 <OUT_CUSTOMER_SERVICES_NAME>BIAS_NEGATIV</OUT_CUSTOMER_SERVICES_NAME>\n" +
                    "                                 <OUT_CUSTOMER_SERVICES_STATUS>0</OUT_CUSTOMER_SERVICES_STATUS>\n" +
                    "                             </Variables>\n" +
                    "                             <Categories />\n" +
                    "                         </OUT_CUSTOMER_SERVICES>\n" +
                    "                         <OUT_CUSTOMER_PDN>\n" +
                    "                             <Variables />\n" +
                    "                             <Categories>\n" +
                    "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                     <Variables>\n" +
                    "                                         <COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                    "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                         <COMMON_PROPERTY_VALUE>" + IN_APPL_REQUEST_DATE + "</COMMON_PROPERTY_VALUE>\n" +
                    "                                     </Variables>\n" +
                    "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                     <Variables>\n" +
                    "                                         <COMMON_PROPERTY_NAME>SUBJECTID</COMMON_PROPERTY_NAME>\n" +
                    "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                         <COMMON_PROPERTY_VALUE>" + IN_CUSTOMER_ID + "</COMMON_PROPERTY_VALUE>\n" +
                    "                                     </Variables>\n" +
                    "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                     <Variables>\n" +
                    "                                         <COMMON_PROPERTY_NAME>cusType</COMMON_PROPERTY_NAME>\n" +
                    "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                         <COMMON_PROPERTY_VALUE>Applicant</COMMON_PROPERTY_VALUE>\n" +
                    "                                     </Variables>\n" +
                    "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                     <Variables>\n" +
                    "                                         <COMMON_PROPERTY_NAME>pdnBkiPmt</COMMON_PROPERTY_NAME>\n" +
                    "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                         <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                                     </Variables>\n" +
                    "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                     <Variables>\n" +
                    "                                         <COMMON_PROPERTY_NAME>pdnBkiPmtSum_ApplIsOwn1</COMMON_PROPERTY_NAME>\n" +
                    "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                         <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                                     </Variables>\n" +
                    "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                     <Variables>\n" +
                    "                                         <COMMON_PROPERTY_NAME>pdnBkiPmtSum_ApplIsOwn0</COMMON_PROPERTY_NAME>\n" +
                    "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                         <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                                     </Variables>\n" +
                    "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                     <Variables>\n" +
                    "                                         <COMMON_PROPERTY_NAME>pdnBkiPmtSum_ApplRel5</COMMON_PROPERTY_NAME>\n" +
                    "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                         <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                                     </Variables>\n" +
                    "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                     <Variables>\n" +
                    "                                         <COMMON_PROPERTY_NAME>pdnAvgMonthlyIncome</COMMON_PROPERTY_NAME>\n" +
                    "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                         <COMMON_PROPERTY_VALUE>39374.75</COMMON_PROPERTY_VALUE>\n" +
                    "                                     </Variables>\n" +
                    "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                     <Variables>\n" +
                    "                                         <COMMON_PROPERTY_NAME>pdnConfirmedIncome</COMMON_PROPERTY_NAME>\n" +
                    "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                         <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                                     </Variables>\n" +
                    "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                     <Variables>\n" +
                    "                                         <COMMON_PROPERTY_NAME>pdnBkiIncome</COMMON_PROPERTY_NAME>\n" +
                    "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                         <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                                     </Variables>\n" +
                    "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                     <Variables>\n" +
                    "                                         <COMMON_PROPERTY_NAME>pdnDeclaredIncome</COMMON_PROPERTY_NAME>\n" +
                    "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                         <COMMON_PROPERTY_VALUE>39374.75</COMMON_PROPERTY_VALUE>\n" +
                    "                                     </Variables>\n" +
                    "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                     <Variables>\n" +
                    "                                         <COMMON_PROPERTY_NAME>pdnPerPersonIncome</COMMON_PROPERTY_NAME>\n" +
                    "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                         <COMMON_PROPERTY_VALUE>39374.75</COMMON_PROPERTY_VALUE>\n" +
                    "                                     </Variables>\n" +
                    "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                     <Variables>\n" +
                    "                                         <COMMON_PROPERTY_NAME>pdnRetailIncome</COMMON_PROPERTY_NAME>\n" +
                    "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                         <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                                     </Variables>\n" +
                    "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                     <Variables>\n" +
                    "                                         <COMMON_PROPERTY_NAME>pdnModelingIncome</COMMON_PROPERTY_NAME>\n" +
                    "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                         <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                                     </Variables>\n" +
                    "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                     <Variables>\n" +
                    "                                         <COMMON_PROPERTY_NAME>pdnIncomeCalcPeriod</COMMON_PROPERTY_NAME>\n" +
                    "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                         <COMMON_PROPERTY_VALUE>12</COMMON_PROPERTY_VALUE>\n" +
                    "                                     </Variables>\n" +
                    "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                    "                                 <OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                    "                                     <Variables>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_UUID>109dfa6e-cea5-11eb-a8dd-786d75af10c6-6</OUT_CUSTOMER_PDN_DEC_LOAN_UUID>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>26115248</OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>\n" +
                    "                                     </Variables>\n" +
                    "                                     <Categories>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>" + IN_APPL_REQUEST_DATE + "</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>loanStatus</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>00</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>SUBJECTID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>" + IN_CUSTOMER_ID + "</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>GROUPID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>26115248</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>UUID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>109dfa6e-cea5-11eb-a8dd-786d75af10c6-6</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>pdnInvolvedType</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                     </Categories>\n" +
                    "                                 </OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                    "                                 <OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                    "                                     <Variables>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_UUID>ebb81198-244b-11eb-a191-772fa2def8cb-4</OUT_CUSTOMER_PDN_DEC_LOAN_UUID>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>26115248</OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>\n" +
                    "                                     </Variables>\n" +
                    "                                     <Categories>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>" + IN_APPL_REQUEST_DATE + "</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>loanStatus</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>13</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>SUBJECTID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>" + IN_CUSTOMER_ID + "</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>GROUPID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>26115248</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>UUID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>ebb81198-244b-11eb-a191-772fa2def8cb-4</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>pdnInvolvedType</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                     </Categories>\n" +
                    "                                 </OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                    "                                 <OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                    "                                     <Variables>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_UUID>8dfc47fa-5fad-11ea-95cb-f9c132249232-5</OUT_CUSTOMER_PDN_DEC_LOAN_UUID>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>26115248</OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>\n" +
                    "                                     </Variables>\n" +
                    "                                     <Categories>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>" + IN_APPL_REQUEST_DATE + "</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>loanStatus</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>13</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>SUBJECTID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>" + IN_CUSTOMER_ID + "</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>GROUPID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>26115248</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>UUID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>8dfc47fa-5fad-11ea-95cb-f9c132249232-5</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>pdnInvolvedType</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                     </Categories>\n" +
                    "                                 </OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                    "                                 <OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                    "                                     <Variables>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_UUID>ad93b240-dd3f-11ea-993d-95f185b924a2-6</OUT_CUSTOMER_PDN_DEC_LOAN_UUID>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>26115248</OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>\n" +
                    "                                     </Variables>\n" +
                    "                                     <Categories>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>" + IN_APPL_REQUEST_DATE + "</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>loanStatus</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>13</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>SUBJECTID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>" + IN_CUSTOMER_ID + "</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>GROUPID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>26115248</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>UUID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>ad93b240-dd3f-11ea-993d-95f185b924a2-6</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>pdnInvolvedType</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                     </Categories>\n" +
                    "                                 </OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                    "                                 <OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                    "                                     <Variables>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_UUID>f30e86ef-dd3e-11ea-8c25-bd118258a476-6</OUT_CUSTOMER_PDN_DEC_LOAN_UUID>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>26115248</OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>\n" +
                    "                                     </Variables>\n" +
                    "                                     <Categories>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>" + IN_APPL_REQUEST_DATE + "</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>loanStatus</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>13</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>SUBJECTID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>" + IN_CUSTOMER_ID + "</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>GROUPID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>26115248</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>UUID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>f30e86ef-dd3e-11ea-8c25-bd118258a476-6</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>pdnInvolvedType</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                     </Categories>\n" +
                    "                                 </OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                    "                                 <OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                    "                                     <Variables>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_UUID>f26ed548-eacc-11ea-9e86-762de70bade2-d</OUT_CUSTOMER_PDN_DEC_LOAN_UUID>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>26115248</OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>\n" +
                    "                                     </Variables>\n" +
                    "                                     <Categories>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>" + IN_APPL_REQUEST_DATE + "</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>loanStatus</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>13</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>SUBJECTID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>" + IN_CUSTOMER_ID + "</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>GROUPID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>26115248</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>UUID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>f26ed548-eacc-11ea-9e86-762de70bade2-d</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>pdnInvolvedType</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                     </Categories>\n" +
                    "                                 </OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                    "                                 <OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                    "                                     <Variables>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_UUID>f26f4cb2-eacc-11ea-9e86-682ad6d5c89b-3</OUT_CUSTOMER_PDN_DEC_LOAN_UUID>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>26115248</OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>\n" +
                    "                                     </Variables>\n" +
                    "                                     <Categories>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>" + IN_APPL_REQUEST_DATE + "</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>loanStatus</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>13</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>SUBJECTID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>" + IN_CUSTOMER_ID + "</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>GROUPID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>26115248</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>UUID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>f26f4cb2-eacc-11ea-9e86-682ad6d5c89b-3</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>pdnInvolvedType</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                     </Categories>\n" +
                    "                                 </OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                    "                                 <OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                    "                                     <Variables>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_UUID>f0787ece-3a66-185a-be59-7feee209487a-c</OUT_CUSTOMER_PDN_DEC_LOAN_UUID>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>26115248</OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>\n" +
                    "                                     </Variables>\n" +
                    "                                     <Categories>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>" + IN_APPL_REQUEST_DATE + "</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>loanStatus</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>13</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>SUBJECTID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>" + IN_CUSTOMER_ID + "</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>GROUPID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>26115248</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>UUID</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>f0787ece-3a66-185a-be59-7feee209487a-c</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                         <OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                             <Variables>\n" +
                    "                                                 <COMMON_PROPERTY_NAME>pdnInvolvedType</COMMON_PROPERTY_NAME>\n" +
                    "                                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                                             </Variables>\n" +
                    "                                         </OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                    "                                     </Categories>\n" +
                    "                                 </OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                    "                             </Categories>\n" +
                    "                         </OUT_CUSTOMER_PDN>\n" +
                    "                     </Categories>\n" +
                    "                 </CUSTOMER>\n" +
                    "                 <OUT_APPL_DEC_OFFERS>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_OFFERS_ID>1</OUT_APPL_DEC_OFFERS_ID>\n" +
                    "                         <OUT_APPL_DEC_OFFERS_CREDITLIMIT>100000</OUT_APPL_DEC_OFFERS_CREDITLIMIT>\n" +
                    "                         <OUT_APPL_DEC_OFFERS_MINLIMIT>9999</OUT_APPL_DEC_OFFERS_MINLIMIT>\n" +
                    "                         <OUT_APPL_DEC_OFFERS_APR>0.209</OUT_APPL_DEC_OFFERS_APR>\n" +
                    "                         <OUT_APPL_DEC_OFFERS_PRODUCTID>110</OUT_APPL_DEC_OFFERS_PRODUCTID>\n" +
                    "                         <OUT_APPL_DEC_OFFERS_MAIN>1</OUT_APPL_DEC_OFFERS_MAIN>\n" +
                    "                         <OUT_APPL_DEC_OFFERS_CREDITLIMIT_BANK>100000</OUT_APPL_DEC_OFFERS_CREDITLIMIT_BANK>\n" +
                    "                         <OUT_APPL_DEC_OFFERS_CREDITLIMIT_PDN>100000</OUT_APPL_DEC_OFFERS_CREDITLIMIT_PDN>\n" +
                    "                         <OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP>0</OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP>\n" +
                    "                         <OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP_BANK>0</OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP_BANK>\n" +
                    "                         <OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP_PDN>0</OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP_PDN>\n" +
                    "                     </Variables>\n" +
                    "                     <Categories />\n" +
                    "                 </OUT_APPL_DEC_OFFERS>\n" +
                    "                 <OUT_APPL_PDN>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_PDN_CALC_DATE>" + IN_APPL_REQUEST_DATE + "</OUT_APPL_PDN_CALC_DATE>\n" +
                    "                         <OUT_APPL_PDN_CONFIRMED_INCOME>0</OUT_APPL_PDN_CONFIRMED_INCOME>\n" +
                    "                         <OUT_APPL_PDN_INCOME_PER_PERSON>39374.75</OUT_APPL_PDN_INCOME_PER_PERSON>\n" +
                    "                         <OUT_APPL_PDN_CH_INCOME>0</OUT_APPL_PDN_CH_INCOME>\n" +
                    "                         <OUT_APPL_PDN_VALUE_BY_CONFIRMED_INCOME>0</OUT_APPL_PDN_VALUE_BY_CONFIRMED_INCOME>\n" +
                    "                         <OUT_APPL_PDN_VALUE_BY_INCOME_PER_PERSON>0.0761909599426</OUT_APPL_PDN_VALUE_BY_INCOME_PER_PERSON>\n" +
                    "                         <OUT_APPL_PDN_VALUE_BY_CH_INCOME>0</OUT_APPL_PDN_VALUE_BY_CH_INCOME>\n" +
                    "                         <OUT_APPL_PDN_MIN_VALUE>0.0761909599426</OUT_APPL_PDN_MIN_VALUE>\n" +
                    "                         <OUT_APPL_PDN_CH_EXPENSES>3000</OUT_APPL_PDN_CH_EXPENSES>\n" +
                    "                         <OUT_APPL_PDN_INCOME_FOR_PDN_CALC>39374.75</OUT_APPL_PDN_INCOME_FOR_PDN_CALC>\n" +
                    "                         <OUT_APPL_PDN_CALCULATED_WITH_INCPERPERSON_FLAG>1</OUT_APPL_PDN_CALCULATED_WITH_INCPERPERSON_FLAG>\n" +
                    "                         <OUT_APPL_PDN_TOTALINCOME>" + IN_CUSTOMER_BASICSALARY + "</OUT_APPL_PDN_TOTALINCOME>\n" +
                    "                     </Variables>\n" +
                    "                     <Categories>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>" + IN_APPL_REQUEST_DATE + "</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>pdnLimitCLIP</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>pdnExpense</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>3000</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>pdnIncomeType</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>per_person</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>pdnAvgMonthlyIncome</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>39374.75</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>pdnNotConfirmedIncome</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>39374.75</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>pdnConfirmedIncome</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>pdnPerPersonIncome</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>39374.75</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>pdnImputedIncome</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>pdnValue</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0.0762</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>pdnOfferPSK</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>pdnOfferPmt</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>3000</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>pdnBkiIncome</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>pdnBkiPmt</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>pdnBkiStartDate</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>2024-05-01</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>pdnBkiEndDate</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>2022-05-01</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>pdnBkiMnthCnt</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>18</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_1</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_2</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_3</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_4</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_5</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_6</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_7</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_8</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_9</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_10</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_11</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_12</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_13</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_14</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_15</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_16</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_17</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_18</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_19</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_20</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_21</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_22</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_23</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                             <Variables>\n" +
                    "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_24</COMMON_PROPERTY_NAME>\n" +
                    "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                    "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                    "                             </Variables>\n" +
                    "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                    "                     </Categories>\n" +
                    "                 </OUT_APPL_PDN>\n" +
                    "                 <OUT_APPL_DEC_RULES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_RS_CODE>TECH_RULE_4</OUT_APPL_DEC_RS_CODE>\n" +
                    "                         <OUT_APPL_DEC_RS_TYPE>SI</OUT_APPL_DEC_RS_TYPE>\n" +
                    "                         <OUT_APPL_DEC_RS_NAME>WalkIn_NTB</OUT_APPL_DEC_RS_NAME>\n" +
                    "                         <OUT_APPL_DEC_RS_DESCRIPTION>WalkIn_NTB</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_RULES>\n" +
                    "                 <OUT_APPL_DEC_RULES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_RS_CODE>ND013</OUT_APPL_DEC_RS_CODE>\n" +
                    "                         <OUT_APPL_DEC_RS_TYPE>I</OUT_APPL_DEC_RS_TYPE>\n" +
                    "                         <OUT_APPL_DEC_RS_NAME>В блоке НСИ отсутствует полный перечень нормирующих констант</OUT_APPL_DEC_RS_NAME>\n" +
                    "                         <OUT_APPL_DEC_RS_DESCRIPTION>В блоке НСИ отсутствует полный перечень нормирующих констант</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_RULES>\n" +
                    "                 <OUT_APPL_DEC_RULES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_RS_CODE>SCR_RULE_2</OUT_APPL_DEC_RS_CODE>\n" +
                    "                         <OUT_APPL_DEC_RS_TYPE>SI</OUT_APPL_DEC_RS_TYPE>\n" +
                    "                         <OUT_APPL_DEC_RS_NAME>NTB_S_4$Частная (ЗАО/АО)_0 .. 5_1$Холост / Не замужем_1_3</OUT_APPL_DEC_RS_NAME>\n" +
                    "                         <OUT_APPL_DEC_RS_DESCRIPTION>4_55_0.1818$55$Республика Башкортостан_1</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_RULES>\n" +
                    "                 <OUT_APPL_DEC_RULES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_RS_CODE>SCR_RULE_1</OUT_APPL_DEC_RS_CODE>\n" +
                    "                         <OUT_APPL_DEC_RS_TYPE>SI</OUT_APPL_DEC_RS_TYPE>\n" +
                    "                         <OUT_APPL_DEC_RS_NAME>-2.8276545449435124,-6.162640877954405,0.002102253647013393</OUT_APPL_DEC_RS_NAME>\n" +
                    "                         <OUT_APPL_DEC_RS_DESCRIPTION>1,-5.338589376383459,0.004779685540496534,0</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_RULES>\n" +
                    "                 <OUT_APPL_DEC_RULES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_RS_CODE>SCR_RULE_0</OUT_APPL_DEC_RS_CODE>\n" +
                    "                         <OUT_APPL_DEC_RS_TYPE>SI</OUT_APPL_DEC_RS_TYPE>\n" +
                    "                         <OUT_APPL_DEC_RS_NAME>1:WalkIn_NTB</OUT_APPL_DEC_RS_NAME>\n" +
                    "                         <OUT_APPL_DEC_RS_DESCRIPTION>1:0.0056494628297687486</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_RULES>\n" +
                    "                 <OUT_APPL_DEC_RULES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_RS_CODE>ND013</OUT_APPL_DEC_RS_CODE>\n" +
                    "                         <OUT_APPL_DEC_RS_TYPE>I</OUT_APPL_DEC_RS_TYPE>\n" +
                    "                         <OUT_APPL_DEC_RS_NAME>В блоке НСИ отсутствует полный перечень нормирующих констант</OUT_APPL_DEC_RS_NAME>\n" +
                    "                         <OUT_APPL_DEC_RS_DESCRIPTION>В блоке НСИ отсутствует полный перечень нормирующих констант</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_RULES>\n" +
                    "                 <OUT_APPL_DEC_RULES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_RS_CODE>S1_VER</OUT_APPL_DEC_RS_CODE>\n" +
                    "                         <OUT_APPL_DEC_RS_TYPE>SI</OUT_APPL_DEC_RS_TYPE>\n" +
                    "                         <OUT_APPL_DEC_RS_NAME>S1_VER</OUT_APPL_DEC_RS_NAME>\n" +
                    "                         <OUT_APPL_DEC_RS_DESCRIPTION>L21_V293</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_RULES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>cus_prj_group</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE />\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>limitUpSegment</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR />\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE />\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>lib_PDN</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>libVersion</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>v1.21</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>v_INQUIRY1WEEK_avg</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>10.23287</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>v_INQUIRY1MONTH_avg</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>26.286556</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>v_INQUIRY3MONTH_avg</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>56.445345</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>v_INQUIRY6MONTH_avg</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>86.080923</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>v_INQUIRY9MONTH_avg</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>103.887847</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>v_INQUIRY12MONTH_avg</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>126.401192</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>probability_1</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>0.03980353136081286</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>pd_BKI_CCNTB_v1</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>0.03980353136081286</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>score_BKI_CCNTB_v1</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>-3.1831822823424845</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>megPD</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>0.018098</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>score_OFv3</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>-3.993690072111527</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>score_BKI_CCNTB_v1_calibr</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>-1.9206879851896734</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>score2_BKI_CCNTBv1_meg</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>-3.597614476462521</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>score2_BKI_CCNTBv1_meg_calibrated</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>-2.660275969665012</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>pd_BKI_CCNTB_v1_calibr</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>0.12778486667624236</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>pd_BKI_CCNTBv1_meg_calibrated</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>0.06535847331526524</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>BKI_NEW_MEG</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>6-</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>PD_OLD</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>BKI_NEW_MEG</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>0.005649462829768749</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>limitsDebug</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>WalkIn_NTB 23-67 BKIHIT pilotNew Max_Limit1 6-</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>limitsDebugUp</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>WalkIn_NTB 23-67 BKIHIT pilotNew Max_Limit1 5-</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>cusCCNTBPD</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR />\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE />\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>0.06535847331526524</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>MPL_PARAM</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>PDN_CC_FLAG 0</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>MPL_PARAM</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>PDN_CC_LIMIT 0.5</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>mplPdnCCLimit</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>0.5</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>mplPdnCCFlag</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>0</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>pdnIncome</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>39374.75</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>pdnSpendMax</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>19687.375</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>pdnSpendCurrent</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>0</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>offer_1</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR />\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE />\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>1 100000 0 0 0 100000 100000 9999 110 NULL 1 0.209 0</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>IN_CUSTOMER_CREDITPAYEXP</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>0</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>VAR_CUS_BKI_OVERVIEW_MONTHLY_PAYMENT</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>5400</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>IN_CUSTOMER_OTHEREXP</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>0</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>VAR_CUSTOMER_MIN_LIVING_EXP</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>8643</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>maxOffer.offerCreditLimit</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>100000</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>maxOffer.offerCreditLimit*0.04</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>4000</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>cusTotalIncome</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>" + IN_CUSTOMER_BASICSALARY + "</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>pti</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>0.0768</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>ITERNUM</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>Номер вызова S1</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>2</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>S1CallDateTime</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>Дата и время вызова S1</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>" + LONG_DATA + "</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>applPSKCalcEnabledFlag</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>Флаг расчёта и контроля ПСК в стратегии</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>applPresaleNavigatorFlag</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>Флаг процесса FA / MA NAVIGATOR</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>Segment</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>WalkIn_NTB</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>cusWorkerType</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>Other</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>MaxPTIByPD</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>1.0</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>MaxLimitByPD</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>100000</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>MinLivingExp</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>NULL</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>TotalIncome</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>" + IN_CUSTOMER_BASICSALARY + ".00</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>Score_Raw</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>-5.338589376383459</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>Score_Scaled</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>241.0</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>PD</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>0.06535847331526524</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>RiskLevel</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>NULL</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>MasterGrade</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>6-</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>PrevappMonthlyPayment</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>0.00</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>FccreditMonthlyPayment</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>BkiMonthlyPayment</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>5400.0</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>BkiMonthlyPayment (не учитывать)</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>0.0</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>DepCount</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>RegionMinSalary (ПМ)</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>8643</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>RAPID_CLIP_FLAG</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>BKI_HIT_FLAG</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>1</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>MKK_PD</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>0.1438122751059222</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>MKK_score</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>-1.783980852887030</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>OFFER with ID: 1</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>NULL</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>IsMain:1, Limit:100000, MinLimit:9999, Rate:0.209</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>LIMIT_UP_FLAG</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>LIMITUP</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>NUMBER</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>DIGITAL_LIMIT_PILOT_NEW</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>PILOT_NEW</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>NUMBER</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>11</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>DIGITAL_LIMIT_PILOT_NEW_TECH</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>PILOT_NEW</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>NUMBER</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>11</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>IN_APPL_CREATE_DATE</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>IN_APPL_CREATE_DATE</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>" + IN_APPL_CREATE_DATE + "</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "                 <OUT_APPL_DEC_VARIABLES>\n" +
                    "                     <Variables>\n" +
                    "                         <OUT_APPL_DEC_VAR_NAME>MKK_DECISION</OUT_APPL_DEC_VAR_NAME>\n" +
                    "                         <OUT_APPL_DEC_VAR_DESCR>MKK_VARS_GROUP</OUT_APPL_DEC_VAR_DESCR>\n" +
                    "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                    "                         <OUT_APPL_DEC_VAR_VALUE>DECLINE</OUT_APPL_DEC_VAR_VALUE>\n" +
                    "                     </Variables>\n" +
                    "                 </OUT_APPL_DEC_VARIABLES>\n" +
                    "             </Categories>\n" +
                    "         </Application>\n" +
                    "     </Body>\n" +
                    " </StrategyOneResponseT5>\n" +
                    //"</ConnectorOutput>"+
                    " </Response>\n" +
                    "</soap:Body>\n" +
                    "</soap:Envelope>";

            String main_resp_2 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                    "<soap:Body>\n"+
                    "<root>\n" +
                    " <Address>\n" +
                    "     <msg/>\n" +
                    "     <stat>NON</stat>\n" +
                    " </Address>\n" +
                    " <Phone>\n" +
                    "     <msg/>\n" +
                    "     <stat>NON</stat>\n" +
                    " </Phone>\n" +
                    " <Name>\n" +
                    "     <msg/>\n" +
                    "     <stat>NON</stat>\n" +
                    " </Name>\n" +
                    " <Result>\n" +
                    "     <msg/>\n" +
                    "     <stat>NON</stat>\n" +
                    " </Result>\n" +
                    " <Extended>\n" +
                    "     <Response>\n" +
                    "         <ResultInfo ResultType=\"True\" ExecutionTime=\"177\" DateTime=\"" + current_date_simple + "T07:28:09\"/>\n" +
                    "         <Data>\n" +
                    "             <Report ActualDate=\"" + current_date_simple + "\">\n" +
                    "                 <SparkID>1180</SparkID>\n" +
                    "                 <CompanyType>1</CompanyType>\n" +
                    "                 <Status IsActing=\"true\" Code=\"24\" Type=\"Действующее\" GroupId=\"1\" GroupName=\"Действующее\" Date=\"" + current_date_simple + "\"/>\n" +
                    "                 <EGRPOIncluded>true</EGRPOIncluded>\n" +
                    "                 <IsActing>true</IsActing>\n" +
                    "                 <DateFirstReg>1995-01-13</DateFirstReg>\n" +
                    "                 <ShortNameRus>ПАО АНК \"БАШНЕФТЬ\"</ShortNameRus>\n" +
                    "                 <ShortNameEn>JOINT STOCK OIL COMPANY BASHNEFT</ShortNameEn>\n" +
                    "                 <FullNameRus>ПУБЛИЧНОЕ АКЦИОНЕРНОЕ ОБЩЕСТВО \"АКЦИОНЕРНАЯ НЕФТЯНАЯ КОМПАНИЯ \"БАШНЕФТЬ\"</FullNameRus>\n" +
                    "                 <NormName>БАШНЕФТЬ, ПАО АНК</NormName>\n" +
                    "                 <GUID>23A3D623DB434B15A4829898A47A578E</GUID>\n" +
                    "                 <INN>0274051582</INN>\n" +
                    "                 <KPP>027501001</KPP>\n" +
                    "                 <OGRN>1020202555240</OGRN>\n" +
                    "                 <OKPO>00135645</OKPO>\n" +
                    "                 <FCSMCode>00013-A</FCSMCode>\n" +
                    "                 <RTS>BANE</RTS>\n" +
                    "                 <OKATO Code=\"80401380000\" RegionName=\"Башкортостан (Республика)\" RegionCode=\"80\"/>\n" +
                    "                 <OKTMO Code=\"80701000001\"/>\n" +
                    "                 <OKOGU Code=\"4210001\" Name=\"Хозяйственные общества, образованные из  государственных  предприятий, добровольных объединений государственных предприятий\"/>\n" +
                    "                 <OKFS Code=\"42\" Name=\"Смешанная российская собственность с долей собственности субъектов Российской Федерации\"/>\n" +
                    "                 <OKOPF Code=\"47\" CodeNew=\"12247\" Name=\"Публичные акционерные общества\"/>\n" +
                    "                 <OKVED2List>\n" +
                    "                     <OKVED Code=\"06.10.1\" Name=\"Добыча нефти\" IsMain=\"true\"/>\n" +
                    "                     <OKVED Code=\"01.13.1\" Name=\"Выращивание овощей\"/>\n" +
                    "                     <OKVED Code=\"06.10.3\" Name=\"Добыча нефтяного (попутного) газа\"/>\n" +
                    "                 </OKVED2List>\n" +
                    "                 <CharterCapital>177634501</CharterCapital>\n" +
                    "                 <CharterCapitalHistory>\n" +
                    "                     <CharterCapital ActualDate=\"2014-05-14\">180359000</CharterCapital>\n" +
                    "                     <CharterCapital ActualDate=\"2012-11-29\">227384465</CharterCapital>\n" +
                    "                     <CharterCapital ActualDate=\"2002-10-15\">204792000</CharterCapital>\n" +
                    "                 </CharterCapitalHistory>\n" +
                    "                 <ChangesInNameAndLegalForm>\n" +
                    "                     <Change>\n" +
                    "                         <Name>Публичное акционерное общество \"Акционерная нефтяная Компания \"Башнефть\"</Name>\n" +
                    "                         <ChangeDate>2015-06-05</ChangeDate>\n" +
                    "                     </Change>\n" +
                    "                 </ChangesInNameAndLegalForm>\n" +
                    "                 <LeaderList>\n" +
                    "                     <Leader ActualDate=\"2022-06-01\" FIO=\"Лазеев Андрей Николаевич\" Position=\"генеральный директор\" INN=\"860401000350\"/>\n" +
                    "                     <Leader ActualDate=\"2019-10-02\" FIO=\"Татриев Хасан Курейшевич\" Position=\"генеральный директор\" INN=\"773209571887\"/>\n" +
                    "                     <Leader ActualDate=\"2016-10-13\" FIO=\"Шишкин Андрей Николаевич\" Position=\"президент\" INN=\"770601917412\"/>\n" +
                    "                     <Leader ActualDate=\"2011-04-14\" FIO=\"Корсик Александр Леонидович\" Position=\"президент\" INN=\"772638552680\"/>\n" +
                    "                     <Leader ActualDate=\"2009-10-07\" FIO=\"Хорошавцев Виктор Геннадьевич\" Position=\"президент\" INN=\"183102742280\"/>\n" +
                    "                     <Leader ActualDate=\"2009-05-05\" FIO=\"Граханцев Николай Михайлович\" Position=\"генеральный директор\" INN=\"183101383775\"/>\n" +
                    "                     <Leader ActualDate=\"2006-06-01\" FIO=\"Рахимов Урал Муртазович\" Position=\"генеральный директор\" INN=\"027701130148\"/>\n" +
                    "                     <Leader ActualDate=\"2005-01-26\" FIO=\"Габитов Гимран Хамитович\" Position=\"генеральный директор\" INN=\"026102725458\"/>\n" +
                    "                     <Leader ActualDate=\"2002-10-15\" FIO=\"Исхаков Ильдар Ахмадуллович\" Position=\"генеральный директор\" INN=\"026101974187\"/>\n" +
                    "                 </LeaderList>\n" +
                    "                 <LegalAddresses>\n" +
                    "                     <Address PostCode=\"450077\" Address=\"Башкортостан респ., г. Уфа, ул. Карла Маркса, д. 30 к. 1\" Region=\"Башкортостан респ.\" City=\"г. Уфа\" StreetName=\"ул. Карла Маркса\" BuildingNumber=\"д. 30\" FiasGUID=\"b1bfe16e-b1a7-4cca-9cb5-d2b4f84a462e\" IsHouseFiasGUID=\"true\" FiasCode=\"020000010000000004400000000\" FiasRegion=\"02\" FiasArea=\"000\" FiasCity=\"001\" FiasPlace=\"000\" FiasPlan=\"0000\" FiasStreet=\"0044\" ActualDate=\"2022-09-09\" Housing=\"к. 1\"/>\n" +
                    "                 </LegalAddresses>\n" +
                    "                 <PreviousAddress>\n" +
                    "                     <Address PostCode=\"450077\" Address=\"Башкортостан респ., г. Уфа, ул. Карла Маркса, д. 30 корп. 1\" Region=\"Башкортостан респ.\" City=\"г. Уфа\" StreetName=\"ул. Карла Маркса\" BuildingNumber=\"д. 30 корп. 1\" FiasGUID=\"b1bfe16e-b1a7-4cca-9cb5-d2b4f84a462e\" IsHouseFiasGUID=\"true\" FiasCode=\"020000010000000004400000000\" FiasRegion=\"02\" FiasArea=\"000\" FiasCity=\"001\" FiasPlace=\"000\" FiasPlan=\"0000\" FiasStreet=\"0044\" ActualDate=\"2015-04-09\"/>\n" +
                    "                     <Address PostCode=\"450077\" Address=\"Башкортостан респ., г. Уфа, ул. Карла Маркса, д. 30\" Region=\"Башкортостан респ.\" City=\"г. Уфа\" StreetName=\"ул. Карла Маркса\" BuildingNumber=\"д. 30\" FiasGUID=\"0fc9b7e0-19ea-4ae3-8d54-0d86e2d8c74d\" IsHouseFiasGUID=\"true\" FiasCode=\"020000010000000004400000000\" FiasRegion=\"02\" FiasArea=\"000\" FiasCity=\"001\" FiasPlace=\"000\" FiasPlan=\"0000\" FiasStreet=\"0044\" ActualDate=\"2012-11-27\"/>\n" +
                    "                     <Address Address=\"Башкортостан респ., ул. К Маркса, д. 30\" Region=\"Башкортостан\" ActualDate=\"2012-11-20\"/>\n" +
                    "                     <Address PostCode=\"450077\" Address=\"Башкортостан респ., г. Уфа, ул. Карла Маркса, д. 30\" Region=\"Башкортостан респ.\" City=\"г. Уфа\" StreetName=\"ул. Карла Маркса\" BuildingNumber=\"д. 30\" FiasGUID=\"0fc9b7e0-19ea-4ae3-8d54-0d86e2d8c74d\" IsHouseFiasGUID=\"true\" FiasCode=\"020000010000000004400000000\" FiasRegion=\"02\" FiasArea=\"000\" FiasCity=\"001\" FiasPlace=\"000\" FiasPlan=\"0000\" FiasStreet=\"0044\" ActualDate=\"2012-09-27\"/>\n" +
                    "                     <Address Address=\"Башкортостан респ., ул. К Маркса, д. 30\" Region=\"Башкортостан\" ActualDate=\"2012-08-30\"/>\n" +
                    "                     <Address PostCode=\"450077\" Address=\"Башкортостан респ., г. Уфа, ул. Карла Маркса, д. 30\" Region=\"Башкортостан респ.\" City=\"г. Уфа\" StreetName=\"ул. Карла Маркса\" BuildingNumber=\"д. 30\" FiasGUID=\"0fc9b7e0-19ea-4ae3-8d54-0d86e2d8c74d\" IsHouseFiasGUID=\"true\" FiasCode=\"020000010000000004400000000\" FiasRegion=\"02\" FiasArea=\"000\" FiasCity=\"001\" FiasPlace=\"000\" FiasPlan=\"0000\" FiasStreet=\"0044\" ActualDate=\"2012-04-05\"/>\n" +
                    "                     <Address PostCode=\"450077\" Address=\"Башкортостан респ., г. Уфа, ул. Карла Маркса, д. 30\" Region=\"Башкортостан респ.\" City=\"г. Уфа\" StreetName=\"ул. Карла Маркса\" BuildingNumber=\"д. 30\" FiasGUID=\"0fc9b7e0-19ea-4ae3-8d54-0d86e2d8c74d\" IsHouseFiasGUID=\"true\" FiasCode=\"020000010000000004400000000\" FiasRegion=\"02\" FiasArea=\"000\" FiasCity=\"001\" FiasPlace=\"000\" FiasPlan=\"0000\" FiasStreet=\"0044\" ActualDate=\"2010-02-01\"/>\n" +
                    "                     <Address PostCode=\"450070\" Address=\"Башкортостан респ., г. Уфа\" Region=\"Башкортостан респ.\" City=\"г. Уфа\" FiasGUID=\"7339e834-2cb4-4734-a4c7-1fca2c66e562\" IsHouseFiasGUID=\"false\" FiasCode=\"020000010000000000000000000\" FiasRegion=\"02\" FiasArea=\"000\" FiasCity=\"001\" FiasPlace=\"000\" FiasPlan=\"0000\" FiasStreet=\"0000\" ActualDate=\"2003-05-20\"/>\n" +
                    "                     <Address PostCode=\"450008\" Address=\"Башкортостан респ., г. Уфа, ул. Пушкина, д. 95\" Region=\"Башкортостан респ.\" City=\"г. Уфа\" StreetName=\"ул. Пушкина\" BuildingNumber=\"д. 95\" FiasGUID=\"678fd64a-2cf5-479f-b07a-ed48cbc9573d\" IsHouseFiasGUID=\"true\" FiasCode=\"020000010000000110800000000\" FiasRegion=\"02\" FiasArea=\"000\" FiasCity=\"001\" FiasPlace=\"000\" FiasPlan=\"0000\" FiasStreet=\"1108\" ActualDate=\"2002-10-15\"/>\n" +
                    "                 </PreviousAddress>\n" +
                    "                 <PhoneList>\n" +
                    "                     <Phone Code=\"347\" Number=\"2838133\" VerificationDate=\"2015-02-09\"/>\n" +
                    "                     <Phone Code=\"347\" Number=\"2616133\"/>\n" +
                    "                     <Phone Code=\"347\" Number=\"2622139\"/>\n" +
                    "                     <Phone Code=\"347\" Number=\"2622456\"/>\n" +
                    "                     <Phone Code=\"347\" Number=\"2797387\"/>\n" +
                    "                     <Phone Code=\"347\" Number=\"9274600\"/>\n" +
                    "                     <Phone Code=\"3472\" Number=\"358230\"/>\n" +
                    "                     <Phone Code=\"3472\" Number=\"358310\"/>\n" +
                    "                     <Phone Code=\"3472\" Number=\"425573\"/>\n" +
                    "                     <Phone Code=\"3472\" Number=\"497990\"/>\n" +
                    "                     <Phone Code=\"3472\" Number=\"605699\"/>\n" +
                    "                     <Phone Code=\"3472\" Number=\"697246\"/>\n" +
                    "                     <Phone Code=\"3472\" Number=\"697695\"/>\n" +
                    "                     <Phone Code=\"3472\" Number=\"707558\"/>\n" +
                    "                     <Phone Code=\"3472\" Number=\"797228\"/>\n" +
                    "                 </PhoneList>\n" +
                    "                 <Email>aubakirovayd@bashneft.ru</Email>\n" +
                    "                 <Www>www.bashneft.ru</Www>\n" +
                    "                 <WorkersRange>5001 и более</WorkersRange>\n" +
                    "                 <StaffNumberDisclosure>\n" +
                    "                     <Number ActualDate=\"2021-06-30\">9435</Number>\n" +
                    "                     <Number ActualDate=\"2020-12-31\">9324</Number>\n" +
                    "                     <Number ActualDate=\"2019-12-31\">9183</Number>\n" +
                    "                     <Number ActualDate=\"2014-12-31\">9725</Number>\n" +
                    "                     <Number ActualDate=\"2013-12-31\">11137</Number>\n" +
                    "                     <Number ActualDate=\"2012-12-31\">4411</Number>\n" +
                    "                     <Number ActualDate=\"2011-12-31\">974</Number>\n" +
                    "                     <Number ActualDate=\"2010-12-31\">645</Number>\n" +
                    "                 </StaffNumberDisclosure>\n" +
                    "                 <IncludeInList>\n" +
                    "                     <ListName Id=\"6\" IsNegative=\"false\"/>\n" +
                    "                     <ListName Id=\"12\" IsNegative=\"false\"/>\n" +
                    "                     <ListName Id=\"18\" IsNegative=\"false\"/>\n" +
                    "                     <ListName Id=\"21\" IsNegative=\"false\"/>\n" +
                    "                     <ListName Id=\"22\" IsNegative=\"false\"/>\n" +
                    "                     <ListName Id=\"36\" IsNegative=\"false\"/>\n" +
                    "                     <ListName Id=\"64\" IsNegative=\"false\"/>\n" +
                    "                 </IncludeInList>\n" +
                    "                 <ConsolidatedIndicator Value=\"Low\" Description=\"Низкий риск\">\n" +
                    "                     <AddInfo>\n" +
                    "                         <AddField Name=\"IndexOfDueDiligence\">Низкий риск</AddField>\n" +
                    "                         <AddField Name=\"FailureScore\">Низкий риск</AddField>\n" +
                    "                         <AddField Name=\"PaymentIndex\">Низкий риск просрочки платежа</AddField>\n" +
                    "                     </AddInfo>\n" +
                    "                 </ConsolidatedIndicator>\n" +
                    "                 <IndexOfDueDiligence Index=\"1\" IndexDesc=\"Низкий риск\"/>\n" +
                    "                 <FailureScore FailureScoreValue=\"19\" FailureScoreDesc=\"Низкий риск\"/>\n" +
                    "                 <PaymentIndex PaymentIndexValue=\"93\" PaymentIndexDesc=\"Низкий риск просрочки платежа\"/>\n" +
                    "                 <CreditLimit Value=\"N/A\" Description=\"Расчет кредитного лимита не осуществляется для следующих категорий юридических лиц: Унитарные предприятия, находящиеся в федеральной или муниципальной собственности; Банки; Страховые компании; Некоммерческие организации; Организации без прав юридического лица, а также для эмитентов, чьи акции обращаются на бирже.\"/>\n" +
                    "                 <CompanySize Revenue=\"659240\" Description=\"Крупные предприятия\" ActualDate=\"2021-12-31\"/>\n" +
                    "                 <FederalTaxRegistration>\n" +
                    "                     <RegDate>2002-10-15</RegDate>\n" +
                    "                     <RegAuthority>Межрайонная инспекция ФНС России № 40 по Республике Башкортостан</RegAuthority>\n" +
                    "                     <RegAuthorityAddress>450077, Уфа г, Коммунистическая ул, 59</RegAuthorityAddress>\n" +
                    "                     <RegAuthorityCode>0274</RegAuthorityCode>\n" +
                    "                 </FederalTaxRegistration>\n" +
                    "                 <FederalTaxRegistrationCurrent>\n" +
                    "                     <RegDate>2009-01-01</RegDate>\n" +
                    "                     <RegAuthority>Межрайонная инспекция ФНС России № 39 по Республике Башкортостан</RegAuthority>\n" +
                    "                     <RegAuthorityAddress>450076, Уфа г, Красина ул, 52</RegAuthorityAddress>\n" +
                    "                     <RegAuthorityCode>0280</RegAuthorityCode>\n" +
                    "                 </FederalTaxRegistrationCurrent>\n" +
                    "                 <FederalTaxRegistrationPayment>\n" +
                    "                     <RegDate>2011-02-01</RegDate>\n" +
                    "                     <RegAuthority>Межрайонная инспекция ФНС России № 40 по Республике Башкортостан</RegAuthority>\n" +
                    "                     <RegAuthorityAddress>450077, Уфа г, Коммунистическая ул, 59</RegAuthorityAddress>\n" +
                    "                     <RegAuthorityCode>0274</RegAuthorityCode>\n" +
                    "                 </FederalTaxRegistrationPayment>\n" +
                    "                 <RegistrationInFunds>\n" +
                    "                     <PensionFund>\n" +
                    "                         <RegistrationDate>2013-07-10</RegistrationDate>\n" +
                    "                         <RegisterNumber>002870116876</RegisterNumber>\n" +
                    "                         <RegAuthority>Государственное учреждение - Отделение Пенсионного фонда Российской Федерации по Республике Башкортостан</RegAuthority>\n" +
                    "                     </PensionFund>\n" +
                    "                     <SocialInsuranceFund>\n" +
                    "                         <RegistrationDate>2000-02-21</RegistrationDate>\n" +
                    "                         <RegisterNumber>027400241502121</RegisterNumber>\n" +
                    "                         <RegAuthority>Филиал №12 Государственного учреждения - регионального отделения Фонда социального страхования Российской Федерации по Республике Башкортостан</RegAuthority>\n" +
                    "                     </SocialInsuranceFund>\n" +
                    "                     <CompulsoryMedicalInsuranceFund>\n" +
                    "                         <RegistrationDate>2005-04-04</RegistrationDate>\n" +
                    "                         <RegisterNumber>804010500480281</RegisterNumber>\n" +
                    "                         <RegAuthority>Республиканский фонд обязательного медицинского страхования Республики Башкортостан</RegAuthority>\n" +
                    "                     </CompulsoryMedicalInsuranceFund>\n" +
                    "                 </RegistrationInFunds>\n" +
                    "                 <SubmittedStatements>\n" +
                    "                     <Statement Form=\"Р13014\" ReferenceNumber=\"17993А\" SubmissionDate=\"2024-05-08\" AvailabilityDate=\"2022-06-01\" GRN=\"2220200491509\" DecisionType=\"Решение о государственной регистрации\"/>\n" +
                    "                     <Statement Form=\"Р14001\" SubmissionDate=\"2022-09-25\" AvailabilityDate=\"2020-01-14\" GRN=\"2200200019072\" DecisionType=\"Решение о государственной регистрации\"/>\n" +
                    "                     <Statement Form=\"Р14001\" ReferenceNumber=\"47516А\" SubmissionDate=\"2022-06-22\" AvailabilityDate=\"2019-10-02\" GRN=\"2190280949912\" DecisionType=\"Решение о государственной регистрации\"/>\n" +
                    "                     <Statement Form=\"Р14001\" ReferenceNumber=\"22067А\" SubmissionDate=\"2021-02-21\" AvailabilityDate=\"2018-06-01\" GRN=\"2180280665145\" DecisionType=\"Решение о государственной регистрации\"/>\n" +
                    "                     <Statement Form=\"Р14001\" ReferenceNumber=\"20724А\" SubmissionDate=\"2020-02-05\" AvailabilityDate=\"2017-05-17\" GRN=\"2170280571481\" DecisionType=\"Решение о государственной регистрации\"/>\n" +
                    "                     <Statement Form=\"Р14001\" SubmissionDate=\"2019-07-10\" AvailabilityDate=\"2016-10-13\" GRN=\"6160280088116\" DecisionType=\"Решение о государственной регистрации\"/>\n" +
                    "                     <Statement Form=\"Р13001\" ReferenceNumber=\"18838А\" SubmissionDate=\"2019-03-28\" AvailabilityDate=\"2016-07-08\" GRN=\"2160280744578\" DecisionType=\"Решение о государственной регистрации\"/>\n" +
                    "                     <Statement Form=\"Р13001\" ReferenceNumber=\"36289А\" SubmissionDate=\"2018-08-30\" AvailabilityDate=\"2015-12-07\" GRN=\"6150280037473\" DecisionType=\"Решение о государственной регистрации\"/>\n" +
                    "                     <Statement Form=\"Р13001\" ReferenceNumber=\"19124А\" SubmissionDate=\"2018-04-01\" AvailabilityDate=\"2015-07-10\" GRN=\"2150280547481\" DecisionType=\"Решение о государственной регистрации\"/>\n" +
                    "                     <Statement Form=\"Р13001\" ReferenceNumber=\"15621А\" SubmissionDate=\"2018-02-25\" AvailabilityDate=\"2015-06-05\" GRN=\"2150280461747\" DecisionType=\"Решение о государственной регистрации\"/>\n" +
                    "                     <Statement Form=\"Р14002\" SubmissionDate=\"2017-12-17\" AvailabilityDate=\"2015-03-27\" GRN=\"2150280239074\" DecisionType=\"Решение о государственной регистрации\"/>\n" +
                    "                     <Statement Form=\"Р13002\" ReferenceNumber=\"42877А\" SubmissionDate=\"2017-09-19\" AvailabilityDate=\"2015-01-12\"/>\n" +
                    "                     <Statement Form=\"Р13002\" SubmissionDate=\"2017-09-19\" AvailabilityDate=\"2014-12-31\" GRN=\"6140280204729\" DecisionType=\"Решение о государственной регистрации\"/>\n" +
                    "                     <Statement Form=\"Р13001\" ReferenceNumber=\"18578А\" SubmissionDate=\"2017-03-07\" AvailabilityDate=\"2014-06-20\" GRN=\"2140280530960\" DecisionType=\"Решение о государственной регистрации\"/>\n" +
                    "                     <Statement Form=\"Р13001\" ReferenceNumber=\"14479А\" SubmissionDate=\"2017-01-30\" AvailabilityDate=\"2014-05-14\" GRN=\"2140280429310\" DecisionType=\"Решение о государственной регистрации\"/>\n" +
                    "                 </SubmittedStatements>\n" +
                    "                 <RegistrationCertificates>\n" +
                    "                     <RegistrationCertificate Series=\"02\" Number=\"004176539\" IssueDate=\"2002-10-15\" GRN=\"1020202555240\"/>\n" +
                    "                     <RegistrationCertificate Series=\"02\" Number=\"004177623\" IssueDate=\"2003-01-22\" GRN=\"2030203893839\"/>\n" +
                    "                     <RegistrationCertificate Series=\"02\" Number=\"002795521\" IssueDate=\"2003-05-20\" GRN=\"2030203931580\"/>\n" +
                    "                     <RegistrationCertificate Series=\"02\" Number=\"002917643\" IssueDate=\"2003-08-20\" GRN=\"2030204450220\"/>\n" +
                    "                     <RegistrationCertificate Series=\"02\" Number=\"004672644\" IssueDate=\"2005-01-26\" GRN=\"2050204433563\"/>\n" +
                    "                     <RegistrationCertificate Series=\"02\" Number=\"004673037\" IssueDate=\"2005-03-16\" GRN=\"2050204440240\"/>\n" +
                    "                     <RegistrationCertificate Series=\"02\" Number=\"004673670\" IssueDate=\"2005-05-30\" GRN=\"2050204457719\"/>\n" +
                    "                     <RegistrationCertificate Series=\"02\" Number=\"005266005\" IssueDate=\"2006-01-13\" GRN=\"2060277000056\"/>\n" +
                    "                     <RegistrationCertificate Series=\"02\" Number=\"005266006\" IssueDate=\"2006-01-13\" GRN=\"2060277000067\"/>\n" +
                    "                     <RegistrationCertificate Series=\"02\" Number=\"005357543\" IssueDate=\"2006-06-01\" GRN=\"2060277053879\"/>\n" +
                    "                     <RegistrationCertificate Series=\"02\" Number=\"005913698\" IssueDate=\"2009-01-23\" GRN=\"2090280104055\"/>\n" +
                    "                     <RegistrationCertificate Series=\"02\" Number=\"005913699\" IssueDate=\"2009-01-23\" GRN=\"2090280104264\"/>\n" +
                    "                     <RegistrationCertificate Series=\"02\" Number=\"005913700\" IssueDate=\"2009-01-23\" GRN=\"2090280104286\"/>\n" +
                    "                     <RegistrationCertificate Series=\"02\" Number=\"005913701\" IssueDate=\"2009-01-23\" GRN=\"2090280104297\"/>\n" +
                    "                     <RegistrationCertificate Series=\"02\" Number=\"005944667\" IssueDate=\"2009-05-05\" GRN=\"2090280304850\"/>\n" +
                    "                     <RegistrationCertificate Series=\"02\" Number=\"006024654\" IssueDate=\"2009-10-07\" GRN=\"2090280684228\"/>\n" +
                    "                     <RegistrationCertificate Series=\"02\" Number=\"006202561\" IssueDate=\"2010-02-01\" GRN=\"2100280294992\"/>\n" +
                    "                 </RegistrationCertificates>\n" +
                    "                 <StructureInfo>\n" +
                    "                     <CountCoownerFCSM>0</CountCoownerFCSM>\n" +
                    "                     <CountCoownerRosstat>0</CountCoownerRosstat>\n" +
                    "                     <CountCoownerEGRUL>0</CountCoownerEGRUL>\n" +
                    "                     <CountBranch>0</CountBranch>\n" +
                    "                     <CountBranchRosstat>6</CountBranchRosstat>\n" +
                    "                     <CountBranchEGRUL>6</CountBranchEGRUL>\n" +
                    "                     <CountAffiliatedCompanyFCSM>0</CountAffiliatedCompanyFCSM>\n" +
                    "                     <CountAffiliatedCompanyRosstat>1</CountAffiliatedCompanyRosstat>\n" +
                    "                     <CountAffiliatedCompanyEGRUL>17</CountAffiliatedCompanyEGRUL>\n" +
                    "                     <NonprofitOrganizationRosstat>4</NonprofitOrganizationRosstat>\n" +
                    "                 </StructureInfo>\n" +
                    "                 <AccessibleFinData>\n" +
                    "                     <Period IDPeriod=\"563\" Name=\"2021\" EndDate=\"2021-12-31\"/>\n" +
                    "                     <Period IDPeriod=\"562\" Name=\"2021 - III кв.\" EndDate=\"2021-09-30\"/>\n" +
                    "                     <Period IDPeriod=\"561\" Name=\"2021 - II кв.\" EndDate=\"2021-06-30\"/>\n" +
                    "                     <Period IDPeriod=\"560\" Name=\"2021 - I кв.\" EndDate=\"2021-03-31\"/>\n" +
                    "                     <Period IDPeriod=\"559\" Name=\"2020\" EndDate=\"2020-12-31\"/>\n" +
                    "                     <Period IDPeriod=\"558\" Name=\"2020 - III кв.\" EndDate=\"2020-09-30\"/>\n" +
                    "                     <Period IDPeriod=\"557\" Name=\"2020 - II кв.\" EndDate=\"2020-06-30\"/>\n" +
                    "                     <Period IDPeriod=\"556\" Name=\"2020 - I кв.\" EndDate=\"2020-03-31\"/>\n" +
                    "                 </AccessibleFinData>\n" +
                    "                 <Finance BalanceType=\"1\">\n" +
                    "                     <FinPeriod PeriodName=\"2017\" DateBegin=\"2017-01-01\" DateEnd=\"2017-12-31\">\n" +
                    "                         <StringList>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Нематериальные активы\" Code=\"1110\" Value=\"2832733000\" IdFinPok=\"1\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Основные средства\" Code=\"1150\" Value=\"159181727000\" IdFinPok=\"5\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Долгосрочные финансовые вложения\" Code=\"1170\" Value=\"147479418000\" IdFinPok=\"12\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Отложенные налоговые активы\" Code=\"1180\" Value=\"7830613000\" IdFinPok=\"17\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Прочие внеоборотные активы\" Code=\"1190\" Value=\"16046786000\" IdFinPok=\"18\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Внеоборотные активы\" Code=\"1100\" Value=\"336055338000\" IdFinPok=\"19\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Запасы\" Code=\"1210\" Value=\"32323583000\" IdFinPok=\"20\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Доходы и расходы по обычным видам деятельности\" Name=\"Выручка\" Code=\"2110\" Value=\"558568027000\" IdFinPok=\"91\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Доходы и расходы по обычным видам деятельности\" Name=\"Себестоимость продаж\" Code=\"2120\" Value=\"380882244000\" IdFinPok=\"101\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Доходы и расходы по обычным видам деятельности\" Name=\"Валовая прибыль (убыток)\" Code=\"2100\" Value=\"177176000000\" IdFinPok=\"110\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Доходы и расходы по обычным видам деятельности\" Name=\"Коммерческие расходы\" Code=\"2210\" Value=\"94156633000\" IdFinPok=\"111\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Доходы и расходы по обычным видам деятельности\" Name=\"Управленческие расходы\" Code=\"2220\" Value=\"8673803000\" IdFinPok=\"112\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Доходы и расходы по обычным видам деятельности\" Name=\"Прибыль (убыток) от продажи\" Code=\"2200\" Value=\"74345564000\" IdFinPok=\"113\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Доходы от участия в других организациях\" Code=\"2310\" Value=\"1576237000\" IdFinPok=\"116\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Проценты к получению\" Code=\"2320\" Value=\"4986038000\" IdFinPok=\"114\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Проценты к уплате\" Code=\"2330\" Value=\"11331868000\" IdFinPok=\"115\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Прочие доходы\" Code=\"2340\" Value=\"108194877000\" IdFinPok=\"117\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Прочие расходы\" Code=\"2350\" Value=\"15376102000\" IdFinPok=\"92\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Прибыль (убыток) до налогообложения\" Code=\"2300\" Value=\"162394746000\" IdFinPok=\"95\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Текущий налог на прибыль\" Code=\"2410\" Value=\"-35007017000\" IdFinPok=\"96\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Чистая прибыль (убыток)\" Code=\"2400\" Value=\"129325054000\" IdFinPok=\"100\"/>\n" +
                    "                         </StringList>\n" +
                    "                     </FinPeriod>\n" +
                    "                     <FinPeriod PeriodName=\"2018\" DateBegin=\"2018-01-01\" DateEnd=\"2018-12-31\">\n" +
                    "                         <StringList>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Нематериальные активы\" Code=\"1110\" Value=\"3424341000\" IdFinPok=\"1\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Основные средства\" Code=\"1150\" Value=\"154561409000\" IdFinPok=\"5\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Долгосрочные финансовые вложения\" Code=\"1170\" Value=\"156868243000\" IdFinPok=\"12\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Отложенные налоговые активы\" Code=\"1180\" Value=\"8250671000\" IdFinPok=\"17\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Прочие внеоборотные активы\" Code=\"1190\" Value=\"16449943000\" IdFinPok=\"18\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Внеоборотные активы\" Code=\"1100\" Value=\"342889999000\" IdFinPok=\"19\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Запасы\" Code=\"1210\" Value=\"42415108000\" IdFinPok=\"20\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"НДС по приобретенным ценностям\" Code=\"1220\" Value=\"7699133000\" IdFinPok=\"28\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Дебиторская задолженность\" Code=\"1230\" Value=\"163249221000\" IdFinPok=\"157\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Краткосрочные финансовые вложения\" Code=\"1240\" Value=\"50000000000\" IdFinPok=\"42\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Денежные средства и денежные эквиваленты\" Code=\"1250\" Value=\"502000\" IdFinPok=\"46\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Прочие оборотные активы\" Code=\"1260\" Value=\"2077251000\" IdFinPok=\"51\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Активы  всего\" Code=\"1600\" Value=\"608331214000\" IdFinPok=\"53\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Уставный капитал\" Code=\"1310\" Value=\"177635000\" IdFinPok=\"54\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Резервный капитал\" Code=\"1360\" Value=\"35527000\" IdFinPok=\"56\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Нераспределенная прибыль (непокрытый убыток)\" Code=\"1370\" Value=\"346432568000\" IdFinPok=\"64\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Капитал и резервы\" Code=\"1300\" Value=\"351330815000\" IdFinPok=\"66\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Заёмные средства (долгосрочные)\" Code=\"1410\" Value=\"91817825000\" IdFinPok=\"67\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Отложенные налоговые обязательства\" Code=\"1420\" Value=\"12474811000\" IdFinPok=\"99\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Прочие долгосрочные обязательства\" Code=\"1450\" Value=\"11212314000\" IdFinPok=\"70\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Прочие расходы\" Code=\"2350\" Value=\"11736262000\" IdFinPok=\"92\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Прибыль (убыток) до налогообложения\" Code=\"2300\" Value=\"127352308000\" IdFinPok=\"95\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Текущий налог на прибыль\" Code=\"2410\" Value=\"25852837000\" IdFinPok=\"96\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Чистая прибыль (убыток)\" Code=\"2400\" Value=\"101833004000\" IdFinPok=\"100\"/>\n" +
                    "                         </StringList>\n" +
                    "                     </FinPeriod>\n" +
                    "                     <FinPeriod PeriodName=\"2019\" DateBegin=\"2019-01-01\" DateEnd=\"2019-12-31\">\n" +
                    "                         <StringList>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Нематериальные активы\" Code=\"1110\" Value=\"3580073000\" IdFinPok=\"1\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Основные средства\" Code=\"1150\" Value=\"148181831000\" IdFinPok=\"5\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Долгосрочные финансовые вложения\" Code=\"1170\" Value=\"86841234000\" IdFinPok=\"12\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Отложенные налоговые активы\" Code=\"1180\" Value=\"13346144000\" IdFinPok=\"17\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Прочие внеоборотные активы\" Code=\"1190\" Value=\"16350643000\" IdFinPok=\"18\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Внеоборотные активы\" Code=\"1100\" Value=\"278105969000\" IdFinPok=\"19\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Запасы\" Code=\"1210\" Value=\"40187315000\" IdFinPok=\"20\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"НДС по приобретенным ценностям\" Code=\"1220\" Value=\"2033124000\" IdFinPok=\"28\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Дебиторская задолженность\" Code=\"1230\" Value=\"200459608000\" IdFinPok=\"157\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Краткосрочные финансовые вложения\" Code=\"1240\" Value=\"110304559000\" IdFinPok=\"42\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Денежные средства и денежные эквиваленты\" Code=\"1250\" Value=\"2443000\" IdFinPok=\"46\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Прочие оборотные активы\" Code=\"1260\" Value=\"1861343000\" IdFinPok=\"51\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Оборотные активы\" Code=\"1200\" Value=\"354848392000\" IdFinPok=\"52\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Активы  всего\" Code=\"1600\" Value=\"632954361000\" IdFinPok=\"53\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Уставный капитал\" Code=\"1310\" Value=\"177635000\" IdFinPok=\"54\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Резервный капитал\" Code=\"1360\" Value=\"35527000\" IdFinPok=\"56\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Доходы будущих периодов\" Code=\"1530\" Value=\"17249000\" IdFinPok=\"85\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Прочие краткосрочные обязательства\" Code=\"1550\" Value=\"253000\" IdFinPok=\"88\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Краткосрочные обязательства\" Code=\"1500\" Value=\"120932082000\" IdFinPok=\"89\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Пассивы всего\" Code=\"1700\" Value=\"632954361000\" IdFinPok=\"90\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Доходы и расходы по обычным видам деятельности\" Name=\"Выручка\" Code=\"2110\" Value=\"703150528000\" IdFinPok=\"91\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Доходы и расходы по обычным видам деятельности\" Name=\"Себестоимость продаж\" Code=\"2120\" Value=\"514466674000\" IdFinPok=\"101\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Доходы и расходы по обычным видам деятельности\" Name=\"Валовая прибыль (убыток)\" Code=\"2100\" Value=\"187689889000\" IdFinPok=\"110\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Доходы и расходы по обычным видам деятельности\" Name=\"Коммерческие расходы\" Code=\"2210\" Value=\"109717151000\" IdFinPok=\"111\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Доходы и расходы по обычным видам деятельности\" Name=\"Управленческие расходы\" Code=\"2220\" Value=\"8197411000\" IdFinPok=\"112\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Доходы и расходы по обычным видам деятельности\" Name=\"Прибыль (убыток) от продажи\" Code=\"2200\" Value=\"69775327000\" IdFinPok=\"113\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Доходы от участия в других организациях\" Code=\"2310\" Value=\"2831613000\" IdFinPok=\"116\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Проценты к получению\" Code=\"2320\" Value=\"8244630000\" IdFinPok=\"114\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Проценты к уплате\" Code=\"2330\" Value=\"11625161000\" IdFinPok=\"115\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Прочие доходы\" Code=\"2340\" Value=\"5738324000\" IdFinPok=\"117\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Прочие расходы\" Code=\"2350\" Value=\"14317105000\" IdFinPok=\"92\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Прибыль (убыток) до налогообложения\" Code=\"2300\" Value=\"60647628000\" IdFinPok=\"95\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Текущий налог на прибыль\" Code=\"2410\" Value=\"18534065000\" IdFinPok=\"96\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Чистая прибыль (убыток)\" Code=\"2400\" Value=\"49159699000\" IdFinPok=\"100\"/>\n" +
                    "                         </StringList>\n" +
                    "                     </FinPeriod>\n" +
                    "                     <FinPeriod PeriodName=\"2020\" DateBegin=\"2020-01-01\" DateEnd=\"2020-12-31\">\n" +
                    "                         <StringList>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Нематериальные активы\" Code=\"1110\" Value=\"3927673000\" IdFinPok=\"1\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Основные средства\" Code=\"1150\" Value=\"151226644000\" IdFinPok=\"5\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Долгосрочные финансовые вложения\" Code=\"1170\" Value=\"183589754000\" IdFinPok=\"12\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Отложенные налоговые активы\" Code=\"1180\" Value=\"17959486000\" IdFinPok=\"17\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Прочие внеоборотные активы\" Code=\"1190\" Value=\"15662452000\" IdFinPok=\"18\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Внеоборотные активы\" Code=\"1100\" Value=\"382232963000\" IdFinPok=\"19\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Запасы\" Code=\"1210\" Value=\"32611966000\" IdFinPok=\"20\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"НДС по приобретенным ценностям\" Code=\"1220\" Value=\"957437000\" IdFinPok=\"28\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Дебиторская задолженность\" Code=\"1230\" Value=\"154405823000\" IdFinPok=\"157\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Краткосрочные финансовые вложения\" Code=\"1240\" Value=\"18223286000\" IdFinPok=\"42\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Денежные средства и денежные эквиваленты\" Code=\"1250\" Value=\"38068000\" IdFinPok=\"46\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Прочие оборотные активы\" Code=\"1260\" Value=\"1535438000\" IdFinPok=\"51\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Прибыль (убыток) до налогообложения\" Code=\"2300\" Value=\"-29230402000\" IdFinPok=\"95\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Текущий налог на прибыль\" Code=\"2410\" Value=\"5517459000\" IdFinPok=\"96\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Операционные доходы и расходы\" Name=\"Чистая прибыль (убыток)\" Code=\"2400\" Value=\"-23557058000\" IdFinPok=\"100\"/>\n" +
                    "                         </StringList>\n" +
                    "                     </FinPeriod>\n" +
                    "                     <FinPeriod PeriodName=\"2021\" DateBegin=\"2021-01-01\" DateEnd=\"2021-12-31\">\n" +
                    "                         <StringList>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Нематериальные активы\" Code=\"1110\" Value=\"4278874000\" IdFinPok=\"1\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Основные средства\" Code=\"1150\" Value=\"159156562000\" IdFinPok=\"5\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Долгосрочные финансовые вложения\" Code=\"1170\" Value=\"177884409000\" IdFinPok=\"12\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Отложенные налоговые активы\" Code=\"1180\" Value=\"13914640000\" IdFinPok=\"17\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Прочие внеоборотные активы\" Code=\"1190\" Value=\"15904207000\" IdFinPok=\"18\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Внеоборотные активы\" Code=\"1100\" Value=\"383598621000\" IdFinPok=\"19\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Запасы\" Code=\"1210\" Value=\"41649462000\" IdFinPok=\"20\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"НДС по приобретенным ценностям\" Code=\"1220\" Value=\"1998545000\" IdFinPok=\"28\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Дебиторская задолженность\" Code=\"1230\" Value=\"295232629000\" IdFinPok=\"157\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Краткосрочные финансовые вложения\" Code=\"1240\" Value=\"7633542000\" IdFinPok=\"42\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Денежные средства и денежные эквиваленты\" Code=\"1250\" Value=\"633000\" IdFinPok=\"46\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Прочие оборотные активы\" Code=\"1260\" Value=\"2008560000\" IdFinPok=\"51\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Оборотные активы\" Code=\"1200\" Value=\"348523371000\" IdFinPok=\"52\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Актив\" Name=\"Активы  всего\" Code=\"1600\" Value=\"732121992000\" IdFinPok=\"53\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Уставный капитал\" Code=\"1310\" Value=\"177635000\" IdFinPok=\"54\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Резервный капитал\" Code=\"1360\" Value=\"35527000\" IdFinPok=\"56\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Нераспределенная прибыль (непокрытый убыток)\" Code=\"1370\" Value=\"415579676000\" IdFinPok=\"64\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Капитал и резервы\" Code=\"1300\" Value=\"420437459000\" IdFinPok=\"66\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Заёмные средства (долгосрочные)\" Code=\"1410\" Value=\"65817208000\" IdFinPok=\"67\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Отложенные налоговые обязательства\" Code=\"1420\" Value=\"13659749000\" IdFinPok=\"99\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Прочие долгосрочные обязательства\" Code=\"1450\" Value=\"94714000\" IdFinPok=\"70\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Долгосрочные обязательства\" Code=\"1400\" Value=\"132188207000\" IdFinPok=\"71\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Заёмные средства (краткосрочные)\" Code=\"1510\" Value=\"31233004000\" IdFinPok=\"72\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Кредиторская задолженность\" Code=\"1520\" Value=\"144936122000\" IdFinPok=\"75\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Прочие краткосрочные обязательства\" Code=\"1550\" Value=\"117423000\" IdFinPok=\"88\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Краткосрочные обязательства\" Code=\"1500\" Value=\"179496326000\" IdFinPok=\"89\"/>\n" +
                    "                             <String Form=\"Баланс\" Section=\"Пассив\" Name=\"Пассивы всего\" Code=\"1700\" Value=\"732121992000\" IdFinPok=\"90\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Доходы и расходы по обычным видам деятельности\" Name=\"Выручка\" Code=\"2110\" Value=\"659240031000\" IdFinPok=\"91\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Доходы и расходы по обычным видам деятельности\" Name=\"Себестоимость продаж\" Code=\"2120\" Value=\"472597630000\" IdFinPok=\"101\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Доходы и расходы по обычным видам деятельности\" Name=\"Валовая прибыль (убыток)\" Code=\"2100\" Value=\"184826327000\" IdFinPok=\"110\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Доходы и расходы по обычным видам деятельности\" Name=\"Коммерческие расходы\" Code=\"2210\" Value=\"68742958000\" IdFinPok=\"111\"/>\n" +
                    "                             <String Form=\"Отчет о финансовых результатах\" Section=\"Доходы и расходы по обычным видам деятельности\" Name=\"Управленческие расходы\" Code=\"2220\" Value=\"-7772139000\" IdFinPok=\"112\"/>\n" +
                    "                     </FinPeriod>\n" +
                    "                 </Finance>\n" +
                    "                 <CompanyWithSameInfo>\n" +
                    "                     <TelephoneCount PhoneCode=\"347\" PhoneNumber=\"2358230\">2</TelephoneCount>\n" +
                    "                     <PhoneList>\n" +
                    "                         <PhoneCount Code=\"347\" Number=\"2358230\">2</PhoneCount>\n" +
                    "                         <PhoneCount Code=\"347\" Number=\"2358310\">2</PhoneCount>\n" +
                    "                         <PhoneCount Code=\"347\" Number=\"2425573\">2</PhoneCount>\n" +
                    "                         <PhoneCount Code=\"347\" Number=\"2605699\">2</PhoneCount>\n" +
                    "                         <PhoneCount Code=\"347\" Number=\"2622139\">2</PhoneCount>\n" +
                    "                     </PhoneList>\n" +
                    "                     <AddressCount>6</AddressCount>\n" +
                    "                     <AddressWithoutRoomCount>6</AddressWithoutRoomCount>\n" +
                    "                     <AddressNotAffiliatedCount>1</AddressNotAffiliatedCount>\n" +
                    "                     <AddressFTSCount>1</AddressFTSCount>\n" +
                    "                     <ManagerCountInCountry>2</ManagerCountInCountry>\n" +
                    "                     <ManagerCountInRegion>2</ManagerCountInRegion>\n" +
                    "                     <ManagerInnCount>2</ManagerInnCount>\n" +
                    "                 </CompanyWithSameInfo>\n" +
                    "                 <CompanyLiquidatedWithSameInfo>\n" +
                    "                     <AddressCount>1</AddressCount>\n" +
                    "                     <AddressWithoutRoomCount>1</AddressWithoutRoomCount>\n" +
                    "                 </CompanyLiquidatedWithSameInfo>\n" +
                    "                 <BoardOfDirectors ActualDate=\"2021-12-31\">\n" +
                    "                     <Member>\n" +
                    "                         <Name>Букаев Геннадий Иванович</Name>\n" +
                    "                         <INN>027802668609</INN>\n" +
                    "                         <BirthdayYear>1947</BirthdayYear>\n" +
                    "                         <Position Name=\"Член совета директоров\" Code=\"2\"/>\n" +
                    "                     </Member>\n" +
                    "                     <Member>\n" +
                    "                         <Name>Завалеева Елена Владимировна</Name>\n" +
                    "                         <BirthdayYear>1981</BirthdayYear>\n" +
                    "                         <Position Name=\"Член совета директоров\" Code=\"2\"/>\n" +
                    "                     </Member>\n" +
                    "                     <Member>\n" +
                    "                         <Name>Зелько Рунье</Name>\n" +
                    "                         <Position Name=\"Член совета директоров\" Code=\"2\"/>\n" +
                    "                     </Member>\n" +
                    "                     <Member>\n" +
                    "                         <Name>Касимиро Дидье</Name>\n" +
                    "                         <INN>770601003352</INN>\n" +
                    "                         <BirthdayYear>1966</BirthdayYear>\n" +
                    "                         <Position Name=\"Член совета директоров\" Code=\"2\"/>\n" +
                    "                     </Member>\n" +
                    "                     <Member>\n" +
                    "                         <Name>Латыпов Урал Альфретович</Name>\n" +
                    "                         <BirthdayYear>1972</BirthdayYear>\n" +
                    "                         <Position Name=\"Член совета директоров\" Code=\"2\"/>\n" +
                    "                     </Member>\n" +
                    "                     <Member>\n" +
                    "                         <Name>Назаров Андрей Геннадьевич</Name>\n" +
                    "                         <BirthdayYear>1970</BirthdayYear>\n" +
                    "                         <Position Name=\"Член совета директоров\" Code=\"2\"/>\n" +
                    "                     </Member>\n" +
                    "                     <Member>\n" +
                    "                         <Name>Романов Александр Анатольевич</Name>\n" +
                    "                         <Position Name=\"Член совета директоров\" Code=\"2\"/>\n" +
                    "                     </Member>\n" +
                    "                     <Member>\n" +
                    "                         <Name>Табачников Игорь Борисович</Name>\n" +
                    "                         <Position Name=\"Член совета директоров\" Code=\"2\"/>\n" +
                    "                     </Member>\n" +
                    "                     <Member>\n" +
                    "                         <Name>Хамитов Рустэм Закиевич</Name>\n" +
                    "                         <BirthdayYear>1954</BirthdayYear>\n" +
                    "                         <Position Name=\"Член совета директоров\" Code=\"2\"/>\n" +
                    "                     </Member>\n" +
                    "                     <Member>\n" +
                    "                         <Name>Шишкин Андрей Николаевич</Name>\n" +
                    "                         <INN>770601917412</INN>\n" +
                    "                         <BirthdayYear>1959</BirthdayYear>\n" +
                    "                         <Position Name=\"Член совета директоров\" Code=\"2\"/>\n" +
                    "                     </Member>\n" +
                    "                 </BoardOfDirectors>\n" +
                    "                 <ExecutiveBody ActualDate=\"2021-12-31\">\n" +
                    "                     <Member>\n" +
                    "                         <Name>Татриев Хасан Курейшевич</Name>\n" +
                    "                         <INN>773209571887</INN>\n" +
                    "                         <BirthdayYear>1963</BirthdayYear>\n" +
                    "                         <Position Name=\"Глава коллегиального исполнительного органа\" Code=\"5\"/>\n" +
                    "                     </Member>\n" +
                    "                 </ExecutiveBody>\n" +
                    "                 <PersonsWithoutWarrant ActualDate=\"2022-06-01\">\n" +
                    "                     <Person FIO=\"Лазеев Андрей Николаевич\" Position=\"Генеральный директор\" INN=\"860401000350\" ActualDate=\"2022-06-01\"/>\n" +
                    "                 </PersonsWithoutWarrant>\n" +
                    "                 <StateContracts>\n" +
                    "                     <FederalLaw94>\n" +
                    "                         <Year Year=\"2022\">\n" +
                    "                             <Tenders AdmittedNumber=\"6\" NotAdmittedNumber=\"0\" WinnerNumber=\"6\"/>\n" +
                    "                             <Contracts SignedNumber=\"6\" Sum=\"21207409\"/>\n" +
                    "                         </Year>\n" +
                    "                         <Year Year=\"2021\">\n" +
                    "                             <Tenders AdmittedNumber=\"9\" NotAdmittedNumber=\"0\" WinnerNumber=\"9\"/>\n" +
                    "                             <Contracts SignedNumber=\"9\" Sum=\"19576895\"/>\n" +
                    "                         </Year>\n" +
                    "                         <Year Year=\"2020\">\n" +
                    "                             <Tenders AdmittedNumber=\"3\" NotAdmittedNumber=\"0\" WinnerNumber=\"3\"/>\n" +
                    "                             <Contracts SignedNumber=\"3\" Sum=\"1785483\"/>\n" +
                    "                         </Year>\n" +
                    "                         <Year Year=\"2019\">\n" +
                    "                             <Tenders AdmittedNumber=\"4\" NotAdmittedNumber=\"0\" WinnerNumber=\"4\"/>\n" +
                    "                             <Contracts SignedNumber=\"6\" Sum=\"3748964\"/>\n" +
                    "                         </Year>\n" +
                    "                         <Year Year=\"2018\">\n" +
                    "                             <Tenders AdmittedNumber=\"3\" NotAdmittedNumber=\"0\" WinnerNumber=\"3\"/>\n" +
                    "                             <Contracts SignedNumber=\"1\" Sum=\"712989\"/>\n" +
                    "                         </Year>\n" +
                    "                         <Year Year=\"2017\">\n" +
                    "                             <Tenders AdmittedNumber=\"0\" NotAdmittedNumber=\"0\" WinnerNumber=\"0\"/>\n" +
                    "                             <Contracts SignedNumber=\"2\" Sum=\"320410\"/>\n" +
                    "                         </Year>\n" +
                    "                         <Year Year=\"2016\">\n" +
                    "                             <Tenders AdmittedNumber=\"13\" NotAdmittedNumber=\"0\" WinnerNumber=\"2\"/>\n" +
                    "                             <Contracts SignedNumber=\"0\" Sum=\"0\"/>\n" +
                    "                         </Year>\n" +
                    "                         <Year Year=\"2015\">\n" +
                    "                             <Tenders AdmittedNumber=\"14\" NotAdmittedNumber=\"0\" WinnerNumber=\"11\"/>\n" +
                    "                             <Contracts SignedNumber=\"12\" Sum=\"2918311\"/>\n" +
                    "                         </Year>\n" +
                    "                         <Year Year=\"2014\">\n" +
                    "                             <Tenders AdmittedNumber=\"819\" NotAdmittedNumber=\"0\" WinnerNumber=\"794\"/>\n" +
                    "                             <Contracts SignedNumber=\"793\" Sum=\"346606489\"/>\n" +
                    "                         </Year>\n" +
                    "                         <Year Year=\"2013\">\n" +
                    "                             <Tenders AdmittedNumber=\"2008\" NotAdmittedNumber=\"21\" WinnerNumber=\"1887\"/>\n" +
                    "                             <Contracts SignedNumber=\"1829\" Sum=\"972667694\"/>\n" +
                    "                         </Year>\n" +
                    "                         <Year Year=\"2012\">\n" +
                    "                             <Tenders AdmittedNumber=\"619\" NotAdmittedNumber=\"1\" WinnerNumber=\"608\"/>\n" +
                    "                             <Contracts SignedNumber=\"525\" Sum=\"280338688\"/>\n" +
                    "                         </Year>\n" +
                    "                         <Year Year=\"2011\">\n" +
                    "                             <Tenders AdmittedNumber=\"8\" NotAdmittedNumber=\"0\" WinnerNumber=\"8\"/>\n" +
                    "                             <Contracts SignedNumber=\"7\" Sum=\"6099277\"/>\n" +
                    "                         </Year>\n" +
                    "                     </FederalLaw94>\n" +
                    "                     <FederalLaw223>\n" +
                    "                         <Year Year=\"2018\">\n" +
                    "                             <Tenders AdmittedNumber=\"2\" NotAdmittedNumber=\"0\" WinnerNumber=\"2\"/>\n" +
                    "                             <Contracts SignedNumber=\"2\" Sum=\"1330049\"/>\n" +
                    "                         </Year>\n" +
                    "                         <Year Year=\"2017\">\n" +
                    "                             <Tenders AdmittedNumber=\"42\" NotAdmittedNumber=\"0\" WinnerNumber=\"42\"/>\n" +
                    "                             <Contracts SignedNumber=\"57\" Sum=\"22420591566\"/>\n" +
                    "                         </Year>\n" +
                    "                         <Year Year=\"2016\">\n" +
                    "                             <Tenders AdmittedNumber=\"200\" NotAdmittedNumber=\"30\" WinnerNumber=\"199\"/>\n" +
                    "                             <Contracts SignedNumber=\"183\" Sum=\"111117257377\"/>\n" +
                    "                         </Year>\n" +
                    "                         <Year Year=\"2015\">\n" +
                    "                             <Tenders AdmittedNumber=\"231\" NotAdmittedNumber=\"5\" WinnerNumber=\"216\"/>\n" +
                    "                             <Contracts SignedNumber=\"187\" Sum=\"66365663779\"/>\n" +
                    "                         </Year>\n" +
                    "                         <Year Year=\"2014\">\n" +
                    "                             <Tenders AdmittedNumber=\"203\" NotAdmittedNumber=\"2\" WinnerNumber=\"170\"/>\n" +
                    "                             <Contracts SignedNumber=\"125\" Sum=\"7477541989\"/>\n" +
                    "                         </Year>\n" +
                    "                         <Year Year=\"2013\">\n" +
                    "                             <Tenders AdmittedNumber=\"163\" NotAdmittedNumber=\"3\" WinnerNumber=\"114\"/>\n" +
                    "                             <Contracts SignedNumber=\"65\" Sum=\"507206197\"/>\n" +
                    "                         </Year>\n" +
                    "                         <Year Year=\"2012\">\n" +
                    "                             <Tenders AdmittedNumber=\"16\" NotAdmittedNumber=\"0\" WinnerNumber=\"10\"/>\n" +
                    "                             <Contracts SignedNumber=\"3\" Sum=\"1120480\"/>\n" +
                    "                         </Year>\n" +
                    "                     </FederalLaw223>\n" +
                    "                 </StateContracts>\n" +
                    "                 <ArbitrationCases Total=\"2521\" Considered=\"118\" Appealed=\"9\" DecisionsAndRulings=\"17\" Completed=\"2377\">\n" +
                    "                     <Year Year=\"2022\">\n" +
                    "                         <Plaintiff CasesNumber=\"49\" Sum=\"199342126\"/>\n" +
                    "                         <Defendant CasesNumber=\"26\" Sum=\"195900873\"/>\n" +
                    "                         <ThirdOrOtherPerson CasesNumber=\"8\"/>\n" +
                    "                     </Year>\n" +
                    "                     <Year Year=\"2021\">\n" +
                    "                         <Plaintiff CasesNumber=\"60\" Sum=\"142226249\"/>\n" +
                    "                         <Defendant CasesNumber=\"39\" Sum=\"98065396\"/>\n" +
                    "                         <ThirdOrOtherPerson CasesNumber=\"32\"/>\n" +
                    "                     </Year>\n" +
                    "                     <Year Year=\"2020\">\n" +
                    "                         <Plaintiff CasesNumber=\"61\" Sum=\"627170699\"/>\n" +
                    "                         <Defendant CasesNumber=\"35\" Sum=\"276632849\"/>\n" +
                    "                         <ThirdOrOtherPerson CasesNumber=\"24\"/>\n" +
                    "                     </Year>\n" +
                    "                     <Year Year=\"2019\">\n" +
                    "                         <Plaintiff CasesNumber=\"87\" Sum=\"362939156\"/>\n" +
                    "                         <Defendant CasesNumber=\"61\" Sum=\"1256093923\"/>\n" +
                    "                         <ThirdOrOtherPerson CasesNumber=\"45\"/>\n" +
                    "                     </Year>\n" +
                    "                     <Year Year=\"2018\">\n" +
                    "                         <Plaintiff CasesNumber=\"163\" Sum=\"456659435\"/>\n" +
                    "                         <Defendant CasesNumber=\"71\" Sum=\"284825892\"/>\n" +
                    "                         <ThirdOrOtherPerson CasesNumber=\"40\"/>\n" +
                    "                     </Year>\n" +
                    "                     <Year Year=\"2017\">\n" +
                    "                         <Plaintiff CasesNumber=\"185\" Sum=\"374637327984\"/>\n" +
                    "                         <Defendant CasesNumber=\"133\" Sum=\"331591604956\"/>\n" +
                    "                         <ThirdOrOtherPerson CasesNumber=\"56\"/>\n" +
                    "                     </Year>\n" +
                    "                 </ArbitrationCases>\n" +
                    "                 <ExecutionProceedings Active=\"3\" Executed=\"218\"/>\n" +
                    "             </Report>\n" +
                    "         </Data>\n" +
                    "     </Response>\n" +
                    "     <stat>OK</stat>\n" +
                    " </Extended>\n" +
                    " <Entrepreneur>\n" +
                    "     <msg/>\n" +
                    "     <stat>NON</stat>\n" +
                    " </Entrepreneur>\n" +
                    "</root>\n" +
                    "</soap:Body>\n" +
                    "</soap:Envelope>";



            return ResponseEntity.ok().body(main_resp_2);
        }

    }

    @GetMapping(value = "/Process/Business/Consumer/version10/mi_so_ABS_CLIENT_Acc_Req", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_02_35_CRM(@RequestBody String rq) {
        String IDRequest =extract(rq,"<IDRequest>","</IDRequest>");
        String Customer_ID=extract(rq,"<Customer_ID>","</Customer_ID>");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date= new Date();
        String RequestDate = dateFormat.format(date);
        String IssueDate=extract(rq,"<Date_of_Cert>","</Date_of_Cert>");

        String main_response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                "<soap:Body>\n"+
                " <ABS_CLIENT_Response>\n" +
                "     <ProcessID>CREDIT_CARDS</ProcessID>\n" +
                "     <ProcessName />\n" +
                "     <ABS_CLIENT_Response>\n" +
                "         <IDrequest>"+IDRequest+"_1</IDrequest>\n" +
                "         <RequestDate>"+RequestDate+"</RequestDate>\n" +
                "         <SM1 />\n" +
                "         <Applicant_Info>\n" +
                "             <Applicant>\n" +
                "                 <Customer_ID>"+Customer_ID+"</Customer_ID>\n" +
                "                 <IR_ID>188459923</IR_ID>\n" +
                "                 <Employee>0</Employee>\n" +
                "                 <EmployeeBank>0</EmployeeBank>\n" +
                "                 <CharVip>0</CharVip>\n" +
                "                 <VidCl>Клиент</VidCl>\n" +
                "                 <Gender>Ж</Gender>\n" +
                "                 <BirthPlace>С.САФАРОВО ЧИШМИНСКОГО РАЙОНА Р. БАШКОРТОСТАН</BirthPlace>\n" +
                "                 <IssueDate>"+IssueDate+"</IssueDate>\n" +
                "                 <IssuedBy>КОГАЛЫМСКИМ ГОРОДСКИМ ОТДЕЛОМ ВНУТРЕННИХ ДЕЛ УВД ХАНТЫ-МАНСИЙСКОГО АВТОНОМНОГО ОКРУГА ТЮМЕНСКОЙ ОБЛАСТИ</IssuedBy>\n" +
                "                 <DepCode>862-004</DepCode>\n" +
                "                 <SNILS>12131078395</SNILS>\n" +
                "                 <INN>025000546183</INN>\n" +
                "                 <OID />\n" +
                "                 <IdentSign />\n" +
                "                 <ChangeContactInfoHistory>\n" +
                "                     <ChangeContactInfo>\n" +
                "                         <RetailObj>784266782760</RetailObj>\n" +
                "                         <DateChange>2023-06-30 16:41:22</DateChange>\n" +
                "                         <Manager>КОРБАКОВА ИРИНА ИВАНОВНА</Manager>\n" +
                "                         <Attribute>PHONE 1</Attribute>\n" +
                "                         <OldData />\n" +
                "                         <NewData>9822212476</NewData>\n" +
                "                     </ChangeContactInfo>\n" +
                "                 </ChangeContactInfoHistory>\n" +
                "                 <CategoryClient>PUB</CategoryClient>\n" +
                "                 <CategorySalary />\n" +
                "                 <PacketInfo />\n" +
                "                 <DepositInfo>\n" +
                "                     <AccountTypeDep>Обычный</AccountTypeDep>\n" +
                "                     <AccountNumDep>42301810400990206494</AccountNumDep>\n" +
                "                     <DepositArrest>0</DepositArrest>\n" +
                "                     <VidDogDep>(001) До востребования (RUB)</VidDogDep>\n" +
                "                     <DateBeginDep>2003-12-23 00:00:00</DateBeginDep>\n" +
                "                     <DateEndDep>2013-11-25 00:00:00</DateEndDep>\n" +
                "                     <SumDogDep>405.55</SumDogDep>\n" +
                "                     <PeriodDep>3625</PeriodDep>\n" +
                "                     <CurDep>810</CurDep>\n" +
                "                     <BalanceDep>0.00</BalanceDep>\n" +
                "                     <StateDep>Закрыт</StateDep>\n" +
                "                     <FlagOver>0</FlagOver>\n" +
                "                     <NewCardAccount />\n" +
                "                     <BranchNewCardAccount />\n" +
                "                     <EnrollmentStatusCod>0</EnrollmentStatusCod>\n" +
                "                 </DepositInfo>\n" +
                "                 <DepositInfo>\n" +
                "                     <AccountTypeDep>Обычный</AccountTypeDep>\n" +
                "                     <AccountNumDep>40817810300961003197</AccountNumDep>\n" +
                "                     <DepositArrest>0</DepositArrest>\n" +
                "                     <VidDogDep>(099) Счет для кредита (RUB)</VidDogDep>\n" +
                "                     <DateBeginDep />\n" +
                "                     <DateEndDep />\n" +
                "                     <SumDogDep>0.00</SumDogDep>\n" +
                "                     <PeriodDep />\n" +
                "                     <CurDep>810</CurDep>\n" +
                "                     <BalanceDep>0.00</BalanceDep>\n" +
                "                     <StateDep>Закрыт</StateDep>\n" +
                "                     <FlagOver>0</FlagOver>\n" +
                "                     <NewCardAccount />\n" +
                "                     <BranchNewCardAccount />\n" +
                "                     <EnrollmentStatusCod>1</EnrollmentStatusCod>\n" +
                "                 </DepositInfo>\n" +
                "                 <DepositInfo>\n" +
                "                     <AccountTypeDep>Обычный</AccountTypeDep>\n" +
                "                     <AccountNumDep>40817810500329066811</AccountNumDep>\n" +
                "                     <DepositArrest>0</DepositArrest>\n" +
                "                     <VidDogDep>(001) Карточный вклад (RUB)</VidDogDep>\n" +
                "                     <DateBeginDep>2023-08-11 00:00:00</DateBeginDep>\n" +
                "                     <DateEndDep />\n" +
                "                     <SumDogDep>0.00</SumDogDep>\n" +
                "                     <PeriodDep />\n" +
                "                     <CurDep>810</CurDep>\n" +
                "                     <BalanceDep>0.00</BalanceDep>\n" +
                "                     <StateDep>Работает</StateDep>\n" +
                "                     <FlagOver>1</FlagOver>\n" +
                "                     <NewCardAccount>40817810500329066811</NewCardAccount>\n" +
                "                     <BranchNewCardAccount>001</BranchNewCardAccount>\n" +
                "                     <EnrollmentStatusCod>1</EnrollmentStatusCod>\n" +
                "                 </DepositInfo>\n" +
                "                 <DepositInfo>\n" +
                "                     <AccountTypeDep>Обычный</AccountTypeDep>\n" +
                "                     <AccountNumDep>40817810400329066610</AccountNumDep>\n" +
                "                     <DepositArrest>0</DepositArrest>\n" +
                "                     <VidDogDep>(001) Счет для кредита (RUB)</VidDogDep>\n" +
                "                     <DateBeginDep>2023-08-02 00:00:00</DateBeginDep>\n" +
                "                     <DateEndDep />\n" +
                "                     <SumDogDep>0.00</SumDogDep>\n" +
                "                     <PeriodDep />\n" +
                "                     <CurDep>810</CurDep>\n" +
                "                     <BalanceDep>0.00</BalanceDep>\n" +
                "                     <StateDep>Работает</StateDep>\n" +
                "                     <FlagOver>1</FlagOver>\n" +
                "                     <NewCardAccount>40817810400329066610</NewCardAccount>\n" +
                "                     <BranchNewCardAccount>001</BranchNewCardAccount>\n" +
                "                     <EnrollmentStatusCod>1</EnrollmentStatusCod>\n" +
                "                 </DepositInfo>\n" +
                "                 <DepositInfo>\n" +
                "                     <AccountTypeDep>Обычный</AccountTypeDep>\n" +
                "                     <AccountNumDep>40817810200329066771</AccountNumDep>\n" +
                "                     <DepositArrest>0</DepositArrest>\n" +
                "                     <VidDogDep>(001) Карточный счет ПРИБЫЛЬ NEW (RUB)</VidDogDep>\n" +
                "                     <DateBeginDep>2023-08-02 00:00:00</DateBeginDep>\n" +
                "                     <DateEndDep>2023-09-24 00:00:00</DateEndDep>\n" +
                "                     <SumDogDep>0.00</SumDogDep>\n" +
                "                     <PeriodDep>53</PeriodDep>\n" +
                "                     <CurDep>810</CurDep>\n" +
                "                     <BalanceDep>0.00</BalanceDep>\n" +
                "                     <StateDep>Закрыт</StateDep>\n" +
                "                     <FlagOver>0</FlagOver>\n" +
                "                     <NewCardAccount />\n" +
                "                     <BranchNewCardAccount />\n" +
                "                     <EnrollmentStatusCod>1</EnrollmentStatusCod>\n" +
                "                 </DepositInfo>\n" +
                "                 <DepositInfo>\n" +
                "                     <AccountTypeDep>Обычный</AccountTypeDep>\n" +
                "                     <AccountNumDep>40817810200329066810</AccountNumDep>\n" +
                "                     <DepositArrest>0</DepositArrest>\n" +
                "                     <VidDogDep>(001) Карточный вклад (RUB)</VidDogDep>\n" +
                "                     <DateBeginDep />\n" +
                "                     <DateEndDep />\n" +
                "                     <SumDogDep>599.00</SumDogDep>\n" +
                "                     <PeriodDep>0</PeriodDep>\n" +
                "                     <CurDep>810</CurDep>\n" +
                "                     <BalanceDep>0.00</BalanceDep>\n" +
                "                     <StateDep>Заморожен</StateDep>\n" +
                "                     <FlagOver>0</FlagOver>\n" +
                "                     <NewCardAccount />\n" +
                "                     <BranchNewCardAccount />\n" +
                "                     <EnrollmentStatusCod>1</EnrollmentStatusCod>\n" +
                "                 </DepositInfo>\n" +
                "                 <DepositInfo>\n" +
                "                     <AccountTypeDep>Обычный</AccountTypeDep>\n" +
                "                     <AccountNumDep>40817810400961003194</AccountNumDep>\n" +
                "                     <DepositArrest>0</DepositArrest>\n" +
                "                     <VidDogDep>(099) Счет для кредита (RUB)</VidDogDep>\n" +
                "                     <DateBeginDep />\n" +
                "                     <DateEndDep />\n" +
                "                     <SumDogDep>0.00</SumDogDep>\n" +
                "                     <PeriodDep />\n" +
                "                     <CurDep>810</CurDep>\n" +
                "                     <BalanceDep>0.00</BalanceDep>\n" +
                "                     <StateDep>Закрыт</StateDep>\n" +
                "                     <FlagOver>0</FlagOver>\n" +
                "                     <NewCardAccount />\n" +
                "                     <BranchNewCardAccount />\n" +
                "                     <EnrollmentStatusCod>1</EnrollmentStatusCod>\n" +
                "                 </DepositInfo>\n" +
                "                 <LimitOrders>0</LimitOrders>\n" +
                "                 <CreditInfo>\n" +
                "                     <Credit>\n" +
                "                         <CreditNumDog>0032-2Z3/00035</CreditNumDog>\n" +
                "                         <CreditDogID>798897151281</CreditDogID>\n" +
                "                         <DateBeginCredit>2023-08-02 00:00:00</DateBeginCredit>\n" +
                "                         <TypeClientCredit>1</TypeClientCredit>\n" +
                "                         <DateEndCredit>2024-09-02 00:00:00</DateEndCredit>\n" +
                "                         <NameKindCredit>9#2Z#Потребительский кредит под залог ТС с отлагательным условием</NameKindCredit>\n" +
                "                         <DateCloseCredit>2024-03-29 00:00:00</DateCloseCredit>\n" +
                "                         <LimitCredit>0.0</LimitCredit>\n" +
                "                         <OstSumCredit>0.0</OstSumCredit>\n" +
                "                         <ValutaCred>810</ValutaCred>\n" +
                "                         <PrcCredit>5.9</PrcCredit>\n" +
                "                         <ProsrSumCredit>0.0</ProsrSumCredit>\n" +
                "                         <StatusCredit>13</StatusCredit>\n" +
                "                         <PaySummaCredit>0.0</PaySummaCredit>\n" +
                "                         <DateLastPay>2024-03-29 00:00:00</DateLastPay>\n" +
                "                         <AccountCredit>45506810300321001296</AccountCredit>\n" +
                "                         <UidCreditDog>7956b568-8060-1021-80be-a5dcbc70a03d-9</UidCreditDog>\n" +
                "                         <VidProduct>KRED_PERS_NK</VidProduct>\n" +
                "                         <PacketType />\n" +
                "                         <AnonymCard />\n" +
                "                         <FlagGP>0</FlagGP>\n" +
                "                         <Cobrand />\n" +
                "                         <CardPaySystem />\n" +
                "                         <PlasticType />\n" +
                "                         <SpecialOffer />\n" +
                "                         <CardAccount />\n" +
                "                         <BranchCardAccount />\n" +
                "                         <DateReport>"+RequestDate+"</DateReport>\n" +
                "                         <MannerOfPayment>111E1100</MannerOfPayment>\n" +
                "                         <MaxSumDelay>51720.00</MaxSumDelay>\n" +
                "                         <DateUnloading>2024-03-29 00:00:00</DateUnloading>\n" +
                "                         <ZalogInfo />\n" +
                "                     </Credit>\n" +
                "                 </CreditInfo>\n" +
                "                 <ClientSegment>Mass Retail</ClientSegment>\n" +
                "                 <ClientSegmentFAS>Mass</ClientSegmentFAS>\n" +
                "                 <ResourcePortfolio>0</ResourcePortfolio>\n" +
                "             </Applicant>\n" +
                "         </Applicant_Info>\n" +
                "     </ABS_CLIENT_Response>\n" +
                "     <ResultMsg>\n" +
                "         <ResultCode>1</ResultCode>\n" +
                "         <ResultMessage>OK. </ResultMessage>\n" +
                "     </ResultMsg>\n" +
                " </ABS_CLIENT_Response>\n" +
                "</soap:Body>\n" +
                "</soap:Envelope>";

        //новый ответ от Тибко
        String main_resp_2="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ServiceResponse>\n" +
                "<IDrequest>"+IDRequest+"</IDrequest>\n" +
                "<ReguestDate>"+RequestDate+"</ReguestDate>\n" +
                "<SM1/>\n" +
                "<Applicants_Info>\n" +
                "<Applicant>\n" +
                "<Customer_ID>"+Customer_ID+"</Customer_ID>\n" +
                "<IR_ID>344286603158</IR_ID>\n" +
                "<Employee>0</Employee>\n" +
                "<EmployeeBank>0</EmployeeBank>\n" +
                "<CharVip>0</CharVip>\n" +
                "<VidCl>Клиент</VidCl>\n" +
                "<Gender>Ж</Gender>\n" +
                "<BirthPlace>Д.ЧУЛПАН ШАРАНСКОГО РАЙОНА РЕСП. БАШКОРТОСТАН</BirthPlace>\n" +
                "<IssueDate>"+IssueDate+"</IssueDate>\n" +
                "<IssuedBy>ОТДЕЛЕНИЕМ УФМС РОССИИ ПО РЕСПУБЛИКЕ БАШКОРТОСТАН В ШАРАНСКОМ РАЙОНЕ</IssuedBy>\n" +
                "<DepCode>020-069</DepCode>\n" +
                "<SNILS>10665093650</SNILS>\n" +
                "<INN>025102166818</INN>\n" +
                "<OID/>\n" +
                "<IdentSign/>\n" +
                "<CategoryClient>SAL</CategoryClient>\n" +
                "<CategorySalary>Standart; </CategorySalary>\n" +
                "<SalaryInfo>\n" +
                "<Salary>\n" +
                "<CodeSalary>KKPP</CodeSalary>\n" +
                "<NameOrgSalary>МКУ ЦБ МУО Советского района г.Уфы(родители)</NameOrgSalary>\n" +
                "<INNSalary>0278117795</INNSalary>\n" +
                "<NumberOrgPhone>3472511843</NumberOrgPhone>\n" +
                "<PayrollOrgType>BUDGET</PayrollOrgType>\n" +
                "<StateSalary>Работает</StateSalary>\n" +
                "<DateLinkSalary>2018-01-16 00:00:00</DateLinkSalary>\n" +
                "<DateDisconSalary>2021-12-01 00:00:00</DateDisconSalary>\n" +
                "<Promised_salary/>\n" +
                "<PrimarySalary>1</PrimarySalary>\n" +
                "<BudgetCorp>0</BudgetCorp>\n" +
                "<ALLpayments/>\n" +
                "<AgrPayments>\n" +
                "<AgrPayment>\n" +
                "<DateBeginPer>2023-08-01 00:00:00</DateBeginPer>\n" +
                "<DateEndPer>2023-08-31 00:00:00</DateEndPer>\n" +
                "<SumAllTrans>0.00</SumAllTrans>\n" +
                "</AgrPayment>\n" +
                "<AgrPayment>\n" +
                "<DateBeginPer>2023-09-01 00:00:00</DateBeginPer>\n" +
                "<DateEndPer>2023-09-30 00:00:00</DateEndPer>\n" +
                "<SumAllTrans>0.00</SumAllTrans>\n" +
                "</AgrPayment>\n" +
                "<AgrPayment>\n" +
                "<DateBeginPer>2023-10-01 00:00:00</DateBeginPer>\n" +
                "<DateEndPer>2023-10-31 00:00:00</DateEndPer>\n" +
                "<SumAllTrans>0.00</SumAllTrans>\n" +
                "</AgrPayment>\n" +
                "<AgrPayment>\n" +
                "<DateBeginPer>2023-11-01 00:00:00</DateBeginPer>\n" +
                "<DateEndPer>2023-11-30 00:00:00</DateEndPer>\n" +
                "<SumAllTrans>0.00</SumAllTrans>\n" +
                "</AgrPayment>\n" +
                "<AgrPayment>\n" +
                "<DateBeginPer>2023-12-01 00:00:00</DateBeginPer>\n" +
                "<DateEndPer>2023-12-31 00:00:00</DateEndPer>\n" +
                "<SumAllTrans>0.00</SumAllTrans>\n" +
                "</AgrPayment>\n" +
                "<AgrPayment>\n" +
                "<DateBeginPer>2024-01-01 00:00:00</DateBeginPer>\n" +
                "<DateEndPer>2024-01-31 00:00:00</DateEndPer>\n" +
                "<SumAllTrans>0.00</SumAllTrans>\n" +
                "</AgrPayment>\n" +
                "<AgrPayment>\n" +
                "<DateBeginPer>2024-02-01 00:00:00</DateBeginPer>\n" +
                "<DateEndPer>2024-02-29 00:00:00</DateEndPer>\n" +
                "<SumAllTrans>0.00</SumAllTrans>\n" +
                "</AgrPayment>\n" +
                "<AgrPayment>\n" +
                "<DateBeginPer>2024-03-01 00:00:00</DateBeginPer>\n" +
                "<DateEndPer>2024-03-31 00:00:00</DateEndPer>\n" +
                "<SumAllTrans>0.00</SumAllTrans>\n" +
                "</AgrPayment>\n" +
                "<AgrPayment>\n" +
                "<DateBeginPer>2024-04-01 00:00:00</DateBeginPer>\n" +
                "<DateEndPer>2024-04-30 00:00:00</DateEndPer>\n" +
                "<SumAllTrans>0.00</SumAllTrans>\n" +
                "</AgrPayment>\n" +
                "<AgrPayment>\n" +
                "<DateBeginPer>2024-05-01 00:00:00</DateBeginPer>\n" +
                "<DateEndPer>2024-05-31 00:00:00</DateEndPer>\n" +
                "<SumAllTrans>0.00</SumAllTrans>\n" +
                "</AgrPayment>\n" +
                "<AgrPayment>\n" +
                "<DateBeginPer>2024-06-01 00:00:00</DateBeginPer>\n" +
                "<DateEndPer>2024-06-30 00:00:00</DateEndPer>\n" +
                "<SumAllTrans>0.00</SumAllTrans>\n" +
                "</AgrPayment>\n" +
                "<AgrPayment>\n" +
                "<DateBeginPer>2024-07-01 00:00:00</DateBeginPer>\n" +
                "<DateEndPer>2024-07-31 00:00:00</DateEndPer>\n" +
                "<SumAllTrans>0.00</SumAllTrans>\n" +
                "</AgrPayment>\n" +
                "</AgrPayments>\n" +
                "</Salary>\n" +
                "</SalaryInfo>\n" +
                "<EmployerInfoOneClick/>\n" +
                "<DepositInfo>\n" +
                "<Deposit>\n" +
                "<AccountTypeDep>Обычный</AccountTypeDep>\n" +
                "<AccountNumDep>40817810900829436460</AccountNumDep>\n" +
                "<DepositArrest>0</DepositArrest>\n" +
                "<VidDogDep>(001) Счет для кредита (RUB)</VidDogDep>\n" +
                "<DateBeginDep>2023-02-07 00:00:00</DateBeginDep>\n" +
                "<DateEndDep/>\n" +
                "<SumDogDep>0.00</SumDogDep>\n" +
                "<PeriodDep/>\n" +
                "<CurDep>810</CurDep>\n" +
                "<BalanceDep>0.40</BalanceDep>\n" +
                "<StateDep>Работает</StateDep>\n" +
                "<FlagOver>1</FlagOver>\n" +
                "<NewCardAccount>40817810900829436460</NewCardAccount>\n" +
                "<BranchNewCardAccount>001</BranchNewCardAccount>\n" +
                "<EnrollmentStatusCod>1</EnrollmentStatusCod>\n" +
                "</Deposit>\n" +
                "<Deposit>\n" +
                "<AccountTypeDep>Обычный</AccountTypeDep>\n" +
                "<AccountNumDep>40817810700019314259</AccountNumDep>\n" +
                "<DepositArrest>0</DepositArrest>\n" +
                "<VidDogDep>(001) Текущий банковский счет (RUB)</VidDogDep>\n" +
                "<DateBeginDep>2022-03-23 00:00:00</DateBeginDep>\n" +
                "<DateEndDep>2023-04-24 00:00:00</DateEndDep>\n" +
                "<SumDogDep>0.00</SumDogDep>\n" +
                "<PeriodDep>397</PeriodDep>\n" +
                "<CurDep>810</CurDep>\n" +
                "<BalanceDep>0.00</BalanceDep>\n" +
                "<StateDep>Закрыт</StateDep>\n" +
                "<FlagOver>0</FlagOver>\n" +
                "<NewCardAccount/>\n" +
                "<BranchNewCardAccount/>\n" +
                "<EnrollmentStatusCod>1</EnrollmentStatusCod>\n" +
                "</Deposit>\n" +
                "<Deposit>\n" +
                "<AccountTypeDep>Обычный</AccountTypeDep>\n" +
                "<AccountNumDep>40817810800019281017</AccountNumDep>\n" +
                "<DepositArrest>0</DepositArrest>\n" +
                "<VidDogDep>(001) Карточный вклад (RUB)</VidDogDep>\n" +
                "<DateBeginDep>2018-01-16 00:00:00</DateBeginDep>\n" +
                "<DateEndDep/>\n" +
                "<SumDogDep>0.00</SumDogDep>\n" +
                "<PeriodDep/>\n" +
                "<CurDep>810</CurDep>\n" +
                "<BalanceDep>200.50</BalanceDep>\n" +
                "<StateDep>Работает</StateDep>\n" +
                "<FlagOver>1</FlagOver>\n" +
                "<NewCardAccount>40817810800019281017</NewCardAccount>\n" +
                "<BranchNewCardAccount>001</BranchNewCardAccount>\n" +
                "<EnrollmentStatusCod>1</EnrollmentStatusCod>\n" +
                "</Deposit>\n" +
                "<Deposit>\n" +
                "<AccountTypeDep>Обычный</AccountTypeDep>\n" +
                "<AccountNumDep>42304810200010003350</AccountNumDep>\n" +
                "<DepositArrest>0</DepositArrest>\n" +
                "<VidDogDep>(001) Прибыльный сезон 91 (RUB)</VidDogDep>\n" +
                "<DateBeginDep>2022-03-23 00:00:00</DateBeginDep>\n" +
                "<DateEndDep>2022-03-23 00:00:00</DateEndDep>\n" +
                "<SumDogDep>1093325.00</SumDogDep>\n" +
                "<PeriodDep>0</PeriodDep>\n" +
                "<CurDep>810</CurDep>\n" +
                "<BalanceDep>0.00</BalanceDep>\n" +
                "<StateDep>Закрыт</StateDep>\n" +
                "<FlagOver>0</FlagOver>\n" +
                "<NewCardAccount/>\n" +
                "<BranchNewCardAccount/>\n" +
                "</Deposit>\n" +
                "</DepositInfo>\n" +
                "<LimitOrders>0</LimitOrders>\n" +
                "<CreditInfo>\n" +
                "<Credit>\n" +
                "<CreditNumDog>0082-R83/02714</CreditNumDog>\n" +
                "<CreditDogID>719406847393</CreditDogID>\n" +
                "<DateBeginCredit>2023-02-07 00:00:00</DateBeginCredit>\n" +
                "<TypeClientCredit>Заемщик</TypeClientCredit>\n" +
                "<DateEndCredit>2053-02-07 00:00:00</DateEndCredit>\n" +
                "<NameKindCredit>6#R8#Ипотека. Строящееся жилье (стандарт АИЖК)</NameKindCredit>\n" +
                "<DateCloseCredit>2023-04-17 00:00:00</DateCloseCredit>\n" +
                "<LimitCredit>0</LimitCredit>\n" +
                "<OstSumCredit>0</OstSumCredit>\n" +
                "<ValutaCred>810</ValutaCred>\n" +
                "<PrcCredit>5.59</PrcCredit>\n" +
                "<ProsrSumCredit>0</ProsrSumCredit>\n" +
                "<StatusCredit>Закрыт</StatusCredit>\n" +
                "<PaySummaCredit>0</PaySummaCredit>\n" +
                "<DateLastPay>2023-04-17 00:00:00</DateLastPay>\n" +
                "<AccountCredit>45507810100821016605</AccountCredit>\n" +
                "<UidCreditDog>906b2099-48ca-19b7-8021-5c2437fd4a99-8</UidCreditDog>\n" +
                "<VidProduct>KRED_PERS_NK</VidProduct>\n" +
                "<PacketType/>\n" +
                "<AnonymCard/>\n" +
                "<FlagGP>0</FlagGP>\n" +
                "<Cobrand/>\n" +
                "<CardPaySystem/>\n" +
                "<PlasticType/>\n" +
                "<SpecialOffer/>\n" +
                "<CardAccount/>\n" +
                "<BranchCardAccount/>\n" +
                "<DateReport>"+RequestDate+"</DateReport>\n" +
                "<MannerOfPayment>100</MannerOfPayment>\n" +
                "<MaxSumDelay>0.00</MaxSumDelay>\n" +
                "<DateUnloading>2023-04-17 00:00:00</DateUnloading>\n" +
                "<ZalogInfo>\n" +
                "<Zalog>\n" +
                "<TypeClientZalog>Залогодатель</TypeClientZalog>\n" +
                "<ZalogNumDog>0082-R83/02714/0901</ZalogNumDog>\n" +
                "<ValutaZalog>810</ValutaZalog>\n" +
                "<DateBeginZalog>2023-02-07 00:00:00</DateBeginZalog>\n" +
                "<DateEndZalog>2053-02-07 00:00:00</DateEndZalog>\n" +
                "<DateCloseZalog>2023-04-17 00:00:00</DateCloseZalog>\n" +
                "<StatusZalog>Закрыт</StatusZalog>\n" +
                "</Zalog>\n" +
                "</ZalogInfo>\n" +
                "</Credit>\n" +
                "<Credit>\n" +
                "<CreditNumDog>0082-R83/02768</CreditNumDog>\n" +
                "<CreditDogID>749782755638</CreditDogID>\n" +
                "<DateBeginCredit>2023-04-17 00:00:00</DateBeginCredit>\n" +
                "<TypeClientCredit/>\n" +
                "<DateEndCredit>2053-04-17 00:00:00</DateEndCredit>\n" +
                "<NameKindCredit>6#R8#Ипотека. Строящееся жилье (стандарт АИЖК)</NameKindCredit>\n" +
                "<DateCloseCredit>2023-11-23 00:00:00</DateCloseCredit>\n" +
                "<LimitCredit>0</LimitCredit>\n" +
                "<OstSumCredit>0</OstSumCredit>\n" +
                "<ValutaCred>810</ValutaCred>\n" +
                "<PrcCredit>5.59</PrcCredit>\n" +
                "<ProsrSumCredit>0</ProsrSumCredit>\n" +
                "<StatusCredit>Закрыт</StatusCredit>\n" +
                "<PaySummaCredit>0</PaySummaCredit>\n" +
                "<DateLastPay>2023-11-23 00:00:00</DateLastPay>\n" +
                "<AccountCredit>45507810800821017205</AccountCredit>\n" +
                "<UidCreditDog>3800079e-a17b-1fd9-907e-d917c29d4891-9</UidCreditDog>\n" +
                "<VidProduct>KRED_PERS_NK</VidProduct>\n" +
                "<PacketType/>\n" +
                "<AnonymCard/>\n" +
                "<FlagGP>0</FlagGP>\n" +
                "<Cobrand/>\n" +
                "<CardPaySystem/>\n" +
                "<PlasticType/>\n" +
                "<SpecialOffer/>\n" +
                "<CardAccount/>\n" +
                "<BranchCardAccount/>\n" +
                "<DateReport>"+RequestDate+" 16:12:30</DateReport>\n" +
                "<MannerOfPayment>1111100</MannerOfPayment>\n" +
                "<MaxSumDelay>0.00</MaxSumDelay>\n" +
                "<DateUnloading>2023-11-23 00:00:00</DateUnloading>\n" +
                "<ZalogInfo>\n" +
                "<Zalog>\n" +
                "<TypeClientZalog>Поручитель</TypeClientZalog>\n" +
                "<ZalogNumDog>0082-R83/02768/0102</ZalogNumDog>\n" +
                "<ValutaZalog>810</ValutaZalog>\n" +
                "<DateBeginZalog>2023-04-17 00:00:00</DateBeginZalog>\n" +
                "<DateEndZalog>2056-04-17 00:00:00</DateEndZalog>\n" +
                "<DateCloseZalog>2023-04-27 00:00:00</DateCloseZalog>\n" +
                "<StatusZalog>Закрыт</StatusZalog>\n" +
                "</Zalog>\n" +
                "</ZalogInfo>\n" +
                "</Credit>\n" +
                "</CreditInfo>\n" +
                "<PacketInfo/>\n" +
                "<ClientSegment>Mass Retail</ClientSegment>\n" +
                "<ClientSegmentFAS>Mass</ClientSegmentFAS>\n" +
                "<ResourcePortfolio>200.9</ResourcePortfolio>\n" +
                "</Applicant>\n" +
                "</Applicants_Info>\n" +
                "</ServiceResponse>";
        return ResponseEntity.ok().body(main_resp_2);
    }

    @PostMapping(value = "/Service/ProductCatalogueRomashka_Service.serviceagent/ProductCatalogueEndpoint0", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_01_05_GetTariffs(@RequestBody String rq){
        //===========================================LOG
        try {
            Logger logger = Logger.getLogger("MyLog_01_05");
            FileHandler fh2 = new FileHandler("C:/Program Files/Apache Software Foundation/Tomcat 9.0/logs/MyLogFile01_05.log");
            logger.addHandler(fh2);
            SimpleFormatter l_formatter = new SimpleFormatter();
            fh2.setFormatter(l_formatter);
            logger.info("=========================Request body:===================================");
            logger.info(rq);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //==============================================


        String ApplicationID=extract(rq,"<crifSch:ApplicationID>","</crifSch:ApplicationID>");
        //String CurrentDate=extract(rq,"<CurrentDate>","</CurrentDate>");

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");//Current YYYY-MM-dd
        String CurrentDate = dateFormat.format(date);
        int[] n={1,3,3,5};
        Random rand = new Random();
        int sec=n[rand.nextInt(n.length)];
        long timer=sec*1000;
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String main_response="<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "   <SOAP-ENV:Body>\n" +
                "      <ns0:ProductCatalogueInquiryResponse xmlns:ns0=\"http://www.tibco.com/schemas/CRIF_PRODCAT_R/ProductCatalogueSchemaRomashka/Schema.xsd\">\n" +
                "         <ns0:ProcessID>CREDIT_CARDS</ns0:ProcessID>\n" +
                "         <ns0:ProcessName>CRDS</ns0:ProcessName>\n" +
                "         <ns0:ProductCatalogueResponse>\n" +
                "            <ns0:ApplicationID>"+ApplicationID+"</ns0:ApplicationID>\n" +
                "            <ns0:Tariffs>\n" +
                "               <ns0:Tariff>\n" +
                "                  <ns0:TariffID>110</ns0:TariffID>\n" +
                "                  <ns0:TariffOptions>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>TARIFFNAME</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>ТП 120 дней на максимум</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>TARIFFTYPE</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>ST</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>CURRENCY</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>810</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>MIN_RATE</ns0:NAME>\n" +
                "                        <ns0:TYPE>FLOAT</ns0:TYPE>\n" +
                "                        <ns0:VALUE>20.9</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>MAX_RATE</ns0:NAME>\n" +
                "                        <ns0:TYPE>FLOAT</ns0:TYPE>\n" +
                "                        <ns0:VALUE>20.9</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>PRODUCT_TYPE</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>647</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>MIN_LIMIT</ns0:NAME>\n" +
                "                        <ns0:TYPE>FLOAT</ns0:TYPE>\n" +
                "                        <ns0:VALUE>9999</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>MAX_LIMIT</ns0:NAME>\n" +
                "                        <ns0:TYPE>FLOAT</ns0:TYPE>\n" +
                "                        <ns0:VALUE>1000000</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>PENAMOUNT</ns0:NAME>\n" +
                "                        <ns0:TYPE>FLOAT</ns0:TYPE>\n" +
                "                        <ns0:VALUE>0.05</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>PENDAYAMOUNT</ns0:NAME>\n" +
                "                        <ns0:TYPE>FLOAT</ns0:TYPE>\n" +
                "                        <ns0:VALUE>0.05</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>DELIQDAYPR</ns0:NAME>\n" +
                "                        <ns0:TYPE>FLOAT</ns0:TYPE>\n" +
                "                        <ns0:VALUE>0.054</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>REQUIREMENT</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>standart</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>VARSTR3</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>t</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>BASIC_RATE</ns0:NAME>\n" +
                "                        <ns0:TYPE>FLOAT</ns0:TYPE>\n" +
                "                        <ns0:VALUE>20.9</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>PACKAGE</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>ALONE</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>PAY_SYSTEM</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>MIR</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>COBRAND</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>CRED_CARD_PINSET_GP120</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>CATEGORY_CARD</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>Privilege Plus</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>SPECIAL_CODE</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>11</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>INSTANT</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>1</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>ATMRATE</ns0:NAME>\n" +
                "                        <ns0:TYPE>FLOAT</ns0:TYPE>\n" +
                "                        <ns0:VALUE>39.7</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                  </ns0:TariffOptions>\n" +
                "               </ns0:Tariff>\n" +
                "               <ns0:Tariff>\n" +
                "                  <ns0:TariffID>118</ns0:TariffID>\n" +
                "                  <ns0:TariffOptions>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>TARIFFNAME</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>ТП Премиальная кредитная карта</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>TARIFFTYPE</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>ST</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>CURRENCY</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>810</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>MIN_RATE</ns0:NAME>\n" +
                "                        <ns0:TYPE>FLOAT</ns0:TYPE>\n" +
                "                        <ns0:VALUE>29.9</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>MAX_RATE</ns0:NAME>\n" +
                "                        <ns0:TYPE>FLOAT</ns0:TYPE>\n" +
                "                        <ns0:VALUE>29.9</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>PRODUCT_TYPE</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>647</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>MIN_LIMIT</ns0:NAME>\n" +
                "                        <ns0:TYPE>FLOAT</ns0:TYPE>\n" +
                "                        <ns0:VALUE>299000</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>MAX_LIMIT</ns0:NAME>\n" +
                "                        <ns0:TYPE>FLOAT</ns0:TYPE>\n" +
                "                        <ns0:VALUE>1500000</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>PENAMOUNT</ns0:NAME>\n" +
                "                        <ns0:TYPE>FLOAT</ns0:TYPE>\n" +
                "                        <ns0:VALUE>0.05</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>PENDAYAMOUNT</ns0:NAME>\n" +
                "                        <ns0:TYPE>FLOAT</ns0:TYPE>\n" +
                "                        <ns0:VALUE>0.05</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>DELIQDAYPR</ns0:NAME>\n" +
                "                        <ns0:TYPE>FLOAT</ns0:TYPE>\n" +
                "                        <ns0:VALUE>0.054</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>REQUIREMENT</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>standart</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>BASIC_RATE</ns0:NAME>\n" +
                "                        <ns0:TYPE>FLOAT</ns0:TYPE>\n" +
                "                        <ns0:VALUE>29.9</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>PACKAGE</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>ALONE</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>PAY_SYSTEM</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>MIR</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>COBRAND</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>PREMIUM_CREDIT_CARD</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>CATEGORY_CARD</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>MIR Supreme</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>INSTANT</ns0:NAME>\n" +
                "                        <ns0:TYPE>STRING</ns0:TYPE>\n" +
                "                        <ns0:VALUE>1</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                     <ns0:TariffOption>\n" +
                "                        <ns0:NAME>ATMRATE</ns0:NAME>\n" +
                "                        <ns0:TYPE>FLOAT</ns0:TYPE>\n" +
                "                        <ns0:VALUE>39.7</ns0:VALUE>\n" +
                "                     </ns0:TariffOption>\n" +
                "                  </ns0:TariffOptions>\n" +
                "               </ns0:Tariff>\n" +
                "            </ns0:Tariffs>\n" +
                "            <ns0:RatesOfFunding>\n" +
                "               <ns0:Rates>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.075</ns0:Rate>\n" +
                "                     <ns0:Minterm>0.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>1.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.075</ns0:Rate>\n" +
                "                     <ns0:Minterm>1.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>2.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.076</ns0:Rate>\n" +
                "                     <ns0:Minterm>2.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>3.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.0773</ns0:Rate>\n" +
                "                     <ns0:Minterm>3.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>4.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.0787</ns0:Rate>\n" +
                "                     <ns0:Minterm>4.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>5.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.08</ns0:Rate>\n" +
                "                     <ns0:Minterm>5.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>6.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.0806</ns0:Rate>\n" +
                "                     <ns0:Minterm>6.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>7.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.0813</ns0:Rate>\n" +
                "                     <ns0:Minterm>7.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>8.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.082</ns0:Rate>\n" +
                "                     <ns0:Minterm>8.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>9.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.0827</ns0:Rate>\n" +
                "                     <ns0:Minterm>9.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>10.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.0833</ns0:Rate>\n" +
                "                     <ns0:Minterm>10.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>11.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.084</ns0:Rate>\n" +
                "                     <ns0:Minterm>11.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>12.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.084</ns0:Rate>\n" +
                "                     <ns0:Minterm>12.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>18.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.085</ns0:Rate>\n" +
                "                     <ns0:Minterm>18.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>24.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.0855</ns0:Rate>\n" +
                "                     <ns0:Minterm>24.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>30.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.086</ns0:Rate>\n" +
                "                     <ns0:Minterm>30.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>36.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.088</ns0:Rate>\n" +
                "                     <ns0:Minterm>36.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>60.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.088</ns0:Rate>\n" +
                "                     <ns0:Minterm>60.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>84.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.088</ns0:Rate>\n" +
                "                     <ns0:Minterm>84.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>120.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.088</ns0:Rate>\n" +
                "                     <ns0:Minterm>120.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>180.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.088</ns0:Rate>\n" +
                "                     <ns0:Minterm>180.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>240.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "                  <ns0:Rate>\n" +
                "                     <ns0:CurrencyID>810</ns0:CurrencyID>\n" +
                "                     <ns0:Rate>0.088</ns0:Rate>\n" +
                "                     <ns0:Minterm>240.0</ns0:Minterm>\n" +
                "                     <ns0:Maxterm>360.0</ns0:Maxterm>\n" +
                "                  </ns0:Rate>\n" +
                "               </ns0:Rates>\n" +
                "               <ns0:CreateDate>2018-09-25T23:02:12+03:00</ns0:CreateDate>\n" +
                "            </ns0:RatesOfFunding>\n" +
                "            <ns0:ExchangeRateList>\n" +
                "               <ns0:ExchangeRate>\n" +
                "                  <ns0:Currency>840</ns0:Currency>\n" +
                "                  <ns0:Rate>74.2529</ns0:Rate>\n" +
                "                  <ns0:Rate_Date>2020-12-07</ns0:Rate_Date>\n" +
                "               </ns0:ExchangeRate>\n" +
                "               <ns0:ExchangeRate>\n" +
                "                  <ns0:Currency>978</ns0:Currency>\n" +
                "                  <ns0:Rate>90.2618</ns0:Rate>\n" +
                "                  <ns0:Rate_Date>2020-12-07</ns0:Rate_Date>\n" +
                "               </ns0:ExchangeRate>\n" +
                "               <ns0:ExchangeRate>\n" +
                "                  <ns0:Currency>810</ns0:Currency>\n" +
                "                  <ns0:Rate>1</ns0:Rate>\n" +
                "                  <ns0:Rate_Date>"+CurrentDate+"</ns0:Rate_Date>\n" +
                "               </ns0:ExchangeRate>\n" +
                "            </ns0:ExchangeRateList>\n" +
                "            <ns0:RegionParameters>\n" +
                "               <ns0:Customer_ID>224848011</ns0:Customer_ID>\n" +
                "               <ns0:Min_Living_Cost_in_Region_Living_RUR>8338</ns0:Min_Living_Cost_in_Region_Living_RUR>\n" +
                "               <ns0:Average_Wage_in_Region_of_Employer_RUR>20000</ns0:Average_Wage_in_Region_of_Employer_RUR>\n" +
                "            </ns0:RegionParameters>\n" +
                "            <ns0:PSKLimits>\n" +
                "               <ns0:PSKLimit>\n" +
                "                  <ns0:MAX_PSK>29.088</ns0:MAX_PSK>\n" +
                "                  <ns0:MINLIMIT>0.0</ns0:MINLIMIT>\n" +
                "                  <ns0:MAXLIMIT>29999.0</ns0:MAXLIMIT>\n" +
                "               </ns0:PSKLimit>\n" +
                "               <ns0:PSKLimit>\n" +
                "                  <ns0:MAX_PSK>29.7</ns0:MAX_PSK>\n" +
                "                  <ns0:MINLIMIT>30000.0</ns0:MINLIMIT>\n" +
                "                  <ns0:MAXLIMIT>99999.0</ns0:MAXLIMIT>\n" +
                "               </ns0:PSKLimit>\n" +
                "               <ns0:PSKLimit>\n" +
                "                  <ns0:MAX_PSK>29.7</ns0:MAX_PSK>\n" +
                "                  <ns0:MINLIMIT>100000.0</ns0:MINLIMIT>\n" +
                "                  <ns0:MAXLIMIT>299999.0</ns0:MAXLIMIT>\n" +
                "               </ns0:PSKLimit>\n" +
                "               <ns0:PSKLimit>\n" +
                "                  <ns0:MAX_PSK>29.075</ns0:MAX_PSK>\n" +
                "                  <ns0:MINLIMIT>300000.0</ns0:MINLIMIT>\n" +
                "                  <ns0:MAXLIMIT>9999999.0</ns0:MAXLIMIT>\n" +
                "               </ns0:PSKLimit>\n" +
                "            </ns0:PSKLimits>\n" +
                "         </ns0:ProductCatalogueResponse>\n" +
                "         <ns0:ResultMsg>\n" +
                "            <ns0:ResultCode>1</ns0:ResultCode>\n" +
                "            <ns0:ResultMessage>OK ( Записи найдены )</ns0:ResultMessage>\n" +
                "         </ns0:ResultMsg>\n" +
                "         <ns0:CurrentDate>"+CurrentDate+"</ns0:CurrentDate>\n" +
                "      </ns0:ProductCatalogueInquiryResponse>\n" +
                "</SOAP-ENV:Body>\n" +
                "</SOAP-ENV:Envelope>";

        return ResponseEntity.ok().body(main_response);
    }

    @PostMapping (value = "/Get_CreditHistory", produces = "multipart/form-data;charset=UTF-8")//в комментариях приписка "плохой"
    public ResponseEntity DS_02_05_ExternalCH(@RequestBody String rq){ //тут же и DS_06_10_NationalHunterAndFPS и DS_61_15_CallBIAS
        String configId=rq.substring(rq.indexOf("configId=\"") + "configId=\"".length(), rq.indexOf("\" soapAction"));
        String main_response="";
        String main_resp_2="";

        Locale locale=Locale.US;
        DateFormat dateFormat = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'",locale);
        Date date= new Date();
        String LONG_DATA = dateFormat.format(date);
        if (configId.equals("_NhChAsync")) { //DS_06_10_NationalHunterAndFPS и DS_02_05_ExternalCH

            String APPLICATION_UID=extract(rq,"<crifSch:uidApplication>","</crifSch:uidApplication>");

            main_response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                    "<soap:Body>"+
                    //"<ConnectorOutput>\n" +
                    " <ResultMsg>\n" +
                    "     <ResultCode>1</ResultCode>\n" +
                    "     <ResultMessage />\n" +
                    " </ResultMsg>\n" +
                    //"</ConnectorOutput>"/*+
                    "</soap:Body>\n" +
                    "</soap:Envelope>";


            main_resp_2="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "<SOAP-ENV:Body>\n" +
                    "<outputMessage><ns3:ProcessRequestOut xmlns:ns2=\"http://connector.xws.mbtc.ru\" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns3=\"http://creditregistry.ru/2010/webservice/SingleFormatService\"><cacheUse xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/><created>2024-07-22T11:15:35</created><reportCode>26182092:1402913613</reportCode><response><?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<SINGLE_FORMAT>\n" +
                    " <MAIN>\n" +
                    "  <INFO_SOURCES>\n" +
                    "<SOURCE>NBCH</SOURCE>\n" +
                    "<SOURCE>EI</SOURCE>\n" +
                    "  </INFO_SOURCES>\n" +
                    "  <CREATE_DATETIME>22072024111535</CREATE_DATETIME>\n" +
                    "  <UID>41329f89-2f2c-47ca-af74-b341e4a1c09a</UID>\n" +
                    "  <APPLICATION_UID>"+APPLICATION_UID+"</APPLICATION_UID>\n" +
                    "  <APPLICANT_ID>224846252</APPLICANT_ID>\n" +
                    "  <GROUP_ID>26182092</GROUP_ID>\n" +
                    "  <CONNECTOR_DATA_DETAILS>\n" +
                    "<CONNECTOR_DATA_DETAIL>\n" +
                    " <CONNECTOR_DATA_ID>408098674</CONNECTOR_DATA_ID>\n" +
                    " <SOURCE>NBCH</SOURCE>\n" +
                    " <RESULT_CODE>1</RESULT_CODE>\n" +
                    " <ERROR>1</ERROR>\n" +
                    " <ERROR_TEXT>данные заемщика не найдены</ERROR_TEXT>\n" +
                    " <CONNECTOR_CODE>0</CONNECTOR_CODE>\n" +
                    " <SUBREQUEST_CODE>1</SUBREQUEST_CODE>\n" +
                    " <CREATED_DATE>22072024110923</CREATED_DATE>\n" +
                    " <COMPLETE_DATE>22072024110923</COMPLETE_DATE>\n" +
                    " <CACHE_USE>1</CACHE_USE>\n" +
                    " <BUREAU_STATUS>1</BUREAU_STATUS>\n" +
                    "</CONNECTOR_DATA_DETAIL>\n" +
                    "<CONNECTOR_DATA_DETAIL>\n" +
                    " <CONNECTOR_DATA_ID>408098675</CONNECTOR_DATA_ID>\n" +
                    " <SOURCE>EI</SOURCE>\n" +
                    " <RESULT_CODE>1</RESULT_CODE>\n" +
                    " <ERROR>1</ERROR>\n" +
                    " <ERROR_TEXT>данные заемщика не найдены</ERROR_TEXT>\n" +
                    " <CONNECTOR_CODE>6</CONNECTOR_CODE>\n" +
                    " <SUBREQUEST_CODE>111</SUBREQUEST_CODE>\n" +
                    " <CREATED_DATE>22072024110924</CREATED_DATE>\n" +
                    " <COMPLETE_DATE>22072024110924</COMPLETE_DATE>\n" +
                    " <CACHE_USE>1</CACHE_USE>\n" +
                    " <BUREAU_STATUS>1</BUREAU_STATUS>\n" +
                    "</CONNECTOR_DATA_DETAIL>\n" +
                    "  </CONNECTOR_DATA_DETAILS>\n" +
                    "  <EXTENSION>\n" +
                    "<ITEM_COUNT>1</ITEM_COUNT>\n" +
                    "<ITEM>\n" +
                    " <CODE>CRE_VERSION</CODE>\n" +
                    " <TEXT>2024.3.2</TEXT>\n" +
                    "</ITEM>\n" +
                    "  </EXTENSION>\n" +
                    "  <REPORT_DATETIME>22072024110924</REPORT_DATETIME>\n" +
                    " </MAIN>\n" +
                    " <SECONDARY>\n" +
                    "  <RESPONSE_STATUS>1</RESPONSE_STATUS>\n" +
                    "  <ERROR_TEXT>данные заемщика не найдены</ERROR_TEXT>\n" +
                    "  <INFO_SOURCES>\n" +
                    "<SOURCE>NBCH</SOURCE>\n" +
                    "<SOURCE>EI</SOURCE>\n" +
                    "  </INFO_SOURCES>\n" +
                    " </SECONDARY>\n" +
                    " <GENERAL>\n" +
                    "  <NAMES>0</NAMES>\n" +
                    "  <DOCUMENTS>0</DOCUMENTS>\n" +
                    "  <ADDRESSES>0</ADDRESSES>\n" +
                    "  <PHONES>0</PHONES>\n" +
                    "  <EMPLOYMENTS>0</EMPLOYMENTS>\n" +
                    "  <PASSPORT_CHECKS>0</PASSPORT_CHECKS>\n" +
                    "  <COMPANY_CHECKS>0</COMPANY_CHECKS>\n" +
                    "  <INQUIRIES>0</INQUIRIES>\n" +
                    " </GENERAL>\n" +
                    "</SINGLE_FORMAT>\n" +
                    "</response>\n" +
                    "<uid>41329f89-2f2c-47ca-af74-b341e4a1c09a</uid>\n" +
                    "<uidApplication>"+APPLICATION_UID+"</uidApplication>\n" +
                    "</ns3:ProcessRequestOut>\n" +
                    "<TransportProperties>\n" +
                    "<Headers>\n" +
                    "<Content-Type>text/xml;charset=UTF-8</Content-Type>\n" +
                    "<Content-Length>3691</Content-Length>\n" +
                    "<Date>"+LONG_DATA+"</Date>\n" +//E MMM d yyyy HH:mm:ss 'GMT'Z (z) //Tue Jun 11 2024 12:32:18 GMT+0300 (MSK)
                    "</Headers>\n" +
                    "</TransportProperties>\n" +
                    "</outputMessage>\n"+
                    "</SOAP-ENV:Body>\n" +
                    "</SOAP-ENV:Envelope>";;

        }else{ //DS_61_15_CallBIAS
            //String CustomerID=rq.substring(rq.indexOf("<CustomerID>") + "<CustomerID>".length(), rq.indexOf("</CustomerID>"));
            String CustomerID=extract(rq,"<CustomerID>","</CustomerID>");
            String APPLICATION_UID2=extract(rq,"<UidApplication>","</UidApplication>");
            main_response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                    "<soap:Body>"+
                    //"<ConnectorOutput>\n" +
                    " <Response>\n" +
                    "     <Reports>\n" +
                    "         <Report>\n" +
                    "             <CustomerID>"+CustomerID+"</CustomerID>\n" +
                    "             <ResultMsg>\n" +
                    "                 <ResultCode>1</ResultCode>\n" +
                    "                 <ResultMessage>OK</ResultMessage>\n" +
                    "             </ResultMsg>\n" +
                    "         </Report>\n" +
                    "     </Reports>\n" +
                    " </Response>\n" +
                    //"</ConnectorOutput>"+
                    "</soap:Body>\n" +
                    "</soap:Envelope>";

            main_resp_2="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "<SOAP-ENV:Body>\n" +
                    "<Data>\n" +
                    "<CreditRegistryData>\n" +
                    "<SINGLE_FORMAT>\n" +
                    "<MAIN>\n" +
                    "<INFO_SOURCES>\n" +
                    "<SOURCE>MEGAFON</SOURCE>\n" +
                    "</INFO_SOURCES>\n" +
                    "<CREATE_DATETIME>06082024123346</CREATE_DATETIME>\n" +
                    "<UID>8a4d0cdb-ee3f-4565-9343-58ee666bf863</UID>\n" +
                    "<APPLICATION_UID>"+APPLICATION_UID2+"</APPLICATION_UID>\n" +
                    "<APPLICANT_ID>224847209</APPLICANT_ID>\n" +
                    "<GROUP_ID>26214328</GROUP_ID>\n" +
                    "<CONNECTOR_DATA_DETAILS>\n" +
                    "<CONNECTOR_DATA_DETAIL>\n" +
                    "<CONNECTOR_DATA_ID>408125689</CONNECTOR_DATA_ID>\n" +
                    "<SOURCE>MEGAFON</SOURCE>\n" +
                    "<RESULT_CODE>1</RESULT_CODE>\n" +
                    "<ERROR>0</ERROR>\n" +
                    "<CONNECTOR_CODE>41</CONNECTOR_CODE>\n" +
                    "<SUBREQUEST_CODE>5</SUBREQUEST_CODE>\n" +
                    "<CREATED_DATE>02082024151826</CREATED_DATE>\n" +
                    "<COMPLETE_DATE>02082024151826</COMPLETE_DATE>\n" +
                    "<CACHE_USE>1</CACHE_USE>\n" +
                    "<BUREAU_STATUS>0</BUREAU_STATUS>\n" +
                    "</CONNECTOR_DATA_DETAIL>\n" +
                    "</CONNECTOR_DATA_DETAILS>\n" +
                    "<EXTENSION>\n" +
                    "<ITEM_COUNT>2</ITEM_COUNT>\n" +
                    "<ITEM>\n" +
                    "<CODE>CRE_VERSION</CODE>\n" +
                    "<TEXT>2024.3.2</TEXT>\n" +
                    "</ITEM>\n" +
                    "<ITEM>\n" +
                    "<CODE>reportCode</CODE>\n" +
                    "<TEXT>26214328:846937203</TEXT>\n" +
                    "</ITEM>\n" +
                    "</EXTENSION>\n" +
                    "</MAIN>\n" +
                    "<SECONDARY>\n" +
                    "<RESPONSE_STATUS>0</RESPONSE_STATUS>\n" +
                    "<INFO_SOURCES>\n" +
                    "<SOURCE>MEGAFON</SOURCE>\n" +
                    "</INFO_SOURCES>\n" +
                    "</SECONDARY>\n" +
                    "<GENERAL>\n" +
                    "<NAMES>0</NAMES>\n" +
                    "<DOCUMENTS>0</DOCUMENTS>\n" +
                    "<ADDRESSES>0</ADDRESSES>\n" +
                    "<PHONES>0</PHONES>\n" +
                    "<EMPLOYMENTS>0</EMPLOYMENTS>\n" +
                    "<PASSPORT_CHECKS>0</PASSPORT_CHECKS>\n" +
                    "<COMPANY_CHECKS>0</COMPANY_CHECKS>\n" +
                    "<INQUIRIES>0</INQUIRIES>\n" +
                    "</GENERAL>\n" +
                    "<SCORES>\n" +
                    "<SCORE>\n" +
                    "<CARD_ID>ALL_CLC</CARD_ID>\n" +
                    "<SCORE_VALUE>2.0</SCORE_VALUE>\n" +
                    "<INFO_SOURCES>\n" +
                    "<SOURCE>MEGAFON</SOURCE>\n" +
                    "</INFO_SOURCES>\n" +
                    "<EXTENSION>\n" +
                    "<ITEM_COUNT>1</ITEM_COUNT>\n" +
                    "<ITEM>\n" +
                    "<CODE>CONNECTOR_DATA_ID</CODE>\n" +
                    "<TEXT>408125689</TEXT>\n" +
                    "</ITEM>\n" +
                    "</EXTENSION>\n" +
                    "</SCORE>\n" +
                    "<SCORE>\n" +
                    "<CARD_ID>BLOCK_CNT</CARD_ID>\n" +
                    "<SCORE_VALUE>1.0</SCORE_VALUE>\n" +
                    "<INFO_SOURCES>\n" +
                    "<SOURCE>MEGAFON</SOURCE>\n" +
                    "</INFO_SOURCES>\n" +
                    "<EXTENSION>\n" +
                    "<ITEM_COUNT>1</ITEM_COUNT>\n" +
                    "<ITEM>\n" +
                    "<CODE>CONNECTOR_DATA_ID</CODE>\n" +
                    "<TEXT>408125689</TEXT>\n" +
                    "</ITEM>\n" +
                    "</EXTENSION>\n" +
                    "</SCORE>\n" +
                    "<SCORE>\n" +
                    "<CARD_ID>BLOCK_DUR</CARD_ID>\n" +
                    "<SCORE_VALUE>1.0</SCORE_VALUE>\n" +
                    "<INFO_SOURCES>\n" +
                    "<SOURCE>MEGAFON</SOURCE>\n" +
                    "</INFO_SOURCES>\n" +
                    "<EXTENSION>\n" +
                    "<ITEM_COUNT>1</ITEM_COUNT>\n" +
                    "<ITEM>\n" +
                    "<CODE>CONNECTOR_DATA_ID</CODE>\n" +
                    "<TEXT>408125689</TEXT>\n" +
                    "</ITEM>\n" +
                    "</EXTENSION>\n" +
                    "</SCORE>\n" +
                    "<SCORE>\n" +
                    "<CARD_ID>CIRCLE</CARD_ID>\n" +
                    "<SCORE_VALUE>4.0</SCORE_VALUE>\n" +
                    "<INFO_SOURCES>\n" +
                    "<SOURCE>MEGAFON</SOURCE>\n" +
                    "</INFO_SOURCES>\n" +
                    "<EXTENSION>\n" +
                    "<ITEM_COUNT>1</ITEM_COUNT>\n" +
                    "<ITEM>\n" +
                    "<CODE>CONNECTOR_DATA_ID</CODE>\n" +
                    "<TEXT>408125689</TEXT>\n" +
                    "</ITEM>\n" +
                    "</EXTENSION>\n" +
                    "</SCORE>\n" +
                    "<SCORE>\n" +
                    "<CARD_ID>IS_HOME</CARD_ID>\n" +
                    "<SCORE_VALUE>1.0</SCORE_VALUE>\n" +
                    "<INFO_SOURCES>\n" +
                    "<SOURCE>MEGAFON</SOURCE>\n" +
                    "</INFO_SOURCES>\n" +
                    "<EXTENSION>\n" +
                    "<ITEM_COUNT>1</ITEM_COUNT>\n" +
                    "<ITEM>\n" +
                    "<CODE>CONNECTOR_DATA_ID</CODE>\n" +
                    "<TEXT>408125689</TEXT>\n" +
                    "</ITEM>\n" +
                    "</EXTENSION>\n" +
                    "</SCORE>\n" +
                    "<SCORE>\n" +
                    "<CARD_ID>IS_WORK</CARD_ID>\n" +
                    "<SCORE_VALUE>1.0</SCORE_VALUE>\n" +
                    "<INFO_SOURCES>\n" +
                    "<SOURCE>MEGAFON</SOURCE>\n" +
                    "</INFO_SOURCES>\n" +
                    "<EXTENSION>\n" +
                    "<ITEM_COUNT>1</ITEM_COUNT>\n" +
                    "<ITEM>\n" +
                    "<CODE>CONNECTOR_DATA_ID</CODE>\n" +
                    "<TEXT>408125689</TEXT>\n" +
                    "</ITEM>\n" +
                    "</EXTENSION>\n" +
                    "</SCORE>\n" +
                    "<SCORE>\n" +
                    "<CARD_ID>LIFETIME_BIN</CARD_ID>\n" +
                    "<SCORE_VALUE>5.0</SCORE_VALUE>\n" +
                    "<INFO_SOURCES>\n" +
                    "<SOURCE>MEGAFON</SOURCE>\n" +
                    "</INFO_SOURCES>\n" +
                    "<EXTENSION>\n" +
                    "<ITEM_COUNT>1</ITEM_COUNT>\n" +
                    "<ITEM>\n" +
                    "<CODE>CONNECTOR_DATA_ID</CODE>\n" +
                    "<TEXT>408125689</TEXT>\n" +
                    "</ITEM>\n" +
                    "</EXTENSION>\n" +
                    "</SCORE>\n" +
                    "<SCORE>\n" +
                    "<CARD_ID>PAY_MAX</CARD_ID>\n" +
                    "<SCORE_VALUE>3.0</SCORE_VALUE>\n" +
                    "<INFO_SOURCES>\n" +
                    "<SOURCE>MEGAFON</SOURCE>\n" +
                    "</INFO_SOURCES>\n" +
                    "<EXTENSION>\n" +
                    "<ITEM_COUNT>1</ITEM_COUNT>\n" +
                    "<ITEM>\n" +
                    "<CODE>CONNECTOR_DATA_ID</CODE>\n" +
                    "<TEXT>408125689</TEXT>\n" +
                    "</ITEM>\n" +
                    "</EXTENSION>\n" +
                    "</SCORE>\n" +
                    "<SCORE>\n" +
                    "<CARD_ID>URALSIB_201901_SCORE_DEFAULT</CARD_ID>\n" +
                    "<SCORE_VALUE>0.082939</SCORE_VALUE>\n" +
                    "<INFO_SOURCES>\n" +
                    "<SOURCE>MEGAFON</SOURCE>\n" +
                    "</INFO_SOURCES>\n" +
                    "<EXTENSION>\n" +
                    "<ITEM_COUNT>1</ITEM_COUNT>\n" +
                    "<ITEM>\n" +
                    "<CODE>CONNECTOR_DATA_ID</CODE>\n" +
                    "<TEXT>408125689</TEXT>\n" +
                    "</ITEM>\n" +
                    "</EXTENSION>\n" +
                    "</SCORE>\n" +
                    "<SCORE>\n" +
                    "<CARD_ID>URALSIB_201901_SCORE_FRAUD</CARD_ID>\n" +
                    "<SCORE_VALUE>0.044394</SCORE_VALUE>\n" +
                    "<INFO_SOURCES>\n" +
                    "<SOURCE>MEGAFON</SOURCE>\n" +
                    "</INFO_SOURCES>\n" +
                    "<EXTENSION>\n" +
                    "<ITEM_COUNT>1</ITEM_COUNT>\n" +
                    "<ITEM>\n" +
                    "<CODE>CONNECTOR_DATA_ID</CODE>\n" +
                    "<TEXT>408125689</TEXT>\n" +
                    "</ITEM>\n" +
                    "</EXTENSION>\n" +
                    "</SCORE>\n" +
                    "</SCORES>\n" +
                    "</SINGLE_FORMAT>\n" +
                    "</CreditRegistryData>\n" +
                    "</Data>\n"+
                    "</soap:Body>\n" +
                    "</soap:Envelope>";
        }
        return ResponseEntity.ok().body(main_resp_2);
    }

    @GetMapping(value = "/mi_S_so_sbch", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_02_15_InternalCH(@RequestBody String rq){
        String Application_ID=extract(rq,"<Application_ID>","</Application_ID>");
        String CustomerID=extract(rq,"<CustomerID>","</CustomerID>");
        String FName=extract(rq,"<Name>","</Name>");
        String Surname=extract(rq,"<Surname>","</Surname>");
        String Patronymic=extract(rq,"<Patronymic>","</Patronymic>");
        String BirthDate=extract(rq,"<BirthDate>","</BirthDate>");
        String main_response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                "<soap:Body>"+
                " <InternalCHResult>\n" +
                "     <ProcessID>CREDIT_CARDS</ProcessID>\n" +
                "     <ProcessName />\n" +
                "     <InternalCHResult>\n" +
                "         <Application_ID>"+Application_ID+"</Application_ID>\n" +
                "         <CustomerID>"+CustomerID+"</CustomerID>\n" +
                "         <HistoryFound>1</HistoryFound>\n" +
                "         <Personal_Details>\n" +
                "             <Name>"+FName+"</Name>\n" +
                "             <Surname>"+Surname+"</Surname>\n" +
                "             <Patronymic>"+Patronymic+"</Patronymic>\n" +
                "             <Personal_ID_Main>\n" +
                "                 <ID_Type>21</ID_Type>\n" +
                "                 <ID_Series>6704</ID_Series>\n" +
                "                 <ID_Number>093209</ID_Number>\n" +
                "                 <Issue_Date>25.06.2003</Issue_Date>\n" +
                "                 <Issued_By>КОГАЛЫМСКИМ ГОРОДСКИМ ОТДЕЛОВ ВНУТРЕННИХ ДЕЛ УВД ХАНТЫ-МАНСИЙСКОГО АВТОНОМНОГО ОКРУГА ТЮМЕНСКОЙ ОБЛАСТИ</Issued_By>\n" +
                "             </Personal_ID_Main>\n" +
                "             <Contact>\n" +
                "                 <Contact_Type>H</Contact_Type>\n" +
                "                 <Phone_Number>+7(982)2212476</Phone_Number>\n" +
                "             </Contact>\n" +
                "             <Registration_Address>\n" +
                "                 <Country>RU</Country>\n" +
                "                 <Postal_Code>452172</Postal_Code>\n" +
                "                 <Region>Республика Башкортостан</Region>\n" +
                "                 <Area>рп Чишмы</Area>\n" +
                "                 <City>Чишмы рп</City>\n" +
                "                 <Street>СТРОИТЕЛЬНАЯ</Street>\n" +
                "                 <Street_Type>01</Street_Type>\n" +
                "                 <House>5</House>\n" +
                "                 <Building>А</Building>\n" +
                "                 <Apartment>76</Apartment>\n" +
                "             </Registration_Address>\n" +
                "             <Living_Address>\n" +
                "                 <Country>RU</Country>\n" +
                "                 <Postal_Code />\n" +
                "                 <Region>Ханты-Мансийский авт. округ</Region>\n" +
                "                 <Area>г Когалым</Area>\n" +
                "                 <City>Когалым г</City>\n" +
                "                 <Street>СУРГУТСКОЕ</Street>\n" +
                "                 <Street_Type>01</Street_Type>\n" +
                "                 <House>11</House>\n" +
                "                 <Building>А</Building>\n" +
                "                 <Apartment>22</Apartment>\n" +
                "             </Living_Address>\n" +
                "             <Date_of_Birth>"+BirthDate+"</Date_of_Birth>\n" +
                "             <Place_of_Birth>С.САФАРОВО ЧИШМИНСКОГО РАЙОНА Р. БАШКОРТОСТАН</Place_of_Birth>\n" +
                "             <INN>025000546183</INN>\n" +
                "         </Personal_Details>\n" +
                "         <LoanInformation>\n" +
                "             <DateReported>04.09.2023</DateReported>\n" +
                "             <AccountNumber>45506810300321001296</AccountNumber>\n" +
                "             <AccountType>09</AccountType>\n" +
                "             <Relationship>1</Relationship>\n" +
                "             <DateOpened>02.08.2023</DateOpened>\n" +
                "             <DateLastPayment>02.01.1900</DateLastPayment>\n" +
                "             <AccountStatus>00</AccountStatus>\n" +
                "             <Currency>RUB</Currency>\n" +
                "             <Limit>598417.52</Limit>\n" +
                "             <Balance>0</Balance>\n" +
                "             <PastDue>0.00</PastDue>\n" +
                "             <MannerOfPayment>XXXXXXXXXXXXXXXXXXXXXXXX</MannerOfPayment>\n" +
                "             <DatePaymentDue>02.09.2024</DatePaymentDue>\n" +
                "             <DateInterestPaymentDue>02.09.2024</DateInterestPaymentDue>\n" +
                "             <DateAccountState>04.09.2023</DateAccountState>\n" +
                "             <NextPayment />\n" +
                "             <PaymentFrequency>3</PaymentFrequency>\n" +
                "             <Collateral>00</Collateral>\n" +
                "             <InterestPaymentFrequency>3</InterestPaymentFrequency>\n" +
                "             <AmountOutstanding>0</AmountOutstanding>\n" +
                "         </LoanInformation>\n" +
                "         <LoanSummary>\n" +
                "             <Total_Number_Accounts_Reported>1</Total_Number_Accounts_Reported>\n" +
                "             <Accounts_Rated_Negatively>0</Accounts_Rated_Negatively>\n" +
                "             <Accounts_With_Zero_Balance>0</Accounts_With_Zero_Balance>\n" +
                "             <Total_Credit_Limit>598417.52</Total_Credit_Limit>\n" +
                "             <Total_Instalments_Amount>0</Total_Instalments_Amount>\n" +
                "             <Total_Current_Balance>0</Total_Current_Balance>\n" +
                "             <Total_Past_Due_Balance>0.00</Total_Past_Due_Balance>\n" +
                "             <Most_Recent_Account_Establishment_Date>02.08.2023</Most_Recent_Account_Establishment_Date>\n" +
                "             <Oldest_Account_Establishment_Date>02.08.2023</Oldest_Account_Establishment_Date>\n" +
                "             <Total_Number_Inquiries>0</Total_Number_Inquiries>\n" +
                "             <Number_Inquiries_Last_30days>0</Number_Inquiries_Last_30days>\n" +
                "             <Number_Inquiries_For_Collection_Purposes>0</Number_Inquiries_For_Collection_Purposes>\n" +
                "             <Most_Recent_Inquiry_Date>0</Most_Recent_Inquiry_Date>\n" +
                "         </LoanSummary>\n" +
                "     </InternalCHResult>\n" +
                "     <ResultMsg>\n" +
                "         <ResultCode>1</ResultCode>\n" +
                "         <ResultMessage>Success</ResultMessage>\n" +
                "     </ResultMsg>\n" +
                " </InternalCHResult>\n" +
                "</soap:Body>\n" +
                "</soap:Envelope>";
        return ResponseEntity.ok().body(main_response);
    }

    @GetMapping(value = "/ru/CrifServices.asmx", produces = "multipart/form-data;charset=UTF-8")//уточнить корректность
    public ResponseEntity DS_02_45_GetPFRHistoryInfo(HttpServletRequest request){

        String main_response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + //взято из старой заглушки
                "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                "<soap:Body>\n" +
                "<ConnectorOutput>\n" +
                " <GetPFRClientDataResponse>\n" +
                "     <GetPFRClientDataResult>\n" +
                "         <RequestCrifGuidId>00000000-0000-0000-0000-000000000000</RequestCrifGuidId>\n" +
                "         <RequestClientId>00000000-0000-0000-0000-000000000000</RequestClientId>\n" +
                "         <InterlayerGuidId>00000000-0000-0000-0000-000000000000</InterlayerGuidId>\n" +
                "         <Result>Success</Result>\n" +
                "         <Message>Успешно выгружены данные ПФР для КРИФа</Message>\n" +
                "         <Code>0</Code>\n" +
                "         <Xml>&lt;?xml version=\"1.0\" encoding=\"UTF-8\"?&gt;&lt;Subjects /&gt;</Xml>\n" +
                "     </GetPFRClientDataResult>\n" +
                " </GetPFRClientDataResponse>\n" +
                "</ConnectorOutput>\n" +
                "</soap:Body>\n" +
                "</soap:Envelope>";

        /*String main_response="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<ConnectorOutput />";-*/

        return ResponseEntity.ok().body(main_response);
        }
    @PostMapping(value = "/CRDS", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_03_10_S1_PCM_TIBCO(HttpServletRequest request,@RequestBody String rq) {
        String InquiryCode = extract(rq, "<InquiryCode>", "</InquiryCode>");
        String ProcessCode = extract(rq, "<ProcessCode>", "</ProcessCode>");
        String LayoutVersion = extract(rq, "<LayoutVersion>", "</LayoutVersion>");
        String IN_IN_APPL_REQUEST_DATE = extract(rq, "<IN_APPL_REQUEST_DATE>", "</IN_APPL_REQUEST_DATE>");//current date YYYY-MM-dd
        String IN_CUSTOMER_BASICSALARY_0 = extract(rq, "<IN_CUSTOMER_BASICSALARY>", "</IN_CUSTOMER_BASICSALARY>");
        String IN_CUSTOMER_BASICSALARY = IN_CUSTOMER_BASICSALARY_0.substring(0, IN_CUSTOMER_BASICSALARY_0.indexOf("."));
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy");
        Date date2;
        try {
            date2 = formatter.parse(IN_IN_APPL_REQUEST_DATE);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date2);
        Date date_t = cal.getTime();
        String IN_APPL_CREATE_DATE = formatter.format(date_t);
        request.toString();
        Locale locale = Locale.US;
        DateFormat dateFormat = new SimpleDateFormat("E MMM d yyyy HH:mm:ss 'GMT'Z (z)", locale);
        Date date = new Date();
        String LONG_DATA = dateFormat.format(date);

        Logger logger = Logger.getLogger("MyLog");
        //FileHandler fh;

        String response = null;
        String main_resp2=null;
        try {

            // This block configure the logger with handler and formatter
            FileHandler fh = new FileHandler("C:/Program Files/Apache Software Foundation/Tomcat 9.0/logs/MyLogFile.log");
            logger.addHandler(fh);
            SimpleFormatter l_formatter = new SimpleFormatter();
            fh.setFormatter(l_formatter);
            String hdrs = "";

            // the following statement is used to log any messages
                response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                        "<soap:Body>\n" +
                        "<StrategyOneResponseT5>\n" +
                        "   <Header>\n" +
                        "         <InquiryCode>" + InquiryCode + "</InquiryCode>\n" +
                        "         <ProcessCode>" + ProcessCode + "</ProcessCode>\n" +
                        "         <OrganizationCode>CRDS</OrganizationCode>\n" +
                        "         <ProcessVersion>293</ProcessVersion>\n" +
                        "         <LayoutVersion>" + LayoutVersion + "</LayoutVersion>\n" +
                        "     </Header>\n" +
                        "     <Body>\n" +
                        "         <Application>\n" +
                        "             <Variables>\n" +
                        "                 <OUT_APPL_APPLICATIONID>" + InquiryCode + "</OUT_APPL_APPLICATIONID>\n" +
                        "                 <OUT_APPL_REQUEST_TYPE>BASE</OUT_APPL_REQUEST_TYPE>\n" +
                        "                 <OUT_APPL_DEC_RESULT>PREAPPROVE</OUT_APPL_DEC_RESULT>\n" +
                        "                 <OUT_APPL_DEC_TIMEOUT>0</OUT_APPL_DEC_TIMEOUT>\n" +
                        "                 <OUT_APPL_DEC_PD>0.04095995029315</OUT_APPL_DEC_PD>\n" +
                        "                 <OUT_APPL_DEC_SCORE>177</OUT_APPL_DEC_SCORE>\n" +
                        "                 <OUT_APPL_PTI>0.1128</OUT_APPL_PTI>\n" +
                        "             </Variables>\n" +
                        "             <Categories>\n" +
                        "                 <CUSTOMER>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_CUSTOMER_ID>476844900</OUT_CUSTOMER_ID>\n" +
                        "                         <OUT_CUSTOMER_PTI>0.1128</OUT_CUSTOMER_PTI>\n" +
                        "                         <OUT_CUSTOMER_DEC_RESULT>ACCEPT</OUT_CUSTOMER_DEC_RESULT>\n" +
                        "                         <OUT_CUSTOMER_DEC_PD>0.04095995029315</OUT_CUSTOMER_DEC_PD>\n" +
                        "                         <OUT_CUSTOMER_DEC_SCORE>177</OUT_CUSTOMER_DEC_SCORE>\n" +
                        "                         <OUT_CUSTOMER_DEC_CATEGORY>S</OUT_CUSTOMER_DEC_CATEGORY>\n" +
                        "                         <OUT_CUSTOMER_DEC_CATEGORY_EXT>S</OUT_CUSTOMER_DEC_CATEGORY_EXT>\n" +
                        "                         <OUT_CUSTOMER_DEC_CATEGORY_PC>S</OUT_CUSTOMER_DEC_CATEGORY_PC>\n" +
                        "                         <OUT_CUSTOMER_DEC_CREDITHISTORY_TYPE>СКИ</OUT_CUSTOMER_DEC_CREDITHISTORY_TYPE>\n" +
                        "                         <OUT_CUSTOMER_DEC_CREDITHISTORY_POS_MAXLIMIT>0</OUT_CUSTOMER_DEC_CREDITHISTORY_POS_MAXLIMIT>\n" +
                        "                         <OUT_CUSTOMER_TOTALINCOME>" + IN_CUSTOMER_BASICSALARY + "</OUT_CUSTOMER_TOTALINCOME>\n" +
                        "                     </Variables>\n" +
                        "                     <Categories>\n" +
                        "                         <OUT_CUSTOMER_DEC_VERIFICATION>\n" +
                        "                             <Variables>\n" +
                        "                                 <OUT_CUSTOMER_DEC_VERIFICATION_PART>CM_DOC_MAIN</OUT_CUSTOMER_DEC_VERIFICATION_PART>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_CUSTOMER_DEC_VERIFICATION>\n" +
                        "                         <OUT_CUSTOMER_DEC_RULES>\n" +
                        "                             <Variables>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_CODE>INF_SCORE_NTB_IS_NULL </OUT_CUSTOMER_DEC_RS_CODE>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_TYPE>I</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_NAME>NTB_PD не рассчитан</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_DESCRIPTION>NTB_PD не рассчитан</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_CUSTOMER_DEC_RULES>\n" +
                        "                         <OUT_CUSTOMER_DEC_RULES>\n" +
                        "                             <Variables>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_CODE>N154</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_TYPE>I</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_NAME>Телефон из заявки не совпал с телефонами в БКИ ИЛИ в БКИ отсутствуют данные по телефонам</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_DESCRIPTION>Телефон из заявки не совпал с телефонами в БКИ ИЛИ в БКИ отсутствуют данные по телефонам</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_CUSTOMER_DEC_RULES>\n" +
                        "                         <OUT_CUSTOMER_DEC_RULES>\n" +
                        "                             <Variables>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_CODE>AppValue</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_TYPE>SI</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_NAME>Значение суммарного дохода участника сделки</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_DESCRIPTION>" + IN_CUSTOMER_BASICSALARY + ".00</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_CUSTOMER_DEC_RULES>\n" +
                        "                         <OUT_CUSTOMER_DEC_RULES>\n" +
                        "                             <Variables>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_CODE>S008_fsr</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_TYPE>I</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_NAME>По осн. заемщику число запросов в БКИ за последние 3 месяца &lt;25. Если не было проверки ССБ по заявке. Кроме заемщиков Банка, сотрудников ФК и Банка</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_DESCRIPTION>По осн. заемщику число запросов в БКИ за последние 3 месяца &lt;25. Если не было проверки ССБ по заявке. Кроме заемщиков Банка, сотрудников ФК и Банка</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_CUSTOMER_DEC_RULES>\n" +
                        "                         <OUT_CUSTOMER_DEC_RULES>\n" +
                        "                             <Variables>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_CODE>CM005</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_TYPE>I</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_NAME>Проверка основного документа (паспорт). Результаты проверки сброшены</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_DESCRIPTION>Проверка основного документа (паспорт). Результаты проверки сброшены</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_CUSTOMER_DEC_RULES>\n" +
                        "                         <OUT_CUSTOMER_DEC_RULES>\n" +
                        "                             <Variables>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_CODE>CM006</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_TYPE>I</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_NAME>Проверка документов, подтверждающий доход. Результаты проверки сброшены</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_DESCRIPTION>Проверка документов, подтверждающий доход. Результаты проверки сброшены</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_CUSTOMER_DEC_RULES>\n" +
                        "                         <OUT_CUSTOMER_DEC_RULES>\n" +
                        "                             <Variables>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_CODE>CM011</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_TYPE>I</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_NAME> Проверка документов: документ, косвенно подтверждающий доход. В случае предоставления.</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                        "                                 <OUT_CUSTOMER_DEC_RS_DESCRIPTION> Проверка документов: документ, косвенно подтверждающий доход. В случае предоставления.</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_CUSTOMER_DEC_RULES>\n" +
                        "                         <OUT_CUSTOMER_DEC_SCORES>\n" +
                        "                             <Variables>\n" +
                        "                                 <OUT_CUSTOMER_DEC_SCORES_SCORE>-3.12544108082075</OUT_CUSTOMER_DEC_SCORES_SCORE>\n" +
                        "                                 <OUT_CUSTOMER_DEC_SCORES_SCORE_SCALED>177</OUT_CUSTOMER_DEC_SCORES_SCORE_SCALED>\n" +
                        "                                 <OUT_CUSTOMER_DEC_SCORES_PD>0.04095995029315</OUT_CUSTOMER_DEC_SCORES_PD>\n" +
                        "                                 <OUT_CUSTOMER_DEC_SCORES_CUTOFF>0.163</OUT_CUSTOMER_DEC_SCORES_CUTOFF>\n" +
                        "                                 <OUT_CUSTOMER_DEC_SCORES_PRODUCT_ID>110</OUT_CUSTOMER_DEC_SCORES_PRODUCT_ID>\n" +
                        "                             </Variables>\n" +
                        "                             <Categories />\n" +
                        "                         </OUT_CUSTOMER_DEC_SCORES>\n" +
                        "                         <OUT_CUSTOMER_SERVICES>\n" +
                        "                             <Variables>\n" +
                        "                                 <OUT_CUSTOMER_SERVICES_NAME>BIAS_FSSP</OUT_CUSTOMER_SERVICES_NAME>\n" +
                        "                                 <OUT_CUSTOMER_SERVICES_STATUS>0</OUT_CUSTOMER_SERVICES_STATUS>\n" +
                        "                             </Variables>\n" +
                        "                             <Categories />\n" +
                        "                         </OUT_CUSTOMER_SERVICES>\n" +
                        "                         <OUT_CUSTOMER_SERVICES>\n" +
                        "                             <Variables>\n" +
                        "                                 <OUT_CUSTOMER_SERVICES_NAME>BIAS_NEGATIV</OUT_CUSTOMER_SERVICES_NAME>\n" +
                        "                                 <OUT_CUSTOMER_SERVICES_STATUS>0</OUT_CUSTOMER_SERVICES_STATUS>\n" +
                        "                             </Variables>\n" +
                        "                             <Categories />\n" +
                        "                         </OUT_CUSTOMER_SERVICES>\n" +
                        "                         <OUT_CUSTOMER_PDN>\n" +
                        "                             <Variables />\n" +
                        "                             <Categories>\n" +
                        "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                     <Variables>\n" +
                        "                                         <COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                        "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                         <COMMON_PROPERTY_VALUE>" + IN_IN_APPL_REQUEST_DATE + "</COMMON_PROPERTY_VALUE>\n" +
                        "                                     </Variables>\n" +
                        "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                     <Variables>\n" +
                        "                                         <COMMON_PROPERTY_NAME>SUBJECTID</COMMON_PROPERTY_NAME>\n" +
                        "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                         <COMMON_PROPERTY_VALUE>476844900</COMMON_PROPERTY_VALUE>\n" +
                        "                                     </Variables>\n" +
                        "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                     <Variables>\n" +
                        "                                         <COMMON_PROPERTY_NAME>cusType</COMMON_PROPERTY_NAME>\n" +
                        "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                         <COMMON_PROPERTY_VALUE>Applicant</COMMON_PROPERTY_VALUE>\n" +
                        "                                     </Variables>\n" +
                        "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                     <Variables>\n" +
                        "                                         <COMMON_PROPERTY_NAME>pdnBkiPmt</COMMON_PROPERTY_NAME>\n" +
                        "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                         <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                                     </Variables>\n" +
                        "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                     <Variables>\n" +
                        "                                         <COMMON_PROPERTY_NAME>pdnBkiPmtSum_ApplIsOwn1</COMMON_PROPERTY_NAME>\n" +
                        "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                         <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                                     </Variables>\n" +
                        "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                     <Variables>\n" +
                        "                                         <COMMON_PROPERTY_NAME>pdnBkiPmtSum_ApplIsOwn0</COMMON_PROPERTY_NAME>\n" +
                        "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                         <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                                     </Variables>\n" +
                        "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                     <Variables>\n" +
                        "                                         <COMMON_PROPERTY_NAME>pdnBkiPmtSum_ApplRel5</COMMON_PROPERTY_NAME>\n" +
                        "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                         <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                                     </Variables>\n" +
                        "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                     <Variables>\n" +
                        "                                         <COMMON_PROPERTY_NAME>pdnAvgMonthlyIncome</COMMON_PROPERTY_NAME>\n" +
                        "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                         <COMMON_PROPERTY_VALUE>46307</COMMON_PROPERTY_VALUE>\n" +
                        "                                     </Variables>\n" +
                        "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                     <Variables>\n" +
                        "                                         <COMMON_PROPERTY_NAME>pdnConfirmedIncome</COMMON_PROPERTY_NAME>\n" +
                        "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                         <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                                     </Variables>\n" +
                        "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                     <Variables>\n" +
                        "                                         <COMMON_PROPERTY_NAME>pdnBkiIncome</COMMON_PROPERTY_NAME>\n" +
                        "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                         <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                                     </Variables>\n" +
                        "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                     <Variables>\n" +
                        "                                         <COMMON_PROPERTY_NAME>pdnDeclaredIncome</COMMON_PROPERTY_NAME>\n" +
                        "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                         <COMMON_PROPERTY_VALUE>46307</COMMON_PROPERTY_VALUE>\n" +
                        "                                     </Variables>\n" +
                        "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                     <Variables>\n" +
                        "                                         <COMMON_PROPERTY_NAME>pdnPerPersonIncome</COMMON_PROPERTY_NAME>\n" +
                        "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                         <COMMON_PROPERTY_VALUE>46307</COMMON_PROPERTY_VALUE>\n" +
                        "                                     </Variables>\n" +
                        "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                     <Variables>\n" +
                        "                                         <COMMON_PROPERTY_NAME>pdnRetailIncome</COMMON_PROPERTY_NAME>\n" +
                        "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                         <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                                     </Variables>\n" +
                        "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                     <Variables>\n" +
                        "                                         <COMMON_PROPERTY_NAME>pdnModelingIncome</COMMON_PROPERTY_NAME>\n" +
                        "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                         <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                                     </Variables>\n" +
                        "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                 <OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                                     <Variables>\n" +
                        "                                         <COMMON_PROPERTY_NAME>pdnIncomeCalcPeriod</COMMON_PROPERTY_NAME>\n" +
                        "                                         <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                         <COMMON_PROPERTY_VALUE>12</COMMON_PROPERTY_VALUE>\n" +
                        "                                     </Variables>\n" +
                        "                                 </OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                        "                             </Categories>\n" +
                        "                         </OUT_CUSTOMER_PDN>\n" +
                        "                     </Categories>\n" +
                        "                 </CUSTOMER>\n" +
                        "                 <OUT_APPL_DEC_OFFERS>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_OFFERS_ID>1</OUT_APPL_DEC_OFFERS_ID>\n" +
                        "                         <OUT_APPL_DEC_OFFERS_CREDITLIMIT>29000</OUT_APPL_DEC_OFFERS_CREDITLIMIT>\n" +
                        "                         <OUT_APPL_DEC_OFFERS_MINLIMIT>9999</OUT_APPL_DEC_OFFERS_MINLIMIT>\n" +
                        "                         <OUT_APPL_DEC_OFFERS_APR>0.209</OUT_APPL_DEC_OFFERS_APR>\n" +
                        "                         <OUT_APPL_DEC_OFFERS_PRODUCTID>110</OUT_APPL_DEC_OFFERS_PRODUCTID>\n" +
                        "                         <OUT_APPL_DEC_OFFERS_MAIN>1</OUT_APPL_DEC_OFFERS_MAIN>\n" +
                        "                         <OUT_APPL_DEC_OFFERS_CREDITLIMIT_BANK>29000</OUT_APPL_DEC_OFFERS_CREDITLIMIT_BANK>\n" +
                        "                         <OUT_APPL_DEC_OFFERS_CREDITLIMIT_PDN>30000</OUT_APPL_DEC_OFFERS_CREDITLIMIT_PDN>\n" +
                        "                         <OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP>40000</OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP>\n" +
                        "                         <OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP_BANK>40000</OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP_BANK>\n" +
                        "                         <OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP_PDN>30000</OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP_PDN>\n" +
                        "                     </Variables>\n" +
                        "                     <Categories />\n" +
                        "                 </OUT_APPL_DEC_OFFERS>\n" +
                        "                 <OUT_APPL_PDN>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_PDN_CALC_DATE>" + IN_IN_APPL_REQUEST_DATE + "</OUT_APPL_PDN_CALC_DATE>\n" +
                        "                         <OUT_APPL_PDN_CONFIRMED_INCOME>0</OUT_APPL_PDN_CONFIRMED_INCOME>\n" +
                        "                         <OUT_APPL_PDN_INCOME_PER_PERSON>46307</OUT_APPL_PDN_INCOME_PER_PERSON>\n" +
                        "                         <OUT_APPL_PDN_CH_INCOME>0</OUT_APPL_PDN_CH_INCOME>\n" +
                        "                         <OUT_APPL_PDN_VALUE_BY_CONFIRMED_INCOME>0</OUT_APPL_PDN_VALUE_BY_CONFIRMED_INCOME>\n" +
                        "                         <OUT_APPL_PDN_VALUE_BY_INCOME_PER_PERSON>0.01878765629386</OUT_APPL_PDN_VALUE_BY_INCOME_PER_PERSON>\n" +
                        "                         <OUT_APPL_PDN_VALUE_BY_CH_INCOME>0</OUT_APPL_PDN_VALUE_BY_CH_INCOME>\n" +
                        "                         <OUT_APPL_PDN_MIN_VALUE>0.01878765629386</OUT_APPL_PDN_MIN_VALUE>\n" +
                        "                         <OUT_APPL_PDN_CH_EXPENSES>870</OUT_APPL_PDN_CH_EXPENSES>\n" +
                        "                         <OUT_APPL_PDN_INCOME_FOR_PDN_CALC>46307</OUT_APPL_PDN_INCOME_FOR_PDN_CALC>\n" +
                        "                         <OUT_APPL_PDN_CALCULATED_WITH_INCPERPERSON_FLAG>1</OUT_APPL_PDN_CALCULATED_WITH_INCPERPERSON_FLAG>\n" +
                        "                         <OUT_APPL_PDN_TOTALINCOME>" + IN_CUSTOMER_BASICSALARY + "</OUT_APPL_PDN_TOTALINCOME>\n" +
                        "                     </Variables>\n" +
                        "                     <Categories>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>" + IN_IN_APPL_REQUEST_DATE + "</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>pdnLimitCLIP</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>pdnExpense</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>870</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>pdnIncomeType</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>per_person</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>pdnAvgMonthlyIncome</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>46307</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>pdnNotConfirmedIncome</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>46307</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>pdnConfirmedIncome</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>pdnPerPersonIncome</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>46307</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>pdnImputedIncome</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>pdnValue</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0.0188</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>pdnOfferPSK</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>pdnOfferPmt</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>870</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>pdnBkiIncome</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>pdnBkiPmt</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>pdnBkiStartDate</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>2024-05-01</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>pdnBkiEndDate</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>2022-05-01</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>pdnBkiMnthCnt</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>18</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_1</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_2</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_3</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_4</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_5</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_6</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_7</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_8</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_9</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_10</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_11</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_12</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_13</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_14</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_15</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_16</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_17</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_18</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_19</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_20</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_21</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_22</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_23</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                         <OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                             <Variables>\n" +
                        "                                 <COMMON_PROPERTY_NAME>bkiPmtMnth_24</COMMON_PROPERTY_NAME>\n" +
                        "                                 <COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                        "                                 <COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                        "                             </Variables>\n" +
                        "                         </OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                        "                     </Categories>\n" +
                        "                 </OUT_APPL_PDN>\n" +
                        "                 <OUT_APPL_DEC_RULES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_RS_CODE>TECH_RULE_4</OUT_APPL_DEC_RS_CODE>\n" +
                        "                         <OUT_APPL_DEC_RS_TYPE>SI</OUT_APPL_DEC_RS_TYPE>\n" +
                        "                         <OUT_APPL_DEC_RS_NAME>WalkIn_EXIST</OUT_APPL_DEC_RS_NAME>\n" +
                        "                         <OUT_APPL_DEC_RS_DESCRIPTION>WalkIn_EXIST</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_RULES>\n" +
                        "                 <OUT_APPL_DEC_RULES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_RS_CODE>SCR_RULE_2</OUT_APPL_DEC_RS_CODE>\n" +
                        "                         <OUT_APPL_DEC_RS_TYPE>SI</OUT_APPL_DEC_RS_TYPE>\n" +
                        "                         <OUT_APPL_DEC_RS_NAME>EXIST_S_3$Частная (ЗАО/АО)_6 .. 10_1$Холост / Не замужем_0_2</OUT_APPL_DEC_RS_NAME>\n" +
                        "                         <OUT_APPL_DEC_RS_DESCRIPTION>4_41_0.1818$41$Новосибирская область_0</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_RULES>\n" +
                        "                 <OUT_APPL_DEC_RULES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_RS_CODE>SCR_RULE_1</OUT_APPL_DEC_RS_CODE>\n" +
                        "                         <OUT_APPL_DEC_RS_TYPE>SI</OUT_APPL_DEC_RS_TYPE>\n" +
                        "                         <OUT_APPL_DEC_RS_NAME>-3.28952083053102,0,0</OUT_APPL_DEC_RS_NAME>\n" +
                        "                         <OUT_APPL_DEC_RS_DESCRIPTION>0,-3.1254410808207505,0.04206994873765842,0</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_RULES>\n" +
                        "                 <OUT_APPL_DEC_RULES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_RS_CODE>SCR_RULE_0</OUT_APPL_DEC_RS_CODE>\n" +
                        "                         <OUT_APPL_DEC_RS_TYPE>SI</OUT_APPL_DEC_RS_TYPE>\n" +
                        "                         <OUT_APPL_DEC_RS_NAME>1:WalkIn_EXIST</OUT_APPL_DEC_RS_NAME>\n" +
                        "                         <OUT_APPL_DEC_RS_DESCRIPTION>1:0.04095995029315231</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_RULES>\n" +
                        "                 <OUT_APPL_DEC_RULES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_RS_CODE>S1_VER</OUT_APPL_DEC_RS_CODE>\n" +
                        "                         <OUT_APPL_DEC_RS_TYPE>SI</OUT_APPL_DEC_RS_TYPE>\n" +
                        "                         <OUT_APPL_DEC_RS_NAME>S1_VER</OUT_APPL_DEC_RS_NAME>\n" +
                        "                         <OUT_APPL_DEC_RS_DESCRIPTION>L21_V293</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_RULES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>limitUpSegment</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR />\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE />\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>lib_PDN</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>libVersion</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>v1.21</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>limitsDebug</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>WalkIn_EXIST 23-67 NOHIT 6+</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>limitsDebugUp</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>WalkIn_EXIST 23-67 NOHIT 5+</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>cusCCNTBPD</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR />\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE />\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>1.0</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>MPL_PARAM</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>PDN_CC_FLAG 0</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>MPL_PARAM</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>PDN_CC_LIMIT 0.5</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>mplPdnCCLimit</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>0.5</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>mplPdnCCFlag</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>0</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>pdnIncome</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>46307</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>pdnSpendMax</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>23153.5</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>pdnSpendCurrent</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>0</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>offer_1</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR />\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE />\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>1 29000 40000 40000 30000 29000 30000 9999 110 NULL 1 0.209 0</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>IN_CUSTOMER_CREDITPAYEXP</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>0</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>VAR_CUS_BKI_OVERVIEW_MONTHLY_PAYMENT</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>0</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>IN_CUSTOMER_OTHEREXP</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>0</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>VAR_CUSTOMER_MIN_LIVING_EXP</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>10117</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>maxOffer.offerCreditLimit</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>29000</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>maxOffer.offerCreditLimit*0.04</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>1160</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>cusTotalIncome</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>" + IN_CUSTOMER_BASICSALARY + "</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>pti</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>0.1128</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>ITERNUM</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>Номер вызова S1</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>3</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>S1CallDateTime</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>Дата и время вызова S1</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>" + LONG_DATA + "</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>applPSKCalcEnabledFlag</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>Флаг расчёта и контроля ПСК в стратегии</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>applPresaleNavigatorFlag</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>Флаг процесса FA / MA NAVIGATOR</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>Segment</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>WalkIn_EXIST</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>cusWorkerType</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>Other</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>MaxPTIByPD</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>1.0</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>MaxLimitByPD</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>30000</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>MinLivingExp</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>NULL</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>TotalIncome</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>" + IN_CUSTOMER_BASICSALARY + ".00</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>Score_Raw</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>-3.125441080820750</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>Score_Scaled</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>177.0</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>PD</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>0.04095995029315231</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>RiskLevel</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>NULL</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>MasterGrade</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>6+</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>PrevappMonthlyPayment</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>0.00</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>FccreditMonthlyPayment</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>DepCount</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>RegionMinSalary (ПМ)</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>10117</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>RAPID_CLIP_FLAG</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>1</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>BKI_HIT_FLAG</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>MKK_PD</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>NULL</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>MKK_score</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>NULL</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>OFFER with ID: 1</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>NULL</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>IsMain:1, Limit:29000, MinLimit:9999, Rate:0.209</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>LIMIT_UP_FLAG</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>LIMITUP</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>NUMBER</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>DIGITAL_LIMIT_PILOT_NEW</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>PILOT_NEW</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>NUMBER</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>DIGITAL_LIMIT_PILOT_NEW_TECH</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>PILOT_NEW</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>NUMBER</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>IN_APPL_CREATE_DATE</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>IN_APPL_CREATE_DATE</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>" + IN_APPL_CREATE_DATE + "</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "                 <OUT_APPL_DEC_VARIABLES>\n" +
                        "                     <Variables>\n" +
                        "                         <OUT_APPL_DEC_VAR_NAME>MKK_DECISION</OUT_APPL_DEC_VAR_NAME>\n" +
                        "                         <OUT_APPL_DEC_VAR_DESCR>MKK_VARS_GROUP</OUT_APPL_DEC_VAR_DESCR>\n" +
                        "                         <OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                        "                         <OUT_APPL_DEC_VAR_VALUE>DECLINE</OUT_APPL_DEC_VAR_VALUE>\n" +
                        "                     </Variables>\n" +
                        "                 </OUT_APPL_DEC_VARIABLES>\n" +
                        "             </Categories>\n" +
                        "         </Application>\n" +
                        "     </Body>\n" +
                        " </StrategyOneResponseT5>\n" +
                        "</soap:Body>\n" +
                        "</soap:Envelope>";

                main_resp2="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<group>\n" +
                        " <ResultCode>1</ResultCode>\n" +
                        " <ResultMsg>OK ( Записи найдены )</ResultMsg>\n" +
                        "</group>       <?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<root>\n" +
                        " <ns0:ProductCatalogueInquiryResponse xmlns:ns0=\"http://www.tibco.com/schemas/CRIF_PRODCAT_R/ProductCatalogueSchemaRomashka/Schema.xsd2\">\n" +
                        "     <ProcessID>576f4160-5bd7-11ef-9062-005056bdcef5</ns0:ProcessID>\n" +
                        "     <ProcessName>CRDS</ns0:ProcessName>\n" +
                        "     <ProductCatalogueResponse>\n" +
                        "         <ApplicationID>"+InquiryCode+"</ns0:ApplicationID>\n" +
                        "         <Tariffs>\n" +
                        "             <Tariff>\n" +
                        "                 <TariffID>106</ns0:TariffID>\n" +
                        "                 <TariffOptions>\n" +
                        "                     <TariffOption>\n" +
                        "                         <NAME>TARIFFNAME</ns0:NAME>\n" +
                        "                         <TYPE>STRING</ns0:TYPE>\n" +
                        "                         <VALUE>ТП 120 дней на максимум</ns0:VALUE>\n" +
                        "                     </ns0:TariffOption>\n" +
                        "                     <TariffOption>\n" +
                        "                         <NAME>TARIFFTYPE</ns0:NAME>\n" +
                        "                         <TYPE>STRING</ns0:TYPE>\n" +
                        "                         <VALUE>ST</ns0:VALUE>\n" +
                        "                     </ns0:TariffOption>\n" +
                        "                     <TariffOption>\n" +
                        "                         <NAME>CURRENCY</ns0:NAME>\n" +
                        "                         <TYPE>STRING</ns0:TYPE>\n" +
                        "                         <VALUE>810</ns0:VALUE>\n" +
                        "                     </ns0:TariffOption>\n" +
                        "                     <TariffOption>\n" +
                        "                         <NAME>MIN_RATE</ns0:NAME>\n" +
                        "                         <TYPE>FLOAT</ns0:TYPE>\n" +
                        "                         <VALUE>39.9</ns0:VALUE>\n" +
                        "                     </ns0:TariffOption>\n" +
                        "                     <TariffOption>\n" +
                        "                         <NAME>MAX_RATE</ns0:NAME>\n" +
                        "                         <TYPE>FLOAT</ns0:TYPE>\n" +
                        "                         <VALUE>39.9</ns0:VALUE>\n" +
                        "                     </ns0:TariffOption>\n" +
                        "                     <TariffOption>\n" +
                        "                         <NAME>PRODUCT_TYPE</ns0:NAME>\n" +
                        "                         <TYPE>STRING</ns0:TYPE>\n" +
                        "                         <VALUE>647</ns0:VALUE>\n" +
                        "                     </ns0:TariffOption>\n" +
                        "                     <TariffOption>\n" +
                        "                         <NAME>MIN_LIMIT</ns0:NAME>\n" +
                        "                         <TYPE>FLOAT</ns0:TYPE>\n" +
                        "                         <VALUE>9999</ns0:VALUE>\n" +
                        "                     </ns0:TariffOption>\n" +
                        "                     <TariffOption>\n" +
                        "                         <NAME>MAX_LIMIT</ns0:NAME>\n" +
                        "                         <TYPE>FLOAT</ns0:TYPE>\n" +
                        "                         <VALUE>1000000</ns0:VALUE>\n" +
                        "                     </ns0:TariffOption>\n" +
                        "                     <TariffOption>\n" +
                        "                         <NAME>PENAMOUNT</ns0:NAME>\n" +
                        "                         <TYPE>FLOAT</ns0:TYPE>\n" +
                        "                         <VALUE>0.05</ns0:VALUE>\n" +
                        "                     </ns0:TariffOption>\n" +
                        "                     <TariffOption>\n" +
                        "                         <NAME>PENDAYAMOUNT</ns0:NAME>\n" +
                        "                         <TYPE>FLOAT</ns0:TYPE>\n" +
                        "                         <VALUE>0.05</ns0:VALUE>\n" +
                        "                     </ns0:TariffOption>\n" +
                        "                     <TariffOption>\n" +
                        "                         <NAME>DELIQDAYPR</ns0:NAME>\n" +
                        "                         <TYPE>FLOAT</ns0:TYPE>\n" +
                        "                         <VALUE>0.054</ns0:VALUE>\n" +
                        "                     </ns0:TariffOption>\n" +
                        "                     <TariffOption>\n" +
                        "                         <NAME>REQUIREMENT</ns0:NAME>\n" +
                        "                         <TYPE>STRING</ns0:TYPE>\n" +
                        "                         <VALUE>standart</ns0:VALUE>\n" +
                        "                     </ns0:TariffOption>\n" +
                        "                     <TariffOption>\n" +
                        "                         <NAME>BASIC_RATE</ns0:NAME>\n" +
                        "                         <TYPE>FLOAT</ns0:TYPE>\n" +
                        "                         <VALUE>39.9</ns0:VALUE>\n" +
                        "                     </ns0:TariffOption>\n" +
                        "                     <TariffOption>\n" +
                        "                         <NAME>PACKAGE</ns0:NAME>\n" +
                        "                         <TYPE>STRING</ns0:TYPE>\n" +
                        "                         <VALUE>ALONE</ns0:VALUE>\n" +
                        "                     </ns0:TariffOption>\n" +
                        "                     <TariffOption>\n" +
                        "                         <NAME>PAY_SYSTEM</ns0:NAME>\n" +
                        "                         <TYPE>STRING</ns0:TYPE>\n" +
                        "                         <VALUE>MIR</ns0:VALUE>\n" +
                        "                     </ns0:TariffOption>\n" +
                        "                     <TariffOption>\n" +
                        "                         <NAME>COBRAND</ns0:NAME>\n" +
                        "                         <TYPE>STRING</ns0:TYPE>\n" +
                        "                         <VALUE>CRED_CARD_PINSET_GP120</ns0:VALUE>\n" +
                        "                     </ns0:TariffOption>\n" +
                        "                     <TariffOption>\n" +
                        "                         <NAME>CATEGORY_CARD</ns0:NAME>\n" +
                        "                         <TYPE>STRING</ns0:TYPE>\n" +
                        "                         <VALUE>Privilege Plus</ns0:VALUE>\n" +
                        "                     </ns0:TariffOption>\n" +
                        "                     <TariffOption>\n" +
                        "                         <NAME>SPECIAL_CODE</ns0:NAME>\n" +
                        "                         <TYPE>STRING</ns0:TYPE>\n" +
                        "                         <VALUE>11</ns0:VALUE>\n" +
                        "                     </ns0:TariffOption>\n" +
                        "                     <TariffOption>\n" +
                        "                         <NAME>INSTANT</ns0:NAME>\n" +
                        "                         <TYPE>STRING</ns0:TYPE>\n" +
                        "                         <VALUE>1</ns0:VALUE>\n" +
                        "                     </ns0:TariffOption>\n" +
                        "                     <TariffOption>\n" +
                        "                         <NAME>ATMRATE</ns0:NAME>\n" +
                        "                         <TYPE>FLOAT</ns0:TYPE>\n" +
                        "                         <VALUE>39.7</ns0:VALUE>\n" +
                        "                     </ns0:TariffOption>\n" +
                        "                 </ns0:TariffOptions>\n" +
                        "             </ns0:Tariff>\n" +
                        "         </ns0:Tariffs>\n" +
                        "         <RatesOfFunding>\n" +
                        "             <Rates>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.041</ns0:Rate>\n" +
                        "                     <Minterm>0</ns0:Minterm>\n" +
                        "                     <Maxterm>1</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.041</ns0:Rate>\n" +
                        "                     <Minterm>1</ns0:Minterm>\n" +
                        "                     <Maxterm>2</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.041</ns0:Rate>\n" +
                        "                     <Minterm>2</ns0:Minterm>\n" +
                        "                     <Maxterm>3</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.041</ns0:Rate>\n" +
                        "                     <Minterm>3</ns0:Minterm>\n" +
                        "                     <Maxterm>4</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.041</ns0:Rate>\n" +
                        "                     <Minterm>4</ns0:Minterm>\n" +
                        "                     <Maxterm>5</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.041</ns0:Rate>\n" +
                        "                     <Minterm>5</ns0:Minterm>\n" +
                        "                     <Maxterm>6</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.041</ns0:Rate>\n" +
                        "                     <Minterm>6</ns0:Minterm>\n" +
                        "                     <Maxterm>7</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.041</ns0:Rate>\n" +
                        "                     <Minterm>7</ns0:Minterm>\n" +
                        "                     <Maxterm>8</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.041</ns0:Rate>\n" +
                        "                     <Minterm>8</ns0:Minterm>\n" +
                        "                     <Maxterm>9</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.041</ns0:Rate>\n" +
                        "                     <Minterm>9</ns0:Minterm>\n" +
                        "                     <Maxterm>10</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.041</ns0:Rate>\n" +
                        "                     <Minterm>10</ns0:Minterm>\n" +
                        "                     <Maxterm>11</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.041</ns0:Rate>\n" +
                        "                     <Minterm>11</ns0:Minterm>\n" +
                        "                     <Maxterm>12</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.042</ns0:Rate>\n" +
                        "                     <Minterm>12</ns0:Minterm>\n" +
                        "                     <Maxterm>18</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.043</ns0:Rate>\n" +
                        "                     <Minterm>18</ns0:Minterm>\n" +
                        "                     <Maxterm>24</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.0435</ns0:Rate>\n" +
                        "                     <Minterm>24</ns0:Minterm>\n" +
                        "                     <Maxterm>30</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.044</ns0:Rate>\n" +
                        "                     <Minterm>30</ns0:Minterm>\n" +
                        "                     <Maxterm>36</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.044</ns0:Rate>\n" +
                        "                     <Minterm>36</ns0:Minterm>\n" +
                        "                     <Maxterm>60</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.044</ns0:Rate>\n" +
                        "                     <Minterm>60</ns0:Minterm>\n" +
                        "                     <Maxterm>84</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.044</ns0:Rate>\n" +
                        "                     <Minterm>84</ns0:Minterm>\n" +
                        "                     <Maxterm>120</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.044</ns0:Rate>\n" +
                        "                     <Minterm>120</ns0:Minterm>\n" +
                        "                     <Maxterm>180</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.044</ns0:Rate>\n" +
                        "                     <Minterm>180</ns0:Minterm>\n" +
                        "                     <Maxterm>240</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>840</ns0:CurrencyID>\n" +
                        "                     <Rate>0.044</ns0:Rate>\n" +
                        "                     <Minterm>240</ns0:Minterm>\n" +
                        "                     <Maxterm>360</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.011</ns0:Rate>\n" +
                        "                     <Minterm>0</ns0:Minterm>\n" +
                        "                     <Maxterm>1</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.011</ns0:Rate>\n" +
                        "                     <Minterm>1</ns0:Minterm>\n" +
                        "                     <Maxterm>2</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.011</ns0:Rate>\n" +
                        "                     <Minterm>2</ns0:Minterm>\n" +
                        "                     <Maxterm>3</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.011</ns0:Rate>\n" +
                        "                     <Minterm>3</ns0:Minterm>\n" +
                        "                     <Maxterm>4</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.011</ns0:Rate>\n" +
                        "                     <Minterm>4</ns0:Minterm>\n" +
                        "                     <Maxterm>5</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.011</ns0:Rate>\n" +
                        "                     <Minterm>5</ns0:Minterm>\n" +
                        "                     <Maxterm>6</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.011</ns0:Rate>\n" +
                        "                     <Minterm>6</ns0:Minterm>\n" +
                        "                     <Maxterm>7</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.011</ns0:Rate>\n" +
                        "                     <Minterm>7</ns0:Minterm>\n" +
                        "                     <Maxterm>8</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.011</ns0:Rate>\n" +
                        "                     <Minterm>8</ns0:Minterm>\n" +
                        "                     <Maxterm>9</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.011</ns0:Rate>\n" +
                        "                     <Minterm>9</ns0:Minterm>\n" +
                        "                     <Maxterm>10</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.011</ns0:Rate>\n" +
                        "                     <Minterm>10</ns0:Minterm>\n" +
                        "                     <Maxterm>11</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.011</ns0:Rate>\n" +
                        "                     <Minterm>11</ns0:Minterm>\n" +
                        "                     <Maxterm>12</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.011</ns0:Rate>\n" +
                        "                     <Minterm>12</ns0:Minterm>\n" +
                        "                     <Maxterm>18</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.011</ns0:Rate>\n" +
                        "                     <Minterm>18</ns0:Minterm>\n" +
                        "                     <Maxterm>24</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.011</ns0:Rate>\n" +
                        "                     <Minterm>24</ns0:Minterm>\n" +
                        "                     <Maxterm>30</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.011</ns0:Rate>\n" +
                        "                     <Minterm>30</ns0:Minterm>\n" +
                        "                     <Maxterm>36</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.013</ns0:Rate>\n" +
                        "                     <Minterm>36</ns0:Minterm>\n" +
                        "                     <Maxterm>60</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.013</ns0:Rate>\n" +
                        "                     <Minterm>60</ns0:Minterm>\n" +
                        "                     <Maxterm>84</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.013</ns0:Rate>\n" +
                        "                     <Minterm>84</ns0:Minterm>\n" +
                        "                     <Maxterm>120</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.013</ns0:Rate>\n" +
                        "                     <Minterm>120</ns0:Minterm>\n" +
                        "                     <Maxterm>180</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.013</ns0:Rate>\n" +
                        "                     <Minterm>180</ns0:Minterm>\n" +
                        "                     <Maxterm>240</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>978</ns0:CurrencyID>\n" +
                        "                     <Rate>0.013</ns0:Rate>\n" +
                        "                     <Minterm>240</ns0:Minterm>\n" +
                        "                     <Maxterm>360</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.075</ns0:Rate>\n" +
                        "                     <Minterm>0</ns0:Minterm>\n" +
                        "                     <Maxterm>1</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.075</ns0:Rate>\n" +
                        "                     <Minterm>1</ns0:Minterm>\n" +
                        "                     <Maxterm>2</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.076</ns0:Rate>\n" +
                        "                     <Minterm>2</ns0:Minterm>\n" +
                        "                     <Maxterm>3</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.0773</ns0:Rate>\n" +
                        "                     <Minterm>3</ns0:Minterm>\n" +
                        "                     <Maxterm>4</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.0787</ns0:Rate>\n" +
                        "                     <Minterm>4</ns0:Minterm>\n" +
                        "                     <Maxterm>5</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.08</ns0:Rate>\n" +
                        "                     <Minterm>5</ns0:Minterm>\n" +
                        "                     <Maxterm>6</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.0806</ns0:Rate>\n" +
                        "                     <Minterm>6</ns0:Minterm>\n" +
                        "                     <Maxterm>7</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.0813</ns0:Rate>\n" +
                        "                     <Minterm>7</ns0:Minterm>\n" +
                        "                     <Maxterm>8</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.082</ns0:Rate>\n" +
                        "                     <Minterm>8</ns0:Minterm>\n" +
                        "                     <Maxterm>9</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.0827</ns0:Rate>\n" +
                        "                     <Minterm>9</ns0:Minterm>\n" +
                        "                     <Maxterm>10</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.0833</ns0:Rate>\n" +
                        "                     <Minterm>10</ns0:Minterm>\n" +
                        "                     <Maxterm>11</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.084</ns0:Rate>\n" +
                        "                     <Minterm>11</ns0:Minterm>\n" +
                        "                     <Maxterm>12</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.084</ns0:Rate>\n" +
                        "                     <Minterm>12</ns0:Minterm>\n" +
                        "                     <Maxterm>18</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.085</ns0:Rate>\n" +
                        "                     <Minterm>18</ns0:Minterm>\n" +
                        "                     <Maxterm>24</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.0855</ns0:Rate>\n" +
                        "                     <Minterm>24</ns0:Minterm>\n" +
                        "                     <Maxterm>30</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.086</ns0:Rate>\n" +
                        "                     <Minterm>30</ns0:Minterm>\n" +
                        "                     <Maxterm>36</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.088</ns0:Rate>\n" +
                        "                     <Minterm>36</ns0:Minterm>\n" +
                        "                     <Maxterm>60</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.088</ns0:Rate>\n" +
                        "                     <Minterm>60</ns0:Minterm>\n" +
                        "                     <Maxterm>84</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.088</ns0:Rate>\n" +
                        "                     <Minterm>84</ns0:Minterm>\n" +
                        "                     <Maxterm>120</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.088</ns0:Rate>\n" +
                        "                     <Minterm>120</ns0:Minterm>\n" +
                        "                     <Maxterm>180</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.088</ns0:Rate>\n" +
                        "                     <Minterm>180</ns0:Minterm>\n" +
                        "                     <Maxterm>240</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "                 <Rate>\n" +
                        "                     <CurrencyID>810</ns0:CurrencyID>\n" +
                        "                     <Rate>0.088</ns0:Rate>\n" +
                        "                     <Minterm>240</ns0:Minterm>\n" +
                        "                     <Maxterm>360</ns0:Maxterm>\n" +
                        "                 </ns0:Rate>\n" +
                        "             </ns0:Rates>\n" +
                        "             <CreateDate>2018-09-25T23:02:12+03:00</ns0:CreateDate>\n" +
                        "         </ns0:RatesOfFunding>\n" +
                        "         <ExchangeRateList>\n" +
                        "             <ExchangeRate>\n" +
                        "                 <Currency>840</ns0:Currency>\n" +
                        "                 <Rate>74.2529</ns0:Rate>\n" +
                        "                 <Rate_Date>2020-12-07</ns0:Rate_Date>\n" +
                        "             </ns0:ExchangeRate>\n" +
                        "             <ExchangeRate>\n" +
                        "                 <Currency>978</ns0:Currency>\n" +
                        "                 <Rate>90.2618</ns0:Rate>\n" +
                        "                 <Rate_Date>2020-12-07</ns0:Rate_Date>\n" +
                        "             </ns0:ExchangeRate>\n" +
                        "             <ExchangeRate>\n" +
                        "                 <Currency>810</ns0:Currency>\n" +
                        "                 <Rate>1</ns0:Rate>\n" +
                        "                 <Rate_Date>2024-08-16</ns0:Rate_Date>\n" +
                        "             </ns0:ExchangeRate>\n" +
                        "         </ns0:ExchangeRateList>\n" +
                        "         <RegionParameters>\n" +
                        "             <Customer_ID>476894702</ns0:Customer_ID>\n" +
                        "             <Min_Living_Cost_in_Region_Living_RUR>10000</ns0:Min_Living_Cost_in_Region_Living_RUR>\n" +
                        "             <Average_Wage_in_Region_of_Employer_RUR>20000</ns0:Average_Wage_in_Region_of_Employer_RUR>\n" +
                        "         </ns0:RegionParameters>\n" +
                        "         <PSKLimits>\n" +
                        "             <PSKLimit>\n" +
                        "                 <MAX_PSK>29.088</ns0:MAX_PSK>\n" +
                        "                 <MINLIMIT>0</ns0:MINLIMIT>\n" +
                        "                 <MAXLIMIT>29999</ns0:MAXLIMIT>\n" +
                        "             </ns0:PSKLimit>\n" +
                        "             <PSKLimit>\n" +
                        "                 <MAX_PSK>29.7</ns0:MAX_PSK>\n" +
                        "                 <MINLIMIT>30000</ns0:MINLIMIT>\n" +
                        "                 <MAXLIMIT>99999</ns0:MAXLIMIT>\n" +
                        "             </ns0:PSKLimit>\n" +
                        "             <PSKLimit>\n" +
                        "                 <MAX_PSK>29.7</ns0:MAX_PSK>\n" +
                        "                 <MINLIMIT>100000</ns0:MINLIMIT>\n" +
                        "                 <MAXLIMIT>299999</ns0:MAXLIMIT>\n" +
                        "             </ns0:PSKLimit>\n" +
                        "             <PSKLimit>\n" +
                        "                 <MAX_PSK>29.075</ns0:MAX_PSK>\n" +
                        "                 <MINLIMIT>300000</ns0:MINLIMIT>\n" +
                        "                 <MAXLIMIT>9999999</ns0:MAXLIMIT>\n" +
                        "             </ns0:PSKLimit>\n" +
                        "         </ns0:PSKLimits>\n" +
                        "     </ns0:ProductCatalogueResponse>\n" +
                        "     <ResultMsg>\n" +
                        "         <ResultCode>1</ns0:ResultCode>\n" +
                        "         <ResultMessage>OK ( Записи найдены )</ns0:ResultMessage>\n" +
                        "     </ns0:ResultMsg>\n" +
                        "     <CurrentDate>2024-08-16</ns0:CurrentDate>\n" +
                        " </ns0:ProductCatalogueInquiryResponse>\n" +
                        "</root>";

            logger.info("=========================Request headers:==============================");
            logger.info(HeadersGet(request));
            logger.info("=========================Request body:===================================");
            logger.info(rq);
            logger.info("=========================Responce body:==============================");
            logger.info(main_resp2);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(main_resp2);
    }

    @GetMapping(value = "/CreditRegistry_GetStopList_IPOTEKA", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_06_20_StopList(HttpServletRequest request){
        String response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                "<soap:Body>"+
                " <ResultMsg>\n" +
                "     <ResultCode>1</ResultCode>\n" +
                "     <ResultMessage />\n" +
                " </ResultMsg>\n" +
                "</soap:Body>\n" +
                "</soap:Envelope>";

        String main_resp2="<?xml version=\"\"1.0\"\" encoding=\"\"UTF-8\"\"?>\n" +
                "<Response xmlns=\"\"http://mbtc.ru/xws/StopList\"\" xmlns:SOAP-ENV=\"\"http://schemas.xmlsoap.org/soap/envelope/\"\" xmlns:soap=\"\"http://schemas.xmlsoap.org/soap/envelope/\"\">\n" +
                "<StatusRs>\n" +
                "<ErrorFlag>0</ErrorFlag>\n" +
                "</StatusRs>\n" +
                "<SearchRs xmlns:xsi=\"\"http://www.w3.org/2001/XMLSchema-instance\"\" xsi:nil=\"\"true\"\"/>\n" +
                "</Response>";
        return ResponseEntity.ok().body(main_resp2);
    }

    @GetMapping(value = "/WebServices/WsOperatorInfo.asmx", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_06_70_CheckPhoneNumbers(HttpServletRequest request){

        String response="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                "<soap:Body>"+
                " <Response>\n" +
                "     <SearchRs MatchInfoType=\"RADD\">\n" +
                "         <ResultMsg>\n" +
                "             <ResultCode>1</ResultCode>\n" +
                "             <ResultMessage>DONE. No data found.</ResultMessage>\n" +
                "         </ResultMsg>\n" +
                "     </SearchRs>\n" +
                "     <SearchRs MatchInfoType=\"LADD\">\n" +
                "         <ResultMsg>\n" +
                "             <ResultCode>1</ResultCode>\n" +
                "             <ResultMessage>DONE. No data found.</ResultMessage>\n" +
                "         </ResultMsg>\n" +
                "     </SearchRs>\n" +
                "     <SearchRs MatchInfoType=\"ID\">\n" +
                "         <ResultMsg>\n" +
                "             <ResultCode>1</ResultCode>\n" +
                "             <ResultMessage>DONE. No data found.</ResultMessage>\n" +
                "         </ResultMsg>\n" +
                "     </SearchRs>\n" +
                "     <SearchRs MatchInfoType=\"FIO\">\n" +
                "         <ResultMsg>\n" +
                "             <ResultCode>1</ResultCode>\n" +
                "             <ResultMessage>DONE. No data found.</ResultMessage>\n" +
                "         </ResultMsg>\n" +
                "     </SearchRs>\n" +
                "     <SearchRs MatchInfoType=\"MPH\">\n" +
                "         <ResultMsg>\n" +
                "             <ResultCode>1</ResultCode>\n" +
                "             <ResultMessage>DONE. No data found.</ResultMessage>\n" +
                "         </ResultMsg>\n" +
                "     </SearchRs>\n" +
                "     <SearchRs MatchInfoType=\"WRKCMP\">\n" +
                "         <ResultMsg>\n" +
                "             <ResultCode>1</ResultCode>\n" +
                "             <ResultMessage>DONE. No data found.</ResultMessage>\n" +
                "         </ResultMsg>\n" +
                "     </SearchRs>\n" +
                " </Response>\n" +
                "</soap:Body>\n" +
                "</soap:Envelope>";

        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/WebServices/WsExternalCalendar.asmx", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_09_10_ExternalCalendar(HttpServletRequest request,@RequestBody String rq){ //здесь же и DS_12_10_ExternalCalendar

            String soapAction = extract(rq, "soapAction=\"http://tempuri.org/", "\">");
            Logger logger = Logger.getLogger("MyLog");
            String response;

            if (soapAction.equals("GetTimeoutInConcordanceWithExternalCalendarData_WithCancel")) {
                String InputDateTime = extract(rq, "InputDateTime=\"", "\" Update_Cancel_TimeOut");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date date;
                try {
                    date = formatter.parse(InputDateTime);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.DATE, 5);
                Date dateWith5Days = cal.getTime();
                String date5 = formatter.format(dateWith5Days);
                cal.add(Calendar.DATE, 60);
                Date dateWith60Days = cal.getTime();
                String date60 = formatter.format(dateWith60Days);

/*
                response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<ConnectorOutput>\n" +
                        " <GetTimeoutInConcordanceWithExternalCalendarData_WithCancelResponse>\n" +
                        "     <ExternalCalendarOutputType WebUI_Id=\"CRDS_7100\" Cancel_TimeOut=\"" + date60 + "\" Exit_TimeOut=\"" + date5 + "\" Current_TimeOut=\"" + date5 + "\" Update_Cancel_TimeOut=\"0\" Type_Of_TimeOut=\"E\" />\n" +
                        " </GetTimeoutInConcordanceWithExternalCalendarData_WithCancelResponse>\n" +
                        "</ConnectorOutput>\n"+
                        "</soap:Body>\n" +
                        "</soap:Envelope>";*/

                response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                        "<soap:Body>\n" +
                        " <GetTimeoutInConcordanceWithExternalCalendarData_WithCancelResponse>\n" +
                        "     <ExternalCalendarOutputType WebUI_Id=\"${WebUI_Id}\" Cancel_TimeOut=\""+date60+"\" Exit_TimeOut=\""+date5+"\" Current_TimeOut=\""+date5+"\" Update_Cancel_TimeOut=\"0\" Type_Of_TimeOut=\"E\" />\n" +
                        " </GetTimeoutInConcordanceWithExternalCalendarData_WithCancelResponse>\n" +
                        "</soap:Body>\n" +
                        "</soap:Envelope>";

            } else { //DS_12_10_ExternalCalendar

                /*response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<ConnectorOutput>\n" +
                        " <GetIsWorkDayFlagFromExternalCalendarDataResponse>\n" +
                        "     <GetIsWorkDayFlagFromExternalCalendarDataResult IsWorkDay=\"1\" />\n" +
                        " </GetIsWorkDayFlagFromExternalCalendarDataResponse>\n" +
                        "</ConnectorOutput>";*/

                response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                        "<soap:Body>\n" +
                        " <GetIsWorkDayFlagFromExternalCalendarDataResponse>\n" +
                        "     <GetIsWorkDayFlagFromExternalCalendarDataResult IsWorkDay=\"1\" />\n" +
                        " </GetIsWorkDayFlagFromExternalCalendarDataResponse>\n" +
                        "</soap:Body>\n" +
                        "</soap:Envelope>";



            }
        try {
            FileHandler fh2 = new FileHandler("C:/Program Files/Apache Software Foundation/Tomcat 9.0/logs/MyLogFile2.log");
            logger.addHandler(fh2);
            SimpleFormatter l_formatter = new SimpleFormatter();
            fh2.setFormatter(l_formatter);
            String hdrs = "";

            logger.info("=========================ExternalCalendar==============================");

            logger.info("=========================Request headers:==============================");
            logger.info(HeadersGet(request));
            logger.info("=========================Request body:===================================");
            logger.info(rq);
            logger.info("=========================Responce body:==============================");
            logger.info(response);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(response);

        }

    @PostMapping(value = "/NcService.svc", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_14_10_NumberConversion(HttpServletRequest request){

        String response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                "<soap:Body>\n"+
                " <ConvertNumberToStringResponse>\n" +
                "     <ConvertNumberToStringResult>\n" +
                "         <SingleResult>\n" +
                "             <CuId>999</CuId>\n" +
                "             <Result>Тридцать тысяч рублей 00 копеек</Result>\n" +
                "             <Tag>1</Tag>\n" +
                "         </SingleResult>\n" +
                "         <SingleResult>\n" +
                "             <CuId>999</CuId>\n" +
                "             <Result>Тридцать тысяч рублей 00 копеек</Result>\n" +
                "             <Tag>2</Tag>\n" +
                "         </SingleResult>\n" +
                "         <SingleResult>\n" +
                "             <CuId>999</CuId>\n" +
                "             <Result>Тридцать четыре целых девяносто сотых</Result>\n" +
                "             <Tag>5</Tag>\n" +
                "         </SingleResult>\n" +
                "         <SingleResult>\n" +
                "             <CuId>999</CuId>\n" +
                "             <Result>Тридцать тысяч рублей 00 копеек</Result>\n" +
                "             <Tag>13</Tag>\n" +
                "         </SingleResult>\n" +
                "         <SingleResult>\n" +
                "             <CuId>999</CuId>\n" +
                "             <Result>Ноль целых пятьдесят четыре тысячных</Result>\n" +
                "             <Tag>17</Tag>\n" +
                "         </SingleResult>\n" +
                "         <SingleResult>\n" +
                "             <CuId>999</CuId>\n" +
                "             <Result>Тридцать четыре целых восемьсот семьдесят две тысячных</Result>\n" +
                "             <Tag>19</Tag>\n" +
                "         </SingleResult>\n" +
                "         <SingleResult>\n" +
                "             <CuId>999</CuId>\n" +
                "             <Result>Ноль рублей 00 копеек</Result>\n" +
                "             <Tag>22-476832306</Tag>\n" +
                "         </SingleResult>\n" +
                "         <SingleResult>\n" +
                "             <CuId>999</CuId>\n" +
                "             <Result>Ноль рублей 00 копеек</Result>\n" +
                "             <Tag>24-476832306</Tag>\n" +
                "         </SingleResult>\n" +
                "         <SingleResult>\n" +
                "             <CuId>999</CuId>\n" +
                "             <Result>Ноль рублей 00 копеек</Result>\n" +
                "             <Tag>24-476832306</Tag>\n" +
                "         </SingleResult>\n" +
                "         <SingleResult>\n" +
                "             <CuId>999</CuId>\n" +
                "             <Result>Тридцать девять тысяч триста семьдесят четыре рубля 75 копеек</Result>\n" +
                "             <Tag>27</Tag>\n" +
                "         </SingleResult>\n" +
                "         <SingleResult>\n" +
                "             <CuId>999</CuId>\n" +
                "             <Result>Двадцать две тысячи семьсот тридцать семь рублей восемь копеек</Result>\n" +
                "             <Tag>100</Tag>\n" +
                "         </SingleResult>\n" +
                "     </ConvertNumberToStringResult>\n" +
                " </ConvertNumberToStringResponse>\n" +
                "</soap:Body>\n" +
                "</soap:Envelope>";

        return ResponseEntity.ok().body(response);
    }


    @PostMapping(value = "/CRIF_AFS_Request_Sync", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_16_20_AFS(HttpServletRequest request,@RequestBody String rq){
        //String appId=extract(rq,"<app:id>","</app:id>");
        String appId=rq.substring(rq.indexOf("<app:id>") + "<app:id>".length(), rq.indexOf("</app:id>"));
        String action=rq.substring(rq.indexOf("<afs:action>") + "<afs:action>".length(), rq.indexOf("</afs:action>"));
        /*
        String response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ConnectorOutput>\n" +
                " <CFResponse>\n" +
                "     <action>"+action+"</action>\n" +
                "     <actionSNA>0</actionSNA>\n" +
                "     <sourceId>CreditFlow</sourceId>\n" +
                "     <ruleSetId>main</ruleSetId>\n" +
                "     <duration>55</duration>\n" +
                "     <appId>"+appId+"</appId>\n" +
                "     <appVersion>2</appVersion>\n" +
                "     <appsCount>10</appsCount>\n" +
                "     <rulesCount>3</rulesCount>\n" +
                "     <matchCount>19</matchCount>\n" +
                "     <queueStatus>0</queueStatus>\n" +
                "     <matchResult>\n" +
                "         <match>\n" +
                "             <appId>"+appId+"</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>BANK_OFFICE_IP</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000293640</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292805</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292678</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000291625</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000293964</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292802</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292809</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292804</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000293866</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000293640</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292805</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292678</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000291625</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000293964</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292802</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292809</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292804</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000293866</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "     </matchResult>\n" +
                "     <ResultMsg>\n" +
                "         <ResultCode>1</ResultCode>\n" +
                "         <ResultMessage>OK</ResultMessage>\n" +
                "     </ResultMsg>\n" +
                " </CFResponse>\n" +
                "</ConnectorOutput>";*/
        String response="";
        if (action.equals("matcUpdate")){
            response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                "<soap:Body>\n"+
                //"<ConnectorOutput>\n" +
                " <CFResponse>\n" +
                "     <action>"+action+"</action>\n" +
                "     <actionSNA>0</actionSNA>\n" +
                "     <sourceId>CreditFlow</sourceId>\n" +
                "     <ruleSetId>main</ruleSetId>\n" +
                "     <duration>55</duration>\n" +
                "     <appId>"+appId+"</appId>\n" +
                "     <appVersion>2</appVersion>\n" +
                "     <appsCount>10</appsCount>\n" +
                "     <rulesCount>3</rulesCount>\n" +
                //"     <matchCount>1</matchCount>\n" +
                "     <queueStatus>0</queueStatus>\n" +
                /*"     <matchResult>\n" +
                "         <match>\n" +
                "             <appId>"+appId+"</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>BANK_OFFICE_IP</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000293640</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292805</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292678</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000291625</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000293964</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292802</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292809</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292804</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000293866</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000293640</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292805</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292678</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000291625</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000293964</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292802</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292809</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292804</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000293866</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "     </matchResult>\n"+*/
                "     <ResultMsg>\n" +
                "         <ResultCode>1</ResultCode>\n" +
                "         <ResultMessage>OK</ResultMessage>\n" +
                "     </ResultMsg>\n" +
                " </CFResponse>\n" +
                //"</ConnectorOutput>\n"+
                "</soap:Body>\n" +
                "</soap:Envelope>";
        } else{
        response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                "<soap:Body>\n"+
                //"<ConnectorOutput>\n" +
                " <CFResponse>\n" +
                "     <action>"+action+"</action>\n" +
                "     <actionSNA>0</actionSNA>\n" +
                "     <sourceId>CreditFlow</sourceId>\n" +
                "     <ruleSetId>main</ruleSetId>\n" +
                "     <duration>55</duration>\n" +
                "     <appId>"+appId+"</appId>\n" +
                "     <appVersion>2</appVersion>\n" +
                "     <appsCount>10</appsCount>\n" +
                "     <rulesCount>3</rulesCount>\n" +
                "     <matchCount>1</matchCount>\n" +
                "     <queueStatus>0</queueStatus>\n" +
                "     <matchResult>\n" +
                "         <match>\n" +
                "             <appId>"+appId+"</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>BANK_OFFICE_IP</rule>\n" +
                "         </match>\n" +
                /*"         <match>\n" +
                "             <appId>0000293640</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292805</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292678</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000291625</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000293964</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292802</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292809</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292804</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000293866</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>SUSP_4</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000293640</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292805</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292678</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000291625</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000293964</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292802</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292809</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000292804</appId>\n" +
                "             <appVersion>1</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +
                "         <match>\n" +
                "             <appId>0000293866</appId>\n" +
                "             <appVersion>2</appVersion>\n" +
                "             <rule>NEW_RULE7</rule>\n" +
                "         </match>\n" +*/
                "     </matchResult>\n"+
                "     <ResultMsg>\n" +
                "         <ResultCode>1</ResultCode>\n" +
                "         <ResultMessage>OK</ResultMessage>\n" +
                "     </ResultMsg>\n" +
                " </CFResponse>\n" +
                //"</ConnectorOutput>\n"+
                "</soap:Body>\n" +
                "</soap:Envelope>";
        }

        try {
            Logger logger = Logger.getLogger("MyLog");
            FileHandler fh2 = new FileHandler("C:/Program Files/Apache Software Foundation/Tomcat 9.0/logs/MyLogFile_NO_MATCH.log");
            logger.addHandler(fh2);
            SimpleFormatter l_formatter = new SimpleFormatter();
            fh2.setFormatter(l_formatter);
            String hdrs = "";

            logger.info("=========================ExternalCalendar==============================");

            logger.info("=========================Request headers:==============================");
            logger.info(HeadersGet(request));
            logger.info("=========================Request body:===================================");
            logger.info(rq);
            logger.info("=========================Responce body:==============================");
            logger.info(response);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return ResponseEntity.ok().body(response);

    }

    @PostMapping(value = "/SOAP_TIBCO/PODB", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_26_25_SearchPresaleDB_EBPP(HttpServletRequest request){

        String response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                "<soap:Body>\n"+
                " <Resultsets>\n" +
                "     <ResultSet2>\n" +
                "         <Record2>\n" +
                "             <CONTACT_ID>-1</CONTACT_ID>\n" +
                "             <VAR_NUM>1</VAR_NUM>\n" +
                "             <PARAM_NAME>LOAN_PERIOD</PARAM_NAME>\n" +
                "             <PARAM_TYPE>INTEGER</PARAM_TYPE>\n" +
                "             <CHAR_VAL>12</CHAR_VAL>\n" +
                "             <INT_CREATE_DATE>2024-02-29T09:16:07+03:00</INT_CREATE_DATE>\n" +
                "         </Record2>\n" +
                "         <Record2>\n" +
                "             <CONTACT_ID>-1</CONTACT_ID>\n" +
                "             <VAR_NUM>1</VAR_NUM>\n" +
                "             <PARAM_NAME>MKK_APP_ID</PARAM_NAME>\n" +
                "             <PARAM_TYPE>STRING</PARAM_TYPE>\n" +
                "             <CHAR_VAL>2e6be3598deb4c83919cccb015ad603b</CHAR_VAL>\n" +
                "             <INT_CREATE_DATE>2024-02-29T09:16:07+03:00</INT_CREATE_DATE>\n" +
                "         </Record2>\n" +
                "     </ResultSet2>\n" +
                "     <error>0</error>\n" +
                "     <errtext>Ответ от ЕБПП для КРИФ получен</errtext>\n" +
                " </Resultsets>\n" +
                "</soap:Body>\n" +
                "</soap:Envelope>";

        String main_resp2="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<resultSet>\n" +
                " <outputSet>\n" +
                "     <RESULT>&lt;return&gt;&lt;/return&gt;</RESULT>\n" +
                " </outputSet>\n" +
                " <UnresolvedResultsets>&lt;?xml version=\"1.0\" encoding=\"UTF-8\"?&gt;\n" +
                "\n" +
                "&lt;Resultsets/&gt;</UnresolvedResultsets>\n" +
                "</resultSet>";

        String main_resp3="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
                "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "<SOAP-ENV:Body>\n" +
                "   <ns0:Resultsets xmlns:ns0=\"http://www.tibco.com/schemas/PersonalOffersDataBase/Types/Schema_Output_Get_CRIF.xsd\">\n" +
                "      <ResultSet2>\n" +
                "         <Record2>\n" +
                "            <CONTACT_ID>-1</CONTACT_ID>\n" +
                "            <VAR_NUM>1</VAR_NUM>\n" +
                "            <PARAM_NAME>LOAN_PERIOD</PARAM_NAME>\n" +
                "            <PARAM_TYPE>INTEGER</PARAM_TYPE>\n" +
                "            <CHAR_VAL>12</CHAR_VAL>\n" +
                "            <INT_CREATE_DATE>2024-02-29T09:16:07+03:00</INT_CREATE_DATE>\n" +
                "         </Record2>\n" +
                "         <Record2>\n" +
                "            <CONTACT_ID>-1</CONTACT_ID>\n" +
                "            <VAR_NUM>1</VAR_NUM>\n" +
                "            <PARAM_NAME>MKK_APP_ID</PARAM_NAME>\n" +
                "            <PARAM_TYPE>STRING</PARAM_TYPE>\n" +
                "            <CHAR_VAL>2e6be3598deb4c83919cccb015ad603b</CHAR_VAL>\n" +
                "            <INT_CREATE_DATE>2024-02-29T09:16:07+03:00</INT_CREATE_DATE>\n" +
                "         </Record2>\n" +
                "      </ResultSet2>\n" +
                "      <error>0</error>\n" +
                "      <errtext>Ответ от ЕБПП для КРИФ получен</errtext>\n" +
                "   </ns0:Resultsets>\n" +
                "</SOAP-ENV:Body>\n" +
                "</SOAP-ENV:Envelope>";

        return ResponseEntity.ok().body(main_resp3);
    }

    @GetMapping(value = "/Processes/Bussiness/crif_retail_card_new_hash", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_28_05_GetBranchID(HttpServletRequest request){

        String response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                "<soap:Body>\n"+
                " <NumberOfmgnCardResponse>\n" +
                "     <BranchID>0000</BranchID>\n" +
                "     <CoBrand>CRED_CARD_PINSET_GP120</CoBrand>\n" +
                "     <ResultMsg>\n" +
                "         <ResultCode>1</ResultCode>\n" +
                "         <ResultMessage>Success</ResultMessage>\n" +
                "     </ResultMsg>\n" +
                " </NumberOfmgnCardResponse>\n" +
                "</soap:Body>\n" +
                "</soap:Envelope>";

        String main_resp2="<resultSet>\n" +
                " <outputSet>\n" +
                " <RETURN_VALUE>0000</RETURN_VALUE>\n" +
                " <P_COBRAND>CRED_CARD_PINSET_GP120_FLAGMAN</P_COBRAND>\n" +
                " </outputSet> <UnresolvedResultsets><?xml version=\"1.0\" encoding=\"UTF-8\"> <Resultsets/&gt;</UnresolvedResultsets>\n" +
                " </resultSet>";
        return ResponseEntity.ok().body(main_resp2);

    }

    @GetMapping(value = "/Process/Business/Consumer/GetInterest", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_304_30_RBP_PSK_Reguest(@RequestBody String rq){
        //String ProcessID=extract(rq,"<ProcessID>","</ProcessID>");
        String ProcessID=rq.substring(rq.indexOf("<ProcessID>") + "<ProcessID>".length(), rq.indexOf("</ProcessID>"));
        //String ApplicationID=extract(rq,"<ApplicationID>","</ApplicationID>");
        String ApplicationID=rq.substring(rq.indexOf("<ApplicationID>") + "<ApplicationID>".length(), rq.indexOf("</ApplicationID>"));

        String response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                "<soap:Body>\n"+
                " <InterestRateInquiryResponse>\n" +
                "     <ProcessID>"+ProcessID+"</ProcessID>\n" +
                "     <ProcessName>CRDS</ProcessName>\n" +
                "     <InterestRateResponse>\n" +
                "         <ApplicationID>"+ApplicationID+"</ApplicationID>\n" +
                "         <InterestRate>24.9</InterestRate>\n" +
                "     </InterestRateResponse>\n" +
                "     <ResultMsg>\n" +
                "         <ResultCode>1</ResultCode>\n" +
                "         <ResultMessage>SUCCESS</ResultMessage>\n" +
                "     </ResultMsg>\n" +
                " </InterestRateInquiryResponse>\n" +
                "</soap:Body>\n" +
                "</soap:Envelope>";

        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/mi_so_ABS_Acc_Req", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_36_15_CallAutoABSAcc(@RequestBody String rq){ //здесь же и DS_944_30_CalcPSKInABS
        String Application_ID=extract(rq,"<Application_ID>","</Application_ID>");
        String configId=extract(rq,"configId=\"","\" soapAction");
        String response =null;

        if (configId.equals("_absAutoAccOpen")) {

            response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                    "<soap:Body>\n" +
                    " <ABS_Accounts_Opening_Response>\n" +
                    "     <ProcessID>CREDIT_CARDS</ProcessID>\n" +
                    "     <ProcessName>CREDIT_CARDS</ProcessName>\n" +
                    "     <ABS_Accounts_Opening_Response>\n" +
                    "         <Application_ID>"+Application_ID+"</Application_ID>\n" +
                    "         <Request_ID>921271451521</Request_ID>\n" +
                    "         <ABSAvailabilityFlag>1</ABSAvailabilityFlag>\n" +
                    "     </ABS_Accounts_Opening_Response>\n" +
                    "     <ResultMsg>\n" +
                    "         <ResultCode>1</ResultCode>\n" +
                    "         <ResultMessage>Success</ResultMessage>\n" +
                    "     </ResultMsg>\n" +
                    " </ABS_Accounts_Opening_Response>\n" +
                    "</soap:Body>\n" +
                    "</soap:Envelope>";



        } else {//DS_944_30_CalcPSKInABS

            response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                    "<soap:Body>\n" +
                    " <ABS_Accounts_Opening_Response>\n" +
                    "     <ProcessID>CREDIT_CARDS</ProcessID>\n" +
                    "     <ProcessName>CREDIT_CARDS</ProcessName>\n" +
                    "     <PSKOutput>\n" +
                    "         <Request_ID>"+Application_ID+"</Request_ID>\n" +
                    "         <Disbursed_Credit_Amount>0</Disbursed_Credit_Amount>\n" +
                    "         <PSK_Int>39.860</PSK_Int>\n" +
                    "         <PSK_Sum>86818.94</PSK_Sum>\n" +
                    "         <Max_PSK>80</Max_PSK>\n" +
                    "         <PSK_Excess>0</PSK_Excess>\n" +
                    "     </PSKOutput>\n" +
                    "     <ResultMsg>\n" +
                    "         <ResultCode>1</ResultCode>\n" +
                    "         <ResultMessage>Success</ResultMessage>\n" +
                    "     </ResultMsg>\n" +
                    " </ABS_Accounts_Opening_Response>\n" +
                    "</soap:Body>\n" +
                    "</soap:Envelope>";

        }
        return ResponseEntity.ok().body(response);
    }


    @GetMapping(value = "/mi_so_ABS_Acc_Retr", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_36_38_GetCreditInfoAuto(@RequestBody String rq){
        String Application_ID=extract(rq,"<Application_ID>","</Application_ID>");
        String Request_ID=extract(rq,"<Request_ID>","</Request_ID>");

        String response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                "<soap:Body>\n" +
                " <ABS_Accounts_Retrieval_Response>\n" +
                "     <ProcessID>CREDIT_CARDS</ProcessID>\n" +
                "     <ProcessName />\n" +
                "     <ABS_Accounts_Retrieval_Response>\n" +
                "         <Application_ID>"+Application_ID+"</Application_ID>\n" +
                "         <Request_ID>"+Request_ID+"</Request_ID>\n" +
                "         <AccountsOutput>\n" +
                "             <Credit_Contract_Number>0013-ND3/02847</Credit_Contract_Number>\n" +
                "             <IBSO_DOG_ID>921271452753</IBSO_DOG_ID>\n" +
                "             <Credit_Contract_date />\n" +
                "             <Credit_Contract_Expiration_Date />\n" +
                "             <Disbursed_Credit_Amount>0</Disbursed_Credit_Amount>\n" +
                "             <Monthly_Repayment_Amount />\n" +
                "             <Repayment_Start_Day />\n" +
                "             <Prior_Repayment_Moratorium_Months />\n" +
                "             <Prior_Repayment_Min_Amount />\n" +
                "             <Prior_Repayment_Fee_pr />\n" +
                "             <Penalty_Fee_Amount />\n" +
                "             <Delinquency_Fee_pr />\n" +
                "             <Interest_Rate_2_pr />\n" +
                "             <Effective_Interest_Rate />\n" +
                "             <Accreditive_Account />\n" +
                "             <Current_Account_Number />\n" +
                "             <Current_Account_Type />\n" +
                "             <Current_Account_Agreement_Number />\n" +
                "             <Current_Account_Agrrement_Date />\n" +
                "             <Loan_Account_Number />\n" +
                "             <Loan_Acc_Opening_Comission_pr />\n" +
                "             <Loan_Acc_Opening_Comission_Max_Amt />\n" +
                "             <Customer_Quality />\n" +
                "             <Loan_Acc_Opening_Commission_Min_Amt />\n" +
                "             <Loan_Acc_Opening_Commission_Calculated />\n" +
                "             <Delinquency_Amount />\n" +
                "         </AccountsOutput>\n" +
                "         <ABSAvailabilityFlag>1</ABSAvailabilityFlag>\n" +
                "     </ABS_Accounts_Retrieval_Response>\n" +
                "     <ResultMsg>\n" +
                "         <ResultCode>1</ResultCode>\n" +
                "         <ResultMessage>SUCCESS - IBSO</ResultMessage>\n" +
                "     </ResultMsg>\n" +
                " </ABS_Accounts_Retrieval_Response>\n" +
                "</soap:Body>\n" +
                "</soap:Envelope>";

        String main_resp2="<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n" +
                "<ns0:ABS_Accounts_Retrieval_Response xmlns:ns0=\"http://uralsib.ru/crif\"> \n" +
                "<ProcessID>CREDIT_CARDS</ProcessID> \n" +
                "<ProcessName/> \n" +
                "<ABS_Accounts_Retrieval_Response> \n" +
                "<Application_ID>"+Application_ID+"</Application_ID> \n" +
                "<Request_ID>"+Request_ID+"</Request_ID> \n" +
                "<AccountsOutput> \n" +
                "<Credit_Contract_Number/> \n" +
                "<Credit_Contract_date/> \n" +
                "<Credit_Contract_Expiration_Date/> \n" +
                "<Disbursed_Credit_Amount/> \n" +
                "<Monthly_Repayment_Amount/> \n" +
                "<Repayment_Start_Day/> \n" +
                "<Prior_Repayment_Moratorium_Months/> \n" +
                "<Prior_Repayment_Min_Amount/> \n" +
                "<Prior_Repayment_Fee_pr/> \n" +
                "<Penalty_Fee_Amount/> \n" +
                "<Delinquency_Fee_pr/> \n" +
                "<Interest_Rate_2_pr/> \n" +
                "<Effective_Interest_Rate/> \n" +
                "<Accreditive_Account/> \n" +
                "<Current_Account_Number/> \n" +
                "<Current_Account_Type/> \n" +
                "<Current_Account_Agreement_Number/> \n" +
                "<Current_Account_Agrrement_Date/> \n" +
                "<Loan_Account_Number/> \n" +
                "<Loan_Acc_Opening_Comission_pr/> \n" +
                "<Loan_Acc_Opening_Comission_Max_Amt/> \n" +
                "<Customer_Quality/> \n" +
                "<Loan_Acc_Opening_Commission_Min_Amt/> \n" +
                "<Loan_Acc_Opening_Commission_Calculated/> \n" +
                "<Delinquency_Amount/> \n" +
                "<ID_INS_DOG_CARD>948343615527</ID_INS_DOG_CARD> \n" +
                "</AccountsOutput> \n" +
                "<ABSAvailabilityFlag>1</ABSAvailabilityFlag> \n" +
                "</ABS_Accounts_Retrieval_Response> \n" +
                "<ResultMsg> \n" +
                "<ResultCode>1</ResultCode> \n" +
                "<ResultMessage>SUCCESS - IBSO</ResultMessage> \n" +
                "</ResultMsg> \n" +
                "</ns0:ABS_Accounts_Retrieval_Response> \n";

        return ResponseEntity.ok().body(main_resp2);
    }


    @GetMapping(value = "/mockCreditRegistry__SPARKBinding", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_53_10_CheckBankruptcySPARK(@RequestBody String rq){
        String CustomerID=extract(rq,"<CustomerID>","</CustomerID>");
        String response="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                "<soap:Body>\n" +
                " <Response>\n" +
                "     <Reports>\n" +
                "         <Report>\n" +
                "             <CustomerID>"+CustomerID+"</CustomerID>\n" +
                "             <ResultMsg>\n" +
                "                 <ResultCode>1</ResultCode>\n" +
                "                 <ResultMessage>OK</ResultMessage>\n" +
                "             </ResultMsg>\n" +
                "         </Report>\n" +
                "     </Reports>\n" +
                " </Response>\n" +
                "</soap:Body>\n" +
                "</soap:Envelope>";

        return ResponseEntity.ok().body(response);
    }


    @GetMapping(value = "/Process/Crif_SendCardOper", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_65_10_SendToDS(HttpServletRequest request){
        return ResponseEntity.ok().build();//нет примеров запросов и ответов
    }

    @GetMapping(value = "/Send_SMS_Service", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_906_10_SendSMS(HttpServletRequest request){
        return ResponseEntity.ok().body("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<soap:Envelope xlmns:soap=\"http://www.w3.org/2003/05/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\">\n" +
                "<soap:Body>\n" +
                " <ResultMsg ResultCode=\"0\" ResultMsg=\"OK\" />\n" +
                "</soap:Body>\n" +
                "</soap:Envelope>");
    }


    @GetMapping(value = "/G_gurda", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity G_gurda(HttpServletRequest request){//debug

        return ResponseEntity.ok().body(request.toString());
    }


}


